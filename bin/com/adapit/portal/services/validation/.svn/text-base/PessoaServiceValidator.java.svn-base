package com.adapit.portal.services.validation;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.brazilutils.br.cpfcnpj.CpfCnpj;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springmodules.validation.commons.DefaultBeanValidator;

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
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.PessoaService;

/**
 * @spring.bean id="pessoaService" singleton="true"
 */
@SuppressWarnings("serial")
public class PessoaServiceValidator extends AbstractValidator implements
		PessoaService, Serializable {

	private DefaultBeanValidator validator;

	private PessoaService pessoaService;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setPessoaService(PessoaService userService) {
		this.pessoaService = userService;
	}

	/**
	 * @spring.property ref="pessoaServiceDAOHibernate" singleton="true"
	 */
	public PessoaService getPessoaService() {
		return this.pessoaService;
	}

	public PessoaServiceValidator() {
		super();
		setName("pessoaServiceValidator");
	}

	@Override
	public boolean cnpjExists(String cnpj) throws Exception {
		return pessoaService.cnpjExists(cnpj);
	}

	@Override
	public boolean cpfExists(String cpf) throws Exception {
		return pessoaService.cpfExists(cpf);
	}

	@Override
	public boolean emailExists(String email) throws Exception {
		return pessoaService.emailExists(email);
	}

	@Override
	public Endereco getEnderecoByPessoaId(Long id) throws Exception {		
		return pessoaService.getEnderecoByPessoaId(id);
	}

	@Override
	public Instrutor initializeInstrutor(Long id) throws Exception {
		return pessoaService.initializeInstrutor(id);
	}

	@Override
	public boolean inscricaoEstadualExists(String inscricaoEstadual)
			throws Exception {
		return pessoaService.inscricaoEstadualExists(inscricaoEstadual);
	}

	@Override
	public boolean rgExists(String rg) throws Exception {
		return pessoaService.rgExists(rg);
	}

	@Override
	public Usuario saveOrUpdate(Usuario user, Pessoa p, Endereco ender)
			throws FieldMsgValidationException, ConstraintViolationException,
			DataException, NonUniqueObjectException, Exception {
		try {
			String instanceName=p.getClass().getSimpleName();
			char c = instanceName.charAt(0);
			instanceName = Character.toLowerCase(c) + instanceName.substring(1);
			
			String tipo="";
			if (p.getTipoPessoa() instanceof Fisica)
				tipo="fisica";
			else tipo="juridica";
			
			
			BindException errors1 = new BindException(user, "usuario");
			validator.validate(user, errors1);
			BindException errors2 = new BindException(p, instanceName);
			validator.validate(p, errors2);
			BindException errors3 = new BindException(p.getTipoPessoa(), tipo);
			if (!p.getTipoPessoa().isIgnoreValidation())
				validator.validate(p.getTipoPessoa(), errors3);
			
			
			
			BindException errors4 = new BindException(ender, "endereco");
			validator.validate(ender, errors4);
			/*
			errors1.addAllErrors(errors2);
			errors1.addAllErrors(errors3);
			errors1.addAllErrors(errors4);*/
			
			if (errors1.hasErrors()) {
				throw errors1;
			} else if (errors2.hasErrors()) {
				throw errors2;
			} else if (errors3.hasErrors()) {
				throw errors3;
			} else if (errors4.hasErrors()) {
				throw errors4;
			}else if (!p.getTipoPessoa().isIgnoreValidation()){
				
				TipoPessoa tipoPessoa = p.getTipoPessoa();
				if (tipoPessoa instanceof Fisica) {
					org.springframework.validation.BindException errorsdef = new org.springframework.validation.BindException(tipoPessoa, "fisica");
					if (!CpfCnpj.isValid(((Fisica)tipoPessoa).getCpf())){
						ObjectError oe1 = new ObjectError("fisica", new String[]{"cpf"}, null,"errors.invalidcpf");
						errorsdef.addError(oe1);
						throw errorsdef;
					}
				}else if (tipoPessoa instanceof Juridica) {
					org.springframework.validation.BindException errorsdef = new org.springframework.validation.BindException(tipoPessoa, "juridica");
					if (!CpfCnpj.isValid(((Juridica)tipoPessoa).getCnpj())){
						ObjectError oe1 = new ObjectError("juridica", new String[]{"cnpj"}, null,"errors.invalidcnpj");
						errorsdef.addError(oe1);
						throw errorsdef;
					}
				}
				return this.pessoaService.saveOrUpdate(user,p,ender);			
			}else{
				return this.pessoaService.saveOrUpdate(user,p,ender);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Pessoa saveOrUpdate(Pessoa p, Endereco ender)
			throws FieldMsgValidationException, ConstraintViolationException,
			DataException, NonUniqueObjectException, Exception {
		try {
			String instanceName=p.getClass().getSimpleName();
			char c = instanceName.charAt(0);
			instanceName = Character.toLowerCase(c) + instanceName.substring(1);
			
			String tipo="";
			if (p.getTipoPessoa() instanceof Fisica)
				tipo="fisica";
			else tipo="juridica";
			
			
			BindException errors2 = new BindException(p, instanceName);
			validator.validate(p, errors2);
			BindException errors3 = new BindException(p.getTipoPessoa(), tipo);
			if (!p.getTipoPessoa().isIgnoreValidation())
				validator.validate(p.getTipoPessoa(), errors3);
			BindException errors4 = new BindException(ender, "endereco");
			validator.validate(ender, errors4);
			
			
			if (errors2.hasErrors()) {
				throw errors2;
			} else if (errors3.hasErrors()) {
				throw errors3;
			} else if (errors4.hasErrors()) {
				throw errors4;
			}else {
				return this.pessoaService.saveOrUpdate(p,ender);			
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Instrutor getInstrutor(long id) throws Exception{		
		try{
			return pessoaService.getInstrutor(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public Participante getParticipante(long id)  throws Exception{
		try{
			return pessoaService.getParticipante(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	@Override
	public Funcionario getFuncionario(long id)  throws Exception{		
		try{
			return pessoaService.getFuncionario(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public PessoaEmDivulgacao getComitente(long id)  throws Exception{		
		try{
			return pessoaService.getComitente(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	@Override
	public Fisica getPessoaFisicaByIdPessoa(long id) throws Exception {
		try{
			return pessoaService. getPessoaFisicaByIdPessoa(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public Juridica getPessoaJuridicaByIdPessoa(long id) throws Exception {
		try{
			return pessoaService. getPessoaJuridicaByIdPessoa(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	@Override
	public List<RepresentanteLegal> listRepresentanteLegalLazy()
			throws Exception {
		try{
			return pessoaService.listRepresentanteLegalLazy();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public List<RepresentanteLegal> listRepresentanteLegalLoading()
			throws Exception {
		try{
			return pessoaService.listRepresentanteLegalLoading();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Participante getParticipanteByIdUsuario(Class clazz,String login)
			throws Exception {
		return pessoaService.getParticipanteByIdUsuario(clazz, login);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public long getParticipanteIdByIdUsuario(Class clazz,String login)
			throws Exception {
		return pessoaService.getParticipanteIdByIdUsuario(clazz, login);
	}
	
	@Override
	public Participante initializeParticipante(Long id) throws Exception {
		return pessoaService.initializeParticipante(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isPessoaInstanceOf(long idPessoa, Class clazz) {
		return pessoaService.isPessoaInstanceOf(idPessoa, clazz);
	}
	
	@Override
	public FieldMsgValidation validateUnique(int tipo, int idTipo, String cpfcnpj, String rgie, long pId, String pemail) {
		return pessoaService.validateUnique(tipo, idTipo, cpfcnpj, rgie, pId, pemail);
	}
	
	@Override
	public Imagem mergePersonalImage(int imageid, long personid)
			throws Exception {
		return pessoaService.mergePersonalImage(imageid, personid);
	}
	
	@Override
	public Imagem getPersonalImage(long personid) throws Exception {
		return pessoaService.getPersonalImage(personid);
	}
	
	@Override
	public AtributoPessoa getAtributoPessoaByAttributeTypeAndPersonId(
			long personid, PersonAttributeType type) throws Exception {
		return pessoaService.getAtributoPessoaByAttributeTypeAndPersonId(personid, type);
	}

	@Override
	public List<AtributoPessoa> listAtributoPessoaByPersonId(long personid)
			throws Exception {
		return pessoaService.listAtributoPessoaByPersonId(personid);
	}

	@Override
	public List<ComentarioCliente> listComentarioClienteByValues(
			Boolean apresentar, Long idPessoa, Integer idCom,
			ClassificacaoComentarioCliente clif) throws Exception {
		return pessoaService.listComentarioClienteByValues(apresentar, idPessoa, idCom, clif);
	}

	@Override
	public List<Long> listPersonIdByAttribute(PersonAttributeType type)
			throws Exception {
		return pessoaService.listPersonIdByAttribute(type);
	}

	@Override
	public ComentarioCliente updateComentarioCliente(Boolean apresentar,
			String comentario, ClassificacaoComentarioCliente clif,
			ComentarioClientePk pk, Date data) throws Exception {
		return pessoaService.updateComentarioCliente(apresentar, comentario, clif, pk, data);
	}
	
	@Override
	public Pessoa saveOrUpdate(ComitenteSimples p)
			throws FieldMsgValidationException, ConstraintViolationException,
			DataException, NonUniqueObjectException, Exception {
		return pessoaService.saveOrUpdate(p);
	}
	

	@Override
	public AtributoPessoa save(AtributoPessoa att) throws Exception{
		return pessoaService.save(att);
	}

	@Override
	public void delete(AtributoPessoa att) throws Exception{
		pessoaService.delete(att);
	}
	
	@Override
	public boolean violatesUniqueOnPropertyEmail(Pessoa pessoa ) throws Exception{
		try{
			return this.pessoaService.violatesUniqueOnPropertyEmail(pessoa);
		}catch(Exception ex){		
			ex.printStackTrace();
			throw ex;
		}
	}
}
