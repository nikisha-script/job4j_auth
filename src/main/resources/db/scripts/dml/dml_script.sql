--liquibase formatted sql

--changeset nikishin:insert_into_person
insert into person (login, password) values ('parsentev', '123');
insert into person (login, password) values ('ban', '123');
insert into person (login, password) values ('ivan', '123');