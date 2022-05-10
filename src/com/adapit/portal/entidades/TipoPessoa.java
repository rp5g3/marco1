package com.adapit.portal.entidades;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@SequenceGenerator(name="TipoPessoa_Gen",allocationSize=1,initialValue=1,sequenceName="PersonTypeSeq")
public abstract class TipoPessoa implements Serializable{
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TipoPessoa_Gen")
	@Id
	private int id;

	@OneToOne (targetEntity=Pessoa.class,cascade={CascadeType.MERGE,CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="person_id")
	public Pessoa pessoa;

	@Transient
	private boolean ignoreValidation = false;
	
	public void setPessoa(Pessoa pessoa ){
		this.pessoa=pessoa;
	}

	
	public Pessoa getPessoa(){
		return this.pessoa;
	}
	
	public void setId(int id ){
		this.id=id;
	}
	
	
	public int getId(){
		return this.id;
	}

	@Transient
	public String getNomeFormatado() {
		return "";
	}


	/**
	 * @return the ignoreValidation
	 */
	public boolean isIgnoreValidation() {
		return ignoreValidation;
	}


	/**
	 * @param ignoreValidation the ignoreValidation to set
	 */
	public void setIgnoreValidation(boolean ignoreValidation) {
		this.ignoreValidation = ignoreValidation;
	}
}