package gl2.example.tournamentproject.model;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Tournament {
    public enum TournamentStatus { PENDING, ONGOING, FINISHED }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String format;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private boolean inscOpen;
    private TournamentStatus status;
    public Tournament() {}

    public Tournament(String name, String format, LocalDate dateDebut, LocalDate dateFin, boolean inscOpen) {
        this.name = name;
        this.format = format;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.inscOpen = inscOpen;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public boolean isInscOpen() {
        return inscOpen;
    }
    public void setInscOpen(boolean inscOpen) {
        this.inscOpen = inscOpen;
    }

    public TournamentStatus getStatus() {
        return status;
    }
    public void setStatus(TournamentStatus st) {
        this.status = st;
    }



}
