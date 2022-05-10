package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="FormacaoTreinamento_Gen",allocationSize=1,initialValue=1,sequenceName="TrainingFormationSeq")
@Table(name="TrainingFormation")
public class FormacaoTreinamento implements Serializable {

	private static final long serialVersionUID = 7463646598989322677L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FormacaoTreinamento_Gen")
	@Id
	private int id;

	@Column(nullable = true, length = 10000,name="desription")
	private String descricao;
	
	@Column(name="technology")
	private Technologies technology;

	@Column(nullable = false,name="formation_cost")
	private float avaliacao;

	@Column(nullable = false, length = 8,name="formation_code")
	private String codigo;

	@OneToMany(targetEntity = TrainingFormationItem.class, mappedBy = "trainingFormation", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name="train_formation_item_id")
	protected List<TrainingFormationItem> trainingFormationItens = new ArrayList<TrainingFormationItem>();
	
	@ManyToOne(cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	@JoinColumn(name="formation_image_id")
	private Imagem imagem;
	
	@Column(name="formation_name")
	private String nome;
	
	@Column(name="total_required_time")
	private int cargaHorariaTotal;
	
	@Column(name="preference_order")
	private int ordemPreferencia=1;
	
	
	public void setTrainingFormationItens(List<TrainingFormationItem> itensProduto) {
		this.trainingFormationItens = itensProduto;
	}


	
	public List<TrainingFormationItem> getTrainingFormationItens() {
		return this.trainingFormationItens;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getId() {
		return this.id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getDescricao() {
		return this.descricao;
	}


	public void setAvaliacao(float avaliacao) {
		this.avaliacao = avaliacao;
	}


	public float getAvaliacao() {
		return this.avaliacao;
	}

	/**
	 * 
	 * @spring.validator arg0resource="formacaoTreinamento.codigo" maxlength="8" minlength="3"
	 *                   type="required"
	 */
	public void setCodigo(String codLote) {
		this.codigo = codLote;
	}


	public String getCodigo() {
		return this.codigo;
	}
	
	public Technologies getTechnology() {
		return technology;
	}

	public void setTechnology(Technologies tec) {
		this.technology = tec;
	}
	

	public Imagem getImagem() {
		return imagem;
	}

	/**
	 * @param imagem the imagem to set
	 */
	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}


	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * @return the cargaHorariaTotal
	 */
	public int getCargaHorariaTotal() {
		return cargaHorariaTotal;
	}


	/**
	 * 
	 * @spring.validator arg0resource="formacaoTreinamento.cargaHorariaTotal" type="required,integer"
	 */
	public void setCargaHorariaTotal(int cargaHorariaTotal) {
		this.cargaHorariaTotal = cargaHorariaTotal;
	}



	/**
	 * @return the ordemPreferencia
	 */
	public int getOrdemPreferencia() {
		return ordemPreferencia;
	}



	/**
	 * @param ordemPreferencia the ordemPreferencia to set
	 */
	public void setOrdemPreferencia(int ordemPreferencia) {
		this.ordemPreferencia = ordemPreferencia;
	}
	
}