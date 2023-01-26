create table if not exists product (
                             product_id serial not null,

                             name varchar(255) not null unique,
                             price numeric not null unique,
                             description varchar(255) not null,
                             weight numeric,
                             height numeric,
                             photo varchar(255),
                             available_in_store boolean not null,

                             created_at timestamp not null,
                             deleted_at timestamp,
                             updated_at timestamp,

                             store_id int8,
                             category_id int8,

                             primary key (product_id)
);

create table if not exists category (
                               category_id serial not null,

                               name varchar(255) not null,

                               created_at timestamp not null,
                               deleted_at timestamp,
                               updated_at timestamp,

                               store_id int8,
                               primary key (category_id)
);

alter table if exists product add constraint product_has_category foreign key (category_id) references category;

-- PK starts at 1
ALTER SEQUENCE public.category_category_id_seq RESTART WITH 1;
ALTER SEQUENCE public.product_product_id_seq RESTART WITH 1;

-- google product, store with id 1
insert into category values (1, 'Connectique', now(), null, null, 1);
insert into category values (2, 'Ecran', now(), null, null, 1);

insert into product values (1, 'Cable RJ45 4 mètres', 74, 'Cable de connexion à internet pour un port RJ45', null, null, 'path/to/photo', true, now(), null, null, 1, 1);
insert into product values (2, 'Cable HDMI', 15, 'Cable de connexion à un port HTML', null, null, 'path/to/photo', true, now(), null, null, 1, 1);
insert into product values (3, 'Ecran lenovo', 455.32, 'Ecran lenovo 32 pouces', 15.3, 81.23, 'path/to/photo', true, now(), null, null, 1, 2);
insert into product values (4, 'Télévision extra large', 1500, 'Télévision super grande et trop bien', 20, 3, 'path/to/photo', false, now(), null, null, 1, 2);
