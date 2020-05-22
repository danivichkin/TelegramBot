create sequence hibernate_sequence start 1 increment 1;

create table usr(
    id int8 PRIMARY KEY,
    userFirstName varchar,
    userLastName varchar,
    username varchar,
    lastCallBackQuery varchar
    );


create table notes(
    id int8 primary key ,
    title varchar,
    description varchar,
    date timestamptz,
    user_id int8
);

alter table if exists notes
    add constraint notes_user_fk
        foreign key (user_id) references usr(id);
