package md.orange.workouttrackerapi.dto;

import lombok.*;
import md.orange.workouttrackerapi.entity.Exercise;
import md.orange.workouttrackerapi.enums.Status;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkoutDTO {
    private Long id;
    private String name;
    private LocalDateTime workoutTime;
    private Set<Long> exercisesId;
    private Status status;
    private Long userId;
}
