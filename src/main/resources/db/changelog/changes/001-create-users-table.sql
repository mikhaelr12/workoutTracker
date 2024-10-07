create table users(
    id bigint primary key not null,
    username varchar(255) not null,
    password varchar(255) not null
);

alter table users owner to root;
create sequence user_id_seq;
alter sequence user_id_seq owner to root;