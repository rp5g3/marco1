package com.adapit.portal.services.validation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springmodules.validation.commons.DefaultBeanValidator;

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

/**
 * @spring.bean id="preferenciaService" singleton="true"
 */
@SuppressWarnings("serial")
public class PreferenciaServiceValidator extends AbstractValidator implements
		PreferenciaService, Serializable {

	private DefaultBeanValidator validator;

	private PreferenciaService preferenciaService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setPreferenciaService(PreferenciaService userService) {
		this.preferenciaService = userService;
	}

	/**
	 * @spring.property ref="preferenciaServiceDAOHibernate" singleton="true"
	 */
	public PreferenciaService getPreferenciaService() {
		return this.preferenciaService;
	}

	public PreferenciaServiceValidator() {
		super();
		setName("preferenciaServiceValidator");
	}

	@Override
	public void updateOutrasCategoriasByPreferenciaId(Integer id, String value)
			throws Exception {
		preferenciaService.updateOutrasCategoriasByPreferenciaId(id, value);
	}
	
	@Override
	public PreferenciaCategoria createPreferencia(boolean isNewRegister, Usuario usuario)
			throws Exception {
		if (usuario == null) throw new ValidationException("The user naccon be null!");
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
			FeedbackType type, Date d1, Date d2, Long part_id, Integer target_id)  throws Exception{
		return preferenciaService.listMessageFeedbackCounterBy(type, d1, d2, part_id, target_id);
	}
}
