#!/usr/bin/env python
# coding: utf-8

# In[1]:


import sys
import pandas as pd
from sqlalchemy import create_engine

import pymysql


# In[2]:


df = pd.read_csv('title.principals.tsv', sep='\t')


# In[3]:


df.head()


# In[4]:


df = df[df['category'].isin(['self', 'actor','actress'])]
df.drop(['ordering', 'category', 'job'], axis=1, inplace=True)
df['characters'].replace(to_replace ='\[|\]|"|', value = '', inplace=True, regex = True)
df.head()


# In[5]:


df.rename(columns={'tconst': 'movie_id','nconst': 'actor_id', 'characters': 'character_name'}, inplace=True)
df.head()


# In[6]:


df['actor_id'].replace(to_replace ='nm', value = '', inplace=True, regex = True)
df['actor_id'] = df['actor_id'].astype('int32')

df['movie_id'].replace(to_replace ='tt', value = '', inplace=True, regex = True)
df['movie_id'] = df['movie_id'].astype('int32')
df.head()


# In[7]:


tableName   = "appearances"
dataFrame   = df      

sqlEngine       = create_engine('mysql+pymysql://root:cinemalocal@127.0.0.1/cinema', pool_recycle=3600)
dbConnection    = sqlEngine.connect()

try:
    frame = dataFrame.to_sql(tableName, dbConnection, if_exists='replace');
except ValueError as vx:
    print(vx)
except Exception as ex:   
    print(ex)
else:
    print("Table %s created successfully."%tableName); 
finally:
    dbConnection.close()

