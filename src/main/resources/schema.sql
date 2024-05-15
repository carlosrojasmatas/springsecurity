-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile
drop table if exists account_transactions;
drop table if exists accounts;
drop table if exists users;
drop table if exists loans;
drop table if exists cards;
drop table if exists authorities;
drop table if exists customer;
drop table if exists contact_messages;
drop table if exists notice_details;

CREATE TABLE customer
(
    id            uuid PRIMARY KEY,
    name          varchar(100) NOT NULL,
    email         varchar(100) NOT NULL,
    mobile_number varchar(20)  NOT NULL,
    pwd           text         NOT NULL,
    role          varchar(50)  NOT NULL,
    created_at    timestamp    NOT NULL DEFAULT current_timestamp
);

INSERT INTO customer VALUES ('03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', 'carlos', 'carlos@carlos.com', '073777777','$2a$12$zr6O/XFoGPO73j6.nHm3cOdXtapAEv1AlFD0ZD/by6FyqvChQvS4y', 'ADMIN');

CREATE TABLE accounts
(
    account_number int          NOT NULL PRIMARY KEY,
    customer_id    uuid         NOT NULL,
    account_type   varchar(100) NOT NULL,
    branch_address varchar(200) NOT NULL,
    created_at      timestamp    NOT NULL DEFAULT current_timestamp,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);

CREATE INDEX customer_id ON accounts (customer_id);
INSERT INTO accounts VALUES (1865764534,'03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', 'SAVINGS', 'Sveavägen 46, 111 34 Stockholm');

CREATE TABLE account_transactions
(
    id                  uuid           NOT NULL PRIMARY KEY,
    account_number      int            NOT NULL,
    customer_id         uuid           NOT NULL,
    transaction_dt      timestamp      NOT NULL,
    transaction_type    varchar(100)   NOT NULL,
    transaction_amount  decimal(10, 2) NOT NULL,
    transaction_summary varchar(200)   NOT NULL,
    closing_balance     decimal(10, 2) NOT NULL,
    created_at          timestamp      NOT NULL DEFAULT current_timestamp,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE,
    CONSTRAINT fk_account FOREIGN KEY (account_number) REFERENCES accounts (account_number) ON DELETE CASCADE
);

CREATE INDEX on account_transactions (customer_id, account_number);

INSERT INTO account_transactions (id, account_number, customer_id, transaction_dt, transaction_type, transaction_amount,
                                  transaction_summary, closing_balance)
VALUES (gen_random_uuid(), 1865764534, '03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', current_timestamp + interval '12 hours',
        'Withdrawal', 30,'Coffee Shop', 34500);

INSERT INTO account_transactions (id, account_number, customer_id, transaction_dt, transaction_type, transaction_amount,
                                  transaction_summary,
                                  closing_balance)
VALUES (gen_random_uuid(), 1865764534, '03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', current_timestamp - interval '13 hours', 'Deposit',
        50, 'Uber', 34500);

INSERT INTO account_transactions (id, account_number, customer_id, transaction_dt, transaction_type, transaction_amount,
                                  transaction_summary,
                                  closing_balance)
VALUES (gen_random_uuid(), 1865764534, '03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', current_timestamp + interval '5 days',
        'Self Deposit', 500,'Deposit',  34900);

INSERT INTO account_transactions (id, account_number, customer_id, transaction_dt, transaction_type, transaction_amount,
                                  transaction_summary,
                                  closing_balance)
VALUES (gen_random_uuid(), 1865764534, '03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', current_timestamp - interval '6 days', 'Ebay',
        600,'Withdrawal',  34300);

INSERT INTO account_transactions (id, account_number, customer_id, transaction_dt, transaction_type, transaction_amount,
                                  transaction_summary,
                                  closing_balance)
VALUES (gen_random_uuid(), 1865764534, '03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', current_timestamp + interval '1 week',
        'OnlineTransfer', 700,'Deposit', 35000);

INSERT INTO account_transactions (id, account_number, customer_id, transaction_dt, transaction_type, transaction_amount,
                                  transaction_summary,
                                  closing_balance)
VALUES (gen_random_uuid(), 1865764534, '03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', current_timestamp - interval '1 week', 'Amazon.com',
        100,'Withdrawal', 34900);

CREATE TABLE loans
(
    loan_number        serial          NOT NULL,
    customer_id        uuid          NOT NULL,
    start_dt           date         NOT NULL,
    loan_type          varchar(100) NOT NULL,
    total_loan         int          NOT NULL,
    amount_paid        int          NOT NULL,
    outstanding_amount int          NOT NULL,
    create_dt          date DEFAULT NULL,
    PRIMARY KEY (loan_number),
    CONSTRAINT loan_customer_ibfk_1 FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);

CREATE INDEX ON loans(customer_id);

INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount,
                     create_dt)
VALUES ('03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13');

INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount,
                     create_dt)
VALUES ('03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06');

INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount,
                     create_dt)
VALUES ('03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14');

INSERT INTO loans (customer_id, start_dt, loan_type, total_loan, amount_paid, outstanding_amount,
                     create_dt)
VALUES ('03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');

CREATE TABLE cards
(
    card_id          serial          NOT NULL,
    card_number      varchar(100) NOT NULL,
    customer_id      uuid          NOT NULL,
    card_type        varchar(100) NOT NULL,
    total_limit      int          NOT NULL,
    amount_used      int          NOT NULL,
    available_amount int          NOT NULL,
    create_dt        date DEFAULT NULL,
    PRIMARY KEY (card_id),
    CONSTRAINT card_customer_ibfk_1 FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);
CREATE INDEX on cards (customer_id);


INSERT INTO cards (card_number, customer_id, card_type, total_limit, amount_used, available_amount,
                     create_dt)
VALUES ('4565XXXX4656', '03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', 'Credit', 10000, 500, 9500, current_date);

INSERT INTO cards (card_number, customer_id, card_type, total_limit, amount_used, available_amount,
                     create_dt)
VALUES ('3455XXXX8673', '03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', 'Credit', 7500, 600, 6900, current_date);

INSERT INTO cards (card_number, customer_id, card_type, total_limit, amount_used, available_amount,
                     create_dt)
VALUES ('2359XXXX9346', '03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', 'Credit', 20000, 4000, 16000, current_date);

CREATE TABLE notice_details
(
    notice_id      serial          NOT NULL,
    notice_summary varchar(200) NOT NULL,
    notice_details varchar(500) NOT NULL,
    start_date   date         NOT NULL,
    end_date   date DEFAULT NULL,
    create_dt      date DEFAULT NULL,
    update_dt      date DEFAULT NULL,
    PRIMARY KEY (notice_id)
);

INSERT INTO notice_details (notice_summary, notice_details, start_date, end_date, create_dt,
                              update_dt) VALUES ('Home Loan Interest rates reduced',
        'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately',
        current_date - interval '30 DAY', current_date + interval '30 DAY', current_date, null);

INSERT INTO notice_details (notice_summary, notice_details, start_date, end_date, create_dt,
                              update_dt) VALUES ('Net Banking Offers',
        'Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher',
        current_date - interval '30 DAY', current_date + interval '30 DAY', current_date, null);

INSERT INTO notice_details (notice_summary, notice_details, start_date, end_date, create_dt,
                              update_dt) VALUES ('Mobile App Downtime',
        'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities',
        current_date - interval '30 DAY', current_date + interval '30 DAY', current_date, null);

INSERT INTO notice_details (notice_summary, notice_details, start_date, end_date, create_dt,
                              update_dt) VALUES ('E Auction notice',
        'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction',
        current_date - interval '30 DAY', current_date + interval '30 DAY', current_date, null);

INSERT INTO notice_details (notice_summary, notice_details, start_date, end_date, create_dt,
                              update_dt) VALUES ('Launch of Millennia Cards',
        'Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase',
        current_date - interval '30 DAY', current_date + interval '30 DAY', current_date, null);

INSERT INTO notice_details (notice_summary, notice_details, start_date, end_date, create_dt,
                              update_dt) VALUES ('COVID-19 Insurance',
        'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details',
        current_date - interval '30 DAY', current_date + interval '30 DAY', current_date, null);

CREATE TABLE contact_messages
(
    contact_id    varchar(50)   NOT NULL,
    contact_name  varchar(50)   NOT NULL,
    contact_email varchar(100)  NOT NULL,
    subject       varchar(500)  NOT NULL,
    message       varchar(2000) NOT NULL,
    create_dt     date DEFAULT NULL,
    PRIMARY KEY (contact_id)
);

CREATE TABLE authorities
(
    id          serial         NOT NULL,
    customer_id uuid         NOT NULL,
    name        varchar(50) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT authorities_ibfk_1 FOREIGN KEY (customer_id) REFERENCES customer (id)
);

CREATE INDEX ON authorities (customer_id);

INSERT INTO authorities (customer_id, name)
VALUES ('03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', 'VIEWACCOUNT');

INSERT INTO authorities (customer_id, name)
VALUES ('03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', 'VIEWCARDS');

INSERT INTO authorities (customer_id, name)
VALUES ('03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', 'VIEWLOANS');

INSERT INTO authorities (customer_id, name)
VALUES ('03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5', 'VIEWBALANCE');
--
-- DELETE
-- FROM authorities;
--
-- INSERT INTO authorities (customer_id, name)
-- VALUES (1, 'ROLE_USER');
--
-- INSERT INTO authorities (customer_id, name)
-- VALUES (1, 'ROLE_ADMIN');


-- create table users(
--                       username varchar(50) not null primary key,
--                       password text not null,
--                       enabled boolean not null
-- );
--
-- create table authorities (
--                              username varchar(50) not null,
--                              authority varchar(50) not null,
--                              constraint fk_authorities_users foreign key(username) references users(username)
-- );
-- create unique index ix_auth_username on authorities (username,authority);
--
-- create table customer(
--     id uuid primary key ,
--     email varchar(50) not null,
--     pwd text not null,
--     role varchar(50) not null
-- )

-- insert into customer values ('03f0f5e4-283e-4b58-a6a8-32a0f5a6c6e5','test@test.com','12345','admin');
