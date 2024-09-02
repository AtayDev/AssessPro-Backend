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

@RestController
@RequestMapping("/shifter_api")
@CrossOrigin(origins = "${frontend.url}") // Adjust the allowed origin as necessary
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

    @PostMapping("/managers/import")
    public ResponseEntity<String> importManagers(@RequestParam("file") MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<HeadManager> managers = new ArrayList<>();

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { // Skip header row
                    continue;
                }
                HeadManager manager = new HeadManager();
                manager.setId((int) row.getCell(0).getNumericCellValue());
                manager.setEmail(row.getCell(1).getStringCellValue());
                manager.setFname(row.getCell(2).getStringCellValue());
                manager.setLname(row.getCell(3).getStringCellValue());
                managers.add(manager);
            }

            System.out.println("managers size: "+managers.size());
            managerRepository.saveAll(managers);
            return ResponseEntity.ok("Managers imported successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to import managers: " + e.getMessage());
        }
    }

}
