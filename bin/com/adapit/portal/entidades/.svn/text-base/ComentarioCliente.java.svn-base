package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@SequenceGenerator(name="CustomerComment_Gen",allocationSize=1,initialValue=1,sequenceName="CustomerCommentSeq")
@NamedQueries(value={
@NamedQuery(name="comentarioCliente.listByIdPessoa",query="select a from ComentarioCliente a where a.pessoa.id=:id order by a.dataHora ASC"),
@NamedQuery(name="comentarioCliente.listWebByCommercialSolution",query="select a from ComentarioCliente a where a.commercialSolution.id=:comid and a.aprovado=true order by a.classificacao ASC"),
@NamedQuery(name="comentarioCliente.listByCommercialSolution",query="select a from ComentarioCliente a where a.commercialSolution.id=:comid order by a.classificacao ASC"),
@NamedQuery(name="comentarioCliente.listByApproval",query="select a from ComentarioCliente a where a.aprovado=:ap order by a.classificacao ASC"),
@NamedQuery(name="comentarioCliente.listWebByClassification",query="select a from ComentarioCliente a where a.classificacao=:classification and a.aprovado=true order by a.classificacao ASC"),
@NamedQuery(name="comentarioCliente.listByClassification",query="select a from ComentarioCliente a where a.classificacao=:classification and a.aprovado=true order by a.classificacao ASC")
})
@Table(name="CustomerComment")
@IdClass(ComentarioClientePk.class)
public class ComentarioCliente implements Serializable{

	private static final long serialVersionUID = 233257443368822482L;
	
	/*@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CustomerComment_Gen")
	@Id
	private int id;*/

	@Id
	@Column(nullable = false,name="person_id", insertable = false, updatable = false)
	private long idPessoa;
	
	@Id
	@Column(nullable = false,name="commercial_solution_id", insertable = false, updatable = false)
	private int idComSol;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false,name="postTime")
	private Date dataHora;	

	@ManyToOne(targetEntity=Pessoa.class,fetch=FetchType.LAZY)
	@JoinColumn(name="person_id")
	private Pessoa pessoa;

	@Column(nullable = false,name="approved")
	private boolean aprovado=false;
	
	@Column(nullable = false,name="classification")
	@Enumerated(value=EnumType.ORDINAL)
	private ClassificacaoComentarioCliente classificacao = ClassificacaoComentarioCliente.Bom;
	
	@Basic(fetch=FetchType.LAZY)
	@ManyToOne (targetEntity=ComercialSolution.class,fetch=FetchType.LAZY)
	@JoinColumn(name="commercial_solution_id")
	private ComercialSolution commercialSolution;
	
	@Column(nullable=false,length=2000,name="comment")
	private String comentario;
	
	/*public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}*/

	/**
	 * 
	 * @spring.validator arg0resource="access.data" type="date"
	 */
	public void setDataHora(Date data) {
		this.dataHora = data;
	}
	
	public Date getDataHora() {
		return this.dataHora;
	}
	
	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public ClassificacaoComentarioCliente getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(ClassificacaoComentarioCliente classificacao) {
		this.classificacao = classificacao;
	}

	public ComercialSolution getCommercialSolution() {
		return commercialSolution;
	}

	public void setCommercialSolution(ComercialSolution commercialSolution) {
		this.commercialSolution = commercialSolution;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public int getIdComSol() {
		return idComSol;
	}

	public void setIdComSol(int idComSol) {
		this.idComSol = idComSol;
	}
}
