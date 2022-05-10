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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="TrainingFormationItem_Gen",allocationSize=1,initialValue=1,sequenceName="TrainingFormationItemSeq")
@Table(name="TrainingFormationItem")
public class TrainingFormationItem implements Serializable {

	private static final long serialVersionUID = 8214369923741537L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TrainingFormationItem_Gen")
	@Id
	private int id;

	@Column(nullable = false)
	private int itemOrder;

	@ManyToOne(targetEntity = TrainingSolution.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="training_solution_id")
	protected TrainingSolution trainingSolution;
	
	@ManyToOne(targetEntity = FormacaoTreinamento.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@Column(nullable = true)
	@JoinColumn(name="trainig_formation_id")
	protected FormacaoTreinamento trainingFormation;

	public void setTrainingSolution(TrainingSolution produto) {
		this.trainingSolution = produto;
	}

	
	public TrainingSolution getTrainingSolution() {
		return this.trainingSolution;
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
	public void setItemOrder(int qtd) {
		this.itemOrder = qtd;
	}


	public int getItemOrder() {
		return this.itemOrder;
	}



	public FormacaoTreinamento getTrainingFormation() {
		return trainingFormation;
	}


	/**
	 * @param formacao the formacao to set
	 */
	public void setTrainingFormation(FormacaoTreinamento formacao) {
		this.trainingFormation = formacao;
	}

}