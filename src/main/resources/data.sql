insert into applicationtypes values (1,'Verification Of LV Systems (IEC 60364-6)');
insert into applicationtypes values (2,'Verification of HV system (up to 33 kV) (IEC 61936-1)');
insert into applicationtypes values (3,'Lightning protection conformity assessment, risk assessment, inspection and maintenance (IEC 62305-3 & 4)');
insert into applicationtypes values (4,'EMC assessment of an installation (IEC 61000-5-1)');
insert into applicationtypes values (5,'Failure analysis of electronic systems');
insert into applicationtypes values (6,'Conformity and project analysis');

create table users 
(user_id integer not null, 
user_active boolean,
creation_date datetime,
email varchar(255),
first_name varchar(255),
last_name varchar(255),
password varchar(255),
user_role varchar(255),
user_exist boolean,
user_name varchar(255),
user_type varchar(255),
updated_date datetime,
primary key (user_id));

create table applicationtypes(id integer not null, application varchar(255), primary key(id))