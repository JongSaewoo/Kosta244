package com.example.demo.demodocker1.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
//	@GetMapping("/")
	@RequestMapping("/")
	public String welcome() {
		return "WELCOME";
	}
}