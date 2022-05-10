package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="TreinamentoAgendado_Gen",allocationSize=1,initialValue=1,sequenceName="ScheduledTrainingSeq")
@Table(name="ScheduledTraining")
public class Treinamento implements Serializable {

	@Transient
	private static final long serialVersionUID = 8877374868346L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TreinamentoAgendado_Gen")
	@Id
	private int id;
	
	@Column(nullable=true,length=150,name="abstract")
	private String resumo;

	@Column(nullable = false,name="gain")
	private float receita;
	
	@Column(name="cost")
	private float custo;

	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private ScheduledTrainingStatus status = ScheduledTrainingStatus.Em_planejamento;

	@Column(nullable = false, length = 20,name="scheduled_training_code")
	private String codigo;

	@OneToMany(targetEntity = ComercialSolutionItem.class, mappedBy = "treinamento", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	protected List<ComercialSolutionItem> comercialSolutionItens = new ArrayList<ComercialSolutionItem>();
	
	@ManyToOne(targetEntity = TurmaTreinamento.class,  fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name="class_training_id")
	private TurmaTreinamento turma;
	
	@OneToMany(mappedBy="treinamento",fetch=FetchType.LAZY,cascade={CascadeType.REFRESH,CascadeType.REMOVE})
	protected Collection<AgendaTreinamento> agendas = new ArrayList<AgendaTreinamento>();
	
	@OneToOne (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="schedule_id")
	protected AgendaTreinamento agendaTreinamento;
	
	@ManyToMany (fetch=FetchType.LAZY,targetEntity=Participante.class,cascade={CascadeType.MERGE})
	@JoinTable(name="PARTICIPANT_TRAINING"
		,joinColumns={@JoinColumn(name="SCHED_TRAINING_ID")}
		,inverseJoinColumns={@JoinColumn(name="PARTICIPANT_ID")}	
	)
	protected Collection<Participante> interessados = new ArrayList<Participante>();

	@ManyToOne
	@JoinColumn(name="training_solution_id")
	protected TrainingSolution treinamento;
	
	public void setComercialSolutionItens(List<ComercialSolutionItem> itensProduto) {
		this.comercialSolutionItens = itensProduto;
	}
	
	public List<ComercialSolutionItem> getComercialSolutionItens() {
		return this.comercialSolutionItens;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getId() {
		return this.id;
	}


	public void setReceita(float avaliacao) {
		this.receita = avaliacao;
	}

	public float getReceita() {
		return this.receita;
	}

	public void setStatus(ScheduledTrainingStatus status) {
		this.status = status;
	}


	public ScheduledTrainingStatus getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @spring.validator arg0resource="treinamento.codigo" maxlength="20" minlength="6"
	 *                   type="required"
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getCodigo() {
		return this.codigo;
	}



	
	
	public TurmaTreinamento getTurma() {
		return turma;
	}

	public void setTurma(TurmaTreinamento turma) {
		this.turma = turma;
	}


	
	
	public Collection<AgendaTreinamento> getAgendas() {
		return agendas;
	}

	public void setAgendas(Collection<AgendaTreinamento> agendas) {
		this.agendas = agendas;
	}

	
	public AgendaTreinamento getAgendaTreinamento() {
		return agendaTreinamento;
	}

	public void setAgendaTreinamento(AgendaTreinamento agendaLote) {
		this.agendaTreinamento = agendaLote;
	}


	public Collection<Participante> getInteressados() {
		return interessados;
	}

	public void setInteressados(Collection<Participante> interessados) {
		this.interessados = interessados;
	}
	
	
	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	/**
	 * @return the custo
	 */
	public float getCusto() {
		return custo;
	}

	/**
	 * @param custo the custo to set
	 */
	public void setCusto(float custo) {
		this.custo = custo;
	}

	/**
	 * @return the treinamento
	 */
	public TrainingSolution getTreinamento() {
		return treinamento;
	}

	/**
	 * @param treinamento the treinamento to set
	 */
	public void setTreinamento(TrainingSolution treinamento) {
		this.treinamento = treinamento;
	}
	
}