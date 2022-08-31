package com.example.docker.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.docker.entity.Test;
import com.example.docker.repository.TestRepository;

@RestController
public class WelcomeController {
	@Autowired
	private TestRepository repository;
	
	@RequestMapping("/")
	public String welcome() {
		return "WELCOME";
	}
	
	@GetMapping("/list")
	public List<Test> list() {
		return repository.findAll();
	}
}
