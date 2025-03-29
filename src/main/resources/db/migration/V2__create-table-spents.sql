create table spents (

    id bigint not null auto_increment,
    user_id bigint not null,
    spent varchar(100) not null,
    status varchar(100) not null,
    spent_value varchar(20) not null,
    category varchar(100) not null,
    account varchar(100) not null,
    method varchar(100) not null,
    spent_date varchar(10) not null,

    primary key(id),
    constraint fk_spents_user_id foreign key(user_id) references users(id) on delete cascade

);