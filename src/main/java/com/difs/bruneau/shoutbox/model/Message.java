package com.difs.bruneau.shoutbox.model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "message_table")
public class Message {
	
	@Id
	@GeneratedValue
	@Column(name = "id_message")
	private int idMessage;
	
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "id_pseudo", nullable = false)
	private User idPseudo;
	
    @Basic
    @Column(name = "date_envoi",columnDefinition="Datetime default NOW()")
    private java.sql.Timestamp dateEnvoi;
    
   

	@Column(name = "message_subject")
    private String messageSubject;
    
    @Column(name = "can_report")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean canReport = true;
    
  

	@OneToOne(mappedBy = "message", cascade = { CascadeType.ALL })
    private Report report;
    
	/**
	 * @return the canReport
	 */
	public boolean isCanReport() {
		return canReport;
	}

	/**
	 * @param canReport the canReport to set
	 */
	public void setCanReport(boolean canReport) {
		this.canReport = canReport;
	}

	/**
	 * @return the report
	 */
	public Report getReport() {
		return report;
	}

	/**
	 * @param report the report to set
	 */
	public void setReport(Report report) {
		this.report = report;
	}

	/**
	 * @return the idMessage
	 */
	public int getIdMessage() {
		return idMessage;
	}

	/**
	 * @param idMessage the idMessage to set
	 */
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	/**
	 * @return the idPseudo
	 */
	public User getIdPseudo() {
		return idPseudo;
	}

	/**
	 * @param idPseudo the idPseudo to set
	 */
	public void setIdPseudo(User idPseudo) {
		this.idPseudo = idPseudo;
	}

	/**
	 * @return the dateEnvoi
	 */
	public java.sql.Timestamp getDateEnvoi() {
		return dateEnvoi;
	}

	/**
	 * @param dateEnvoi the dateEnvoi to set
	 */
	public void setDateEnvoi(java.sql.Timestamp dateEnvoi) {
		this.dateEnvoi = dateEnvoi;
	}

	/**
	 * @return the messageSubject
	 */
	public String getMessageSubject() {
		return messageSubject;
	}

	/**
	 * @param messageSubject the messageSubject to set
	 */
	public void setMessageSubject(String messageSubject) {
		this.messageSubject = messageSubject;
	}


    
    
}
