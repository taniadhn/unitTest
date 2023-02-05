package com.dehghan.unittest.student;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;


    @BeforeEach
    void setUp() {


    }

    @DisplayName("it Should Check If select Exist Email")
    @Test
    void itShouldCheckIfselectExistEmail() {

        //given
        String email = "tanidehghan@gmail.com";
        StudentEntity  student = new StudentEntity(
                "Tania",
                email,
                Gender.FEMALE
        );
        underTest.save(student);

        //when
        boolean expected = underTest.selectExistEmail(email);

        //then
        assertThat(expected).isTrue();


    }


    @DisplayName("it Should Check If select Email Does Not Exists")
    @Test
    void itShouldCheckIfselectEmailDoesNotExists() {

        //given
        String email = "tanidehghan@gmail.com";

        //when
        boolean expected = underTest.selectExistEmail(email);

        //then
        assertThat(expected).isFalse();


    }
    @AfterEach
    void tearDown() {
        //After any test
        underTest.deleteAll();
    }


}