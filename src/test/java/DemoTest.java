import org.example.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DemoTest {

   static int fValue1;
    static  int fValue2;
    static  Calculator calculator;
//    @BeforeAll
//    public static void setUp() {
//        fValue1 = 4;
//        fValue2 = 2;
//        calculator = new Calculator();
//    }
//
//    @AfterAll
//    public static void afterAll(){
//        System.out.println("Clean up any resources !!");
//    }
    //to accept different parameters for a method
    @ParameterizedTest
    @MethodSource("returnDifferentParameters")
    public void test(int a, int b){
        Calculator calculator = new Calculator();
        int result = calculator.divide(a,b);
        assertEquals(2,result);
       // System.out.println("hello test");
    }


    @Disabled
    @Test
    public void disabledTest(){
        System.out.println("Still working!!");
    }

    private static Stream<Arguments> returnDifferentParameters(){
        return Stream.of(Arguments.of(4,2),
                Arguments.of(10,5),Arguments.of(12,6));
    }
}
