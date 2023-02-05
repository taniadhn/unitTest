package com.dehghan.unittest.student;

import com.dehghan.unittest.student.exceptions.BadRequestException;
import com.dehghan.unittest.student.exceptions.StudentNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private StudentService underTest;

    @BeforeEach
    void setUp() {

        //We can get fresh instance for student service
        underTest = new StudentService(
                studentRepository
        );
    }



    @Test
    void canGetAllStudents() {

        //when
        underTest.getAllStudents();
        //then
        verify(studentRepository).findAll();



    }

    @Test
    void newStudent() {
        //given
        StudentEntity  student = new StudentEntity(
                "Haniyeh",
                "Haniyeh@mail.com",
                Gender.FEMALE
        );
        //when
        underTest.newStudent(student);

        //then
        ArgumentCaptor<StudentEntity> studentEntityArgumentCaptor =
                ArgumentCaptor.forClass(StudentEntity.class);
        verify(studentRepository)
                .save(studentEntityArgumentCaptor.capture());

        StudentEntity captureStudent = studentEntityArgumentCaptor.getValue();

        assertThat(captureStudent).isEqualTo(student);
    }

    @DisplayName("will Throw When Email Is Taken")
    @Test
    void willThrowWhenEmailIsTaken() {
        //given
        StudentEntity  student = new StudentEntity(
                "Tania",
                "tanidehghan@gmail.com",
                Gender.FEMALE
        );

        given(studentRepository.selectExistEmail(anyString()))
                .willReturn(true);
        //when
        //then
        assertThatThrownBy(()-> underTest.newStudent(student))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email" + student.getEmail() + "taken");




        verify(studentRepository, never()).save(any());
    }

    @DisplayName("Can Delete Student")
    @Test
    void canDeleteStudent() {
        //given
        long id = 10;
        given(studentRepository.existsById(id))
                .willReturn(true);
        //when
        underTest.deleteStudent(id);

        //then
        verify(studentRepository).deleteById(id);
    }

    @Test
    void willThrownWhenDeleteStudentNotFound(){

        //given
        long id = 10;
        given(studentRepository.existsById(id))
                .willReturn(false);
        //when
        //then
        assertThatThrownBy(() -> underTest.deleteStudent(id))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("student with id" + id + "does not exists");
        verify(studentRepository, never()).deleteById(any());
    }

}