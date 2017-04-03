DELETE FROM TASK;
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (1, 1, 7, 'Geben Sie den Nach- und Vornamen aller Angestellten aufsteigend sortiert aus.', 'SELECT nname, vname FROM personal ORDER BY nname asc, vname asc', '2007-05-30 07:19:27.992083', '2008-11-10 23:32:33.252000', 'Bedenken Sie, dass mehrere Angestellten den gleichen Nachnamen haben!', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (2, 1, 8, 'Finden Sie alle Angestellten, deren Gehalt zwischen 1500 und 3000 liegt.', 'SELECT * FROM Personal WHERE Gehalt BETWEEN 1500 AND 3000', '2007-05-30 07:19:28.617554', '2012-01-27 08:30:49.192000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (3, 1, 9, 'Finden Sie alle Angestellen mit ''ei'' im Nachnamen, sortiert nach Ihrer Personalnummer.', 'SELECT * FROM personal WHERE nname LIKE ''%ei%''
ORDER BY persnr
', '2007-05-30 07:19:29.735019', '2013-07-03 23:04:08.698000', 'Benutzen Sie den LIKE-Operator!', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (4, 1, 10, 'Finde alle Angestellten mit ''ma'' im Nachnamen,  sortiert nach Ihrem vollständigen Namen.', 'SELECT * FROM personal WHERE UPPER (nname) LIKE ''%MA%''
ORDER BY nname, vname
', '2007-05-30 07:19:30.361942', '2013-07-03 23:04:08.698000', 'Nutzen Sie LIKE und eine geeignete SQL-Funktion um Unterschiede in Groß-/Kleinschreibung auszugleichen', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (5, 1, 11, 'Finden Sie alle Angestellten, deren Nachnamen mehr als 4 Buchstaben hat!
Sortieren Sie nach der persnr', 'SELECT * FROM Personal WHERE nname LIKE ''_____%''
ORDER BY persnr
', '2007-05-30 07:19:31.015435', '2010-09-02 12:21:05.897000', 'Sie können mit LIKE oder mit einer geeigneten SQL-Funktion arbeiten.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (6, 1, 12, 'Finden Sie alle Angestellten, die keinen Nachnamen haben', 'SELECT * FROM Personal WHERE NName IS NULL', '2007-05-30 07:19:31.642937', '2012-01-27 08:30:49.192000', 'Wenn kein Nachname vergeben wurde, dann ist ein NULL-Wert gespeichert.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (7, 1, 13, 'Finden sie alle Akteneinträge, die ab 1998 mit einem Gehalt von über 2800 gemacht wurden', 'SELECT * FROM akte 
WHERE datum >= TO_DATE(''1998-01-01'', ''YYYY-MM-DD'') AND gehalt > 2800', '2007-05-30 07:19:32.347193', '2015-10-16 19:43:18.276000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (8, 2, 14, 'Wieviel Gehalt wird für alle Angestellten zusammen in Tabelle Personal (gehaltssumme) ausbezahlt?', 'SELECT sum(gehalt) gehaltssumme FROM personal', '2007-05-30 07:19:32.990330', '2013-07-21 01:58:50.683000', 'Nutzen Sie die SUM-Funktion', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (9, 2, 16, 'Bestimmen Sie die Anzahl der Mitarbeiter pro Projekt absteigend sortiert nach der Mitarbeiterzahl!', 'SELECT projnr, count(*) as anzahl FROM personal 
GROUP BY projnr  
ORDER BY anzahl DESC', '2007-05-30 07:19:33.619931', '2013-07-21 01:58:50.683000', 'Sie müssen nach der Projektnummer gruppieren!', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (10, 2, 17, 'Wie viele Projekte führen die Abteilungen im Mittel (Alias: mittl_projzahl) aus?', 'SELECT avg(projcnt) AS mittl_projzahl 
FROM (SELECT count(*) AS projcnt 
      FROM projekt GROUP BY abtnr)', '2007-05-30 07:19:34.245348', '2013-09-13 09:27:02.632000', 'Ermitteln Sie als Basistabelle zuerst, wie viele Projekte jede Abteilung hat (erfordert Gruppierung nach abtnr).
Über diese Werte dann den Durchschnitt berechnen.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (11, 2, 18, 'Finden Sie alle Telefonnummern, die mehr als 1mal vergeben wurden!', 'SELECT telefonnr, count(*) Anzahl FROM personal  GROUP BY  telefonnr having count(*) > 1', '2007-05-30 07:19:34.882146', '2013-07-21 01:58:50.683000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (12, 2, 19, 'Wieviel Gehalt wird pro Abteilung ausbezahlt? - Sortieren Sie nach der AbtNr - Es brauchen nur die Abteilungen, die Mitarbeiter haben, ausgegeben werden.', 'SELECT abtnr, SUM(gehalt) as Gehaltssumme
FROM Projekt JOIN Personal USING(projnr)
GROUP BY abtnr
ORDER BY abtnr
', '2007-05-30 07:19:35.526180', '2016-11-04 15:04:03.281000', 'Sie benötigen einen Join auf den Tabellen Projekt und Personal', 0, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (13, 2, 54, 'Geben Sie für alle Projekte, die mehr als 5 Mitarbeiter haben ODER für ihre Mitarbeiter zusammen mehr als 3000 Euro Gehalt zahlen müssen, die projnr, das zu zahlende Gehalt und die Anzahl der Mitarbeiter aus. Sortierung nach projnr.', 'SELECT projnr, sum(gehalt) AS gehaelter, count(*) AS anz_mitarbeiter 
FROM personal 
GROUP BY projnr 
HAVING count(*) > 5 OR sum(gehalt) > 3000
ORDER BY projnr
', '2007-05-30 07:19:36.249738', '2016-01-18 20:36:20.917000', '', 0, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (14, 2, 20, 'Wie viele Mitarbeiter haben die Projekte im Mittel (M_PRO_PROJ)?', 'SELECT AVG(mp) AS M_PRO_PROJ 
FROM (SELECT COUNT(*) AS mp FROM Personal GROUP BY projnr)', '2007-05-30 07:19:36.879901', '2013-07-21 01:58:50.683000', 'Bestimmen Sie zunächst die Anzahl der Mitarbeiter pro Projekt - und darauf aufbauend den Mittelwert.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (15, 3, 21, 'Erstellen Sie eine Telefonliste der Abteilungsleiter mit Abteilungsname, VName, NNamen und Telefonnummer, 
sortiert nach dem Abteilungsnamen.', 'SELECT abtname, vname, nname, telefonnr 
FROM Personal JOIN Abteilung ON persnr = chefnr 
ORDER BY abtname', '2007-05-30 07:19:37.595614', '2012-01-27 08:30:49.192000', 'Ein ganz einfacher Join mit ON', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (16, 3, 27, 'Geben Sie für jeden Angestellten mit einem ''a'' im Nachnamen aus: persnr, vname, nname sowie die Positionen seiner Akteneinträge, sofern Einträge vorhanden sind. Sortierung nach persnr und position.', 'SELECT persnr, vname, nname, akte.position
FROM personal LEFT JOIN akte USING(persnr)
WHERE nname LIKE ''%a%''
ORDER BY persnr, position
', '2007-05-30 07:19:38.245754', '2013-10-24 18:13:11.894000', 'Erfordert OUTER JOIN', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (17, 3, 24, 'Ermitteln Sie die Daten aller Mitarbeiter, die für ein Projekt arbeiten, das zur Abteilung des Abteilungsleiters Donald gehört.', 'SELECT  m.* FROM personal m 
JOIN projekt p ON m.projnr=p.projnr 
JOIN abteilung a ON p.abtnr = a.abtnr 
JOIN personal c ON a.chefnr = c.persnr 
WHERE c.vname = ''Donald''', '2007-05-30 07:19:38.871167', '2013-07-21 01:58:50.683000', 'Die Personaltabelle muss 2mal in dem Join vorkommen! Sie brauchen daher mind. einen Alias.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (18, 3, 23, 'Ermitteln Sie den Abteilungsleiter von Heinz.', 'SELECT c.* 
FROM personal m 
  JOIN projekt p USING (projnr)
  JOIN abteilung a USING (abtnr)
  JOIN personal c ON a.chefnr = c.persnr 
WHERE m.vname = ''Heinz''', '2007-05-30 07:19:39.510953', '2013-09-13 09:27:02.632000', 'Die Personaltabelle muss 2 mal in dem Join vorkommen! Sie brauchen daher mind. einen Alias.
Der Join umfasst insgesamt 4 Tabellen.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (19, 3, 105, 'Geben Sie alle 12 Abteilungen aus mit der Anzahl der ihnen zugeordneten Projekte! Sortierung nach der abtnr.', 'SELECT abtnr, abtname, COUNT(projnr) AS anz_proj
FROM Abteilung LEFT JOIN Projekt USING (abtnr) 
GROUP BY abtnr, abtname
ORDER BY abtnr
', '2007-05-30 07:19:40.159411', '2013-10-29 16:48:39.765000', 'Nicht alle Abteilungen haben zugeordnete Projekte!', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (20, 3, 125, 'Geben Sie alle Projekte aus und zu jedem Projekt den Namen des Abteilungsleiters, falls dieser dem Projekt zugeordnet ist! Die Sortierung soll aufsteigend nach der projnr erfolgen.', 'SELECT p.projnr, p.abtnr, vname, nname 
FROM projekt p LEFT JOIN abteilung a ON p.abtnr = a.abtnr 
LEFT JOIN personal pe ON pe.persnr = a.chefnr AND pe.projnr = p.projnr 
ORDER BY p.projnr', '2007-05-30 07:19:40.808861', '2013-07-21 01:58:50.683000', 'Da alle Projekte ausgegeben werden sollen, aber der Abteilungsleiter nicht bei allen Projekten mitarbeitet, müssen Sie einen OUTER JOIN einsetzen.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (21, 3, 26, 'Finden Sie aus den Akten alle Angestellte, die innerhalb eines Kalenderjahres eine Gehaltserhöhung von mindestens 1000 erhalten haben. Sortieren Sie nach der persnr und Jahr.', 'SELECT DISTINCT a.persnr, to_char(a.datum,''YYYY'') as Jahr
FROM Akte a 
JOIN Akte b ON a.persnr = b.persnr 
           AND a.datum < b.datum AND b.gehalt-a.gehalt >= 1000
           AND to_char(a.datum,''YYYY'') = to_char(b.datum,''YYYY'')
ORDER BY persnr, jahr
', '2007-05-30 07:19:41.438515', '2013-10-29 16:48:39.765000', 'Sie müssen jeweils 2 Akteneinträge miteinander joinen.

Das Jahr erhalten Sie mit to_char(datum,''YYYY'').', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (22, 4, 28, 'Bestimmen Sie die Abteilungen mit den meisten Projekten, sowie die Anzahl der zugehörigen Projekte (proj_anz)', 'SELECT abtnr, proj_anz 
FROM (SELECT abtnr, count(*) AS proj_anz FROM projekt GROUP BY abtnr) proj_anz1
WHERE proj_anz = (SELECT MAX(anz) 
                  FROM (SELECT count(*) AS anz FROM projekt GROUP BY abtnr) proj_anz2)', '2007-05-30 07:19:42.072733', '2015-01-10 14:34:57.739000', 'Benutzen Sie geschachtelte SELECT-Statements. Denken Sie daran, dass Sie auch in der FROM-Klausel eine Tabelle mit SELECT konstruieren können.', 0, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (23, 4, 29, 'Ermitteln Sie die durchschnittliche Anzahl von Projekten pro Abteilung', 'SELECT avg(panz) 
FROM (SELECT abtnr, COUNT(*) panz FROM projekt p GROUP BY abtnr) proj_anz', '2007-05-30 07:19:42.854724', '2012-01-27 08:30:49.192000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (24, 4, 30, 'Geben Sie ALLE Angestellten sowie die Anzahl ihrer Akteneinträge aus (Sortiert nach persnr). 
ACHTUNG: Nicht alle haben Akteneinträge!', 'SELECT p.persnr, p.nname, p.vname, COUNT(a.persnr) as anzahl 
FROM akte a RIGHT JOIN personal p on a.persnr = p.persnr 
GROUP BY p.persnr, p.nname, p.vname
ORDER BY persnr
', '2007-05-30 07:19:43.522714', '2012-01-27 08:30:49.192000', 'Sie benötigen einen OUTER JOIN !', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (25, 4, 31, 'Ermitteln Sie alle Angestellte, die Akteneinträge vor dem 01.01.2003 haben, sowie die Anzahl dieser Einträge! 
Sortierung absteigend nach der Anzahl der Einträge.
Anmerkung: Datumsformat kann bestimmt werden durch TO_DATE(''01.01.2003'',''DD.MM.YYYY'')', 'SELECT p.persnr, nname, vname, anzahl 
FROM personal p JOIN (SELECT persnr, COUNT(*) AS anzahl 
                      FROM Akte WHERE datum < TO_DATE(''2003-01-01'',''YYYY-MM-DD'') 
                      GROUP BY persnr) a ON p.persnr = a.persnr
ORDER BY anzahl DESC
', '2007-05-30 07:19:44.158113', '2014-06-23 15:57:15.336000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (26, 4, 32, 'Ermitteln Sie die Projekte mit dem höchsten Durchschnittsgehalt der beteiligten Personen!', 'SELECT projnr, AVG(gehalt) 
FROM personal 
GROUP BY projnr 
HAVING AVG(gehalt) = (SELECT MAX (avggehalt) 
                      FROM ( SELECT AVG (gehalt) avggehalt 
                             FROM personal GROUP BY projnr))', '2007-05-30 07:19:44.803116', '2011-07-09 14:41:31.604000', 'Subquery im HAVING erforderlich', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (27, 4, 33, 'Geben Sie für den Angestellten mit der persnr 94 alle Gehaltsänderungen zeitlich aufsteigend sortiert mit dem jeweiligen Datum aus.', 'SELECT b.gehalt-a.gehalt erhoehung, b.datum 
FROM Akte a JOIN Akte b ON a.persnr = b.persnr AND a.datum < b.datum 
WHERE a.persnr=94 
AND NOT EXISTS (SELECT datum FROM Akte 
                WHERE persnr=a.persnr AND datum > a.datum AND datum < b.datum)
ORDER BY b.datum
', '2007-05-30 07:19:45.467351', '2014-06-23 15:57:15.336000', 'Um die Differenz zwischen 2 Gehältern zu berechnen, müssen Sie einen Join zwischen 2 Akteneinträge durchführen.
Achten sie darauf, nur direkt aufeinanderfolgende Einträge zu vergleichen.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (28, 5, 34, 'Geben Sie die Personaldaten aller Angestellten aus, die keinen Akteneintrag haben.', 'SELECT * FROM personal 
WHERE persnr NOT IN (SELECT persnr FROM akte)', '2007-05-30 07:19:46.109039', '2012-01-27 08:30:49.192000', 'Es gibt mehrere Lösungen, eine einfache Variante besteht in der Verwendung von  NOT IN.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (29, 5, 35, 'Geben Sie alle Abteilungen (abtname,budget) nach dem Budget und dem abtname sortiert aus, für die es noch mindestens eine weitere gibt, die über das gleiche Budget verfügt.', 'SELECT abtname, budget FROM abteilung a 
WHERE EXISTS (SELECT * FROM abteilung b WHERE b.budget = a.budget AND a.abtnr <> b.abtnr) 
ORDER BY budget, abtname', '2007-05-30 07:19:46.785091', '2013-10-24 18:13:11.894000', 'Nutzen Sie EXISTS. 
Da die Abteilungstabelle 2 mal auftritt, benötigen Sie Alias-Namen für die Abteilungen', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (30, 5, 36, 'Geben Sie alle Mitarbeiter mit gleichen Namen aus! Verwenden Sie dazu: EXISTS.
Die Sortierung soll nach der persnr erfolgen.', 'SELECT persnr, nname, vname FROM personal p 
WHERE EXISTS(SELECT * FROM personal WHERE nname=p.nname AND vname=p.vname AND persnr<>p.persnr)
ORDER BY persnr
', '2007-05-30 07:19:47.463709', '2013-07-03 23:04:08.698000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (31, 5, 37, 'Ermitteln Sie den Angestellten mit dem längsten Nachnamen! Geben Sie den Namen und desen Länge aus (NNAME, LAENGE).', 'SELECT nname, length(nname) AS laenge FROM personal 
WHERE length(nname) = (SELECT MAX(length(nname)) FROM personal)', '2007-05-30 07:19:48.098519', '2013-10-29 16:48:39.765000', 'Die Längenfunktion ist length()', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (32, 5, 38, 'Geben Sie die Nummern aller Projekte an denen kein Angestellter mit Nachname ''Gans'' mitarbeitet 
in aufsteigender Reihenfolge aus', 'SELECT projnr FROM Projekt 
WHERE projnr NOT IN ( SELECT projnr FROM Personal 
                      WHERE nname = ''Gans'' ) 
ORDER BY projnr', '2007-05-30 07:19:48.764395', '2013-10-24 18:13:11.894000', 'Nutzen Sie NOT IN', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (33, 5, 39, 'Geben Sie die Angestellten mit dem geringsten Gehalt aus.', 'SELECT * FROM personal WHERE gehalt = (SELECT MIN(gehalt) FROM personal)', '2007-05-30 07:19:49.404865', '2012-01-27 08:30:49.192000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (34, 5, 40, 'Geben Sie den neusten Akteneintrag aus.', 'SELECT * FROM akte WHERE datum=(SELECT MAX(datum) FROM akte)', '2007-05-30 07:19:50.060640', '2012-01-27 08:30:49.192000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (35, 5, 41, 'Finden Sie alle Angestellten, die nicht am oberen oder unteren Ende mit ihrem Gehalt liegen. Sortierung nach pernr.', 'SELECT * FROM Personal 
WHERE gehalt>(SELECT MIN(gehalt)FROM Personal) 
  AND gehalt<(SELECT MAX(gehalt)FROM Personal)
ORDER BY persnr
', '2007-05-30 07:19:50.691858', '2013-10-29 16:48:39.765000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (36, 5, 42, 'Finden Sie alle Angestellten, die überdurchschnittlich viel verdienen', 'SELECT * FROM Personal 
WHERE gehalt>(SELECT AVG(gehalt)FROM Personal)', '2007-05-30 07:19:51.332930', '2012-01-27 08:30:49.192000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (39, 5, 45, 'Ermitteln Sie für jedes Projekt das Durchschnittsgehalt innerhalb des Projektes  und dessen
Abweichung vom Durchschnittsgehalt im Gesamtunternehmen jeweils auf 1 Stelle hinter dem Komma genau.
', 'SELECT projnr, round(AVG(gehalt),1) as d_gehalt, 
       ROUND( AVG(gehalt) - (SELECT AVG(gehalt) FROM Personal),1) AS abweichung
FROM Personal 
GROUP BY projnr
', '2007-06-18 06:54:31.521859', '2012-01-27 08:30:49.192000', 'Nutzen sie eine Subquery in der SELECT-Klausel um den Gesamtdurchschnitt zu bestimmen.
Runden mit ROUND(val,1)', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (40, 6, 42, 'Finden Sie die Namen aller Spalten der Tabelle ABTEILUNG.', 'SELECT column_name 
FROM user_tab_columns 
WHERE table_name = ''ABTEILUNG''', '2008-01-12 15:27:53.604409', '2010-09-02 12:21:05.897000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (41, 6, 43, 'Finden Sie die Tabellen mit den kürzesten Namen.', 'SELECT table_name FROM user_tables 
WHERE length(table_name) = (SELECT MIN(length(table_name)) FROM user_tables)
', '2008-01-12 15:33:58.223901', '2010-09-02 12:21:05.897000', 'Tabelle: user_tables', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (42, 7, 44, 'Bestimmen Sie die Namen und Vornamen aller männlichen Personen, aufsteigend sortiert.', 'SELECT name, vorname FROM person WHERE geschlecht = ''M'' ORDER BY name, vorname', '2008-01-13 11:26:21.812025', '2008-03-16 12:29:07.051000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (43, 7, 60, 'Bestimmen Sie die Namen und die Einnahmen aller  Personen, deren Einnahmen zwischen 200000 und 300000 liegen, absteigend nach den Einnahmen sortiert.', 'SELECT name, vorname,einnahmen FROM person 
WHERE einnahmen BETWEEN 200000 AND 300000 ORDER BY einnahmen DESC', '2008-01-13 11:33:10.160892', '2008-03-21 12:25:27.010000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (44, 8, 55, 'Geben Sie alle Personen (persnr, name) mit Ihrer Adresse (plz, ort) vom Typ P
sortiert nach dem Wohnort aus.', 'SELECT persnr, name, plz, ort, typ 
FROM Person NATURAL JOIN Adresse 
WHERE typ = ''P''ORDER BY ort
', '2008-01-19 00:45:18.042678', '2008-03-24 11:35:49.731000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (45, 4, 47, 'Ermitteln Sie für jeden Angestellten alle Projekte, die sich sein Gehalt nicht leisten können.
Geben Sie für diese Projekte die Differenz zwischen Gehalt und Projektbudget sortiert nach persnr und projnr aus.', 'SELECT persnr, p.projnr, gehalt, budget, budget-gehalt AS differenz
FROM personal CROSS JOIN (SELECT projnr, budget FROM Projekt) p
WHERE budget - gehalt < 0
ORDER BY persnr, p.projnr', '2008-01-22 19:26:31.793773', '2012-01-26 17:36:46.976000', 'Sie müssen alle Kombinationen betrachten, daher sollten Sie einen CROSS JOIN einsetzen.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (46, 9, 48, 'Ermitteln Sie die Verkaufszahlen vor 2007 für jedes Produkt aus der Kategorie Computer für jede Kombination von Region und Jahr sowie die Gesamtverkaufszahl für diese Kategorie. Sortierung nach region, jahr, produkt.', 'SELECT region, jahr, produkt, SUM(anzahl) AS summe
FROM Verkaeufe JOIN Produkte USING (produkt)
WHERE kategorie = ''Computer'' and jahr < 2007
GROUP BY CUBE(region, jahr, produkt)
ORDER BY region, jahr, produkt', '2008-01-24 09:31:19.743048', '2014-06-23 23:04:31.277000', 'Benutzen Sie die GROUP BY mit CUBE Funktion!', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (47, 9, 49, 'Ermitteln Sie die Summe der Verkaufszahlen aggregiert entlang der Produktdimensionshierarchie sowie die Anzahl der Verkäufe. Sortierung gemäß der Dimensionshierarchie.', 'SELECT kategorie, wgruppe, produkt,
       SUM(anzahl) AS summe,
       COUNT(*) AS anzahl
FROM Verkaeufe JOIN Produkte USING(produkt)
GROUP BY ROLLUP(kategorie,wgruppe, produkt)
ORDER BY kategorie,wgruppe, produkt', '2008-01-24 09:35:18.776568', '2016-06-26 23:25:23.292000', 'Nutzen Sie ROLLUP', 0, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (48, 9, 50, 'Ermitteln Sie alle Verkaufszahlen für jede Warengruppe zusammen mit ihrer Kategorie sowie
zusammen mit der Region. Sortierung nach: kategorie, wgruppe, region', 'SELECT kategorie, wgruppe, region,
SUM(anzahl) AS summe, COUNT(*) AS anzahl
FROM Verkaeufe JOIN Produkte USING(produkt)
GROUP BY GROUPING SETS ((kategorie,wgruppe), (wgruppe, region))
ORDER BY kategorie, wgruppe, region', '2008-01-24 19:15:25.749930', '2016-06-26 23:25:23.292000', 'Benutzen Sie GROUP BY mit GROUPING SETS', 0, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (49, 9, 51, 'Berechnen Sie für jedes Jahr die Summe der Verkaufszahlen mit der Anzahl der Verkäufe  für die Hierarchie kategorie, wgruppe. Sortierung nach jahr, kategorie, wgruppe', 'SELECT jahr, kategorie, wgruppe,
SUM(anzahl) AS summe, COUNT(*) AS anzahl
FROM Verkaeufe NATURAL JOIN Produkte
GROUP BY ROLLUP(kategorie,wgruppe),jahr
ORDER BY jahr, kategorie, wgruppe', '2008-01-24 21:56:30.581797', '2016-06-26 23:25:23.292000', 'Benutzen Sie GROUP BY mit ROLLUP(...)', 0, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (50, 9, 52, 'Berechnen Sie alle Summen der Verkaufszahlen für die Hierarchien kategorie, wgruppe und jahr. 
Sortierung nach jahr, kategorie, wgruppe.', 'SELECT jahr, kategorie, wgruppe,
       SUM(anzahl) AS summe, COUNT(*) AS anzahl
FROM Verkaeufe NATURAL JOIN Produkte
GROUP BY ROLLUP(kategorie,wgruppe), ROLLUP(jahr)
ORDER BY jahr, kategorie, wgruppe', '2008-01-24 22:02:59.854710', '2016-06-26 23:25:23.292000', 'Benutzen Sie GROUP BY ROLLUP ( ... )', 0, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (51, 4, 53, 'Geben Sie für jeden Angestellten mit einem aktuellen Gehalt von mehr als 2000 aus, wie viele Einträge  er in allen, in Tabelle Akte vorkommenden Jahren ab 2002, jeweils hat. 
Sortierung nach persnr, jahr.
Hinweis: Das Jahr erhält man bei Oracle über: 
TO_CHAR(datum, ''YYYY'')', 'SELECT persnr, jahr, COUNT(datum) as anz_eintraege
FROM (SELECT persnr FROM Personal WHERE gehalt > 2000) CROSS JOIN
 ( SELECT DISTINCT TO_CHAR(datum, ''YYYY'') jahr FROM akte WHERE datum >= ''2002-01-01'')
 LEFT JOIN (SELECT persnr, TO_CHAR(datum, ''YYYY'') jahr, datum FROM Akte ) 
     USING (persnr, jahr)
GROUP BY persnr, jahr
ORDER BY persnr, jahr', '2008-01-25 15:15:45.668693', '2012-01-26 17:36:46.976000', 'Alle Personen müssen mit allen Jahren kombiniert werden => CROSS JOIN erforderlich.
Bei der Zuordnung zu den Akteneinträge gibt es nicht zu jeder Kombination einen Wert => LEFT JOIN erforderlich.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (52, 2, 166, 'Ermitteln Sie für jedes Jahr die Anzahl der Einträge in Tabelle Akte.
Sortierung soll absteigend nach dem Jahr erfolgen.
Anmerkung: Bei Oracle erhalten Sie das Jahr über  TO_CHAR(datum, ''YYYY'')', 'SELECT TO_CHAR(datum,''YYYY'') AS jahr, COUNT(*)
FROM Akte 
GROUP BY TO_CHAR(datum,''YYYY'')
ORDER BY jahr DESC', '2008-01-25 15:21:03.351441', '2013-07-21 01:58:50.683000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (53, 8, 75, 'Geben Sie alle Personen mit name, vorname aus, die das Hobby Schwimmen haben und kein Abitur!', 'select name, vorname
from person natural join hobbies
where hobby = ''Schwimmen''
and persnr not in (select persnr from ausbildungen where abschluss = ''Abitur'')
', '2008-03-16 12:33:31.917578', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (55, 10, 69, 'Erstellen Sie eine Liste aller Personen mit persnr, Vorname, Name und der Anzahl ihrer Hobbies (alias: anz_hob)!', 'select persnr, vorname, name, count(*) as anz_hob
from person natural join hobbies
group by persnr, vorname, name
', '2008-03-16 16:39:03.900586', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (56, 10, 83, 'Bestimmen Sie die Personen mit den meisten Ausbildungstagen insgesamt.
Ausgegeben werden soll: Persnr, Vorname, Name, Anzahl der Ausbildungstage (alias anz_tage) 
Sortierung soll nach Name und Vorname erfolgen.', 'select persnr, vorname, name, sum(bis-von) as anz_tage
from person join ausbildungen using (persnr)
group by persnr, vorname, name
having sum(bis-von) = (select max(sum(bis-von)) from ausbildungen group by persnr)
order by name, vorname
', '2008-03-16 16:48:22.769063', '2013-09-13 09:27:02.632000', 'Sie müssen die Differnz zwischen ''bis'' und ''von'' berchnen, um die Anzahl Tage zu erhalten.
Zur Bestimmung der meisten Tage ist eine Gruppierung mit einer HAVING-Bedingung mit Subquery notwendig.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (57, 8, 59, 'Bestimmen Sie alle Personen mit Name, Vorname, die Abitur haben und eine private Adresse im Postleitzahlbereich 6xxxx, sortiert nach Geschlecht!', 'select name, vorname
from person natural join adresse natural join ausbildungen
where abschluss = ''Abitur''
and upper(typ) = ''P''
and plz like ''6____''
order by geschlecht
', '2008-03-16 16:57:49.862282', '2008-03-24 11:35:49.731000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (58, 7, 45, 'Bestimmen Sie alle verschiedenen persnr mit einer Adresse im Postleitzahlbereich 1xxxx!', 'select distinct persnr
from adresse
where plz like ''1____''
', '2008-03-16 17:01:43.939272', '2008-03-21 12:25:27.010000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (59, 7, 62, 'Erzeugen Sie eine Liste aller verschiedenen bisher eingetragenen Abschlüsse, aufsteigend sortiert', 'select distinct abschluss from ausbildungen
order by abschluss asc
', '2008-03-16 17:06:44.135695', '2008-03-24 11:35:49.731000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (60, 7, 66, 'Erstellen Sie eine Liste mit persnr und gehalt, bezogen auf die aktuelle Berufsphase, aufsteigend nach Gehalt sortiert!', 'select persnr, gehalt
from beruf
where beendet is null
order by gehalt asc
', '2008-03-16 17:12:00.073806', '2008-03-24 11:35:49.731000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (61, 10, 70, 'Erstellen Sie eine Liste mit persnr und Anzahl der Arbeitstage (alias anz_tage) pro Person bzgl. aller abgeschlossenen Berufsphasen, absteigend sortiert nach der Anzahl der Arbeitstage!', 'select persnr, sum(beendet-begonnen) as anz_tage
from beruf
where beendet is not null
group by persnr
order by anz_tage desc
', '2008-03-16 17:23:57.136668', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (62, 7, 67, 'Bestimmen Sie die Personennummern (persnr) aller Personen, die zwischen dem 1.1.1990 und dem 31.12.1999 eine Ausbildung begonnen haben', 'select distinct persnr
from ausbildungen
where von between to_date(''01.01.1990'', ''dd.mm.yyyy'') and to_date(''31.12.1999'', ''dd.mm.yyyy'')
', '2008-03-24 11:41:26.742757', '2008-04-01 14:08:43.459000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (63, 7, 72, 'Bestimmen Sie die Personennummern (persnr) aller Personen, die zwischen dem 1.1.1990 und dem 31.12.1999 eine Ausbildung begonnen haben', 'select distinct persnr
from ausbildungen
where von between to_date(''01.01.1990'', ''dd.mm.yyyy'') and to_date(''31.12.1999'', ''dd.mm.yyyy'') 
', '2008-03-24 11:47:26.346963', '2008-04-01 14:08:43.459000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (64, 7, 61, 'Bestimmen Sie die Personennummern (persnr) aller Personen, die zwischen dem 1.1.1980 und dem 31.12.1989 eine Ausbildung begonnen haben
und mit dem Abschluss Abitur abgeschlossen haben!', 'select distinct persnr
from ausbildungen
where von between to_date(''01.01.1980'',''dd.mm.yyyy'') and to_date(''31.12.1989'',''dd.mm.yyyy'')
and abschluss = ''Abitur''
', '2008-03-24 11:49:38.315606', '2009-04-16 10:06:18.220000', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (65, 7, 65, 'Bestimmen Sie alle Personen (persnr, vorname, name), die weiblich sind, vor dem 1.1.1980 geboren wurden und deren Name nicht mit ''Z'' beginnt', 'select persnr, vorname, name 
from person
where upper(geschlecht)=''W''
and gebdat < to_date(''01.01.1980'',''dd.mm.yyyy'')
and name not like ''Z%''
', '2008-03-24 19:11:14.657514', '2008-03-24 11:35:49.731000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (66, 10, 71, 'Bestimmen Sie die Personennummern(persnr) der Personen,
die mehr als 2 Ausbildungen haben!', 'select persnr
from ausbildungen
group by persnr
having count(*) > 2
', '2008-03-24 19:16:14.284874', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (67, 10, 68, 'Erstellen Sie eine Liste aller Personennummern (persnr) mit der Anzahl ihrer Adressen (alias anz_adr)', 'select persnr, count(*) as anz_adr
from adresse
group by persnr
', '2008-03-24 19:18:49.517693', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (68, 10, 82, 'Bestimmen Sie die ''persnr'' der Personen mit den meisten Hobbies!
Geben Sie die persnr mit der Anzahl der Hobbies aus (alias anz_hobbies).
Sortierung nach persnr.', 'select persnr, count(*) as anz_hobbies
from hobbies
group by persnr
having count(*) = (select max(count(*)) from hobbies group by persnr)
order by persnr
', '2008-03-24 19:21:10.835609', '2013-09-13 09:27:02.632000', 'Nutzen Sie Gruppierung mit HAVING. In der HAVING-Bedingung benötigen Sie eine Subquery.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (69, 10, 81, 'Bestimmen Sie die verschiedenen Personennummern (persnr) der Personen, deren Gehalt größer ist als das mittlere Gehalt in den offenen Berufsphasen', 'select distinct persnr
from beruf
where gehalt > (select avg(gehalt) from beruf where beendet is null)
', '2008-03-24 19:30:36.038379', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (70, 7, 64, 'Bestimmen Sie persnr und Beschreibung aller Personen, deren Berufsbeschreibung das Wort ''Design'' enthält!', 'select persnr, beschreibung
from beruf
where beschreibung like ''%Design%''
', '2008-03-24 19:37:06.582162', '2008-03-24 11:35:49.731000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (71, 8, 74, 'Bestimmen Sie Vorname und Name der Person(en), die das Hobby Schwimmen haben, eine Adresse in Zweibrücken besitzen und in der Berufsbeschreibung den String ''bote'' besitzen!', 'select vorname, name
from person natural join hobbies natural join adresse natural join beruf
where ort =''Zweibrücken''
and hobby = ''Schwimmen''
and beruf.beschreibung like ''%bote%''
', '2008-03-24 19:58:04.687150', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (72, 8, 85, 'Erstellen Sie eine Liste aller Personen (persnr, name, vorname) mit ihren Gehältern in den verschiedenen Berufsphasen, absteigend nach der persnr sortiert. Dabei sollen auch die Personen aufgelistet werden, die keinen Berufsdatensatz haben!', 'select persnr, name, vorname, gehalt
from person left outer join beruf using(persnr)
order by persnr desc
', '2008-03-24 20:06:45.389159', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (73, 8, 46, 'Bestimmen Sie eine Liste aller Personen (persnr, vorname, name) mit ihren Hobbies!', 'select persnr, vorname, name, hobby
from person natural join hobbies
', '2008-03-24 20:10:44.712125', '2008-03-24 11:35:49.731000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (74, 8, 84, 'Bestimmen Sie die verschiedenen Personennummern (persnr) der Personen, die eine Berufsphase während einer Ausbildung begonnen haben!', 'select distinct persnr
from ausbildungen join beruf using (persnr)
where bis > begonnen
', '2008-03-24 21:00:27.819005', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (79, 10, 57, 'Wieviele Hobbies (egal ob verschieden oder nicht) gibt es in der Datenbank?', 'select count(*) from hobbies', '2008-03-26 12:37:55.613705', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (80, 10, 58, 'Bestimmen Sie die Summe aller Einnahmen (Alias summe) aller Personen!', 'select sum(einnahmen) as summe from person', '2008-03-26 12:40:22.156844', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (81, 10, 63, 'Bestimmen Sie die mittleren Einnahmen (Alias avg_ein) über alle Personen!', 'select avg(einnahmen) as avg_ein from person', '2008-03-26 12:45:25.118768', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (82, 8, 73, 'Bestimmen Sie alle weiblichen Personen (persnr, vorname, name) mit der Beschreibung (beschreibung) ihrer offenen Berufsphase und dem Ort der dienstlichen Adresse', 'select persnr, vorname, name, beschreibung, ort
from person natural join beruf natural join adresse
where upper(geschlecht) = ''W''
and beendet is null
and upper(typ) = ''D''
', '2008-03-26 13:23:21.405443', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (83, 8, 76, 'Bestimmen Sie zu jeder Person (persnr, name) die Beschreibungen der Ausbildungen und Berufsphasen!', 'select person.persnr, name, ausbildungen.beschreibung, beruf.beschreibung
from person, ausbildungen, beruf
where person.persnr = ausbildungen.persnr
and person.persnr = beruf.persnr
', '2008-03-26 13:30:59.971164', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (85, 11, 87, 'Bestimmen Sie über ein Subselect in der select-Klausel eine Liste aller Personen (persnr, vorname, name) mit den Anzahlen ihrer Adressen (alias: anz_adr)', 'select persnr, vorname, name, (select count(*) from adresse where persnr = person.persnr) as anz_adr
from person
', '2008-03-26 13:48:05.733635', '2008-03-26 12:00:30.616000', NULL, 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (88, 13, 103, 'Finden Sie alle Projekte für die mehr als 3 Personen arbeiten und die nicht zur Abteilung 3 gehören.', 'SELECT projnr FROM personal GROUP BY projnr HAVING COUNT(*) > 3
MINUS
SELECT projnr FROM projekt WHERE abtnr = 3', '2008-11-06 21:18:15.491553', '2014-07-02 10:27:07.959000', 'Nutzen Sie Gruppierung und MINUS !', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (89, 13, 104, 'Finden Sie alle Projekte die zur Abteilung 3 gehören und für die mindestens 2 Personen arbeiten.', 'SELECT projnr FROM Personal GROUP BY projnr HAVING COUNT(*) >= 2
INTERSECT
SELECT  projnr FROM Projekt WHERE abtnr = 3', '2008-11-06 21:21:32.353069', '2014-07-02 10:27:07.959000', 'Bestimmen Sie die Schnittmenge der Projektnummern', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (91, 13, 102, 'Finden Sie alle Abteilungen (abtnr), deren Budget mehr als 30000 beträgt 
oder zu denen ein Projekt mit einem Budet von mehr als 2000 gehört.', 'SELECT abtnr FROM Abteilung WHERE budget > 30000
UNION
SELECT abtnr FROM projekt WHERE budget > 2000
', '2008-11-11 00:01:22.985226', '2012-01-27 08:30:49.192000', 'Nutzen Sie  die Mengenvereinigungsoperation.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (92, 3, 167, 'Geben Sie für jede Abteilung aus, wie viel Gehalt sie für die zugeordneten Mitarbeiter zahlen muss. - Sortieren Sie nach der AbtNr.', 'SELECT abtnr, SUM(gehalt) as gehaltssumme
FROM Abteilung a 
     LEFT JOIN Projekt p USING(abtnr) 
     LEFT JOIN personal pe USING(projnr)
GROUP BY abtnr
ORDER BY abtnr
', '2010-09-02 12:40:16.127044', '2013-07-21 01:58:50.683000', 'Sie benötigen zwei OUTER JOINs', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (93, 1, 106, 'Geben Sie  für alle Angestellten, deren Nachnamen mit einem Buchstaben vor M beginnt, ihren Namen  sowie die Länge ihres Vornamens aus.
Geben Sie die Angestellten so aus, dass diejenigen mit einem längeren Vornamen vor denjenigen mit einem kürzeren ausgegeben werden.', 'SELECT nname, vname, LENGTH(vname) AS laenge 
FROM personal
WHERE nname < ''M''
ORDER BY laenge desc', '2010-09-02 13:02:04.553324', '2015-10-16 19:43:18.276000', 'Nutzen Sie die Funktion LENGTH.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (112, 3, 25, 'Finden Sie die Abteilungen, deren Budget mit dem Budget eines Projektes übereinstimmt.
Sortierung soll nach dem Abtnamen erfolgen.', 'SELECT DISTINCT abtname, budget
FROM abteilung JOIN projekt USING(budget)
ORDER BY abtname
', '2011-05-30 17:23:13.716958', '2015-10-16 19:43:18.276000', 'Ein Join über das budget ist erforderlich.', 0, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (113, 13, 126, 'Finden Sie alle Projekte, für die kein Mitarbeiter mit einem Gehalt über 500 arbeitet. 
Ausgabe nach projnr aufsteigend sortiert.', 'SELECT PROJNR FROM PROJEKT
MINUS 
SELECT PROJNR FROM PERSONAL WHERE GEHALT > 500
ORDER BY projnr
', '2011-06-30 12:16:52.882410', '2014-07-02 10:27:07.959000', 'MINUS nutzen', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (114, 6, 127, 'Finden Sie alle Spaltennamen, die länger als 6 Zeichen sind. Geben Sie diese zusammen mit dem Namen ihrer Tabelle sortiert aus.', 'SELECT table_name, column_name
FROM user_tab_columns
WHERE LENGTH(column_name) > 6
ORDER BY table_name, column_name
', '2011-06-30 12:23:14.774336', '2011-06-30 12:23:14.774336', 'Tabelle: user_tab_columns', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (115, 4, 128, 'Ermitteln Sie für jede Abteilung die Anzahl der zugeordneten Projekte und der Mitarbeiter.
Die Sortierung soll nach dem Abteilungsnamen erfolgen.', 'SELECT abtnr, abtname, COUNT(projnr) AS anz_projekte, coalesce(SUM(anz), 0) AS anz_mitarbeiter
FROM abteilung LEFT JOIN projekt USING(abtnr)
     LEFT JOIN (SELECT projnr, COUNT(*) anz FROM Personal GROUP BY projnr) USING (projnr)
GROUP BY abtnr, abtname
ORDER BY abtname
', '2011-07-09 14:48:21.940820', '2012-01-26 17:36:46.976000', 'Sie benötigen OUTER JOINS, eine SubQuery mit Gruppierung sowie eine Funktion zur Ersetzung von NULL-Werten.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (132, 9, 145, 'Bestimmen Sie für die Region Bayern den prozentualen Anteil der Verkäufe an der Gesamtzahl sowie an dem Anteil der Verkäufe im jeweiligen Jahr. 
Nutzen Sie ROUND(), um die Ausgabe auf 1 Stelle hinter dem Komma zu runden.
Sortierung nach Jahr und Produkt.', 'SELECT region, jahr, produkt, anzahl,
ROUND(anzahl*100 / SUM(anzahl) OVER(),1) AS gesamtanteil,
ROUND(anzahl*100 / SUM(anzahl) OVER(PARTITION BY jahr),1) AS jahresanteil
FROM verkaeufe
WHERE region = ''Bayern''
ORDER BY jahr,produkt
', '2012-06-24 18:09:13.278857', '2016-06-26 23:25:23.292000', 'Nutzen Sie OVER()', 0, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (133, 9, 146, 'Bestimmen Sie für das Jahr 2007 über alle Regionen und Produkte hinweg eine Rangliste nach verkaufter Anzahl. Die Plätze sollen dabei eindeutig nummeriert werden. Sortierung gleicher Plätze nach: region, jahr, produkt', 'SELECT region, jahr, produkt, anzahl, 
       row_number() OVER (ORDER BY anzahl DESC) AS jahresplatz
FROM verkaeufe where jahr = 2007
ORDER BY jahresplatz, region, jahr, produkt', '2012-06-24 18:46:33.031852', '2016-06-26 23:25:23.292000', 'Nutzen Sie row_nmber() und OVER() mit ORDER BY', 0, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (134, 9, 147, 'Lösen Sie Aufgabe 1.7 nun so, dass gleiche Werte auch zu einem gleichen Platz führen.', 'SELECT region, jahr, produkt, anzahl, 
       RANK() OVER (ORDER BY anzahl DESC) AS jahresplatz
FROM verkaeufe where jahr = 2007
order by jahresplatz, region, jahr, produkt', '2012-06-24 19:00:51.655588', '2016-06-26 23:25:23.292000', 'Nutzen Sie RANK()', 0, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (135, 1, 148, 'Geben Sie für alle Angestellten Persnr, Vor- und Nachnamen und die Gesamtlänge ihres Namens aus. Die Sortierung soll dabei gemäß der Gesamtlänge des Namens erfolgen.', 'SELECT persnr, vname, nname, LENGTH(vname||nname) AS laenge 
FROM personal 
ORDER BY laenge', '2012-11-24 17:53:16.907223', '2014-07-02 10:27:07.959000', 'Achtung: Wenn Sie die Längen einfach addieren, ergibt die Summe bei Angestellten ohne Wert für den Nachnamen NULL!
Nutzen Sie eine String-Konkatenation.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (152, 12, 165, 'Finden Sie die persnr aller Personen, deren Einnahmen über 80000 liegen, oder die jemals den Beruf "Sängerin" ausgeübt haben.
Sortierung soll nach der persnr erfolgen.', 'select persnr from person where einnahmen > 800000
union
select persnr from beruf where beschreibung = ''Sängerin''
order by persnr
', '2013-06-23 20:55:02.563086', '2013-09-13 09:27:02.632000', 'Nutzen Sie UNION', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (153, 2, 15, 'Wie viele verschiedene Nachnamen gibt es unter den Mitarbeitern in Tabelle Personal?', 'SELECT COUNT(DISTINCT nname) AS anz_namen FROM personal
', '2013-07-21 02:12:51.270084', '2014-07-02 10:27:07.959000', 'Nutzen Sie DISTINCT', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (154, 3, 22, 'Geben Sie für jeden Angestellten (persnr, nname, vname) den Namen der Abteilung aus, zu der das Projekt gehört, für das er arbeitet. Die Sortierung soll nach der persnr erfolgen.', 'SELECT persnr, nname, vname, abtname
FROM personal JOIN projekt USING(projnr) JOIN abteilung USING(abtnr)
ORDER BY persnr
', '2013-07-21 02:26:23.546027', '2014-07-02 10:27:07.959000', 'Erfordert 2 Joins, da beim Angestellten nur das Projekt hinterlegt ist. Beim Projekt steht dann die Abteilungsnummer.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (155, 11, 168, 'Finden Sie alle Personen (persnr, vorname, nachname), die nicht das Hobby ''Schwimmen'' haben.
Sortierung soll nach der persnr erfolgen.', 'select persnr, vorname, name from person
where persnr not in (select persnr from hobbies where hobby = ''Schwimmen'')
order by persnr
', '2013-09-13 09:44:25.364738', '2013-09-13 09:44:25.364738', '', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (156, 12, 169, 'Finden Sie die persnr aller Personen, die das Hobby Schwimmen, aber nicht das Hobby ''Essen'' haben.
Sortierung soll nach der persnr erfolgen.', 'select persnr from hobbies where hobby = ''Schwimmen''
minus
select persnr from hobbies where hobby = ''Essen''
order by persnr
', '2013-09-13 09:46:00.983381', '2013-09-13 09:46:00.983381', 'Nutzen Sie den MINUS-Operator.', 2, 0);
INSERT INTO TASK (ID, TASKGROUP_ID, RANK, DESCRIPTION, QUERY, DATECREATE, DATELASTMOD, HINT, HINT_TRIALS, SOLUTION_TRIALS) 
VALUES (172, 9, 185, 'Ermitteln Sie die Gesammtsumme der Verkäufe pro Jahr und dazu den gleitenden Durchschnitt aus dem aktuellen Jahr mit dem Vorjahr und dem nächsten Jahr. Runden Sie den Durchschnitt auf 1 Nachkommastelle.', 'SELECT jahr, summe, 
round(avg(summe) OVER (ORDER BY jahr
ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING),1) gl_schnitt
FROM (
  SELECT jahr, sum(anzahl) AS summe
  FROM verkaeufe
  GROUP BY jahr)
', '2014-06-24 00:20:56.779835', '2015-07-06 19:21:15.867000', 'Ermitteln Sie als Basistabelle zunächst die Jahressummen.
Nutzen Sie ROWS BETWEEN zur Spezifikation des Fenseters.
Zum Runden: ROUND', 0, 0);