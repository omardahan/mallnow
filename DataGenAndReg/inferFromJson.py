#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Fri Sep 28 15:34:20 2018

@author: sultan
"""

#monday = 0, ... , sunday 6
def getPopTimes():
  import json
  files = ["azizmall.json", "mallofarabia.json", 
           "redseamall.json", "salammall.json"]
  namePoptimes = {}
  
  for filename in files:
    with open(filename, 'r') as f:
      loaded = json.load(f)
      namePoptimes[loaded["name"]] = loaded["populartimes"]
      
  return namePoptimes
