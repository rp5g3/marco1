package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="FisicPersonData")
public class Fisica extends TipoPessoa implements Serializable {

	private static final long serialVersionUID = 923845677723647L;
	
	@Column(nullable = true,length=14,name="cpf_person_data")
	@Index(name="CPF_Index")
	private String cpf;

	@Column(nullable = true,length=15,name="rg_person_data")
	@Index(name="RG_Index")
	private String rg;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true,name="bourning_date")
	private Date dataNascimento;

	@Column(nullable = true, length = 50,name="profession")
	private String profissao;

	@Column(name="cell_phone_number")
	private String celular;

	@Column(nullable = true, length = 50,name="complementar_name")
	private String sobrenome;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="civil_state")
	private EstadoCivil estadoCivil;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="person_sexual_type")
	private Sexo sexo=Sexo.Masculino;
	

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getCpf() {
		return this.cpf;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}



	public String getRg() {
		return this.rg;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public Date getDataNascimento() {
		return this.dataNascimento;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}


	public String getProfissao() {
		return this.profissao;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}


	public String getSobrenome() {
		return this.sobrenome;
	}

	public void setEstadoCivil(EstadoCivil estadoCivil) {
		this.estadoCivil = estadoCivil;
	}


	public EstadoCivil getEstadoCivil() {
		return this.estadoCivil;
	}


	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Transient
	public String getNomeFormatado() {
		return sobrenome;
	}



}