create table if not exists payment (
    payment_id serial not null,
    state varchar not null,
    transactionPrice numeric not null,
    order_id int8,
    customer int8,

    created_at timestamp not null,
    deleted_at timestamp,
    updated_at timestamp,

    primary key (payment_id)
);