package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="Access_Gen",allocationSize=1,initialValue=1,sequenceName="UserAccessDataSeq")
@NamedQuery(name="access.listAllByLogin",query="select a from Access a where a.usuario.login=:userlogin order by a.dataHora ASC")
@Table(name="UserAccessData")
public class Access implements Serializable {

	private static final long serialVersionUID = 2346143723582482482L;

	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Access_Gen")
	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false,name="accessTime")
	private Date dataHora;	

	@ManyToOne(targetEntity=Usuario.class,/*cascade={CascadeType.PERSIST,CascadeType.MERGE},*/fetch=FetchType.LAZY)
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
	 * @spring.validator arg0resource="access.data" type="date"
	 */
	public void setDataHora(Date data) {
		this.dataHora = data;
	}
	
	public Date getDataHora() {
		return this.dataHora;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}