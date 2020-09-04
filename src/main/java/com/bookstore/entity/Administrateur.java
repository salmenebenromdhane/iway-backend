package com.bookstore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Administrateur {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	private String login;
	private String mdp;
	public Administrateur(){
		
	}
	public Administrateur(String login, String mdp) {
	
		this.login = login;
		this.mdp = mdp;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
