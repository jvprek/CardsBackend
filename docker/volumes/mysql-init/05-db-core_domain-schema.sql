-- CORE DOMAIN
CREATE
DATABASE core_domain;
CREATE
USER 'core_domain'@'%' IDENTIFIED BY 'changeme';
GRANT ALL
ON core_domain.* TO 'core_domain'@'%';

CREATE TABLE core_domain.card_accounts
(
    account_id INT           PRIMARY KEY,
    pan        char(16)      NOT NULL,
    balance    DECIMAL(8, 2) NOT NULL
);
