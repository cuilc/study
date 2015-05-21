-- DROP DATABASE dev;

CREATE DATABASE dev
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'zh_CN.UTF-8'
       LC_CTYPE = 'zh_CN.UTF-8'
       CONNECTION LIMIT = -1;

CREATE SCHEMA tops_clc;

CREATE TABLE tops_clc.tx
(
  id character varying(32) NOT NULL,
  username character varying(20),
  password character varying(20),
  score integer,
  last_logon_time timestamp without time zone,
  CONSTRAINT tx_pkey PRIMARY KEY (id)
)