CREATE TABLE QUERY (
QUERYID NUMBER(20) PRIMARY KEY,
NAME VARCHAR2(100) NOT NULL,
EMAILID VARCHAR2(100) NOT NULL,
QUERYTYPE VARCHAR2(50) NOT NULL,
QUERYDESCRIPTION VARCHAR2(255) NOT NULL,
QUERYRESPONSE VARCHAR2(255),
CREATIONDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
ASSIGNEDTO VARCHAR2(100) DEFAULT 'OPERATIONS',
STATUS VARCHAR2 (20) NOT NULL
);

ALTER TABLE QUERY
ADD FOREIGN KEY (EMAILID) 
REFERENCES CUSTOMER(EMAILID);