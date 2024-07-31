package com.jabran.canopee.controllers;

import com.jabran.canopee.entities.*;
import com.jabran.canopee.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Shifts")

public class SHIFTController {

    ManagerRepository managerRepository;
    TeamRepository teamRepository;
    AgentRepository agentRepository;
    SkillRepository skillRepository;
    ModeEvaluationRepository modeEvaluationRepository;

    @Autowired
    public SHIFTController(ManagerRepository managerRepository, TeamRepository teamRepository, AgentRepository agentRepository, SkillRepository skillRepository, ModeEvaluationRepository modeEvaluationRepository) {
        this.managerRepository = managerRepository;
        this.teamRepository = teamRepository;
        this.agentRepository = agentRepository;
        this.skillRepository = skillRepository;
        this.modeEvaluationRepository = modeEvaluationRepository;
    }

    @GetMapping("/Manager")
    public String getManagerForm(Model model) {
        model.addAttribute("manager", new HeadManager());
        return "shifts/manager-form";
    }

    @PostMapping("/processManagerForm")
    public String processManagerForm(@ModelAttribute("manager") HeadManager headManager) {
        managerRepository.save(headManager);
        return "shifts/process-manager-form";
    }

    @GetMapping("/Team")
    public String getTeamForm(Model model) {
        model.addAttribute("team", new Team());
        List<HeadManager> managers = managerRepository.findAll();
        model.addAttribute("managers", managers);
        return "shifts/team-form";
    }

    @PostMapping("/processTeamForm")
    public String processTeamForm(@ModelAttribute("team") Team team) {
        System.out.println(team.toString());
        teamRepository.save(team);
        return "shifts/process-team-form";
    }

    @GetMapping("/Agent")
    public String getAgentForm(Model model) {
        model.addAttribute("agent", new Agent());
        List<Team> teams = teamRepository.findAll();
        model.addAttribute("teams", teams);
        return "shifts/agent-form";
    }

    @PostMapping("/processAgentForm")
    public String processAgentForm(@ModelAttribute("agent") Agent agent) {
        agentRepository.save(agent);
        return "shifts/process-agent-form";
    }

    @GetMapping("/Agent/{id}")
    public String getAgent(@PathVariable int id, Model model) {
        Agent agent = agentRepository.findById(id).orElse(null);
        model.addAttribute("agent", agent);
        return "shifts/agent-details";
    }

    @GetMapping("/ModeEvaluation")
    public String getModeEvaluation(Model model) {
        model.addAttribute("modeEvaluation", new ModeEvaluation());
        return "shifts/mode-form";
    }

    @PostMapping("/processModeEvalForm")
    public String processModeEvaluation(@ModelAttribute("modeEvaluation") ModeEvaluation modeEvaluation) {
        modeEvaluationRepository.save(modeEvaluation);
        return "shifts/mode-form-save";
    }

    @GetMapping("/Skills")
    public String getSkill(Model model) {
        model.addAttribute("skill", new Skill());
        List<ModeEvaluation> modeEvaluations = modeEvaluationRepository.findAll();
        model.addAttribute("modeEvaluations", modeEvaluations);
        return "shifts/skill-form";
    }

    @PostMapping("/processSkillForm")
    public String processSkillForm(@ModelAttribute("skill") Skill skill) {
        skillRepository.save(skill);
        return "shifts/skill-form-save";
    }
}
