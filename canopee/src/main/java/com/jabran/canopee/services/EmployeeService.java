package com.jabran.canopee.services;

import com.jabran.canopee.repositories.EmployeeRepository;
import com.jabran.canopee.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;


    @Autowired
    EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
    public Employee getEmployeeById(int id){
        Optional<Employee> optionalEmployee =  employeeRepository.findById((Integer)id);
        Employee employee = optionalEmployee.orElse(new Employee(99,"UNKOWN","UNKOWN","UNKOWN","UNKOWN",null));
        return employee;
    }

    public void saveEmployee(Employee e){
        employeeRepository.save(e);
    }

    public void saveAllEmployee(List<Employee> employees){
        employeeRepository.saveAll(employees);
    }

    public void deleteEmployee(int id){
        employeeRepository.deleteById((Integer) id);
    }

    public List<Employee> getEmployeeByFirstName(String fname) {
        return employeeRepository.findByFirstName(fname);
    }

}
