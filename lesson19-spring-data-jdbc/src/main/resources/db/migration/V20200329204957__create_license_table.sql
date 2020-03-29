create table license (
    id int identity primary key,
    name varchar(100) not null,
    user_id int not null
)