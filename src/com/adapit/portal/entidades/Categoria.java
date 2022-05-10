package com.adapit.portal.entidades;





import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@SequenceGenerator(name="Categoria_Gen",allocationSize=1,initialValue=1,sequenceName="CategorySeq")
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="Category")
public class Categoria implements Serializable{
	
	private static final long serialVersionUID = 2346143723582482485L;
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Categoria_Gen")
	@Id
	private int id;
	
	@Column(nullable=false,name="cat_name")
	private String nome;
	
	@Column(length=512,name="cat_template")
	private String template;
	
	@Basic(fetch=FetchType.LAZY)
	@OneToOne(mappedBy="categoria",cascade=CascadeType.ALL)	
	private CategoriaImagem categoriaImagem;
	
	@OrderBy(value="nome")
	@OneToMany (mappedBy="pai",targetEntity=Categoria.class
	,cascade={CascadeType.REMOVE},fetch=FetchType.EAGER)
	protected Set<Categoria>  subCategorias = new TreeSet<Categoria>();
	
	@ManyToOne(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER)
	@JoinColumn(name="parent_id")
	protected Categoria pai;

	
	public void setSubCategorias( Set<Categoria> subCategorias ){
		this.subCategorias=subCategorias;
	}
	
	
	public Set<Categoria> getSubCategorias(){
		return this.subCategorias;
	}
	
	
	
	public void setId(int id ){
		this.id=id;
	}
	
	
	public int getId(){
		return this.id;
	}
	/**
	 * 
	 * @spring.validator arg0resource="categoria.nome" type="required"
	 */
	public void setNome(String nome ){
		this.nome=nome;
	}
	
	
	public String getNome(){
		return this.nome;
	}
	
	public void setTemplate(String obs ){
		this.template=obs;
	}
	
	
	public String getTemplate(){
		return this.template;
	}
	


	public void addSubcategoria(Categoria categoria) throws Exception{
		if (subCategorias == null){
			subCategorias = new TreeSet<Categoria>();
		}
		subCategorias.add(categoria);
		categoria.setPai(this);
	}


	public Categoria removeSubcategoria(Categoria categoria) throws Exception{
		if (subCategorias == null) return null;
		if (subCategorias.contains(categoria)){
			subCategorias.remove(categoria);
			return categoria;
		}
		return null;
	}

	
	public Categoria getPai() {
		return pai;
	}

	public void setPai(Categoria pai) {
		this.pai = pai;
	}


	public CategoriaImagem getCategoriaImagem() {
		return categoriaImagem;
	}

	public void setCategoriaImagem(CategoriaImagem categoriaImagem) {
		this.categoriaImagem = categoriaImagem;
	}
	
	
}