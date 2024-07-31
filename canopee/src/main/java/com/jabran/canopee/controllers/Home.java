package com.jabran.canopee.controllers;

import com.jabran.canopee.entities.Employee;
import com.jabran.canopee.services.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class Home {

    public EmployeeService employeeService;

    @Autowired
    Home(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("message","Hello From Controller");
        model.addAttribute("date", new Date());
        return "greeting";
    }

    @GetMapping("/showForm")
    public String showForm(){
        return "home/home-form";
    }

    @PostMapping("/processForm")
    public String processForm(){
        return "home/process-form";
    }

    @PostMapping("/processFormWithModel")
    public String processFormWithModel(HttpServletRequest request, Model model){
        //Use HttpServletRequest to intercept the incoming request to this handler

        String userName = request.getParameter("userName");
        model.addAttribute("userName",userName);
        return "home/process-form-with-Model";
    }

    @GetMapping("/employeeForm")
    public String getEmployeeForm( Model model){
        //Use HttpServletRequest to intercept the incoming request to this handler
        model.addAttribute("employee", new Employee());
        return "employee/employee-form";
    }

    @PostMapping("/processEmployeeForm")
    public String processEmployeeForm(@ModelAttribute("employee") Employee employee){

        //Persist Student to DB
        employeeService.saveEmployee(employee);

        //Return a confirmation page
        return "employee/confirmation-page";
    }
}
