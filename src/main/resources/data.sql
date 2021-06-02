CREATE DATABASE lv_safety_verification;
use lv_safety_verification;

create table USERS 
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

create table APPLICATIONTYPES(id integer not null, application varchar(255), primary key(id))

insert into APPLICATIONTYPES values (1,'Verification Of LV Systems (IEC 60364-6)');
insert into APPLICATIONTYPES values (2,'Verification of HV system (up to 33 kV) (IEC 61936-1)');
insert into APPLICATIONTYPES values (3,'Lightning protection conformity assessment, risk assessment, inspection and maintenance (IEC 62305-3 & 4)');
insert into APPLICATIONTYPES values (4,'EMC assessment of an installation (IEC 61000-5-1)');
insert into APPLICATIONTYPES values (5,'Failure analysis of electronic systems');
insert into APPLICATIONTYPES values (6,'Conformity and project analysis');


CREATE TABLE COMPANY_TABLE (
    COMPANY_ID INT AUTO_INCREMENT ,
    COMPANY_CODE VARCHAR(255) NOT NULL,
    USER_NAME VARCHAR(255) NOT NULL ,
    CLIENT_NAME VARCHAR(255)NOT NULL UNIQUE,
	IN_ACTIVE BIT,
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
	 IN_ACTIVE BIT,
	 PERSON_INCHARGE VARCHAR(255),
	 E_mail VARCHAR(255)not null,
	 DESIGNATION VARCHAR(255),
	 CONTACT_NO VARCHAR(255),
	 CONSTRAINT PK_PERSON_ID PRIMARY KEY(PERSON_ID),
	 CONSTRAINT FK_SITE_ID FOREIGN KEY (SITE_ID) REFERENCES SITE_TABLE(SITE_ID) ON DELETE CASCADE
 );

CREATE TABLE REPORT_DETAILS_TABLE (
    REPORT_ID INT AUTO_INCREMENT,
	USER_NAME VARCHAR(255),
	DESCRIPTION_REPORT VARCHAR(500),
	REASON_REPORT VARCHAR(500),
	INSTALLATION_TYPE VARCHAR(255),
	DESCRIPTION_PREMISE VARCHAR(255) ,
	ESTIMATED_WIRE_AGE VARCHAR(255),
	EVIDANCE_ADDITION VARCHAR(255),
    PREVIOUS_RECORDS VARCHAR(255),
    LAST_INSPECTION VARCHAR(255),
    NEXT_INSPECTION VARCHAR(255),	
    EXTENT_INSTALLATION VARCHAR(500),
    CLIENT_DETAILS VARCHAR(500),
    INSTALLATION_DETAILS VARCHAR(500),
    VERIFICATION_DATE VARCHAR(500),  
    VERIFIED_ENGINEER VARCHAR(255),
    DESIGNATION VARCHAR(255) ,
    COMPANY VARCHAR(255),
	CREATED_BY VARCHAR(255),
    CREATED_DATE datetime,
    CONSTRAINT PK_REPORT_ID PRIMARY KEY(REPORT_ID)
 );
 
 
 
 CREATE TABLE SIGN_DETAILS_TABLE (
            SIGNATOR_ID INT AUTO_INCREMENT,
            REPORT_ID INT,
			SIGNATOR_ROLE VARCHAR(255),
			PERSON_NAME VARCHAR(255),
			PERSON_CONTACT_NO VARCHAR(255),
			PERSON_MAIL_ID VARCHAR(255),
			MANAGER_NAME VARCHAR(255),
			MANAGER_CONTACT_NO VARCHAR(255),
			MANAGER_MAIL_ID VARCHAR(255),
			COMPANY_NAME VARCHAR(255),
			ADDRESS_LINE1 VARCHAR(255),
			ADDRESS_LINE2 VARCHAR(255),
			LAND_MARK VARCHAR(255),
			STATE VARCHAR(255),
			PIN_CODE VARCHAR(255),
			COUNTRY VARCHAR(255),
			DECLARATION_DATE VARCHAR(255),
			DECLARATION_NAME VARCHAR(255),
			DECLARATION_SIGNATURE BLOB,
			CONSTRAINT PK_SIGNATOR_ID PRIMARY KEY(SIGNATOR_ID),
			CONSTRAINT FK_REPORT_ID FOREIGN KEY (REPORT_ID) REFERENCES REPORT_DETAILS_TABLE(REPORT_ID) ON DELETE CASCADE
);
 
CREATE TABLE COUNTRY_TABLE (COUNTRY_ID INTEGER NOT NULL, NAME VARCHAR(255), CODE VARCHAR(255), CONSTRAINT PK_COUNTRY_ID PRIMARY KEY(COUNTRY_ID));
CREATE TABLE STATE_TABLE (STATE_ID INT NOT NULL, COUNTRY_ID INT, NAME VARCHAR(255), CODE VARCHAR(255),
CONSTRAINT PK_STATE_ID PRIMARY KEY(STATE_ID),
CONSTRAINT FK_COUNTRY_ID FOREIGN KEY (COUNTRY_ID) REFERENCES COUNTRY_TABLE(COUNTRY_ID) ON DELETE CASCADE);

insert into COUNTRY_TABLE values (1, 'INDIA', 'IND');
insert into COUNTRY_TABLE values (2, 'UNITED STATES OF AMERICA', 'USA');
insert into COUNTRY_TABLE values (3, 'BANGLADESH', 'BGL');
insert into COUNTRY_TABLE values (4, 'SRILANKA', 'SRI');
insert into COUNTRY_TABLE values (5, 'UNITED KINGDOM', 'UK');
insert into COUNTRY_TABLE values (6, 'PAKISTAN', 'PAK');
insert into COUNTRY_TABLE values (7, 'AFGANISTAN', 'AFG');
insert into COUNTRY_TABLE values (8, 'MALDIVES', 'MDV');
insert into COUNTRY_TABLE values (9, 'UNITED ARAB EMIRATES', 'UAE');
insert into COUNTRY_TABLE values (10, 'CHINA', 'CHN');
insert into COUNTRY_TABLE values (11, 'SINGAPORE', 'SGP');
insert into COUNTRY_TABLE values (12, 'THAILAND', 'THL');
insert into COUNTRY_TABLE values (13, 'AUSTRALIA', 'AUS');
insert into COUNTRY_TABLE values (14, 'NEW ZEALAND', 'NZL');
insert into COUNTRY_TABLE values (15, 'JAPAN', 'JPN');
insert into COUNTRY_TABLE values (16, 'INDONESIA', 'INA');
insert into COUNTRY_TABLE values (17, 'MALAYSIA', 'MLY');
insert into COUNTRY_TABLE values (18, 'GERMANY', 'GNY');
insert into COUNTRY_TABLE values (19, 'FRANCE', 'FRN');
insert into COUNTRY_TABLE values (20, 'RUSSIA', 'RUS');

INSERT INTO STATE_TABLE VALUES (1,1,'Tamil Nadu','TN');
INSERT INTO STATE_TABLE VALUES (2,1,'Andhra Pradesh','AP');
INSERT INTO STATE_TABLE VALUES (3,1,'Telengana','TS');
INSERT INTO STATE_TABLE VALUES (4,1,'Karnataka','KA');
INSERT INTO STATE_TABLE VALUES (5,1,'Kerala','KL');
INSERT INTO STATE_TABLE VALUES (6,1,'Odisha','OR');
INSERT INTO STATE_TABLE VALUES (7,1,'Maharastra','MH');
INSERT INTO STATE_TABLE VALUES (8,1,'Madhya Pradesh','MP');
INSERT INTO STATE_TABLE VALUES (9,1,'Chattisgarh','CG');
INSERT INTO STATE_TABLE VALUES (10,1,'Jharkand','JD');
INSERT INTO STATE_TABLE VALUES (11,1,'Bihar','BH');
INSERT INTO STATE_TABLE VALUES (12,1,'Uttar Pradesh','UP');
INSERT INTO STATE_TABLE VALUES (13,1,'Gujarat','GU');
INSERT INTO STATE_TABLE VALUES (14,1,'Rajasthan','RJ');
INSERT INTO STATE_TABLE VALUES (15,1,'Punjab','PB');
INSERT INTO STATE_TABLE VALUES (16,1,'Haryana','HR');
INSERT INTO STATE_TABLE VALUES (17,1,'Uttarkhand','UT');
INSERT INTO STATE_TABLE VALUES (18,1,'Sikkim','SK');
INSERT INTO STATE_TABLE VALUES (19,1,'West Bengal','WB');
INSERT INTO STATE_TABLE VALUES (20,1,'Assam','AS');
INSERT INTO STATE_TABLE VALUES (21,1,'Arunachala Pradesh','AR');
INSERT INTO STATE_TABLE VALUES (22,1,'Nagaland','NG');
INSERT INTO STATE_TABLE VALUES (23,1,'Tripura','TP');
INSERT INTO STATE_TABLE VALUES (24,1,'Meghalaya','MG');
INSERT INTO STATE_TABLE VALUES (25,1,'Mizoram','MZ');
INSERT INTO STATE_TABLE VALUES (26,1,'Manipur','GU');
INSERT INTO STATE_TABLE VALUES (27,1,'Goa','GA');
INSERT INTO STATE_TABLE VALUES (28,2,'Others','OT');



--> Summary_Section

 
 CREATE TABLE SUMMERY_TABLE (
            SUMMERY_ID INT AUTO_INCREMENT,
			SITE_ID INT,
			USER_NAME VARCHAR(255),
            LIMITATIONS_INSPECTION  VARCHAR(255),
            EXTENT_INSTALLATION VARCHAR(255),
            AGREED_LIMITATIONS  VARCHAR(255),
            AGREED_WITH VARCHAR(255),
            OPERATIONAL_LIMITATIONS VARCHAR(255),
            INSPECTION_TESTING_DETAILED VARCHAR(255),
            RECOMMENDATIONS_DATE VARCHAR(255),
            GENERAL_CONDITION_INSTALLATION VARCHAR(500),
            OVERALL_ASSESSMENT_INSTALLATION VARCHAR(255),
            CREATED_DATE DATETIME,
            CONSTRAINT PK_SUMMERY_ID PRIMARY KEY(SUMMERY_ID)
              
);

CREATE TABLE OBSERVATIONS_TABLE (
            OBSERVATIONS_ID INT AUTO_INCREMENT,
			SUMMERY_ID INT,
            OBSERVATIONS  VARCHAR(255),
            REFERANCE_NUMBER_REPORT VARCHAR(255),
            FURTHER_ACTIONS  VARCHAR(255),
            COMENT VARCHAR(255),
            CONSTRAINT PK_OBSERVATIONS_ID PRIMARY KEY(OBSERVATIONS_ID),
			CONSTRAINT FK_OBSERVATIONS_SUMMERY_ID FOREIGN KEY (SUMMERY_ID) REFERENCES SUMMERY_TABLE(SUMMERY_ID) ON DELETE CASCADE              
);

   CREATE TABLE DECLARATION_TABLE (
            DECLARATION_ID INT AUTO_INCREMENT,
			SUMMERY_ID INT,
            DECLARATION_ROLE  VARCHAR(255),
            NAME VARCHAR(255),
            SIGNATURE  VARCHAR(255),
            COMPANY VARCHAR(255),
            POSITION VARCHAR(255),
            ADDRESS VARCHAR(500),
            DATE VARCHAR(255),			
            CONSTRAINT PK_DECLARATION_ID PRIMARY KEY(DECLARATION_ID),
			CONSTRAINT FK_DECLARATION_SUMMERY_ID FOREIGN KEY (SUMMERY_ID) REFERENCES SUMMERY_TABLE(SUMMERY_ID) ON DELETE CASCADE              
);