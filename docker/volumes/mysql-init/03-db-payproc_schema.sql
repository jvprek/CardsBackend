-- PAYMENT PROCESSOR

CREATE
    DATABASE payproc;
CREATE
    USER 'payproc'@'%' IDENTIFIED BY 'changeme';
GRANT ALL
    ON payproc.* TO 'payproc'@'%';

CREATE TABLE payproc.cards
(
    pan       CHAR(16)      PRIMARY KEY,
    cust_name VARCHAR(64)   NOT NULL,
    expires   DATE          NOT NULL,
    balance   DECIMAL(8, 2) NOT NULL,
    pin       CHAR(4)       NOT NULL,
    sts       INT           NOT NULL
);
