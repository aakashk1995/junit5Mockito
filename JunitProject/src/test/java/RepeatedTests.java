import org.example.junit.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Order(1)
public class RepeatedTests {
    Calculator calculator;
    @BeforeEach
    public void createCalculatorInstance(){
        calculator = new Calculator();
    }


    @RepeatedTest(3)
    public void test(RepetitionInfo repetitionInfo, TestInfo testInfo){
        System.out.println(repetitionInfo);
        System.out.println(testInfo);
        int a = 4; int b = 2;
        int result = calculator.divide(a,b);
        assertEquals(2,result);
        // System.out.println("hello test");
    }
}
