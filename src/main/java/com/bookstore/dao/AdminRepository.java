package com.bookstore.dao;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.entity.Administrateur;


public interface AdminRepository extends CrudRepository<Administrateur, Integer>{

}
