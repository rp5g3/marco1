package com.adapit.portal.entidades;


import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.io.Serializable;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="Endereco_GEN",allocationSize=1,initialValue=1,sequenceName="AddressSeq")
@Table(name="Address")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 376659892743628L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Endereco_GEN")
	@Id
	private int id;

	@Column(nullable = true, length = 100, name="quarter_name")
	private String bairro;

	@Column(nullable = true, length = 100, name="city_name")
	private String cidade;

	@Column(name="cowntry")
	private Pais pais = Pais.Brasil;

	@Column(nullable = true, length = 100, name="state_name")
	private String estado;

	@Column(nullable = true,name="house_number")
	private int numero;

	@Column(nullable = true, length = 20,name="postal_box_number")
	private String caixaPostal;

	@Column(nullable = true, length = 9, name="postal_code")
	private String cep;

	@Column(nullable = true, length = 100, name="street_name")
	private String rua;

	@Column(nullable = true, name="complementar_number")
	private String complemento;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="address_type")
	private AddressType tipo = AddressType.Residencial;
	
	private String local;

	public void setId(int id) {
		this.id = id;
	}

	
	public int getId() {
		return this.id;
	}

	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getBairro() {
		return this.bairro;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getCidade() {
		return this.cidade;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	public Pais getPais() {
		return this.pais;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getEstado() {
		return this.estado;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	
	public int getNumero() {
		return this.numero;
	}

	public void setCaixaPostal(String caixaPostal) {
		this.caixaPostal = caixaPostal;
	}

	public String getCaixaPostal() {
		return this.caixaPostal;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}


	public String getCep() {
		return this.cep;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}


	public String getRua() {
		return this.rua;
	}


	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getComplemento() {
		return this.complemento;
	}


	public AddressType getTipo() {
		return tipo;
	}

	public void setTipo(AddressType tipo) {
		this.tipo = tipo;
	}
	
	@Column(nullable = true, length = 255)
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

}