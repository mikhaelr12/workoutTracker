package md.orange.workouttrackerapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "muscles")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Muscle {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "muscle_id_seq")
    @SequenceGenerator(name = "muscle_id_seq", sequenceName = "muscle_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
