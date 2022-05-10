package com.adapit.portal.entidades;

import java.io.Serializable;

import javax.persistence.Basic;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="ParticipacaoTurma_Gen",allocationSize=1,initialValue=1,sequenceName="TrainingClassAccessSeq")
@Table(name="TrainingClassAccess")
public class ParticipacaoTurma implements Serializable {

	private static final long serialVersionUID = 2395920943066L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ParticipacaoTurma_Gen")
	@Id
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(nullable = true, name="user_access_hour")
	private java.util.Date horaIngresso;

	@Basic(fetch = FetchType.LAZY)
	@ManyToOne(targetEntity = Participante.class, cascade = { CascadeType.REFRESH })
	@JoinColumn(name="participant_id")
	private Participante participante;

	@Basic(fetch = FetchType.LAZY)
	@ManyToOne(targetEntity = TurmaTreinamento.class, cascade = { CascadeType.REFRESH })
	@JoinColumn(name="training_class_id")
	protected TurmaTreinamento turma;

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}


	public Participante getParticipante() {
		return this.participante;
	}

	public void setTurma(TurmaTreinamento leilao) {
		this.turma = leilao;
	}


	public TurmaTreinamento getTurma() {
		return this.turma;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @spring.validator arg0resource="participacaoTurma.horaIngresso"
	 *                   type="date"
	 */
	public void setHoraIngresso(java.util.Date horaIngresso) {
		this.horaIngresso = horaIngresso;
	}


	public java.util.Date getHoraIngresso() {
		return this.horaIngresso;
	}

}