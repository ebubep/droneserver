
## Drones

[[_TOC_]]

---

:scroll: **START**


### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Description

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has: 
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

This project  exposes a service via REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). 

The service allows:
- registering a drone;
- checking available drones for loading;
- check drone battery level for a given drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone; 


### Platform
Built with JDK 17 and Spring 2.7
Database is H2 in embedded mode 
Required data is pre-loaded in droneserver\src\main\resources\data.sql file
Database schema is at droneserver\src\main\resources\chema.sql

Build Instructions 
For Windows
Execute .\droneserver\gradlew.bat --configure-on-demand -x check bootRun

For POSIX systems 
Execute .\droneserver\gradlew --configure-on-demand -x check bootRun

### REST End-points 
http://localhost:8080/register
Test this end-point by passing the contents of register.json as a body parameter in Postman.
In Postman, I used Body -> raw ->json

http://localhost:8080/load
Test this end-point by passing the contents of load_drone.json as a body parameter in Postman.
In Postman, I used Body -> raw ->json

http://localhost:8080/check_loaded_items
Test this end-point by passing the contents of check_loaded_items.json as a body parameter in Postman.
In Postman, I used Body -> raw ->json

http://localhost:8080/check_battery_level
Test this end-point by passing the contents of check_loaded_items.json as a body parameter in Postman.

http://localhost:8080/check_available_drones
This end-point requires no input JSON parameter.

http://localhost:8080/view_all_drones
This end-point requires no input JSON parameter.

http://localhost:8080/view_battery_levels
This end-point requires no input JSON parameter.


:scroll: **END** 
