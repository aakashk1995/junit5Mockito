package org.example.junit.implementations;

import org.example.junit.interfaces.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MathServiceTest {
    @Mock
    Calculator calculator;
    @InjectMocks
   private MathService mathService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        //mathService = new MathService(calculator);
    }
    @Test
    public void test_arguments_captor(){
        //used to test arguments passed to method for mock class
        ArgumentCaptor<Integer> argumentCaptor = ArgumentCaptor.forClass(Integer.class);

        when(calculator.add(anyInt(),anyInt())).thenReturn(10);

        int result = mathService.multipleSum(5,3);

        verify(calculator).add(argumentCaptor.capture(),argumentCaptor.capture()); //capture arguments
        verify(calculator,times(1)).add(anyInt(),anyInt());  // capture how many times method
        //invocation had happen
        verify(calculator).add(5,3); // capture that this mock method was called with exact arguments that have passed

        int a = argumentCaptor.getAllValues().get(0);
        int b = argumentCaptor.getAllValues().get(1);

        assertEquals(5,a);
        assertEquals(3,b);
        assertEquals(20,result);
    }
    @Test
    public void testSum(){
        when(calculator.add(anyInt(),anyInt())).thenReturn(8).thenReturn(10).thenReturn(5);
//        when(calculator.add(anyInt(),anyInt())).thenAnswer(invocationOnMock ->
//                {
//                   int a =  invocationOnMock.getArgument(0) ;
//                    int b =  invocationOnMock.getArgument(1);
//                    return a+b;
//                });
        int result = mathService.multipleSum(3,5);
        int result1 = mathService.multipleSum(5,5);
        int result2 = mathService.multipleSum(0,5);
        assertEquals(16,result);
        assertEquals(20,result1);
        assertEquals(10,result2);
       // when(mathService.multipleSum(anyInt(),anyInt())).thenCallRealMethod();

    }
}