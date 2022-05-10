package com.adapit.portal.entidades;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@OnDelete(action=OnDeleteAction.CASCADE)
@Table(name="EmployeePerson")
public class Funcionario extends Pessoa implements Serializable{
	private static final long serialVersionUID = 827768940969346L;
	
	@OneToOne (
			targetEntity=Usuario.class
			,mappedBy="dadosPessoais"
			,cascade={CascadeType.REMOVE,CascadeType.PERSIST}
			,fetch=FetchType.EAGER)
	protected Usuario user;
	
	@Column(name="contract_date")
	protected Date dataContrato;
	
	@Column(name="salary")
	protected float salario;
	
	@Column(name="employee_function")
	protected String funcao;

	
	public void setUser(Usuario user ){
		this.user=user;
	}
	
	
	
	public Usuario getUser(){
		return this.user;
	}

	/**
	 * 
	 * @spring.validator arg0resource="funcionario.nome" maxlength="100"
	 *                   minlength="2" type="required"
	 */
	public void setNome(String nome) {
		super.setNome(nome);
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="funcionario.email" type="required,email"
	 */
	public void setEmail(String email) {
		super.setEmail(email);
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="funcionario.telefone" maxlength="20"
	 *                   minlength="10" type="required"
	 */
	public void setTelefone(String telefone) {
		super.setTelefone(telefone);
	}


	public Date getDataContrato() {
		return dataContrato;
	}


	public void setDataContrato(Date dataContrato) {
		this.dataContrato = dataContrato;
	}


	public float getSalario() {
		return salario;
	}


	public void setSalario(float salario) {
		this.salario = salario;
	}


	public String getFuncao() {
		return funcao;
	}


	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}


}