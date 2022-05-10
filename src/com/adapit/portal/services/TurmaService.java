package com.adapit.portal.services;

import java.util.Date;
import java.util.List;

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

public interface TurmaService {

	public List<Object[]> listTrainingClassValues() throws Exception;
	
	public TurmaTreinamento loadTrainingClassByTrainingClassId(int idTurma) throws Exception;
	
	public List<TurmaTreinamento> listTrainingClassesOnValidInterval() throws Exception;
	
	public Object listTrainingClassAccordingTo(TrainingClassFilterType filtro, int left,
			boolean usarEndereco, int idEndereco, boolean usarData,
			Date dataTreinamento, Date dataEncerramento,
			boolean usarRegras, ClassificacaoTreinamento classificacaoTurma,
			TipoPacoteTreinamento subClassificacaoTurma,
			TurnoTreinamento regraExecucaoTurma,
			TipoExecucaoTreinamento tipoExecucaoTurma, int orderBy)
			throws Exception ;
	
	public Endereco loadAddress(int id) throws Exception;
	
	public CondicaoParticipacaoTreinamento loadTrainingClassParticipationConditionByTrainingClassId(int id) throws Exception;
	
	public TurmaTreinamento saveOrUpdate(TurmaTreinamento l) throws Exception;
	
	public TurmaTreinamento loadTrainingClassCascading(TurmaTreinamento objTurma) throws Exception;
	
	public CondicaoParticipacaoTreinamento saveTrainingClassParticipationCondition(CondicaoParticipacaoTreinamento condPartLeilao) throws Exception;
	
	public void removeScheduledTrainingFromTrainingClass(Treinamento l, TurmaTreinamento turma) throws Exception;
	
	/*@Deprecated
	public List<PessoaEmDivulgacao> listComitentesByTipo(PersonExpositionType tipo) throws Exception;	*/
	
	public List<Instrutor> listAllInstructors() throws Exception;
	
	public List<CondicaoParticipacaoTreinamento> listTrainingClassParticipationConditions() throws Exception;
	
	public List<Endereco> listAddressesByAddressType(AddressType tipo) throws Exception;
	
	public CondicaoParticipacaoTreinamento loadTrainingClassParticipationCondition(int id) throws Exception;
	
	public List<Treinamento> listScheduledTrainingsByTrainingClassId(int id) throws Exception;
	
	public List<Treinamento> listAllScheduledTrainingsWithoutTrainingClass() throws Exception;
	
	public List<Treinamento> listAllScheduledTrainingsWithoutDefinedSchedule() throws Exception;
	
	public List<Treinamento> listAllScheduledTrainingsWithPendingApproval() throws Exception;
	
	public void mergeImageTrainingClass(int imageid, int turmaid) throws Exception;
	
	public void mergeImageTrainingFormation(int imageid, int formacaoid) throws Exception;
	
	public Object[] getExtraInformationFromTurma(int turmaid) throws Exception;
}
