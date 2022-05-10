package com.adapit.portal.entidades;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NamedQueries({
	@NamedQuery(name="agendaTreinamento.confirmadaemperiodolances",
		query="from AgendaTreinamento a where" +
		" a.confirmada=true" +
		" and a.inicioPrevisto <= current_timestamp" +
		" and current_timestamp <= a.terminoPrevisto" +
		" and a.recebendoInteressados=false"),
	@NamedQuery(name="agendaTreinamento.confirmaexpiradamasnaoencerrada",
				query="from AgendaTreinamento a where" +
		" a.confirmada=true" +
		" and current_timestamp > a.terminoPrevisto " +
		" and a.encerrada=false")
}
)
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(name="AgendaTreinamento_Gen",allocationSize=1,initialValue=1,sequenceName="ScheduledTrainingSolutionScheduleSeq")
@Table(name="ScheduledTrainingSolutionSchedule")
public class AgendaTreinamento implements Serializable{
	
	private static final long serialVersionUID = 2346143723582482483L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AgendaTreinamento_Gen")
	@Id
	private int id;
	
	@Column(name="expectedBegining")
	@Temporal(TemporalType.DATE)
	private Date inicioPrevisto;
	
/*	@Column(name="initialValue")
	private float lanceInicial;
	
	@Column(name="increment")
	private float incremento;*/
	
	@Column(name="expectedFinishing")
	@Temporal(TemporalType.DATE)
	private Date terminoPrevisto;
	
	@Column(name="beginDate")
	private Date inicio;
	
	@Column(name="finishDate")
	private Date termino;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusAgenda status = StatusAgenda.Não_cadastrada;
	
	@Column(name="confirmed")
	private boolean confirmada=false;
	
	@Column(name="obs")
	private String obs;
	
	@Basic(fetch=FetchType.LAZY)
	@ManyToOne (targetEntity=Treinamento.class,/*cascade={CascadeType.MERGE},*/fetch=FetchType.LAZY)
	@JoinColumn(name="scheduled_training_id")
	private Treinamento treinamento;
	
	@OneToOne (targetEntity=Encerramento.class,mappedBy="agendaTreinamento",fetch=FetchType.LAZY)
	@JoinColumn(name="sched_training_finalization_id")
	private Encerramento encerramento;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=true,name="day_turn")
	private TurnoTreinamento turno;
	
	@Column(name="init_hour",length=5)
	private String horaInicio;
	
	@Column(name="finish_hour",length=5)
	private String horaFim;
	
	@Column(name="participant_join_enabled")
	private boolean recebendoInteressados=false;
	
	@Column(name="finished")
	private boolean encerrada=false;
	
	@OneToOne (targetEntity=CondicaoPagamento.class,mappedBy="agendaTreinamento",fetch=FetchType.LAZY)
	@JoinColumn(name="conditioned_scheduled_training_id")
	private CondicaoPagamento condicaoPagamento;
	
	@Column(name="interval",length=100)
	private String intervalo;

	
	public boolean isEncerrada() {
		return encerrada;
	}

	public void setEncerrada(boolean encerrada) {
		this.encerrada = encerrada;
	}

	
	
	public void setTreinamento(Treinamento treinamento ){
		this.treinamento=treinamento;
	}
	
	
	public Treinamento getTreinamento(){
		return this.treinamento;
	}
	
	public void setEncerramento(Encerramento encerramento ){
		this.encerramento=encerramento;
	}
	
	
	
	public Encerramento getEncerramento(){
		return this.encerramento;
	}
	
	public void setId(int id ){
		this.id=id;
	}
	
	
	public int getId(){
		return this.id;
	}
	
	/**
	 * @spring.validator arg0resource="agendaTreinamento.inicioPrevisto" type="required,date"
	 */
	public void setInicioPrevisto(Date inicioPrevisto){
		this.inicioPrevisto=inicioPrevisto;
	}
	
	public Date getInicioPrevisto(){
		return this.inicioPrevisto;
	}
	
	/**
	 * @spring.validator arg0resource="agendaTreinamento.lanceInicial" type="double"
	 */
/*	public void setLanceInicial(float lanceInicial ){
		this.lanceInicial=lanceInicial;
	}
	
	public float getLanceInicial(){
		return this.lanceInicial;
	}*/
	
	/**
	 * @spring.validator arg0resource="agendaTreinamento.terminoPrevisto" type="required,date"
	 */
	public void setTerminoPrevisto(Date terminoPrevisto ){
		this.terminoPrevisto=terminoPrevisto;
	}
	
	public Date getTerminoPrevisto(){
		return this.terminoPrevisto;
	}
	
	public void setInicio(Date inicio ){
		this.inicio=inicio;
	}
	
	public Date getInicio(){
		return this.inicio;
	}
	

	public void setStatus(StatusAgenda status ){
		this.status=status;
	}
	
	
	public StatusAgenda getStatus(){
		return this.status;
	}
	
	public void setObs(String obs ){
		this.obs=obs;
	}
	
	public String getObs(){
		return this.obs;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}
	
	public boolean isConfirmada() {
		return confirmada;
	}

	public void setConfirmada(boolean confirmada) {
		this.confirmada = confirmada;
	}

/*	public float getIncremento() {
		return incremento;
	}*/

	/**
	 * @spring.validator arg0resource="agendaTreinamento.incremento" type="double"
	 */
/*	public void setIncremento(float incremento) {
		this.incremento = incremento;
	}*/



/*	
	@Transient
	public boolean isInNextValueInterval(float valor, float ultimo) {
		float valorMin = ultimo + incremento;
		if (valor >= valorMin) return true;
		return false;
	}
	
	@Transient
	public boolean isInNextValueIntervalDifferent(float valor, float ultimo) {
		float valorMin = ultimo + incremento;
		if (valor > valorMin) return true;
		return false;
	}*/

	public boolean isRecebendoInteressados() {
		return recebendoInteressados;
	}

	public void setRecebendoInteressados(boolean recebendoLances) {
		this.recebendoInteressados = recebendoLances;
	}



	
	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	/**
	 * @return the chamadaAgenda
	 */
	public TurnoTreinamento getTurno() {
		return turno;
	}

	/**
	 * @param chamadaAgenda the chamadaAgenda to set
	 */
	public void setTurno(TurnoTreinamento chamadaAgenda) {
		this.turno = chamadaAgenda;
	}

	/**
	 * @return the horaInicio
	 */
	public String getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the horaFim
	 */
	public String getHoraFim() {
		return horaFim;
	}

	/**
	 * @param horaFim the horaFim to set
	 */
	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}

	/**
	 * @return the intervalo
	 */
	public String getIntervalo() {
		return intervalo;
	}

	/**
	 * @param intervalo the intervalo to set
	 */
	public void setIntervalo(String intervalo) {
		this.intervalo = intervalo;
	}

}