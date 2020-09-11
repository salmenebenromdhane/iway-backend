package com.controller;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Administrateur;
import com.bookstore.entity.JwtRequest;
import com.bookstore.entity.JwtResponse;
import com.bookstore.entity.Societe;
import com.config.JwtTokenUtil;
import com.services.IAdminService;
import com.services.ISocieteService;


@RestController
@CrossOrigin
public class JwtAuthenticationController {
	@Autowired
	private ISocieteService serviceSociete;
	@Autowired
	private IAdminService serviceAdmin;

	@Autowired
    public JavaMailSender emailSender;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestParam String username, @RequestParam String password,@RequestParam String role,@RequestBody Societe req,@RequestParam String address)
			throws Exception {
	System.out.println("helooooo");
		
		if(req!=null && role.equals("")){
			
			Societe s = serviceSociete.ajouterSociete(
					req);
			 int leftLimit = 97; // letter 'a'
			    int rightLimit = 122; // letter 'z'
			    int targetStringLength = 10;
			    Random random = new Random();
			 
			    String generatePwd = random.ints(leftLimit, rightLimit + 1)
			      .limit(targetStringLength)
			      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
			      .toString();
			    Societe spwd=null;
			    System.out.println(s.getId());
			    if(s!=null) {
			    	SimpleMailMessage message = new SimpleMailMessage(); 
			        message.setFrom("foulenbenfoulen02096@gmail.com");
			        message.setTo(address); 
			        message.setSubject("Circulation COVID19"); 
			        message.setText("le mot de passe est :"+generatePwd);
			        emailSender.send(message);
			     
			         spwd =serviceSociete.ajoutPassword(s, generatePwd);
			    }
			  
		}
		else if(role.equals("Societe")) {
			Societe s=null;
			 s =serviceSociete.login(username, password);
			System.out.println("gggggggggggggggg"+s);
			if(s!=null) {
				System.out.println("aaaaaaaaaaaa");
				final UserDetails userDetails = jwtInMemoryUserDetailsService
						.loadUserByUsername(username);
				System.out.println("sssssssssssssss");
				final String token = jwtTokenUtil.generateToken(userDetails);
				s.setToken(token);
				
				return new ResponseEntity<Societe>  (s, HttpStatus.OK);
			}
			return new ResponseEntity<String>  ("nf", HttpStatus.OK);
		}
		else if(role.equals("Administrateur")) {
			System.out.println("zzzzzzzzzzzzz");
			Administrateur admin = serviceAdmin.login(username, password);
			System.out.println(admin);
			if(admin!=null) {
				final UserDetails userDetails = jwtInMemoryUserDetailsService
						.loadUserByUsername(username);
				System.out.println(userDetails);
				final String token = jwtTokenUtil.generateToken(userDetails);
			admin.setToken(token);
				return new ResponseEntity<Administrateur>  (admin, HttpStatus.OK);
			}
		}
		 return new ResponseEntity<String>  ("nf", HttpStatus.OK);
		
		//authenticate(username, password);

		

		
	}
	
	private void register() {
		
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
