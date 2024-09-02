package com.jabran.canopee.controllers;

import com.jabran.canopee.entities.Agent;
import com.jabran.canopee.entities.Evaluation;
import com.jabran.canopee.entities.Team;
import com.jabran.canopee.repositories.AgentRepository;
import com.jabran.canopee.repositories.TeamRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@CrossOrigin(origins = "${frontend.url}") // Adjust the allowed origin as necessary
public class AgentController {

    TeamRepository teamRepository;
    AgentRepository agentRepository;

    private static final Logger logger = LoggerFactory.getLogger(AgentController.class);

    @Autowired
    public AgentController(AgentRepository agentRepository, TeamRepository teamRepository) {
        this.agentRepository = agentRepository;
        this.teamRepository = teamRepository;
    }

    @PostMapping("/Agents")
    public ResponseEntity<Agent> saveAgent(@RequestBody Agent agent) {
         Agent savedAgent = agentRepository.save(agent);
         return ResponseEntity.ok(savedAgent);
    }

    @GetMapping("/Agents")
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

    @GetMapping("/Agents/count")
    public int getNumberOfAgents() {
        return agentRepository.findAll().size();
    }

    @GetMapping("/Agent/{id}")
    public Agent getAgentById(@PathVariable int id) {
        Agent agent = agentRepository.findById(id).orElse(null);
        return agent;
    }

    @PostMapping("/agents/import")
    public ResponseEntity<String> importAgents(@RequestParam("file") MultipartFile file) {
        logger.info("Starting importAgents method");
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<Agent> agents = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }

                int teamId = (int) row.getCell(5).getNumericCellValue();
                logger.info("Processing row: {}", row.getRowNum());
                logger.info("Team ID: {}", teamId);

                Optional<Team> teamOpt = teamRepository.findById(teamId);
                if (teamOpt.isEmpty()) {
                    logger.error("Team ID {} not found", teamId);
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Team ID " + teamId + " not found. Please create it first.");
                }

                Agent agent = new Agent();
                agent.setId((int) row.getCell(0).getNumericCellValue());
                agent.setFname(row.getCell(1).getStringCellValue());
                agent.setLname(row.getCell(2).getStringCellValue());
                agent.setEmail(row.getCell(3).getStringCellValue());
                agent.setFonction(Agent.Fonction.valueOf(row.getCell(4).getStringCellValue()));
                agent.setTeam(teamOpt.get());

                logger.info("Adding agent: {}", agent);
                agents.add(agent);
            }

            agentRepository.saveAll(agents);
            logger.info("Agents imported successfully");
            return ResponseEntity.ok("Agents imported successfully!");
        } catch (Exception e) {
            logger.error("Failed to import agents", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to import agents: " + e.getMessage());
        }
    }
}
