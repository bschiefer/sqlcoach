drop table projekt;
drop table abteilung;
drop table akte;
drop table personal;

CREATE TABLE Personal 
(     PersNr    DECIMAL(5)   NOT NULL,
      VName     VARCHAR(25) NOT NULL,
      NName     VARCHAR(25) ,
      ProjNr    DECIMAL(4)   NOT NULL,          
      TelefonNr DECIMAL(10),
      Gehalt    DECIMAL(8)   CHECK (Gehalt >= 0),
      PRIMARY KEY (PersNr)
);

CREATE TABLE Akte
(     PersNr    DECIMAL(5) NOT NULL, 
      Datum     Date       NOT NULL,
      Position  VARCHAR(25),
      Gehalt    DECIMAL(8) CHECK (Gehalt >= 0),
      PRIMARY KEY (PersNr,Datum),
      FOREIGN KEY (PersNr) REFERENCES Personal(PersNr) ON DELETE CASCADE
);

CREATE TABLE Abteilung
(     AbtNr     DECIMAL(4)   NOT NULL,
      AbtName   VARCHAR(15) NOT NULL,
      Budget    DECIMAL(7),
      PersNr    DECIMAL(5),
      PRIMARY KEY (AbtNr),
      FOREIGN KEY (PersNr) REFERENCES Personal(PersNr) ON DELETE CASCADE
);

CREATE TABLE Projekt
(     ProjNr    DECIMAL(4) NOT NULL,
      Budget    DECIMAL(7),
      AbtNr     DECIMAL(4) NOT NULL,
      PRIMARY KEY (ProjNr),
      FOREIGN KEY (AbtNr) REFERENCES Abteilung(AbtNr) ON DELETE CASCADE
);      

-- first create table and then insert, after insert alter table ...
ALTER TABLE Personal ADD CONSTRAINT proj_ok
FOREIGN KEY (ProjNr) REFERENCES Projekt(ProjNr);
-- not possible in MySQL DEFERRABLE INITIALLY DEFERRED;

CREATE VIEW Pers_Abt AS
  SELECT pe.persnr, nname, vname, abtname
  FROM Personal pe JOIN Projekt  p ON pe.projnr = p.projnr
                   JOIN Abteilung a ON a.abtnr = p.abtnr;