package com.adapit.portal.entidades;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@SequenceGenerator(sequenceName="CommercialSolutionSeq",
			name="comercialSolution_generator",initialValue=1,allocationSize=1)
@Table(name="CommercialSolution")
public class ComercialSolution implements Serializable{

	private static final long serialVersionUID = 99846124124673457L;
	
	@Id	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="comercialSolution_generator")
	private int id;
	
	@Column(nullable=false,length=2048,name="description")
	private String descricao;
	
	@Column(nullable=false,name="price")
	private float avaliacao;
	
	@Column(name="creation_date")
	private Date dataCriacao;
	
	@OrderBy(value="indice")
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="SOLUTION_IMAGE"
			,joinColumns={@JoinColumn(name="SOLUTION_ID")}
			,inverseJoinColumns={@JoinColumn(name="IMAGE_ID")}
	)
	private List<Imagem> imagens = new ArrayList<Imagem>();
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="SOLUTION_FILE"
			,joinColumns={@JoinColumn(name="SOLUTION_ID")}
			,inverseJoinColumns={@JoinColumn(name="FILE_ID")}
	)
	private List<Arquivo> arquivos = new ArrayList<Arquivo>();
	
	@OneToOne (targetEntity=Categoria.class
			,cascade={CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name="solution_cat_id")
	private Categoria categoria;	
	
	@Column(name="use_web_formater")
	private boolean utilizarFormatador=false;
	
	@Column(name="name")
	private String nome;
	
	@Column(length=5000,name="abstract")
	private String resumo;
	
	@Column(name="keys_words")
	private String keyWords;
	
	private CommercialSolutionType solutionType = CommercialSolutionType.Outros_serviços;
	
	@Column(name="publish_to_customer")
	private boolean publicar=false;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private CssDefinition cssDefinition;

	
	public ComercialSolution() {
		super();
		setDataCriacao(new Date());
	}
	
	public ComercialSolution(int id){
		super();
		this.id=id;
	}


	public void setCategoria(Categoria categoria ){
		this.categoria=categoria;		
	}
	
	
	
	public Categoria getCategoria(){
		return this.categoria;
	}
	
	public void setId(int id ){
		this.id=id;
	}

	
	public int getId(){
		return this.id;
	}
	/**
	 * @spring.validator arg0resource="comercialSolution.descricao" type="required"
	 */
	public void setDescricao(String descricao ){
		this.descricao=descricao;
	}
	
	
	public String getDescricao(){
		return this.descricao;
	}
	
	/**
	 * 
	 * @spring.validator arg0resource="comercialSolution.avaliacao" type="float"
	 */
	public void setAvaliacao(float avaliacao ){
		this.avaliacao=avaliacao;
	}
	
	
	public float getAvaliacao(){
		return this.avaliacao;
	}


	public Date getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	
	@Transient
	public String getFormatedDescricao() {
		String str = "<table border=0 cellpadding=0 cellspacing=0 width=\"100%\" style=\"background-color:transparent;\">";
		//String title = getCategoria().getNome();
		try {
			String content = getDescricao();

			/*if (title != null && !title.equals("")) {
				str += "<tr><td><b>"+title+ "</b></td></tr>";
			}*/
			if (content != null && !content.equals("") && utilizarFormatador){
				String dados[] = content.split("\n");
				for (int j = 0; j < dados.length; j++) {
					str += "<tr bgcolor=\""+((j%2>0)?"#f5f5f5":"e8e8e8")+"\">";
					String dado = dados[j];
					String attval[] = dado.split(":");
					if (attval != null && attval.length == 2) {
						String att = attval[0];
						str += "<td><b>" + att + ":</b></td>";
						String val = attval[1];
						str += "<td>" + val + "</td>";
					}else{
						str += "<td>" + dado + "</td>";
					}
					str += "</tr>";
				}
			}else if(content != null && !content.equals("")){
				str += "<tr>";
				str += "<td>" + content + "</td>";				
				str += "</tr>";
			}else{
				str += "<tr>";
				str += "<td>Sem informações do produto/serviço</td>";				
				str += "</tr>";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		str+="</table>";
		return str;
	}
	

	public List<Imagem> getImagens() {
		return imagens;
	}


	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}

	
	public boolean getUtilizarFormatador() {
		return utilizarFormatador;
	}

/*	public boolean isUtilizarFormatador() {
		return utilizarFormatador;
	}
*/

	public void setUtilizarFormatador(boolean utilizarFormatador) {
		this.utilizarFormatador = utilizarFormatador;
	}


	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * @spring.validator arg0resource="comercialSolution.nome" type="required"
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	
	public String getResumo() {
		return resumo;
	}


	/**
	 * @spring.validator arg0resource="comercialSolution.resumo" type="required"
	 */
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}


	/**
	 * @return the keyWords
	 */
	public String getKeyWords() {
		return keyWords;
	}


	/**
	 * @spring.validator arg0resource="comercialSolution.keyWords" type="required"
	 */
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}


	/**
	 * @return the solutionType
	 */
	public CommercialSolutionType getSolutionType() {
		return solutionType;
	}


	/**
	 * @spring.validator arg0resource="comercialSolution.solutionType" type="required"
	 */
	public void setSolutionType(CommercialSolutionType solutionType) {
		this.solutionType = solutionType;
	}



	public List<Arquivo> getArquivos() {
		return arquivos;
	}


	/**
	 * @param arquivos the arquivos to set
	 */
	public void setArquivos(List<Arquivo> arquivos) {
		this.arquivos = arquivos;
	}


	/**
	 * @return the publicar
	 */
	public boolean isPublicar() {
		return publicar;
	}


	/**
	 * @param publicar the publicar to set
	 */
	public void setPublicar(boolean publicar) {
		this.publicar = publicar;
	}

	public void setCssDefinition(CssDefinition cssDefinition) {
		this.cssDefinition = cssDefinition;
	}

	public CssDefinition getCssDefinition() {
		return cssDefinition;
	}

	public void copyProperties(ComercialSolution solution) {

		this.avaliacao = solution.getAvaliacao();
		this.descricao = solution.getDescricao();
		this.keyWords = solution.getKeyWords();
		this.nome = solution.getNome();
		this.publicar = solution.isPublicar();
		this.resumo = solution.getResumo();
		this.solutionType = solution.getSolutionType();
		this.utilizarFormatador = solution.getUtilizarFormatador();
		if(solution.getCategoria() != null)
			this.categoria = solution.getCategoria();
		if(solution instanceof SoftwareSolution){
			SoftwareSolution ss = (SoftwareSolution) solution;
			SoftwareSolution curr = (SoftwareSolution) this;
			curr.setLicencaUso(ss.getLicencaUso());
			curr.setPlataforma(ss.getPlataforma());
			curr.setSigla(((SoftwareSolution) solution).getSigla());
			curr.setSistemasOperacionais(ss.getSistemasOperacionais());
			curr.setVersao(ss.getVersao());
			curr.setUrlProjeto(ss.getUrlProjeto());
		}
	}


}