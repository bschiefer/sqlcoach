/* ************************************************* */
/* Skript zum Anlegen der Daten fuer die SQL-Übungen */
/* Stand: 28.03.2000    Bernhard Schiefer            */
/* ************************************************* */

/* ACHTUNG: Constraints beachten ! */
alter session set constraints deferred;

delete from akte;
delete from projekt;
delete from abteilung;
delete from personal;

insert into Personal values (1, 'Donald',	'Duck',	1, 1201, 1000);
insert into Personal values (2, 'Dagobert',	'Duck',	1, 1202, 200);
insert into Personal values (3, 'Tick',		'Duck',	1, 1203, 50);
insert into Personal values (4, 'Trick',	'Duck',	1, 1203, 50);
insert into Personal values (5, 'Track',	'Duck',	1, 1203, 50);
insert into Personal values (6, 'Gustav',	'Gans',	23, 2201, 2000);
insert into Personal values (7, 'Gundula',	'Gans',	23, 2202, 0);
insert into Personal values (8, 'Daniel',	'Duesentrieb',	2, 2203, 5000);
insert into Personal values (10,'Karl',		'Meier',	1, 1204, 50);
insert into Personal values (21,'Klaus',	'Meier',	3, 3201, 2000);
insert into Personal values (71,'Micky',	'Maus',	3, 3201, 3000);
insert into Personal values (72,'Goofy',	NULL,	3, 3202, 1000);
insert into Personal values (73,'Minni',	'Maus',	3, 3203, 2000);
insert into Personal values (51,'Baghira',	NULL,	6, 6202, 100);
insert into Personal values (52,'Balou',	NULL,	6, 6203, 100);
insert into Personal values (81,'Tom', 		NULL,	7, 5201, 1500);
insert into Personal values (82,'Jerry',	NULL,	7, 5201, 1500);
insert into Personal values (89,'Hugo',		'Geiermeier',	7, 5205, 2000);
insert into Personal values (90,'Klaus',	'Meier',	4, 4201, 2000);
insert into Personal values (91,'Asterix',	NULL,	4, 4201, 2000);
insert into Personal values (92,'Obelix',	NULL,	4, 4201, 2000);
insert into Personal values (93,'Idefix',	NULL,	4, 4203, 2000);
insert into Personal values (94,'Majestix',	NULL,	4, 4204, 7000);

insert into Abteilung values (1, 'Buchhaltung',	10000,	1);
insert into Abteilung values (2, 'FuE',		15000,	71);
insert into Abteilung values (3, 'Immobilien',	4000,	91);
insert into Abteilung values (4, 'Marketing',	50000,	81);
insert into Abteilung values (5, 'Controling',	20000,	94);
insert into Abteilung values (6, 'Hausmeister', 1000,	94);
insert into Abteilung values (7, 'Kantine',	1000,	94);
insert into Abteilung values (8, 'Vorstand',	100000,	94);
insert into Abteilung values (9, 'Fuhrpark',	7000,	94);
insert into Abteilung values (10, 'Service',	30000,	71);
insert into Abteilung values (11, 'Einkauf',	15000,	1);
insert into Abteilung values (12, 'Verkauf',	30000,	1);

insert into Projekt values (1, 5000,	1);
insert into Projekt values (2, 1000,	1);
insert into Projekt values (3, 2200,	10);
insert into Projekt values (4, 100,	3);
insert into Projekt values (5, 500,	4);
insert into Projekt values (6, 1000,	1);
insert into Projekt values (7, 3000,	4);
insert into Projekt values (8, 1000,	8);
insert into Projekt values (9, 1000,	4);
insert into Projekt values (10, 1000,	7);
insert into Projekt values (23, 5500,	3);

insert into Akte values (1, '1.4.1999', 'Oberbuchhalter',	1000);
insert into Akte values (1, '1.1.1997', 'Buchhalter',		200);
insert into Akte values (2, '1.1.1997', 'Buchhalter',		200);
insert into Akte values (3, '1.1.1997', 'Hilfsbuchhalter',	50);
insert into Akte values (4, '1.1.1997', 'Hilfsbuchhalter',	50);
insert into Akte values (5, '1.1.1997', 'Hilfsbuchhalter',	50);
insert into Akte values (6, '1.1.1993', 'Angestellter',		2000);
insert into Akte values (7, '1.1.1999', 'Praktikant',		0);
insert into Akte values (8, '1.1.1999', 'Erfinder',		5000);
insert into Akte values (10, '1.1.1998', 'Hilfsbuchhalter',	50);
insert into Akte values (94, '1.3.1982', 'Schuhputzer',		100);
insert into Akte values (94, '1.5.1983', 'Angestellter',	1000);
insert into Akte values (94, '1.5.1989', 'Abteilungsleiter',	3000);
insert into Akte values (94, '1.5.1998', 'Hauptabteilungsleiter', 7000);
insert into Akte values (91, '1.5.1997', 'Abteilungsleiter',	2000);
insert into Akte values (71, '1.1.1992', 'Junior Berater',	1000);
insert into Akte values (71, '1.4.1993', 'Berater',		2000);
insert into Akte values (71, '1.4.1998', 'Senior Berater',	3000);
insert into Akte values (21, '1.1.1991', 'Junior Berater',	800);
insert into Akte values (21, '1.4.1992', 'Berater',		1200);
insert into Akte values (21, '1.4.1997', 'Senior Berater',	2000);
insert into Akte values (90, '1.1.1998', 'Angestellter',	2000);
insert into Akte values (91, '1.5.1993', 'Angestellter',	2000);
insert into Akte values (92, '1.5.1993', 'Angestellter',	2000);
insert into Akte values (93, '1.1.1994', 'Angestellter',	2000);






