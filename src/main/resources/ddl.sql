DROP DATABASE IF EXISTS subway
;
CREATE DATABASE subway
;
USE subway
;

CREATE TABLE station (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE)
;

CREATE TABLE worker (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	hourly_wage DECIMAL NULL CHECK (hourly_wage >= 0),
	UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE)
;

CREATE TABLE line (
	name VARCHAR(255) NOT NULL PRIMARY KEY,
	UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE)
;

CREATE TABLE driver (
	worker_id BIGINT UNSIGNED NOT NULL,
	line_name VARCHAR(255) NOT NULL,
	UNIQUE INDEX id_UNIQUE (worker_id ASC) VISIBLE,
	INDEX name_idx (line_name ASC) VISIBLE,
	CONSTRAINT fk_driver_worker_id
		FOREIGN KEY (worker_id)
		REFERENCES worker (id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT fk_driver_line_name
		FOREIGN KEY (line_name)
		REFERENCES line (name)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION)
;

CREATE TABLE zone (
	name VARCHAR(255) NOT NULL PRIMARY KEY,
	base_fare_one_minute DECIMAL NOT NULL CHECK (base_fare_one_minute >= 0),
	UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE)
;

CREATE TABLE route_section (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	departure_station_id BIGINT UNSIGNED NOT NULL,
	destination_station_id BIGINT UNSIGNED NOT NULL,
	minutes BIGINT UNSIGNED NOT NULL,
	zone_name VARCHAR(255) NOT NULL,
	UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
	INDEX name_idx (zone_name ASC) VISIBLE,
	INDEX id_idx (departure_station_id ASC) VISIBLE,
	INDEX id_idx1 (destination_station_id ASC) VISIBLE,
	CONSTRAINT fk_zone_name
		FOREIGN KEY (zone_name)
		REFERENCES zone (name)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT fk_departure_station_id
		FOREIGN KEY (departure_station_id)
		REFERENCES station (id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT fk_destination_station_id
		FOREIGN KEY (destination_station_id)
		REFERENCES station (id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION)
;

CREATE TABLE discount (
	name VARCHAR(255) NOT NULL PRIMARY KEY,
	reduction_percentage DECIMAL NOT NULL CHECK (reduction_percentage >= 0),
	UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE)
;

CREATE TABLE transit_pass (
	name VARCHAR(255) NOT NULL PRIMARY KEY,
	outermost_zone_name VARCHAR(255) NOT NULL,
	number_of_days BIGINT UNSIGNED NOT NULL,
	price DECIMAL NOT NULL CHECK (price >= 0),
	UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE,
	INDEX name_idx (outermost_zone_name ASC) VISIBLE,
	CONSTRAINT fk_outermost_zone_name
		FOREIGN KEY (outermost_zone_name)
		REFERENCES zone (name)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION)
;

CREATE TABLE passenger (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	discount_name VARCHAR(255) NULL,
	credit DECIMAL NOT NULL CHECK (credit >= 0),
	transit_pass_name VARCHAR(255) NULL,
	pass_validity_starting_day DATE NULL,
	UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
	INDEX name_idx (discount_name ASC) VISIBLE,
	INDEX name_idx1 (transit_pass_name ASC) VISIBLE,
	CONSTRAINT fk_discount_name
		FOREIGN KEY (discount_name)
		REFERENCES discount (name)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT fk_transit_pass_name
		FOREIGN KEY (transit_pass_name)
		REFERENCES transit_pass (name)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION)
;

CREATE TABLE line_has_route_section (
	line_name VARCHAR(255) NOT NULL,
	route_section_id BIGINT UNSIGNED NOT NULL,
	section_no BIGINT UNSIGNED NOT NULL,
	INDEX id_idx (route_section_id ASC) VISIBLE,
	CONSTRAINT fk_line_name
		FOREIGN KEY (line_name)
		REFERENCES line (name)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT fk_route_section_id
		FOREIGN KEY (route_section_id)
		REFERENCES route_section (id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION)
;

CREATE TABLE station_worker (
	worker_id BIGINT UNSIGNED NOT NULL,
	station_id BIGINT UNSIGNED NOT NULL,
	UNIQUE INDEX worker_id_UNIQUE (worker_id ASC),
	CONSTRAINT fk_station_worker_id
		FOREIGN KEY (worker_id)
		REFERENCES worker (id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION,
	CONSTRAINT fk_station_id
		FOREIGN KEY (station_id)
		REFERENCES station (id)
		ON DELETE NO ACTION
		ON UPDATE NO ACTION)
;
