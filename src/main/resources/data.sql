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

create table APPLICATIONTYPES(id integer not null, application varchar(255), code varchar(5), primary key(id))

insert into APPLICATIONTYPES values (1,'Verification Of LV Systems (IEC 60364-6)', 'LVS');
insert into APPLICATIONTYPES values (2,'Verification Of HV System (up to 33 kV) (IEC 61936-1)', 'HVS');
insert into APPLICATIONTYPES values (3,'Lightning Protection Conformity Assessment, Risk Assessment, Inspection And Maintenance (IEC 62305-3 & 4)', 'RISK');
insert into APPLICATIONTYPES values (4,'EMC Assessment Of An Installation (IEC 61000-5-1)', 'EMC');
insert into APPLICATIONTYPES values (5,'Failure Analysis Of Electronic Systems', 'FAES');
insert into APPLICATIONTYPES values (6,'Conformity And Project Analysis', 'CPA');


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

---> Supply characteristics and earthing arrangement API.
     ----------------------------------------------------

CREATE TABLE SUPPLY_CHARACTERISTICS_TABLE (

            SUPPLY_CHARACTERISTICS_ID INT AUTO_INCREMENT,
			SITE_ID INT,
			USER_NAME VARCHAR(255),
            MAIN_SYSTEM_EARTING VARCHAR(255),
            SYSTEM_EARTH_BNOTE VARCHAR(255),
            LIVE_CONDUCTOR_TYPE VARCHAR(255),
            LIVE_CONDUCTOR_AC VARCHAR(255),
            LIVE_CONDUCTOR_DC VARCHAR(255),
            LIVE_CONDUCTOR_BNOTE VARCHAR(255),
            MAIN_NOMINAL_VOLTAGE VARCHAR(255),
            MAIN_NOMINAL_FREQUENCY VARCHAR(255),
            MAIN_FAULT_CURRENT VARCHAR(255),
            MAIN_LOOP_IMPEDANCE VARCHAR(255),
            MAIN_CURRENT_PROTECTIVE_DEVICE VARCHAR(255),
            MAIN_RATED_CURRENT VARCHAR(255),
            MAIN_CURRENT_DISCONNECTION VARCHAR(255),
            AL_AVILABLE_SUPPLY BIT,
            AL_NUM_SUPPLY VARCHAR(255),
            MAXIMUM_DEMAND VARCHAR(255),
            MAXIMUM_LOAD VARCHAR(255),
            MEANS_EARTHING VARCHAR(255),
            ELECTORDE_TYPE VARCHAR(255),
            ELECTORDE_MATERIAL VARCHAR(255),
            NUM_LOCATION INT,
            CONDUCTOR_SIZE VARCHAR(255),
            CONDUCTOR_MATERIAL VARCHAR(255),
            CONDUCTOR_VERIFY BIT,
            BONDING_CONDUCTOR_SIZE VARCHAR(255),
            BONDING_CONDUCTOR_MATERIAL VARCHAR(255),
            BONDING_CONDUCTOR_VERIFY BIT,
            BONDING_JOINTS_TYPE VARCHAR(255),
            BONDING_NO_OF_JOINTS INT,
            EARTHING_CONDUCTOR_SIZE VARCHAR(255),
            EARTHING_CONDUCTOR_MATERIAL VARCHAR(255),
            EARTHING_CONDUCTOR_VERIFY BIT,
            EARTHING_JOINTS_TYPE VARCHAR(255),
            EARTHING_NO_OF_JOINTS INT,
            CREATED_DATE datetime,
            CONSTRAINT PK_SUPPLY_CHARACTERISTICS_ID PRIMARY KEY(SUPPLY_CHARACTERISTICS_ID)
              
);
 
CREATE TABLE SUPPLY_PARAMETERS_TABLE (

            SUPPLY_PARAMETERS_ID INT AUTO_INCREMENT,
            SUPPLY_CHARACTERISTICS_ID INT,
            AL_SYSTEM_EARTING VARCHAR(255),
            AL_SUPPLY_NO VARCHAR(255),
            AL_SUPPLY_STNAME VARCHAR(255),
            AL_SYSTEM_EARTING_BNOTE VARCHAR(255),
            AL_LIVECONDUCTOR_TYPE VARCHAR(255),
            AL_LIVECONDUCTOR_AC VARCHAR(255),
            AL_LIVECONDUCTOR_DC VARCHAR(255),
            AL_LIVECONDUCTOR_BNOTE VARCHAR(255),
             AL_NOMINAL_VOLTAGE VARCHAR(255),
            AL_NOMINAL_FREQUENCY VARCHAR(255),
            AL_FAULT_CURRENT VARCHAR(255),
            AL_LOOP_IMPEDANCE VARCHAR(255),
            AL_INSTALLED_CAPACITY VARCHAR(255),
            AL_ACTUAL_LOAD VARCHAR(255) ,
            AL_RATED_CURRNT VARCHAR(255),
            AL_CURRENT_DISCONNECTION VARCHAR(255),
            CONSTRAINT PK_SUPPLY_PARAMETERS_ID PRIMARY KEY(SUPPLY_PARAMETERS_ID),
            CONSTRAINT FK_SUPPLY_CHARACTERISTICS_ID FOREIGN KEY (SUPPLY_CHARACTERISTICS_ID) REFERENCES SUPPLY_CHARACTERISTICS_TABLE(SUPPLY_CHARACTERISTICS_ID) ON DELETE CASCADE

 );
 
  CREATE TABLE CIRCUIT_BREAKER_TABLE (
 
            CIRCUIT_BREAKER_ID INT AUTO_INCREMENT, 
            SUPPLY_CHARACTERISTICS_ID INT,
            LOCATION VARCHAR(255),
            TYPE VARCHAR(255),
			CURRENT_CURVE_TYPE VARCHAR(255),
            POLES_NO VARCHAR(255),
            CURRRENT VARCHAR(255),
            VOLTAGE VARCHAR(255),
            FUSE VARCHAR(255),
            RESIDUAL_CURRENT VARCHAR(255),
            RESIDUAL_TIME VARCHAR(255),
            CONSTRAINT PK_CIRCUIT_BREAKER_ID PRIMARY KEY(CIRCUIT_BREAKER_ID),
            CONSTRAINT FK_CIRCUIT_BREAKER_SUPPLY_CHARACTERISTICS_ID FOREIGN KEY (SUPPLY_CHARACTERISTICS_ID) REFERENCES SUPPLY_CHARACTERISTICS_TABLE(SUPPLY_CHARACTERISTICS_ID) ON DELETE CASCADE
                   
);

CREATE TABLE INSTALLATION_LOCATION_TABLE (
            INSTALLATION_LOCATION_REPORT_ID INT AUTO_INCREMENT,
            SUPPLY_CHARACTERISTICS_ID INT,
            LOCATION_NO VARCHAR(255),
            LOCATION_NAME VARCHAR(500),
            ELECTORDE_RESISTANCE_EARTH VARCHAR(500),
            ELECTORDE_RESISTANCE_GRID VARCHAR(255),
            CONSTRAINT PK_INSTALLATION_LOCATION_REPORT_ID PRIMARY KEY(INSTALLATION_LOCATION_REPORT_ID),
            CONSTRAINT FK_INSTALLATION_LOCATION_REPORTS_SUPPLY_CHARACTERISTICS_ID FOREIGN KEY (SUPPLY_CHARACTERISTICS_ID) REFERENCES SUPPLY_CHARACTERISTICS_TABLE(SUPPLY_CHARACTERISTICS_ID) ON DELETE CASCADE


 );

 CREATE TABLE BOUNDING_LOCATION_REPORTS_TABLE (
            BOUNDING_REPORT_ID INT AUTO_INCREMENT,
            SUPPLY_CHARACTERISTICS_ID INT,
			LOCATION VARCHAR(255),
			JOINT_NO VARCHAR(255),
            JOINT_RESISTANCE VARCHAR(255) ,
            CONSTRAINT PK_BOUNDING_LOCATION_REPORT_ID PRIMARY KEY(BOUNDING_REPORT_ID),
            CONSTRAINT FK_BOUNDING_LOCATION_REPORTS_SUPPLY_CHARACTERISTICS_ID FOREIGN KEY (SUPPLY_CHARACTERISTICS_ID) REFERENCES SUPPLY_CHARACTERISTICS_TABLE(SUPPLY_CHARACTERISTICS_ID) ON DELETE CASCADE


 );
 
  CREATE TABLE EARTHING_LOCATION_REPORTS_TABLE (
            EARTHING_LOCATION_REPORT_ID INT AUTO_INCREMENT,
            SUPPLY_CHARACTERISTICS_ID INT,
			LOCATION VARCHAR(255),
			JOINT_NO VARCHAR(255),
            JOINT_RESISTANCE VARCHAR(255) ,
            CONSTRAINT PK_EARTHING_LOCATION_REPORT_ID PRIMARY KEY(EARTHING_LOCATION_REPORT_ID),
            CONSTRAINT FK_EARTHING_LOCATION_REPORTS_SUPPLY_CHARACTERISTICS_ID FOREIGN KEY (SUPPLY_CHARACTERISTICS_ID) REFERENCES SUPPLY_CHARACTERISTICS_TABLE(SUPPLY_CHARACTERISTICS_ID) ON DELETE CASCADE


 );