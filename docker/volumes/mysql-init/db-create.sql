-- PAYMENT PROCESSOR

CREATE
DATABASE payproc;
CREATE
USER 'payproc'@'%' IDENTIFIED BY 'changeme';
GRANT ALL
ON payproc.* TO 'payproc'@'%';

CREATE TABLE payproc.cards
(
    pan       char(16) PRIMARY KEY,
    cust_name VARCHAR(64)   NOT NULL,
    expires   DATE          NOT NULL,
    balance   DECIMAL(8, 2) NOT NULL,
    pin       CHAR(4)       NOT NULL,
    status    INT           NOT NULL
);

INSERT INTO payproc.cards(pan, cust_name, expires, balance, status)
VALUES ('5555555555554444', 'JOHN DOE', '2026-11-18', 2000.00,'1111' 0),
       ('5454545454545454', 'GEORGE FORD', '2026-11-19', 2000.00, '2222', 1),
       ('5454545454545455', 'ANITA ECKBERG', '2026-11-20', 2000.00, '3333', 2),
       ('5454545454545456', 'DORA LEMOS', '2026-11-21', 2000.00, '4444', 1);
-- CORE DOMAIN
CREATE
DATABASE core_domain;
CREATE
USER 'core_domain'@'%' IDENTIFIED BY 'changeme';
GRANT ALL
ON core_domain.* TO 'core_domain'@'%';

CREATE TABLE core_domain.card_accounts
(
    account_id INT PRIMARY KEY,
    pan        char(16)      NOT NULL,
    balance    DECIMAL(8, 2) NOT NULL
);

INSERT INTO core_domain.card_accounts (account_id, pan, balance)
VALUES (1, '5555555555554444', 2000.00),
       (2, '5454545454545454', 2000.00),
       (3, '5454545454545455', 2000.00),
       (4, '5454545454545456', 2000.00);

-- CARDS DOMAIN
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
    status     INT           NOT NULL
);

INSERT INTO cards_domain.accounts (account_id, cust_name, pan, expires, balance, status)
VALUES (1, 'JOHN DOE', '5555555555554444', '2026-11-18', 2000.00, 0),
       (2, 'GEORGE FORD', '5454545454545454', '2026-11-19', 2000.00, 1),
       (3, 'ANITA ECKBERG', '5454545454545455', '2026-11-20', 2000.00, 2),
       (4, 'DORA LEMOS', '5454545454545456', '2026-11-21', 2000.00, 1);

