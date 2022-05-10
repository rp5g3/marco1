package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@SequenceGenerator(name="ContatoProcessoTreinamento_SEQ",allocationSize=1,initialValue=1,sequenceName="TrainingContactRequirementSeq")
@Table(name="TrainingContactRequirement")
public class ContatoProcessoTreinamento implements Serializable {

	private static final long serialVersionUID = 124368788458862L;
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ContatoProcessoTreinamento_SEQ")
	@Id
	private int id;

	@Column(nullable = false, length = 10,name="tecnologies")
	private String tecnologias;

	@Column(nullable = false, length = 30, name="details")
	private String detalhes;

	@Column(nullable = false, length = 100, name="contact_name")
	private String requerente;

	@Column(nullable = false, length = 100, name="contact_address_location")
	private String localRealizacao;

	@Column(name="recomendations")
	private String recomendacoes;

	@Column(name="planing_date")
	@Temporal(TemporalType.DATE)
	private Date dataPlanejada;

	@Column(nullable = false,name="desired_part_number")
	private int numeroAlunos;

	@ManyToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="contact_id")
	private ContatoTreinamento contatoTreinamento;


	
	public ContatoTreinamento getContatoTreinamento() {
		return contatoTreinamento;
	}

	public void setContatoTreinamento(ContatoTreinamento comarca) {
		this.contatoTreinamento = comarca;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @spring.validator arg0resource="contatoProcessoTreinamento.tecnologias" maxlength="100"
	 *                   minlength="1" type="required"
	 */
	public void setTecnologias(String tecnologias) {
		this.tecnologias = tecnologias;
	}

	
	public String getTecnologias() {
		return this.tecnologias;
	}


	public void setDetalhes(String processo) {
		this.detalhes = processo;
	}


	public String getDetalhes() {
		return this.detalhes;
	}

	/**
	 * 
	 * @spring.validator arg0resource="contatoProcessoTreinamento.requerente" maxlength="250"
	 *                   minlength="5" type="required"
	 */
	public void setRequerente(String requerente) {
		this.requerente = requerente;
	}

	
	public String getRequerente() {
		return this.requerente;
	}

	/**
	 * 
	 * @spring.validator arg0resource="contatoProcessoTreinamento.localRealizacao" maxlength="100"
	 *                   minlength="5" type="required"
	 */
	public void setLocalRealizacao(String local) {
		this.localRealizacao = local;
	}


	public String getLocalRealizacao() {
		return this.localRealizacao;
	}

	public void setRecomendacoes(String procRequerente) {
		this.recomendacoes = procRequerente;
	}

	public String getRecomendacoes() {
		return this.recomendacoes;
	}


	public void setDataPlanejada(Date procRequerido) {
		this.dataPlanejada = procRequerido;
	}

	public Date getDataPlanejada() {
		return this.dataPlanejada;
	}

	/**
	 * 
	 * @spring.validator arg0resource="contatoProcessoTreinamento.numeroAlunos"
	 *                   type="required,integer"
	 */
	public void setNumeroAlunos(int numAlunos) {
		this.numeroAlunos = numAlunos;
	}


	public int getNumeroAlunos() {
		return this.numeroAlunos;
	}

}