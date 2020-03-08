package com.demo.cinemaapi.config;

import io.github.bucket4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class RequestThrottlingFilter implements Filter {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Bucket requestBucket = this.buckets.computeIfAbsent(username, key -> getUserBucket());

        ConsumptionProbe probe = requestBucket.tryConsumeAndReturnRemaining(1);

        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (probe.isConsumed()) {
            response.addHeader("X-Rate-Limit-Remaining",
                    Long.toString(probe.getRemainingTokens()));
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        response.addHeader("X-Rate-Limit-Retry-After-Milliseconds",
                Long.toString(TimeUnit.NANOSECONDS.toMillis(probe.getNanosToWaitForRefill())));
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
    }

    private static Bucket getUserBucket() {
        return Bucket4j.builder()
                .addLimit(Bandwidth.classic(5, Refill.intervally(5, Duration.ofMinutes(1))))
                .build();
    }
}
