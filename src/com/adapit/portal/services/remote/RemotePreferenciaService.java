package com.adapit.portal.services.remote;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;

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
import com.adapit.portal.services.PreferenciaService;
import com.workcase.gui.utils.SwingContext;

public class RemotePreferenciaService implements PreferenciaService{
	private PreferenciaService preferenciaService;

	private static RemotePreferenciaService instance;

	private RemotePreferenciaService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			preferenciaService = (PreferenciaService) beanFactory
					.getBean("remotePreferenciaServiceHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemotePreferenciaService getInstance() {
		if (instance == null) {
			instance = new RemotePreferenciaService();
		}
		return instance;
	}

	
	@Override
	public void updateOutrasCategoriasByPreferenciaId(Integer id, String value)
			throws Exception {
		preferenciaService.updateOutrasCategoriasByPreferenciaId(id, value);
	}

	@Override
	public PreferenciaCategoria createPreferencia(boolean isNewRegister, Usuario usuario)
			throws Exception {
		return preferenciaService.createPreferencia(isNewRegister, usuario);
	}
	
	@Override
	public PreferenciaCategoria updatePreferenciaByPropertyName(String propName, int id,
			boolean value) throws Exception {
		return preferenciaService.updatePreferenciaByPropertyName(propName, id, value);
	}

	@Override
	public List<Categoria> listCategoriasPreferediasByIdPessoa(long id)
			throws Exception {		
		return preferenciaService.listCategoriasPreferediasByIdPessoa(id);
	}

	@Override
	public List<CategoriaPreferidaDTO> listCategoriasPreferediasByPessoa(long id)
			throws Exception {
		return preferenciaService.listCategoriasPreferediasByPessoa(id);
	}

	@Override
	public Destaque getDestaqueIds() throws Exception {
		return preferenciaService.getDestaqueIds();
	}

	@Override
	public Destaque loadDestaque() throws Exception {
		return preferenciaService.loadDestaque();
	}

	@Override
	public Destaque saveAndMerge(News n, Publication p, SoftwareSolution ss)
			throws Exception {
		return preferenciaService.saveAndMerge(n, p, ss);
	}
	
	@Override
	public void saveOrUpdate(MessageFeedbackCounter counter) throws Exception{
		preferenciaService.saveOrUpdate(counter);
	}
	
	@Override
	public List<MessageFeedbackCounter> listMessageFeedbackCounterBy(
			FeedbackType type, Date d1, Date d2, Long part_id, Integer target_id) throws Exception{
		return preferenciaService.listMessageFeedbackCounterBy(type, d1, d2, part_id, target_id);
	}
}
