package com.bookstore.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Employe {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	private String nom;
    private String prenom;
    private String adresse;
    private int cin;
    private int tel;

    @Enumerated(EnumType.STRING)
    private RoleEmploye role;
    @ManyToOne
    @JsonIgnore
    private Societe societe;

	public RoleEmploye getRole() {
		return role;
	}
	public void setRole(RoleEmploye role) {
		this.role = role;
	}

	
	public Societe getSociete() {
		return societe;
	}
	
	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Employe(String nom, String prenom, int cin, RoleEmploye role,Societe societe) {
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.societe=societe;
		this.role=role;
	}
	public Employe() {
		
	}
	public Employe(String nom, String prenom, String adresse, int cin, RoleEmploye role,Societe societe,int tel) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cin = cin;
		this.societe=societe;
		this.role=role;
		this.tel=tel;
	}
	
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
    
}
