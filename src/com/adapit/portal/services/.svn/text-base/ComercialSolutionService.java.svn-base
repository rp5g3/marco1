package com.adapit.portal.services;

import java.util.List;

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

public interface ComercialSolutionService {

	@SuppressWarnings("unchecked")
	public List listCommercialSolutionsByWithName(String descricao);

	@SuppressWarnings("unchecked")
	public List listCommercialSolutionsByNameBeginingWith(String descricao);

	@SuppressWarnings("unchecked")
	public List listCommercialSolutionsByNameEndingWith(String descricao);

	@SuppressWarnings("unchecked")
	public List listCommercialSolutionsByDescription(String descricao);

	@SuppressWarnings("unchecked")
	public List listCommercialSolutionsByIdCategory(int id);

	@SuppressWarnings("unchecked")
	public List listCommercialSolutionsByValueRange(float valorA, float valorB);

	@SuppressWarnings("unchecked")
	public List listScheduledTrainingSolutions();

	@SuppressWarnings("unchecked")
	public List listNotScheduledTrainingSolutions();

	@SuppressWarnings("unchecked")
	public List listTrainingSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin,
			ScheduleScheduledTrainingFilterType loteKind, int firstResult);
	
	public List<ComercialSolution> listCommercialSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin, int firstResult);
	
	public List<SoftwareSolution> listSoftwareSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin, int firstResult);
	
	public Integer countTrainingSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin,
			ScheduleScheduledTrainingFilterType loteKind);
	
	public Integer countCommercialSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin);
	
	public Integer countSoftwareSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin);
	
	public List<CommercialSolutionDetailTab> listTabsByCommercialSolutionId(int id, String lang) throws Exception;

	@SuppressWarnings("unchecked")
	public List listAll();

	public ComercialSolution saveOrUpdate(ComercialSolution produto) throws Exception;

	public ComercialSolution deleteById(int id) throws Exception;

	public ComercialSolution deleteByTrainingSolutionItemId(int id) throws Exception;

	public ComercialSolution deleteByIdCategoria(int id) throws Exception;

	public ComercialSolution getCommercialSolutionById(int id) throws Exception;

	public ComercialSolution getCommercialSolutionByName(String descricao) throws Exception;
	
	public ComercialSolution getCommercialSolutionByIdCascadingProperties(java.io.Serializable id, boolean images, boolean categoria, boolean itemProduto) throws Exception;
	
	public ComercialSolution mergeCascadingProperties(ComercialSolution solution, boolean images, boolean categoria) throws Exception;
	
	public TrainingSolution mergeCascadingProperties(TrainingSolution solution, boolean images, boolean categoria, boolean itemProduto) throws Exception;
	
	public Imagem removeImageFromCommercialSolution(int idProd, int idImg) throws Exception;
	
	public ComercialSolution loadCommercialSolutionEagerImages(ComercialSolution produto) throws Exception;
	
	public ComercialSolution attachImageIntoCommercialSolution(ComercialSolution produto, List<Imagem> selectedImgs) throws Exception;
	
	public ComercialSolutionItem removeTrainingSolutionItemFromScheduledTrainig(ComercialSolutionItem ip) throws Exception;
	
	public ComercialSolutionHistory generateTrainingSolutionHistory(TrainingSolution produto, Treinamento lote, ComercialSolutionItem ip, boolean apagarProduto) throws Exception;
	
	public void deleteCommercialSolutionById(int id, ComercialSolution p) throws Exception;
	
	public ComercialSolutionItem saveOrUpdateComercialSolutionItem(ComercialSolutionItem ip, Participante cliente, Treinamento treinAgendadod) throws Exception;
	
	public TrainingFormationItem saveOrUpdateComercialSolutionItem(TrainingFormationItem ip, TrainingSolution solution, FormacaoTreinamento formacao) throws Exception;
	
	public TrainingSolution getTrainingSolutionByIdCascadingProperties(java.io.Serializable id, boolean images, boolean categoria, boolean instr) throws Exception;
	
	public SoftwareSolution getSoftwareSolutionByIdCascadingProperties(java.io.Serializable id, boolean images, boolean categoria) throws Exception;
	
	public void publishComercialSolutionBySolutionId(int id, boolean value) throws Exception;
	
	/////////////////////////19/09/2009/////////////////////////////
	
	public SoftwareDomainInterest getSoftwareDomainInterestByName(String name) throws Exception;
	
	public List<SoftwareSolution> listSoftwaresByDomainName(String name, Boolean pub) throws Exception;
	
	public List<SoftwareDomainInterest> listAllSoftwareDomainInterest(Integer swid, String sigle) throws Exception;
	
	public SoftwareDomainInterest merge(SoftwareDomainInterest domain, SoftwareSolution ss) throws Exception;
	
	public SoftwareDomainInterest unmerge(SoftwareDomainInterest domain, SoftwareSolution ss) throws Exception;
	
	public CssDefinition getCssDefinition(ComercialSolution solution) throws Exception;
	
	public List<CssDefinition> listCssDefinitions(String name) throws Exception;
	
	public CssDefinition saveOrUpdate(CssDefinition css) throws Exception;
	
	public void merge(ComercialSolution solution, CssDefinition css, boolean merge) throws Exception;

	public void generateHtmlCatalog(SoftwareDomainInterest domain) throws Exception;
}