create table users(
	id int(11) NOT NULL auto_increment,
	first_name varchar(20),
	last_name varchar(20),
	
	created_by varchar(20),
	created_at datetime,
	updated_by varchar(20),
	updated_at datetime,
	PRIMARY KEY  (id)
);


create table orders(
	id int(11) NOT NULL auto_increment,
	name varchar(20),
	note varchar(200),
	wf_id varchar(20),
	wf_status varchar(20),
	
	created_by varchar(20),
	created_at datetime,
	updated_by varchar(20),
	updated_at datetime,
	PRIMARY KEY  (id)
);

