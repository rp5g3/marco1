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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(name="Encerramento_Gen",allocationSize=1,initialValue=1,sequenceName="ScheduledTrainingFinalizationSeq")
@Table(name="ScheduledTrainingFinalization")
public class Encerramento implements Serializable{
	private static final long serialVersionUID = 923834734863658L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Encerramento_Gen")
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="finalization_date")
	private Date data;
	
	@Column(name="has_condition")
	private boolean condicionado=false;

	
	@OneToOne (targetEntity=AgendaTreinamento.class/*,cascade={CascadeType.MERGE}*/,fetch=FetchType.LAZY)
	@JoinColumn(name="shedule_id")
	private AgendaTreinamento agendaTreinamento;
	
	@OneToOne (targetEntity=EncerramentoCondicionado.class,cascade={CascadeType.REMOVE},fetch=FetchType.LAZY)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="finalization_condition_id")
	private EncerramentoCondicionado encerrCond;
	

	
	public void setAgendaTreinamento(AgendaTreinamento agenda ){
		this.agendaTreinamento=agenda;
	}
	
	public AgendaTreinamento getAgendaTreinamento(){
		return this.agendaTreinamento;
	}
	
	public void setEncerrCond(EncerramentoCondicionado encerrCond ){
		this.encerrCond=encerrCond;
	}
		
	public EncerramentoCondicionado getEncerrCond(){
		return this.encerrCond;
	}
	
	public void setId(int id ){
		this.id=id;
	}
	

	public int getId(){
		return this.id;
	}
	
	public void setData(Date data ){
		this.data=data;
	}
	
	public Date getData(){
		return this.data;
	}
	
	public void setCondicionado(boolean condicionado ){
		this.condicionado=condicionado;
	}
	
	public boolean getCondicionado(){
		return this.condicionado;
	}



}