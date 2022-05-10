package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="EnterprisePersonData")
public class Juridica extends TipoPessoa implements Serializable {

	private static final long serialVersionUID = 309882036324745L;
	
	@Column(nullable = true,length=18,name="cnpj")
	@Index(name="CNPJ_index")
	private String cnpj;

	@Column(nullable = true, length = 150,name="activity")
	private String ramoAtividade;

	@Column(nullable = true, length = 20, name="state_inscription")
	private String inscricaoEstadual;

	@Column(nullable = true, length = 20, name="commercial_phone_number")
	private String telefoneComercial;

	@Column(nullable = true, length = 120, name="social_reason")
	private String razaoSocial;

	@ManyToOne(targetEntity = RepresentanteLegal.class, cascade = {CascadeType.PERSIST },fetch = FetchType.LAZY)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="legal_representant_id")
	protected RepresentanteLegal representanteLegal;

	
	public void setRepresentanteLegal(RepresentanteLegal representanteLegal) {
		this.representanteLegal = representanteLegal;
	}


	public RepresentanteLegal getRepresentanteLegal() {
		return this.representanteLegal;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getCnpj() {
		return this.cnpj;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}


	public String getRamoAtividade() {
		return this.ramoAtividade;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}


	public String getInscricaoEstadual() {
		return this.inscricaoEstadual;
	}

	public void setTelefoneComercial(String telefoneComercial) {
		this.telefoneComercial = telefoneComercial;
	}


	public String getTelefoneComercial() {
		return this.telefoneComercial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}


	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	@Transient
	public String getNomeFormatado() {
		return razaoSocial;
	}


}