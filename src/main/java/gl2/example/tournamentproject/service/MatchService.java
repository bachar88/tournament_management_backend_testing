package gl2.example.tournamentproject.service;

import gl2.example.tournamentproject.model.Match;
import gl2.example.tournamentproject.model.Tournament;
import gl2.example.tournamentproject.repository.MatchRepository;
import gl2.example.tournamentproject.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MatchService {
    @Autowired
    private MatchRepository matchRepository;
    public List<Match> getAllMatchs() {
        return matchRepository.findAll();
    }

    public List<Match> getMatchsByTournamentId(int id){
        return matchRepository.findByIdTourn(id);
    }

    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public Match addMatch(Match match) {
        return matchRepository.save(match);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }

    public Optional<Match> patchMatch(Long id, Map<String, Object> updates) {
        return matchRepository.findById(id).map(existing -> {
            if (updates.containsKey("idTournament")) {
                existing.setIdTourn((Integer) updates.get("idTournament"));
            }
            if (updates.containsKey("dateHeure")) {
                existing.setDateHeure(LocalDateTime.parse((String) updates.get("dateHeure")));
            }
            if (updates.containsKey("lieu")) {
                existing.setLieu((String) updates.get("lieu"));
            }
            if (updates.containsKey("statut")) {
                existing.setStatut((String) updates.get("statut"));
            }
            if (updates.containsKey("scoreDom")) {
                existing.setScoreDom((Integer) updates.get("scoreDom"));
            }
            if (updates.containsKey("scoreExt")) {
                existing.setScoreExt((Integer) updates.get("scoreExt"));
            }
            if (updates.containsKey("forfait")) {
                existing.setForfait((Boolean) updates.get("forfait"));
            }
            if (updates.containsKey("dateReport")) {
                // Note: Conversion dépend de votre format de date
                existing.setDateReport(new Date((Long) updates.get("dateReport")));
            }

            return matchRepository.save(existing);
        });
    }
    public Match updateScore(Long id, int scoreDom, int scoreExt) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match non trouvé"));
        match.setScoreDom(scoreDom);
        match.setScoreExt(scoreExt);
        return matchRepository.save(match);
    }

    public Match updateStatus(Long id, String status) {
        Match match = matchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Match non trouvé"));
        match.setStatut(status);
        return matchRepository.save(match);
    }


}
