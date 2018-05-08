CREATE TABLE solomatov.company(id integer NOT NULL, name character varying, CONSTRAINT company_pkey PRIMARY KEY (id));
CREATE TABLE solomatov.person(id integer NOT NULL, name character varying, company_id integer,CONSTRAINT person_pkey PRIMARY KEY (id));
insert into solomatov.company (id, name) VALUES(1, 'ПАО "ГАЗПРОМ"');
insert into solomatov.company (id, name) VALUES(2, 'ПАО "РОСНЕФТЬ"');
insert into solomatov.company (id, name) VALUES(3, 'ПАО "ЛУКОЙЛ"');
insert into solomatov.company (id, name) VALUES(4, 'ПАО "РОСАВТОДОР"');
insert into solomatov.company (id, name) VALUES(5, 'ПАО "ГАЗПРОМБАНК"');
insert into solomatov.person (id, name, company_id) VALUES(1, 'ИВАНОВ ИВАН ИВАНОВИЧ-1', 1);
insert into solomatov.person (id, name, company_id) VALUES(2, 'ПЕТРОВ ПЕТР ПЕТРОВИЧ-2', 2);
insert into solomatov.person (id, name, company_id) VALUES(3, 'СИДОРОВ СИДОР СИДОРОВИЧ-2', 2);
insert into solomatov.person (id, name, company_id) VALUES(4, 'АЛЕКСЕЕВ АЛЕКСЕЙ АЛЕКСЕЕВИЧ-2', 2);
insert into solomatov.person (id, name, company_id) VALUES(5, 'ФЕДОРОВ ФЕДОР ФЕДОРОВИЧ-3', 3);
insert into solomatov.person (id, name, company_id) VALUES(6, 'ДЕНИСОВ ДЕНИС ДЕНИСОВИЧ-3', 3);
insert into solomatov.person (id, name, company_id) VALUES(7, 'КОНСТАНТИНОВ КОНСТАНТИН КОНСТАНТИНОВИЧ-3', 3);
insert into solomatov.person (id, name, company_id) VALUES(8, 'КУЗЬМИН КУЗЬМА КУЗЬМИЧ-4', 4);
insert into solomatov.person (id, name, company_id) VALUES(9, 'АНДРЕЕВ АНДРЕЙ АНДРЕЕВИЧ-5', 5);
insert into solomatov.person (id, name, company_id) VALUES(10, 'ВАСИЛЬЕВ ВАСИЛИЙ ВАСИЛЬЕВИЧ-5', 5);
-- первое задание
select p.name, c.name from solomatov.person as p, solomatov.company as c where company_id <> 5 and p.company_id = c.id order by c.id;
-- первый вариант второго задания
select c.name, a.numberperson from (select company_id, count(id) numberperson from solomatov.person group  by company_id) as a, solomatov.company c
	where a.numberperson = (select max(numberperson)
				                    from (select company_id, count(id) numberperson
				                          from solomatov.person
				                          group  by company_id) as b)
	      and c.id = a.company_id;
-- второй вариант второго задания
with count_person as
  (select company_id, count(id) numberperson
    from   solomatov.person
    group  by company_id)
select c.name, a.numberperson
from   count_person as a, solomatov.company as c
where  a.numberperson = (select max(numberperson) from count_person) and c.id = a.company_id;
