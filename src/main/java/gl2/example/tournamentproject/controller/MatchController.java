package gl2.example.tournamentproject.controller;

import gl2.example.tournamentproject.dto.MatchDTO;
import gl2.example.tournamentproject.dto.MatchRequest;
import gl2.example.tournamentproject.model.Match;
import gl2.example.tournamentproject.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping
    public List<MatchDTO> getAllMatches() {
        return matchService.getAllMatches()
                .stream()
                .map(MatchDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDTO> getMatchById(@PathVariable Long id) {
        Optional<Match> match = matchService.getMatchById(id);
        return match.map(m -> ResponseEntity.ok(new MatchDTO(m)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public MatchDTO addMatch(@RequestBody MatchRequest request) {
        Match match = matchService.createMatch(request);
        return new MatchDTO(match);
    }

    @PatchMapping("/{id}/score")
    public ResponseEntity<MatchDTO> updateScore(@PathVariable Long id,
                                                @RequestBody Map<String, Object> updates) {
        return matchService.updateScore(id, updates)
                .map(m -> ResponseEntity.ok(new MatchDTO(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<MatchDTO> updateStatus(@PathVariable Long id,
                                                 @RequestBody Map<String, Object> updates) {
        return matchService.updateStatus(id, updates)
                .map(m -> ResponseEntity.ok(new MatchDTO(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}