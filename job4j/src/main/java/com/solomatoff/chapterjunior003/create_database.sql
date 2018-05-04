-- Database: my_db

-- DROP DATABASE my_db;

CREATE DATABASE my_db
  WITH OWNER = postgres
       ENCODING = 'WIN1251'
       TABLESPACE = pg_default
       LC_COLLATE = 'Russian_Russia.1251'
       LC_CTYPE = 'Russian_Russia.1251'
       CONNECTION LIMIT = -1;