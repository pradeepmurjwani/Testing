package com.devops.bootcamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devops.bootcamp.CalculatorService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CalculatorServiceTest {

	CalculatorService calculatorService = new CalculatorService();
	ScientificCalculatorService scientificCalculatorService = new ScientificCalculatorService();
	
	@Test
	public void testMessage() {
		Assertions.assertEquals(calculatorService.sayHello(), "Hello");
	}
	
	@Test
	public void testWelcomeMessage() {
		Assertions.assertEquals(calculatorService.welcomeMessage("Pradeep"), "Welcome Pradeep");
	}
	
	@Test
	public void testSum() {
		Assertions.assertEquals(calculatorService.sum(10,20), 30);
	}
	
	@Test
	public void testSubtract() {
		Assertions.assertEquals(calculatorService.subtract(10,20), -10);
	}
	
	@Test
	public void testSum1() {
		Assertions.assertNotEquals(calculatorService.sum(10,20), 20);
	}
	
	@Test
	public void testSubtract1() {
		Assertions.assertNotEquals(calculatorService.subtract(10,20), -20);
	}
	
	@Test
	public void testDivision() {
		Assertions.assertEquals(calculatorService.divide(10,20), 0.5);
	}
	
	@Test
	public void testMultiplication() {
		Assertions.assertEquals(calculatorService.multiply(10,20), 200);
	}
	
	@Test
	public void testModulus() {
		Assertions.assertEquals(scientificCalculatorService.mod(10,20), 10);
	}
	
	@Test
	public void testPercentage() {
		Assertions.assertEquals(scientificCalculatorService.percentage(10,20), 50);
	}
	
	@Test
	public void testSquareRoot() {
		Assertions.assertEquals(scientificCalculatorService.sqrt(16), 4);
	}
	
	@Test
	public void testFactorial() {
		Assertions.assertEquals(scientificCalculatorService.factorial(10), 3628800);
	}
}
