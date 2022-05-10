package com.adapit.portal.entidades;

import java.io.Serializable;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="ComercialSolutionItem_Gen",allocationSize=1,initialValue=1,sequenceName="ParticTraiSubsItemSeq")
@Table(name="ParticipantTrainingSubscriptionItem")
public class ComercialSolutionItem implements Serializable {

	private static final long serialVersionUID = 283869394745458L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ComercialSolutionItem_Gen")
	@Id
	private int id;

	@Column(nullable = true,name="discount_taxe")
	private int desconto;

	@Column(nullable = false, name="accorded_value")
	private float valorAcertado;

	@ManyToOne(targetEntity = Treinamento.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="scheduled_training_id")
	protected Treinamento treinamento;
	
	@ManyToOne(targetEntity = Participante.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="participant_id")
	protected Participante inscrito;
	
	@ManyToOne(targetEntity = TrainingSolution.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="training_solution_id")
	protected TrainingSolution trainingSolution;
	
	@OneToOne
	@JoinColumn(name="payment_condition_id")
	private CondicaoPagamento condicaoPagto;
	
	@Column(name="confirmed")
	private boolean confirmada=false;
	
	@Column(name="payment_has_done")
	private boolean quitada=false;

	public void setTreinamento(Treinamento t) {
		this.treinamento = t;
	}

	
	public Treinamento getTreinamento() {
		return this.treinamento;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @spring.validator arg0resource="comercialSolutionItem.qtd" type="required,integer"
	 */
	public void setDesconto(int qtd) {
		this.desconto = qtd;
	}

	
	public int getDesconto() {
		return this.desconto;
	}

	/**
	 * 
	 * @spring.validator arg0resource="comercialSolutionItem.valorTotal"
	 *                   type="required,float"
	 */
	public void setValorAcertado(float valorTotal) {
		this.valorAcertado = valorTotal;
	}

	
	public float getValorAcertado() {
		return this.valorAcertado;
	}



	public Participante getInscrito() {
		return inscrito;
	}


	/**
	 * @param inscrito the inscrito to set
	 */
	public void setInscrito(Participante inscrito) {
		this.inscrito = inscrito;
	}



	/**
	 * @return the condicaoPagto
	 */
	public CondicaoPagamento getCondicaoPagto() {
		return condicaoPagto;
	}



	/**
	 * @param condicaoPagto the condicaoPagto to set
	 */
	public void setCondicaoPagto(CondicaoPagamento condicaoPagto) {
		this.condicaoPagto = condicaoPagto;
	}


	/**
	 * @return the confirmada
	 */
	public boolean isConfirmada() {
		return confirmada;
	}


	/**
	 * @param confirmada the confirmada to set
	 */
	public void setConfirmada(boolean confirmada) {
		this.confirmada = confirmada;
	}


	/**
	 * @return the quitada
	 */
	public boolean isQuitada() {
		return quitada;
	}


	/**
	 * @param quitada the quitada to set
	 */
	public void setQuitada(boolean quitada) {
		this.quitada = quitada;
	}


	/**
	 * @return the trainingSolution
	 */
	public TrainingSolution getTrainingSolution() {
		return trainingSolution;
	}


	/**
	 * @param trainingSolution the trainingSolution to set
	 */
	public void setTrainingSolution(TrainingSolution trainingSolution) {
		this.trainingSolution = trainingSolution;
	}


}