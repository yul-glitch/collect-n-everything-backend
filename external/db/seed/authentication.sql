create table if not exists users (
    user_id serial not null,
    email varchar(255) not null unique,
    user_password varchar(255) not null,
    user_role varchar(255) not null,
    store_id int8 not null,
    customer_id int8,

    created_at timestamp not null,
    deleted_at timestamp,
    updated_at timestamp,

    primary key (user_id)
);