package com.bookstore.entity;


import java.util.List;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Societe {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	private String rne;
	private String rasionSociale;
    private String matriculeFiscale;
    private int effectifTotal;
    private String adresse;
    private String governorat;
    private String secteurActivite;
    private String ministereDeTutuelle;
    private String email;
    private String etatDemande;
    private int nbEffectifMobilise;
    private String motDePasse;
    private String token;
    
    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	@OneToMany(mappedBy = "societe")
    
    private List<Employe> listeEmployes;
    public Societe(String rasionSociale) {
    	this.rasionSociale = rasionSociale;
    }
    public Societe() {
    	
    }
	public Societe(String rasionSociale, String matriculeFiscale, int effectifTotal, String adresse,
			String governorat, String secteurActivite,String rne,String ministereDeTutuelle,String email,int nbEffectifMobilise) {
		this.rasionSociale = rasionSociale;
		this.matriculeFiscale = matriculeFiscale;
		this.effectifTotal = effectifTotal;
		this.adresse = adresse;
		this.governorat = governorat;
		this.secteurActivite = secteurActivite;
		this.rne=rne;
		this.ministereDeTutuelle=ministereDeTutuelle;
		this.email=email;
		this.nbEffectifMobilise=nbEffectifMobilise;
		
	}
	public int getNbEffectifMobilise() {
		return nbEffectifMobilise;
	}
	public void setNbEffectifMobilise(int nbEffectifMobilise) {
		this.nbEffectifMobilise = nbEffectifMobilise;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRasionSociale() {
		return rasionSociale;
	}
	public void setRasionSociale(String rasionSociale) {
		this.rasionSociale = rasionSociale;
	}
	public String getMatriculeFiscale() {
		return matriculeFiscale;
	}
	public void setMatriculeFiscale(String matriculeFiscale) {
		this.matriculeFiscale = matriculeFiscale;
	}
	public int getEffectifTotal() {
		return effectifTotal;
	}
	public void setEffectifTotal(int effectifTotal) {
		this.effectifTotal = effectifTotal;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getGovernorat() {
		return governorat;
	}
	public void setGovernorat(String governorat) {
		this.governorat = governorat;
	}

	
	
	
	public List<Employe> getListeEmployes() {
		return listeEmployes;
	}
	public void setListeEmployes(List<Employe> listeEmployes) {
		this.listeEmployes = listeEmployes;
	}
	public String getRne() {
		return rne;
	}
	public void setRne(String rne) {
		this.rne = rne;
	}
	public String getSecteurActivite() {
		return secteurActivite;
	}
	public void setSecteurActivite(String secteurActivite) {
		this.secteurActivite = secteurActivite;
	}
	public String getMinistereDeTutuelle() {
		return ministereDeTutuelle;
	}
	public void setMinistereDeTutuelle(String ministereDeTutuelle) {
		this.ministereDeTutuelle = ministereDeTutuelle;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEtatDemande() {
		return etatDemande;
	}
	public void setEtatDemande(String etatDemande) {
		this.etatDemande = etatDemande;
	}
	
}
