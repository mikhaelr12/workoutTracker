create table workouts(
    id bigint primary key not null,
    name varchar(255),
    workout_time timestamp
);

alter table workouts owner to root;
create sequence workout_id_seq;
alter sequence workout_id_seq owner to root;