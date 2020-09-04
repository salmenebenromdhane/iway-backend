package com.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dao.AdminRepository;
import com.bookstore.entity.Administrateur;
import com.bookstore.entity.Societe;

@Service
public class AdminService implements IAdminService {
	@PersistenceContext
    private EntityManager em;
	@Autowired
	private AdminRepository repo;
	@Override
	public Administrateur login(String email, String mdp) {
		Administrateur admin=null;
		try{admin = em.createQuery("select u from Administrateur u where u.login='" + email+"' and u.mdp='"+mdp+"'", Administrateur.class)
				.getSingleResult();

	} catch (javax.persistence.PersistenceException k) {
		System.out.println("exception");
		return null;
		
	}
		return admin;
	}

}
