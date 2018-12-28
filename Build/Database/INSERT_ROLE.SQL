TRUNCATE TABLE ROLE;

INSERT INTO ROLE VALUES (1,'ADMIN','Role for User account management');
INSERT INTO ROLE VALUES (2,'CUSTOMER','Role for Customer to buy, view, claim and renew policies');
INSERT INTO ROLE VALUES (3,'OPERATIONS','Role for Operations to resolve Customer queries');
INSERT INTO ROLE VALUES (4,'MANAGER','Role for Managers to approve policies, claims and resolve user queries');
INSERT INTO ROLE VALUES (5,'INSPECTOR','Role for Inspectors to provide comments for claims');

COMMIT;