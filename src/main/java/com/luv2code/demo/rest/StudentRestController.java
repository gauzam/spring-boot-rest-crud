package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        //check the studentId against the list size
        if( (studentId >= theStudents.size()) || (studentId < 0) ){

            //means student not found
            throw new StudentNotFoundException("Student ID not found - " + studentId);
        }

        //means student id exists, so we return the Student
        return theStudents.get(studentId);
    }

}
