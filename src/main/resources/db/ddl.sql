DROP TABLE CART_PRODUCT;
DROP TABLE PRODUCT;
DROP TABLE CART;
DROP TABLE MANUFACTURER;

CREATE TABLE MANUFACTURER
(
    ID   BIGSERIAL    NOT NULL PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,

    UNIQUE (NAME)
);

DROP TABLE MANUFACTURER;

CREATE TABLE PRODUCT
(
    ID                 BIGSERIAL      NOT NULL PRIMARY KEY,
    TITLE              VARCHAR(255)   NOT NULL,
    COST               DECIMAL(10, 2) NOT NULL,
    MANUFACTURE_DATE   DATE           NOT NULL,
    VERSION            INT            NOT NULL DEFAULT 0,
    CREATED_BY         VARCHAR(255),
    CREATED_DATE       TIMESTAMP,
    LAST_MODIFIED_BY   VARCHAR(255),
    LAST_MODIFIED_DATE TIMESTAMP,
    STATUS             VARCHAR(20)    NOT NULL DEFAULT 'ACTIVE',
    UNIQUE (TITLE)
);

CREATE TABLE CART
(
    ID     BIGSERIAL NOT NULL PRIMARY KEY,
    STATUS VARCHAR(255) DEFAULT ''
);

CREATE TABLE CART_PRODUCT
(
    CART_ID    BIGINT NOT NULL,
    PRODUCT_ID BIGINT NOT NULL,

    PRIMARY KEY (CART_ID, PRODUCT_ID),

    CONSTRAINT fk_cart_product_cart
        FOREIGN KEY (CART_ID)
            REFERENCES CART (ID),


    CONSTRAINT fk_cart_product_product
        FOREIGN KEY (PRODUCT_ID)
            REFERENCES PRODUCT (ID)
);

CREATE TABLE ACCOUNT_USER
(
    ID                      BIGSERIAL    NOT NULL PRIMARY KEY,
    username                VARCHAR(255) NOT NULL,
    password                VARCHAR(255) NOT NULL,
    firstname               VARCHAR(255) NOT NULL,
    lastname                VARCHAR(255) NOT NULL,
    account_non_expired     BOOLEAN      NOT NULL,
    account_non_locked      BOOLEAN      NOT NULL,
    credentials_non_expired BOOLEAN      NOT NULL,
    enabled                 BOOLEAN      NOT NULL,

    UNIQUE (username)
);

CREATE TABLE AUTHORITY
(
    ID   BIGSERIAL    NOT NULL PRIMARY KEY,
    permission VARCHAR(255) NOT NULL,

    UNIQUE (permission)
);

create table account_role
(
    ID   BIGSERIAL    NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,

    UNIQUE (name)
);

CREATE TABLE user_role
(
    USER_ID    BIGINT NOT NULL,
    ROLE_ID BIGINT NOT NULL,

    PRIMARY KEY (USER_ID, ROLE_ID),

    CONSTRAINT fk_user_authority_account_user
        FOREIGN KEY (USER_ID)
            REFERENCES ACCOUNT_USER (ID),


    CONSTRAINT fk_user_role
        FOREIGN KEY (ROLE_ID)
            REFERENCES account_role (ID)
);

CREATE TABLE role_authority
(
    ROLE_ID    BIGINT NOT NULL,
    AUTHORITY_ID BIGINT NOT NULL,

    PRIMARY KEY (ROLE_ID, AUTHORITY_ID),

    CONSTRAINT fk_user_authority_account_role
        FOREIGN KEY (ROLE_ID)
            REFERENCES account_role (ID),


    CONSTRAINT fk_user_authority_authority
        FOREIGN KEY (AUTHORITY_ID)
            REFERENCES AUTHORITY (ID)
);



SELECT *
FROM cart;

ALTER TABLE PRODUCT
    ADD COLUMN VERSION            INT NOT NULL DEFAULT 0,
    ADD COLUMN CREATED_BY         VARCHAR(255),
    ADD COLUMN CREATED_DATE       TIMESTAMP,
    ADD COLUMN LAST_MODIFIED_BY   VARCHAR(255),
    ADD COLUMN LAST_MODIFIED_DATE TIMESTAMP;

ALTER TABLE PRODUCT
    ADD COLUMN STATUS VARCHAR(20) NOT NULL DEFAULT 'ACTIVE';

SELECT *
FROM PRODUCT;-- WHERE ID=106;

insert into account_user (username, password, firstname, lastname, account_non_expired, account_non_locked, credentials_non_expired, enabled)
values ('user', '$2a$10$CtjdavwwBXwFZ1liD.nfhu4ua.g7ArJxojCcZ98IdBv0dpSeUdDti', 'Иван', 'Иванов', true, true, true, true),
 ('admin', '$2a$10$pxwuknQocnU34d4ooFz/ougUnHI0xSlbgqhDbOtYFhjetJuZY1E92', 'Владимир', 'Николаев', true, true, true, true);

insert into authority (permission)
values ('product.create'),
       ('product.read'),
       ('product.update'),
       ('product.delete');

insert into account_role(name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into user_role(user_id, role_id)
values (1, 2),
       (2, 1);

insert into role_authority(ROLE_ID, AUTHORITY_ID)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 2);


