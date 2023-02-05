package com.dehghan.unittest.student;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping
    public List<StudentEntity> getAllStudents(){

        return  studentService.getAllStudents();
    }

    @PostMapping
    public  void newStudent(@Valid @RequestBody StudentEntity student){
        studentService.newStudent(student);
    }

    @DeleteMapping("/{deletedId}")
    public  void  deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }
}
