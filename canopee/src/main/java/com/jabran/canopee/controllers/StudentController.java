package com.jabran.canopee.controllers;

import com.jabran.canopee.entities.Student;
import com.jabran.canopee.entities.StudentErrorResponse;
import com.jabran.canopee.exceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class StudentController {

    private static List<Student> db = Arrays.asList(new Student(1, "John", "Hope"));

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        Student s1 = new Student(1, "John", "Hope");
        Student s2 = new Student(2,"Mike","Jones");
        students.add(s1);
        students.add(s2);
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        List<Student> students = new ArrayList<>();
        Student s1 = new Student(1, "John", "Hope");
        Student s2 = new Student(2,"Mike","Jones");
        students.add(s1);
        students.add(s2);

        List<Student> foundStudent = students.stream().filter((s) -> {
            boolean b = s.getId() == id;
            return b;
        }).collect(Collectors.toList());

        //Only covers the case where the id is not found in the database
        if(foundStudent.isEmpty()) {
            //Who's gonna handle this exception
            throw new StudentNotFoundException("No student found by id: "+id);
        } else {
            return foundStudent.get(0);
        }
    }

    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student s) {
        return s;
    }

}
