package gl2.example.tournamentproject.service;

import gl2.example.tournamentproject.model.Player;
import gl2.example.tournamentproject.model.Team;
import gl2.example.tournamentproject.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gl2.example.tournamentproject.dto.PlayerRequest;
import gl2.example.tournamentproject.repository.TeamRepository;
import gl2.example.tournamentproject.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;  // Attention à ne pas confondre avec TeamService

    public Player createPlayer(PlayerRequest request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team not found"));
        Player player = new Player(request.getName(), request.getAge(), team);
        player = playerRepository.save(player);
        // Force l'initialisation de l'équipe
        player.getTeam().getName();
        return player;
    }
    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerById(Long id) {
        return playerRepository.findById(id);
    }

    public List<Player> getPlayersByTeam(Long teamId) {
        return playerRepository.findByTeamId(teamId);
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }

    public Optional<Player> patchPlayer(Long id, Map<String, Object> updates) {
        Optional<Player> existing = playerRepository.findById(id);
        if (existing.isEmpty()) return Optional.empty();

        Player p = existing.get();
        if (updates.containsKey("name")) {
            p.setName((String) updates.get("name"));
        }
        if (updates.containsKey("age")) {
            p.setAge((Integer) updates.get("age"));
        }
        return Optional.of(playerRepository.save(p));
    }
}