use test_base;
select * from person;
insert into person(name) values('jo');
select * from person;
ALTER TABLE `test_base`.`person`
    ADD COLUMN `heihgt` INT NOT NULL AFTER `name`;
select * from person;
update person set person.heihgt = '185' where id = '1';
select * from person;
update person set person.heihgt = '185' where id = '1';
select * from person;
