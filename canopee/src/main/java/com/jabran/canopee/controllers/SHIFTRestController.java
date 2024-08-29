package com.jabran.canopee.controllers;

import com.jabran.canopee.entities.Agent;
import com.jabran.canopee.entities.Employee;
import com.jabran.canopee.entities.HeadManager;
import com.jabran.canopee.entities.Team;
import com.jabran.canopee.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v2")
@CrossOrigin(origins = "http://localhost:3000") // Adjust the allowed origin as necessary

public class SHIFTRestController {

    ManagerRepository managerRepository;
    TeamRepository teamRepository;
    AgentRepository agentRepository;
    SkillRepository skillRepository;
    ModeEvaluationRepository modeEvaluationRepository;

    @Autowired
    public SHIFTRestController(ManagerRepository managerRepository, TeamRepository teamRepository, AgentRepository agentRepository, SkillRepository skillRepository, ModeEvaluationRepository modeEvaluationRepository) {
        this.managerRepository = managerRepository;
        this.teamRepository = teamRepository;
        this.agentRepository = agentRepository;
        this.skillRepository = skillRepository;
        this.modeEvaluationRepository = modeEvaluationRepository;
    }

    @GetMapping("/Agents")
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }
    @GetMapping("/Agent/{id}")
    public Agent getAgentById(@PathVariable int id) {
        Agent agent = agentRepository.findById(id).orElse(null);
        return agent;
    }


}
