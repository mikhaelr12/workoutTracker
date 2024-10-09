insert into exercises (id, name, repetitions, sets, weights, muscle_id)
values (nextval('exercise_id_seq'), 'Push up', 20, 3, null, 1),
       (nextval('exercise_id_seq'), 'Bench press', 15, 4, 50, 1),
       (nextval('exercise_id_seq'), 'Squat', 20, 4, 20, 3),
       (nextval('exercise_id_seq'), 'Bicep curl', 15, 4, 20, 4),
       (nextval('exercise_id_seq'), 'Cable curl', 15, 4, 20, 4),
       (nextval('exercise_id_seq'), 'Deadlift', 4, 4, 50, 2 )
