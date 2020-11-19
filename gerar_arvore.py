#!/usr/bin/env python
# coding: utf-8

# In[1]:


import pandas as pd
import numpy as np
from sklearn import tree

train = pd.read_csv('ArvoreDefinitiva.csv')    
y_train = train['Caro']
x_train = train.drop(['Caro'], axis=1).values 
decision_tree = tree.DecisionTreeClassifier(max_depth = 20)
decision_tree.fit(x_train, y_train)

with open("ArvoreDefinitiva.dot", 'w') as f:
     f = tree.export_graphviz(decision_tree,
                              out_file=f,
                              max_depth = 20,
                              impurity = True,
                              feature_names = list(train.drop(['Caro'], axis=1)),
                              class_names = ['False', 'True'],
                              rounded = True,
                              filled= True )


# In[ ]:




