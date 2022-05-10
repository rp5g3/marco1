package com.adapit.portal.services.validation;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.entidades.TrainingFormationItem;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.services.ScheduledTrainingFilterType;
import com.adapit.portal.services.TreinamentoService;
/**
 * @spring.bean id="treinamentoService" singleton="true"
 */
@SuppressWarnings({"serial","unchecked","unused","static-access","deprecation"})
public class TreinamentoServiceValidator extends AbstractValidator implements
		TreinamentoService {

	private DefaultBeanValidator validator;

	private TreinamentoService treinamentoService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setTreinamentoService(TreinamentoService loteService) {
		this.treinamentoService = loteService;
	}

	/**
	 * @spring.property ref="treinamentoServiceDAOHibernate" singleton="true"
	 */
	public TreinamentoService getTreinamentoService() {
		return this.treinamentoService;
	}

	public TreinamentoServiceValidator() {
		super();
		setName("treinamentoServiceValidator");
	}

	/**
	 * Retorna todos os lotes que tiverem no código do lote o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listScheduledTrainingLikeCodigo(String codLote) {
		try {
			return this.treinamentoService.listScheduledTrainingLikeCodigo(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes em que o cõdigo termina com o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listScheduledTrainingByCodigoEndingWith(String codLote) {
		try {
			return this.treinamentoService.listScheduledTrainingByCodigoEndingWith(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes em que o código inicia com o valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listScheduledTrainingByCodigoBeginingWith(String codLote) {
		try {
			return this.treinamentoService.listScheduledTrainingByCodigoBeginingWith(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}


	/**
	 * Retorna todos os lotes em que o status é igual ao valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	public List listScheduledTrainingByStatus(ScheduledTrainingStatus status) {
		try {
			return this.treinamentoService.listScheduledTrainingByStatus(status);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes de um comprador que possui no nome um valor igual
	 * ao do argumento passado como parâmetro
	 */
	@SuppressWarnings("unchecked")
	public List listScheduledTrainingLikeNomeAutor(Participante joincompraComprador) {
		try {
			return this.treinamentoService.listScheduledTrainingLikeNomeAutor(joincompraComprador);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna todos os lotes de um comprador que possui o id igual ao do
	 * argumenta passado como parâmetro
	 */
	@SuppressWarnings("unchecked")
	public List listScheduledTrainingByIdAutor(int joinPessoaId) {
		try {
			return this.treinamentoService.listScheduledTrainingByIdAutor(joinPessoaId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}



	@SuppressWarnings("unchecked")
	public List listAllScheduledTraining() {
		try {
			return this.treinamentoService.listAllScheduledTraining();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public boolean saveOrUpdate(Treinamento lote) throws Exception {
		System.out.println("saveOrUpdate VALIDACAO");
		try {
			BindException errors = new BindException(lote, "lote");
			validator.validate(lote, errors);
			if (errors.hasErrors()) {
				throw new Exception("exceptions.invalidFields");
			} else {
				try {
					return this.treinamentoService.saveOrUpdate(lote);
				} catch (DataIntegrityViolationException dive) {
					dive.printStackTrace();
					throw new Exception("lote.crudError");
				} catch (DataAccessException dae) {
					dae.printStackTrace();
					throw new Exception("exceptions.saveOrUpdate");
				}
			}
		} catch (Exception e) {
			// 
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Apaga o lote pelo identificador do lote
	 */
	public Treinamento deleteById(int id) throws Exception {
		try {
			return this.treinamentoService.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByIdError");
		}
	}

	/**
	 * apga o lote que possui o código igual ao valor passado como argumento
	 */
	public Treinamento deleteByCodigo(String codLote) throws Exception {
		try {
			return this.treinamentoService.deleteByCodigo(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByCodLoteError");
		}
	}


	/**
	 * apaga todos os lotes que foram comprados por uma determinada pessoa. Esta
	 * pessoa é idetificado pelo parametro id.
	 */
	public Treinamento deleteByIdAutor(Participante joincompraComprador)
			throws Exception {
		try {
			return this.treinamentoService.deleteByIdAutor(joincompraComprador);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("deleteByIdParticipanteError");
		}
	}

	public Treinamento getScheduledTrainingById(int id) throws Exception {
		try {
			return this.treinamentoService.getScheduledTrainingById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("getLoteByIdError");
		}
	}

	public Treinamento getScheduledTrainingByCodigo(String codLote) throws Exception {
		try {
			return this.treinamentoService.getScheduledTrainingByCodigo(codLote);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("getLoteByCodLoteError");
		}
	}

	
	@Override
	public AgendaTreinamento getScheduledTrainingScheduleIdByScheduledTrainingId(int loteId) throws Exception {
		return treinamentoService.getScheduledTrainingScheduleIdByScheduledTrainingId(loteId);
	}


	
	@Override
	public Object filterScheduledTrainingBy(ScheduledTrainingFilterType tipoFiltro, int firstResultIndex,	int tabSelectedIndex, 
			boolean filtrarSomenteLeilao, Boolean filtrarRetirados,	Boolean filtrarArrematados, 
			Boolean filtrarComPagamentoQuitado, Integer idLeilao,String statusLote,	String descricaoLote, 
			String numeroProcesso, Integer paiSubLote,	int orderBy, int secBegin) throws Exception{
		
		return treinamentoService.filterScheduledTrainingBy(tipoFiltro, firstResultIndex, tabSelectedIndex, filtrarSomenteLeilao, filtrarRetirados, filtrarArrematados, filtrarComPagamentoQuitado, idLeilao, statusLote, descricaoLote, numeroProcesso, paiSubLote, orderBy, secBegin);
	}

	@Override
	public void removeScheduledTraining(Treinamento l, TurmaTreinamento leilao) throws Exception {
		treinamentoService.removeScheduledTraining(l, leilao);
	}
	
	@Override
	public Participante getAuthorByScheduledTrainingId(int idLote) throws Exception {
		return treinamentoService.getAuthorByScheduledTrainingId(idLote);
	}

	

	@Override
	public void delete(Treinamento l) throws Exception {
		treinamentoService.delete(l);
	}

	@Override
	public List<Treinamento> listAllScheduledTrainingByTurmaId(int idLeilao) throws Exception {
		return treinamentoService.listAllScheduledTrainingByTurmaId(idLeilao);
	}

	@Override
	public Treinamento loadScheduledTrainingById(int idLote, boolean cascadeAgendas) throws Exception {
		return treinamentoService.loadScheduledTrainingById(idLote,cascadeAgendas);
	}

	@Override
	public Treinamento updateScheduledTrainingProperties(Treinamento lote) throws Exception {
		return treinamentoService.updateScheduledTrainingProperties(lote);
	}

	@Override
	public List<ComercialSolutionItem> getComercialSolutionItensByScheduledTrainingId(int loteId) throws Exception {
		return treinamentoService.getComercialSolutionItensByScheduledTrainingId(loteId);
	}
	
	
	@Override
	public void removeTrainingSolutionFromScheduledTraining(boolean definitivo, int idItemProd)
			throws Exception {
		treinamentoService.removeTrainingSolutionFromScheduledTraining(definitivo, idItemProd);
	}

	@Override
	public void delete(Treinamento l, int idLeilao) throws Exception {
		treinamentoService.delete(l, idLeilao);
	}
	
	@Override
	public Object[] getScheduledTrainingItemPropertiesByItemId(int idItem) throws Exception {
		return treinamentoService.getScheduledTrainingItemPropertiesByItemId(idItem);
	}

	@Override
	public void createFormationWithTrainingSolutions(TrainingSolution prods[], FormacaoTreinamento treinamento) throws Exception{
		treinamentoService.createFormationWithTrainingSolutions(prods, treinamento);
	}

	@Override
	public void delete(FormacaoTreinamento l, int idTurma) throws Exception {
		treinamentoService.delete(l, idTurma);
	}

	@Override
	public List<ComercialSolutionItem> getTrainingSolutionItensByFormationId(
			int formacaoId) throws Exception {		
		return treinamentoService.getTrainingSolutionItensByFormationId(formacaoId);
	}

	@Override
	public List<FormacaoTreinamento> listAllTrainingFormations()
			throws Exception {		
		return treinamentoService.listAllTrainingFormations();
	}

	@Override
	public FormacaoTreinamento loadTrainingFormationByFormationId(int idFormacao)
			throws Exception {
		return treinamentoService.loadTrainingFormationByFormationId(idFormacao);
	}

	@Override
	public TrainingFormationItem mergeNewTrainingSolutionOnTrainingFormation(TrainingSolution solution, FormacaoTreinamento treinamento) throws Exception{
		return treinamentoService.mergeNewTrainingSolutionOnTrainingFormation(solution, treinamento);
	}

	@Override
	public FormacaoTreinamento updateTrainingFormationProperties(
			FormacaoTreinamento treinamento) throws Exception {
		return treinamentoService.updateTrainingFormationProperties(treinamento);
	}
	
	@Override
	public void createScheduledTrainingWithTrainingSolutions(
			TrainingSolution[] prods, Treinamento treinamento) throws Exception {
		treinamentoService.createScheduledTrainingWithTrainingSolutions(prods, treinamento);
	}
	
	@Override
	public Object[] getTrainingFormationItemPropertiesByItemId(int idItem)
			throws Exception {
		return treinamentoService.getTrainingFormationItemPropertiesByItemId(idItem);
	}

	@Override
	public List<TrainingFormationItem> getTrainingFormationItensByFormationId(
			int formacaoId) throws Exception {
		return treinamentoService.getTrainingFormationItensByFormationId(formacaoId);
	}
	
	@Override
	public void removeTrainingSolutionFromTrainingFormation(boolean definitivo,
			int idFormationId) throws Exception {
		treinamentoService.removeTrainingSolutionFromTrainingFormation(definitivo, idFormationId);
	}

	@Override
	public void mergeNewTrainingSolutionOnScheduledTraining(
			TrainingSolution prod, Treinamento treinamento) throws Exception {
		treinamentoService.mergeNewTrainingSolutionOnScheduledTraining(prod, treinamento);
	}

	@Override
	public ComercialSolutionItem mergeParticipanteOnScheduledTraining(Participante participante, Treinamento scheduledTraining)	throws Exception {
		return treinamentoService.mergeParticipanteOnScheduledTraining(participante, scheduledTraining);
	}
	
	@Override
	public FormacaoTreinamento getFormacaoById(int id) throws Exception{
		return treinamentoService.getFormacaoById(id);
	}
	
	@Override
	public List<TrainingSolution> listTrainingSolutionsByKeywords(String[] kw)
			throws Exception {
		return treinamentoService.listTrainingSolutionsByKeywords(kw);
	}

	@Override
	public List<TrainingSolution> listAllTrainingSolutions() throws Exception {		
		return treinamentoService.listAllTrainingSolutions();
	}

	@Override
	public List<Treinamento> createTrainemantosByFormacaoIdAndTurmaId(int fid,
			int tid) throws Exception {
		return treinamentoService.createTrainemantosByFormacaoIdAndTurmaId(fid, tid);
	}
	
}