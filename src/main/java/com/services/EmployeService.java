package com.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.dao.EmployeRepository;
import com.bookstore.entity.Employe;
import com.bookstore.entity.Societe;


@Service
public class EmployeService implements IEmployeService{
	@PersistenceContext
    private EntityManager em;
	@Autowired
	private EmployeRepository repo;

	

	@Override
	public Employe ajouterEmploye(Employe e) {
		return repo.save(e);
	}



	@Override
	public Employe TrouverResponsable(int id) {
		Employe emp=null;
		try{emp = em.createQuery("select u from Employe u where u.societe="+id+" and u.role='Responsable'", Employe.class)
				.getSingleResult();

	} catch (javax.persistence.PersistenceException k) {
		return emp;
	}
		return emp;
	}
	
}
