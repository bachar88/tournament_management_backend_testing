package gl2.example.tournamentproject.controller;

import gl2.example.tournamentproject.model.Match;
import gl2.example.tournamentproject.model.Tournament;
import gl2.example.tournamentproject.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    @Autowired
    private MatchService matchService;

    @GetMapping
    public List<Match> getAllMatchs() {
        return matchService.getAllMatchs();
    }

    // 1. Mettre à jour le score
    @PatchMapping("/{id}/score")
    public Match updateMatchScore(
            @PathVariable Long id,
            @RequestParam int scoreDom,
            @RequestParam int scoreExt) {
        return matchService.updateScore(id, scoreDom, scoreExt);
    }
    // 2. Mettre à jour le statut
    @PatchMapping("/{id}/status")
    public Match updateMatchStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return matchService.updateStatus(id, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getTournamentById(@PathVariable Long id) {
        Optional<Match> match = matchService.getMatchById(id);
        return match.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/tournament/{tournamentId}")
    public List<Match> getMatchesByTournament(@PathVariable int tournamentId) {
        // Cette méthode devra être ajoutée à votre MatchService
        return matchService.getMatchsByTournamentId(tournamentId);
    }
    @PostMapping
    public Match addMatch(@RequestBody Match match) {
        return matchService.addMatch(match);
    }




    // 3. Annuler (Supprimer) un match
    @DeleteMapping("/{id}")
    public void cancelMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
    }

}
