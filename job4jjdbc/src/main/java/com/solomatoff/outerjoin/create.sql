create table transmission(
 id serial PRIMARY KEY,
 name character varying
);
create table carbody(
 id serial PRIMARY KEY,
 name character varying
);
create table engine(
 id serial PRIMARY KEY,
 name character varying
);
create table car(
 id serial PRIMARY KEY,
 name character varying,
 transmission_id integer references transmission,
 carbody_id integer references carbody,
 engine_id integer references engine
);
