USE subway;

#################################################
#				network elements				#
#################################################
INSERT INTO zones (name, base_fare_one_minute) VALUES
("A", 0.17),
("B", 0.22)
;

INSERT INTO lines_ (name) VALUES 
("2"),
("11"),
("12"),
("13"),
("18")
;

INSERT INTO stations (name) VALUES
("Plac Cyryla Ratajskiego"), -- 1
("Fredry"),
("Most Teatralny"), -- 3
("Rynek Jeżycki"),
("Polna"), -- 5
("Żeromskiego"),
("Stare Zoo"), -- 7
("Rondo Kaponiera"),
("Zamek"), -- 9
("Poznań Główny"),
("Bałtyk"), -- 11
("Most Dworcowy"),
("Dworzec Zachodni"), -- 13
("Park Wilsona")
;

INSERT INTO route_sections (departure_station_id, destination_station_id, minutes, zone_name) VALUES
(9, 8, 2, "A"), -- 1
(8, 7, 1, "A"),
(7, 4, 3, "A"), -- 3
(4, 5, 2, "B"),
(5, 6, 2, "B"), -- 5
(10, 12, 2, "A"),
(12, 8, 3, "A"), -- 7
(8, 3, 1, "A"),
(3, 4, 3, "A"), -- 9
(10, 13, 4, "A"), 
(13, 8, 2, "A"), -- 11
(8, 3, 1, "A")
;

INSERT INTO lines_have_route_sections (line_name, route_section_id, section_no) VALUES
("2", 1, 1),
("2", 2, 2),
("2", 3, 3),
("2", 4, 4),
("2", 5, 5),

("18", 6, 1),
("18", 7, 2),
("18", 8, 3),
("18", 9, 4),
("18", 4, 5),
("18", 5, 6),

("12", 10, 1),
("12", 11, 2),
("12", 8, 3)
;

INSERT INTO subways (name, passenger_table_name, line_table_name, worker_table_name) VALUE
("Metro Poznańskie", "passengers", "lines_", "workers")
;

#################################################
#				commute resources				#
#################################################
INSERT INTO discounts (name, reduction_percentage) VALUES 
("Student",	51),
("Teacher",	33),
("Senior",	37)
;

INSERT INTO transit_passes (name, outermost_zone_name, number_of_days, price) VALUES
("Weekly A", 	"A",	7,		31),
("Weekly A+B", 	"B",	7,		45),
("Monthly A", 	"A",	31,		86),
("Monthly A+B",	"B",	31,		112),
("Yearly A",	"A",	366,	702),
("Yearly A+B",	"B",	366,	1067)
;

INSERT INTO passengers (name, discount_name, credit, transit_pass_name, pass_validity_starting_day) VALUES 
("Izabella Jętka",	"Student",	4.20,	"Monthly A+B",	'2025-01-21'),
("Michał Dętka",	"Teacher",	NULL,	"Yearly A",		'2024-10-01'),
("Zofia Cętka",		"Senior",	34.9,	NULL,			NULL),
("Jakub Piętka",	NULL,		NULL,	"Weekly A+B",	'2025-01-11'),
("Karol Zakrętka",	NULL,		NULL,	NULL,			NULL),
("Agata Smętka",	NULL,		NULL,	NULL,			NULL)
;

#################################################
#					workers						#
#################################################
INSERT INTO jobs (title) VALUES
("driver"),
("engineer"),
("station worker")
;

INSERT INTO workers (name, hourly_wage, job_title) VALUES
("Jan Osik",			17.99,	"driver"),
("Barbara Bigosik",		28,		"driver"),
("Jan Paweł Donosik",	21.37,	"driver"),
("Sławomira Grosik",	51.11,	"engineer"),
("Fryderyk Stosik",		23.3,	"station worker"),
("Kalina Głosik",		32.9,	"driver"),
("Jakub Losik",			19.75,	"driver"),
("Tatiana Termosik",	20.99,	"driver")
;
