package com.difs.bruneau.shoutbox.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class Role {

	@Id
	@Column(name = "id_role")
	private int idRole;
	
	
	@Column(name = "libelle_role")
	private String libelleRole;


	
	@OneToMany(mappedBy = "idRole", cascade = { CascadeType.ALL })
    private Set<User> users = new HashSet<User>();
	
	public void addUser(User user) {
		users.add(user);      
		user.setIdRole(this);
    }
	
	/**
	 * @return the idRole
	 */
	public int getIdRole() {
		return idRole;
	}


	/**
	 * @param idRole the idRole to set
	 */
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}


	/**
	 * @return the libelleRole
	 */
	public String getLibelleRole() {
		return libelleRole;
	}


	/**
	 * @param libeeleRole the libeeleRole to set
	 */
	public void setLibeleRole(String libeleRole) {
		this.libelleRole = libeleRole;
	}
	
	
	
}
