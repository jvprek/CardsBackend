-- CARDS DOMAIN
USE cards_domain;

INSERT INTO cards_domain.accounts (account_id, cust_name, pan, expires, balance, sts)
VALUES (1, 'JOHN DOE', '5555555555554444', '2026-11-18', 0, 0),
       (2, 'GEORGE FORD', '5454545454545454', '2026-11-19', 2000.00, 1),
       (3, 'ANITA ECKBERG', '5454545454545455', '2026-11-20', 2000.00, 2),
       (4, 'DORA LEMOS', '5454545454545456', '2026-11-21', 300.00, 1);

