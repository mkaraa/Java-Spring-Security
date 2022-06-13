package com.sp.security.Java.Spring.Security.controllers;

import com.sp.security.Java.Spring.Security.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student("1","Jack Grealish"),
            new Student("2","Kun Agüero"),
            new Student("3","İlkay Gündoğan"),
            new Student("4","Gabriel Jesus"),
            new Student("5","Pep Guardiola"),
            new Student("6","Yaya Toure")
    );

    @GetMapping(path = "{studentId}")
    public Student getAllStudents(@PathVariable String studentId){
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
