package gl2.example.tournamentproject.dto;

public class PlayerRequest {
    private String name;
    private int age;
    private Long teamId;

    public PlayerRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }
}