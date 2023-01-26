create table if not exists customer (
    customer_id serial not null,
    firstname varchar not null ,
    lastname varchar not null ,
    email varchar not null unique,
    phone_number varchar not null,
    created_at timestamp not null,
    deleted_at timestamp,
    updated_at timestamp,
    store_id int8 not null,
    primary key (customer_id)
);