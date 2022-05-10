package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@OnDelete(action=OnDeleteAction.CASCADE)
@Table(name="LegalRepresentantPerson")
public class RepresentanteLegal extends Participante implements Serializable{

	private static final long serialVersionUID = 23472458285245L;

	@OneToOne(mappedBy="pessoa",targetEntity = Fisica.class, cascade = { CascadeType.ALL })
	private Fisica tipoPessoa;
	
	@OneToMany(mappedBy="representanteLegal",fetch=FetchType.LAZY)
	private Collection<Juridica> pessoasJuridicasRepresentadas = new ArrayList<Juridica>();
	
	public void setTipoPessoa(Fisica tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	
	public Fisica getTipoPessoa() {
		return this.tipoPessoa;
	}

	
	public Collection<Juridica> getPessoasJuridicasRepresentadas() {
		return pessoasJuridicasRepresentadas;
	}

	public void setPessoasJuridicasRepresentadas(
			Collection<Juridica> pessoasJuridicasRepresentadas) {
		this.pessoasJuridicasRepresentadas = pessoasJuridicasRepresentadas;
	}
	
}
