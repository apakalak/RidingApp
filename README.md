# Ride Sharing

You work for a ride sharing company. Your company wants you to develop a RESTful HTTP API using Spring Boot and MySQL.

## Overview

Your cars operate in many different cities. Each car has only one driver. But a driver may drive as many many cars as they want, though not at the same time. A driver can only pick up one passenger per trip. Each time a passenger gets in a car that is called a trip. A driver can get a citation or violation by the police for bad driving. If the driver gets 3 or more violations, then he cannot drive anymore. Each city has a day and night per kilometer rate.

## Specifications

- A city has a name and the state it resides, i.e., Bangalore, Karnataka
- A city has a day and night rate per km, i.e., 50 INR and 75 INR.
- A car has a make, model and year, i.e., 2016 Nissan Pathfinder.
- A car also has a type: Basic, Lux (there is an additional fee for Lux)
- A driver has a name, age, gender.
- A driver also has number of violations or tickets given out by police. (more than 3 violations and driver banned)
- A passenger has name, age, gender and credit balance on their account.
- Each trip should record:
  - The duration of the trip, i.e., 12.5 minutes
  - Start and Stop times
  - The car used on the trip
  - The passenger on the trip
  - The trip distance, i.e., 7km
  - Cost, i.e., 150 INR
  - Tip percent, i.e, 10%
  - Total cost, i.e., 165 INR

## Scenario

A passenger gets created, Sara, with a credit balance of 10,000 INR. She can go on as many trips as she wants as long as she has credit on her account. Bill is her driver. Bill drives a 2017 BMW X-5. This car is of type Lux - which is an additial 5% charge to the fare. He takes her on a trip and the trip lasts for 25 minutes taking her a distance of 10km. Bill is driving in Bangalore. Its day/night rate is 20/25 INR. Sara is riding at night so her rate is 25 INR per km * 10km (distance) = 250 INR + 5% for Lux = 250 + 12.5 = 262.5 INR. She gives 20% tip = 52.5 INR for total 315 INR - 10000 balance = updated balance 9685 INR.

## Warning

- TEST YOUR SERVICES AND CONTROLLERS, USE TEST SUITES
- ADD DATA VALIDATIONS/CONSTRAINTS

## API

- Create cities, cars, drivers, passengers
- Delete cities, cars, drivers and passengers - as long as no trips have occurred
- Methods to find cities, cars, drivers, passengers
- Add a violation or ticket to a driver, if the driver gets more than 3 violations then he is banned
- Add a trip (can be successful if the diver is not banned and passenger has enough money)
- Find all the drivers in a city
- Find all the cities where a driver works
- Find all the cars a driver owns
- Find the driver for a car
- Find all the trips a car has been on
- Find all the trips by a particular driver
- Given a trip, find the car
- Given a trip, find the driver
- Given a trip, find the passenger
- Find all the passengers for a specific city
- Given a passenger, find all the drivers who have given that person a ride
- Given a passenger - find the total amount spent on trips in the past day, week, month, year
- Given a passenger - find the shortest/longest trip
- Given a city - find the total money made on trips
- Given a driver - find the total money made