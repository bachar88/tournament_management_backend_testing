package gl2.example.tournamentproject.dto;

public class TeamRequest {
    private String name;
    private Long tournamentId;

    public TeamRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getTournamentId() { return tournamentId; }
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }
}