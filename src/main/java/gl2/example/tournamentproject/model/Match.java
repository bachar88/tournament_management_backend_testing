package gl2.example.tournamentproject.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Match {

    public enum MatchStatus { SCHEDULED, PLAYED, POSTPONED }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team_a_id")
    private Team teamA;

    @ManyToOne
    @JoinColumn(name = "team_b_id")
    private Team teamB;

    private int scoreA;
    private int scoreB;

    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    private LocalDateTime scheduledAt;

    public Match() {}

    public Match(Tournament tournament, Team teamA, Team teamB, LocalDateTime scheduledAt) {
        this.tournament = tournament;
        this.teamA = teamA;
        this.teamB = teamB;
        this.scheduledAt = scheduledAt;
        this.status = MatchStatus.SCHEDULED;
        this.scoreA = 0;
        this.scoreB = 0;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }

    public Team getTeamA() { return teamA; }
    public void setTeamA(Team teamA) { this.teamA = teamA; }

    public Team getTeamB() { return teamB; }
    public void setTeamB(Team teamB) { this.teamB = teamB; }

    public int getScoreA() { return scoreA; }
    public void setScoreA(int scoreA) { this.scoreA = scoreA; }

    public int getScoreB() { return scoreB; }
    public void setScoreB(int scoreB) { this.scoreB = scoreB; }

    public MatchStatus getStatus() { return status; }
    public void setStatus(MatchStatus status) { this.status = status; }

    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
}