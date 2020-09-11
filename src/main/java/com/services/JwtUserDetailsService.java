package com.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Administrateur;
import com.bookstore.entity.Societe;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private ISocieteService serviceSociete;
	@Autowired
	private IAdminService serviceAdmin;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/*if ("javainusee".equals(username)) {
			return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		*/
		
		System.out.println("wwwwwwwwwwww");
		Societe societe=null;
		Administrateur admin=null;
		 societe = serviceSociete.getByEmail(username);
		System.out.println("aaaaaaaaa");
		 admin= serviceAdmin.getByUserName(username);
		System.out.println("bbbbbbbbbbb");
		if (societe == null && admin==null) {
			System.out.println("11111111");
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		else if(societe!=null) {
			System.out.println("222222");
			System.out.println(societe);
			return new User(username, societe.getMotDePasse(),
					new ArrayList<>());
		}
		
		else if(admin!=null) {
			System.out.println("3333333333");
			return new User(username, admin.getMdp(),
					new ArrayList<>());
		}
			
		else {
			System.out.println("444444444");
			return null;
		}
		
	}
	
	/*Societe societe = serviceSociete.getByEmail(username);
	if (societe == null) {
		throw new UsernameNotFoundException("User not found with username: " + username);
	}
	return new org.springframework.security.core.userdetails.User(societe.getEmail(), societe.getMotDePasse(),
			new ArrayList<>());*/

}