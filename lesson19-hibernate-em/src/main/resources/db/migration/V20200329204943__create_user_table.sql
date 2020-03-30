create table users (
    id serial primary key,
    username varchar(20) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null
)