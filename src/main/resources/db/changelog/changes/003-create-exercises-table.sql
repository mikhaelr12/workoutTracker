create table exercises(
    id bigint not null primary key,
    name varchar(255),
    repetitions int,
    sets int,
    weights int
);

alter table exercises owner to root;
create sequence exercise_id_seq;
alter sequence exercise_id_seq owner to root;