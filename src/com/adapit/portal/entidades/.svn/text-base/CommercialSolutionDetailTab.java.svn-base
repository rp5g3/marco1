package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@IdClass(CommercialSolutionDetailTabPK.class)
@Table(name="CommercialSolutionDetailTab")
public class CommercialSolutionDetailTab implements Serializable{

	private static final long serialVersionUID = 837613562346L;

	@Id
	@Column(name="commercial_solution_id")
	private int commercial_solution_id;
	
	@Id
	@Column(length=20,name="detail_tab_name")
	private String detail_tab_name;
	
	@Column(length=100,name="detail_name")
	private String detailName;
	
	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	@Id
	@Column(length=4,name="detail_text_language")
	private String detail_text_language;
	
	@Column(length=20000)
	private String detail;
	
	private boolean toPublish;
	
	private int index=5;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the idComSol
	 */
	public int getCommercial_solution_id() {
		return commercial_solution_id;
	}

	/**
	 * @param idComSol the idComSol to set
	 */
	public void setCommercial_solution_id(int idComSol) {
		this.commercial_solution_id = idComSol;
	}

	/**
	 * @return the key
	 */
	public String getDetail_tab_name() {
		return detail_tab_name;
	}

	/**
	 * @param key the key to set
	 */
	public void setDetail_tab_name(String key) {
		this.detail_tab_name = key;
	}

	/**
	 * @return the value
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 
	 * @spring.validator arg0resource="commercialSolutionDetailTab.detail" type="required"
	 */
	public void setDetail(String value) {
		this.detail = value;
	}

	/**
	 * @return the language
	 */
	public String getDetail_text_language() {
		return detail_text_language;
	}

	/**
	 * @param language the language to set
	 */
	public void setDetail_text_language(String language) {
		this.detail_text_language = language;
	}

	/**
	 * @return the toPublish
	 */
	public boolean isToPublish() {
		return toPublish;
	}

	/**
	 * @param toPublish the toPublish to set
	 */
	public void setToPublish(boolean toPublish) {
		this.toPublish = toPublish;
	}
}
