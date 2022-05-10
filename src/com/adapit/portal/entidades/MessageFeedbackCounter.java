package com.adapit.portal.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MessageFeedbackCounter implements Serializable {

	private static final long serialVersionUID = -7401660146218070479L;
	
	private long participant_id;
	
	private int idTarget;

	@Id
	private Date dataAcesso;
	
	public enum FeedbackType{Newsletters,Updates,Events,Papers}
	
	private String target;
	
	private FeedbackType feedbackType=FeedbackType.Newsletters;
	
	

	public MessageFeedbackCounter(long participantId, int idTarget,
			Date dataAcesso, String target, FeedbackType feedbackType) {
		super();
		participant_id = participantId;
		this.idTarget = idTarget;
		this.dataAcesso = dataAcesso;
		this.target = target;
		this.feedbackType = feedbackType;
	}

	public MessageFeedbackCounter() {
		super();
	}

	public long getParticipant_id() {
		return participant_id;
	}

	public void setParticipant_id(long participantId) {
		participant_id = participantId;
	}

	public int getIdTarget() {
		return idTarget;
	}

	public void setIdTarget(int idTarget) {
		this.idTarget = idTarget;
	}

	public Date getDataAcesso() {
		return dataAcesso;
	}

	public void setDataAcesso(Date dataAcesso) {
		this.dataAcesso = dataAcesso;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public FeedbackType getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(FeedbackType feedbackType) {
		this.feedbackType = feedbackType;
	} 
	
	
}
