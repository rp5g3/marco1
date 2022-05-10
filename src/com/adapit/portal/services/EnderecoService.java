package com.adapit.portal.services;

import java.util.List;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Pais;

public interface EnderecoService {

	public List<Endereco> listEnderecoByTipo(AddressType tipo)	throws Exception;
	
	public List<Endereco> listEnderecoEagerByTipo(AddressType tipo) throws Exception;
	
	public List<String> listAllCidadesByTipo(AddressType tipo)	throws Exception;
	
	public List<String> listAllEstadosByPais(Pais pais)	throws Exception;
	
	public List<String> listAllCidadesByEstado(String value) throws Exception;
	
	public List<String> listAllBairrosByCidade(String value) throws Exception;
	
	public List<String> listAllRuasByBairro(String value) throws Exception;

}
