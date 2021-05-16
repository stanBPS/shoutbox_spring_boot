package com.difs.bruneau.shoutbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.difs.bruneau.shoutbox.details.UserDetail;
import com.difs.bruneau.shoutbox.model.User;
import com.difs.bruneau.shoutbox.repository.UserRepository;

/**
 * Création de notre propre user service 
 * @author stanb
 *
 */
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository user_repository;

	/**
	 * Override la methode loadUserByUsername on recupere l'object User et si il existe on le met dans un UserDetail
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = user_repository.findByUserPseudo(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("Pseudo introuvable");
		}
		return new UserDetail(user);
	}

}
