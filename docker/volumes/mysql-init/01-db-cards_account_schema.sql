-- CARDS DOMAIN SCHEMA
CREATE
DATABASE cards_domain;
CREATE
USER 'cards_domain'@'%' IDENTIFIED BY 'changeme';
GRANT ALL
ON cards_domain.* TO 'cards_domain'@'%';

CREATE TABLE cards_domain.accounts
(
    account_id INT           PRIMARY KEY,
    cust_name  VARCHAR(64)   NOT NULL,
    pan        CHAR(16)      NOT NULL,
    expires    DATE          NOT NULL,
    balance    DECIMAL(8, 2) NOT NULL,
    sts        INT           NOT NULL
);



