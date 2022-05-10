package com.adapit.portal.services.validation;

import java.util.List;

import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.services.EnderecoService;

/**
 * @spring.bean id="enderecoService" singleton="true"
 */
public class EnderecoServiceValidator extends AbstractValidator implements EnderecoService{

	private DefaultBeanValidator validator;

	private EnderecoService service;

	/**
	 * @spring.property ref="enderecoServiceDAOHibernate" singleton="true"
	 */
	public EnderecoService getService() {
		return service;
	}

	public void setService(EnderecoService service) {
		this.service = service;
	}

	public DefaultBeanValidator getValidator() {
		return validator;
	}

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public EnderecoServiceValidator() {
		super();
		setName("enderecoServiceValidator");
	}

	@Override
	public List<String> listAllCidadesByTipo(AddressType tipo)
			throws Exception {
		return service.listAllCidadesByTipo(tipo);
	}

	@Override
	public List<Endereco> listEnderecoByTipo(AddressType tipo)
			throws Exception {
		return service.listEnderecoByTipo(tipo);
	}

	@Override
	public List<Endereco> listEnderecoEagerByTipo(AddressType tipo)
			throws Exception {
		return service.listEnderecoEagerByTipo(tipo);
	}

	@Override
	public List<String> listAllBairrosByCidade(String value) throws Exception {
		return service.listAllBairrosByCidade(value);
	}

	@Override
	public List<String> listAllCidadesByEstado(String value) throws Exception {
		return service.listAllCidadesByEstado(value);
	}

	@Override
	public List<String> listAllEstadosByPais(Pais pais) throws Exception {
		return service.listAllEstadosByPais(pais);
	}

	@Override
	public List<String> listAllRuasByBairro(String value) throws Exception {
		return service.listAllRuasByBairro(value);
	}
	
	
}
