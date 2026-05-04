package gl2.example.tournamentproject.controller;

import gl2.example.tournamentproject.model.Tournament;
import gl2.example.tournamentproject.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<Tournament> getAllTournaments(){ return tournamentService.getAllTournaments(); }

    @GetMapping("/isOpen")
    public List<Tournament> getAllTournamentsOpen(){
        return tournamentService.getAllTournamentsOpen();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id){
        Optional<Tournament> tourn= tournamentService.getTournamentById(id);
        return tourn.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tournament addTournament(@RequestBody Tournament t){return tournamentService.addTournament(t);}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateEmployee(
            @PathVariable Long id,
            @RequestBody Tournament tourn) {

        // Optionnel : vérifier si l'employé avec l'ID donné existe
        Optional<Tournament> existingTourn = tournamentService.getTournamentById(id);
        if (existingTourn.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Mettre à jour l'employé avec les nouvelles informations
        tourn.setId(id);  // S'assurer que l'ID de l'objet est le même que celui dans l'URL
        Tournament updatedTourn = tournamentService.addTournament(tourn);

        return ResponseEntity.ok(updatedTourn);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tournament> patchTournament(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        return tournamentService.patchTournament(id, updates)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
