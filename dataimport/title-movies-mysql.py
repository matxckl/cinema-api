#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd
from sqlalchemy import create_engine

import pymysql


# In[2]:


df = pd.read_csv('title.basics.tsv', sep='\t')


# In[3]:


df.head()


# In[5]:


df.drop(df.columns[[1, 3, 4, 6, 7, 8]], axis=1, inplace=True)


# In[9]:


df = df[(pd.to_numeric(df['startYear'], errors='coerce') > 2014)]


# In[13]:


df.rename(columns={'tconst': 'movie_id','primaryTitle': 'title','startYear': 'year'}, inplace=True)
df.head()


# In[14]:


df['movie_id'] = df['movie_id'].replace(to_replace ='tt', value = '', regex = True)
df['movie_id'] = df['movie_id'].astype('int32')
df.head()


# In[15]:


tableName   = "Movies"

sqlEngine       = create_engine('mysql+pymysql://root:cinemalocal@127.0.0.1/cinema', pool_recycle=3600)
dbConnection    = sqlEngine.connect()

try:
    frame = df.to_sql(tableName, dbConnection, if_exists='fail');
except ValueError as vx:
    print(vx)
except Exception as ex:   
    print(ex)
else:
    dbConnection.execute('ALTER TABLE `Movies` ADD PRIMARY KEY (`movie_id`);')
    print("Table %s created successfully."%tableName);   
finally:
    dbConnection.close()


# In[ ]:




