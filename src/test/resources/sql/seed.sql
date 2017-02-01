use ridingApp_test;

set FOREIGN_KEY_CHECKS = 0;
truncate table cities;
TRUNCATE table drivers;
truncate table cars;
TRUNCATE table passangers;
truncate table trips;
set FOREIGN_KEY_CHECKS = 1;


INSERT INTO cities (name,state,country,day_charge,night_charge,lux_tax)
    VALUES
      ('bengluru','karnataka','india',20,30,5),
      ('mangaluru','karnataka','india',25,35,10),
      ('mysuru','karnataka','india',15,20,5),
      ('cochin','kerala','india',30,35,5);

INSERT INTO drivers (name,age,gender,no_of_voilation,city_id)
VALUES
  ('sudhir',23,'MALE',0,2),
  ('magdhir',22,'MALE',1,1),
  ('shankari',30,'FEMALE',3,3),
  ('krishna',23,'MALE',0,3);

INSERT INTO cars (make,model, year, type,driver_id)
VALUES
  ('Maruthi','SX4',2010,'LUX',2),
  ('Maruthi','zen',1990,'BASIC',1),
  ('Maruthi','SWIFT',2010,'BASIC',2),
  ('Hyundai','i10',2010,'BASIC',2);

INSERT INTO passangers (name,age, gender,balance)
VALUES
  ('Rishi',22,'MALE',200),
  ('Abhi',11,'MALE',1000),
  ('Ridhi',10,'FEMALE',100);

INSERT INTO trips (car_id,pass_id,passanger_point, passanger_review,driver_point,driver_review,total_cost)
VALUES
  (1,1,2,'good',2,'good',200),
  (2,2,2,'good',2,'good',300);