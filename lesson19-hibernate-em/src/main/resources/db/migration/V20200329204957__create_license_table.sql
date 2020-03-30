create table license (
    id serial primary key,
    name varchar(100) not null,
    user_id int not null
)