create table if not exists orders (
    order_id serial not null,
    total_price numeric,
    assigned_to int8,
    order_payed boolean,
    status varchar,
    store_id int8,
    customer_id int8,

    created_at timestamp not null,
    deleted_at timestamp,
    updated_at timestamp,

    primary key (order_id)
);

create table if not exists purchase (
    purchase_id serial not null,
    price numeric,
    product_id int8,
    order_id int8,

    created_at timestamp not null,
    deleted_at timestamp,
    updated_at timestamp,

    primary key (purchase_id)
);
