-- Table: solomatov.parserlog

CREATE TABLE solomatov.parserlog
(
  loggerdate timestamp without time zone,
  logger character varying,
  priority character varying,
  message character varying
);
GRANT ALL ON TABLE solomatov.parserlog TO public;
