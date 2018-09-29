#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Fri Sep 28 19:01:15 2018

@author: sultan
"""
import numpy as np
from inferFromJson import getPopTimes
import random
import pandas as pd

#want to return dict with nameMall : list of dicts of popularity in a day
def generateFakeData():
  mallPoptimeDict = getPopTimes()
  mallData = {}
  for name, weekPopularity in mallPoptimeDict.items():
    mallData[name] = genMonthData(name, weekPopularity)
    #should return list of 30 days's parking array starting from monday
    #returns [array1, array2, ..., array30]
  return mallData


#input "aziz mall", "[{name: "monday", data: [], ... }, ]
def genMonthData(name, weekPopularity):
  popDay = {}
  dayCopies = {}
  for item in weekPopularity:
    popDay[item["name"]] = item["data"]
    
  for day in popDay.keys():
    hourlyPoints= np.array(popDay[day])
    
    from scipy.interpolate import interp1d
    x = np.linspace(0, 24, num=24, endpoint=True)
    y = np.cos(-x**2/9.0)
    y = np.squeeze(hourlyPoints)
    f2 = interp1d(x, y, kind='cubic')
    
    xnew = np.linspace(0, 24, num=24*60, endpoint=True)
    
    popPercent = np.ceil(f2(xnew))
    popPercent[popPercent == -0] = 0
    popPercent[popPercent < 0] = 0
    
    if day == "Monday":
      dayCopies[day] = genDaysCopies(popPercent, name, 5)
    elif day == "Tuesday":
      dayCopies[day] = genDaysCopies(popPercent, name, 5)
    else:
      dayCopies[day] = genDaysCopies(popPercent, name, 4)
      
  return dayCopies
      
#input 
  #popPercent: (60*40, 1) array with popularity percentages
  #name  name of the day
  #numOfCopies how many simulated days of name
  #takes popularity percentages and shuffles then every 3 hours for variation
def genDaysCopies(popPercent, name, numOfCopies):
  generatedCopies = []
  for i in range(numOfCopies):
    newDayPopPercent = np.zeros(popPercent.shape)
    for timeChunk in range(24*60): #for every three hours in a day
      newDayPopPercent[timeChunk: (timeChunk+1)*60/60]= np.int(np.random.permutation(popPercent[timeChunk : (timeChunk+1)*60/60]) * (1 + random.uniform(0.0, 0.4)))
    generatedCopies.append(newDayPopPercent)
  return generatedCopies



fakeData = generateFakeData()

#mallPoptimeDict = getPopTimes()
#
#x_data = np.array(range(0,24))
#y_data = mallPoptimeDict["Aziz Mall"][4]["data"]
#plt.figure(figsize=(24, 100))
#plt.scatter(x_data, y_data)
#plt.show()
#    
#for i in fakeData["Aziz Mall"]["Friday"]:
#    x_data = np.array(range(0,1440))
#    y_data = i
#    plt.figure(figsize=(24, 100))
#    plt.scatter(x_data, y_data)
#    plt.show()

def getMallDF(name):
  days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
  weeklyDfs = []
  for day in days:
    #weeklyDfs.append(pd.DataFrame(np.array())
    weeklyDfs.append(pd.DataFrame(np.array(fakeData[name][day]).transpose()))
  
  return weeklyDfs
