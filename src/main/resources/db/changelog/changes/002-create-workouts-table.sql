create table workouts(
    id bigint primary key not null,
    name varchar(255),
    workout_time timestamp,
    user_id bigint,
    status varchar(255),
    constraint FK_USER_WORKOUTS foreign key  (user_id) references users (id)
);

alter table workouts owner to root;
create sequence workout_id_seq;
alter sequence workout_id_seq owner to root;