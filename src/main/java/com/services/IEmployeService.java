package com.services;

import com.bookstore.entity.Employe;

public interface IEmployeService {
	public Employe ajouterEmploye(Employe e);
	public Employe TrouverResponsable(int id);
}
