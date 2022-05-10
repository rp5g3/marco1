package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(value={
@NamedQuery(name="personAttribute.getByAttributeAndId",query="select p from AtributoPessoa p where p.atributo=:attribute and p.idPessoa=:id"),
@NamedQuery(name="personAttribute.listById",query="select p from AtributoPessoa p where p.idPessoa=:id"),
@NamedQuery(name="personAttribute.listPersonIdByAttribute",query="select p.idPessoa from AtributoPessoa p where p.atributo=:attribute")
})
@Table(name="PersonAttribute")
@IdClass(PersonAttributePk.class)
public class AtributoPessoa implements Serializable{

	private static final long serialVersionUID = 23325742674822483L;
	
	@Id
	@Column(nullable = false,name="person_id", insertable = false, updatable = false)
	private long idPessoa;
	
	@Column(nullable = false,name="attribute", insertable = false, updatable = false)
	@Enumerated(value=EnumType.ORDINAL)
	private PersonAttributeType atributo = PersonAttributeType.Contato;

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public PersonAttributeType getAtributo() {
		return atributo;
	}

	public void setAtributo(PersonAttributeType atributo) {
		this.atributo = atributo;
	}
	
}
