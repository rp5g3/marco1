package com.adapit.portal.services.validation;

import java.io.Serializable;
import java.util.List;

import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.Encerramento;
import com.adapit.portal.entidades.EncerramentoCondicionado;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurnoTreinamento;
import com.adapit.portal.services.AgendaTreinamentoService;

/**
 * @spring.bean id="agendaTreinamentoService" singleton="true"
 */
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class AgendaTreinamentoServiceValidator extends AbstractValidator implements AgendaTreinamentoService, Serializable {

	private DefaultBeanValidator validator;

	private AgendaTreinamentoService agendaService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setAgendaService(AgendaTreinamentoService userService) {
		this.agendaService = userService;
	}

	/**
	 * @spring.property ref="agendaTreinamentoServiceDAOHibernate" singleton="true"
	 */
	public AgendaTreinamentoService getAgendaService() {
		return this.agendaService;
	}

	public AgendaTreinamentoServiceValidator() {
		super();
		setName("agendaTreinamentoServiceValidator");
	}

	@SuppressWarnings("deprecation")
	@Override
	public float updateIncremento(int idAgenda, float valor) throws Exception {
		return agendaService.updateIncremento(idAgenda, valor);
	}

	
	

	@Override
	public TurnoTreinamento getScheduledTrainingPeriod(int idAgenda)
			throws Exception {
		return agendaService.getScheduledTrainingPeriod(idAgenda);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isAgendaEncerrada(int idAgenda) throws Exception {
		return agendaService.isAgendaEncerrada(idAgenda);
	}
	
	@Override
	public List<Treinamento> listScheduledTrainingToPrepareAccount(int idLeilao) throws Exception {
		return agendaService.listScheduledTrainingToPrepareAccount(idLeilao);
	}
	

	
	@SuppressWarnings("deprecation")
	@Override
	public EncerramentoCondicionado aprovarHomologacao(int idLote,
			EncerramentoCondicionado encCond, float valorAprovado)
			throws Exception {
		return agendaService.aprovarHomologacao(idLote, encCond, valorAprovado);
	}

	
	
	@Override
	public AgendaTreinamento saveOrUpdateMergeScheduledTrainingId(AgendaTreinamento a, int idLote)
			throws Exception {
		return agendaService.saveOrUpdateMergeScheduledTrainingId(a, idLote);
	}

	@Override
	public void cancelTrainingSchedule(AgendaTreinamento agenda) throws Exception {
		agendaService.cancelTrainingSchedule(agenda);
	}

	@Override
	public void confirmTrainingSchedule(AgendaTreinamento agenda, Treinamento lote,
			TurnoTreinamento chamada) throws Exception {
		agendaService.confirmTrainingSchedule(agenda, lote, chamada);
	}
	
	@Override
	public void generateScheduleHistoryByScheduledTrainingId(int idLote) throws Exception {
		agendaService.generateScheduleHistoryByScheduledTrainingId(idLote);
	}

	@Override
	public Encerramento loadEncerramento(int idEncerr) throws Exception {
		return agendaService.loadEncerramento(idEncerr);
	}

	
	@Override
	public int getScheduledTrainingScheduleNumber(int idAgenda) throws Exception {
		return agendaService.getScheduledTrainingScheduleNumber(idAgenda);
	}
	
}