package md.orange.workouttrackerapi.entity;

import jakarta.persistence.*;
import lombok.*;
import md.orange.workouttrackerapi.enums.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
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

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "workout_exercises",
            joinColumns = @JoinColumn(name = "workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private Set<Exercise> exercises;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "user_id")
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER_WORKOUT"))
    private Long userId;
}
