package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.services.IEmployeService;
import com.services.ISocieteService;


@Controller
@RequestMapping("ds")
public class DemandeCirculationController {
	@Autowired
	private IEmployeService serviceEmploye;
	@Autowired
	private ISocieteService serviceSociete;

	@GetMapping("test")
	public ResponseEntity<String> sayHello(){
	
		return new ResponseEntity<String>("works", HttpStatus.OK);
		
	}
}
