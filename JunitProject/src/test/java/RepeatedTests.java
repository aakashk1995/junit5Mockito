import org.example.junit.Calculator;
import org.example.junit.implementations.MathService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

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
