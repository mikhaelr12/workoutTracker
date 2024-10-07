package md.orange.workouttrackerapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Table(name = "workouts")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workout_id_seq")
    @SequenceGenerator(name = "workout_id_seq", sequenceName = "workout_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "workout_time")
    private LocalDateTime workoutTime;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "workout_exercises",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private Set<Exercise> exercises;

}
