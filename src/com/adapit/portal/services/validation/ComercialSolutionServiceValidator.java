package com.adapit.portal.services.validation;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.validation.BindException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.ComercialSolutionHistory;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.CommercialSolutionDetailTab;
import com.adapit.portal.entidades.CssDefinition;
import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.SoftwareDomainInterest;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.entidades.TrainingFormationItem;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.services.ComercialSolutionService;
import com.adapit.portal.services.ScheduleScheduledTrainingFilterType;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.dao.hibernate.FileServiceDAOHibernate;
import com.adapit.portal.services.local.LocalImagemService;
import com.adapit.portal.util.global.GlobalVariables;

/**
 * @spring.bean id="comercialSolutionService" singleton="true"
 */
public class ComercialSolutionServiceValidator extends AbstractValidator implements
		ComercialSolutionService {

	private DefaultBeanValidator validator;

	private ComercialSolutionService comercialSolutionService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setComercialSolutionService(ComercialSolutionService comercialSolutionService) {
		this.comercialSolutionService = comercialSolutionService;
	}

	/**
	 * @spring.property ref="comercialSolutionServiceDAOHibernate" singleton="true"
	 */
	public ComercialSolutionService getComercialSolutionService() {
		return this.comercialSolutionService;
	}

	public ComercialSolutionServiceValidator() {
		super();
		setName("comercialSolutionServiceValidator");
	}

	/**
	 * Retorna todos os comercialSolutions em que a descrição contenha o valor passado
	 * como argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByWithName(String descricao) {
		try {
			return this.comercialSolutionService.listCommercialSolutionsByWithName(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o comercialSolution em que a descrição inicia pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByNameBeginingWith(String descricao) {
		try {
			return this.comercialSolutionService.listCommercialSolutionsByNameBeginingWith(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o comercialSolution em que a descrição termina pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByNameEndingWith(String descricao) {
		try {
			return this.comercialSolutionService.listCommercialSolutionsByNameEndingWith(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna o comercialSolution em que a descrição é igual ao valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByDescription(String descricao) {
		try {
			return this.comercialSolutionService.listCommercialSolutionsByDescription(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os comercialSolutions em que a categoria é igual ao id passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByIdCategory(int id) {
		try {
			return this.comercialSolutionService.listCommercialSolutionsByIdCategory(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os comercialSolutions em que a avaliação esteja na faixa de valores
	 * passados como argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByValueRange(float valorA, float valorB) {
		try {
			return this.comercialSolutionService.listCommercialSolutionsByValueRange(valorA, valorB);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os comercialSolutions já loteados
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listScheduledTrainingSolutions() {
		try {
			return this.comercialSolutionService.listScheduledTrainingSolutions();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os comercialSolutions não loteados
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listNotScheduledTrainingSolutions() {
		try {
			return this.comercialSolutionService.listNotScheduledTrainingSolutions();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List listTrainingSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin,
			ScheduleScheduledTrainingFilterType loteKind, int firstResult) {
		try {
			return this.comercialSolutionService.listTrainingSolutionsAccordingTo(descricao, descKind,
					categoria, valorInic, valorFin, loteKind, firstResult);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@Override
	public Integer countTrainingSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin,
			ScheduleScheduledTrainingFilterType loteKind) {
		try {
			return this.comercialSolutionService.countTrainingSolutionsAccordingTo(descricao, descKind,
					categoria, valorInic, valorFin, loteKind);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List listAll() {
		try {
			return this.comercialSolutionService.listAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public ComercialSolution saveOrUpdate(ComercialSolution comercialSolution) throws Exception {
		BindException errors = new BindException(comercialSolution, "comercialSolution");
		validator.validate(comercialSolution, errors);
		if (errors.hasErrors()) {
			throw new ValidationException("exceptions.invalidFields");
		} else {
			try {
				return this.comercialSolutionService.saveOrUpdate(comercialSolution);
			} catch (Exception dive) {
				dive.printStackTrace();
				throw dive;
			} 
		}
	}

	/**
	 * Remove o comercialSolution que contém o identificador passado como argumento
	 */
	@Override
	public ComercialSolution deleteById(int id) throws Exception {
		try {
			return this.comercialSolutionService.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByIdError");
		}
	}

	/**
	 * Remove todos os comercialSolutions que pertençam a um lote
	 */
	@Override
	public ComercialSolution deleteByTrainingSolutionItemId(int id) throws Exception {
		try {
			return this.comercialSolutionService.deleteByTrainingSolutionItemId(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByIdLoteError");
		}
	}

	/**
	 * Remove todos os comercialSolutions de uma determinada categoria
	 */
	@Override
	public ComercialSolution deleteByIdCategoria(int id) throws Exception {
		try {
			return this.comercialSolutionService.deleteByIdCategoria(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByIdCategoriaError");
		}
	}

	/**
	 * Retorna um comercialSolution pelo identificador
	 */
	@Override
	public ComercialSolution getCommercialSolutionById(int id) throws Exception {
		try {
			return this.comercialSolutionService.getCommercialSolutionById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("getComercialSolutionByIdError");
		}
	}

	/**
	 * Retorna um comercialSolution pela descrição
	 */
	@Override
	public ComercialSolution getCommercialSolutionByName(String descricao) throws Exception {
		try {
			return this.comercialSolutionService.getCommercialSolutionByName(descricao);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("getComercialSolutionByDescricaoError");
		}
	}

	@Override
	public ComercialSolution getCommercialSolutionByIdCascadingProperties(Serializable id,
			boolean images, boolean categoria, boolean itemComercialSolution)
			throws Exception {
		return comercialSolutionService.getCommercialSolutionByIdCascadingProperties(id, images, categoria, itemComercialSolution);
	}

	@Override
	public TrainingSolution mergeCascadingProperties(TrainingSolution comercialSolution, boolean images,
			boolean categoria, boolean itemComercialSolution) throws Exception {
		return comercialSolutionService.mergeCascadingProperties(comercialSolution, images, categoria, itemComercialSolution);
	}

	@Override
	public Imagem removeImageFromCommercialSolution(int idProd, int idImg)
			throws Exception {
		return comercialSolutionService.removeImageFromCommercialSolution(idProd, idImg);
	}
	
	@Override
	public ComercialSolution loadCommercialSolutionEagerImages(ComercialSolution comercialSolution) throws Exception {
		return comercialSolutionService.loadCommercialSolutionEagerImages(comercialSolution);
	}

	@Override
	public ComercialSolution attachImageIntoCommercialSolution(ComercialSolution comercialSolution,
			List<Imagem> selectedImgs) throws Exception {
		return comercialSolutionService.attachImageIntoCommercialSolution(comercialSolution, selectedImgs);
	}

	@Override
	public ComercialSolutionHistory generateTrainingSolutionHistory(TrainingSolution comercialSolution, Treinamento lote,
			ComercialSolutionItem ip, boolean apagarComercialSolution) throws Exception {
		return comercialSolutionService.generateTrainingSolutionHistory(comercialSolution, lote, ip, apagarComercialSolution);
	}

	@Override
	public ComercialSolutionItem removeTrainingSolutionItemFromScheduledTrainig(ComercialSolutionItem ip)
			throws Exception {
		return comercialSolutionService.removeTrainingSolutionItemFromScheduledTrainig(ip);
	}
	
	@Override
	public void deleteCommercialSolutionById(int id, ComercialSolution p) throws Exception {
		comercialSolutionService.deleteCommercialSolutionById(id,p);
	}
	
	@Override
	public ComercialSolutionItem saveOrUpdateComercialSolutionItem(
			ComercialSolutionItem ip, Participante cliente,
			Treinamento treinAgendadod) throws Exception {
		return comercialSolutionService.saveOrUpdateComercialSolutionItem(ip, cliente, treinAgendadod);
	}
	
	@Override
	public TrainingFormationItem saveOrUpdateComercialSolutionItem(TrainingFormationItem ip, TrainingSolution solution, FormacaoTreinamento formacao) throws Exception {
		return comercialSolutionService.saveOrUpdateComercialSolutionItem(ip, solution, formacao);
	}
	
	@Override
	public List<ComercialSolution> listCommercialSolutionsAccordingTo(String descricao,
			StringQueryKind descKind, Categoria categoria, float valorInic,
			float valorFin, int firstResult) {
		return comercialSolutionService.listCommercialSolutionsAccordingTo(descricao, descKind, categoria, valorInic, valorFin, firstResult);
	}
	
	@Override
	public Integer countCommercialSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin) {
		return comercialSolutionService.countCommercialSolutionsAccordingTo(descricao, descKind, categoria, valorInic, valorFin);
	}
	
	@Override
	public SoftwareSolution getSoftwareSolutionByIdCascadingProperties(
			Serializable id, boolean images, boolean categoria)
			throws Exception {
		return comercialSolutionService.getSoftwareSolutionByIdCascadingProperties(id, images, categoria);
	}

	@Override
	public TrainingSolution getTrainingSolutionByIdCascadingProperties(
			Serializable id, boolean images, boolean categoria, boolean instr)
			throws Exception {
		return comercialSolutionService.getTrainingSolutionByIdCascadingProperties(id, images, categoria, instr);
	}
	
	@Override
	public List<SoftwareSolution> listSoftwareSolutionsAccordingTo(String descricao,
			StringQueryKind descKind, Categoria categoria, float valorInic,
			float valorFin, int firstResult) {
		return comercialSolutionService.listSoftwareSolutionsAccordingTo(descricao, descKind, categoria, valorInic, valorFin, firstResult);
	}
	
	@Override
	public Integer countSoftwareSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin) {
		return comercialSolutionService.countSoftwareSolutionsAccordingTo(descricao, descKind, categoria, valorInic, valorFin);
	}
	
	@Override
	public void publishComercialSolutionBySolutionId(int id, boolean value)	throws Exception {
		comercialSolutionService.publishComercialSolutionBySolutionId(id, value);
	}
	
	@Override
	public ComercialSolution mergeCascadingProperties(
			ComercialSolution solution, boolean images, boolean categoria)
			throws Exception {
		return comercialSolutionService.mergeCascadingProperties(solution, images, categoria);
	}
	
	@Override
	public List<CommercialSolutionDetailTab> listTabsByCommercialSolutionId(
			int id, String lang) throws Exception {
		return comercialSolutionService.listTabsByCommercialSolutionId(id, lang);
	}
	
	@Override
	public SoftwareDomainInterest getSoftwareDomainInterestByName(String name)
			throws Exception {
		return comercialSolutionService.getSoftwareDomainInterestByName(name);
	}

	@Override
	public List<SoftwareDomainInterest> listAllSoftwareDomainInterest(Integer swid, String sigle)
			throws Exception {
		return comercialSolutionService.listAllSoftwareDomainInterest(swid,sigle);
	}

	@Override
	public List<SoftwareSolution> listSoftwaresByDomainName(String name, Boolean pub)
			throws Exception {
		return comercialSolutionService.listSoftwaresByDomainName(name,pub);
	}

	@Override
	public SoftwareDomainInterest merge(SoftwareDomainInterest domain,
			SoftwareSolution ss) throws Exception {
		return comercialSolutionService.merge(domain, ss);
	}

	@Override
	public SoftwareDomainInterest unmerge(SoftwareDomainInterest domain,
			SoftwareSolution ss) throws Exception {
		return comercialSolutionService.unmerge(domain, ss);
	}

	@Override
	public CssDefinition getCssDefinition(ComercialSolution solution)
			throws Exception {
		return comercialSolutionService.getCssDefinition(solution);
	}

	@Override
	public List<CssDefinition> listCssDefinitions(String name) throws Exception {
		return comercialSolutionService.listCssDefinitions(name);
	}

	@Override
	public void merge(ComercialSolution solution, CssDefinition css,
			boolean merge) throws Exception {
		comercialSolutionService.merge(solution, css, merge);
	}

	@Override
	public CssDefinition saveOrUpdate(CssDefinition css) throws Exception {
		return comercialSolutionService.saveOrUpdate(css);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void generateHtmlCatalog(SoftwareDomainInterest domain)
			throws Exception {
		//String html="";
		Map model = new HashMap();
		model.put("adapitsite", GlobalVariables.URL);
		model.put("sd", domain);
		
		if(domain.getSoftwares() != null && domain.getSoftwares().size()>0){
			for(SoftwareSolution ss: domain.getSoftwares()){
				ComercialSolution cc = loadCommercialSolutionEagerImages(ss);
				if(cc != null){
					if(cc.getImagens() != null && cc.getImagens().size()>0){
						ss.setImagens(cc.getImagens());
						if(ss.getImagens() != null && ss.getImagens().size()>0){
							for(Imagem im: ss.getImagens()){
								im.getId();
								im.getRotulo();
							}
						}
					}
				}
				List<CommercialSolutionDetailTab> list = listTabsByCommercialSolutionId(
						ss.getId(), "PT-BR");
				System.out.println("número de abas "+list.size());
				ss.setTransientDetails(list);
			}
		}
		
		String textVelocity = VelocityEngineUtils
				.mergeTemplateIntoString(
						velocityEngine,
						"com/adapit/portal/services/velocity/welcome_domain_catalog.vm",
						model);
		textVelocity = textVelocity.replace("src=\"imgs", "src=\"../imgs")
		.replace("<img src=\"comercialSolutionBigImage", "<img src=\"../comercialSolutionBigImage");
		//System.out.println(textVelocity);
		File f = new File(GlobalVariables.WEBAPPS+"/ROOT/domainCatalog"+domain.getId());
		f.mkdirs();
		FileServiceDAOHibernate.saveFileToDirectory(textVelocity.getBytes("UTF-8"), GlobalVariables.WEBAPPS+"ROOT/domainCatalog"+domain.getId()+"/index.jsp");
	}
	
	private VelocityEngine velocityEngine;
	/**
	 * @spring.property ref="velocityEngine" singleton="true"
	 */
	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
}