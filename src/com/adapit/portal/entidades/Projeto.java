package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.workcase.utils.IdGenerator;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(
		name="Projeto_GEN",
		allocationSize=1,initialValue=1,sequenceName="SoftwareProjectSeq"
)
@Table(name="SoftwareProject")
public class Projeto implements Serializable {

	
	private static final long serialVersionUID = 1235124143264378983L;

	private int id;

	private String descricao;

	private float avaliacao;

	private ScheduledTrainingStatus status;

	private String codigoProjeto;

	private boolean retirado;

	private Cliente financiador;

	private Fornecedor fornecedor;

	protected List<ItemProjeto> itensProjeto = new ArrayList<ItemProjeto>();
	
	protected Collection<AgendaProjeto> agendas = new ArrayList<AgendaProjeto>();
	
	protected AgendaProjeto agendaProjeto;
	
	protected Collection<Cliente> interessados = new ArrayList<Cliente>();
	
	protected Collection<Cliente> compradores = new ArrayList<Cliente>();
	
	public void setFinanciador(Cliente comprador) {
		this.financiador = comprador;
	}

	@ManyToOne(targetEntity = Cliente.class, cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
	public Cliente getFinanciador() {
		return this.financiador;
	}


	public void setFornecedor(Fornecedor comitente) {
		this.fornecedor = comitente;
	}

	
	@ManyToOne(targetEntity = Fornecedor.class, cascade = { CascadeType.REFRESH},fetch = FetchType.LAZY)
	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setItensProjeto(List<ItemProjeto> itensProduto) {
		this.itensProjeto = itensProduto;
	}


	@OneToMany(targetEntity = ItemProjeto.class, mappedBy = "projeto", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	public List<ItemProjeto> getItensProjeto() {
		return this.itensProjeto;
	}

	public void setId(int id) {
		this.id = id;
	}

	@GeneratedValue(strategy=GenerationType.TABLE, generator="Projeto_GEN")
	@Id
	public int getId() {
		return this.id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(nullable = true, length = 10000)
	public String getDescricao() {
		return this.descricao;
	}


	public void setAvaliacao(float avaliacao) {
		this.avaliacao = avaliacao;
	}

	@Column(nullable = false)
	public float getAvaliacao() {
		return this.avaliacao;
	}

	public void setStatus(ScheduledTrainingStatus status) {
		this.status = status;
	}

	@Enumerated(EnumType.STRING)
	public ScheduledTrainingStatus getStatus() {
		return this.status;
	}

	/**
	 * 
	 * @spring.validator arg0resource="projeto.codigoProjeto" maxlength="4" minlength="3"
	 *                   type="required"
	 */
	public void setCodigoProjeto(String codProj) {
		this.codigoProjeto = codProj;
	}

	@Column(nullable = false, length = 4)
	public String getCodigoProjeto() {
		return this.codigoProjeto;
	}

	public void setRetirado(boolean retirado) {
		this.retirado = retirado;
	}

	public boolean getRetirado() {
		return this.retirado;
	}

	
	

	
	
	@OneToMany(mappedBy="projeto",fetch=FetchType.LAZY,cascade={CascadeType.REFRESH,CascadeType.REMOVE})
	public Collection<AgendaProjeto> getAgendas() {
		return agendas;
	}

	public void setAgendas(Collection<AgendaProjeto> agendas) {
		this.agendas = agendas;
	}

	@OneToOne (cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@Column(name="agendaprojeto_id")
	public AgendaProjeto getAgendaProjeto() {
		return agendaProjeto;
	}

	public void setAgendaProjeto(AgendaProjeto agendaLote) {
		this.agendaProjeto = agendaLote;
	}

	@ManyToMany (fetch=FetchType.LAZY,targetEntity=Cliente.class,cascade={CascadeType.MERGE})
	@JoinTable(name="CLIENTE_INTERESSE_PROJETO")
	public Collection<Cliente> getInteressados() {
		return interessados;
	}

	public void setInteressados(Collection<Cliente> interessados) {
		this.interessados = interessados;
	}
	
	@ManyToMany (fetch=FetchType.LAZY,targetEntity=Cliente.class,cascade={CascadeType.MERGE})
	@JoinTable(name="CLIENTE_COMPRA_PROJETO")
	public Collection<Cliente> getCompradores() {
		return compradores;
	}

	public void setCompradores(Collection<Cliente> compradores) {
		this.compradores = compradores;
	}
	
	@Transient
	public String getFormatedDescricao(){
		String str="";
		String formated = getDescricao();
		if (formated != null && formated.indexOf("]")>0){
			try {
				String itens[] = formated.split("]");
				for (int i=0; i < itens.length; i++){
					
					String item = itens[i];
					if (item != null && !item.equals("") && item.indexOf("[") > 0){
						String title = item.substring(0, item.indexOf("["));
						String content = item.substring(item.indexOf("[")+1, item.length());
						if (title != null && !title.equals("")){
							str+="<table width=\"100%\" class=\"presentation\"><tr><th colspan=\"2\" height=\"30px\">"+title+"</th></tr>";
							String dados[] = content.split(",");
							for (int j=0; j < dados.length; j++){
								str+="<tr>";
								String dado = dados[j];
								String attval[] = dado.split(":");
								if (attval.length == 2){
									String att = attval[0];
									str+="<td><b>"+att+":</b></td>";
									String val = attval[1];
									str+="<td>"+val+"</td>";
								}
								str+="</tr>";
							}
							str+="</table>";
						}
					}
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
				return getDescricao();
			}
		}else return getDescricao();
		return str;
	}
	
	@Transient
	public String getDescricaoAsTabbedPane(){
		String str = getFormatedDescricao();
		String gen = IdGenerator.getInstance().generateId(3);
		str = "<div id=\"descTab"+getId()+gen+"\" dojoType=\"dijit.layout.ContentPane\" style=\"width:385px; height:160px; \" cacheContent=\"false\">"+str+"</div>";
		return str;
	}
	
	
	
}