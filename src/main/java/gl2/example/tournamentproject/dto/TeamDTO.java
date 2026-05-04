package gl2.example.tournamentproject.dto;

import gl2.example.tournamentproject.model.Team;

public class TeamDTO {
    private Long id;
    private String name;
    private Long tournamentId;  // seulement l'ID, pas l'objet complet

    public TeamDTO() {}

    public TeamDTO(Team t) {
        this.id = t.getId();
        this.name = t.getName();
        this.tournamentId = t.getTournament() != null ? t.getTournament().getId() : null;
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getTournamentId() { return tournamentId; }
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }
}