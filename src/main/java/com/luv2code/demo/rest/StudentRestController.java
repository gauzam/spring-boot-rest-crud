package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //define @PostConstruct to load the student data... only once!
    @PostConstruct
    private void loadData(){

        theStudents = new ArrayList<>();

        theStudents.add(new Student("Sam", "Altman"));
        theStudents.add(new Student("Elon", "Musk"));
        theStudents.add(new Student("Paul", "Graham"));

    }

    //define endpoint for "/students" - returns a list of all the students
    @GetMapping("/students")
    public List<Student> getStudents(){

        return theStudents;
    }

    //define endpoint for "/students/{studentId}" - returns a student at given index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        //just index into the list.. for now

        return theStudents.get(studentId);
    }

}
