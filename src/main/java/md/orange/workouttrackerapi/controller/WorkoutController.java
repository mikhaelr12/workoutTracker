package md.orange.workouttrackerapi.controller;

import lombok.AllArgsConstructor;
import md.orange.workouttrackerapi.config.JwtService;
import md.orange.workouttrackerapi.dto.WorkoutDTO;
import md.orange.workouttrackerapi.service.WorkoutService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/workout")
@RestController
@AllArgsConstructor
public class WorkoutController {

    private JwtService jwtService;
    private WorkoutService workoutService;

    public String getUser(String token) {
        return token.startsWith("Bearer ") ? token.substring(7) : token;
    }

    @GetMapping()
    public ResponseEntity<List<WorkoutDTO>> getAllWorkouts(@RequestHeader("Authorization") String token) {
        String jwtToken = getUser(token);
        return ResponseEntity.ok(workoutService.getAllWorkouts(jwtToken));
    }

    @PostMapping()
    public ResponseEntity<?> createWorkout(@RequestBody WorkoutDTO workoutDTO,
                                           @RequestHeader("Authorization") String token) {
        String jwtToken = getUser(token);
        workoutService.createWorkout(workoutDTO, jwtToken);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<?> deleteWorkout(@RequestHeader("Authorization") String token, @PathVariable Long workoutId){
        String jwtToken = getUser(token);
        workoutService.deleteWorkout(jwtToken, workoutId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{workoutId}")
    public ResponseEntity<?> updateWorkout(@RequestHeader("Authorization") String token,
                                           @PathVariable Long workoutId, WorkoutDTO workoutDTO)
    {
        String jwtToken = getUser(token);
        workoutService.updateWorkout(jwtToken,workoutId,workoutDTO);
        return ResponseEntity.ok().build();
    }
}
