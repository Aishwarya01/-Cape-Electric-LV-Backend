CREATE DATABASE lv_safety_verification;
use lv_safety_verification;

---------> Users Table <------------

CREATE TABLE users(    
			user_id INTEGER NOT NULL, 
			user_active BOOLEAN,
			creation_date DATETIME,
			email VARCHAR(255),
			first_name VARCHAR(255),
			last_name VARCHAR(255),
			password VARCHAR(255),
			user_role VARCHAR(255),
			user_exist BOOLEAN,
			user_name VARCHAR(255),
			user_type VARCHAR(255),
			updated_date DATETIME,
			one_time_password INTEGER
			PRIMARY KEY (user_id)
	);
	
--------> Country Table with insert queries <------------

CREATE TABLE country_table (
			COUNTRY_ID INTEGER NOT NULL, 
			NAME VARCHAR(255), 
			CODE VARCHAR(255), 
			CONSTRAINT PK_COUNTRY_ID PRIMARY KEY(COUNTRY_ID)
);

			INSERT INTO country_table VALUES (1, 'INDIA', 'IND');
			INSERT INTO country_table VALUES (2, 'UNITED STATES OF AMERICA', 'USA');
			INSERT INTO country_table VALUES (3, 'BANGLADESH', 'BGL');
			INSERT INTO country_table VALUES (4, 'SRILANKA', 'SRI');
			INSERT INTO country_table VALUES (5, 'UNITED KINGDOM', 'UK');
			INSERT INTO country_table VALUES (6, 'PAKISTAN', 'PAK');
			INSERT INTO country_table VALUES (7, 'AFGANISTAN', 'AFG');
			INSERT INTO country_table VALUES (8, 'MALDIVES', 'MDV');
			INSERT INTO country_table VALUES (9, 'UNITED ARAB EMIRATES', 'UAE');
			INSERT INTO country_table VALUES (10, 'CHINA', 'CHN');
			INSERT INTO country_table VALUES (11, 'SINGAPORE', 'SGP');
			INSERT INTO country_table VALUES (12, 'THAILAND', 'THL');
			INSERT INTO country_table VALUES (13, 'AUSTRALIA', 'AUS');
			INSERT INTO country_table VALUES (14, 'NEW ZEALAND', 'NZL');
			INSERT INTO country_table VALUES (15, 'JAPAN', 'JPN');
			INSERT INTO country_table VALUES (16, 'INDONESIA', 'INA');
			INSERT INTO country_table VALUES (17, 'MALAYSIA', 'MLY');
			INSERT INTO country_table VALUES (18, 'GERMANY', 'GNY');
			INSERT INTO country_table VALUES (19, 'FRANCE', 'FRN');
			INSERT INTO country_table VALUES (20, 'RUSSIA', 'RUS');

--------> State Table with insert queries <------------

CREATE TABLE state_table (
			STATE_ID INT NOT NULL, 
			COUNTRY_ID INT, 
			NAME VARCHAR(255), 
			CODE VARCHAR(255),
			CONSTRAINT PK_STATE_ID PRIMARY KEY(STATE_ID),
			CONSTRAINT FK_COUNTRY_ID FOREIGN KEY (COUNTRY_ID) REFERENCES country_table(COUNTRY_ID) ON DELETE CASCADE
);

			INSERT INTO state_table VALUES (1,1,'Tamil Nadu','TN');
			INSERT INTO state_table VALUES (2,1,'Andhra Pradesh','AP');
			INSERT INTO state_table VALUES (3,1,'Telengana','TS');
			INSERT INTO state_table VALUES (4,1,'Karnataka','KA');
			INSERT INTO state_table VALUES (5,1,'Kerala','KL');
			INSERT INTO state_table VALUES (6,1,'Odisha','OR');
			INSERT INTO state_table VALUES (7,1,'Maharastra','MH');
			INSERT INTO state_table VALUES (8,1,'Madhya Pradesh','MP');
			INSERT INTO state_table VALUES (9,1,'Chattisgarh','CG');
			INSERT INTO state_table VALUES (10,1,'Jharkand','JD');
			INSERT INTO state_table VALUES (11,1,'Bihar','BH');
			INSERT INTO state_table VALUES (12,1,'Uttar Pradesh','UP');
			INSERT INTO state_table VALUES (13,1,'Gujarat','GU');
			INSERT INTO state_table VALUES (14,1,'Rajasthan','RJ');
			INSERT INTO state_table VALUES (15,1,'Punjab','PB');
			INSERT INTO state_table VALUES (16,1,'Haryana','HR');
			INSERT INTO state_table VALUES (17,1,'Uttarkhand','UT');
			INSERT INTO state_table VALUES (18,1,'Sikkim','SK');
			INSERT INTO state_table VALUES (19,1,'West Bengal','WB');
			INSERT INTO state_table VALUES (20,1,'Assam','AS');
			INSERT INTO state_table VALUES (21,1,'Arunachala Pradesh','AR');
			INSERT INTO state_table VALUES (22,1,'Nagaland','NG');
			INSERT INTO state_table VALUES (23,1,'Tripura','TP');
			INSERT INTO state_table VALUES (24,1,'Meghalaya','MG');
			INSERT INTO state_table VALUES (25,1,'Mizoram','MZ');
			INSERT INTO state_table VALUES (26,1,'Manipur','GU');
			INSERT INTO state_table VALUES (27,1,'Goa','GA');
			INSERT INTO STATE_TABLE VALUES (28,2,'Others','OT');

--------> Applicationtypes Table with insert queries <------------

CREATE TABLE applicationtypes(
			ID INTEGER NOT NULL, 
			APPLICATION VARCHAR(255), 
			CODE VARCHAR(20), 
			PRIMARY KEY(ID),
			APPLICATION_NAME VARCHAR(10)

);

			 INSERT INTO APPLICATIONTYPES VALUES  (1,'Verification Of LV Systems (IEC 60364-6)', 'LV Systems','LV');
			 INSERT INTO APPLICATIONTYPES VALUES  (2,'Verification Of HV Systems (up to 33 kV) (IEC 61936-1)', 'HV Systems','HV');
			 INSERT INTO APPLICATIONTYPES VALUES  (3,'Lightning Protection Conformity Assessment, Risk Assessment, Inspection And Maintenance (IEC 62305-3 & 4)', 'Risk Assessment', 'LPS');
			 INSERT INTO APPLICATIONTYPES VALUES  (4,'EMC Assessment Of An Installation (IEC 61000-5-1)', 'EMC Assessment','EMC');
			 INSERT INTO APPLICATIONTYPES VALUES  (5,'Failure Analysis Of Electronic Systems', 'Failure Analysis','FAS');
			 INSERT INTO APPLICATIONTYPES VALUES  (6,'Conformity And Project Analysis', 'Conformity Project','CPA');


--------> Company Table <------------

CREATE TABLE company_table (
		    COMPANY_ID INT AUTO_INCREMENT ,
		    COMPANY_CODE VARCHAR(255) NOT NULL,
		    USER_NAME VARCHAR(255) NOT NULL ,
		    CLIENT_NAME VARCHAR(255)NOT NULL,
			IN_ACTIVE BIT,
		    CREATED_BY VARCHAR(255) NOT NULL,
		    UPDATED_BY VARCHAR(255) NOT NULL,
		    CREATED_DATE datetime NOT NULL,    
		    UPDATED_DATE datetime NOT NULL,
		    CONSTRAINT PK_COMPANY_ID PRIMARY KEY(COMPANY_ID)
);

--------> Department Table <------------

CREATE TABLE department_table (
		    DEPARTMENT_ID INT AUTO_INCREMENT,
		    COMPANY_ID INT,
		    DEPARTMENT_CODE VARCHAR(255)NOT NULL,
			USER_NAME VARCHAR(255)NOT NULL,
			CLIENT_NAME VARCHAR(255) NOT NULL,
			DEPARTMENT_NAME VARCHAR(255)NOT NULL,
			CREATED_BY VARCHAR(255)NOT NULL,
		    UPDATED_BY VARCHAR(255)NOT NULL,
		    CREATED_DATE datetime NOT NULL,    
		    UPDATED_DATE datetime NOT NULL,
			CONSTRAINT PK_DEPARTMENT_ID PRIMARY KEY(DEPARTMENT_ID),
		    CONSTRAINT FK_COMPANY_ID FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY_TABLE(COMPANY_ID) ON DELETE CASCADE
);

--------> Site Table <------------

CREATE TABLE site_table (
		    SITE_ID INT AUTO_INCREMENT,
			DEPARTMENT_ID INT,
			SITE_CODE VARCHAR(255) NOT NULL,
		    USER_NAME VARCHAR(255),
		 	CLIENT_NAME VARCHAR(255),
			DEPARTMENT_NAME VARCHAR(255),
			SITE VARCHAR(255) NOT NULL,
			ADDRESSLINE_1 VARCHAR(255) NOT NULL,
			ADDRESSLINE_2 VARCHAR(255) NULL,
			LAND_MARK VARCHAR(255) NULL,
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

CREATE TABLE site_person_table(
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



--------> Part_1  Basic information


CREATE TABLE report_details_table (
		    REPORT_ID INT AUTO_INCREMENT,
		    SITE_ID INT,
			USER_NAME VARCHAR(255),
			DESCRIPTION_REPORT VARCHAR(500),
			REASON_REPORT VARCHAR(500),
			INSTALLATION_TYPE VARCHAR(255),
			DESCRIPTION_PREMISE VARCHAR(255) ,
			ESTIMATED_WIRE_AGE VARCHAR(255),
			EVIDANCE_ADDITION VARCHAR(255),
			EVIDANCE_WIRE_AGE VARCHAR(255),
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
		    LIMITATIONS VARCHAR(255),
			CREATED_BY VARCHAR(255),
		    CREATED_DATE datetime,
		    UPDATED_BY VARCHAR(255),
            UPDATED_DATE datetime,
		    CONSTRAINT PK_REPORT_ID PRIMARY KEY(REPORT_ID)
 );
 
 
 
 CREATE TABLE sign_details_table (
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
			DECLARATION_SIGNATURE VARCHAR(255),
			CONSTRAINT PK_SIGNATOR_ID PRIMARY KEY(SIGNATOR_ID),
			CONSTRAINT FK_REPORT_ID FOREIGN KEY (REPORT_ID) REFERENCES REPORT_DETAILS_TABLE(REPORT_ID) ON DELETE CASCADE
);
 



--------> Part_2 Supply characteristics and earthing arrangement API.

CREATE TABLE supply_characteristics_table (

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
            AL_AVILABLE_SUPPLY VARCHAR(255),
            AL_NUM_SUPPLY VARCHAR(255),
            MAXIMUM_DEMAND VARCHAR(255),
            MAXIMUM_LOAD VARCHAR(255),
            MEANS_EARTHING VARCHAR(255),
            ELECTORDE_TYPE VARCHAR(255),
            ELECTORDE_MATERIAL VARCHAR(255),
            NUM_LOCATION INT,
            CONDUCTOR_SIZE VARCHAR(255),
            CONDUCTOR_MATERIAL VARCHAR(255),
            CONDUCTOR_VERIFY VARCHAR(255),
            BONDING_CONDUCTOR_SIZE VARCHAR(255),
            BONDING_CONDUCTOR_MATERIAL VARCHAR(255),
            BONDING_CONDUCTOR_VERIFY VARCHAR(255),
            BONDING_JOINTS_TYPE VARCHAR(255),
            BONDING_NO_OF_JOINTS INT,
            EARTHING_CONDUCTOR_SIZE VARCHAR(255),
            EARTHING_CONDUCTOR_MATERIAL VARCHAR(255),
            EARTHING_CONDUCTOR_VERIFY VARCHAR(255),
            EARTHING_JOINTS_TYPE VARCHAR(255),
            EARTHING_NO_OF_JOINTS INT,
            CREATED_BY VARCHAR(255),
		    CREATED_DATE datetime,
		    UPDATED_BY VARCHAR(255),
            UPDATED_DATE datetime,
            CONSTRAINT PK_SUPPLY_CHARACTERISTICS_ID PRIMARY KEY(SUPPLY_CHARACTERISTICS_ID)
              
);
 
CREATE TABLE supply_parameters_table (

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
            AL_PROTECTIVE_DEVICE VARCHAR(255),
            AL_RATED_CURRNT VARCHAR(255),
            AL_CURRENT_DISCONNECTION VARCHAR(255),
            CONSTRAINT PK_SUPPLY_PARAMETERS_ID PRIMARY KEY(SUPPLY_PARAMETERS_ID),
            CONSTRAINT FK_SUPPLY_CHARACTERISTICS_ID FOREIGN KEY (SUPPLY_CHARACTERISTICS_ID) REFERENCES SUPPLY_CHARACTERISTICS_TABLE(SUPPLY_CHARACTERISTICS_ID) ON DELETE CASCADE

 );
 
  CREATE TABLE circuit_breaker_table (
 
            CIRCUIT_BREAKER_ID INT AUTO_INCREMENT, 
            SUPPLY_CHARACTERISTICS_ID INT,
            LOCATION VARCHAR(255),
            TYPE VARCHAR(255),
            POLES_NO VARCHAR(255),
            CURRRENT VARCHAR(255),
            VOLTAGE VARCHAR(255),
            FUSE VARCHAR(255),
            RESIDUAL_CURRENT VARCHAR(255),
            RESIDUAL_TIME VARCHAR(255),
            CONSTRAINT PK_CIRCUIT_BREAKER_ID PRIMARY KEY(CIRCUIT_BREAKER_ID),
            CONSTRAINT FK_CIRCUIT_BREAKER_SUPPLY_CHARACTERISTICS_ID FOREIGN KEY (SUPPLY_CHARACTERISTICS_ID) REFERENCES SUPPLY_CHARACTERISTICS_TABLE(SUPPLY_CHARACTERISTICS_ID) ON DELETE CASCADE
                   
);

CREATE TABLE installation_location_table (
            INSTALLATION_LOCATION_REPORT_ID INT AUTO_INCREMENT,
            SUPPLY_CHARACTERISTICS_ID INT,
            LOCATION_NO VARCHAR(255),
            LOCATION_NAME VARCHAR(500),
            ELECTORDE_RESISTANCE_EARTH VARCHAR(500),
            ELECTORDE_RESISTANCE_GRID VARCHAR(255),
            CONSTRAINT PK_INSTALLATION_LOCATION_REPORT_ID PRIMARY KEY(INSTALLATION_LOCATION_REPORT_ID),
            CONSTRAINT FK_INSTALLATION_LOCATION_REPORTS_SUPPLY_CHARACTERISTICS_ID FOREIGN KEY (SUPPLY_CHARACTERISTICS_ID) REFERENCES SUPPLY_CHARACTERISTICS_TABLE(SUPPLY_CHARACTERISTICS_ID) ON DELETE CASCADE


 );

 CREATE TABLE bounding_location_reports_table (
            BOUNDING_REPORT_ID INT AUTO_INCREMENT,
            SUPPLY_CHARACTERISTICS_ID INT,
			LOCATION VARCHAR(255),
			JOINT_NO VARCHAR(255),
            JOINT_RESISTANCE VARCHAR(255) ,
            CONSTRAINT PK_BOUNDING_LOCATION_REPORT_ID PRIMARY KEY(BOUNDING_REPORT_ID),
            CONSTRAINT FK_BOUNDING_LOCATION_REPORTS_SUPPLY_CHARACTERISTICS_ID FOREIGN KEY (SUPPLY_CHARACTERISTICS_ID) REFERENCES SUPPLY_CHARACTERISTICS_TABLE(SUPPLY_CHARACTERISTICS_ID) ON DELETE CASCADE


 );
 
  CREATE TABLE earthing_location_reports_table (
            EARTHING_LOCATION_REPORT_ID INT AUTO_INCREMENT,
            SUPPLY_CHARACTERISTICS_ID INT,
			LOCATION VARCHAR(255),
			JOINT_NO VARCHAR(255),
            JOINT_RESISTANCE VARCHAR(255) ,
            CONSTRAINT PK_EARTHING_LOCATION_REPORT_ID PRIMARY KEY(EARTHING_LOCATION_REPORT_ID),
            CONSTRAINT FK_EARTHING_LOCATION_REPORTS_SUPPLY_CHARACTERISTICS_ID FOREIGN KEY (SUPPLY_CHARACTERISTICS_ID) REFERENCES SUPPLY_CHARACTERISTICS_TABLE(SUPPLY_CHARACTERISTICS_ID) ON DELETE CASCADE


 );
 
--------> part_3  Periodic_inspection
CREATE TABLE periodic_inspection_table(

            PERIODIC_INSPECTION_ID INT AUTO_INCREMENT,
            SITE_ID INT,
			USER_NAME VARCHAR(225),
			CREATED_BY VARCHAR(255),
		    CREATED_DATE datetime,
		    UPDATED_BY VARCHAR(255),
            UPDATED_DATE datetime,
			CONSTRAINT PK_PERIODIC_INSPECTION_ID PRIMARY KEY(PERIODIC_INSPECTION_ID)

);
CREATE TABLE ipao_inspection_table (
            IPAO_INSPECTION_ID INT AUTO_INCREMENT,
            PERIODIC_INSPECTION_ID INT,
            LOCATION_NUMBER INT,
            LOCATION_NAME VARCHAR(225),
            I_SERVICE_CABLE VARCHAR(20),
            I_SERVICE_FUSE VARCHAR(20),
            I_METER_DISTRIBUTOR VARCHAR(20),
            I_METER_CONSUMER VARCHAR(20),
            I_METER_EQU VARCHAR(20),
            I_ISOLATOR VARCHAR(20),
            P_EARTHING_ARRANGEMENT VARCHAR(20),
            P_ADEQUATE_ARRANGEMENT VARCHAR(20),
            P_CONNECTION_GENERATOR VARCHAR(20),
            P_COMPATIBILITY_CHARACTERISTICS VARCHAR(20),
            P_AUTOMATIC_DISCONNECT_GENERATOR VARCHAR(20),
            P_PREVENT_CONNECT_GENERATOR VARCHAR(20),
            P_ISOLATE_GENERATOR VARCHAR(20),
            A_MAIN_EARTHING VARCHAR(20),
            A_EARTH_ELECTORDE_ARRANGEMENT VARCHAR(20),
            A_EARTH_CONDUCTOR_CONNECTION VARCHAR(20),
            A_ACCESSIBILITY VARCHAR(20),
            A_MAIN_PROTECT_BONDING VARCHAR(20),
            A_ALL_PROTECT_BONDING VARCHAR(20),
            A_ALL_APPROPRIATE_LOCATION VARCHAR(20),
            A_FELV_REQUIREMENT VARCHAR(20),
            O_SELV_SYSTEM VARCHAR(20),
            O_PELV_SYSTEM VARCHAR(20),
            O_DOUBLE_INSULATION VARCHAR(20),
            O_REINFORCED_INSULATION VARCHAR(20),
            O_BASIC_ELECTRICAL_SEPARTION VARCHAR(20),
            O_INSULATION_LIVE_PARTS VARCHAR(20),
            O_BARRIERS_ENCLOSERS VARCHAR(20),
            O_OBSTACLES VARCHAR(20),
            O_PLACING_OUT_REACH VARCHAR(20),
            O_NON_CONDUCT_LOCATION VARCHAR(20),
            O_FAULT_ELECTRICAL_SEPARTION VARCHAR(20),
            O_FAULT_NON_CONDUCT_LOCATION VARCHAR(20),
            O_OPERATING_CURRENT VARCHAR(20),
            O_SUPPLEMENTARY_BONDING VARCHAR(20),
            CONSTRAINT PK_IPAO_INSPECTION_ID PRIMARY KEY(IPAO_INSPECTION_ID),
            CONSTRAINT FK_PERIODIC_INSPECTION_ID  FOREIGN KEY (PERIODIC_INSPECTION_ID) REFERENCES PERIODIC_INSPECTION_TABLE(PERIODIC_INSPECTION_ID) ON DELETE CASCADE
 );
 
 CREATE TABLE consumer_table (  
            CONSUMER_ID INT AUTO_INCREMENT,
	        IPAO_INSPECTION_ID INT,
            ACCESS_AND_WORKING VARCHAR(20),
            SECURITY_OF_FIXING VARCHAR(20),
            LIVE_PARTS_NOT_DAMAGE VARCHAR(20),
            SECURITY_OF_BARRIERS VARCHAR(20),
            SUITABILITY_OF_ENCLOSURE VARCHAR(20),
            ENCLOSURE_NOT_DAMAGED VARCHAR(20),
            PRESENCE_OF_OBSTACLES VARCHAR(20),
            PLACING_OUT_OF_CONSUMER VARCHAR(20),
            PRESENCE_MAIN_SWITCHES VARCHAR(20),
            OPERATION_MAIN_SWITCHES VARCHAR(20),
            MANUAL_CIRCUIT_BREAKERS VARCHAR(20),
            SWITCH_CAUSES_RCD VARCHAR(20),
            RCD_FAULT_PROTECTION VARCHAR(20),
            RCD_ADDITIONAL_PROTECTION VARCHAR(20),
            OVER_VOLTAGE_PROTECTION VARCHAR(20),
            INDICATION_OF_SPD VARCHAR(20),
            RCD_QUARTERLY_TEST VARCHAR(20),
            DIAGRAMS_CHARTS VARCHAR(20),
            NONSTANDARD_CABLE_COLOUR VARCHAR(20),
            AL_SUPPLY_OF_ORIGN VARCHAR(20),
            AL_SUPPLY_OF_METER VARCHAR(20),
            AL_SUPPLY_DISTRIBUTION VARCHAR(20),
            ALL_POINTS_ISOLATION VARCHAR(20),
            NEXT_INSPECTION VARCHAR(20),
            OTHER_REQUIRED_LABELLING VARCHAR(20),
            BASES_CORRECT_TYPE VARCHAR(20),
            SINGLE_POLE VARCHAR(20),
            MECHANICAL_DAMAGE VARCHAR(20),
            ELECTROMAGNETIC VARCHAR(20),
            ALL_CONDUCTOR_CON VARCHAR(20),
			CONSTRAINT PK_CONSUMER_ID PRIMARY KEY(CONSUMER_ID),
			CONSTRAINT FK_CONSUMER_IPAO_INSPECTION_ID  FOREIGN KEY (IPAO_INSPECTION_ID) REFERENCES IPAO_INSPECTION_TABLE(IPAO_INSPECTION_ID) ON DELETE CASCADE

 );
		
CREATE TABLE circuit_table (      
            CIRCUIT_ID INT AUTO_INCREMENT,
		    IPAO_INSPECTION_ID INT ,
            IDENTIFICATION_CONDUCTORS VARCHAR(20),
            CABLE_INSTALLATION VARCHAR(20),
            EXAMINATION_CABLES VARCHAR(20),
            EXAMINATION_INSULATION VARCHAR(20),
            NON_SHEATHED_CABLES VARCHAR(20),
            CONTAINMENT_SYSTEMS VARCHAR(20),
            TEMPERATURE_RATING VARCHAR(20),
            CABLES_TERMINATED VARCHAR(20),
            CURRENT_CARRY_CAPACITY VARCHAR(20),
            ADEQUACY_PROTECT_DEVICES VARCHAR(20),
            PRESENCE_PROTECT_CONDUCTORS VARCHAR(20),
            CO_ORDINATION VARCHAR(20),
            WIRING_SYSTEMS VARCHAR(20),
            CABLES_CONCEAL_UNDER_FLOORS VARCHAR(20),
            OPERATING_CURRENT_CIRCUITS VARCHAR(20),
            OPERATING_CURRENT_SOCKET VARCHAR(20),
            CABLES_CONC_DEPTH VARCHAR(20),
            SECTIONS_REGARDLESS_DEPTH VARCHAR(20),
            PROVISION_FIRE_BARRIERS VARCHAR(20),
            SEPARATION_BAND VARCHAR(20),
            SEPARATION_ELECTRICAL VARCHAR(20),
            CONDITION_CIRCUIT_ACCESSORIES VARCHAR(20),
            CONDUCTOR_CORRECT_TERMINATED VARCHAR(20),
            CONDUCTOR_VISIBLE_OUTSIDE VARCHAR(20),
            CONN_LIVE_CONDUCTORS VARCHAR(20),
            ADEQUATELY_CONNECTED_ENCLOSURE VARCHAR(20),
            SUITABILITY_CIRCUIT_ACCESSORIES VARCHAR(20),
            CONDITION_ACCESSORIES VARCHAR(20),
            SINGLE_POLE_DEVICES VARCHAR(20),
            ADEQUACY_CONNECTIONS VARCHAR(20),
            ISOLATION_AND_SWITCHING VARCHAR(20),
			CONSTRAINT PK_CIRCUIT_ID PRIMARY KEY(CIRCUIT_ID),
			CONSTRAINT FK_CIRCUIT_IPAO_INSPECTION_ID  FOREIGN KEY (IPAO_INSPECTION_ID) REFERENCES IPAO_INSPECTION_TABLE(IPAO_INSPECTION_ID) ON DELETE CASCADE
);    
		
		                    
CREATE TABLE isolation_current_table (       
            ISOLATION_CURRENT_ID INT AUTO_INCREMENT,
	    	IPAO_INSPECTION_ID INT,
            I_PRESENCE_DEVICES VARCHAR(20),
            I_CONDITION_DEVICES VARCHAR(20),
            I_LOCATION_DEVICES VARCHAR(20),
            I_CAPABLE_SECURED VARCHAR(20),
            I_OPERATION_VERIFY VARCHAR(20),
            I_INSTALL_CIRCUIT VARCHAR(20),
            I_WARNING_LABEL VARCHAR(20),
            SW_PRESENCE_DEVICES VARCHAR(20),
            SW_CONDITION_DEVICES VARCHAR(20),
            SW_ACCEPTABLE_LOCATION VARCHAR(20),
            SW_CAPABLE_OFF_POSITION VARCHAR(20),
            SW_CORRECT_OPERATION VARCHAR(20),
            SW_CIRCUIT VARCHAR(20),
            SW_WARNING_LABEL VARCHAR(20),
            EM_SWIT_PRESENCE_DEVICES VARCHAR(20),
            EM_SWIT_CONDITION_DEVICES VARCHAR(20),
            EM_SWIT_LOCATION_DEVICES VARCHAR(20),
            EM_SWIT_OPERATION_VERIFY VARCHAR(20),
            EM_SWIT_INSTALL_CIRCUIT VARCHAR(20),
            FU_SWIT_PRESENCE_DEVICES VARCHAR(20),
            FU_SWIT_LOCATION_DEVICES VARCHAR(20),
            FU_SWIT_OPERATION_VERIFY VARCHAR(20),
            C_SUITABILITY_EQUIPMENT VARCHAR(20),
            C_ENCLOSURE_NOT_DAMAGED VARCHAR(20),
            C_SUITABILITY_ENVIRONMENT VARCHAR(20),
            C_SECURITY_FIXING VARCHAR(20),
            C_CABLE_ENTRY_HOLES VARCHAR(20),
            C_PROVISION_VOLTAGE VARCHAR(20),
            C_PROVISION_OVERLOAD VARCHAR(20),
            C_CORRECT_TYPE_LAMPS VARCHAR(20),
            C_INSULA_DISPLACEMENT_BOX VARCHAR(20),
            C_OVERHEAT_SURROUNDING VARCHAR(20),
            C_OVERHEAT_CONDUCTORS VARCHAR(20),
			CONSTRAINT PK_ISOLATION_CURRENT_ID PRIMARY KEY(ISOLATION_CURRENT_ID),
			CONSTRAINT FK_ISOLATION_IPAO_INSPECTION_ID  FOREIGN KEY (IPAO_INSPECTION_ID) REFERENCES IPAO_INSPECTION_TABLE(IPAO_INSPECTION_ID) ON DELETE CASCADE

);

--------> Part_4 TestingAPI

CREATE TABLE testing_reports_table (
				TESTING_REPORT_ID INT AUTO_INCREMENT,
				SITE_ID INT,
				USER_NAME VARCHAR(225),       

				CREATED_BY VARCHAR(255),
			    CREATED_DATE datetime,
			    UPDATED_BY VARCHAR(255),
	            UPDATED_DATE datetime,
				CONSTRAINT PK_TESTING_REPORT_ID PRIMARY KEY(TESTING_REPORT_ID)
              
);


CREATE TABLE testing_table (

				TESTING_ID INT AUTO_INCREMENT,
				TESTING_REPORT_ID INT,
				LOCATION_NUMBER INT,
                LOCATION_NAME VARCHAR(225),
 				TEST_ENGINEER_NAME VARCHAR(225),
				DATE VARCHAR(225),
				DETAILS_TEST_INSTRUMENT VARCHAR(225),
				CONTINUITY VARCHAR(225),
				INSULATION_RESISANCE VARCHAR(225),
				IMPEDANCE VARCHAR(225),
				RCD VARCHAR(225),
				EARTH_ELECTRODE_RESISTANCE VARCHAR(225),
				DESIGNATION  VARCHAR(225),
				COMPANY_NAME  VARCHAR(225),        

 				CONSTRAINT PK_TESTING_ID PRIMARY KEY(TESTING_ID),
				CONSTRAINT FK_DISTRIBUTION_TESTING_REPORT_ID FOREIGN KEY (TESTING_REPORT_ID) REFERENCES TESTING_REPORTS_TABLE(TESTING_REPORT_ID) ON DELETE CASCADE 
               
);

CREATE TABLE testing_distribution_table (

                DISTRIBUTION_ID INT AUTO_INCREMENT,
				TESTING_ID INT,
                DISTRIBUTION_BOARD_DETAILS VARCHAR(225),
				REFERANCE VARCHAR(225),
				LOCATION VARCHAR(225),
				CORRECT_SUPPLY_POLARITY VARCHAR(225),
				NUM_OUTPUT_CIRCUITS_USE VARCHAR(225),
				RATINGS_AMPS VARCHAR(225),
				NUM_OUTPUT_CIRCUITS_SPARE VARCHAR(225),
				INSTALLED_EQUIPMENT_VULNARABLE  VARCHAR(225),
				INCOMING_VOLTAGE VARCHAR(225),
				INCOMING_ZS VARCHAR(225),
				INCOMING_IPF VARCHAR(225),
				CONSTRAINT PK_TESTING_DISTRIBUTION_ID PRIMARY KEY(DISTRIBUTION_ID),
				CONSTRAINT FK_DISTRIBUTION_TESTING_ID FOREIGN KEY (TESTING_ID) REFERENCES TESTING_TABLE(TESTING_ID) ON DELETE CASCADE     

				
	);

CREATE TABLE testing_records_table ( 
                TESTING_RECORD_ID INT AUTO_INCREMENT,
				TESTING_ID INT,
 				CIRCUIT_NO VARCHAR(225),
				CIRCUIT_DESC  VARCHAR(225),
				CIRCUIT_STANDARD_NO  VARCHAR(225),
				CIRCUIT_TYPE  VARCHAR(225), 
				CIRCUIT_RATING VARCHAR(225), 
				CIRCUIT_BREAKING_CAPACITY VARCHAR(225),
				CONDUCTOR_INSTALLATION VARCHAR(225),
				CONDUCTOR_LIVE VARCHAR(225),
				CONDUCTOR_PECPC VARCHAR(225),
				CONTINUTIY_APPROXIMATELENGTH VARCHAR(225),
				CONTINUTIY_R1_R2 VARCHAR(225),
				CONTINUTIY_R2 VARCHAR(225),
				CONTINUTIY_LL VARCHAR(225),
				CONTINUTIY_LE VARCHAR(225),
				CONTINUTIY_POLARITY VARCHAR(225),
				TEST_VOLTAGE VARCHAR(225),
				TEST_LOOPIMPEDANCE VARCHAR(225),
				TEST_FAULTCURRENT VARCHAR(225),
				DISCONNECTION_TIME VARCHAR(225),
				 
				RCD_CURRENT VARCHAR(225),
				RCD_OPERATINGCURRENT VARCHAR(225),
				RCD_OPERATINGFIVECURRENT VARCHAR(225),
				RCD_TESTBUTTONOPERATION VARCHAR(225),
				RCD_REMARKS VARCHAR(225),
					
				CONSTRAINT PK_TESTING_RECORD_ID PRIMARY KEY(TESTING_RECORD_ID),
				CONSTRAINT FK_TESTING_ID FOREIGN KEY (TESTING_ID) REFERENCES TESTING_TABLE(TESTING_ID) ON DELETE CASCADE              

 );

 

--------> Part_5 Summary_Section

 CREATE TABLE summary_table (
            SUMMARY_ID INT AUTO_INCREMENT,
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
            CREATED_BY VARCHAR(255),
		    CREATED_DATE datetime,
		    UPDATED_BY VARCHAR(255),
            UPDATED_DATE datetime,
            CONSTRAINT PK_SUMMARY_ID PRIMARY KEY(SUMMARY_ID)
              
);

CREATE TABLE observations_table (
            OBSERVATIONS_ID INT AUTO_INCREMENT,
			SUMMARY_ID INT,
            OBSERVATIONS  VARCHAR(255),
            REFERANCE_NUMBER_REPORT VARCHAR(255),
            FURTHER_ACTIONS  VARCHAR(255),
            COMMENT VARCHAR(255),
            CONSTRAINT PK_OBSERVATIONS_ID PRIMARY KEY(OBSERVATIONS_ID),
			CONSTRAINT FK_OBSERVATIONS_SUMMARY_ID FOREIGN KEY (SUMMARY_ID) REFERENCES SUMMARY_TABLE(SUMMARY_ID) ON DELETE CASCADE              
);

   CREATE TABLE declaration_table (
            DECLARATION_ID INT AUTO_INCREMENT,
			SUMMARY_ID INT,
            DECLARATION_ROLE  VARCHAR(255),
            NAME VARCHAR(255),
            SIGNATURE  VARCHAR(255),
            COMPANY VARCHAR(255),
            POSITION VARCHAR(255),
            ADDRESS VARCHAR(500),
            DATE VARCHAR(255),			
            CONSTRAINT PK_DECLARATION_ID PRIMARY KEY(DECLARATION_ID),
			CONSTRAINT FK_DECLARATION_SUMMARY_ID FOREIGN KEY (SUMMARY_ID) REFERENCES SUMMARY_TABLE(SUMMARY_ID) ON DELETE CASCADE              
);