package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
@SuppressWarnings({"serial","unchecked","unused","static-access"})

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@OnDelete(action=OnDeleteAction.CASCADE)
@Table(name="TrainingTutorPerson")
public class Instrutor extends Participante implements Serializable{

	private static final long serialVersionUID = 9982743692476473L;
	
	/*@Basic(fetch=FetchType.LAZY)
	@OneToOne (targetEntity=Usuario.class
	,cascade={CascadeType.REMOVE})
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Usuario user;*/
	
	@Column(name="training_hour_comission")
	private float comissao=20;
	
	@OrderBy(value = "dataTreinamento")
	@Basic(fetch = FetchType.LAZY)
	@OneToMany(targetEntity = TurmaTreinamento.class, mappedBy = "instrutor", cascade = {CascadeType.REFRESH })
	protected Collection<TurmaTreinamento> turmas = new ArrayList<TurmaTreinamento>();

	/*public void setUser(Usuario user ){
		this.user=user;
	}
	
	
	public Usuario getUser(){
		return this.user;
	}*/
	
	public void setTurmas(Collection<TurmaTreinamento> leiloes) {
		this.turmas = leiloes;
	}

	
	public Collection<TurmaTreinamento> getTurmas() {
		return this.turmas;
	}
	



	public float getComissao() {
		return comissao;
	}

	public void setComissao(float percentualComissao) {
		this.comissao = percentualComissao;
	}



	/**
	 * 
	 * @spring.validator arg0resource="instrutor.nome" maxlength="100"
	 *                   minlength="2" type="required"
	 */
	public void setNome(String nome) {
		super.setNome(nome);
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="instrutor.email" type="required,email"
	 */
	public void setEmail(String email) {
		super.setEmail(email);
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="instrutor.telefone" maxlength="20"
	 *                   minlength="10" type="required"
	 */
	public void setTelefone(String telefone) {
		super.setTelefone(telefone);
	}

}
