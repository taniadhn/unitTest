package com.dehghan.unittest.student;


import com.dehghan.unittest.student.exceptions.BadRequestException;
import com.dehghan.unittest.student.exceptions.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;

    public List<StudentEntity> getAllStudents(){
        return studentRepository.findAll();
    }

    public void newStudent(StudentEntity student){

        Boolean existEmail = studentRepository
                .selectExistEmail(student.getEmail());
        if(existEmail){
            throw new BadRequestException("Email" + student.getEmail() + "taken");
        }
        studentRepository.save(student);
    }

    public  void deleteStudent(Long studentId){
        if(!studentRepository.existsById(studentId))
            throw  new StudentNotFoundException(
                    "Student with id" + studentId + "does not exists"
            );
        studentRepository.deleteById(studentId);
    }
}
