package gl2.example.tournamentproject.dto;

import gl2.example.tournamentproject.model.Player;

public class PlayerDTO {
    private Long id;
    private String name;
    private int age;
    private Long teamId;

    public PlayerDTO() {}

    public PlayerDTO(Player p) {
        this.id = p.getId();
        this.name = p.getName();
        this.age = p.getAge();
        this.teamId = p.getTeam() != null ? p.getTeam().getId() : null;
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }
}