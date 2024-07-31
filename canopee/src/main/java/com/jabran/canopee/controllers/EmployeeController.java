package com.jabran.canopee.controllers;

import com.jabran.canopee.entities.Employee;
import com.jabran.canopee.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){
        return employeeService.getEmployeeById((Integer)id);
    }
    @PostMapping("/employees")
    public void saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
    }

    @PostMapping("/employees/all")
    public void saveAllEmployees(@RequestBody List<Employee> employees){
        employeeService.saveAllEmployee(employees);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee((Integer) id);
    }

    @PutMapping("/employees")
    public void updateEmployee(@RequestBody Employee e){
        employeeService.saveEmployee(e);
    }

    @GetMapping("/employees/names/{fname}")
    public List<Employee> getEmployeesByFname(@PathVariable String fname){
        return employeeService.getEmployeeByFirstName(fname);
    }





}
