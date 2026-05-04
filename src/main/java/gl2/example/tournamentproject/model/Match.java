package gl2.example.tournamentproject.model;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;


@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idTourn;
    private LocalDateTime dateHeure;
    private String lieu;
    private String statut;
    private int scoreDom;
    private int scoreExt;
    private boolean forfait;
    @Column(nullable = true)
    private Date dateReport;
    // --- GETTERS ---

    public int getId() {return id;}

    public int getIdTourn() {return idTourn;}

    public LocalDateTime getDateHeure() {return dateHeure;}

    public String getLieu() {return lieu;}

    public String getStatut() {return statut;}

    public int getScoreDom() {return scoreDom;}

    public int getScoreExt() {return scoreExt;}

    public boolean isForfait() {return forfait;}

    public Date getDateReport() {return dateReport;}

    // --- SETTERS ---

    public void setId(int id) {this.id = id;}

    public void setIdTourn(int id) {this.idTourn = id;}

    public void setDateHeure(LocalDateTime dateHeure) {this.dateHeure = dateHeure;}

    public void setLieu(String lieu) {this.lieu = lieu;}

    public void setStatut(String statut) {this.statut = statut;}

    public void setScoreDom(int scoreDom) {this.scoreDom = scoreDom;}

    public void setScoreExt(int scoreExt) {this.scoreExt = scoreExt;}

    public void setForfait(boolean forfait) {this.forfait = forfait;}

    public void setDateReport(Date dateReport) {this.dateReport = dateReport;}

}
