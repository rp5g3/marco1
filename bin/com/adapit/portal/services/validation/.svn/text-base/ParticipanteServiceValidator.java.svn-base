package com.adapit.portal.services.validation;


import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.ParticipanteService;
import com.adapit.portal.services.PessoaService;
import com.adapit.portal.services.UserService;

/**
* @spring.bean id="participanteService" singleton="true"
*/
public class ParticipanteServiceValidator implements ParticipanteService{
	
	private DefaultBeanValidator validator;
	
	private ParticipanteService participanteService;
	
	private PessoaService pessoaService;
	
	private UserService userService;


	
	public void setValidator(DefaultBeanValidator validator ){
		this.validator = validator;
	}
	
	public DefaultBeanValidator getValidator(){
		return this.validator;
	}
	
	public void setParticipanteService(ParticipanteService participanteService ){
		this.participanteService = participanteService;
	}
	/**
	* @spring.property ref="participanteServiceDAOHibernate" singleton="true"
	*/
	public ParticipanteService getParticipanteService(){
		return this.participanteService;
	}
	
	public ParticipanteServiceValidator(){
		super();

	}
	
	public Participante saveAndMerge(Participante participante ,Usuario usuario ,TipoPessoa tipoPessoa ,PreferenciaCategoria preferenciaCategoria ,Endereco endereco ) throws FieldMsgValidationException, ValidationException, NonUniqueObjectException, ConstraintViolationException, DataException, Exception{

		BindException errors1 = new BindException(participante, "participante");
		validator.validate(participante, errors1);
		try{
			//Inheritance errors - com.adapit.portal.entidades.PessoaEmDivulgacao
			BindException inheritedErrors = new BindException(participante, "pessoaEmDivulgacao");
			validator.validate(participante, inheritedErrors);
			if (inheritedErrors.hasErrors()) errors1.addAllErrors(inheritedErrors);
		}catch(Exception ex){}

		try{
			//Inheritance errors - com.adapit.portal.entidades.Pessoa
			BindException inheritedErrors = new BindException(participante, "pessoa");
			validator.validate(participante, inheritedErrors);
			if (inheritedErrors.hasErrors()) errors1.addAllErrors(inheritedErrors);
		}catch(Exception ex){}

		
		if(participante.getId()<=0)try {
			canCadastreUsuario(usuario, errors1);
			//use only in case entity is embedded into the form
			/*System.out.println(usuario.getClass().getSimpleName());
			BindException errors2 = new BindException(usuario, "usuario");
			validator.validate(usuario, errors2);
			if (errors2.hasErrors()) errors1.addAllErrors(errors2);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			//use only in case entity is embedded into the form
			BindException errors3 = new BindException(tipoPessoa, "fisica");
			validator.validate(tipoPessoa, errors3);
			if (errors3.hasErrors()) errors1.addAllErrors(errors3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			//use only in case entity is embedded into the form
			BindException errors4 = new BindException(preferenciaCategoria, "preferenciaCategoria");
			validator.validate(preferenciaCategoria, errors4);
			if (errors4.hasErrors()) errors1.addAllErrors(errors4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			//use only in case entity is embedded into the form
			BindException errors5 = new BindException(endereco, "endereco");
			validator.validate(endereco, errors5);
			if (errors5.hasErrors()) errors1.addAllErrors(errors5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(pessoaService.violatesUniqueOnPropertyEmail(participante)){
			errors1.addError(FieldMsgValidationException.createFieldError("pessoa", "email", "error.violatesunique",participante.getEmail()));
		}
		if (errors1.hasErrors()) {
			throw new FieldMsgValidationException(errors1);
		}
		else {
			try {
				return this.participanteService.saveAndMerge(participante,usuario,tipoPessoa,preferenciaCategoria,endereco);
			} catch (DataIntegrityViolationException dive) {
				dive.printStackTrace();
				throw dive;
			} catch (DataAccessException dae) {
				dae.printStackTrace();
				throw dae;
			} catch (Exception ex) {
				ex.printStackTrace();
				throw ex;
			}
		}
	}
	
	protected boolean canCadastreUsuario(Usuario usuario, BindException errors) throws Exception{
		if (usuario == null || usuario.getPassword() == null || usuario.getPasswordConf() == null && usuario.isNewUser()){
			ObjectError oe1 = new ObjectError("usuario", new String[]{"password","passwordConf"}, new Object[]{new Integer(6)},"errors.minlength");
			ObjectError oe2 = new ObjectError("usuario", new String[]{"password","passwordConf"}, new Object[]{new Integer(8)},"errors.maxlength");
			errors.addError(oe1);
			errors.addError(oe2);
			return false;
		}
		if (usuario.isNewUser()){
			if (usuario.getPassword().equals(usuario.getPasswordConf())){
				System.out.println("Validando o usuário");
				if( !(usuario.getPassword().length() < 6 || usuario.getPassword().length() > 8)){
					if (usuario.isNewUser()){
						boolean existe = usuarioExiste(usuario);
						if (existe){
							System.out.println("Usuario ja existe!");
							ObjectError oe = new ObjectError("usuario", new String[]{"login"}, null,"errors.loginjustexist");
							errors.addError(oe);
							return false;
						}else{
							System.out.println("Usuario não existe e pode ser cadastrado");
							return true;
						}				
					}
					else return true;
				}else{
					System.out.println("Senha ou login incorretos!");
					ObjectError oe1 = new ObjectError("usuario", new String[]{"password","passwordConf"}, new Object[]{new Integer(6)},"errors.minlength");
					ObjectError oe2 = new ObjectError("usuario", new String[]{"password","passwordConf"}, new Object[]{new Integer(8)},"errors.maxlength");
					errors.addError(oe1);
					errors.addError(oe2);
					return false;
				}
			}else{
				System.out.println("Senhas não conferem");
				if( !(usuario.getPassword().length() < 6 || usuario.getPassword().length() > 8)){
					ObjectError oe1 = new ObjectError("usuario", new String[]{"password"}, new Object[]{"passwordConf"},"errors.differentpass");
					errors.addError(oe1);
					return false;
					
				}else{
					ObjectError oe1 = new ObjectError("usuario", new String[]{"password","passwordConf"}, new Object[]{new Integer(6)},"errors.minlength");
					ObjectError oe2 = new ObjectError("usuario", new String[]{"password","passwordConf"}, new Object[]{new Integer(8)},"errors.maxlength");
					errors.addError(oe1);
					errors.addError(oe2);
					return false;
				}
				
			}
		}
		return usuarioExiste(usuario);
	}
	
	public boolean usuarioExiste(Usuario usuario) throws Exception{
		try{			
			boolean usuarioExiste = userService.isValid(usuario.getLogin());
			return usuarioExiste;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public void setPessoaService(PessoaService pessoaService ){
		this.pessoaService = pessoaService;
	}
	/**
	* @spring.property ref="pessoaServiceDAOHibernate" singleton="true"
	*/
	public PessoaService getPessoaService(){
		return this.pessoaService;
	}

	/**
	* @spring.property ref="userServiceDAOHibernate" singleton="true"
	*/
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Participante load(Participante participante) throws Exception {
		return participanteService.load(participante);
	}



}