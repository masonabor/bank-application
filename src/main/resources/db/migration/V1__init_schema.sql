CREATE SEQUENCE bank.address_info_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE bank.bank_accounts_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE bank.users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE bank.address_info
(
    id          NUMBER(38, 0) NOT NULL,
    country     VARCHAR2(255) NOT NULL,
    region      VARCHAR2(255) NOT NULL,
    city        VARCHAR2(255) NOT NULL,
    address     VARCHAR2(255) NOT NULL,
    postal_code VARCHAR2(255) NOT NULL,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    CONSTRAINT pk_address_info PRIMARY KEY (id)
);

CREATE TABLE bank.bank_accounts
(
    id NUMBER(38, 0) NOT NULL,
    card_number     VARCHAR2(255) NOT NULL,
    balance         DECIMAL       NOT NULL,
    user_id         NUMBER(38, 0) NOT NULL,
    created_at      TIMESTAMP,
    due_to          TIMESTAMP     NOT NULL,
    updated_at      TIMESTAMP,
    CONSTRAINT pk_bank_accounts PRIMARY KEY (id)
);

CREATE TABLE bank.users
(
    id                       NUMBER(38, 0) NOT NULL,
    first_name               VARCHAR2(255) NOT NULL,
    last_name                VARCHAR2(255) NOT NULL,
    middle_name              VARCHAR2(255) NOT NULL,
    email                    VARCHAR2(255) NOT NULL,
    gender                   VARCHAR2(255) NOT NULL,
    address_info_id          NUMBER(38, 0),
    phone_number             VARCHAR2(255) NOT NULL,
    alternative_phone_number VARCHAR2(255),
    status                   NUMBER(5)     NOT NULL,
    created_at               TIMESTAMP,
    updated_at               TIMESTAMP,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE bank.bank_accounts
    ADD CONSTRAINT uc_bank_accounts_card_number UNIQUE (card_number);

ALTER TABLE bank.users
    ADD CONSTRAINT uc_users_address_info UNIQUE (address_info_id);

ALTER TABLE bank.users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE bank.users
    ADD CONSTRAINT uc_users_phone_number UNIQUE (phone_number);

ALTER TABLE bank.bank_accounts
    ADD CONSTRAINT FK_BANK_ACCOUNTS_ON_USER FOREIGN KEY (user_id) REFERENCES bank.users (id);

ALTER TABLE bank.users
    ADD CONSTRAINT FK_USERS_ON_ADDRESS_INFO FOREIGN KEY (address_info_id) REFERENCES bank.address_info (id);