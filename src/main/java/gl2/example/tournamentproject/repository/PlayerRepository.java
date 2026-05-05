package gl2.example.tournamentproject.repository;

import gl2.example.tournamentproject.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    // Trouve tous les joueurs d'une équipe donnée
    List<Player> findByTeamId(Long teamId);

    // Trouve les joueurs par nom
    List<Player> findByName(String name);
}