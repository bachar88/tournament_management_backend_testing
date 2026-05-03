package gl2.example.tournamentproject.service;

import gl2.example.tournamentproject.model.Tournament;
import gl2.example.tournamentproject.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public Tournament addTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }

    public Optional<Tournament> patchTournament(Long id, Map<String, Object> updates) {
        return tournamentRepository.findById(id).map(existing -> {

            if (updates.containsKey("name")) {
                existing.setName((String) updates.get("name"));
            }
            if (updates.containsKey("format")) {
                existing.setFormat((String) updates.get("format"));
            }
            if (updates.containsKey("dateDebut")) {
                existing.setDateDebut(LocalDate.parse((String) updates.get("dateDebut")));
            }
            if (updates.containsKey("dateFin")) {
                existing.setDateFin(LocalDate.parse((String) updates.get("dateFin")));
            }
            if (updates.containsKey("inscOpen")) {
                existing.setInscOpen((Boolean) updates.get("inscOpen"));
            }

            return tournamentRepository.save(existing);
        });
    }
    public List<Tournament> getAllTournamentsOpen(){
        return tournamentRepository.findByInscOpenTrue();
    }
}
