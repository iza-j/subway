USE subway;

INSERT INTO station (name) VALUES
("Plac Cyryla Ratajskiego"),
("Fredry"),
("Most Teatralny"),
("Rynek Jeżycki"),
("Polna"),
("Żeromskiego"),
("Stare Zoo"),
("Rondo Kaponiera"),
("Zamek"),
("Święty Marcin"),
("Bałtyk"),
("Most Dworcowy"),
("Dworzec Zachodni"),
("Park Wilsona")
;

INSERT INTO zone (name) VALUES
("A"),
("B")
;

INSERT INTO line (name) VALUES 
("2"),
("3"),
("6"),
("8"),
("11"),
("12"),
("13"),
("14"),
("15"),
("18")
;

INSERT INTO discount (name, reduction_percentage) VALUES 
("Student",	51),
("Teacher",	33),
("Senior",	37)
;

INSERT INTO transit_pass (name, outermost_zone_name, number_of_days, price) VALUES
("Weekly A", 	"A",	7,		31),
("Weekly A+B", 	"B",	7,		45),
("Monthly A", 	"A",	31,		86),
("Monthly A+B",	"B",	31,		112),
("Yearly A",	"A",	366,	702),
("Yearly A+B",	"B",	366,	1067)
;

INSERT INTO passenger (name, discount_name, credit, transit_pass_name, pass_validity_starting_day) VALUES 
("Izabella Jętka",	"Student",	4.20,	"Monthly A+B",	2025-01-07),
("Michał Dętka",	"Teacher",	NULL,	"Yearly A",		2024-10-01),
("Zofia Cętka",		"Senior",	34.9,	NULL,			NULL),
("Jakub Piętka",	NULL,		NULL,	"Weekly A+B",	2025-01-21)
;

INSERT INTO worker (name, hourly_wage) VALUES
("Jan Osik",			17.99),
("Barbara Bigosik",		28),
("Jan Paweł Donosik",	21.37),
("Sławomira Grosik",	51.11),
("Fryderyk Stosik",		NULL)
;
