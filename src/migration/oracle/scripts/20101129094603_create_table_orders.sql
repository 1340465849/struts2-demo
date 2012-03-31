--// create table orders
-- Migration SQL that makes the change goes here.
create table orders(
    id numeric(11,0) NOT NULL,
    name varchar(20),
    note varchar(200),
    wf_id varchar(20),
    wf_status varchar(20),
    
    created_by varchar(20),
    created_at date,
    updated_by varchar(20),
    updated_at date,
    PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table orders;

