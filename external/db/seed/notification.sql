create table if not exists notification (
     notification_id serial not null,
     customer_id int8 not null,
     message varchar(255) not null,
     phone_number varchar(255) not null,
    primary key (notification_id)
);
