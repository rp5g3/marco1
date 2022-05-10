package com.adapit.portal.entidades;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(name="TurmaTreinamento_Gen",allocationSize=1,initialValue=1,sequenceName="TrainingClassSeq")
@Table(name="TrainingClass")
public class TurmaTreinamento implements Serializable{
	
	private static final long serialVersionUID = 9299496883246L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TurmaTreinamento_Gen")
	@Id
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="begining")
	private Date dataTreinamento;
	
	@Column(nullable=true,name="finishing")
	private Date dataEncerramento;
		
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=true,name="training_spec_kind")
	private TipoPacoteTreinamento subClassificacao;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=true,name="classification")
	private ClassificacaoTreinamento classificacao;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=true,name="execution_type")
	private TipoExecucaoTreinamento tipoExecucao;	
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=true,name="day_turn")
	private TurnoTreinamento turno = TurnoTreinamento.Manhã;
	
	
	@Basic(fetch=FetchType.LAZY)
	@OneToMany (targetEntity=Treinamento.class, mappedBy="turma", cascade = CascadeType.MERGE)
	private Collection<Treinamento> treinamentos = new ArrayList<Treinamento>();
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE})
	@JoinColumn(name="condition_id")
	private CondicaoParticipacaoTreinamento condicaoParticipacao;		

	
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE})
	@JoinColumn(name="location_address_id")
	private Endereco enderecoPresencial;
	
	@ManyToOne(targetEntity=Instrutor.class,cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name="training_tutor_id")
	private Instrutor instrutor;	

	@Basic(fetch=FetchType.LAZY)
	@ManyToOne (targetEntity=ContatoTreinamento.class,cascade={CascadeType.MERGE})
	@JoinColumn(name="client_id")
	protected ContatoTreinamento comitente;

	@Basic(fetch=FetchType.LAZY)
	@OneToMany (targetEntity=ParticipacaoTurma.class,mappedBy="turma",cascade=CascadeType.REMOVE)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Collection<ParticipacaoTurma> participacoes;
	
	@OneToOne(targetEntity=ContatoProcessoTreinamento.class,fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="training_contact_requirement_id")
	private ContatoProcessoTreinamento processo;
	
	@Column(name="participnat_instructions",length=500)
	private String instrucoesParticipantes;
	
	@Column(name="participant_min_number")
	private int numeroMinimoParticipantes;
	
	@Column(name="participant_max_number")
	private int numeroMaximoParticipantes;
	
	@ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name="training_class_image_id")
	private Imagem imagem;
	
	@ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name="training_formation_id")
	private FormacaoTreinamento formacao;



	public TurnoTreinamento getTurno() {
		return turno;
	}

	public void setTurno(TurnoTreinamento regraComitenteLeilao) {
		this.turno = regraComitenteLeilao;
	}

	public void setComitente(ContatoTreinamento comitente ){
		this.comitente=comitente;
	}
	

	public ContatoTreinamento getComitente(){
		return this.comitente;
	}
	
	public void setParticipacoes( Collection<ParticipacaoTurma> participacoes ){
		this.participacoes=participacoes;
	}
	

	public Collection<ParticipacaoTurma> getParticipacoes(){
		return this.participacoes;
	}
	
	public void setId(int id ){
		this.id=id;
	}
	

	public int getId(){
		return this.id;
	}

	

	public void setDataTreinamento(Date dataPresencialPrimeira ){
		this.dataTreinamento=dataPresencialPrimeira;
	}
	

	public Date getDataTreinamento(){
		return this.dataTreinamento;
	}

	public void setSubClassificacao(TipoPacoteTreinamento tipo ){
		this.subClassificacao=tipo;
	}

	public TipoPacoteTreinamento getSubClassificacao(){
		return this.subClassificacao;
	}
	
	public void setClassificacao(ClassificacaoTreinamento procedencia ){
		this.classificacao=procedencia;
	}
	

	public ClassificacaoTreinamento getClassificacao(){
		return this.classificacao;
	}
	
	public void setTipoExecucao(TipoExecucaoTreinamento tipoVenda ){
		this.tipoExecucao=tipoVenda;
	}
	

	public TipoExecucaoTreinamento getTipoExecucao(){
		return this.tipoExecucao;
	}


	public Collection<Treinamento> getTreinamentos() {
		return treinamentos;
	}

	public void setTreinamentos(Collection<Treinamento> treinamentos) {
		this.treinamentos = treinamentos;
	}


	public CondicaoParticipacaoTreinamento getCondicaoParticipacao() {
		return condicaoParticipacao;
	}

	public void setCondicaoParticipacao(
			CondicaoParticipacaoTreinamento condicaoParticipacao) {
		this.condicaoParticipacao = condicaoParticipacao;
	}


	public Endereco getEnderecoPresencial() {
		return enderecoPresencial;
	}

	public void setEnderecoPresencial(Endereco enderecoPresencial) {
		this.enderecoPresencial = enderecoPresencial;
	}


	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor leiloeiro) {
		this.instrutor = leiloeiro;
	}


	public ContatoProcessoTreinamento getProcesso() {
		return processo;
	}

	public void setProcesso(ContatoProcessoTreinamento processo) {
		this.processo = processo;
	}

	/**
	 * @return the dataEncerramento
	 */
	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	/**
	 * @param dataEncerramento the dataEncerramento to set
	 */
	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	/**
	 * @return the instrucoesParticipantes
	 */
	public String getInstrucoesParticipantes() {
		return instrucoesParticipantes;
	}

	/**
	 * @param instrucoesParticipantes the instrucoesParticipantes to set
	 */
	public void setInstrucoesParticipantes(String instrucoesParticipantes) {
		this.instrucoesParticipantes = instrucoesParticipantes;
	}

	/**
	 * @return the numeroMinimoParticipantes
	 */
	public int getNumeroMinimoParticipantes() {
		return numeroMinimoParticipantes;
	}

	/**
	 * @param numeroMinimoParticipantes the numeroMinimoParticipantes to set
	 */
	public void setNumeroMinimoParticipantes(int numeroMinimoParticipantes) {
		this.numeroMinimoParticipantes = numeroMinimoParticipantes;
	}

	/**
	 * @return the numeroMaximoParticipantes
	 */
	public int getNumeroMaximoParticipantes() {
		return numeroMaximoParticipantes;
	}

	/**
	 * @param numeroMaximoParticipantes the numeroMaximoParticipantes to set
	 */
	public void setNumeroMaximoParticipantes(int numeroMaximoParticipantes) {
		this.numeroMaximoParticipantes = numeroMaximoParticipantes;
	}


	public Imagem getImagem() {
		return imagem;
	}

	/**
	 * @param imagem the imagem to set
	 */
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}


	public FormacaoTreinamento getFormacao() {
		return formacao;
	}

	/**
	 * @param formacao the formacao to set
	 */
	public void setFormacao(FormacaoTreinamento formacao) {
		this.formacao = formacao;
	}


	
}