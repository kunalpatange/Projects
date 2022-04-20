create table account_details
(
    account_id int auto_increment primary key,
    balance    int not null
);

create table address_city
(
    street_address varchar(150) not null primary key,
    city           varchar(50)  null
);

create table distributor_details
(
    distributor_id int auto_increment primary key,
    contact_person varchar(50) not null,
    phone_number   varchar(50) not null,
    type           varchar(50) not null,
    name           varchar(50) not null,
    acccount_Id    int         not null,
    constraint distributor_details_unique
        unique (acccount_Id),
    constraint distributor_details_account_details_account_id_fk
        foreign key (acccount_Id) references account_details (account_id)
            on update cascade on delete cascade
);

create table distributor_address_details
(
    distributor_id int          not null primary key,
    street_address varchar(150) not null,
    constraint distributor_addr_details_distributor_details_distributor_id_fk
        foreign key (distributor_id) references distributor_details (distributor_id)
            on update cascade on delete cascade,
    constraint distributor_address_details_address_city_street_address_fk
        foreign key (street_address) references address_city (street_address)
            on update cascade on delete cascade
);

create table publication_details
(
    publication_id   int auto_increment primary key,
    title            varchar(150) not null,
    topic            varchar(150) null,
    publication_type varchar(50)  not null,
    number_of_copies int          not null
);

create table edition_details
(
    isbn           int auto_increment primary key,
    edition_number int  not null,
    date           date not null,
    publication_id int  not null,
    constraint edition_details_publication_details_publication_id_fk
        foreign key (publication_id) references publication_details (publication_id)
            on update cascade on delete cascade
);

create table issue_details
(
    issue_id       int auto_increment primary key,
    date           date not null,
    publication_id int  not null,
    constraint issue_details_publication_details_publication_id_fk
        foreign key (publication_id) references publication_details (publication_id)
            on update cascade on delete cascade
);

create table magazine_details
(
    publication_id int not null primary key,
    periodicity    int not null,
    constraint magazine_details_publication_details_publication_id_fk
        foreign key (publication_id) references publication_details (publication_id)
            on update cascade on delete cascade
);

create table order_details
(
    order_id         int auto_increment primary key,
    price            int  not null,
    number_of_copies int  not null,
    version          int  not null,
    shipping_cost    int  not null,
    due_date         date null,
    ordered_date     date not null,
    distributor_id   int  not null,
    publication_id   int  not null,
    constraint order_details_distributor_details_distributor_id_fk
        foreign key (distributor_id) references distributor_details (distributor_id),
    constraint order_details_publication_details_publication_id_fk
        foreign key (publication_id) references publication_details (publication_id)
);

create table writer_details
(
    writer_id  int auto_increment primary key,
    is_invited tinyint(1)  not null,
    name       varchar(50) not null,
    type       varchar(50) not null
);

create table article_details
(
    article_number int           not null,
    issue_id       int           not null,
    title          varchar(150)  not null,
    text           varchar(1000) null,
    date           date          not null,
    writer_id      int           not null,
    primary key (article_number, issue_id),
    constraint article_details_issue_details_issue_id_fk
        foreign key (issue_id) references issue_details (issue_id)
            on update cascade on delete cascade,
    constraint article_details_writer_details_writer_id_fk
        foreign key (writer_id) references writer_details (writer_id)
            on update cascade on delete cascade
);

create table chapter_details
(
    chapter_number int           not null,
    isbn           int           not null,
    title          varchar(150)  not null,
    text           varchar(1000) null,
    date           date          not null,
    writer_id      int           not null,
    primary key (chapter_number, isbn),
    constraint chapter_details_edition_details_isbn_fk
        foreign key (isbn) references edition_details (isbn)
            on update cascade on delete cascade,
    constraint chapter_details_writer_details_writer_id_fk
        foreign key (writer_id) references writer_details (writer_id)
            on update cascade on delete cascade
);

create table payment_details
(
    payment_id   int auto_increment primary key,
    payment_date date not null,
    amount       int  not null,
    writer_id    int  not null,
    constraint payment_details_writer_details_writer_id_fk
        foreign key (writer_id) references writer_details (writer_id)
            on update cascade on delete cascade
);

