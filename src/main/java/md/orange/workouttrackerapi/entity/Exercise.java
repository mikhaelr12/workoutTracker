package md.orange.workouttrackerapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name ="exercises")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_id_seq")
    @SequenceGenerator(name = "exercise_id_seq", sequenceName = "exercise_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "repetitions")
    private Integer repetitions;

    @Column(name = "sets")
    private Integer sets;

    @Column(name = "weights")
    private Integer weights;

    @Column(name = "muscle_id")
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_MUSCLE_EXERCISE"))
    private Long muscleId;
}
