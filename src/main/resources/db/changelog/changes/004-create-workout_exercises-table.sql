create table workout_exercises (
            workout_id bigint not null ,
            exercise_id bigint not null ,
            primary key (workout_id, exercise_id),
            foreign key (workout_id) references workouts(id) on delete cascade ,
            foreign key (exercise_id) references exercises(id) on delete cascade
);