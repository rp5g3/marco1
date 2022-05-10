package com.adapit.portal.services;

import java.util.List;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.Encerramento;
import com.adapit.portal.entidades.EncerramentoCondicionado;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurnoTreinamento;

public interface AgendaTreinamentoService {

	@Deprecated
	public float updateIncremento(int idAgenda, float valor) throws Exception;
	
	public TurnoTreinamento getScheduledTrainingPeriod(int idAgenda) throws Exception;
	
	@Deprecated
	public boolean isAgendaEncerrada(int idAgenda) throws Exception;
	
	public List<Treinamento> listScheduledTrainingToPrepareAccount(int idTurma) throws Exception;
	
	@Deprecated
	public EncerramentoCondicionado aprovarHomologacao(int idTreinamento, EncerramentoCondicionado encCond, float valorAprovado) throws Exception;

	public AgendaTreinamento saveOrUpdateMergeScheduledTrainingId(AgendaTreinamento a, int idTreinamento) throws Exception;

	public void cancelTrainingSchedule(AgendaTreinamento agenda) throws Exception;

	public void confirmTrainingSchedule(AgendaTreinamento agenda, Treinamento lote, TurnoTreinamento chamada)
			throws Exception;

	public void generateScheduleHistoryByScheduledTrainingId(int idLote) throws Exception;

	public Encerramento loadEncerramento(int idEncerr) throws Exception;

	public int getScheduledTrainingScheduleNumber(int idAgenda) throws Exception;
}
