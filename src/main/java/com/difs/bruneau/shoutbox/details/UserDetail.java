package com.difs.bruneau.shoutbox.details;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.difs.bruneau.shoutbox.model.User;

/**
 * Création du model UserDetail 
 * @author stanb
 *
 */
public class UserDetail implements org.springframework.security.core.userdetails.UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	
	
	public UserDetail(User user) {
		this.user = user;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getIdRole().getLibelleRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		return user.getUserPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserPseudo();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
