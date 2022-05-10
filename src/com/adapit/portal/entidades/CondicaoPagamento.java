package com.adapit.portal.entidades;


import java.io.Serializable;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(name="Condicao_pagto_seq",allocationSize=1,initialValue=1,sequenceName="PaymentConditionSeq")
@Table(name="PaymentCondition")
public class CondicaoPagamento implements Serializable{
	
	private static final long serialVersionUID = 837613562346L;

	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Condicao_pagto_seq")
	@Id
	private int id;
	
	@Column(name="parcel_number")
	private int numeroPrestacoes=-1;
	
	@Column(name="input_number")
	private int numeroEntradas=-1;
	
	@Column(name="free_mounth_number")
	private int carenciaMeses=-1;
	
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="payment_condition_type")
	private TipoPagamento tipo;
	
	@OneToOne (targetEntity=AgendaTreinamento.class,fetch=FetchType.LAZY)
	@JoinColumn(name="scheduled_training_id")
	private AgendaTreinamento agendaTreinamento;
	
	
	
	public TipoPagamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoPagamento tipo) {
		this.tipo = tipo;
	}

	public void setId(int id ){
		this.id=id;
	}
	

	public int getId(){
		return this.id;
	}

	public int getNumeroPrestacoes() {
		return numeroPrestacoes;
	}

	public void setNumeroPrestacoes(int numeroPrestacoes) {
		this.numeroPrestacoes = numeroPrestacoes;
	}

	public int getNumeroEntradas() {
		return numeroEntradas;
	}

	public void setNumeroEntradas(int numeroEntradas) {
		this.numeroEntradas = numeroEntradas;
	}

	public int getCarenciaMeses() {
		return carenciaMeses;
	}

	public void setCarenciaMeses(int carenciaMeses) {
		this.carenciaMeses = carenciaMeses;
	}

	public AgendaTreinamento getAgendaTreinamento() {
		return agendaTreinamento;
	}

	public void setAgendaTreinamento(AgendaTreinamento agendaLote) {
		this.agendaTreinamento = agendaLote;
	}


}