CREATE TABLE ACCIDENTDETAILS (
	ACCIDENTID NUMBER(10) PRIMARY KEY,
	ACCIDENTDATE TIMESTAMP NOT NULL,
	LOCATION VARCHAR2(100) NOT NULL,
	CITY VARCHAR2(100) NOT NULL,
	STATE VARCHAR2(100) NOT NULL,
	NOOFPERSONSTRAVELLING NUMBER(2) NOT NULL,
	ACCIDENTDESCRIPTION VARCHAR2(255) NOT NULL,
	THIRDPARTYRESPONSIBLE CHAR(1) DEFAULT 'N',
	FIRDETAILS VARCHAR2(255),
	DRIVERNAME VARCHAR2(100),
	DRIVERRELATON VARCHAR2(50),
	DRIVERCONTACT VARCHAR2(20),
	DRIVERLICENSENO VARCHAR2(50)
);