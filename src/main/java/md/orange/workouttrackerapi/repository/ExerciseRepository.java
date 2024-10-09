package md.orange.workouttrackerapi.repository;

import md.orange.workouttrackerapi.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
