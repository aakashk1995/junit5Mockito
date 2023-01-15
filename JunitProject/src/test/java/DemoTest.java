import org.example.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Order(2)
public class DemoTest {

   static int fValue1;
    static  int fValue2;
    @Mock
    Calculator calculator;

    @BeforeEach
    public void createCalculatorInstance(){
        calculator = new Calculator();
    }

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
   // @MethodSource("returnDifferentParameters")
    @CsvSource({"4,2","10,5"})
    public void test(int a, int b){
        int result = calculator.divide(a,b);
        assertEquals(2,result);
       // System.out.println("hello test");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Aakash","sakshi"})
    public void valueSourceTest(String value){
        System.out.println(value);
        assertNotNull(value);
    }


    @Test
    public void calculatorTest(){
      int resut=   calculator.divide(4,2);
        System.out.println(resut);
    }
    @Disabled
    @Test
    public void disabledTest(){
        System.out.println("Still working!!");
    }

//    private static Stream<Arguments> returnDifferentParameters(){
//        return Stream.of(Arguments.of(4,2),
//                Arguments.of(10,5),Arguments.of(12,6));
//    }
}
