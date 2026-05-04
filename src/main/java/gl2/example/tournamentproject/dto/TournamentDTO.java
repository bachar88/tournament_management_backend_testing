package gl2.example.tournamentproject.dto;

import gl2.example.tournamentproject.model.Tournament;
import java.time.LocalDate;

public class TournamentDTO {
    private Long id;
    private String name;
    private String format;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private boolean inscOpen;
    private Tournament.TournamentStatus status;

    // Constructeurs
    public TournamentDTO() {}

    public TournamentDTO(Tournament t) {
        this.id = t.getId();
        this.name = t.getName();
        this.format = t.getFormat();
        this.dateDebut = t.getDateDebut();
        this.dateFin = t.getDateFin();
        this.inscOpen = t.isInscOpen();
        this.status = t.getStatus();
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }
    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public boolean isInscOpen() { return inscOpen; }
    public void setInscOpen(boolean inscOpen) { this.inscOpen = inscOpen; }
    public Tournament.TournamentStatus getStatus() { return status; }
    public void setStatus(Tournament.TournamentStatus status) { this.status = status; }
}