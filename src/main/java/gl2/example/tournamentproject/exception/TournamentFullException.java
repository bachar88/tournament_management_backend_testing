package gl2.example.tournamentproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TournamentFullException extends RuntimeException {
    public TournamentFullException(String message) {
        super(message);
    }
}