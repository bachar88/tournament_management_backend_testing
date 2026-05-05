package gl2.example.tournamentproject.controller;

import gl2.example.tournamentproject.dto.TeamDTO;
import gl2.example.tournamentproject.dto.TeamRequest;
import gl2.example.tournamentproject.model.Team;
import gl2.example.tournamentproject.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams()
                .stream()
                .map(TeamDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        Optional<Team> team = teamService.getTeamById(id);
        return team.map(t -> ResponseEntity.ok(new TeamDTO(t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TeamDTO addTeam(@RequestBody TeamRequest request) {
        Team team = teamService.createTeam(request);
        return new TeamDTO(team);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        Optional<Team> existing = teamService.getTeamById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();
        team.setId(id);
        Team updated = teamService.addTeam(team);
        return ResponseEntity.ok(new TeamDTO(updated));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeamDTO> patchTeam(@PathVariable Long id,
                                             @RequestBody Map<String, Object> updates) {
        return teamService.patchTeam(id, updates)
                .map(t -> ResponseEntity.ok(new TeamDTO(t)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}