CREATE TABLE QUERY (
QUERYID NUMBER(20) PRIMARY KEY,
NAME VARCHAR2(100) NOT NULL,
EMAILID VARCHAR2(100) NOT NULL,
QUERYTYPE VARCHAR2(50) NOT NULL,
QUERYDESCRIPTION VARCHAR2(255) NOT NULL,
QUERYRESPONSE VARCHAR2(255),
CREATIONDATE DATE,
ASSIGNEDTO VARCHAR2(100) DEFAULT 'OPERATIONS',
STATUS VARCHAR2 (10) NOT NULL
);

ALTER TABLE QUERY
ADD FOREIGN KEY (EMAILID) 
REFERENCES CUSTOMER(EMAILID);