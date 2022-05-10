package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="SoftwareDomainInterest_Gen",allocationSize=1,initialValue=1,sequenceName="SoftwareDomainInterestSeq")
@Table(name="SoftwareDomainInterest")
public class SoftwareDomainInterest implements Serializable {

	private static final long serialVersionUID = 345724234643535L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SoftwareDomainInterest_Gen")
	@Id
	private int id;


	@ManyToMany(targetEntity = SoftwareSolution.class, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	protected List<SoftwareSolution> softwares = new ArrayList<SoftwareSolution>();
	
	@Column(name="formation_name")
	private String nome;
	
	@Column(length=5000)
	private String descricao;

	public void setId(int id) {
		this.id = id;
	}

	
	public int getId() {
		return this.id;
	}



	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	public List<SoftwareSolution> getSoftwares() {
		return softwares;
	}


	public void setSoftwares(List<SoftwareSolution> softwares) {
		this.softwares = softwares;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String desc) {
		this.descricao = desc;
	}


	
}