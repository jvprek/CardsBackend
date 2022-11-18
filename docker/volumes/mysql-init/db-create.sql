CREATE DATABASE core_domain;
CREATE TABLE core_domain.account (
    account_id INT PRIMARY KEY,
    cust_name VARCHAR(255) NOT NULL,
    pan VARCHAR(16) NOT NULL,
    expires DATE NOT NULL,
    balance DECIMAL(8,2) NOT NULL
);

CREATE USER 'core_domain'@'%' IDENTIFIED BY 'changeme';
GRANT ALL ON core_domain.* TO 'core_domain'@'%';
    
    
CREATE DATABASE cards_domain;
CREATE TABLE cards_domain.account (
    account_id INT PRIMARY KEY,
    cust_name VARCHAR(255) NOT NULL,
    pan VARCHAR(16) NOT NULL,
    expires DATE NOT NULL,
    balance DECIMAL(8,2) NOT NULL
);

CREATE USER 'cards_domain'@'%' IDENTIFIED BY 'changeme';
GRANT ALL ON cards_domain.* TO 'cards_domain'@'%';
