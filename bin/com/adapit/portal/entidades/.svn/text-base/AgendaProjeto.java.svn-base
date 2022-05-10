package com.adapit.portal.entidades;


import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(
		name="AgendaProjeto_GEN",		
		allocationSize=1,initialValue=1,sequenceName="SoftwareProjectScheduleSeq" 
)
@Table(name="SoftwareProjectSchedule")
public class AgendaProjeto implements Serializable{
	
	private static final long serialVersionUID = 1977284577632456L;

	@GeneratedValue(strategy=GenerationType.TABLE, generator="AgendaProjeto_GEN")
	@Id
	private int id;
	
	@Column(name="expectedBeginingDate")
	private Date inicioPrevisto;
	
	
	private float lanceInicial;
	
	private float lanceMinimo;
	
	private float incremento;
	
	@Column(name="expectedFinishingDate")
	private Date terminoPrevisto;
	
	@Column(name="startDate")
	private Date inicio;
	
	@Column(name="finishDate")
	private Date termino;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusAgenda status;
	
	@Column(name="confirmed")
	private boolean confirmada=false;
	
	@Column(name="obs")
	private String obs;

	
	@Basic(fetch=FetchType.LAZY)
	@ManyToOne (targetEntity=Projeto.class,cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	private Projeto projeto;
	
	
	
	public void setProjeto(Projeto proj ){
		this.projeto=proj;
	}
	
	
	public Projeto getProjeto(){
		return this.projeto;
	}
		
	public void setId(int id ){
		this.id=id;
	}
	
	
	public int getId(){
		return this.id;
	}
	
	/**
	 * @spring.validator arg0resource="agendaProjeto.inicioPrevisto" type="required,date"
	 */
	public void setInicioPrevisto(Date inicioPrevisto){
		this.inicioPrevisto=inicioPrevisto;
	}
	
	public Date getInicioPrevisto(){
		return this.inicioPrevisto;
	}
	
	/**
	 * @spring.validator arg0resource="agendaProjeto.lanceInicial" type="double"
	 */
	public void setLanceInicial(float lanceInicial ){
		this.lanceInicial=lanceInicial;
	}
	
	public float getLanceInicial(){
		return this.lanceInicial;
	}
	
	/**
	 * @spring.validator arg0resource="agendaProjeto.lanceMinimo" type="double"
	 */
	public void setLanceMinimo(float lanceMinimo ){
		this.lanceMinimo=lanceMinimo;
	}
	
	public float getLanceMinimo(){
		return this.lanceMinimo;
	}
	
	/**
	 * @spring.validator arg0resource="agendaProjeto.terminoPrevisto" type="required,date"
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

	/*public boolean isConfirmada() {
		return confirmada;
	}*/
	
	public boolean getConfirmada() {
		return confirmada;
	}

	public void setConfirmada(boolean confirmada) {
		this.confirmada = confirmada;
	}

	public float getIncremento() {
		return incremento;
	}

	/**
	 * @spring.validator arg0resource="agendaProjeto.incremento" type="double"
	 */
	public void setIncremento(float incremento) {
		this.incremento = incremento;
	}

}