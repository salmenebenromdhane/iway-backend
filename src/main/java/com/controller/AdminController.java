package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.entity.Administrateur;
import com.bookstore.entity.Societe;
import com.services.IAdminService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private IAdminService adminService;
	
	@GetMapping("login")
	public ResponseEntity<Administrateur> loginAdmin(@RequestParam String email,@RequestParam String mdp){
		
	
			Administrateur s =adminService.login(email, mdp);
			System.out.println(s);
			return new ResponseEntity<Administrateur> (s, HttpStatus.OK);
	

		
	}
	
}
