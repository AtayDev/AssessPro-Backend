package com.jabran.canopee.controllers;

import com.jabran.canopee.entities.HeadManager;
import com.jabran.canopee.entities.Team;
import com.jabran.canopee.repositories.ManagerRepository;
import com.jabran.canopee.repositories.TeamRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shifter_api")
@CrossOrigin(origins = "http://localhost:3000") // Adjust the allowed origin as necessary

public class TeamController {

    private final ManagerRepository managerRepository;
    TeamRepository teamRepository;
    @Autowired
    public TeamController(TeamRepository teamRepository, ManagerRepository managerRepository) {
        this.teamRepository = teamRepository;
        this.managerRepository = managerRepository;
    }

    @PostMapping("/Team")
    public void saveTeam(@RequestBody Team team){
        teamRepository.save(team);
    }
    @GetMapping("/Teams")
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }
    @GetMapping("/Team/{id}")
    public Team getTeamById(@PathVariable int id) {
        return teamRepository.findById(id).orElse(null);
    }

    @GetMapping("/Team/{id}/manager")
    public ResponseEntity<HeadManager> getTeamManager(@PathVariable int id) {
        Team team = teamRepository.findById(id).orElse(null);
        if (team != null) {
            return ResponseEntity.ok(team.getManager());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/Teams/count")
    public int getNumberOfTeams() {
        List<Team> allTeams = teamRepository.findAll();
        return allTeams.size();
    }

    @PostMapping("/teams/import")
    public ResponseEntity<String> importTeams(@RequestParam("file") MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<Team> teams = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }

                int managerId = (int) row.getCell(1).getNumericCellValue();
                Optional<HeadManager> managerOpt = managerRepository.findById(managerId);
                if (managerOpt.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Manager ID " + managerId + " not found. Please create it first.");
                }

                Team team = new Team();
                team.setId((int) row.getCell(0).getNumericCellValue());
                team.setManager(managerOpt.get());
                teams.add(team);
            }

            teamRepository.saveAll(teams);
            return ResponseEntity.ok("Teams imported successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import teams: " + e.getMessage());
        }
    }

}
