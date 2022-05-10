package com.adapit.portal.services;


import java.util.Date;
import java.util.List;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;

import com.adapit.portal.entidades.AtributoPessoa;
import com.adapit.portal.entidades.ClassificacaoComentarioCliente;
import com.adapit.portal.entidades.ComentarioCliente;
import com.adapit.portal.entidades.ComentarioClientePk;
import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.ComitenteSimples;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Funcionario;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.PersonAttributeType;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.RepresentanteLegal;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.validation.FieldMsgValidation;
import com.adapit.portal.services.validation.FieldMsgValidationException;

public interface PessoaService {

	public Usuario saveOrUpdate(Usuario user, Pessoa p, Endereco ender) 
		throws FieldMsgValidationException,
			   org.hibernate.exception.ConstraintViolationException,
			   org.hibernate.exception.DataException,
			   org.hibernate.NonUniqueObjectException,
			   Exception;
	public Pessoa saveOrUpdate(Pessoa p, Endereco ender)
	throws FieldMsgValidationException, org.hibernate.exception.ConstraintViolationException,
	org.hibernate.exception.DataException, org.hibernate.NonUniqueObjectException, Exception;
	
	public boolean cpfExists(String cpf) throws Exception;
	
	public boolean rgExists(String rg) throws Exception;
	
	public boolean cnpjExists(String cnpj) throws Exception;
	
	public boolean inscricaoEstadualExists(String inscricaoEstadual) throws Exception;
	
	public boolean emailExists(String email) throws Exception;
	
	public Instrutor initializeInstrutor(Long id) throws Exception;
	
	public Endereco getEnderecoByPessoaId(Long id) throws Exception;
	
	public Instrutor getInstrutor(long id) throws Exception;
	
	public Participante getParticipante(long id)  throws Exception;
	
	public Funcionario getFuncionario(long id)  throws Exception;
	
	public PessoaEmDivulgacao getComitente(long id)  throws Exception;
	
	public Fisica getPessoaFisicaByIdPessoa(long id) throws Exception;
	
	public Juridica getPessoaJuridicaByIdPessoa(long id) throws Exception;
	
	public List<RepresentanteLegal> listRepresentanteLegalLazy() throws Exception;
	
	public List<RepresentanteLegal> listRepresentanteLegalLoading() throws Exception;
		
	@SuppressWarnings("unchecked")
	public Participante getParticipanteByIdUsuario(Class clazz, String login) throws Exception;
	
	@SuppressWarnings("unchecked")
	public long getParticipanteIdByIdUsuario(Class clazz, String login) throws Exception;
	
	@SuppressWarnings("unchecked")
	public boolean isPessoaInstanceOf(long idPessoa, Class clazz);
	
	public Participante initializeParticipante(Long id) throws Exception;
	
	public FieldMsgValidation validateUnique(int tipo, int idTipo, String cpfcnpj, String rgie, long pId, String pemail);
	
	public Imagem mergePersonalImage(int imageid, long personid) throws Exception;
	
	public Imagem getPersonalImage(long personid) throws Exception;
	
	public AtributoPessoa getAtributoPessoaByAttributeTypeAndPersonId(long personid, PersonAttributeType type) throws Exception;
	
	public List<AtributoPessoa> listAtributoPessoaByPersonId(long personid)	throws Exception;
	
	public List<Long> listPersonIdByAttribute(PersonAttributeType type)	throws Exception;
	
	public List<ComentarioCliente> listComentarioClienteByValues(Boolean apresentar,
			Long idPessoa, Integer idCom, ClassificacaoComentarioCliente clif)
			throws Exception;
	
	public ComentarioCliente updateComentarioCliente(Boolean apresentar,
			String comentario, ClassificacaoComentarioCliente clif,
			ComentarioClientePk pk, Date data) throws Exception;
	
	public Pessoa saveOrUpdate(ComitenteSimples p) throws FieldMsgValidationException,
			ConstraintViolationException, DataException,
			NonUniqueObjectException, Exception;
	
	public AtributoPessoa save(AtributoPessoa att) throws Exception;
	
	public void delete(AtributoPessoa att) throws Exception;
	
	public boolean violatesUniqueOnPropertyEmail(Pessoa pessoa ) throws Exception;

}
