#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd
from sqlalchemy import create_engine

import pymysql


# In[9]:


df = pd.read_csv('name.basics.tsv', sep='\t')
df.head()
#| pd.to_numeric(df['deathYear'], errors='coerce') > 2013


# In[11]:


df = df[(~df['deathYear'].str.isnumeric()) | (pd.to_numeric(df['deathYear'], errors='coerce') > 2013)]
df.head()


# In[12]:


df.drop(['birthYear', 'deathYear', 'primaryProfession', 'knownForTitles'], axis=1, inplace=True)


# In[13]:


df.rename(columns={'nconst': 'actor_id','primaryName': 'name'}, inplace=True)
df.head()


# In[14]:


df['actor_id'] = df['actor_id'].replace(to_replace ='nm', value = '', regex = True)
df['actor_id'] = df['actor_id'].astype('int32')
df.head()


# In[21]:


tableName   = "Actors"
dataFrame   = df

sqlEngine       = create_engine('mysql+pymysql://root:cinemalocal@127.0.0.1/cinema', pool_recycle=3600)
dbConnection    = sqlEngine.connect()

try:
    frame = dataFrame.to_sql(tableName, dbConnection, if_exists='fail');
except ValueError as vx:
    print(vx)
except Exception as ex:   
    print(ex)
else:
    dbConnection.execute('ALTER TABLE `Actors` ADD PRIMARY KEY (`actor_id`);')
    print("Table %s created successfully."%tableName);   
finally:
    dbConnection.close()


# In[ ]:




