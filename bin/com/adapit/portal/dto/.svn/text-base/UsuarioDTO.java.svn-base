package com.adapit.portal.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.adapit.portal.entidades.AtributoPessoa;
import com.adapit.portal.entidades.PersonAttributeType;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.UserLayoutPreferences;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.services.PersonType;

public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 237835264376524476L;
	private String welcomeName, sessionId,ticket;
	private List<LoteReportDTO> loteReports;
	private Date lastAcess;
	private PersonType tipoPessoa;
	
	private PreferenciaCategoria preferencia;
	private UserCadastreType userRole;
	
	private String id;
	private long participanteId;

	private boolean active;
	private boolean authenticated;
	private UserLayoutPreferences preferences;
	private List<AtributoPessoa> atributos;

	

	public List<AtributoPessoa> getAtributos() {
		return atributos;
	}



	public void setAtributos(List<AtributoPessoa> atributos) {
		this.atributos = atributos;
	}

	public boolean hasAttribute(PersonAttributeType att){
		if(atributos != null && atributos.size()>0){
			for(AtributoPessoa ap: atributos){
				if(ap.getAtributo() == att)
					return true;
			}
		}
		return false;
	}


	public UserLayoutPreferences getPreferences() {
		return preferences;
	}



	public void setPreferences(UserLayoutPreferences preferences) {
		this.preferences = preferences;
	}



	public UsuarioDTO() {
		super();
	}



	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<LoteReportDTO> getLoteReports() {
		return loteReports;
	}

	public void setLoteReports(List<LoteReportDTO> loteReports) {
		this.loteReports = loteReports;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public Date getLastAcess() {
		return lastAcess;
	}

	public void setLastAcess(Date lastAcess) {
		this.lastAcess = lastAcess;
	}



	public String getWelcomeName() {
		return welcomeName;
	}



	public void setWelcomeName(String welcomeName) {
		this.welcomeName = welcomeName;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public PreferenciaCategoria getPreferencia() {
		return preferencia;
	}



	public void setPreferencia(PreferenciaCategoria preferencia) {
		this.preferencia = preferencia;
	}



	public String getTicket() {
		return ticket;
	}



	public void setTicket(String ticket) {
		this.ticket = ticket;
	}



	public PersonType getTipoPessoa() {
		return tipoPessoa;
	}



	public void setTipoPessoa(PersonType tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}



	public long getParticipanteId() {
		return participanteId;
	}



	public void setParticipanteId(long participanteId) {
		this.participanteId = participanteId;
	}



	public UserCadastreType getUserRole() {
		return userRole;
	}



	public void setUserRole(UserCadastreType userRule) {
		this.userRole = userRule;
	}




}
