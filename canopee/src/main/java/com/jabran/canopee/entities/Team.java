package com.jabran.canopee.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Team {

    @Id
    private int id;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("team-agents")
    private List<Agent> agents;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonBackReference("headManager-teams")
    private HeadManager manager;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("team-evaluations")
    private List<Evaluation> evaluations;

    public Team() {
    }

    public Team(int id, List<Agent> agents, HeadManager manager) {
        this.id = id;
        this.agents = agents;
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public HeadManager getManager() {
        return manager;
    }

    public void setManager(HeadManager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", agents=" + agents +
                ", manager=" + manager +
                '}';
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }
}
