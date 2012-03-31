--// create table users
-- Migration SQL that makes the change goes here.
create table users(
    id numeric(11,0) NOT NULL,
    first_name varchar(20),
    last_name varchar(20),
    
    created_by varchar(20),
    created_at date,
    updated_by varchar(20),
    updated_at date,
    PRIMARY KEY  (id)
);



--//@UNDO
-- SQL to undo the change goes here.
drop table users;

