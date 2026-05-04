package gl2.example.tournamentproject.repository;

import gl2.example.tournamentproject.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

    // Trouve tous les matchs d'un tournoi donné
    List<Match> findByTournamentId(Long tournamentId);

    // Trouve les matchs par statut
    List<Match> findByStatus(Match.MatchStatus status);
}