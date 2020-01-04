-- ************************************** "Medico"

CREATE TABLE "Medico"
(
 "CodiceFiscale" 	varchar(16) NOT NULL,
 "IndirizzoClinica" varchar(50) NOT NULL,
 "TelefonoClinica"	varchar(50) NOT NULL,

 CONSTRAINT "FK_74" FOREIGN KEY ( "CodiceFiscale" ) REFERENCES "Persona" ( "CodiceFiscale" )
);

CREATE UNIQUE INDEX "PK_Medico" ON "Medico"
(
 "CodiceFiscale"
);

CREATE INDEX "fkIdx_74" ON "Medico"
(
 "CodiceFiscale"
);

-- ************************************** "Notifiche"

CREATE TABLE "Notifiche"
(
 "Persona" varchar(16) NOT NULL,
 "ID"      int NOT NULL,
 "Testo"   text NOT NULL,
 "Letto"   boolean NOT NULL,
 CONSTRAINT "FK_164" FOREIGN KEY ( "Persona" ) REFERENCES "Persona" ( "CodiceFiscale" )
);

CREATE UNIQUE INDEX "PK_Notifiche" ON "Notifiche"
(
 "Persona",
 "ID"
);

CREATE INDEX "fkIdx_164" ON "Notifiche"
(
 "Persona"
);

-- ************************************** "Persona"

CREATE TABLE "Persona"
(
 "CodiceFiscale" varchar(16) NOT NULL,
 "Nome"          varchar(50) NOT NULL,
 "Cognome"       varchar(50) NOT NULL,
 "DataNascita"   date NOT NULL,
 "Email"         varchar(50) NOT NULL,
 "Foto"          bytea NOT NULL,
 "LuogoNascita"  varchar(3) NOT NULL,
 "Provincia"     varchar(3) NOT NULL,
 "Sesso"         char(1) NOT NULL,
 "MedicoDiBase"  varchar(16) NULL,
 "Password"      varchar(50) NOT NULL,
 "Indirizzo"     varchar(50) NOT NULL,
 "Telefono"      varchar(50) NOT NULL,
 CONSTRAINT "FK_67" FOREIGN KEY ( "LuogoNascita" ) REFERENCES "Provincia" ( "Sigla" ),
 CONSTRAINT "FK_70" FOREIGN KEY ( "Provincia" ) REFERENCES "Provincia" ( "Sigla" ),
 CONSTRAINT "FK_78" FOREIGN KEY ( "MedicoDiBase" ) REFERENCES "Medico" ( "CodiceFiscale" )
);

CREATE UNIQUE INDEX "PK_Persona" ON "Persona"
(
 "CodiceFiscale"
);

CREATE INDEX "fkIdx_67" ON "Persona"
(
 "LuogoNascita"
);

CREATE INDEX "fkIdx_70" ON "Persona"
(
 "Provincia"
);

CREATE INDEX "fkIdx_78" ON "Persona"
(
 "MedicoDiBase"
);

-- ************************************** "Prestazioni"

CREATE TABLE "Prestazioni"
(
 "ID"           int NOT NULL,
 "isMedicinale" boolean NOT NULL,
 "Descrizione"  varchar(50) NOT NULL

);

CREATE UNIQUE INDEX "PK_Prestazioni" ON "Prestazioni"
(
 "ID"
);


-- ************************************** "Prescrizioni"

CREATE TABLE "Prescrizioni"
(
 "ID"                int NOT NULL,
 "Paziente"          varchar(16) NOT NULL,
 "Medico"            varchar(16) NOT NULL,
 "ProvinciaRilascio" varchar(3) NOT NULL,
 "Prestazione"       int NOT NULL,
 "DataRilascio"      date NOT NULL,
 "DataEvasione"      date NULL,
 CONSTRAINT "FK_101" FOREIGN KEY ( "Medico" ) REFERENCES "Medico" ( "CodiceFiscale" ),
 CONSTRAINT "FK_104" FOREIGN KEY ( "Paziente" ) REFERENCES "Persona" ( "CodiceFiscale" ),
 CONSTRAINT "FK_115" FOREIGN KEY ( "ProvinciaRilascio" ) REFERENCES "ServizioSanitarioProvinciale" ( "Provincia" ),
 CONSTRAINT "FK_123" FOREIGN KEY ( "Prestazione" ) REFERENCES "Prestazioni" ( "ID" )
);

CREATE UNIQUE INDEX "PK_Prescrizioni" ON "Prescrizioni"
(
 "ID"
);

CREATE INDEX "fkIdx_101" ON "Prescrizioni"
(
 "Medico"
);

CREATE INDEX "fkIdx_104" ON "Prescrizioni"
(
 "Paziente"
);

CREATE INDEX "fkIdx_115" ON "Prescrizioni"
(
 "ProvinciaRilascio"
);

CREATE INDEX "fkIdx_123" ON "Prescrizioni"
(
 "Prestazione"
);


-- ************************************** "Provincia"

CREATE TABLE "Provincia"
(
 "Sigla" varchar(3) NOT NULL,
 "Nome"  varchar(50) NOT NULL

);

CREATE UNIQUE INDEX "PK_Provincia" ON "Provincia"
(
 "Sigla"
);

-- ************************************** "ServizioSanitarioProvinciale"

CREATE TABLE "ServizioSanitarioProvinciale"
(
 "Provincia" varchar(3) NOT NULL,
 CONSTRAINT "FK_86" FOREIGN KEY ( "Provincia" ) REFERENCES "Provincia" ( "Sigla" )
);

CREATE UNIQUE INDEX "PK_ServizioSanitarioProvinciale" ON "ServizioSanitarioProvinciale"
(
 "Provincia"
);

CREATE INDEX "fkIdx_86" ON "ServizioSanitarioProvinciale"
(
 "Provincia"
);

-- ************************************** "SSP_PrestazioniDisponibili"

CREATE TABLE "SSP_PrestazioniDisponibili"
(
 "IDProvincia"   varchar(3) NOT NULL,
 "Costo"         double precision NOT NULL,
 "IDPrestazione" int NOT NULL,
 CONSTRAINT "FK_134" FOREIGN KEY ( "IDProvincia" ) REFERENCES "ServizioSanitarioProvinciale" ( "Provincia" ),
 CONSTRAINT "FK_138" FOREIGN KEY ( "IDPrestazione" ) REFERENCES "Prestazioni" ( "ID" )
);

CREATE UNIQUE INDEX "PK_SSP_PrestazioniDisponibili" ON "SSP_PrestazioniDisponibili"
(
 "IDProvincia",
 "IDPrestazione"
);

CREATE INDEX "fkIdx_134" ON "SSP_PrestazioniDisponibili"
(
 "IDProvincia"
);

CREATE INDEX "fkIdx_138" ON "SSP_PrestazioniDisponibili"
(
 "IDPrestazione"
);

-- ************************************** "Ticket"

CREATE TABLE "Ticket"
(
 "ID"            int NOT NULL,
 "Paziente"      varchar(16) NOT NULL,
 "IDProvincia"   varchar(3) NOT NULL,
 "IDPrestazione" int NOT NULL,
 "Emesso"        date NOT NULL,
 "Pagato"        date NULL DEFAULT NULL,
 CONSTRAINT "FK_172" FOREIGN KEY ( "Paziente" ) REFERENCES "Persona" ( "CodiceFiscale" ),
 CONSTRAINT "FK_175" FOREIGN KEY ( "IDProvincia", "IDPrestazione" ) REFERENCES "SSP_PrestazioniDisponibili" ( "IDProvincia", "IDPrestazione" )
);

CREATE UNIQUE INDEX "PK_Ticket" ON "Ticket"
(
 "ID"
);

CREATE INDEX "fkIdx_172" ON "Ticket"
(
 "Paziente"
);

CREATE INDEX "fkIdx_175" ON "Ticket"
(
 "IDProvincia",
 "IDPrestazione"
);


-- ************************************** "Admin"

CREATE TABLE "Admin"
(
 "CodiceFiscale" varchar(16) NOT NULL,
 CONSTRAINT "FK_208" FOREIGN KEY ( "CodiceFiscale" ) REFERENCES "Persona" ( "CodiceFiscale" )
);

CREATE UNIQUE INDEX "PK_Admin" ON "Admin"
(
 "CodiceFiscale"
);

CREATE INDEX "fkIdx_208" ON "Admin"
(
 "CodiceFiscale"
);

-- ************************************** "Visite"

CREATE TABLE "Visite"
(
 "ID"          int NOT NULL,
 "Paziente"    varchar(16) NOT NULL,
 "Medico"      varchar(16) NOT NULL,
 "Data"        date NOT NULL,
 "Relazione"   text NOT NULL,
 "Prestazione" int NULL,
 CONSTRAINT "FK_157" FOREIGN KEY ( "Paziente" ) REFERENCES "Persona" ( "CodiceFiscale" ),
 CONSTRAINT "FK_200" FOREIGN KEY ( "Medico" ) REFERENCES "Medico" ( "CodiceFiscale" ),
 CONSTRAINT "FK_204" FOREIGN KEY ( "Prestazione" ) REFERENCES "Prestazioni" ( "ID" )
);

CREATE UNIQUE INDEX "PK_Visite" ON "Visite"
(
 "ID"
);

CREATE INDEX "fkIdx_157" ON "Visite"
(
 "Paziente"
);

CREATE INDEX "fkIdx_200" ON "Visite"
(
 "Medico"
);

CREATE INDEX "fkIdx_204" ON "Visite"
(
 "Prestazione"
);
