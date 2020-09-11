package com.services;

import java.util.List;

import com.bookstore.entity.Societe;

public interface ISocieteService {

	public Societe ajouterSociete(Societe s);
	public Societe TrouverSociete(int id);
	public Societe login(String email,String rne);
	public Societe modifierSociete(Societe s);
	public Societe ajoutPassword(Societe s,String pwd);
	public Societe repondreDemande(Societe s,String reponse);
	public List<Societe> DemandesEncours();
	public Societe getByEmail(String email);
}
