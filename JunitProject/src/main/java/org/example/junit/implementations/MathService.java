package org.example.junit.implementations;


import org.example.junit.interfaces.Calculator;

public class MathService {

    private Calculator calculator;

    public MathService(Calculator calculator){
        this.calculator = calculator;
    }

    public int multipleSum(int a, int b){
        int sum = this.calculator.add(a,b);
        return sum*2;
    }
}
