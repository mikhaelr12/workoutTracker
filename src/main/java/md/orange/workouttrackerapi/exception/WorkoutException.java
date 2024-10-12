package md.orange.workouttrackerapi.exception;

import java.io.Serial;

public class WorkoutException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public WorkoutException(String message) {
        super(message);
    }
    public WorkoutException() {super();}
}
