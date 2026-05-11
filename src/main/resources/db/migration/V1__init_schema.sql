CREATE SEQUENCE bank.accounts_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE bank.address_infos_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE bank.postings_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE bank.transactions_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE bank.users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE bank.accounts
(
    deleted_at  NUMBER(1)     NOT NULL,
    id          NUMBER(38, 0) NOT NULL,
    card_number VARCHAR2(255) NOT NULL,
    balance     DECIMAL       NOT NULL,
    user_id     NUMBER(38, 0) NOT NULL,
    created_at  TIMESTAMP,
    due_to      TIMESTAMP     NOT NULL,
    updated_at  TIMESTAMP,
    CONSTRAINT pk_accounts PRIMARY KEY (id)
);

CREATE TABLE bank.address_infos
(
    id          NUMBER(38, 0) NOT NULL,
    country     VARCHAR2(255) NOT NULL,
    region      VARCHAR2(255) NOT NULL,
    city        VARCHAR2(255) NOT NULL,
    address     VARCHAR2(255) NOT NULL,
    postal_code VARCHAR2(255) NOT NULL,
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    CONSTRAINT pk_address_infos PRIMARY KEY (id)
);

CREATE TABLE bank.postings
(
    deleted_at     NUMBER(1)     NOT NULL,
    id             NUMBER(38, 0) NOT NULL,
    account_id     NUMBER(38, 0) NOT NULL,
    type           NUMBER(5),
    amount         DECIMAL,
    description    VARCHAR2(255),
    transaction_id NUMBER(38, 0) NOT NULL,
    created_at     TIMESTAMP     NOT NULL,
    CONSTRAINT pk_postings PRIMARY KEY (id)
);

CREATE TABLE bank.transactions
(
    deleted_at          NUMBER(1)     NOT NULL,
    id                  NUMBER(38, 0) NOT NULL,
    from_number         VARCHAR2(255) NOT NULL,
    to_number           VARCHAR2(255) NOT NULL,
    amount              DECIMAL       NOT NULL,
    transaction_comment VARCHAR2(255),
    created_at          TIMESTAMP,
    CONSTRAINT pk_transactions PRIMARY KEY (id)
);

CREATE TABLE bank.users
(
    deleted_at               NUMBER(1)     NOT NULL,
    id                       NUMBER(38, 0) NOT NULL,
    first_name               VARCHAR2(255) NOT NULL,
    last_name                VARCHAR2(255) NOT NULL,
    middle_name              VARCHAR2(255) NOT NULL,
    email                    VARCHAR2(255) NOT NULL,
    password                 VARCHAR2(255) NOT NULL,
    gender                   VARCHAR2(255) NOT NULL,
    address_info_id          NUMBER(38, 0) NOT NULL,
    phone_number             VARCHAR2(255) NOT NULL,
    alternative_phone_number VARCHAR2(255),
    status                   NUMBER(5)     NOT NULL,
    created_at               TIMESTAMP,
    updated_at               TIMESTAMP,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE bank.accounts
    ADD CONSTRAINT uc_accounts_card_number UNIQUE (card_number);

ALTER TABLE bank.users
    ADD CONSTRAINT uc_users_address_info UNIQUE (address_info_id);

ALTER TABLE bank.users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE bank.users
    ADD CONSTRAINT uc_users_phone_number UNIQUE (phone_number);

ALTER TABLE bank.accounts
    ADD CONSTRAINT FK_ACCOUNTS_ON_USER FOREIGN KEY (user_id) REFERENCES bank.users (id);

ALTER TABLE bank.postings
    ADD CONSTRAINT FK_POSTINGS_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES bank.accounts (id);

ALTER TABLE bank.postings
    ADD CONSTRAINT FK_POSTINGS_ON_TRANSACTION FOREIGN KEY (transaction_id) REFERENCES bank.transactions (id);

ALTER TABLE bank.users
    ADD CONSTRAINT FK_USERS_ON_ADDRESS_INFO FOREIGN KEY (address_info_id) REFERENCES bank.address_infos (id);


