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
	SITE VARCHAR(255) NOT NULL,
	ADDRESSLINE_1 VARCHAR(255) NOT NULL,
	ADDRESSLINE_2 VARCHAR(255) NOT NULL,
	LAND_MARK VARCHAR(255) NOT NULL,
	CITY VARCHAR(255) NOT NULL,
	STATE VARCHAR(255) NOT NULL,
	COUNTRY VARCHAR(255) NOT NULL,
	ZIP_CODE VARCHAR(255) NOT NULL,
	CREATED_BY VARCHAR(255) NOT NULL,
    UPDATED_BY VARCHAR(255) NOT NULL,
    CREATED_DATE datetime NOT NULL,    
    UPDATED_DATE datetime NOT NULL,
	CONSTRAINT PK_SITE_ID PRIMARY KEY(SITE_ID),
    CONSTRAINT FK_DEPARTMENT_ID FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENT_TABLE(DEPARTMENT_ID) ON DELETE CASCADE
);

CREATE TABLE SITE_PERSON_TABLE(
 PERSON_ID INT AUTO_INCREMENT,
 SITE_ID INT,
 PERSON_INCHARGE VARCHAR(255),
 E_mail VARCHAR(255)not null unique,
 DESIGNATION VARCHAR(255),
 CONTACT_NO VARCHAR(255),
 CONSTRAINT PK_PERSON_ID PRIMARY KEY(PERSON_ID),
 CONSTRAINT FK_SITE_ID FOREIGN KEY (SITE_ID) REFERENCES SITE_TABLE(SITE_ID) ON DELETE CASCADE
 );

 

CREATE TABLE COUNTRY_TABLE (COUNTRY_ID INTEGER NOT NULL, NAME VARCHAR(255), CODE VARCHAR(255), CONSTRAINT PK_COUNTRY_ID PRIMARY KEY(COUNTRY_ID));
CREATE TABLE STATE_TABLE (STATE_ID INT NOT NULL, COUNTRY_ID INT, NAME VARCHAR(255), CODE VARCHAR(255),
CONSTRAINT PK_STATE_ID PRIMARY KEY(STATE_ID),
CONSTRAINT FK_COUNTRY_ID FOREIGN KEY (COUNTRY_ID) REFERENCES COUNTRY_TABLE(COUNTRY_ID) ON DELETE CASCADE);

insert into country_table values (1, 'INDIA', 'IND');
insert into country_table values (2, 'UNITED STATES OF AMERICA', 'USA');
insert into country_table values (3, 'BANGLADESH', 'BGL');
insert into country_table values (4, 'SRILANKA', 'SRI');
insert into country_table values (5, 'UNITED KINGDOM', 'UK');
insert into country_table values (6, 'PAKISTAN', 'PAK');
insert into country_table values (7, 'AFGANISTAN', 'AFG');
insert into country_table values (8, 'MALDIVES', 'MDV');
insert into country_table values (9, 'UNITED ARAB EMIRATES', 'UAE');
insert into country_table values (10, 'CHINA', 'CHN');
insert into country_table values (11, 'SINGAPORE', 'SGP');
insert into country_table values (12, 'THAILAND', 'THL');
insert into country_table values (13, 'AUSTRALIA', 'AUS');
insert into country_table values (14, 'NEW ZEALAND', 'NZL');
insert into country_table values (15, 'JAPAN', 'JPN');
insert into country_table values (16, 'INDONESIA', 'INA');
insert into country_table values (17, 'MALAYSIA', 'MLY');
insert into country_table values (18, 'GERMANY', 'GNY');
insert into country_table values (19, 'FRANCE', 'FRN');
insert into country_table values (20, 'RUSSIA', 'RUS');

INSERT INTO State_Table VALUES (1,1,'Tamil Nadu','TN');
INSERT INTO State_Table VALUES (2,1,'Andhra Pradesh','AP');
INSERT INTO State_Table VALUES (3,1,'Telengana','TS');
INSERT INTO State_Table VALUES (4,1,'Karnataka','KA');
INSERT INTO State_Table VALUES (5,1,'Kerala','KL');
INSERT INTO State_Table VALUES (6,1,'Odisha','OR');
INSERT INTO State_Table VALUES (7,1,'Maharastra','MH');
INSERT INTO State_Table VALUES (8,1,'Madhya Pradesh','MP');
INSERT INTO State_Table VALUES (9,1,'Chattisgarh','CG');
INSERT INTO State_Table VALUES (10,1,'Jharkand','JD');
INSERT INTO State_Table VALUES (11,1,'Bihar','BH');
INSERT INTO State_Table VALUES (12,1,'Uttar Pradesh','UP');
INSERT INTO State_Table VALUES (13,1,'Gujarat','GU');
INSERT INTO State_Table VALUES (14,1,'Rajasthan','RJ');
INSERT INTO State_Table VALUES (15,1,'Punjab','PB');
INSERT INTO State_Table VALUES (16,1,'Haryana','HR');
INSERT INTO State_Table VALUES (17,1,'Uttarkhand','UT');
INSERT INTO State_Table VALUES (18,1,'Sikkim','SK');
INSERT INTO State_Table VALUES (19,1,'West Bengal','WB');
INSERT INTO State_Table VALUES (20,1,'Assam','AS');
INSERT INTO State_Table VALUES (21,1,'Arunachala Pradesh','AR');
INSERT INTO State_Table VALUES (22,1,'Nagaland','NG');
INSERT INTO State_Table VALUES (23,1,'Tripura','TP');
INSERT INTO State_Table VALUES (24,1,'Meghalaya','MG');
INSERT INTO State_Table VALUES (25,1,'Mizoram','MZ');
INSERT INTO State_Table VALUES (26,1,'Manipur','GU');
INSERT INTO State_Table VALUES (27,1,'Goa','GA');
INSERT INTO State_Table VALUES (28,2,'Others','OT');