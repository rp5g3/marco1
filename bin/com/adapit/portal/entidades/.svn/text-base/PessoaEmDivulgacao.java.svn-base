package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@SuppressWarnings({"unused"})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@OnDelete(action=OnDeleteAction.CASCADE)
@Table(name="ExpositionPerson")
public class PessoaEmDivulgacao extends Pessoa implements Serializable {

	private static final long serialVersionUID = 923885678723647L;
	
	public PessoaEmDivulgacao(){
		super();
	}
	
	public PessoaEmDivulgacao(long id){
		super(id);
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="image_id")
	private Imagem logotipo;

	@Column(name="exposition_details",length=1000)
	private String descricao;
	
	@Column(length=8192,name="exposition_presentation")
	private String apresentacao;
	
	private boolean softDevExp=false;

	private boolean trainExp=false;
	
	private boolean saleExp=false;
	
	private boolean managerExp=false;
	
	private boolean researchExp=false;
	
	private boolean divulgavel=true;
	
	public boolean isSoftDevExp() {
		return softDevExp;
	}

	public void setSoftDevExp(boolean cliente) {
		this.softDevExp = cliente;
	}

	public boolean isTrainExp() {
		return trainExp;
	}

	public void setTrainExp(boolean fornecedor) {
		this.trainExp = fornecedor;
	}

	public boolean isSaleExp() {
		return saleExp;
	}

	public void setSaleExp(boolean colaborador) {
		this.saleExp = colaborador;
	}

	public boolean isManagerExp() {
		return managerExp;
	}

	public void setManagerExp(boolean parceiro) {
		this.managerExp = parceiro;
	}

	public boolean isResearchExp() {
		return researchExp;
	}

	public void setResearchExp(boolean desenvolvedor) {
		this.researchExp = desenvolvedor;
	}

	public boolean isDivulgavel() {
		return divulgavel;
	}

	public void setDivulgavel(boolean divulgavel) {
		this.divulgavel = divulgavel;
	}


	
	
	public String getApresentacao() {
		return apresentacao;
	}

	public void setApresentacao(String apresentacao) {
		this.apresentacao = apresentacao;
	}


	public void setLogotipo(Imagem logotipo) {
		this.logotipo = logotipo;
	}

	
	
	public Imagem getLogotipo() {
		return this.logotipo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * 
	 * @spring.validator arg0resource="pessoaEmDivulgacao.nome" maxlength="100"
	 *                   minlength="2" type="required"
	 */
	public void setNome(String nome) {
		super.setNome(nome);
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="pessoaEmDivulgacao.email" type="email"
	 */
	public void setEmail(String email) {
		super.setEmail(email);
	}
	
	
	public void setTelefone(String telefone) {
		super.setTelefone(telefone);
	}
	
	
}