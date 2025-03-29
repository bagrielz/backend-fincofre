create table spents (

    id bigint not null auto_increment,
    spent varchar(100) not null,
    status varchar(100) not null,
    spent_value varchar(20) not null,
    category varchar(100) not null,
    account varchar(100) not null,
    method varchar(100) not null,

    primary key(id)

);