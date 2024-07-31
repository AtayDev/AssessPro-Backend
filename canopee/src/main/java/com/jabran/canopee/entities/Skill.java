package com.jabran.canopee.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String label;
    private String description;

    @ManyToOne
    @JoinColumn(name = "mode_id")
    @JsonBackReference
    private ModeEvaluation modeEvaluation;

    public Skill() {
    }

    public Skill(int id, String label, String description, ModeEvaluation modeEvaluation) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.modeEvaluation = modeEvaluation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ModeEvaluation getModeEvaluation() {
        return modeEvaluation;
    }

    public void setModeEvaluation(ModeEvaluation modeEvaluation) {
        this.modeEvaluation = modeEvaluation;
    }
}
