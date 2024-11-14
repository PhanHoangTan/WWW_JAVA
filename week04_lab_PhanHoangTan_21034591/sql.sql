USE lab06_accounts;

CREATE TABLE Account (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_name VARCHAR(255) NOT NULL,  -- Use snake case
    balance DOUBLE NOT NULL
);
