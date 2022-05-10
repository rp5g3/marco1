package com.adapit.portal.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="TrainingSolution")
public class TrainingSolution extends ComercialSolution {

	private static final long serialVersionUID = 8877374868346L;
	
	@Column(nullable = false, length = 6,name="training_code")
	private String codigo;

	@ManyToOne(targetEntity = Instrutor.class, cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	@JoinColumn(name="training_author_id")
	private Instrutor autor;
	
	@Column(length=20000,name="program_content")
	private String conteudoProgramatico;
	
	@Column(name="required_hours")
	private int cargaHoraria;
	
	@Column(name="technologies",length=200)
	private String tecnologias;
	
	@Column(name="support_material", length=150)
	private String materialApoio;
	
	@Column(name="support_softwares", length=200)
	private String softwares;
	
	@Column(name="training_type")
	private TrainingSolutionType tipoTreinamento;
	
	@Column(name="objectives",length=5000)
	private String objetivos;
	
	@OneToMany(targetEntity = TrainingFormationItem.class, mappedBy = "trainingSolution", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	protected List<TrainingFormationItem> trainingFormationItens = new ArrayList<TrainingFormationItem>();
	
	@OneToMany(targetEntity = ComercialSolutionItem.class, mappedBy = "trainingSolution", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	protected List<ComercialSolutionItem> comercialSolutionItens = new ArrayList<ComercialSolutionItem>();
	
	
	public List<TrainingFormationItem> getTrainingFormationItens() {
		return trainingFormationItens;
	}

	public void setTrainingFormationItens(List<TrainingFormationItem> itens) {
		this.trainingFormationItens = itens;
	}
	
	public enum TrainingSolutionType{
		Tecnologia_Producao_Software, Tecnologia_Metal_Mecanica, Informatica_Basica, Nivelamento_Tecnico, Para_Venda, Para_Marketing, Organizacional, Capacitacao_Profissional, Nivel_Medio, Nivel_Superior
	}

	public void setAutor(Instrutor comprador) {
		this.autor = comprador;
	}

	
	public Instrutor getAutor() {
		return this.autor;
	}


	/**
	 * 
	 * @spring.validator arg0resource="trainingSolution.codigo" maxlength="6" minlength="4"
	 *                   type="required"
	 */
	public void setCodigo(String codLote) {
		this.codigo = codLote;
	}

	
	public String getCodigo() {
		return this.codigo;
	}


	/**
	 * @return the conteudoProgramatico
	 */
	public String getConteudoProgramatico() {
		return conteudoProgramatico;
	}

	/**
	 * 
	 * @spring.validator arg0resource="trainingSolution.conteudoProgramatico" type="required"
	 */
	public void setConteudoProgramatico(String conteudoProgramatico) {
		this.conteudoProgramatico = conteudoProgramatico;
	}

	/**
	 * @return the cargaHoraria
	 */
	public int getCargaHoraria() {
		return cargaHoraria;
	}

	/**
	 * 
	 * @spring.validator arg0resource="trainingSolution.conteudoProgramatico" type="required,integer"
	 */
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	/**
	 * @return the tecnologias
	 */
	public String getTecnologias() {
		return tecnologias;
	}

	/**
	 * 
	 * @spring.validator arg0resource="trainingSolution.tecnologias" type="required"
	 */
	public void setTecnologias(String tecnologias) {
		this.tecnologias = tecnologias;
	}

	/**
	 * @return the materialApoio
	 */
	public String getMaterialApoio() {
		return materialApoio;
	}

	/**
	 * 
	 * @spring.validator arg0resource="trainingSolution.materialApoio" type="required"
	 */
	public void setMaterialApoio(String materialApoio) {
		this.materialApoio = materialApoio;
	}

	/**
	 * @return the softwares
	 */
	public String getSoftwares() {
		return softwares;
	}

	/**
	 * 
	 * @spring.validator arg0resource="trainingSolution.softwares" type="required"
	 */
	public void setSoftwares(String softwares) {
		this.softwares = softwares;
	}

	/**
	 * @return the tipoTreinamento
	 */
	public TrainingSolutionType getTipoTreinamento() {
		return tipoTreinamento;
	}

	/**
	 * 
	 * @spring.validator arg0resource="trainingSolution.tipoTreinamento" type="required"
	 */
	public void setTipoTreinamento(TrainingSolutionType tipoTreinamento) {
		this.tipoTreinamento = tipoTreinamento;
	}

	/**
	 * @return the objetivos
	 */
	public String getObjetivos() {
		return objetivos;
	}

	/**
	 * 
	 * @spring.validator arg0resource="trainingSolution.objetivos" type="required"
	 */
	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}

	
	
	
	
	
	/**
	 * @spring.validator arg0resource="traningSolution.descricao" type="required"
	 */
	public void setDescricao(String descricao ){
		super.setDescricao(descricao);
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="traningSolution.avaliacao" type="float"
	 */
	public void setAvaliacao(float avaliacao ){
		super.setAvaliacao(avaliacao);
	}
	
	/**
	 * @spring.validator arg0resource="traningSolution.nome" type="required"
	 */
	public void setNome(String nome) {
		super.setNome(nome);
	}

	/**
	 * @spring.validator arg0resource="traningSolution.resumo" type="required"
	 */
	public void setResumo(String resumo) {
		super.setResumo(resumo);
	}

	/**
	 * @spring.validator arg0resource="traningSolution.keyWords" type="required"
	 */
	public void setKeyWords(String keyWords) {
		super.setKeyWords(keyWords);
	}

	/**
	 * @spring.validator arg0resource="traningSolution.solutionType" type="required"
	 */
	public void setSolutionType(CommercialSolutionType solutionType) {
		super.setSolutionType(solutionType);
	}

	
	public List<ComercialSolutionItem> getComercialSolutionItens() {
		return comercialSolutionItens;
	}
	
	public void setComercialSolutionItens(
			List<ComercialSolutionItem> comercialSolutionItems) {
		this.comercialSolutionItens = comercialSolutionItems;
	}
}
