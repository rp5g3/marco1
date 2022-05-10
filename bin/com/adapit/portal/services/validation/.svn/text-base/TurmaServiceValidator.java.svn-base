package com.adapit.portal.services.validation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.ClassificacaoTreinamento;
import com.adapit.portal.entidades.CondicaoParticipacaoTreinamento;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.TipoExecucaoTreinamento;
import com.adapit.portal.entidades.TipoPacoteTreinamento;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.entidades.TurnoTreinamento;
import com.adapit.portal.services.TrainingClassFilterType;
import com.adapit.portal.services.TurmaService;

/**
 * @spring.bean id="turmaService" singleton="true"
 */
@SuppressWarnings({"serial","unchecked","unused","static-access","deprecation"})
public class TurmaServiceValidator extends AbstractValidator implements TurmaService, Serializable {

	private DefaultBeanValidator validator;

	private TurmaService turmaService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setTurmaService(TurmaService userService) {
		this.turmaService = userService;
	}

	/**
	 * @spring.property ref="turmaServiceDAOHibernate" singleton="true"
	 */
	public TurmaService getTurmaService() {
		return this.turmaService;
	}

	public TurmaServiceValidator() {
		super();
		setName("turmaServiceValidator");
	}

	@Override
	public List<Object[]> listTrainingClassValues() throws Exception {
		return turmaService.listTrainingClassValues();
	}
	
	@Override
	public TurmaTreinamento loadTrainingClassByTrainingClassId(int idLeilao) throws Exception {
		return turmaService.loadTrainingClassByTrainingClassId(idLeilao);
	}
	
	@Override
	public List<TurmaTreinamento> listTrainingClassesOnValidInterval() throws Exception {
		return turmaService.listTrainingClassesOnValidInterval();
	}

	@Override
	public Object listTrainingClassAccordingTo(TrainingClassFilterType filtro, int left,
			boolean usarEndereco, int idEndereco, boolean usarData,
			Date dataTreinamento, Date dataEncerramento,
			boolean usarRegras, ClassificacaoTreinamento classificacaoLeilao,
			TipoPacoteTreinamento subClassificacaoLeilao,
			TurnoTreinamento regraExecucaoLeilao,
			TipoExecucaoTreinamento tipoExecucaoLeilao, int orderBy)
			throws Exception {
		return turmaService.listTrainingClassAccordingTo(filtro, left, usarEndereco, idEndereco, usarData, dataTreinamento, dataEncerramento, usarRegras, classificacaoLeilao, subClassificacaoLeilao, regraExecucaoLeilao, tipoExecucaoLeilao, orderBy);
	}
	


	@Override
	public List<Treinamento> listScheduledTrainingsByTrainingClassId(int idLeilao) throws Exception {
		return turmaService.listScheduledTrainingsByTrainingClassId(idLeilao);
	}



	@Override
	public List<Treinamento> listAllScheduledTrainingsWithoutTrainingClass() throws Exception {
		return turmaService.listAllScheduledTrainingsWithoutTrainingClass();
	}

	@Override
	public List<Treinamento> listAllScheduledTrainingsWithoutDefinedSchedule() throws Exception {
		return turmaService.listAllScheduledTrainingsWithoutDefinedSchedule();
	}

	@Override
	public List<Treinamento> listAllScheduledTrainingsWithPendingApproval() throws Exception {
		return turmaService.listAllScheduledTrainingsWithPendingApproval();
	}

/*	@Override
	public List<PessoaEmDivulgacao> listComitentesByTipo(PersonExpositionType tipo)
			throws Exception {
		return turmaService.listComitentesByTipo(tipo);
	}*/

	@Override
	public List<CondicaoParticipacaoTreinamento> listTrainingClassParticipationConditions() throws Exception {
		return turmaService.listTrainingClassParticipationConditions();
	}

	@Override
	public List<Endereco> listAddressesByAddressType(AddressType tipo) throws Exception {
		return turmaService.listAddressesByAddressType(tipo);
	}

	@Override
	public List<Instrutor> listAllInstructors() throws Exception {
		return turmaService.listAllInstructors();
	}

	@Override
	public CondicaoParticipacaoTreinamento loadTrainingClassParticipationCondition(int id)
			throws Exception {
		return turmaService.loadTrainingClassParticipationCondition(id);
	}

	@Override
	public CondicaoParticipacaoTreinamento loadTrainingClassParticipationConditionByTrainingClassId(int id)
			throws Exception {
		return turmaService.loadTrainingClassParticipationCondition(id);
	}

	@Override
	public Endereco loadAddress(int id) throws Exception {
		return turmaService.loadAddress(id);
	}

	@Override
	public TurmaTreinamento loadTrainingClassCascading(TurmaTreinamento objLeilao) throws Exception {
		return turmaService.loadTrainingClassCascading(objLeilao);
	}

	@Override
	public void removeScheduledTrainingFromTrainingClass(Treinamento l, TurmaTreinamento leilao) throws Exception {
		turmaService.removeScheduledTrainingFromTrainingClass(l, leilao);
	}

	@Override
	public CondicaoParticipacaoTreinamento saveTrainingClassParticipationCondition(
			CondicaoParticipacaoTreinamento condPartLeilao) throws Exception {
		return turmaService.saveTrainingClassParticipationCondition(condPartLeilao);
	}

	@Override
	public TurmaTreinamento saveOrUpdate(TurmaTreinamento l) throws Exception {
		return turmaService.saveOrUpdate(l);
	}
	
	@Override
	public void mergeImageTrainingClass(int imageid, int turmaid) throws Exception {
		turmaService.mergeImageTrainingClass(imageid, turmaid);
	}

	public void mergeImageTrainingFormation(int imageid, int formacaoid) throws Exception{
		turmaService.mergeImageTrainingFormation(imageid, formacaoid);
	}
	
	@Override
	public Object[] getExtraInformationFromTurma(int turmaid) throws Exception {
		return turmaService.getExtraInformationFromTurma(turmaid);
	}
}
