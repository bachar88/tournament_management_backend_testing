package gl2.example.tournamentproject.service;

import gl2.example.tournamentproject.model.Match;
import gl2.example.tournamentproject.model.Team;
import gl2.example.tournamentproject.model.Tournament;
import gl2.example.tournamentproject.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gl2.example.tournamentproject.dto.MatchRequest;
import gl2.example.tournamentproject.repository.TournamentRepository;
import gl2.example.tournamentproject.repository.TeamRepository;
import gl2.example.tournamentproject.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private TournamentRepository tournamentRepository; // déjà peut-être présent
    @Autowired
    private TeamRepository teamRepository; // déjà peut-être présent

    public Match createMatch(MatchRequest request) {
        Tournament tournament = tournamentRepository.findById(request.getTournamentId())
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found"));
        Team teamA = teamRepository.findById(request.getTeamAId())
                .orElseThrow(() -> new ResourceNotFoundException("Team A not found"));
        Team teamB = teamRepository.findById(request.getTeamBId())
                .orElseThrow(() -> new ResourceNotFoundException("Team B not found"));
        Match match = new Match(tournament, teamA, teamB, request.getScheduledAt());
        match = matchRepository.save(match);
        // Initialiser les relations
        match.getTournament().getName();
        match.getTeamA().getName();
        match.getTeamB().getName();
        return match;
    }
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Optional<Match> getMatchById(Long id) {
        return matchRepository.findById(id);
    }

    public List<Match> getMatchesByTournament(Long tournamentId) {
        return matchRepository.findByTournamentId(tournamentId);
    }

    public Match addMatch(Match match) {
        return matchRepository.save(match);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }

    public Optional<Match> updateScore(Long id, Map<String, Object> updates) {
        Optional<Match> existing = matchRepository.findById(id);
        if (existing.isEmpty()) return Optional.empty();

        Match m = existing.get();
        if (updates.containsKey("scoreA")) {
            m.setScoreA((Integer) updates.get("scoreA"));
        }
        if (updates.containsKey("scoreB")) {
            m.setScoreB((Integer) updates.get("scoreB"));
        }
        return Optional.of(matchRepository.save(m));
    }

    public Optional<Match> updateStatus(Long id, Map<String, Object> updates) {
        Optional<Match> existing = matchRepository.findById(id);
        if (existing.isEmpty()) return Optional.empty();

        Match m = existing.get();
        if (updates.containsKey("status")) {
            m.setStatus(Match.MatchStatus.valueOf((String) updates.get("status")));
        }
        return Optional.of(matchRepository.save(m));
    }
}