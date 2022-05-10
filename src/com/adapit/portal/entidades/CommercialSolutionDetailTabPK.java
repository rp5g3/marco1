package com.adapit.portal.entidades;

import java.io.Serializable;


public class CommercialSolutionDetailTabPK implements Serializable{


	private static final long serialVersionUID = 3459892458899745L;

	private int commercial_solution_id;
	
	private String detail_tab_name;
	
	private String detail_text_language;

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
	 * @return the tabName
	 */
	public String getDetail_tab_name() {
		return detail_tab_name;
	}

	/**
	 * @param tabName the tabName to set
	 */
	public void setDetail_tab_name(String tabName) {
		this.detail_tab_name = tabName;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commercial_solution_id;
		result = prime * result
				+ ((detail_text_language == null) ? 0 : detail_text_language.hashCode());
		result = prime * result + ((detail_tab_name == null) ? 0 : detail_tab_name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CommercialSolutionDetailTabPK other = (CommercialSolutionDetailTabPK) obj;
		if (commercial_solution_id != other.commercial_solution_id)
			return false;
		if (detail_text_language == null) {
			if (other.detail_text_language != null)
				return false;
		} else if (!detail_text_language.equals(other.detail_text_language))
			return false;
		if (detail_tab_name == null) {
			if (other.detail_tab_name != null)
				return false;
		} else if (!detail_tab_name.equals(other.detail_tab_name))
			return false;
		return true;
	}
	
	
}
