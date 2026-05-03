package gl2.example.tournamentproject.repository;

import gl2.example.tournamentproject.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

        // Trouve tous les tournois dont les inscriptions sont ouvertes
        List<Tournament> findByInscOpenTrue();

        // Trouve les tournois par leur format (ex: "championnat")
        List<Tournament> findByFormat(String format);

        // Trouve un tournoi par son nom exact
        Optional<Tournament> findByName(String name);

}
