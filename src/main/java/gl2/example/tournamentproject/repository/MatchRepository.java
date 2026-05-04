package gl2.example.tournamentproject.repository;

import gl2.example.tournamentproject.model.Match;
import gl2.example.tournamentproject.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByIdTourn(int idTourn);
}
