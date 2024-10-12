create table muscles(
    id bigint primary key,
    name varchar(255)
);

alter table muscles owner to root;
create sequence muscle_id_seq;
alter sequence muscle_id_seq owner to root;