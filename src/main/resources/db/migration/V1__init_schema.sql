CREATE SEQUENCE bank.users_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE bank.users
(
    id                       NUMBER(38, 0) NOT NULL,
    first_name               VARCHAR2(255),
    last_name                VARCHAR2(255),
    middle_name              VARCHAR2(255),
    email                    VARCHAR2(255),
    gender                   VARCHAR2(255),
    address                  VARCHAR2(255),
    state_of_origin          VARCHAR2(255),
    account_number           VARCHAR2(255),
    balance                  NUMBER(19, 4),
    phone_number             VARCHAR2(255),
    alternative_phone_number VARCHAR2(255),
    status                   VARCHAR2(255),
    created_at               TIMESTAMP,
    updated_at               TIMESTAMP,
    CONSTRAINT pk_users PRIMARY KEY (id)
);