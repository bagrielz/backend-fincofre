create table users (

    id bigint not null auto_increment,
    login varchar(255) not null,
    name varchar(100) not null,
    email varchar(255) not null,
    password varchar(255) not null,

    primary key(id)

);