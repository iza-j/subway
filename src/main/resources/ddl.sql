DROP DATABASE IF EXISTS subway;
CREATE DATABASE subway;
USE subway;


#################################################
#				network elements				#
#################################################
CREATE TABLE station (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL
);


CREATE TABLE zone (
	name VARCHAR(255) NOT NULL PRIMARY KEY,
	base_fare_one_minute DECIMAL NOT NULL CHECK (base_fare_one_minute >= 0)
);


CREATE TABLE line (
	name VARCHAR(255) NOT NULL PRIMARY KEY
);


CREATE TABLE route_section (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	departure_station_id BIGINT UNSIGNED NOT NULL,
	destination_station_id BIGINT UNSIGNED NOT NULL,
	minutes BIGINT UNSIGNED NOT NULL,
	zone_name VARCHAR(255) NOT NULL,
    
	CONSTRAINT route_section_fk_departure_station_id FOREIGN KEY (departure_station_id) REFERENCES station (id)
		ON DELETE CASCADE,
	CONSTRAINT route_section_fk_destination_station_id FOREIGN KEY (destination_station_id) REFERENCES station (id)
		ON DELETE CASCADE,
	CONSTRAINT route_section_fk_zone_name FOREIGN KEY (zone_name) REFERENCES zone (name)
		ON DELETE RESTRICT
);


CREATE TABLE line_has_route_section (
	line_name VARCHAR(255) NOT NULL,
	route_section_id BIGINT UNSIGNED NOT NULL,
	section_no BIGINT UNSIGNED NOT NULL,
    
	CONSTRAINT line_has_route_section_fk_line_name FOREIGN KEY (line_name) REFERENCES line (name)
		ON DELETE CASCADE,
	CONSTRAINT line_has_route_section_fk_route_section_id FOREIGN KEY (route_section_id) REFERENCES route_section (id)
		ON DELETE RESTRICT
);


#################################################
#				commute resources				#
#################################################
CREATE TABLE discount (
	name VARCHAR(255) NOT NULL PRIMARY KEY,
	reduction_percentage DECIMAL NOT NULL CHECK (reduction_percentage >= 0)
);


CREATE TABLE transit_pass (
	name VARCHAR(255) NOT NULL PRIMARY KEY,
	outermost_zone_name VARCHAR(255) NOT NULL,
	number_of_days BIGINT UNSIGNED NOT NULL,
	price DECIMAL NOT NULL CHECK (price >= 0),
    
	CONSTRAINT transit_pass_fk_zone_name FOREIGN KEY (outermost_zone_name) REFERENCES zone (name)
		ON DELETE RESTRICT
);


CREATE TABLE passenger (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	discount_name VARCHAR(255) NULL,
	credit DECIMAL NOT NULL CHECK (credit >= 0),
	transit_pass_name VARCHAR(255) NULL,
	pass_validity_starting_day DATE NULL,
    
	CONSTRAINT passenger_fk_discount_name FOREIGN KEY (discount_name) REFERENCES discount (name)
		ON DELETE RESTRICT,
	CONSTRAINT passenger_fk_transit_pass_name FOREIGN KEY (transit_pass_name) REFERENCES transit_pass (name)
		ON DELETE RESTRICT
);


#################################################
#					workers						#
#################################################
CREATE TABLE worker (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	hourly_wage DECIMAL NULL CHECK (hourly_wage >= 0)
);


CREATE TABLE driver (
	worker_id BIGINT UNSIGNED NOT NULL,
	line_name VARCHAR(255) NULL,
    
	CONSTRAINT driver_fk_worker_id FOREIGN KEY (worker_id) REFERENCES worker (id)
		ON DELETE CASCADE,
	CONSTRAINT driver_fk_line_name FOREIGN KEY (line_name) REFERENCES line (name)
		ON DELETE SET NULL
);


CREATE TABLE station_worker (
	worker_id BIGINT UNSIGNED NOT NULL,
	station_id BIGINT UNSIGNED NULL,
    
	CONSTRAINT station_worker_fk_worker_id FOREIGN KEY (worker_id) REFERENCES worker (id)
		ON DELETE CASCADE,
	CONSTRAINT station_worker_fk_station_id FOREIGN KEY (station_id) REFERENCES station (id)
		ON DELETE SET NULL
);

