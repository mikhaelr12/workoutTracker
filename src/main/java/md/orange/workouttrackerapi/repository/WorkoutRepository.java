package md.orange.workouttrackerapi.repository;

import md.orange.workouttrackerapi.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> getAllWorkoutsByUserId(Long id);
}
