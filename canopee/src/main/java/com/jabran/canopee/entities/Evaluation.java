package com.jabran.canopee.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Evaluation {

    public enum Fonction {
        OPPT, OP, OPF, CC, DSE, CED, AGT, CE
    }

    public enum Competence {
        ADR, CA, DMP_MTI, EP, INCENDIE, M3C, MR, PROCEDURE, REGULISATION_EN_MANU, RONDE, SURVEILLANCE_SDC, TS
    }

    //ModeEvaluation: Auto-évaluation A4 Coeur de métier
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date date;
    private Fonction fonction;
    private Competence competence;
    private int objectif;
    private int note;
    private String pointsFort;
    private String aAmeliorer;
    private Fonction pilote;
    private Date echeance;

    private String modeEvaluation;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    @JsonBackReference("agent-evaluations")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference("team-evaluations")
    private Team team;


    public Evaluation() {
    }

    public Evaluation(int id, Date date, Fonction fonction, Competence competence, int objectif, int note, String pointsFort, String aAmeliorer, Fonction pilote, Date echeance, String modeEvaluation, Agent agent, Team team) {
        this.id = id;
        this.date = date;
        this.fonction = fonction;
        this.competence = competence;
        this.objectif = objectif;
        this.note = note;
        this.pointsFort = pointsFort;
        this.aAmeliorer = aAmeliorer;
        this.pilote = pilote;
        this.echeance = echeance;
        this.modeEvaluation = modeEvaluation;
        this.agent = agent;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public int getObjectif() {
        return objectif;
    }

    public void setObjectif(int objectif) {
        this.objectif = objectif;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getPointsFort() {
        return pointsFort;
    }

    public void setPointsFort(String pointsFort) {
        this.pointsFort = pointsFort;
    }

    public String getaAmeliorer() {
        return aAmeliorer;
    }

    public void setaAmeliorer(String aAmeliorer) {
        this.aAmeliorer = aAmeliorer;
    }

    public Fonction getPilote() {
        return pilote;
    }

    public void setPilote(Fonction pilote) {
        this.pilote = pilote;
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public String getModeEvaluation() {
        return modeEvaluation;
    }

    public void setModeEvaluation(String modeEvaluation) {
        this.modeEvaluation = modeEvaluation;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }



}
