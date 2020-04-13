DROP ALL OBJECTS;

CREATE TABLE User
(
    id           IDENTITY PRIMARY KEY NOT NULL,
    first_name   VARCHAR(50)          NOT NULL,
    last_name    VARCHAR(50)          NOT NULL,
    description  VARCHAR(50)          NOT NULL,
    login        VARCHAR(50)          NOT NULL,
    password     VARCHAR(50)          NOT NULL,
    admin        BOOLEAN              NOT NULL,
    banned       BOOLEAN              NOT NULL,
    ban_end_date DATE,
);

CREATE TABLE Item
(
    id       IDENTITY PRIMARY KEY NOT NULL,
    name     VARCHAR(50)          NOT NULL,
    borrowed BOOLEAN              NOT NULL,
    owner_id BIGINT               NOT NULL
);

CREATE TABLE Debt
(
    id          IDENTITY PRIMARY KEY NOT NULL,
    return_date DATE                 NOT NULL,
    item_id     BIGINT               NOT NULL,
    creditor_id BIGINT               NOT NULL,
    debtor_id   BIGINT               NOT NULL,
    status_id   BIGINT               NOT NULL
);

CREATE TABLE Status
(
    id   IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(50)          NOT NULL
);

CREATE TABLE Notification
(
    id          IDENTITY PRIMARY KEY NOT NULL,
    description VARCHAR(255)         NOT NULL,
    user_id     BIGINT               NOT NULL
);

CREATE TABLE Complaint
(
    id             IDENTITY PRIMARY KEY NOT NULL,
    description    VARCHAR(255)         NOT NULL,
    complainant_id BIGINT               NOT NULL,
    defendant_id   BIGINT               NOT NULL,
    debt_id        BIGINT               NOT NULL,
    status_id      BIGINT               NOT NULL
);

ALTER TABLE Notification
    ADD CONSTRAINT notification_user_id_fk FOREIGN KEY (user_id) REFERENCES User (id);

ALTER TABLE Complaint
    ADD CONSTRAINT complaint_status_id_fk FOREIGN KEY (status_id) REFERENCES Status (id);

ALTER TABLE Complaint
    ADD CONSTRAINT complaint_debt_id_fk FOREIGN KEY (debt_id) REFERENCES Debt (id);

ALTER TABLE Complaint
    ADD CONSTRAINT complaint_defendant_id_fk FOREIGN KEY (defendant_id) REFERENCES User (id);

ALTER TABLE Complaint
    ADD CONSTRAINT complaint_complainant_id_fk FOREIGN KEY (complainant_id) REFERENCES User (id);

ALTER TABLE Debt
    ADD CONSTRAINT debt_item_id_fk FOREIGN KEY (item_id) REFERENCES Item (id);

ALTER TABLE Debt
    ADD CONSTRAINT debt_creditor_id_fk FOREIGN KEY (creditor_id) REFERENCES User (id);

ALTER TABLE Debt
    ADD CONSTRAINT debt_debtor_id_fk FOREIGN KEY (debtor_id) REFERENCES User (id);

ALTER TABLE Debt
    ADD CONSTRAINT debt_status_id_fk FOREIGN KEY (status_id) REFERENCES Status (id);

ALTER TABLE Item
    ADD CONSTRAINT item_owner_id_fk FOREIGN KEY (owner_id) REFERENCES User (id);