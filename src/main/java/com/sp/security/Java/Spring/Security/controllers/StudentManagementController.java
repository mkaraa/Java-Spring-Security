package com.sp.security.Java.Spring.Security.controllers;

import com.sp.security.Java.Spring.Security.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/student")
public class StudentManagementController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student("1","Jack Grealish"),
            new Student("2","Kun Agüero"),
            new Student("3","İlkay Gündoğan"),
            new Student("4","Gabriel Jesus"),
            new Student("5","Pep Guardiola"),
            new Student("6","Yaya Toure")
    );

    @GetMapping
    public List<Student> getAllStudents(){
        return STUDENTS;
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        System.out.println(student.getStudentName());
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") String studentId){
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable String studentId,@RequestBody Student student){
        System.out.printf("%s %s%n",studentId,student);
    }

}
