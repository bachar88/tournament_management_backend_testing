package gl2.example.tournamentproject.repository;

import gl2.example.tournamentproject.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    // Trouve toutes les équipes d'un tournoi donné
    List<Team> findByTournamentId(Long tournamentId);

    // Trouve une équipe par son nom
    List<Team> findByName(String name);
}