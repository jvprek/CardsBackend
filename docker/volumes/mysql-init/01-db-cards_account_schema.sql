-- CARDS DOMAIN SCHEMA
CREATE
    DATABASE cards_domain;
CREATE
    USER 'cards_domain'@'%' IDENTIFIED BY 'changeme';
GRANT ALL
    ON cards_domain.* TO 'cards_domain'@'%';

CREATE TABLE cards_domain.accounts
(
    account_id INT PRIMARY KEY,
    cust_name  VARCHAR(64)   NOT NULL,
    pan        CHAR(16)      NOT NULL,
    expires    DATE          NOT NULL,
    balance    DECIMAL(8, 2) NOT NULL,
    sts        TINYINT      NOT NULL
);


CREATE TABLE cards_domain.user_requests
(
    id           INT AUTO_INCREMENT PRIMARY KEY,
    account_id   INT,
    request_type TINYINT NOT NULL,
    submitted    DATETIME NOT NULL,
    completed    DATETIME,
    CONSTRAINT user_requests_accounts_fk FOREIGN KEY (account_id)
        REFERENCES cards_domain.accounts (account_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE

);




