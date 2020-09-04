package com.bookstore.dao;

import org.springframework.data.repository.CrudRepository;

import com.bookstore.entity.Employe;


public interface EmployeRepository extends CrudRepository<Employe, Integer>{

}
