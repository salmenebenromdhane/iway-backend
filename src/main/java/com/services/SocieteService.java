package com.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bookstore.dao.SocieteRepository;
import com.bookstore.entity.Societe;

@Service
public class SocieteService implements ISocieteService {
	@PersistenceContext
    private EntityManager em;
	@Autowired
	private SocieteRepository repo;

 
	@Override
	public Societe ajouterSociete(Societe s) {
		return repo.save(s);
	}
	@Override
	public Societe TrouverSociete(int id) {
		return repo.findOne(id);
	}
	@Override
	public Societe login(String email, String rne) {
		Societe societe=null;
		try{societe = em.createQuery("select u from Societe u where u.email='" + email+"' and u.motDePasse='"+rne+"'", Societe.class)
				.getSingleResult();

	} catch (javax.persistence.PersistenceException k) {
		return null;
	}
		return societe;
	}
	@Override
	public Societe modifierSociete(Societe s) {
		Societe societe=null;
		try{societe = em.createQuery("select u from Societe u where u.rne='" + s.getRne()+"'", Societe.class)
				.getSingleResult();

	} catch (javax.persistence.PersistenceException k) {
		return null;
	}
		societe.setNbEffectifMobilise(s.getNbEffectifMobilise());
		societe.setEtatDemande("En cours");
		return repo.save(societe);
	}
	@Override
	public List<Societe> DemandesEncours() {
		List<Societe> list=new ArrayList<>();
		try{list = em.createQuery("select u from Societe u where u.etatDemande='En cours'", Societe.class).getResultList();
	} catch (javax.persistence.PersistenceException k) {
		return list;
	}
				return list;
	}
	@Override
	public Societe repondreDemande(Societe s,String reponse) {
		s.setEtatDemande(reponse);
	
		return repo.save(s);
	}
	@Override
	public Societe ajoutPassword(Societe s,String pwd) {
	
		
		Societe societe=null;
		try{societe = em.createQuery("select u from Societe u where u.rne='" + s.getRne()+"'", Societe.class)
				.getSingleResult();
		
	} catch (javax.persistence.PersistenceException k) {
		return null;
	}
		societe.setNbEffectifMobilise(s.getNbEffectifMobilise());
		societe.setMotDePasse(pwd);
		System.out.println("ssssssssssss");
		   System.out.println(societe.getMotDePasse());
		return repo.save(societe);
	}
	@Override
	public Societe getByEmail(String email) {
		Societe societe=null;
		try{societe = em.createQuery("select u from Societe u where u.email='" +email+"'", Societe.class)
				.getSingleResult();

	} catch (javax.persistence.PersistenceException k) {
		return null;
	}
		return societe;
	}
	

}
