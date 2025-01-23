-- 10 "delete" statements
-- 10 "update" statements
-- 5 "alter table"
-- 5 statements with aggregation functions and "group by" with and without "having"

-- -- for error 1055:
-- SET sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY','')); 

USE subway
;

-- view all workers and their postings
SELECT id, name, hourly_wage, line_name, station_id 
	FROM worker w
	LEFT JOIN driver d ON w.id = d.worker_id
	LEFT JOIN station_worker s ON w.id = s.worker_id
;

-- view all existing route sections
SELECT route_section.id, departure_station.name AS departure, destination_station.name AS destination, minutes
	FROM route_section
    LEFT JOIN station AS departure_station ON route_section.departure_station_id = departure_station.id
    LEFT JOIN station AS destination_station ON route_section.destination_station_id = destination_station.id
    ORDER BY departure, destination ASC
;

-- view all route sections of a specific line
DROP PROCEDURE IF EXISTS view_line_sections;
-- delimiter differen tthan ';' is needed to signal which operations are part of a procedure
DELIMITER // 
CREATE PROCEDURE view_line_sections(arg VARCHAR(255)) 
BEGIN
	SELECT line_name, section_no, departure_station.name AS departure, destination_station.name AS destination, minutes, zone_name
		FROM route_section
		LEFT JOIN station AS departure_station ON route_section.departure_station_id = departure_station.id
		LEFT JOIN station AS destination_station ON route_section.destination_station_id = destination_station.id
		RIGHT JOIN line_has_route_section ON line_has_route_section.route_section_id = route_section.id
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
	FROM passenger p
    INNER JOIN transit_pass t ON p.transit_pass_name = t.name
;

-- update data of passengers whose transit passes have expired
SET SQL_SAFE_UPDATES = 0;
UPDATE passenger p INNER JOIN transit_pass t ON p.transit_pass_name = t.name
    SET p.transit_pass_name = NULL, p.pass_validity_starting_day = NULL
	WHERE DATE_ADD(pass_validity_starting_day, INTERVAL number_of_days DAY) < CURDATE()
;

-- view all passengers 
SELECT * FROM passenger
;

-- do a full outer join
SELECT *
	FROM driver d
	LEFT JOIN line l ON d.line_name = l.name
UNION
SELECT *
	FROM driver d
	RIGHT JOIN line l ON d.line_name = l.name
;


-- view existing lines and fares for their whole routes
SELECT line.name, SUM(base_fare_one_minute*minutes) AS base_fare
	FROM line
    LEFT JOIN line_has_route_section lhrs ON line.name = lhrs.line_name
    LEFT JOIN route_section rs ON lhrs.route_section_id = rs.id
	LEFT JOIN station dep ON rs.departure_station_id = dep.id
	LEFT JOIN station dest ON rs.destination_station_id = dest.id
    LEFT JOIN zone ON rs.zone_name = zone.name
    GROUP BY line.name
;
