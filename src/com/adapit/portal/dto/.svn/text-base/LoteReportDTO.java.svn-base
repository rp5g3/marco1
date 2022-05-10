package com.adapit.portal.dto;

import java.io.Serializable;
import java.util.Date;

import com.adapit.portal.entidades.StatusAgenda;


public class LoteReportDTO implements Serializable {

	private static final long serialVersionUID = 984868927466723724L;
	private Date inicioPrimeiro, fimPrimeiro;
	private Date inicioSegundo, fimSegundo;
	private StatusAgenda status;
	private int numeroLances;
	private float lanceVencendo;
	private String codigo;
	private float lanceInicial;
	private float incremento;
	private boolean loteSelecionado=false;
	private int idLote;
	private boolean leilaoIniciou=false;
	private long idParticipante;
	private int idAgenda;
	//private float proximoLance;

	public float getLanceInicial() {
		return lanceInicial;
	}

	public void setLanceInicial(float lanceInicial) {
		this.lanceInicial = lanceInicial;
	}

	public float getIncremento() {
		return incremento;
	}

	public void setIncremento(float incremento) {
		this.incremento = incremento;
	}

	public float getProximoLance() {
		if (lanceVencendo > 0.0f){
			return lanceVencendo +incremento;
		}else{
			return lanceInicial +incremento;
		}
	}



	public Date getInicioPrimeiro() {
		return inicioPrimeiro;
	}

	public void setInicioPrimeiro(Date inicioPrimeiro) {
		this.inicioPrimeiro = inicioPrimeiro;
	}

	public Date getFimPrimeiro() {
		return fimPrimeiro;
	}

	public void setFimPrimeiro(Date fimPrimeiro) {
		this.fimPrimeiro = fimPrimeiro;
	}

	public Date getInicioSegundo() {
		return inicioSegundo;
	}

	public void setInicioSegundo(Date inicioSegundo) {
		this.inicioSegundo = inicioSegundo;
	}

	public Date getFimSegundo() {
		return fimSegundo;
	}

	public void setFimSegundo(Date fimSegundo) {
		this.fimSegundo = fimSegundo;
	}

	public StatusAgenda getStatus() {
		return status;
	}

	public void setStatus(StatusAgenda status) {
		this.status = status;
	}

	public int getNumeroLances() {
		return numeroLances;
	}

	public void setNumeroLances(int numeroLances) {
		this.numeroLances = numeroLances;
	}

	public float getLanceVencendo() {
		return lanceVencendo;
	}

	public void setLanceVencendo(float lanceVencendo) {
		this.lanceVencendo = lanceVencendo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean isLoteSelecionado() {
		return loteSelecionado;
	}

	public void setLoteSelecionado(boolean loteSelecionado) {
		this.loteSelecionado = loteSelecionado;
	}

	public int getIdLote() {
		return idLote;
	}

	public void setIdLote(int idLote) {
		this.idLote = idLote;
	}

	public boolean isLeilaoIniciou() {
		return leilaoIniciou;
	}

	public void setLeilaoIniciou(boolean leilaoIniciou) {
		this.leilaoIniciou = leilaoIniciou;
	}

	public long getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(long idParticipante) {
		this.idParticipante = idParticipante;
	}

	public int getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}

	private boolean participanteVencendo = false;
	
	public void setParticipanteVencendo(boolean b) {
		participanteVencendo=b;
	}

	public boolean isParticipanteVencendo() {
		return participanteVencendo;
	}

}
