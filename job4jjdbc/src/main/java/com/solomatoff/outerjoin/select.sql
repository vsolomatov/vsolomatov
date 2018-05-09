select c.name, t.*, cb.*, e.* from car as c, transmission as t, carbody as cb, engine as e
where c.transmission_id = t.id and c.carbody_id = cb.id and c.engine_id = e.id;

select t.id as id, t.name as name from transmission as t left outer join car as c on t.id = c.transmission_id where c.transmission_id is NULL
union
select e.id as id, e.name as name from engine as e left outer join car as c on e.id = c.engine_id where c.engine_id is NULL
union
select cb.id as id, cb.name as name from carbody as cb left outer join car as c on cb.id = c.carbody_id where c.carbody_id is NULL
order by name, id;