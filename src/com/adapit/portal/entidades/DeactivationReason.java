package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="DeactivationReason_GEN",allocationSize=1,initialValue=1,sequenceName="UserDeactivationReasonSeq")
@Table(name="UserDeactivationReason")
public class DeactivationReason implements Serializable {

	private static final long serialVersionUID = 92388523462347348L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DeactivationReason_GEN")
	@Id
	private int id;

	@Column(nullable = false, length = 256)
	private String reason;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date date;
	
	@ManyToOne(targetEntity=Usuario.class,cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinColumn(name="user_login")
	private Usuario usuario;

	public void setId(int id) {
		this.id = id;
	}

	
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @spring.validator arg0resource="deactivationReason.reason"
	 *                   maxlength="256" minlength="2" type="required"
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getReason() {
		return this.reason;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Date getDate() {
		return this.date;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}