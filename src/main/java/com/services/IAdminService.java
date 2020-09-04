package com.services;

import com.bookstore.entity.Administrateur;

public interface IAdminService {

	public Administrateur login(String email,String mdp);
}
