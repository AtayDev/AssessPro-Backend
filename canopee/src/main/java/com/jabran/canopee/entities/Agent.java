package com.jabran.canopee.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.*;

@Entity
public class Agent {

    public enum Fonction {
        OPPT, OP, OPF, CC, DSE, CED, AGT, CE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fname;
    private String lname;
    private String email;
    private Fonction fonction;


    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("agent-evaluations")
    private List<Evaluation> evaluations;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference("team-agents")
    private Team team;

    public Agent() {
    }

    public Agent(int id, String fname, String lname, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public Agent(int id, String fname, String lname, String email, Team team) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.team = team;
    }

    public Agent(int id, String fname, String lname, String email, Fonction fonction, Team team) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.fonction = fonction;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
