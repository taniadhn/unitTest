package com.dehghan.unittest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UnitTestApplicationTests {

    Calculater underTest = new Calculater();

    //this is junit
    @Test
    void isShouldAddNumbers() {
        //given
        int numberOne = 20;
        int numberTwo = 30;

        //When
        int result = underTest.add(numberOne, numberTwo);

        //Ten
        int expected  = 50;
        assertThat(result).isEqualTo(expected);
    }

    class Calculater{
        int add(int a, int b){
            return  a +b;
        }
    }

}
