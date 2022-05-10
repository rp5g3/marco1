package com.adapit.portal.entidades;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Customer")
public class Cliente extends Fornecedor implements Serializable{

	private static final long serialVersionUID = 432372357245457L;
	
	private Collection<Categoria>  preferencias = new ArrayList<Categoria>();
	
	private Usuario user;
	
	private Collection<Projeto> projetosInteresse = new ArrayList<Projeto>();
	
	private Collection<Projeto> projetosCompra = new ArrayList<Projeto>();
	
	private Collection<ClienteContaPagar> contaPagar = new ArrayList<ClienteContaPagar>();
	
	public void setPreferencias( Collection<Categoria> preferencias ){
		this.preferencias=preferencias;
	}
	
	@OrderBy(value="nome")
	@ManyToMany (fetch=FetchType.LAZY,targetEntity=Categoria.class,cascade={CascadeType.REFRESH})
	@JoinTable(
		name="PARTICIPANTE_CATEGORIA"
		,joinColumns={@JoinColumn(name="CATEGORIA_ID")}
		,inverseJoinColumns={@JoinColumn(name="PESSOA_ID")}
	)
	public Collection<Categoria> getPreferencias(){
		return this.preferencias;
	}
	
	
	public void setUser(Usuario user ){
		this.user=user;
	}
	
	@Basic(fetch=FetchType.EAGER)
	@OneToOne (targetEntity=Usuario.class
	,cascade={CascadeType.ALL})
	public Usuario getUser(){
		return this.user;
	}
	
	
	@OneToMany(targetEntity = ClienteContaPagar.class, mappedBy = "cliente", cascade = { CascadeType.REFRESH },fetch = FetchType.LAZY)
	public Collection<ClienteContaPagar> getContaPagar() {
		return contaPagar;
	}

	public void setContaPagar(Collection<ClienteContaPagar> contaPagar) {
		this.contaPagar = contaPagar;
	}

	

	@OrderBy(value="codigoProjeto")
	@ManyToMany (fetch=FetchType.LAZY,targetEntity=Projeto.class,cascade={CascadeType.REMOVE,CascadeType.MERGE},mappedBy="interessados")
	@JoinTable(name="CLIENTE_INTERESSE_PROJETO")
	public Collection<Projeto> getProjetosInteresse() {
		return projetosInteresse;
	}

	public void setProjetosInteresse(Collection<Projeto> projs) {
		this.projetosInteresse = projs;
	}

	@OrderBy(value="codigoProjeto")
	@ManyToMany (fetch=FetchType.LAZY,targetEntity=Projeto.class,cascade={CascadeType.REMOVE,CascadeType.MERGE},mappedBy="interessados")
	@JoinTable(name="CLIENTE_COMPRA_PROJETO")
	public Collection<Projeto> getProjetosCompra() {
		return projetosCompra;
	}

	public void setProjetosCompra(Collection<Projeto> projs) {
		this.projetosCompra = projs;
	}

}