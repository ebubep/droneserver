drop table if exists drone;
drop table if exists drone_model;
drop table if exists drone_state;
drop table if exists medication;
drop table if exists battery_log;
drop table if exists load_event;
drop table if exists load_items;

CREATE TABLE IF NOT EXISTS drone (
  id int  NOT NULL AUTO_INCREMENT,
  serial_number varchar(100) NOT NULL,
  model int NOT NULL,
  weight_limit int NOT NULL,
  battery int NOT NULL,
  drone_state int NOT NULL,
  created_at timestamp NULL ,
  updated_at timestamp NOT NULL ,
  PRIMARY KEY (id)); 


CREATE TABLE IF NOT EXISTS drone_model (
  id INTEGER NOT  NULL AUTO_INCREMENT,
  name varchar(20) NOT NULL,
  PRIMARY KEY (id)
) ;


CREATE TABLE IF NOT EXISTS  medication (
  id INTEGER  NOT  NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  weight float NOT NULL,
  code varchar(10) NOT NULL,
  image text NOT NULL,
  created_at timestamp NULL ,
  updated_at timestamp NOT NULL,
  PRIMARY KEY (id)
) ;

CREATE TABLE IF NOT EXISTS `battery_log` (
  `id` int  NOT  NULL AUTO_INCREMENT,
  `drone_id` int NOT NULL,
  `battery_level` int NOT NULL,
  PRIMARY KEY (`id`)
) ;



CREATE TABLE IF NOT EXISTS `load_event` (
  `id` int NOT NULL AUTO_INCREMENT,
  `drone_id` int NOT NULL,
    `operator_on_duty` varchar(50) NOT NULL,
  `start_date` timestamp NOT NULL ,
  `end_date` timestamp NOT NULL ,
  PRIMARY KEY (`id`)
);



CREATE TABLE IF NOT EXISTS `load_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `med_code` varchar(50) NOT NULL,
    `qty` int NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);
