package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(	name="ParticipanteContaPagar_GEN",	sequenceName="ParticipantPaymentSeq",allocationSize=1,initialValue=1)
@Table(name="ParticipantPayment")
public class ParticipanteContaPagar implements Serializable {

	private static final long serialVersionUID = 2436234235725L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ParticipanteContaPagar_GEN")
	@Id
	private int id;

	@Column(name="value")
	private float valor;

	@Temporal(TemporalType.DATE)
	@Column(name="payment_date")
	private Date dataPagto;
	
	@Temporal(TemporalType.DATE)
	@Column(name="expiration_date")
	private Date dataVencimento;

	@Column(name="send_relatori_by_sedex")
	private boolean faturaPorSedex;
	
	@Column(name="send_banck_payment_document")
	private boolean boletoBancario;

	@ManyToOne(targetEntity = CondicaoPagamento.class,fetch = FetchType.LAZY)
	@JoinColumn(name="payment_condition_id")
	private CondicaoPagamento condicaoPagamento;
	
	@ManyToOne(targetEntity = AgendaTreinamento.class,fetch = FetchType.LAZY)
	@JoinColumn(name="schedule_id")
	private AgendaTreinamento agenda;

	@Basic(fetch = FetchType.LAZY)
	@ManyToOne(targetEntity = Participante.class, cascade = { CascadeType.REFRESH })
	@JoinColumn(name="client_id")
	private Participante cliente;
	
	@Column(name="payment_done")
	private boolean paga;
	
	@Column(name="rejected_by_client")
	private boolean desitenciaArrematante;
	
	@Column(name="payment_kind")
	private FormaPagamento formaPagamento;


	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}


	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}


	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}



	public CondicaoPagamento getCondicaoPagamento() {
		return this.condicaoPagamento;
	}

	
	public void setCliente(Participante cliente) {
		this.cliente = cliente;
	}


	public Participante getCliente() {
		return this.cliente;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getId() {
		return this.id;
	}

	public void setValor(float valorLotes) {
		this.valor = valorLotes;
	}

	public float getValor() {
		return this.valor;
	}

	public void setDataPagto(Date dataPagto) {
		this.dataPagto = dataPagto;
	}


	public Date getDataPagto() {
		return this.dataPagto;
	}

	public void setFaturaPorSedex(boolean faturaPorSedex) {
		this.faturaPorSedex = faturaPorSedex;
	}

	public boolean getFaturaPorSedex() {
		return this.faturaPorSedex;
	}


	public boolean isPaga() {
		return paga;
	}

	public void setPaga(boolean paga) {
		this.paga = paga;
	}


	public Date getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(Date dataAgendada) {
		this.dataVencimento = dataAgendada;
	}


	public boolean isBoletoBancario() {
		return boletoBancario;
	}


	public void setBoletoBancario(boolean boletoBancario) {
		this.boletoBancario = boletoBancario;
	}



	public AgendaTreinamento getAgenda() {
		return agenda;
	}


	public void setAgenda(AgendaTreinamento agenda) {
		this.agenda = agenda;
	}


	public boolean isDesitenciaArrematante() {
		return desitenciaArrematante;
	}


	public void setDesitenciaArrematante(boolean cancelada) {
		this.desitenciaArrematante = cancelada;
	}

}