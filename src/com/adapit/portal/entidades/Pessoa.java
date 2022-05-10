package com.adapit.portal.entidades;

import java.io.Serializable;

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
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="Pessoa_Gen",allocationSize=1,initialValue=1,sequenceName="PersonSeq")
@Table(name="Person")
public abstract class Pessoa implements Serializable {

	public Pessoa(){
		super();
	}
	
	public Pessoa(long id){
		super();
		this.id=id;
	}
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Pessoa_Gen")
	@Id
	private long id;

	@Column(nullable = false, name="person_name")
	private String nome;

	@Column(length = 100, name="person_email")
	@Index(name="EmailIndex")
	private String email;

	@Column(nullable = false, length = 20, name="phone_number")
	private String telefone;

	@Column(nullable = true, length = 20, name="fax_number")
	private String fax;

	@OneToOne(mappedBy="pessoa",targetEntity = TipoPessoa.class, cascade = { CascadeType.ALL }, fetch=FetchType.EAGER)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private TipoPessoa tipoPessoa;

	@OneToOne(targetEntity = Endereco.class,cascade ={CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="address_id")
	private Endereco endereco;

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	
	public TipoPessoa getTipoPessoa() {
		return this.tipoPessoa;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	
	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public long getId() {
		return this.id;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getNome() {
		return this.nome;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getEmail() {
		return this.email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
	public String getTelefone() {
		return this.telefone;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getFax() {
		return this.fax;
	}
	
	@Transient
	public String getNomeFormatado(){
		return nome + " " + tipoPessoa.getNomeFormatado();
	}

}