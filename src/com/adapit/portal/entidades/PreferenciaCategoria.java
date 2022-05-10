package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="Pref_cat_gen",allocationSize=1,initialValue=1,sequenceName="CategoryUserPreferenceSeq")
@NamedQueries({
@NamedQuery(name="preferencia.getByLogin",query="select p from PreferenciaCategoria p where p.usuario.login=:userlogin"),
@NamedQuery(name="preferencia.updatePreferencias",query="update PreferenciaCategoria pref set pref.preferencias=:value where pref.id=:id"),
@NamedQuery(name="preferencia.receberEmailAtualizacoesSoftware",query="update PreferenciaCategoria pref set pref.receberEmailAtualizacoesSoftware=:value where pref.id=:id"),
@NamedQuery(name="preferencia.interesseEmConsultoria",query="update PreferenciaCategoria pref set pref.interesseEmConsultoria=:value where pref.id=:id"),
@NamedQuery(name="preferencia.receberEmailNovosProdutos",query="update PreferenciaCategoria pref set pref.receberEmailNovosProdutos=:value where pref.id=:id"),
@NamedQuery(name="preferencia.interesseEmTreinamentos",query="update PreferenciaCategoria pref set pref.interesseEmTreinamentos=:value where pref.id=:id"),
@NamedQuery(name="preferencia.receberEmailSobreEventos",query="update PreferenciaCategoria pref set pref.receberEmailSobreEventos=:value where pref.id=:id"),
@NamedQuery(name="preferencia.receberNotificacaoNewsByEmail",query="update PreferenciaCategoria pref set pref.receberNotificacaoNewsByEmail=:value where pref.id=:id")
})
@Table(name="CategoryUserPreference")
public class PreferenciaCategoria implements Serializable{
	private static final long serialVersionUID = 24583468365845685L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Pref_cat_gen")
	@Id
	private int id;
	
	@Column(name="software_pref")
	private boolean receberEmailAtualizacoesSoftware=true;
	
	@Column(name="cunsultance_services_pref")
	private boolean interesseEmConsultoria=true;
	
	@Column(name="software_on_demmand_pref")
	private boolean receberEmailNovosProdutos=true;
	
	@Column(name="training_pref")
	private boolean interesseEmTreinamentos=true;
	
	@Column(name="other_pref")
	private boolean receberEmailSobreEventos=true;
	
	@Column(name="notify_me_by_email")
	private boolean receberNotificacaoNewsByEmail=true;
	
	@Column(name="custom_preferences")
	private String preferencias;
	
	@OneToOne
	@Column(name="user_login")
	private Usuario usuario; 
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isReceberEmailAtualizacoesSoftware() {
		return receberEmailAtualizacoesSoftware;
	}
	public void setReceberEmailAtualizacoesSoftware(boolean informatica) {
		this.receberEmailAtualizacoesSoftware = informatica;
	}
	public boolean isInteresseEmConsultoria() {
		return interesseEmConsultoria;
	}
	public void setInteresseEmConsultoria(boolean veiculos) {
		this.interesseEmConsultoria = veiculos;
	}
	public boolean isReceberEmailNovosProdutos() {
		return receberEmailNovosProdutos;
	}
	public void setReceberEmailNovosProdutos(boolean imoveis) {
		this.receberEmailNovosProdutos = imoveis;
	}
	public boolean isInteresseEmTreinamentos() {
		return interesseEmTreinamentos;
	}
	public void setInteresseEmTreinamentos(boolean maquinarios) {
		this.interesseEmTreinamentos = maquinarios;
	}
	public boolean isReceberEmailSobreEventos() {
		return receberEmailSobreEventos;
	}
	public void setReceberEmailSobreEventos(boolean diversos) {
		this.receberEmailSobreEventos = diversos;
	}
	public String getPreferencias() {
		return preferencias;
	}
	public void setPreferencias(String preferencias) {
		this.preferencias = preferencias;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isReceberNotificacaoNewsByEmail() {
		return receberNotificacaoNewsByEmail;
	}
	public void setReceberNotificacaoNewsByEmail(boolean receberNotificacaoEmail) {
		this.receberNotificacaoNewsByEmail = receberNotificacaoEmail;
	}
	
}
