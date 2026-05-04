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

    public List<Tournament> getAllTournamentsOpen() {
        return tournamentRepository.findByInscOpenTrue();
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public Tournament addTournament(Tournament t) {
        return tournamentRepository.save(t);
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }

    public Optional<Tournament> patchTournament(Long id, Map<String, Object> updates) {
        Optional<Tournament> existing = tournamentRepository.findById(id);
        if (existing.isEmpty()) return Optional.empty();

        Tournament t = existing.get();
        updates.forEach((key, value) -> {
            switch (key) {
                case "name"      -> t.setName((String) value);
                case "format"    -> t.setFormat((String) value);
                case "inscOpen"  -> t.setInscOpen((Boolean) value);
                case "dateDebut" -> t.setDateDebut(LocalDate.parse((String) value));
                case "dateFin"   -> t.setDateFin(LocalDate.parse((String) value));
                case "status"    -> t.setStatus(Tournament.TournamentStatus.valueOf((String) value));
            }
        });
        return Optional.of(tournamentRepository.save(t));
    }
}