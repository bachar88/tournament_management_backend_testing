package gl2.example.tournamentproject.controller;

import gl2.example.tournamentproject.dto.TournamentDTO;
import gl2.example.tournamentproject.dto.TeamDTO;
import gl2.example.tournamentproject.dto.MatchDTO;
import gl2.example.tournamentproject.model.Tournament;
import gl2.example.tournamentproject.model.Team;
import gl2.example.tournamentproject.model.Match;
import gl2.example.tournamentproject.service.TournamentService;
import gl2.example.tournamentproject.service.TeamService;
import gl2.example.tournamentproject.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private MatchService matchService;

    // GET all : retourne une liste de DTO
    @GetMapping
    public List<TournamentDTO> getAllTournaments() {
        return tournamentService.getAllTournaments()
                .stream()
                .map(TournamentDTO::new)
                .collect(Collectors.toList());
    }

    // GET open : idem
    @GetMapping("/isOpen")
    public List<TournamentDTO> getAllTournamentsOpen() {
        return tournamentService.getAllTournamentsOpen()
                .stream()
                .map(TournamentDTO::new)
                .collect(Collectors.toList());
    }

    // GET by id
    @GetMapping("/{id}")
    public ResponseEntity<TournamentDTO> getTournamentById(@PathVariable Long id) {
        Optional<Tournament> tourn = tournamentService.getTournamentById(id);
        return tourn.map(t -> ResponseEntity.ok(new TournamentDTO(t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST : on reçoit une entité, on renvoie le DTO
    @PostMapping
    public TournamentDTO addTournament(@RequestBody Tournament t) {
        Tournament saved = tournamentService.addTournament(t);
        return new TournamentDTO(saved);
    }

    // DELETE inchangé
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
        return ResponseEntity.noContent().build();
    }

    // PUT : reçoit entité, renvoie DTO
    @PutMapping("/{id}")
    public ResponseEntity<TournamentDTO> updateTournament(
            @PathVariable Long id,
            @RequestBody Tournament tourn) {
        Optional<Tournament> existingTourn = tournamentService.getTournamentById(id);
        if (existingTourn.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tourn.setId(id);
        Tournament updated = tournamentService.addTournament(tourn);
        return ResponseEntity.ok(new TournamentDTO(updated));
    }

    // PATCH : retourne un DTO après mise à jour
    @PatchMapping("/{id}")
    public ResponseEntity<TournamentDTO> patchTournament(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        return tournamentService.patchTournament(id, updates)
                .map(t -> ResponseEntity.ok(new TournamentDTO(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    // Nouveaux endpoints : équipes et matchs d’un tournoi, en DTO
    @GetMapping("/{id}/teams")
    public List<TeamDTO> getTeamsByTournament(@PathVariable Long id) {
        return teamService.getTeamsByTournament(id)
                .stream()
                .map(TeamDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/matches")
    public List<MatchDTO> getMatchesByTournament(@PathVariable Long id) {
        return matchService.getMatchesByTournament(id)
                .stream()
                .map(MatchDTO::new)
                .collect(Collectors.toList());
    }
}