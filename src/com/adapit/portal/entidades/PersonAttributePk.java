package com.adapit.portal.entidades;

import java.io.Serializable;


public class PersonAttributePk  implements Serializable{

	private static final long serialVersionUID = 23325742674822482L;	
	
/*	@Id
	@Column(nullable = false,name="person_id")*/
	private long idPessoa;
	
/*	@Id
	@Column(nullable = false,name="attribute")
	@Enumerated(value=EnumType.ORDINAL)*/
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((atributo == null) ? 0 : atributo.hashCode());
		result = prime * result + (int) (idPessoa ^ (idPessoa >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PersonAttributePk other = (PersonAttributePk) obj;
		if (atributo == null) {
			if (other.atributo != null)
				return false;
		} else if (!atributo.equals(other.atributo))
			return false;
		if (idPessoa != other.idPessoa)
			return false;
		return true;
	}
	
	
	
}
