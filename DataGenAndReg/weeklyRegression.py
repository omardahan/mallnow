#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Fri Sep 28 22:55:18 2018

@author: sultan
"""

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import datetime

def getPrediction(X, y):
  # Fitting Linear Regression to the dataset
  from sklearn.linear_model import LinearRegression
  lin_reg = LinearRegression()
  lin_reg.fit(X, y)
  
  # Fitting Polynomial Regression to the dataset
  from sklearn.preprocessing import PolynomialFeatures
  poly_reg = PolynomialFeatures(degree = 6)
  X_poly = poly_reg.fit_transform(X)
  poly_reg.fit(X_poly, y)
  lin_reg_2 = LinearRegression()
  lin_reg_2.fit(X_poly, y)
  
#  # Visualising the Linear Regression results
#  plt.scatter(X, y, color = 'red')
#  plt.plot(X, lin_reg.predict(X), color = 'blue')
#  plt.title('Truth or Bluff (Linear Regression)')
#  plt.xlabel('Position level')
#  plt.ylabel('Salary')
#  plt.show()
#  
#  # Visualising the Polynomial Regression results
#  plt.scatter(X, y, color = 'red')
#  plt.plot(X, lin_reg_2.predict(poly_reg.fit_transform(X)), color = 'blue')
#  plt.title('Truth or Bluff (Polynomial Regression)')
#  plt.xlabel('Position level')
#  plt.ylabel('Salary')
#  plt.show()
#  
#  # Visualising the Polynomial Regression results (for higher resolution and smoother curve)
#  X_grid = np.arange(min(X), max(X), 0.1)
#  X_grid = X_grid.reshape((len(X_grid), 1))
#  plt.scatter(X, y, color = 'red')
#  plt.plot(X_grid, lin_reg_2.predict(poly_reg.fit_transform(X_grid)), color = 'blue')
#  plt.title('Truth or Bluff (Polynomial Regression)')
#  plt.xlabel('Position level')
#  plt.ylabel('Salary')
#  plt.show()
  
  return lin_reg_2.predict(poly_reg.fit_transform(X))

days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
mallsParkingCapacity = {"Mall of Arabia": 2000, "Aziz Mall": 900, "Red Sea Mall": 1100, "Al Salaam Mall": 500  }

from fakeDataGen import getMallDF

#nameOfMall = "Aziz Mall"
for nameOfMall in mallsParkingCapacity.keys():
  dflist = getMallDF(nameOfMall)
  weeklyPrediction = {}
  for daysInd in range(len(dflist)):
    df = dflist[daysInd]
    df = df.mean(axis=1)
    df = np.ceil(df)
    df[df > 100] = 100
    df[df < 0] = 0
    df = pd.DataFrame(df)
    X = np.array(pd.DataFrame(np.arange(0, 60*24)).iloc[:, 0]).reshape(-1,1)
    y = np.array(df.iloc[:, 0]).reshape(-1,1)
    forEveryMinutePrediction = getPrediction(X,y)
    forEveryMinutePrediction = np.ceil(forEveryMinutePrediction)
    forEveryMinutePrediction[forEveryMinutePrediction < 0] = 0
    forEveryMinutePrediction[forEveryMinutePrediction == -0] = 0
    forEveryMinutePrediction[forEveryMinutePrediction > 100] = 100
    weeklyPrediction[days[daysInd]] = forEveryMinutePrediction * mallsParkingCapacity[nameOfMall]/100
  
  databasePrediction = {}
  ldatabasePediction = []
  datelist = []
  date = datetime.datetime.now()
  for dayInd in [6,0,1,2,3,4,5]:
    date +=  datetime.timedelta(days=1)
    datelist.append(str(date.date()))
    databasePrediction[str(date.date())] = weeklyPrediction[days[dayInd]]
    ldatabasePediction.append(weeklyPrediction[days[dayInd]])
    
  time =  datetime.datetime(100,1,1,00,00,00)
  indicesTime = []
  for i in range(0,1440):
    indicesTime.append(str(time.time()))
    time += datetime.timedelta(0,0,0,0,1)
  
  modIndexList = []
  modValuesList = []  
  for datestamp in databasePrediction.keys():
    i = 0
    for timestamp in indicesTime:
      modIndexList.append(datestamp + " " + timestamp)
      modValuesList.append(databasePrediction[datestamp][i])
      i += 1
      
  dbdf4 = pd.DataFrame(np.array(modValuesList).reshape(-1,1))
  dbdf4.index = modIndexList
  dbdf4.columns = [str(mallsParkingCapacity[nameOfMall])]
  
  dbdf4.to_csv(nameOfMall.lower().replace(" ", "") + ".csv", sep='\t')
  
#  dbdf2 = pd.DataFrame(np.array(databasePrediction.values()).reshape(-1,7), columns = databasePrediction.keys())
#  dbdf2 = dbdf2[datelist]
#  dbdf3 = dbdf2
#  dbdf3.index = indicesTime
#  
#  dbdf3.to_csv(nameOfMall.lower().replace(" ", "") + ".csv", sep='\t')