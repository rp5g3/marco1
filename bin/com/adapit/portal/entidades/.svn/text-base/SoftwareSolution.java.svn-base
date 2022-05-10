package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="SoftwareSolution")
public class SoftwareSolution extends ComercialSolution implements Serializable {

	private static final long serialVersionUID = 224236235700347L;
	
	@Column(nullable = false, length = 20,name="sigle")
	private String sigla;
	
	@Column(name="functionalities",length=20000)
	private String funcionalidades;
	
	@Column(name="license", length=50)
	private String licencaUso;
	
	@Column(name="software_type")
	private SoftwareSolutionType tipoSoftware;
	
	@Column(name="software_project_url",length=140)
	private String urlProjeto;
	
	@Column(name="version",length=10)
	private String versao;
	
	@Column(name="platforms",length=140)
	private String plataforma;
	
	@Column(name="operational_systems",length=150)
	private String sistemasOperacionais;
	
	@Transient
	private List<CommercialSolutionDetailTab> transientDetails;

	public enum SoftwareSolutionType{
		OnDemmand, OfTheShelf
	}
	/**
	 * 
	 * @spring.validator arg0resource="softwareSolution.sigla" maxlength="8" minlength="2" type="required"
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	
	public String getSigla() {
		return this.sigla;
	}	

	/**
	 * @return the funcionalidades
	 */
	public String getFuncionalidades() {
		return funcionalidades;
	}

	/**
	 * @spring.validator arg0resource="softwareSolution.funcionalidades" type="required"
	 */
	public void setFuncionalidades(String funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	/**
	 * @return the licencaUso
	 */
	public String getLicencaUso() {
		return licencaUso;
	}

	/**
	 * @spring.validator arg0resource="softwareSolution.licensaUso"  type="required"
	 */
	public void setLicencaUso(String licencaUso) {
		this.licencaUso = licencaUso;
	}

	/**
	 * @return the tipoSoftware
	 */
	public SoftwareSolutionType getTipoSoftware() {
		return tipoSoftware;
	}

	/**
	 * @spring.validator arg0resource="softwareSolution.tipoSoftware" type="required"
	 */
	public void setTipoSoftware(SoftwareSolutionType tipoSoftware) {
		this.tipoSoftware = tipoSoftware;
	}

	/**
	 * @return the urlProjeto
	 */
	public String getUrlProjeto() {
		return urlProjeto;
	}

	/**
	 * @spring.validator arg0resource="softwareSolution.urlProjeto"  type="required"
	 */
	public void setUrlProjeto(String urlProjeto) {
		this.urlProjeto = urlProjeto;
	}

	/**
	 * @return the versao
	 */
	public String getVersao() {
		return versao;
	}

	/**
	 * @spring.validator arg0resource="softwareSolution.versao" type="required"
	 */
	public void setVersao(String versao) {
		this.versao = versao;
	}

	/**
	 * @return the plataforma
	 */
	public String getPlataforma() {
		return plataforma;
	}

	/**
	 * @spring.validator arg0resource="softwareSolution.plataforma" type="required"
	 */
	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	/**
	 * @return the sistemasOperacionais
	 */
	public String getSistemasOperacionais() {
		return sistemasOperacionais;
	}

	/**
	 * @spring.validator arg0resource="softwareSolution.sistemasOperacionais" type="required"
	 */
	public void setSistemasOperacionais(String sistemasOperacionais) {
		this.sistemasOperacionais = sistemasOperacionais;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * @spring.validator arg0resource="softwareSolution.descricao" type="required"
	 */
	public void setDescricao(String descricao ){
		super.setDescricao(descricao);
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="softwareSolution.avaliacao" type="float"
	 */
	public void setAvaliacao(float avaliacao ){
		super.setAvaliacao(avaliacao);
	}
	
	/**
	 * @spring.validator arg0resource="softwareSolution.nome" type="required"
	 */
	public void setNome(String nome) {
		super.setNome(nome);
	}

	/**
	 * @spring.validator arg0resource="softwareSolution.resumo" type="required"
	 */
	public void setResumo(String resumo) {
		super.setResumo(resumo);
	}

	/**
	 * @spring.validator arg0resource="softwareSolution.keyWords" type="required"
	 */
	public void setKeyWords(String keyWords) {
		super.setKeyWords(keyWords);
	}

	/**
	 * @spring.validator arg0resource="softwareSolution.solutionType" type="required"
	 */
	public void setSolutionType(CommercialSolutionType solutionType) {
		super.setSolutionType(solutionType);
	}


	public List<CommercialSolutionDetailTab> getTransientDetails() {
		return transientDetails;
	}


	public void setTransientDetails(
			List<CommercialSolutionDetailTab> transientDetails) {
		this.transientDetails = transientDetails;
	}


}