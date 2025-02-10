-- -- for error 1055:
-- SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY','')); 

USE subway
;

-- add birth_year to the workers table
ALTER TABLE workers ADD birth_year BIGINT UNSIGNED NULL;
UPDATE workers SET birth_year = 1990 WHERE id = 1;
UPDATE workers SET birth_year = 1987 WHERE id = 2;
UPDATE workers SET birth_year = 1990 WHERE id = 3;
UPDATE workers SET birth_year = 1989 WHERE id = 4;
UPDATE workers SET birth_year = 1989 WHERE id = 5;

-- see whether drivers make more or less than other workers
SELECT IF(job_title="driver", TRUE, FALSE) AS is_a_driver, AVG(hourly_wage)
	FROM workers 
	GROUP BY is_a_driver
;

-- add line_name to the workers table
ALTER TABLE workers ADD line_name VARCHAR(255) NULL
;
ALTER TABLE workers ADD 
CONSTRAINT worker_fk_line_name FOREIGN KEY (line_name) REFERENCES lines_ (name)
	ON DELETE SET NULL
;
UPDATE workers SET line_name = "2" WHERE id = 1;
UPDATE workers SET line_name = "12" WHERE id = 3;

-- add station_id to the workers table
ALTER TABLE workers ADD station_id BIGINT UNSIGNED NULL
;
ALTER TABLE workers ADD 
CONSTRAINT worker_fk_station_id FOREIGN KEY (station_id) REFERENCES stations (id)
	ON DELETE SET NULL
;
UPDATE workers SET station_id = 8 WHERE id = 4;
UPDATE workers SET station_id = 10 WHERE id = 5;
SELECT * FROM workers
;

-- see average wage by year of birth
SELECT birth_year, AVG(hourly_wage)
	FROM workers 
	GROUP BY birth_year
;

-- see how many employees earn over 21 /hour
SELECT IF(hourly_wage > 21, "earning over 21", "earning 21 or less") AS wage, COUNT(workers.name) 
	FROM workers
    LEFT JOIN lines_ ON lines_.name = workers.line_name
    GROUP BY wage
;

-- view all existing route sections
SELECT route_sections.id, departure_station.name AS departure, destination_station.name AS destination, minutes
	FROM route_sections
    LEFT JOIN stations AS departure_station ON route_sections.departure_station_id = departure_station.id
    LEFT JOIN stations AS destination_station ON route_sections.destination_station_id = destination_station.id
    ORDER BY departure, destination ASC
;

-- view all route sections of a specific line
DROP PROCEDURE IF EXISTS view_line_sections;
-- delimiter differen tthan ';' is needed to signal which operations are part of a procedure
DELIMITER // 
CREATE PROCEDURE view_line_sections(arg VARCHAR(255)) 
BEGIN
	SELECT line_name, section_no, departure_station.name AS departure, destination_station.name AS destination, minutes, zone_name
		FROM route_sections
		LEFT JOIN stations AS departure_station ON route_sections.departure_station_id = departure_station.id
		LEFT JOIN stations AS destination_station ON route_sections.destination_station_id = destination_station.id
		RIGHT JOIN lines_have_route_sections ON lines_have_route_sections.route_section_id = route_sections.id
		WHERE line_name = arg
		ORDER BY section_no ASC
	;
END 
// DELIMITER ;
CALL view_line_sections("2");
CALL view_line_sections("12");
CALL view_line_sections("18");


-- view passengers that own transit passes
SELECT p.name, pass_validity_starting_day, number_of_days, DATE_ADD(pass_validity_starting_day, INTERVAL number_of_days DAY) AS pass_expiration_date
	FROM passengers p
    INNER JOIN transit_passes t ON p.transit_pass_name = t.name
;

-- update data of passengers whose transit passes have expired
SET SQL_SAFE_UPDATES = 0;
UPDATE passengers p INNER JOIN transit_passes t ON p.transit_pass_name = t.name
    SET p.transit_pass_name = NULL, p.pass_validity_starting_day = NULL
	WHERE DATE_ADD(pass_validity_starting_day, INTERVAL number_of_days DAY) < CURDATE()
;

-- delete and add some passengers
DELETE passengers FROM passengers WHERE id = 5
;
DELETE passengers FROM passengers WHERE name = "Agata Smętka" AND id = 6
;
INSERT INTO passengers (name) VALUE ("Janina Giętka")
;
SELECT * FROM passengers
;

-- -- delete all discounts
-- DELETE FROM discounts
-- ;
-- SELECT * FROM discounts
-- ;

-- do a full outer join for workers and lines to see which lines have no drivers
SELECT *
	FROM workers w
	LEFT JOIN lines_ l ON w.line_name = l.name
UNION
SELECT *
	FROM workers w
	RIGHT JOIN lines_ l ON w.line_name = l.name
;

-- view existing lines and calculate fares for their whole routes
SELECT lines_.name, SUM(base_fare_one_minute*minutes) AS base_fare
	FROM lines_
    LEFT JOIN lines_have_route_sections lhrs ON lines_.name = lhrs.line_name
    LEFT JOIN route_sections rs ON lhrs.route_section_id = rs.id
	LEFT JOIN stations dep ON rs.departure_station_id = dep.id
	LEFT JOIN stations dest ON rs.destination_station_id = dest.id
    LEFT JOIN zones ON rs.zone_name = zones.name
    GROUP BY lines_.name
    HAVING base_fare IS NOT NULL
;

-- -- delete subway lines that have no route sections
-- DELETE lines_ 
-- 	FROM lines_ 
-- 	LEFT JOIN lines_have_route_sections lhrs ON lines_.name = lhrs.line_name
-- 	WHERE lhrs.line_name IS NULL
-- ;

-- see how many lines arrive at each station
SELECT stations.name, COUNT(route_sections.id) 
	FROM stations 
    RIGHT JOIN route_sections ON stations.id = route_sections.destination_station_id
    GROUP BY name
;

-- -- fire everybody working at Rondo Kaponiera and Poznań Główny stations :(
-- DELETE workers 
-- 	FROM workers
--     WHERE station_id = 8 OR station_id = 10
-- ;
-- SELECT * FROM workers
-- ;
