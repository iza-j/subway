DROP DATABASE IF EXISTS subway;
CREATE DATABASE subway;
USE subway;


#################################################
#				network elements				#
#################################################
CREATE TABLE zone (
	name VARCHAR(255) NOT NULL PRIMARY KEY,
	base_fare_one_minute DECIMAL(4,2) NOT NULL CHECK (base_fare_one_minute >= 0) -- values from 0 to 99.99
);


CREATE TABLE line (
	name VARCHAR(255) NOT NULL PRIMARY KEY
);


CREATE TABLE station (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL
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
	reduction_percentage DECIMAL(4,2) NOT NULL CHECK (reduction_percentage >= 0)
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
	credit DECIMAL(8,2) NULL CHECK (credit >= 0),
	transit_pass_name VARCHAR(255) NULL,
	pass_validity_starting_day DATE NULL,
    
	CONSTRAINT passenger_fk_discount_name FOREIGN KEY (discount_name) REFERENCES discount (name)
		ON DELETE SET NULL,
	CONSTRAINT passenger_fk_transit_pass_name FOREIGN KEY (transit_pass_name) REFERENCES transit_pass (name)
		ON DELETE RESTRICT
);


CREATE TABLE subway (
	name VARCHAR(255) NOT NULL PRIMARY KEY,
	passenger_table_name VARCHAR(255) NOT NULL,
    line_table_name VARCHAR(255) NOT NULL, -- can't name it 'lines' because it's a reserved keyword
    worker_table_name VARCHAR(255) NOT NULL
);


#################################################
#					workers						#
#################################################
CREATE TABLE job (
	title VARCHAR(255) NOT NULL PRIMARY KEY
);


CREATE TABLE worker (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	hourly_wage DECIMAL(8,2) NULL CHECK (hourly_wage >= 0),
    job_title VARCHAR(255) NULL,
    
    CONSTRAINT worker_fk_job_title FOREIGN KEY (job_title) REFERENCES job (title)
		ON DELETE SET NULL
);

