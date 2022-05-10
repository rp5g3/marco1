package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(
		name="ClienteContaPagar_GEN",		
		allocationSize=1,
		initialValue=1,sequenceName="CustomerPaymentSeq"
)
@Table(name="CustomerPayment")
public class ClienteContaPagar implements Serializable {

	
	private static final long serialVersionUID = 773462357723577L;

	private int id;

	private float valor;

	private Date dataPagto;
	
	private Date dataAgendada;

	private float comissao;

	private float valorTotal;

	private boolean faturaPorSedex;

	private CondicaoPagamento condicaoPagamento;


	protected Participante cliente;
	
	private boolean paga;


	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}


	@ManyToOne(targetEntity = CondicaoPagamento.class,fetch = FetchType.LAZY)
	public CondicaoPagamento getCondicaoPagamento() {
		return this.condicaoPagamento;
	}

	
	public void setCliente(Participante cliente) {
		this.cliente = cliente;
	}

	@Basic(fetch = FetchType.LAZY)
	@ManyToOne(targetEntity = Participante.class, cascade = { CascadeType.REFRESH })
	public Participante getCliente() {
		return this.cliente;
	}

	public void setId(int id) {
		this.id = id;
	}

	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ClienteContaPagar_GEN")
	@Id
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

	public void setComissao(float comissao) {
		this.comissao = comissao;
	}

	public float getComissao() {
		return this.comissao;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public float getValorTotal() {
		return this.valorTotal;
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


	public Date getDataAgendada() {
		return dataAgendada;
	}


	public void setDataAgendada(Date dataAgendada) {
		this.dataAgendada = dataAgendada;
	}

}