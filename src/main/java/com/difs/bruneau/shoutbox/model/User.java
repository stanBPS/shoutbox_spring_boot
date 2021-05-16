package com.difs.bruneau.shoutbox.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
public class User {
	
	@Id
	@Column(name = "id_pseudo")
	private int idPseudo;
	


	/**
	 * @return the idPseudo
	 */
	public int getIdPseudo() {
		return idPseudo;
	}

	/**
	 * @param idPseudo the idPseudo to set
	 */
	public void setIdPseudo(int idPseudo) {
		this.idPseudo = idPseudo;
	}

	@Column(name = "user_pseudo")
	private String userPseudo;
	
	@Column(name = "user_password")
	private String userPassword;
	
	@ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
	private Role idRole;

	public User() {
		
	}
	

	public User(String userPseudo) {
		this.userPseudo = userPseudo;
	}

	/**
	 * @return the messages
	 */
	public Set<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages the messages to set
	 */
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	/**
	 * @return the idRole
	 */
	public Role getIdRole() {
		return idRole;
	}

	/**
	 * @param idRole the idRole to set
	 */
	public void setIdRole(Role idRole) {
		this.idRole = idRole;
	}

	/**
	 * @return the userPseudo
	 */
	public String getUserPseudo() {
		return userPseudo;
	}

	/**
	 * @param userPseudo the userPseudo to set
	 */
	public void setUserPseudo(String userPseudo) {
		this.userPseudo = userPseudo;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword the userPassword to set
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	@OneToMany(mappedBy = "idPseudo", cascade = { CascadeType.ALL })
    private Set<Message> messages = new HashSet<Message>();
    
    
    /*public void addMessage(Message message) {
    	messages.add(message);      
    	message.setIdPseudo(this);
    }*/
}
