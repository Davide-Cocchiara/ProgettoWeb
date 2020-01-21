-- ************************************** "provincia"

create table "provincia"
(
 "sigla" varchar(3) not null,
 "nome"  varchar(50) not null

);

create unique index "pk_provincia" on "provincia"
(
 "sigla"
);

-- ************************************** "persona"

create table "persona"
(
 "codicefiscale" varchar(16) not null,
 "nome"          varchar(50) not null,
 "cognome"       varchar(50) not null,
 "datanascita"   date not null,
 "email"         varchar(50) not null,
 "foto"          bytea,
 "luogonascita"  varchar(3) not null,
 "provincia"     varchar(3) not null,
 "sesso"         char(1) not null,
 constraint "fk_67" foreign key ( "luogonascita" ) references "provincia" ( "sigla" ),
 constraint "fk_70" foreign key ( "provincia" ) references "provincia" ( "sigla" )
);

create unique index "pk_persona" on "persona"
(
 "codicefiscale"
);

create index "fkidx_67" on "persona"
(
 "luogonascita"
);

create index "fkidx_70" on "persona"
(
 "provincia"
);










-- ************************************** "medico"

create table "medico"
(
 "codicefiscale" 	varchar(16) not null,
 "indirizzoclinica" varchar(50) not null,
 "telefonoclinica"	varchar(50) not null,

 constraint "fk_74" foreign key ( "codicefiscale" ) references "persona" ( "codicefiscale" )
);

create unique index "pk_medico" on "medico"
(
 "codicefiscale"
);

create index "fkidx_74" on "medico"
(
 "codicefiscale"
);

-- ************************************** "medicoassegnato"

create table "medicoassegnato"
(
 "paziente" varchar(16) not null,
 "medico"   varchar(16) not null,
 constraint "fk_244" foreign key ( "paziente" ) references "persona" ( "codicefiscale" ),
 constraint "fk_248" foreign key ( "medico" ) references "medico" ( "codicefiscale" )
);

create unique index "pk_medicoassegnato" on "medicoassegnato"
(
 "paziente"
);

create index "fkidx_244" on "medicoassegnato"
(
 "paziente"
);

create index "fkidx_248" on "medicoassegnato"
(
 "medico"
);

-- ************************************** "notifiche"

create table "notifiche"
(
 "persona" varchar(16) not null,
 "id"      serial,
 "testo"   text not null,
 "letto"   boolean not null,
 constraint "fk_164" foreign key ( "persona" ) references "persona" ( "codicefiscale" )
);

create unique index "pk_notifiche" on "notifiche"
(
 "persona",
 "id"
);

create index "fkidx_164" on "notifiche"
(
 "persona"
);


-- ************************************** "prestazioni"

create table "prestazioni"
(
 "id"      serial,
 "ismedicinale" boolean not null,
 "descrizione"  varchar(50) not null

);

create unique index "pk_prestazioni" on "prestazioni"
(
 "id"
);


-- ************************************** "serviziosanitarioprovinciale"

create table "serviziosanitarioprovinciale"
(
 "provincia" varchar(3) not null,
 constraint "fk_86" foreign key ( "provincia" ) references "provincia" ( "sigla" )
);

create unique index "pk_serviziosanitarioprovinciale" on "serviziosanitarioprovinciale"
(
 "provincia"
);

create index "fkidx_86" on "serviziosanitarioprovinciale"
(
 "provincia"
);


-- ************************************** "prescrizioni"

create table "prescrizioni"
(
 "id"      serial,
 "paziente"          varchar(16) not null,
 "medico"            varchar(16) not null,
 "provinciarilascio" varchar(3) not null,
 "prestazione"       int not null,
 "datarilascio"      date not null,
 "dataevasione"      date null,
 constraint "fk_101" foreign key ( "medico" ) references "medico" ( "codicefiscale" ),
 constraint "fk_104" foreign key ( "paziente" ) references "persona" ( "codicefiscale" ),
 constraint "fk_115" foreign key ( "provinciarilascio" ) references "serviziosanitarioprovinciale" ( "provincia" ),
 constraint "fk_123" foreign key ( "prestazione" ) references "prestazioni" ( "id" )
);

create unique index "pk_prescrizioni" on "prescrizioni"
(
 "id"
);

create index "fkidx_101" on "prescrizioni"
(
 "medico"
);

create index "fkidx_104" on "prescrizioni"
(
 "paziente"
);

create index "fkidx_115" on "prescrizioni"
(
 "provinciarilascio"
);

create index "fkidx_123" on "prescrizioni"
(
 "prestazione"
);


-- ************************************** "ssp_prestazionidisponibili"

create table "ssp_prestazionidisponibili"
(
 "idprovincia"   varchar(3) not null,
 "costo"         double precision not null,
 "idprestazione" int not null,
 constraint "fk_134" foreign key ( "idprovincia" ) references "serviziosanitarioprovinciale" ( "provincia" ),
 constraint "fk_138" foreign key ( "idprestazione" ) references "prestazioni" ( "id" )
);

create unique index "pk_ssp_prestazionidisponibili" on "ssp_prestazionidisponibili"
(
 "idprovincia",
 "idprestazione"
);

create index "fkidx_134" on "ssp_prestazionidisponibili"
(
 "idprovincia"
);

create index "fkidx_138" on "ssp_prestazionidisponibili"
(
 "idprestazione"
);

-- ************************************** "ticket"

create table "ticket"
(
 "id"      serial,
 "paziente"      varchar(16) not null,
 "idprovincia"   varchar(3) not null,
 "idprestazione" int not null,
 "emesso"        date not null,
 "pagato"        date null default null,
 constraint "fk_172" foreign key ( "paziente" ) references "persona" ( "codicefiscale" ),
 constraint "fk_175" foreign key ( "idprovincia", "idprestazione" ) references "ssp_prestazionidisponibili" ( "idprovincia", "idprestazione" )
);

create unique index "pk_ticket" on "ticket"
(
 "id"
);

create index "fkidx_172" on "ticket"
(
 "paziente"
);

create index "fkidx_175" on "ticket"
(
 "idprovincia",
 "idprestazione"
);


-- ************************************** "admin"

create table "admin"
(
 "codicefiscale" varchar(16) not null,
 constraint "fk_208" foreign key ( "codicefiscale" ) references "persona" ( "codicefiscale" )
);

create unique index "pk_admin" on "admin"
(
 "codicefiscale"
);

create index "fkidx_208" on "admin"
(
 "codicefiscale"
);

-- ************************************** "visite"

create table "visite"
(
 "id"      serial,
 "paziente"    varchar(16) not null,
 "medico"      varchar(16) not null,
 "data"        date not null,
 "relazione"   text not null,
 "prestazione" int null,
 constraint "fk_157" foreign key ( "paziente" ) references "persona" ( "codicefiscale" ),
 constraint "fk_200" foreign key ( "medico" ) references "medico" ( "codicefiscale" ),
 constraint "fk_204" foreign key ( "prestazione" ) references "prestazioni" ( "id" )
);

create unique index "pk_visite" on "visite"
(
 "id"
);

create index "fkidx_157" on "visite"
(
 "paziente"
);

create index "fkidx_200" on "visite"
(
 "medico"
);

create index "fkidx_204" on "visite"
(
 "prestazione"
);


create table "account"
(
 "codicefiscale" varchar(16) not null,
 "hashpass"      text not null,
 constraint "fk_236" foreign key ( "codicefiscale" ) references "persona" ( "codicefiscale" )
);

create unique index "pk_account" on "account"
(
 "codicefiscale"
);

create index "fkidx_236" on "account"
(
 "codicefiscale"
);


-- ************************************** "authtoken"

create table "authtoken"
(
 "id"            serial not null,
 "codicefiscale" varchar(16) not null,
 "token"         text not null,
 "expires"       date not null,
 constraint "fk_224" foreign key ( "codicefiscale" ) references "persona" ( "codicefiscale" )
);

create unique index "pk_authtoken" on "authtoken"
(
 "id"
);

create index "fkidx_224" on "authtoken"
(
 "codicefiscale"
);