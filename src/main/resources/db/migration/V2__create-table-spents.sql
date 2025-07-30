create table spents (

    id bigint not null auto_increment,
    user_id bigint not null,
    spent varchar(100) not null,
    status varchar(20) not null,
    spent_value decimal(19, 4) not null,
    category varchar(20) not null,
    account varchar(20) not null,
    spent_type varchar(20) not null,
    method varchar(20) not null,
    spent_date date not null,

    primary key(id),
    constraint fk_spents_user_id foreign key(user_id) references users(id) on delete cascade

);