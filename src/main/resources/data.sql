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


CREATE TABLE COMPANY_TABLE (
    COMPANY_ID INT AUTO_INCREMENT ,
    COMPANY_CODE VARCHAR(255) NOT NULL,
    USER_NAME VARCHAR(255) NOT NULL ,
    CLIENT_NAME VARCHAR(255)NOT NULL UNIQUE,
	IN_ACTIVE boolean,
    CREATED_BY VARCHAR(255) NOT NULL,
    UPDATED_BY VARCHAR(255) NOT NULL,
    CREATED_DATE datetime NOT NULL,    
    UPDATED_DATE datetime NOT NULL,
    CONSTRAINT PK_COMPANY_ID PRIMARY KEY(COMPANY_ID)
     
);
CREATE TABLE DEPARTMENT_TABLE (
    DEPARTMENT_ID INT AUTO_INCREMENT,
    COMPANY_ID INT,
    DEPARTMENT_CODE VARCHAR(255)NOT NULL,
	USER_NAME VARCHAR(255)NOT NULL,
	CLIENT_NAME VARCHAR(255) NOT NULL,
	DEPARTMENT_NAME VARCHAR(255)NOT NULL UNIQUE,
	CREATED_BY VARCHAR(255)NOT NULL,
    UPDATED_BY VARCHAR(255)NOT NULL,
    CREATED_DATE datetime NOT NULL,    
    UPDATED_DATE datetime NOT NULL,
	CONSTRAINT PK_DEPARTMENT_ID PRIMARY KEY(DEPARTMENT_ID),
    CONSTRAINT FK_COMPANY_ID FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY_TABLE(COMPANY_ID) ON DELETE CASCADE
);

CREATE TABLE SITE_TABLE (
    SITE_ID INT AUTO_INCREMENT,
	DEPARTMENT_ID INT,
	SITE_CODE VARCHAR(255) NOT NULL,
    USER_NAME VARCHAR(255),
 	CLIENT_NAME VARCHAR(255),
	DEPARTMENT_NAME VARCHAR(255),
	SITE VARCHAR(255) NOT NULL UNIQUE,
	PERSON_INCHARGE VARCHAR(255) NOT NULL,
	E_MAIL VARCHAR(255) NOT NULL,
	ADDRESSLINE_1 VARCHAR(255) NOT NULL,
	ADDRESSLINE_2 VARCHAR(255) NOT NULL,
	LAND_MARK VARCHAR(255) NOT NULL,
	CITY VARCHAR(255) NOT NULL,
	STATE VARCHAR(255) NOT NULL,
	COUNTRY VARCHAR(255) NOT NULL,
	ZIP_CODE VARCHAR(255) NOT NULL,
	PHONE_NUMBER VARCHAR(255) NOT NULL,
	CREATED_BY VARCHAR(255) NOT NULL,
    UPDATED_BY VARCHAR(255) NOT NULL,
    CREATED_DATE datetime NOT NULL,    
    UPDATED_DATE datetime NOT NULL,
	CONSTRAINT PK_SITE_ID PRIMARY KEY(SITE_ID),
    CONSTRAINT FK_DEPARTMENT_ID FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENT_TABLE(DEPARTMENT_ID) ON DELETE CASCADE
);

