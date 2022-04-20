INSERT INTO tpalla.account_details (account_id, balance)
VALUES (1, 215);
INSERT INTO tpalla.account_details (account_id, balance)
VALUES (2, 0);

INSERT INTO tpalla.address_city (street_address, city)
VALUES ('2200, A Street, NC ', 'charlotte');
INSERT INTO tpalla.address_city (street_address, city)
VALUES ('2200, B Street, NC', 'Raleigh');

INSERT INTO tpalla.distributor_details (distributor_id, contact_person, phone_number, type, name, acccount_Id)
VALUES (2001, 'Jason', '9191234567', 'bookstore', 'BookSell', 1);
INSERT INTO tpalla.distributor_details (distributor_id, contact_person, phone_number, type, name, acccount_Id)
VALUES (2002, 'Alex', '9291234568', 'wholesaler', 'BookDist', 2);

INSERT INTO tpalla.distributor_address_details (distributor_id, street_address)
VALUES (2001, '2200, A Street, NC');
INSERT INTO tpalla.distributor_address_details (distributor_id, street_address)
VALUES (2002, '2200, B Street, NC');


INSERT INTO tpalla.publication_details (publication_id, title, topic, publication_type, number_of_copies)
VALUES (1001, 'introduction to database', 'technology', 'book', 200);
INSERT INTO tpalla.publication_details (publication_id, title, topic, publication_type, number_of_copies)
VALUES (1002, 'Healthy Diet', 'health', 'magazine', 150);
INSERT INTO tpalla.publication_details (publication_id, title, topic, publication_type, number_of_copies)
VALUES (1003, 'Animal Science', 'science', 'magazine', 300);

INSERT INTO tpalla.edition_details (isbn, edition_number, date, publication_id)
VALUES (12345, 2, '2018-10-10', 1001);

INSERT INTO tpalla.issue_details (issue_id, date, publication_id)
VALUES (1, '2020-02-24', 1002);
INSERT INTO tpalla.issue_details (issue_id, date, publication_id)
VALUES (2, '2020-03-01', 1003);

INSERT INTO tpalla.magazine_details (publication_id, periodicity)
VALUES (1002, 7);
INSERT INTO tpalla.magazine_details (publication_id, periodicity)
VALUES (1003, 30);

INSERT INTO tpalla.order_details (order_id, price, number_of_copies, version, shipping_cost, due_date, ordered_date,
                                  distributor_id, publication_id)
VALUES (4001, 20, 30, 12345, 30, '2020-01-15', '2020-01-02', 2001, 1001);
INSERT INTO tpalla.order_details (order_id, price, number_of_copies, version, shipping_cost, due_date, ordered_date,
                                  distributor_id, publication_id)
VALUES (4002, 20, 10, 12345, 15, '2020-02-15', '2020-02-05', 2001, 1001);
INSERT INTO tpalla.order_details (order_id, price, number_of_copies, version, shipping_cost, due_date, ordered_date,
                                  distributor_id, publication_id)
VALUES (4003, 10, 10, 2, 15, '2020-02-25', '2020-02-10', 2002, 1003);

INSERT INTO tpalla.writer_details (writer_id, is_invited, name, type)
VALUES (3002, 0, 'Ethen', 'editor');
INSERT INTO tpalla.writer_details (writer_id, is_invited, name, type)
VALUES (3004, 0, '/', 'editor');
INSERT INTO tpalla.writer_details (writer_id, is_invited, name, type)
VALUES (3001, 0, 'John', 'editor');
INSERT INTO tpalla.writer_details (writer_id, is_invited, name, type)
VALUES (3003, 1, 'Cathy', 'author');

INSERT INTO tpalla.article_details (article_number, issue_id, title, text, date, writer_id)
VALUES (1, 1, 'Healthy Diet', 'ABC', '2020-02-24', 3002);
INSERT INTO tpalla.article_details (article_number, issue_id, title, text, date, writer_id)
VALUES (1, 2, 'Animal Science', 'AAA', '2020-03-01', 3004);

INSERT INTO tpalla.payment_details (payment_id, payment_date, amount, writer_id)
VALUES (1, '2020-04-01', 1000, 3002);
INSERT INTO tpalla.payment_details (payment_id, payment_date, amount, writer_id)
VALUES (2, '2020-04-01', 1000, 3001);
INSERT INTO tpalla.payment_details (payment_id, payment_date, amount, writer_id)
VALUES (3, '2020-04-01', 1200, 3003);

