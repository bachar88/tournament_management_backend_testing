package gl2.example.tournamentproject.service;

import gl2.example.tournamentproject.model.Team;
import gl2.example.tournamentproject.model.Tournament;
import gl2.example.tournamentproject.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gl2.example.tournamentproject.dto.TeamRequest;
import gl2.example.tournamentproject.repository.TournamentRepository;
import gl2.example.tournamentproject.exception.ResourceNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private TournamentRepository tournamentRepository;

    public Team createTeam(TeamRequest request) {
        Tournament tournament = tournamentRepository.findById(request.getTournamentId())
                .orElseThrow(() -> new ResourceNotFoundException("Tournament not found"));
        Team team = new Team(request.getName(), tournament);
        team = teamRepository.save(team);

        // Diagnostic
        System.out.println("Tournament loaded: " + team.getTournament());
        System.out.println("Tournament ID: " + (team.getTournament() != null ? team.getTournament().getId() : "NULL"));

        return team;
    }
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public List<Team> getTeamsByTournament(Long tournamentId) {
        return teamRepository.findByTournamentId(tournamentId);
    }

    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public Optional<Team> patchTeam(Long id, Map<String, Object> updates) {
        Optional<Team> existing = teamRepository.findById(id);
        if (existing.isEmpty()) return Optional.empty();

        Team t = existing.get();
        if (updates.containsKey("name")) {
            t.setName((String) updates.get("name"));
        }
        return Optional.of(teamRepository.save(t));
    }
}