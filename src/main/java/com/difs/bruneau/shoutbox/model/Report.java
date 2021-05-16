package com.difs.bruneau.shoutbox.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "message_report")
public class Report {
	
	@Id
	@Column(name = "id_gestion")
	private int idGestion;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "id_message", referencedColumnName = "id_message")
	private Message message;
	
	@Column(name = "report_subject")
	private String reportSubject;
	
	

	/**
	 * @return the idGestion
	 */
	public int getIdGestion() {
		return idGestion;
	}

	/**
	 * @param idGestion the idGestion to set
	 */
	public void setIdGestion(int idGestion) {
		this.idGestion = idGestion;
	}

	/**
	 * @return the reportSubject
	 */
	public String getReportSubject() {
		return reportSubject;
	}

	/**
	 * @param reportSubject the reportSubject to set
	 */
	public void setReportSubject(String reportSubject) {
		this.reportSubject = reportSubject;
	}
	
	/**
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
	

}
