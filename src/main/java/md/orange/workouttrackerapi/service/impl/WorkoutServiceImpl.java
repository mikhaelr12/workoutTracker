package md.orange.workouttrackerapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.orange.workouttrackerapi.config.JwtService;
import md.orange.workouttrackerapi.dto.WorkoutDTO;
import md.orange.workouttrackerapi.entity.Exercise;
import md.orange.workouttrackerapi.entity.User;
import md.orange.workouttrackerapi.entity.Workout;
import md.orange.workouttrackerapi.enums.Status;
import md.orange.workouttrackerapi.exception.UserException;
import md.orange.workouttrackerapi.exception.WorkoutException;
import md.orange.workouttrackerapi.repository.ExerciseRepository;
import md.orange.workouttrackerapi.repository.UserRepository;
import md.orange.workouttrackerapi.repository.WorkoutRepository;
import md.orange.workouttrackerapi.service.WorkoutService;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class WorkoutServiceImpl implements WorkoutService {

    private UserRepository userRepository;

    private JwtService jwtService;

    private WorkoutRepository workoutRepository;

    private ExerciseRepository exerciseRepository;

    private User getUser(String jwtToken){
        String username = jwtService.extractUsername(jwtToken);
        return userRepository.findByUsername(username).orElse(null);
    }

    private Set<Exercise> getExercises(WorkoutDTO workoutDTO){
        Set<Exercise> exercises = new HashSet<>();
        for (Long exerciseId : workoutDTO.getExercisesId()) {
            Exercise exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new WorkoutException("Exercise not found with ID: " + exerciseId));
            exercises.add(exercise);
        }
        return exercises;
    }

    @Override
    public List<WorkoutDTO> getAllWorkouts(String token) {
        User user = getUser(token);
        if(user == null){
            throw new UserException("No user found");
        }
        List<Workout> workouts = workoutRepository.getAllWorkoutsByUserId(user.getId());
        if(workouts.isEmpty()){
            throw new WorkoutException("No workouts found");
        }
        return workouts.stream().map(workout -> WorkoutDTO.builder()
                .id(workout.getId())
                .name(workout.getName())
                .workoutTime(workout.getWorkoutTime())
                .exercisesId(workout.getExercises().stream()
                        .map(Exercise::getId)
                        .collect(Collectors.toSet()))
                .status(workout.getStatus())
                .build()
        ).collect(Collectors.toList());
    }

    @Transactional
    public void createWorkout(WorkoutDTO workoutDTO, String token) {
        User user = getUser(token);
        if(user == null){
            throw new UserException("No user found");
        }
        Set<Exercise> exercises = getExercises(workoutDTO);
    workoutRepository.save(
            Workout.builder()
            .name(workoutDTO.getName())
            .status(Status.valueOf(workoutDTO.getStatus().toString()))
            .workoutTime(workoutDTO.getWorkoutTime())
            .userId(user.getId())
            .exercises(exercises)
            .build());
    }

    @Override
    public void deleteWorkout(String token, Long workoutId) {
        User user = getUser(token);
        if(user == null){
            throw new UserException("No user found");
        }
        Optional<Workout> workout = workoutRepository.findById(workoutId);
        if (workout.isEmpty()) {
            throw new WorkoutException("Workout not found with ID: " + workoutId);
        }
        workoutRepository.delete(workout.get());
    }

    @Override
    public void updateWorkout(String jwtToken, Long workoutId, WorkoutDTO workoutDTO) {
        User user = getUser(jwtToken);
        if (user == null){
            throw new UserException("No user found");
        }
        Optional<Workout> workout = workoutRepository.findById(workoutId);
        if (workout.isEmpty()) {
            throw new WorkoutException("Workout not found with ID: " + workoutId);
        }
        Set<Exercise> exercises = getExercises(workoutDTO);
        workoutRepository.save(
                Workout.builder()
                        .name(workoutDTO.getName())
                        .status(Status.valueOf(workoutDTO.getStatus().toString()))
                        .workoutTime(workoutDTO.getWorkoutTime())
                        .userId(user.getId())
                        .exercises(exercises)
                        .build());
    }
}
