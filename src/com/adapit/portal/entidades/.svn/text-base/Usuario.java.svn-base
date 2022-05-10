package com.adapit.portal.entidades;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import sun.misc.BASE64Encoder;



@NamedQueries(
{
	@NamedQuery(name="usuario.updateActiveByLogin",query="update Usuario u set u.active=:active where u.login=:userlogin"),
	@NamedQuery(name="usuario.listDeactivationByLogin",query="select d from DeactivationReason d where d.usuario.login=:userlogin"),
	@NamedQuery(name="usuario.getEmail",query="select usuario.dadosPessoais.email from Usuario usuario where usuario.login = :login"),
	@NamedQuery(name="usuario.getNome",query="select usuario.dadosPessoais.nome from Usuario usuario where usuario.login = :login")
}
)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="UserData")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 987277357273486876L;
	
	@Column(nullable = false, length = 20,name="user_login")
	@Id
	private String login;

	@Column(nullable = false, length = 50, name="user_password")
	private String password;

	@Transient
	private String passwordConf;

	@Column(name="active_to_acess_system")
	private boolean active;

	@Column(name="authenticated")
	private boolean autenticado;

	@Column(name="user_ticket_account")
	private String ticket;

	@Transient
	private boolean newUser = true;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="user_last_acess")
	private Date lastAccess;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="user_cadastre_type")
	private UserCadastreType userCadastreType;

	@OneToMany(targetEntity = DeactivationReason.class, mappedBy = "usuario", cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@OrderBy(value = "date")
	@Column(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<DeactivationReason> deactivationReasonList = new TreeSet<DeactivationReason>();

	@OneToOne(targetEntity = Pessoa.class, cascade = { CascadeType.REMOVE,
			CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name="personal_data_id")
	private Pessoa dadosPessoais;

	@OneToMany(targetEntity = Access.class, mappedBy = "usuario",
			cascade = { CascadeType.REMOVE }, fetch = FetchType.LAZY)
	@OrderBy(value = "dataHora")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Collection<Access> accessList = new TreeSet<Access>();

	@OneToOne(targetEntity = PreferenciaCategoria.class, cascade = { CascadeType.REMOVE }, 
			fetch = FetchType.LAZY, mappedBy = "usuario")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="user_cat_preference_id")
	private PreferenciaCategoria preferencia;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=true,name="modification_date")
	private Date modification;

	public boolean isNewUser() {
		return newUser;
	}

	public void setNewUser(boolean newUser) {
		this.newUser = newUser;
	}

	public void setDeactivationReasonList(
			Collection<DeactivationReason> inactivationReasons) {
		this.deactivationReasonList = inactivationReasons;
	}

	public Collection<DeactivationReason> getDeactivationReasonList() {
		return this.deactivationReasonList;
	}

	public void setDadosPessoais(Pessoa dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public Pessoa getDadosPessoais() {
		return this.dadosPessoais;
	}

	public void setAccessList(Collection<Access> accessList) {
		this.accessList = accessList;
	}

	public Collection<Access> getAccessList() {
		return this.accessList;
	}

	/*
	 * public void setId(int id) { this.id = id; }
	 * 
	 * @GeneratedValue(strategy=GenerationType.SEQUENCE,
	 * generator="Usuario_GEN") @Id public int getId() { return this.id; }
	 */

	/**
	 * 
	 * @spring.validator arg0resource="usuario.login" maxlength="20"
	 *                   minlength="6" type="required"
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	// @Index(name="Login_index")
	public String getLogin() {
		return this.login;
	}

	/**
	 * 
	 * @spring.validator arg0resource="usuario.password" maxlength="8"
	 *                   minlength="6" type="required"
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Date getLastAccess() {
		return this.lastAccess;
	}

	public void setUserCadastreType(UserCadastreType rule) {
		this.userCadastreType = rule;
	}

	public UserCadastreType getUserCadastreType() {
		return this.userCadastreType;
	}

	public String getPasswordConf() {
		return passwordConf;
	}

	/**
	 * 
	 * @spring.validator arg0resource="usuario.passwordConf" maxlength="8"
	 *                   minlength="6" type="required"
	 */
	public void setPasswordConf(String passwordConf) {
		this.passwordConf = passwordConf;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean authenticated) {
		this.autenticado = authenticated;
	}

	public PreferenciaCategoria getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(PreferenciaCategoria preferencia) {
		this.preferencia = preferencia;
	}
	
	@Transient
	public static String encript(String senha) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(senha.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(digest.digest());
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();
			return senha;
		}
	}

	public Usuario() {
		super();
		modification = new Date();
	}
	
	public Usuario(String login) {
		super();
		this.login = login;
		modification = new Date();
	}

	public Date getModification() {
		return modification;
	}

	public void setModification(Date modification) {
		this.modification = modification;
	}

}