package com.adapit.portal.entidades;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(name="EncerramentoCondicionado_GEN",sequenceName="CondSchedTrainingFinalizationSeq",allocationSize=1,initialValue=1)
@Table(name="ConditionedScheduledTrainingFinalization")
public class EncerramentoCondicionado implements Serializable{
	
	private static final long serialVersionUID = 987868243699882346L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EncerramentoCondicionado_GEN")
	@Id
	private int id;
	
	@Column(name="approved")
	private boolean aprovado;
	
	@Column(name="approved_value")
	private float valorAprovado;


	
	public void setId(int id ){
		this.id=id;
	}
	
	
	public int getId(){
		return this.id;
	}
	
	public void setAprovado(boolean aprovado ){
		this.aprovado=aprovado;
	}
	
	public boolean getAprovado(){
		return this.aprovado;
	}
	
	public void setValorAprovado(float valorAprovado ){
		this.valorAprovado=valorAprovado;
	}
	
	public float getValorAprovado(){
		return this.valorAprovado;
	}



}