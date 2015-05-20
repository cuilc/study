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