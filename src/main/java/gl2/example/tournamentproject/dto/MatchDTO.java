package gl2.example.tournamentproject.dto;

import gl2.example.tournamentproject.model.Match;
import java.time.LocalDateTime;

public class MatchDTO {
    private Long id;
    private Long tournamentId;
    private Long teamAId;
    private Long teamBId;
    private int scoreA;
    private int scoreB;
    private Match.MatchStatus status;
    private LocalDateTime scheduledAt;

    public MatchDTO() {}

    public MatchDTO(Match m) {
        this.id = m.getId();
        this.tournamentId = m.getTournament() != null ? m.getTournament().getId() : null;
        this.teamAId = m.getTeamA() != null ? m.getTeamA().getId() : null;
        this.teamBId = m.getTeamB() != null ? m.getTeamB().getId() : null;
        this.scoreA = m.getScoreA();
        this.scoreB = m.getScoreB();
        this.status = m.getStatus();
        this.scheduledAt = m.getScheduledAt();
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTournamentId() { return tournamentId; }
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }

    public Long getTeamAId() { return teamAId; }
    public void setTeamAId(Long teamAId) { this.teamAId = teamAId; }

    public Long getTeamBId() { return teamBId; }
    public void setTeamBId(Long teamBId) { this.teamBId = teamBId; }

    public int getScoreA() { return scoreA; }
    public void setScoreA(int scoreA) { this.scoreA = scoreA; }

    public int getScoreB() { return scoreB; }
    public void setScoreB(int scoreB) { this.scoreB = scoreB; }

    public Match.MatchStatus getStatus() { return status; }
    public void setStatus(Match.MatchStatus status) { this.status = status; }

    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
}