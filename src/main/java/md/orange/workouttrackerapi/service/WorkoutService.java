package md.orange.workouttrackerapi.service;

import md.orange.workouttrackerapi.dto.WorkoutDTO;

import java.util.List;

public interface WorkoutService {
    List<WorkoutDTO> getAllWorkouts(String token);

    void createWorkout(WorkoutDTO workoutDTO, String token);

    void deleteWorkout(String token, Long workoutId);

    void updateWorkout(String jwtToken, Long workoutId, WorkoutDTO workoutDTO);
}
