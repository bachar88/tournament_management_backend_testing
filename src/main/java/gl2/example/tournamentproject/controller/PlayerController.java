package gl2.example.tournamentproject.controller;

import gl2.example.tournamentproject.dto.PlayerDTO;
import gl2.example.tournamentproject.dto.PlayerRequest;
import gl2.example.tournamentproject.model.Player;
import gl2.example.tournamentproject.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers()
                .stream()
                .map(PlayerDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Long id) {
        Optional<Player> player = playerService.getPlayerById(id);
        return player.map(p -> ResponseEntity.ok(new PlayerDTO(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PlayerDTO addPlayer(@RequestBody PlayerRequest request) {
        Player player = playerService.createPlayer(request);
        return new PlayerDTO(player);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> existing = playerService.getPlayerById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();
        player.setId(id);
        Player updated = playerService.addPlayer(player);
        return ResponseEntity.ok(new PlayerDTO(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlayerDTO> patchPlayer(@PathVariable Long id,
                                                 @RequestBody Map<String, Object> updates) {
        return playerService.patchPlayer(id, updates)
                .map(p -> ResponseEntity.ok(new PlayerDTO(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}