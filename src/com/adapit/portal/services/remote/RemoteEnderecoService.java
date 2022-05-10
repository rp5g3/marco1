package com.adapit.portal.services.remote;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.services.EnderecoService;
import com.workcase.gui.utils.SwingContext;

public class RemoteEnderecoService implements EnderecoService{
	private EnderecoService service;

	private static RemoteEnderecoService instance;

	private RemoteEnderecoService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			service = (EnderecoService) beanFactory
					.getBean("remoteEnderecoServiceHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemoteEnderecoService getInstance() {
		if (instance == null)
			instance = new RemoteEnderecoService();
		return instance;
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
