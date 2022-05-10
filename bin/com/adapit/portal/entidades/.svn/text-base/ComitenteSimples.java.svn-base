package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class ComitenteSimples extends PessoaEmDivulgacao implements Serializable{

	private static final long serialVersionUID = 235734836438368L;

	@Override
	public TipoPessoa getTipoPessoa() {
		return null;
	}

	@Override
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		
	}

	@Override
	public Endereco getEndereco() {
		return null;
	}

	@Override
	public void setEndereco(Endereco endereco) {
		
	}
	
	@Transient
	public String getNomeFormatado(){
		return super.getNome();
	}
	
}
