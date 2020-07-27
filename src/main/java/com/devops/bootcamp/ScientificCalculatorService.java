package com.devops.bootcamp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScientificCalculatorService {

	@GetMapping("/mod")
	public int mod(@RequestParam int a, @RequestParam int b) {
		return a%b;
	}
	
	@GetMapping("/percentage")
	public float percentage(@RequestParam float marksObtained, @RequestParam float totalMarks) {
		return ((marksObtained/totalMarks)*100);
	}
	
	@GetMapping("/squareRoot")
	public double sqrt(@RequestParam double a) {
		return Math.sqrt(a);
	}
	
	@GetMapping("/factorial")
	public long factorial(@RequestParam int number) {
		long fact = 1;
        for(int i = 1; i <= number; ++i) {
            fact *= i;
        }
        return fact;
	}
}
