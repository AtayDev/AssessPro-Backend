package com.jabran.canopee.controllers;

import com.jabran.canopee.entities.HeadManager;
import com.jabran.canopee.entities.Team;
import com.jabran.canopee.repositories.ManagerRepository;
import com.jabran.canopee.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shifter_api")
@CrossOrigin(origins = "http://localhost:3000") // Adjust the allowed origin as necessary
public class ManagerController {

    ManagerRepository managerRepository;
    TeamRepository teamRepository;

    @Autowired
    public ManagerController(ManagerRepository managerRepository,TeamRepository teamRepository) {
        this.managerRepository = managerRepository;
        this.teamRepository = teamRepository;
    }

    @GetMapping("/Managers")
    public List<HeadManager> getAllManagers() {
        return managerRepository.findAll();
    }

    @GetMapping("/Manager/{id}")
    public HeadManager getManagerById(@PathVariable int id) {
        HeadManager headManager = managerRepository.findById(id).orElse(null);
        return headManager;
    }

    @GetMapping("/Manager/{id}/Teams")
    public List<Team> getTeamsOfManager(@PathVariable int id) {
        return teamRepository.findByManagerId(id);
    }

    @PostMapping("/Manager")
    public void saveManager(@RequestBody HeadManager headManager){
        managerRepository.save(headManager);
    }

    @GetMapping("/Managers/count")
    public int getNumberOfManagers() {
        List<HeadManager> allManagers = managerRepository.findAll();
        return allManagers.size();
    }

}
