package com.jabran.canopee.controllers;

import com.jabran.canopee.entities.Evaluation;
import com.jabran.canopee.entities.HeadManager;
import com.jabran.canopee.repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifter_api")
@CrossOrigin(origins = "${frontend.url}") // Adjust the allowed origin as necessary
public class EvaluationController {

    EvaluationRepository evaluationRepository;

    @Autowired
    public EvaluationController(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    @PostMapping("/Evaluation")
    public ResponseEntity<Evaluation> saveManager(@RequestBody Evaluation evaluation){
        if (evaluation.getAgent() != null) {
            evaluation.setTeam(null);
        } else if (evaluation.getTeam() != null) {
            evaluation.setAgent(null);
        }
        Evaluation savedEvaluation = evaluationRepository.save(evaluation);
        return ResponseEntity.ok(savedEvaluation);
    }

    @GetMapping("/Evaluation/Agent/{id}")
    public List<Evaluation> getEvaluationOfAgentById(@PathVariable int id){
        List<Evaluation> evaluations = evaluationRepository.findByAgentId(id);
        return evaluations;
    }

    @GetMapping("/Evaluation/Team/{id}")
    public List<Evaluation> getEvaluationOfTeamById(@PathVariable int id){
        List<Evaluation> evaluations = evaluationRepository.findByTeamId(id);
        return evaluations;
    }

    @PutMapping("/Evaluation/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable int id, @RequestBody Evaluation evaluationDetails) {
        Evaluation evaluation = evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation not found with id " + id));

        evaluation.setDate(evaluationDetails.getDate());
        evaluation.setFonction(evaluationDetails.getFonction());
        evaluation.setCompetence(evaluationDetails.getCompetence());
        evaluation.setObjectif(evaluationDetails.getObjectif());
        evaluation.setNote(evaluationDetails.getNote());
        evaluation.setPointsFort(evaluationDetails.getPointsFort());
        evaluation.setaAmeliorer(evaluationDetails.getaAmeliorer());
        evaluation.setPilote(evaluationDetails.getPilote());
        evaluation.setEcheance(evaluationDetails.getEcheance());
        evaluation.setModeEvaluation(evaluationDetails.getModeEvaluation());

        if (evaluationDetails.getAgent() != null) {
            evaluation.setAgent(evaluationDetails.getAgent());
            evaluation.setTeam(null);
        } else if (evaluationDetails.getTeam() != null) {
            evaluation.setTeam(evaluationDetails.getTeam());
            evaluation.setAgent(null);
        }

        Evaluation updatedEvaluation = evaluationRepository.save(evaluation);
        return ResponseEntity.ok(updatedEvaluation);
    }


}
