package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name="ContatoTreinamento_SEQ",sequenceName="TrainingContactSeq",allocationSize=1,initialValue=1)
@Table(name="TrainingContact")
public class ContatoTreinamento implements Serializable{

	private static final long serialVersionUID = 8735817859325L;


	@Id
	@GeneratedValue(generator="ContatoTreinamento_SEQ",strategy=GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="contact_name")
	private String nome;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="address_id")
	private Endereco endereco;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="contatoTreinamento")
	private Collection<ContatoProcessoTreinamento> processos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	
	
	/**
	 * 
	 * @spring.validator arg0resource="contatoTreinamento.nome" type="required"
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Collection<ContatoProcessoTreinamento> getProcessos() {
		return processos;
	}

	public void setProcessos(Collection<ContatoProcessoTreinamento> processos) {
		this.processos = processos;
	}
}
