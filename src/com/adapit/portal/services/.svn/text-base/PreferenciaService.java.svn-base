package com.adapit.portal.services;

import java.util.Date;
import java.util.List;

import com.adapit.portal.dto.CategoriaPreferidaDTO;
import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.Destaque;
import com.adapit.portal.entidades.MessageFeedbackCounter;
import com.adapit.portal.entidades.News;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.Publication;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.entidades.MessageFeedbackCounter.FeedbackType;

public interface PreferenciaService {

	public void updateOutrasCategoriasByPreferenciaId(Integer id, String value) throws Exception;
	
	public PreferenciaCategoria createPreferencia(boolean isNewRegister, Usuario usuario) throws Exception;
	
	public PreferenciaCategoria updatePreferenciaByPropertyName(String propName, int id, boolean value) throws Exception;
	
	public List<Categoria> listCategoriasPreferediasByIdPessoa(long id) throws Exception;

	public List<CategoriaPreferidaDTO> listCategoriasPreferediasByPessoa(long id)
			throws Exception;

	public Destaque saveAndMerge(News n, Publication p, SoftwareSolution ss)
			throws Exception;

	public Destaque getDestaqueIds() throws Exception;

	public Destaque loadDestaque() throws Exception;

	public void saveOrUpdate(MessageFeedbackCounter counter) throws Exception;

	public List<MessageFeedbackCounter> listMessageFeedbackCounterBy(
			FeedbackType type, Date d1, Date d2, Long partId, Integer targetId) throws Exception;
}
