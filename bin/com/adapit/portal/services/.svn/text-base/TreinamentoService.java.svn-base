package com.adapit.portal.services;

import java.util.List;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.entidades.TrainingFormationItem;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;

public interface TreinamentoService {

	@SuppressWarnings("unchecked")
	@Deprecated
	public List listScheduledTrainingLikeCodigo(String codLote);

	@SuppressWarnings("unchecked")
	@Deprecated
	public List listScheduledTrainingByCodigoEndingWith(String codLote);

	@SuppressWarnings("unchecked")
	@Deprecated
	public List listScheduledTrainingByCodigoBeginingWith(String codLote);

	@SuppressWarnings("unchecked")
	public List listScheduledTrainingByStatus(ScheduledTrainingStatus status);

	@SuppressWarnings("unchecked")
	@Deprecated
	public List listScheduledTrainingLikeNomeAutor(Participante joinAutor);

	@SuppressWarnings("unchecked")
	@Deprecated
	public List listScheduledTrainingByIdAutor(int joinAutorId);

	@SuppressWarnings("unchecked")
	public List listAllScheduledTraining();

	public boolean saveOrUpdate(Treinamento treinamento) throws Exception;

	public Treinamento deleteById(int id) throws Exception;

	@Deprecated
	public Treinamento deleteByCodigo(String codLote) throws Exception;

	@Deprecated
	public Treinamento deleteByIdAutor(Participante joincompraComprador)
			throws Exception;

	public Treinamento getScheduledTrainingById(int id) throws Exception;

	@Deprecated
	public Treinamento getScheduledTrainingByCodigo(String codLote) throws Exception;
	
	public AgendaTreinamento getScheduledTrainingScheduleIdByScheduledTrainingId(int loteId) throws Exception;
	
	public Object filterScheduledTrainingBy(ScheduledTrainingFilterType tipoFiltro, int firstResultIndex,	int tabSelectedIndex, 
			boolean filtrarSomenteLeilao, Boolean filtrarRetirados,	Boolean filtrarArrematados, 
			Boolean filtrarComPagamentoQuitado, Integer idLeilao,String statusLote,	String descricaoLote, 
			String numeroProcesso, Integer paiSubLote,	int orderBy, int secBegin) throws Exception;
	
	public void removeScheduledTraining(Treinamento t, TurmaTreinamento turma) throws Exception;
	
	@Deprecated
	public Participante getAuthorByScheduledTrainingId(int idTreinamento) throws Exception;
	
	public List<Treinamento> listAllScheduledTrainingByTurmaId(int idTurma) throws Exception;
	
	public Treinamento updateScheduledTrainingProperties(Treinamento treinamento) throws Exception;
	
	public Treinamento loadScheduledTrainingById(int idtrei, boolean cascadeAgendas) throws Exception;
	
	public void delete(Treinamento l) throws Exception;
	
	public List<ComercialSolutionItem> getComercialSolutionItensByScheduledTrainingId(int treinamentoId) throws Exception;
	
	public void mergeNewTrainingSolutionOnScheduledTraining(TrainingSolution prod, Treinamento treinamento) throws Exception;

	public void removeTrainingSolutionFromScheduledTraining(boolean definitivo, int idItemProd) throws Exception;
	
	public void delete(Treinamento l, int idTurma) throws Exception;
	
	public Object[] getScheduledTrainingItemPropertiesByItemId(int idItem) throws Exception;
	
	public void createFormationWithTrainingSolutions(TrainingSolution prods[], FormacaoTreinamento treinamento) throws Exception;
	
	public FormacaoTreinamento loadTrainingFormationByFormationId(int idFormacao) throws Exception;
	
	public List<ComercialSolutionItem> getTrainingSolutionItensByFormationId(int formacaoId) throws Exception;
	
	public TrainingFormationItem mergeNewTrainingSolutionOnTrainingFormation(TrainingSolution solution, FormacaoTreinamento treinamento) throws Exception;
	
	public void delete(FormacaoTreinamento l, int idTurma) throws Exception;
		
	public List<FormacaoTreinamento> listAllTrainingFormations() throws Exception;
	
	public FormacaoTreinamento updateTrainingFormationProperties(FormacaoTreinamento treinamento) throws Exception;
	
	public void createScheduledTrainingWithTrainingSolutions(TrainingSolution prods[], Treinamento treinamento) throws Exception;
	
	public Object[] getTrainingFormationItemPropertiesByItemId(int idItem) throws Exception;
	
	public List<TrainingFormationItem> getTrainingFormationItensByFormationId(int formacaoId) throws Exception;
	
	public void removeTrainingSolutionFromTrainingFormation(boolean definitivo, int idFormationId) throws Exception;
	
	public ComercialSolutionItem mergeParticipanteOnScheduledTraining(Participante participante, Treinamento scheduledTraining) throws Exception;
	
	public FormacaoTreinamento getFormacaoById(int id) throws Exception;
	
	public List<TrainingSolution> listTrainingSolutionsByKeywords(String kw[]) throws Exception;
	
	public List<TrainingSolution> listAllTrainingSolutions() throws Exception;
	
	public List<Treinamento> createTrainemantosByFormacaoIdAndTurmaId(int fid, int tid) throws Exception;
}