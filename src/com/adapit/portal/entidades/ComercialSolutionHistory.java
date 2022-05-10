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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(name="Hist_ComSolGen",allocationSize=1,initialValue=1,sequenceName="CommercialSolutionHistorySeq")
@Table(name="CommercialSolutionHistory")
public class ComercialSolutionHistory implements Serializable{
	private static final long serialVersionUID = 88993846821477L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Hist_ComSolGen")
	@Id
	private int id;
	
	@OneToOne (targetEntity=ComercialSolution.class,cascade={CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="commercial_solution_id")
	private ComercialSolution comercialSolution;
	
	@Column(name="modification_date")
	private Date alteracao;	

	@Column(name="description")
	private String descricao;
	
	@OneToOne (targetEntity=Treinamento.class,cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="scheduled_training_id")
	private Treinamento treinamento;
	
	@OneToOne (targetEntity=TurmaTreinamento.class
			,cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="class_training_id")
	private TurmaTreinamento turma;
	
	@OneToOne (targetEntity=Participante.class
			,cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="training_author_id")
	private Participante arrematante;
	
	@OneToOne (targetEntity=PessoaEmDivulgacao.class
			,cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="general_person_id")
	private PessoaEmDivulgacao pessoaEmDivulgacao;
	
	@Column(name="item_quant")
	private int qtdItem;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public ComercialSolution getComercialSolution() {
		return comercialSolution;
	}

	public void setComercialSolution(ComercialSolution produto) {
		this.comercialSolution = produto;
	}

	public Date getAlteracao() {
		return alteracao;
	}

	public void setAlteracao(Date alteracao) {
		this.alteracao = alteracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descicao) {
		this.descricao = descicao;
	}

	
	public Treinamento getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(Treinamento trein) {
		this.treinamento = trein;
	}

	
	public TurmaTreinamento getTurma() {
		return turma;
	}

	public void setTurma(TurmaTreinamento turma) {
		this.turma = turma;
	}

	
	public Participante getArrematante() {
		return arrematante;
	}

	public void setArrematante(Participante arrematante) {
		this.arrematante = arrematante;
	}

	
	public PessoaEmDivulgacao getComitente() {
		return pessoaEmDivulgacao;
	}

	public void setComitente(PessoaEmDivulgacao pessoaEmDivulgacao) {
		this.pessoaEmDivulgacao = pessoaEmDivulgacao;
	}

	public int getQtdItem() {
		return qtdItem;
	}

	public void setQtdItem(int qtdItem) {
		this.qtdItem = qtdItem;
	}
	
}
