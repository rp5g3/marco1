package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="ItemProjeto_GEN",sequenceName="SoftwareProjectItemSeq",allocationSize=1,initialValue=1)
@Table(name="SoftwareProjectItem")
public class ItemProjeto implements Serializable {

	private static final long serialVersionUID = 2346234727557L;

	private int id;

	private int qtd;

	private float valorTotal;

	protected ComercialSolution produto;

	protected Projeto projeto;

	public void setProduto(ComercialSolution produto) {
		this.produto = produto;
	}

	@OneToOne(targetEntity = ComercialSolution.class, cascade = { CascadeType.PERSIST,CascadeType.REMOVE}, fetch = FetchType.LAZY)
	public ComercialSolution getProduto() {
		return this.produto;
	}

	public void setProjeto(Projeto proj) {
		this.projeto = proj;
	}

	@ManyToOne(targetEntity = Projeto.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public Projeto getProjeto() {
		return this.projeto;
	}

	public void setId(int id) {
		this.id = id;
	}

	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ItemProjeto_GEN")
	@Id
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @spring.validator arg0resource="itemProduto.qtd" type="required,integer"
	 */
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	@Column(nullable = false)
	public int getQtd() {
		return this.qtd;
	}

	/**
	 * 
	 * @spring.validator arg0resource="itemProduto.valorTotal"
	 *                   type="required,float"
	 */
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	@Column(nullable = false)
	public float getValorTotal() {
		return this.valorTotal;
	}

}