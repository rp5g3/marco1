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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@OnDelete(action=OnDeleteAction.CASCADE)
@NamedQuery(name="participante.preferencias",query="select p.preferencias from Participante p where p.id=:id")
@Table(name="ParticipantPerson")
public class Participante extends PessoaEmDivulgacao implements Serializable{
	
	private static final long serialVersionUID = 82873749629372L;

	
	public Participante(){
		super();
	}
	
	public Participante(long id){
		super(id);
	}
	
	@OrderBy(value="nome")
	@ManyToMany (fetch=FetchType.LAZY,targetEntity=Categoria.class,cascade={CascadeType.MERGE})
	@JoinTable(
		name="PARTICIPANT_CATEGORY"
		,joinColumns={@JoinColumn(name="PARTICIPANT_ID")}
		,inverseJoinColumns={@JoinColumn(name="CATEGORY_ID")}
	)
	private Collection<Categoria>  preferencias = new ArrayList<Categoria>();
	
	@OrderBy(value="horaIngresso")
 	@OneToMany (mappedBy="participante",targetEntity=ParticipacaoTurma.class,
 			cascade={CascadeType.REMOVE},fetch=FetchType.LAZY)
 	@OnDelete(action=OnDeleteAction.CASCADE)
	private Collection<ParticipacaoTurma>  participacoes = new ArrayList<ParticipacaoTurma>();
	
	@Basic(fetch=FetchType.LAZY)
	@OneToOne (targetEntity=Usuario.class
	,cascade={CascadeType.REMOVE})
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Usuario user;

	@OneToMany(targetEntity = ParticipanteContaPagar.class, mappedBy = "cliente",
			cascade = { CascadeType.REMOVE },fetch = FetchType.LAZY)
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Collection<ParticipanteContaPagar> contaPagar = new ArrayList<ParticipanteContaPagar>();
	
	@OrderBy(value="codigo")
	@ManyToMany (fetch=FetchType.LAZY,targetEntity=Treinamento.class,
			cascade={CascadeType.PERSIST},mappedBy="interessados")
	@JoinTable(name="PARTICIPANT_TRAINING"
		,joinColumns={@JoinColumn(name="PARTICIPANT_ID")}
		,inverseJoinColumns={@JoinColumn(name="SCHED_TRAINING_ID")}	
	)
	private Collection<Treinamento> meusTreinamentos = new ArrayList<Treinamento>();
	
	@OneToMany(mappedBy="inscrito",fetch=FetchType.LAZY)
	private Collection<ComercialSolutionItem> itensTreinamentos = new ArrayList<ComercialSolutionItem>();
	
	private PrimeiroContatoIniciado primeiroContato = PrimeiroContatoIniciado.Recomendação;

	
	public PrimeiroContatoIniciado getPrimeiroContato() {
		return primeiroContato;
	}


	public void setPrimeiroContato(PrimeiroContatoIniciado primeiroContato) {
		this.primeiroContato = primeiroContato;
	}


	public void setPreferencias( Collection<Categoria> preferencias ){
		this.preferencias=preferencias;
	}
	

	public Collection<Categoria> getPreferencias(){
		return this.preferencias;
	}
	
	public void setParticipacoes( Collection<ParticipacaoTurma> participacoes ){
		this.participacoes=participacoes;
	}
	
	
	public Collection<ParticipacaoTurma> getParticipacoes(){
		return this.participacoes;
	}
	
	public void setUser(Usuario user ){
		this.user=user;
	}
	
	
	public Usuario getUser(){
		return this.user;
	}

	
	public Collection<ParticipanteContaPagar> getContaPagar() {
		return contaPagar;
	}

	public void setContaPagar(Collection<ParticipanteContaPagar> contaPagar) {
		this.contaPagar = contaPagar;
	}



	
	public Collection<Treinamento> getMeusTreinamentos() {
		return meusTreinamentos;
	}

	public void setMeusTreinamentos(Collection<Treinamento> meusTrein) {
		this.meusTreinamentos = meusTrein;
	}

	
	public Collection<ComercialSolutionItem> getItensTreinamentos() {
		return itensTreinamentos;
	}

	/**
	 * @param itensTreinamentos the itensTreinamentos to set
	 */
	public void setItensTreinamentos(
			Collection<ComercialSolutionItem> itensTreinamentos) {
		this.itensTreinamentos = itensTreinamentos;
	}


	/**
	 * 
	 * @spring.validator arg0resource="participante.nome" maxlength="100"
	 *                   minlength="2" type="required"
	 */
	public void setNome(String nome) {
		super.setNome(nome);
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="participante.email" type="email"
	 */
	public void setEmail(String email) {
		super.setEmail(email);
	}
}