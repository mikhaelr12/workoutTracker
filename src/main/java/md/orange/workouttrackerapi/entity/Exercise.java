package md.orange.workouttrackerapi.entity;

import jakarta.persistence.*;

@Table(name ="exercises")
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercise_id_seq")
    @SequenceGenerator(name = "exercise_id_seq", sequenceName = "exercise_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "repetitions")
    private Integer repetitions;

    @Column(name = "sets")
    private Integer sets;

    @Column(name = "weights")
    private Integer weights;


}
