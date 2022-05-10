package com.adapit.portal.services.controllers.usuario;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brazilutils.br.cpfcnpj.CpfCnpj;
import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.EstadoCivil;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.Sexo;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.local.LocalPessoaService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.validation.FieldMsgValidation;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.services.validation.UserServiceValidator;
import com.workcase.gui.utils.WebResourceMessage;
import com.workcase.utils.DatePropertyEditor;
import com.workcase.utils.IdGenerator;

public class CadastroPessoaMultiActionController extends FormMultiActionController implements InitializingBean {

	private UserServiceValidator userService;
	
	//private PessoaServiceValidator pessoaService;
	
	private Usuario usuario;

	private com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl adapitAutenticateUserMailService;
	

	public CadastroPessoaMultiActionController(){
		super();
		messages = WebResourceMessage.getInstance();
	}

	public ModelAndView formCadastrePessoaFisicaView(HttpServletRequest request,  HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		try {
			BindException ex = validate(request);
			if (ex != null && (ex.getErrorCount()>0 || ex.getFieldErrorCount() > 0)){
				if (usuarioExiste(usuario)) usuario.setNewUser(false);
				else usuario.setNewUser(true);
				return new ModelAndView("pessoafisica/telaCadastroPessoaFisica",m);	
			}
				//return new ModelAndView("pessoafisica/formularioCadastroPessoaFisica",m);
		} catch (BindException e) {
			e.printStackTrace();
			return new ModelAndView("pessoafisica/telaCadastroPessoaFisica",m);
		}catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("pessoafisica/telaCadastroPessoaFisica",m);
		}
		try {
			validateUnique(pessoa);
			
			saveAction(request);
			
			return new ModelAndView("pessoafisica/userCadastreSucess");
		} catch(com.adapit.portal.services.validation.FieldMsgValidationException ex){
			Hashtable<String,String> ht = ex.getErrors();
			for (String key: ht.keySet()){
				System.out.println("Key " + key + "   " + ht.get(key));
				reportWarning(key,ht.get(key));
			}
			//populateErrorList(request, ex, ht, arr);
		}catch (Exception e) {
			e.printStackTrace();
				
		}
		return new ModelAndView("pessoafisica/telaCadastroPessoaFisica",m);
	}
	
	protected Object getCommand(HttpServletRequest request){
		
		usuario = new Usuario();
		//String temLogin = request.getParameter("id_usuario");		
		//usuario.setNewUser(temLogin == null || temLogin.equals(""));
		//String iduser = request.getParameter("id_usuario");
		String ticket = request.getParameter("ticket");
		/*if (iduser != null) usuario.setId(Integer.parseInt(iduser));
		else usuario.setId(0);*/
		if (ticket != null) usuario.setTicket(ticket);
		usuario.setLogin(request.getParameter("login"));
		if (usuario.getLogin() == null) usuario.setLogin(request.getParameter("id_usuario"));
		usuario.setNewUser(Boolean.parseBoolean(request.getParameter("newUser")));
		usuario.setPassword(request.getParameter("password"));
		usuario.setPasswordConf(request.getParameter("passwordConf"));
		return usuario;
	}
	
	protected void saveAction(HttpServletRequest request) throws Exception {		
		System.out.println("Salvando " + usuario.getLogin());
		try {
			usuario.setUserCadastreType(UserCadastreType.Cliente);
			if (usuario.isNewUser())
				usuario.setTicket(IdGenerator.getInstance().generateId(25));
			usuario.setActive(true);
			
			usuario.setDadosPessoais(pessoa);
			pessoa.setUser(usuario);
			
			pessoa.setEndereco(endereco);
			
			pessoa.setTipoPessoa(tipoPessoa);
			tipoPessoa.setPessoa(pessoa);
			
			usuario.setPreferencia(preferencia);
			preferencia.setUsuario(usuario);
			
						
			Session s = LocalServicesUtility.getInstance().openSession();
			try{
				s.beginTransaction();
				
				if (!usuario.isNewUser()){
					
					//Proibido atualizar senha e talz					
					//s.update(usuario);
					if (request.getSession(true).getAttribute("user") == null)
						throw new Exception("Não é possível atualizar um usuário não logado!");
					s.update(endereco);
					s.update(pessoa);
					s.update(tipoPessoa);
					s.update(preferencia);
					s.getTransaction().commit();
				}else{
					usuario.setPassword(Usuario.encript(usuario.getPassword()));
					usuario.setPasswordConf(Usuario.encript(usuario.getPassword()));
					endereco.setId(0);
					s.save(endereco);//endereco.setId(((Integer) s.save(endereco)).intValue());
					s.save(pessoa);//pessoa.setId(((Integer) s.save(pessoa)).intValue());
					s.save(tipoPessoa);//tipoPessoa .setId(((Integer) s.save(tipoPessoa)).intValue());
					s.save(usuario);//usuario.setId(((Integer) s.save(usuario)).intValue());
					s.save(preferencia);//preferencia.setId(((Integer) s.save(preferencia)).intValue());
					s.getTransaction().commit();
				}
				
				
				
				if(usuario.isNewUser() )adapitAutenticateUserMailService.sendAutenticateUserMsg(usuario);
				usuario.setNewUser(false);
			}catch(Exception ex){
				ex.printStackTrace();				
				s.getTransaction().rollback();	
				throw ex;
			}finally{
				if (s != null) s.close();
			}
			
						
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public void validateUnique(Pessoa p) throws Exception{
		LocalPessoaService pessoaService = LocalPessoaService.getInstance();
		TipoPessoa tp = p.getTipoPessoa();
		int tipo=0;
		String email = p.getEmail();
		String cpfcnpj="";
		String rgie="";
		if (tp instanceof Juridica){
			tipo=1;
			cpfcnpj = ((Juridica)tp).getCnpj();
			rgie = ((Juridica)tp).getInscricaoEstadual();
		}else{
			cpfcnpj = ((Fisica)tp).getCpf();
			rgie = ((Fisica)tp).getRg();
		}
		FieldMsgValidation ex = pessoaService.validateUnique(tipo,tp.getId(),cpfcnpj,rgie,p.getId(),email);
		if (ex != null){
			
			Hashtable<String,String> ht = ex.getErrors();
			if (ht != null && ht.size() > 0){
				for(String key : ht.keySet()){
					reportWarning(key,ht.get(key));
				}
			}
			throw new FieldMsgValidationException(ht);
		}
		
	}
	
	public void reportWarning(String propriedadeDuplicada, String errorMsg) {
		System.out.println("Reporting warning: " + errorMsg + "  Prop:" +propriedadeDuplicada);
		try{
			arr.add(messages.getMessage(errorMsg,
					   new Object[][]{{propriedadeDuplicada}}));
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	private Hashtable ht;
	private ArrayList<String> arr ;
	@SuppressWarnings("unchecked")
	protected BindException validate(HttpServletRequest request) throws Exception {
		usuario = (Usuario) getCommand(request);
		
		if (usuario == null || usuario.getLogin() == null) throw new Exception("Usuário e login não podem ser nulos!");
		m = new HashMap();
		
		ht = new Hashtable();		
		arr = new ArrayList();
		
		BindException errors = null;
		if (usuario.isNewUser())try {
			errors = new BindException(usuario, "Problemas ao cadastrar os dados de pessoa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		//errorsValidate(errors,arr,ht);		
		
		referenceData(request, errors);		
		System.out.println(usuario.isNewUser());
		if (errors == null || errors.getErrorCount() < 1){
			if (usuario.isNewUser() && canCadastreUsuario(request,usuario,errors)){
				//throw errors;
				return null;
			}
			else if (!isValidPessoa(request)){				
				ObjectError oe1 = new ObjectError("form", new String[]{"error"}, null,"errors.default");
				errors.addError(oe1);
				//throw errors;
			}
			else if (!isValidPessoaFisica(request)){
				//throw errors;
				ObjectError oe1 = new ObjectError("form", new String[]{"error"}, null,"errors.default");
				errors.addError(oe1);
				//throw errors;
			}
			else if (!isValidEndereco(request)){
				//throw errors;
				ObjectError oe1 = new ObjectError("form", new String[]{"error"}, null,"errors.default");
				errors.addError(oe1);
				//throw errors;
			}
			/*else if (!isValidEndereco(request)){
				//throw errors;
				ObjectError oe1 = new ObjectError("form", new String[]{"error"}, null,"errors.default");
				errors.addError(oe1);
				//throw errors;
			}*/
		}else{
			System.out.println("Existem erros de formulário detectados na classe base");
		}
		
		populateErrorList(request, errors, ht, arr);
		
		
		
		return errors;		
	}
	
	protected boolean canCadastreUsuario(HttpServletRequest request, Usuario usuario, BindException errors) throws Exception{
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
	@SuppressWarnings("unchecked")
	private Map m;

	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request, Errors detectedErrors) throws Exception {

		
		//super.bindAndValidate(request, usuario);
		//System.out.println("Usuario " + usuario.getLogin());
		
		try {
			bindPessoa(request,m,arr,ht);
			bindEndereco(request,m, arr,ht);	
			bindTipoPessoa(request,m,arr,ht);
			bindPreferencia(request,m,arr,ht);
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		
		
		
		m.put("usuario",usuario);		
		m.put("errorFields",ht);
		m.put("errorMessages",arr);
		return m;
	}
	
	boolean dataNascInvalida=false;
	
	@SuppressWarnings("unchecked")
	protected void bindTipoPessoa(HttpServletRequest request ,java.util.Map m ,ArrayList arr ,Hashtable ht ) throws Exception{

		try {
			tipoPessoa.setId(Integer.parseInt(request.getParameter("fisica_id")));
		}
		catch(Exception ex){
			
		}
		
		try {
			DatePropertyEditor dt = new DatePropertyEditor();
			dt.setFormat(messages.getMessage("formats.date"));
			dt.setAsText(request.getParameter("dataNascimento"));			
			tipoPessoa.setDataNascimento((Date)dt.getValue());
			dataNascInvalida=false;
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			dataNascInvalida=true;
		}
		
		try {
			String value = request.getParameter("estadoCivil");
			if (value != null && value.equals("empty")) {
				ht.put("estadoCivil", "errors.required");
				arr.add(
						messages.getMessage("participante.estadoCivil",
								new Object[][]{{"errors.required"}}));
			}
			else{
				String estadocivil = request.getParameter("estadoCivil");
				if (estadocivil != null){
					if (estadocivil.charAt(0) == '-'){
						ht.put("estadoCivil", "errors.required");
						arr.add(
								messages.getMessage("participante.estadoCivil",
										new Object[][]{{"errors.required"}}));
					}else{
						if (estadocivil.equals(EstadoCivil.União_estável.name()))
							tipoPessoa.setEstadoCivil(EstadoCivil.União_estável);
						else{
							estadocivil = estadocivil.substring(0, estadocivil.length()-3);
							tipoPessoa.setEstadoCivil(EstadoCivil.valueOf(estadocivil.replaceAll(" ","_")));
						}
					}
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		tipoPessoa.setProfissao(request.getParameter("profissao"));
		
		tipoPessoa.setCelular(request.getParameter("celular"));
		tipoPessoa.setCpf(request.getParameter("cpf"));
		//validar cpf
		tipoPessoa.setRg(request.getParameter("rg"));
		//validar rg
		
		String sobrenome = request.getParameter("sobrenome");
		if (sobrenome != null) tipoPessoa.setSobrenome(sobrenome);
		String sexo = request.getParameter("sexo");
		if (sexo != null) tipoPessoa.setSexo(Sexo.valueOf(sexo));
		
		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(tipoPessoa, "fisica");
		getValidator().validate(tipoPessoa, errors);
		errorsValidate(errors,arr,ht);
		
		try {
			if (!CpfCnpj.isValid(request.getParameter("cpf"))) {
				/*ObjectError oe1 = new ObjectError("participante", new String[]{"cpf"}, null,"errors.invalidcpf");
				errors.addError(oe1);*/
				ht.put("cpf", "errors.required");
				arr.add(
						messages.getMessage("participante.cpf",
								new Object[][]{{"errors.required"}}));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		pessoa.setTipoPessoa(tipoPessoa);
		
		m.put("fisica",tipoPessoa);
		
		
	}
	
	Fisica tipoPessoa= new Fisica();
	protected boolean isValidPessoaFisica(HttpServletRequest request) throws Exception{

		tipoPessoa.setCelular(request.getParameter("celular"));
		
		try {
			DatePropertyEditor dt = new DatePropertyEditor();
			
			dt.setFormat(messages.getMessage("formats.date"));
			dt.setAsText(request.getParameter("dataNascimento"));			
			tipoPessoa.setDataNascimento((Date)dt.getValue());
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			return false;
		}
		
		try {
			String estadocivil = request.getParameter("estadoCivil");
			if (estadocivil.equals("empty")) {
				return false;
			}
			else{
				estadocivil = estadocivil.substring(0, estadocivil.length()-3);
				tipoPessoa.setEstadoCivil(EstadoCivil.valueOf(estadocivil.replaceAll(" ","_")));
				//tipoPessoa.setEstadoCivil(EstadoCivil.valueOf(value.replaceAll("(a)","").replaceAll(" ","_")));
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
		tipoPessoa.setProfissao(request.getParameter("profissao"));
		//validar rg
		tipoPessoa.setRg(request.getParameter("rg"));
		//validar cpf
		tipoPessoa.setCpf(request.getParameter("cpf"));
		
		tipoPessoa.setSobrenome(request.getParameter("sobrenome"));
		
		
		
		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(tipoPessoa, "fisica");
		getValidator().validate(tipoPessoa, errors);
		
		if (!CpfCnpj.isValid(tipoPessoa.getCpf())) {
			ObjectError oe1 = new ObjectError("participante", new String[]{"cpf"}, null,"errors.invalidcpf");
			errors.addError(oe1);
		}
		
		
				
		if (errors.hasErrors()) return false;
		else return true;
		
	}
	
	private Endereco endereco=new Endereco();
	@SuppressWarnings("unchecked")
	protected void bindEndereco(HttpServletRequest request ,java.util.Map m ,ArrayList arr ,Hashtable ht ) throws Exception{

		endereco = new Endereco();
		try {
			endereco.setId(Integer.parseInt(request.getParameter("endereco_id")));
		}
		catch(Exception ex){
			
		}
		endereco.setBairro(request.getParameter("bairro"));
		endereco.setCidade(request.getParameter("cidade"));
		String pais = request.getParameter("pais");
		if (pais != null )try {
			endereco.setPais(Pais.valueOf(pais.replaceAll(" ","_")));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		String estado = request.getParameter("estadocombo");
		if (estado == null || estado.equals("..")) endereco.setEstado(request.getParameter("estado"));
		else{
			endereco.setEstado(estado);
		}
		
		try {endereco.setNumero(Integer.parseInt(request.getParameter("numero")));  }
		catch(Exception ex){
			ex.printStackTrace();
			arr.add(messages.getMessage("errors.integer",
			   new Object[][]{{"endereco.numero"}}));
		}
		endereco.setCaixaPostal(request.getParameter("caixaPostal"));
		//validar cep
		endereco.setCep(request.getParameter("cep"));
		endereco.setRua(request.getParameter("rua"));
		try {endereco.setComplemento(request.getParameter("complemento"));  }
		catch(Exception ex){
			arr.add(messages.getMessage("errors.integer",
			   new Object[][]{{"endereco.complemento"}}));
		}
		endereco.setTipo(AddressType.Residencial);
		
		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(endereco, "endereco");
		getValidator().validate(endereco, errors);
		errorsValidate(errors,arr,ht);
		
		pessoa.setEndereco(endereco);
		
		m.put( "endereco",endereco);
		
		
	}
	
	protected boolean isValidEndereco(HttpServletRequest request) throws Exception{
		
		endereco.setBairro(request.getParameter("bairro"));
		endereco.setCidade(request.getParameter("cidade"));
		try {
			endereco.setPais(Pais.valueOf(request.getParameter("pais").replaceAll(" ","_")));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		endereco.setEstado(request.getParameter("estado"));
		try {endereco.setNumero(Integer.parseInt(request.getParameter("numero")));  }
		catch(Exception ex){
			return false;
		}
		endereco.setCaixaPostal(request.getParameter("caixaPostal"));
		//validar cep
		endereco.setCep(request.getParameter("cep"));
		endereco.setRua(request.getParameter("rua"));
		try {endereco.setComplemento(request.getParameter("complemento"));  }
		catch(Exception ex){
			return false;
		}
		
		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(endereco, "endereco");
		getValidator().validate(endereco, errors);
		
	
		
		if (errors.hasErrors()) return false;
		else return true;
	}

	private Participante pessoa=new Participante();
	
	@SuppressWarnings("unchecked")
	protected void bindPessoa(HttpServletRequest request ,java.util.Map m ,ArrayList arr ,Hashtable ht ) throws Exception{
		String id = request.getParameter("id_participante");
		if (id != null) pessoa.setId(Integer.parseInt(id));
		pessoa.setNome(request.getParameter("nome"));
		pessoa.setEmail(request.getParameter("email"));
		pessoa.setTelefone(request.getParameter("telefone"));
		pessoa.setFax(request.getParameter("fax"));
		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(pessoa, "participante");
		getValidator().validate(pessoa, errors);
		errorsValidate(errors,arr,ht);
		
		usuario.setDadosPessoais(pessoa);
		m.put("participante",pessoa);
	}
	
	protected boolean isValidPessoa(HttpServletRequest request) throws Exception{
		pessoa.setNome(request.getParameter("nome"));
		pessoa.setEmail(request.getParameter("email"));
		pessoa.setTelefone(request.getParameter("telefone"));
		pessoa.setFax(request.getParameter("fax"));
		org.springframework.validation.BindException errors = new org.springframework.validation.BindException(pessoa, "participante");
		getValidator().validate(pessoa, errors);
		
		if (errors.hasErrors()) return false;
		else return true;
	}
	
	PreferenciaCategoria preferencia = new PreferenciaCategoria();
	
	@SuppressWarnings("unchecked")
	protected void bindPreferencia(HttpServletRequest request ,java.util.Map m ,ArrayList arr ,Hashtable ht ) throws Exception{
		String id = request.getParameter("id_preferencia");
		if (id != null) preferencia.setId(Integer.parseInt(id));
		preferencia.setReceberEmailSobreEventos(request.getParameter("diversos") != null);
		preferencia.setReceberEmailNovosProdutos(request.getParameter("imoveis") != null);
		preferencia.setReceberEmailAtualizacoesSoftware(request.getParameter("informatica") != null);
		preferencia.setInteresseEmTreinamentos(request.getParameter("maquinarios") != null);
		preferencia.setPreferencias(request.getParameter("preferencias"));
		preferencia.setReceberNotificacaoNewsByEmail(request.getParameter("receberNotificacaoEmail") != null);
		preferencia.setInteresseEmConsultoria(request.getParameter("veiculos") != null);
		
		usuario.setPreferencia(preferencia);
		m.put("preferencia",preferencia);
	}
	

	public UserServiceValidator getUserService() {
		return userService;
	}

	public void setUserService(UserServiceValidator userService) {
		this.userService = userService;
	}


	public com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl getAdapitAutenticateUserMailService() {
		return adapitAutenticateUserMailService;
	}


	public void setAdapitAutenticateUserMailService(
			com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl leilaoMailService) {
		this.adapitAutenticateUserMailService = leilaoMailService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		// if (person==null || personType==null) throw new
		// Exception("exceptions.local.BussinessNull");
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private void errorsValidate(BindException errors, ArrayList arr){
		if (errors.hasErrors()) {
			Iterator it = errors.getAllErrors().iterator();
			while (it.hasNext()){
				org.springframework.validation.FieldError errorObj = (org.springframework.validation.FieldError) it.next();
				arr.add(
						messages.getMessage(
								errorObj.getDefaultMessage(),
								new Object[][]{{errorObj.getObjectName()+"."+errorObj.getField()}}));
			}
		}
	}

}
