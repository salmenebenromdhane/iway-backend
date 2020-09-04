package com.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.entity.Employe;
import com.bookstore.entity.RoleEmploye;
import com.bookstore.entity.Societe;

import com.services.IEmployeService;
import com.services.ISocieteService;

@Controller
@RequestMapping("base")
public class BaseController {
	@Autowired
	private IEmployeService serviceEmploye;
	@Autowired
	private ISocieteService serviceSociete;

	@PostMapping("AjoutSociete")
	public ResponseEntity<Societe> AjoutSociete(@RequestBody Societe req){
		
		
	Societe s = serviceSociete.ajouterSociete(
			req);
		return new ResponseEntity<Societe>(s, HttpStatus.OK);
		
	}
	@GetMapping("login")
	public ResponseEntity<Societe> loginSociete(@RequestParam String email,@RequestParam String rne){
		
	
			Societe s =serviceSociete.login(email, rne);
			return new ResponseEntity<Societe> (s, HttpStatus.OK);
	

		
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
	public ResponseEntity<String> DemanderAutorisation(@RequestParam String nom,@RequestParam String prenom,@RequestParam int cin,@RequestParam int tel,@RequestParam String adresse,@RequestBody Societe req){
		serviceSociete.modifierSociete(req);
		for(int i=0;i<req.getListeEmployes().size();i++) {
	serviceEmploye.ajouterEmploye(new Employe(req.getListeEmployes().get(i).getNom(),req.getListeEmployes().get(i).getPrenom(),req.getListeEmployes().get(i).getCin(),RoleEmploye.Normal,req));
}
serviceEmploye.ajouterEmploye(new Employe(nom,prenom,adresse,cin,RoleEmploye.Responsable,req,tel));
		return new ResponseEntity<String>("ok", HttpStatus.OK);
		
	}
}
