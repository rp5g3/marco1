package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="CondicaoParticipacaoTreinamento_GEN",allocationSize=1,initialValue=1,sequenceName="TrainingPartConditionSeq")
@Table(name="TrainingParticipationCondition")
public class CondicaoParticipacaoTreinamento implements Serializable {

	private static final long serialVersionUID = 923658273867325L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CondicaoParticipacaoTreinamento_GEN")
	@Id
	private int id;

	@Column(nullable = false, length = 10000, name="condition_text")
	private String texto;


	@Column(nullable = false, length = 256, name="condition_name")
	private String descricao;

	public void setId(int id) {
		this.id = id;
	}

	
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @spring.validator arg0resource="condicaoParticipacaoTreinamento.texto"
	 *                   maxlength="10000" minlength="10" type="required"
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	
	public String getTexto() {
		return this.texto;
	}

	/**
	 * 
	 * @spring.validator arg0resource="condicaoParticipacaoTreinamento.descricao"
	 *                   maxlength="256" minlength="10" type="required"
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	public String getDescricao() {
		return this.descricao;
	}

}