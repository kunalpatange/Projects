INSERT INTO account_details (account_id, balance)
VALUES (1, 1000);
INSERT INTO account_details (account_id, balance)
VALUES (2, 200);
INSERT INTO account_details (account_id, balance)
VALUES (3, 500);
INSERT INTO account_details (account_id, balance)
VALUES (4, 100);

INSERT INTO address_city (street_address, city)
VALUES ('1022 Frisco', 'Dallas');
INSERT INTO address_city (street_address, city)
VALUES ('1111 Yale University Commons', 'New Haven');
INSERT INTO address_city (street_address, city)
VALUES ('2500 Kensington Park', 'New York');
INSERT INTO address_city (street_address, city)
VALUES ('2516 Avent Ferry Road', 'Raleigh');

INSERT INTO distributor_details (distributor_id, contact_person, phone_number, type, name, acccount_Id)
VALUES (1, 'Chris Patt', '9843499451', 'wholesale', 'Ingram Content Group', 1);
INSERT INTO distributor_details (distributor_id, contact_person, phone_number, type, name, acccount_Id)
VALUES (3, 'Noah Williamson', '7443499451', 'bookstore', 'Baker & Tailor Group', 2);
INSERT INTO distributor_details (distributor_id, contact_person, phone_number, type, name, acccount_Id)
VALUES (4, 'Tom Hagen', '7422254421', 'library', 'American West Books', 3);
INSERT INTO distributor_details (distributor_id, contact_person, phone_number, type, name, acccount_Id)
VALUES (5, 'Vignesh Iyyer', '9845107822', 'bookstore', 'Freedom Books', 4);

INSERT INTO distributor_address_details (distributor_id, street_address)
VALUES (5, '1022 Frisco');
INSERT INTO distributor_address_details (distributor_id, street_address)
VALUES (3, '1111 Yale University Commons');
INSERT INTO distributor_address_details (distributor_id, street_address)
VALUES (4, '2500 Kensington Park');
INSERT INTO distributor_address_details (distributor_id, street_address)
VALUES (1, '2516 Avent Ferry Road');

INSERT INTO writer_details (writer_id, is_invited, name, type)
VALUES (1, 0, 'J. K. Rowling', 'Author');
INSERT INTO writer_details (writer_id, is_invited, name, type)
VALUES (2, 0, 'Robert Love', 'Journalist');
INSERT INTO writer_details (writer_id, is_invited, name, type)
VALUES (3, 0, 'James Clear', 'Author');
INSERT INTO writer_details (writer_id, is_invited, name, type)
VALUES (4, 0, 'Angelo Ayala', 'Journalist');
INSERT INTO writer_details (writer_id, is_invited, name, type)
VALUES (5, 0, 'Collen Hover', 'Editor');

INSERT INTO publication_details (publication_id, title, topic, publication_type, number_of_copies)
VALUES (1, 'AARP The Magazine', 'General', 'Magazine', 1000);
INSERT INTO publication_details (publication_id, title, topic, publication_type, number_of_copies)
VALUES (2, 'Harry Potter', 'Fictional', 'Book', 4000);
INSERT INTO publication_details (publication_id, title, topic, publication_type, number_of_copies)
VALUES (3, 'Atomic Habits', 'Motivational', 'Book', 5000);
INSERT INTO publication_details (publication_id, title, topic, publication_type, number_of_copies)
VALUES (4, 'Game Informer', 'Fictional', 'Magazine', 10000);

INSERT INTO edition_details (isbn, edition_number, date, publication_id)
VALUES (1, 1, '2022-03-07', 3);
INSERT INTO edition_details (isbn, edition_number, date, publication_id)
VALUES (2, 1, '2022-03-07', 2);

INSERT INTO chapter_details (chapter_number, isbn, title, text, date, writer_id)
VALUES (1, 1, 'The boy who lived',
        'Mr, and Mrs. Dursley, of number four, Privet Drive, were proud to say that they were perfectly normal, thank you very much. They were the last people you’d expect to be involved in anything strange or mysterious, because they just didn’t hold with such nonsense. Mr. Dursley was the director of a firm called Grunnings, which made drills. He was a big, beefy man with hardly any neck, although he did have a very large mustache. Mrs. Dursley was thin and blonde and had nearly twice the usual amount of neck, which came in very useful as she spent so much of her time craning over garden fences, spying on the neighbors. The Dursleys had a small son called Dudley and in their opinion there was no finer boy anywhere. The Dursleys had everything they wanted, but they also had a secret, and their greatest fear was that somebody would discover it.',
        '2022-03-07', 1);
INSERT INTO chapter_details (chapter_number, isbn, title, text, date, writer_id)
VALUES (2, 2, 'The suprising power of tiny habits',
        '“Success is the product of daily habits—not once-in-a-lifetime transformations.” “You should be far more concerned with your current trajectory than with your current results.” “Your outcomes are a lagging measure of your habits. Your net worth is a lagging measure of your financial habits. Your weight is a lagging measure of your eating habits. Your knowledge is a lagging measure of your learning habits. Your clutter is a lagging measure of your cleaning habits. You get what you repeat.”',
        '2022-03-07', 3);

INSERT INTO magazine_details (publication_id, periodicity)
VALUES (1, 6);
INSERT INTO magazine_details (publication_id, periodicity)
VALUES (4, 1);

INSERT INTO issue_details (issue_id, date, publication_id)
VALUES (1, '2022-03-07', 1);
INSERT INTO issue_details (issue_id, date, publication_id)
VALUES (2, '2022-03-07', 4);

INSERT INTO article_details (article_number, issue_id, title, text, date, writer_id)
VALUES (1, 1, 'Ten unspoken truths about taxes',
        'It’s right there on most every store receipt, pay stub and monthly mortgage statement: a notice of how much of your money went toward taxes. America’s complex, multitiered tax system means that handing over money to the government is perpetually in your face and on your mind, and never more so than as Tax Day approaches each spring.But do taxes have an outsize grip on our imaginations? After all, can you really do much about them? (Spoiler: In most cases, no — as Benjamin Franklin hinted 200-plus years ago.) “I think people either obsess about taxes or they ignore them completely,” says Sallie Mullins Thompson, a CPA who works in the New York City area.',
        '2022-03-07', 2);
INSERT INTO article_details (article_number, issue_id, title, text, date, writer_id)
VALUES (2, 2, 'Lost Judgment: The Kaito Files DLC Releasing Later This Month',
        'More specifically, if you’re excited for more story content in the world of Judgment and its sequel, Lost Judgment, The Kaito Files will hit the game on March 28, meaning you don’t have to wait too much longer now. Described as a story expansion for Lost Judgment centered around Masaharu Kaito, who will actually be the playable character for the DLC, The Kaito Files will hit PlayStation 5, Xbox Series X/S, PlayStation 4, and Xbox One.',
        '2022-03-07', 4);

INSERT INTO order_details (order_id, price, number_of_copies, version, shipping_cost, due_date, ordered_date,
                           distributor_id, publication_id)
VALUES (1, 10, 1000, 1, 10, null, '2022-03-07', 3, 2);
INSERT INTO order_details (order_id, price, number_of_copies, version, shipping_cost, due_date, ordered_date,
                           distributor_id, publication_id)
VALUES (2, 50, 50, 2, 10, null, '2022-03-07', 4, 4);
INSERT INTO order_details (order_id, price, number_of_copies, version, shipping_cost, due_date, ordered_date,
                           distributor_id, publication_id)
VALUES (3, 10, 750, 1, 5, '2022-03-31', '2022-03-07', 5, 1);
INSERT INTO order_details (order_id, price, number_of_copies, version, shipping_cost, due_date, ordered_date,
                           distributor_id, publication_id)
VALUES (4, 20, 100, 2, 5, '2023-03-01', '2022-03-07', 1, 3);

INSERT INTO payment_details (payment_id, payment_date, amount, writer_id)
VALUES (1, '2022-03-01', 1000, 1);
INSERT INTO payment_details (payment_id, payment_date, amount, writer_id)
VALUES (2, '2022-03-01', 1000, 2);
INSERT INTO payment_details (payment_id, payment_date, amount, writer_id)
VALUES (3, '2022-03-01', 1000, 3);
INSERT INTO payment_details (payment_id, payment_date, amount, writer_id)
VALUES (4, '2022-03-01', 1000, 4);
