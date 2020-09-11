package com.controller;




import java.nio.charset.Charset;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.crypto.spec.SecretKeySpec;


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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.bookstore.entity.Employe;
import com.bookstore.entity.RoleEmploye;
import com.bookstore.entity.Societe;

import com.services.IEmployeService;
import com.services.ISocieteService;

import com.services.SocieteService;



@Controller
@RequestMapping("base")
public class BaseController {
	@Autowired
	private IEmployeService serviceEmploye;
	@Autowired
	private ISocieteService serviceSociete;
	@Autowired
    public JavaMailSender emailSender;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("AjoutSociete")
	public ResponseEntity<Societe> AjoutSociete(@RequestBody Societe req,@RequestParam String address){
		
		
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
	    if(s!=null) {
	    	SimpleMailMessage message = new SimpleMailMessage(); 
	        message.setFrom("foulenbenfoulen02096@gmail.com");
	        message.setTo(address); 
	        message.setSubject("Circulation COVID19"); 
	        message.setText("le mot de passe est :"+generatePwd);
	        emailSender.send(message);
	         spwd =serviceSociete.ajoutPassword(s, generatePwd);
	    }
	  
		return new ResponseEntity<Societe>(spwd, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<Societe> loginSociete(@RequestParam String email,@RequestParam String rne) throws Exception{
		System.out.println("teeeest");
		//authenticate(email, rne);
			Societe s =serviceSociete.login(email, rne);
			return new ResponseEntity<Societe> (s, HttpStatus.OK);
	

		
	}
	
	@GetMapping("test")
	public ResponseEntity<String> test(){
		
	
			return new ResponseEntity<String> ("ssss", HttpStatus.OK);
	

		
	}


	@GetMapping("DemandesEncours")
	public ResponseEntity<List<Societe>> DemandesEncours(){
		List<Societe> list = new ArrayList<>();
			list=serviceSociete.DemandesEncours();
			return new ResponseEntity<List<Societe>>  (list, HttpStatus.OK);
	

		
	}
	

	
	
	
	
	
	@GetMapping("TrouverResponsable")
	public ResponseEntity<Employe> TrouverResponsable(@RequestParam int id){
	
		Employe	e=serviceEmploye.TrouverResponsable(id);
			return new ResponseEntity<Employe>  (e, HttpStatus.OK);
	

		
	}
	@PostMapping("repondreDemande")
	public ResponseEntity<Societe> repondreDemande(@RequestParam String reponse,@RequestBody Societe req){
	
		    Societe	e=serviceSociete.repondreDemande(req, reponse);
		    System.out.println(e);
			return new ResponseEntity<Societe>  (e, HttpStatus.OK);
	}
	
	@PostMapping("DemanderAutorisation")
	public ResponseEntity<Employe> DemanderAutorisation(@RequestParam String nom,@RequestParam String prenom,@RequestParam int cin,@RequestParam int tel,@RequestParam String adresse,@RequestBody Societe req){
		serviceSociete.modifierSociete(req);
		System.out.println(req.getNbEffectifMobilise());
		for(int i=0;i<req.getListeEmployes().size();i++) {
			
	serviceEmploye.ajouterEmploye(new Employe(req.getListeEmployes().get(i).getNom(),req.getListeEmployes().get(i).getPrenom(),req.getListeEmployes().get(i).getCin(),RoleEmploye.Normal,req));
}
Employe e=serviceEmploye.ajouterEmploye(new Employe(nom,prenom,adresse,cin,RoleEmploye.Responsable,req,tel));
		return new ResponseEntity<Employe>(e, HttpStatus.OK);
		
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
