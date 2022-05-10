package com.adapit.portal.dto;

import java.io.Serializable;


public class EstadoAgendaLoteDTO implements Serializable{
	private static final long serialVersionUID = 24363458368643L;
	
	private String codLote;
	private float lanceInicial,incremento,lanceVencendo,proxLance;
	private int numLances;
	private int agendaId,loteId,lanceId;
	public String getCodLote() {
		return codLote;
	}
	public void setCodLote(String codLote) {
		this.codLote = codLote;
	}
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
	public float getLanceVencendo() {
		return lanceVencendo;
	}
	public void setLanceVencendo(float lanceVencendo) {
		this.lanceVencendo = lanceVencendo;
	}
	public float getProxLance() {
		return proxLance;
	}
	public void setProxLance(float proxLance) {
		this.proxLance = proxLance;
	}
	public int getNumLances() {
		return numLances;
	}
	public void setNumLances(int numLances) {
		this.numLances = numLances;
	}
	public int getAgendaId() {
		return agendaId;
	}
	public void setAgendaId(int agendaId) {
		this.agendaId = agendaId;
	}
	public int getLoteId() {
		return loteId;
	}
	public void setLoteId(int loteId) {
		this.loteId = loteId;
	}
	public int getLanceId() {
		return lanceId;
	}
	public void setLanceId(int lanceId) {
		this.lanceId = lanceId;
	}
}
