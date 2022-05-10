package com.adapit.portal.services.controllers;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.PrimeiroContatoIniciado;
import com.adapit.portal.entidades.Sexo;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.ImagemService;
import com.adapit.portal.services.ParticipanteService;
import com.adapit.portal.services.PessoaService;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.services.validation.ValidationException;
import com.workcase.utils.DatePropertyEditor;
import com.workcase.utils.IdGenerator;

//@Controller
public class ParticipanteMultiActionController extends MultiActionController {

	// @Resource(name="fakeDateEditor")
	private DatePropertyEditor datePropertyEditor;

	// @Resource(name="participanteService")
	/**
	 * @spring.property ref="participanteService" singleton="true"
	 */
	private ParticipanteService participanteService;

	private com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl adapitAutenticateUserMailService;

	public com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl getAdapitAutenticateUserMailService() {
		return adapitAutenticateUserMailService;
	}

	public void setAdapitAutenticateUserMailService(
			com.adapit.portal.services.mail.AdapitAutenticateUserMailServiceImpl adapitAutenticateUserMailService) {
		this.adapitAutenticateUserMailService = adapitAutenticateUserMailService;
	}

	public DatePropertyEditor getDatePropertyEditor() {
		return datePropertyEditor;
	}

	public void setDatePropertyEditor(DatePropertyEditor datePropertyEditor) {
		this.datePropertyEditor = datePropertyEditor;
	}

	public ParticipanteService getParticipanteService() {
		return participanteService;
	}

	public void setParticipanteService(ParticipanteService participanteService) {
		this.participanteService = participanteService;
	}

	// @RequestMapping("/showParticipanteMaintenanceForm.html")
	public ModelAndView showParticipanteMaintenanceForm(
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println(getClass() + ".showParticipanteMaintenanceForm");

		try {
			if (request.getParameter("participante.id") != null) {
				Long id = Long.parseLong(request
						.getParameter("participante.id"));
				if (id != null)
					try {
						//Participante participante = new Participante();
						//participante.setId(id);
						participante = participanteService.load(new Participante(id));
						//request.setAttribute("participante", participante);
						reverseBindParticipante(request);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ModelAndView("participante/ParticipanteMaintenanceForm");
	}

	public Participante bindParticipante(HttpServletRequest request)
			throws Exception {

		Participante participante = new Participante();
		if (com.adapit.web.NumberUtil.isNumeric(request
				.getParameter("participante.id")))
			participante.setId(Long.parseLong(request
					.getParameter("participante.id")));
		participante.setNome(request.getParameter("participante.nome"));
		participante.setEmail(request.getParameter("participante.email"));
		participante.setTelefone(request.getParameter("participante.telefone"));
		participante.setFax(request.getParameter("participante.fax"));
		try {
			participante.setPrimeiroContato(PrimeiroContatoIniciado
					.valueOf(request
							.getParameter("participante.primeiroContato")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		// participante.setLogotipo(request.getParameter("participante.logotipo"));
		return participante;
	}

	public Usuario bindUsuario(HttpServletRequest request) throws Exception {

		Usuario usuario = new Usuario();
		usuario.setLogin(request.getParameter("usuario.login"));
		usuario.setPassword(request.getParameter("usuario.password"));
		usuario.setPasswordConf(request.getParameter("usuario.passwordConf"));
		usuario.setUserCadastreType(UserCadastreType.Cliente);
		usuario.setActive(true);
		usuario.setTicket(IdGenerator.getInstance().generateId(25));
		return usuario;
	}

	public TipoPessoa bindTipoPessoa(HttpServletRequest request)
			throws Exception {
		Fisica tipoPessoa = new Fisica();
		if (com.adapit.web.NumberUtil.isNumeric(request
				.getParameter("fisica.id")))
			tipoPessoa.setId(java.lang.Integer.parseInt(request
					.getParameter("fisica.id")));
		tipoPessoa.setSobrenome(request.getParameter("fisica.sobrenome"));
		tipoPessoa.setProfissao(request.getParameter("fisica.profissao"));
		if (datePropertyEditor == null)
			datePropertyEditor = new DatePropertyEditor();
		datePropertyEditor.setAsText(request
				.getParameter("fisica.dataNascimento"));
		tipoPessoa.setDataNascimento((Date) datePropertyEditor.getValue());

		tipoPessoa.setSexo(Sexo.valueOf(request.getParameter("fisica.sexo")));
		return tipoPessoa;
	}

	public PreferenciaCategoria bindPreferenciaCategoria(
			HttpServletRequest request) throws Exception {

		PreferenciaCategoria preferenciaCategoria = new PreferenciaCategoria();
		if (com.adapit.web.NumberUtil.isNumeric(request
				.getParameter("preferenciaCategoria.id")))
			preferenciaCategoria.setId(java.lang.Integer.parseInt(request
					.getParameter("preferenciaCategoria.id")));
		preferenciaCategoria
				.setReceberEmailAtualizacoesSoftware(request
						.getParameter("preferenciaCategoria.receberEmailAtualizacoesSoftware") != null);
		preferenciaCategoria
				.setInteresseEmConsultoria(request
						.getParameter("preferenciaCategoria.interesseEmConsultoria") != null);
		preferenciaCategoria
				.setReceberEmailNovosProdutos(request
						.getParameter("preferenciaCategoria.receberEmailNovosProdutos") != null);
		preferenciaCategoria
				.setInteresseEmTreinamentos(request
						.getParameter("preferenciaCategoria.interesseEmTreinamentos") != null);
		preferenciaCategoria
				.setReceberEmailSobreEventos(request
						.getParameter("preferenciaCategoria.receberEmailSobreEventos") != null);
		preferenciaCategoria
				.setReceberNotificacaoNewsByEmail(request
						.getParameter("preferenciaCategoria.receberNotificacaoNewsByEmail") != null);
		preferenciaCategoria.setPreferencias(request
				.getParameter("preferenciaCategoria.preferencias"));
		return preferenciaCategoria;
	}

	public Endereco bindEndereco(HttpServletRequest request) throws Exception {

		Endereco endereco = new Endereco();
		if (com.adapit.web.NumberUtil.isNumeric(request
				.getParameter("endereco.id")))
			endereco.setId(java.lang.Integer.parseInt(request
					.getParameter("endereco.id")));
		endereco.setBairro(request.getParameter("endereco.bairro"));
		endereco.setCidade(request.getParameter("endereco.cidade"));
		endereco.setPais(Pais.valueOf(request.getParameter("endereco.pais")));
		endereco.setEstado(request.getParameter("endereco.estado"));
		if (com.adapit.web.NumberUtil.isNumeric(request
				.getParameter("endereco.numero")))
			endereco.setNumero(java.lang.Integer.parseInt(request
					.getParameter("endereco.numero")));
		endereco.setCaixaPostal(request.getParameter("endereco.caixaPostal"));
		endereco.setCep(request.getParameter("endereco.cep"));
		endereco.setRua(request.getParameter("endereco.rua"));
		endereco.setComplemento(request.getParameter("endereco.complemento"));
		return endereco;
	}

	public void reverseBindParticipante(HttpServletRequest request)
			throws Exception {
		try {
			request.setAttribute("participante", participante);
			request.setAttribute("usuario", participante.getUser());
			request.setAttribute("fisica", participante.getTipoPessoa());
			if (participante != null && participante.getUser() != null
					&& participante.getUser().getPreferencia() != null)
				request.setAttribute("preferenciaCategoria", participante
						.getUser().getPreferencia());
			else
				request.setAttribute("preferenciaCategoria",
						preferenciaCategoria);

			request.setAttribute("endereco", participante.getEndereco());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	Participante participante = null;
	Usuario usuario = null;
	TipoPessoa tipoPessoa = null;
	PreferenciaCategoria preferenciaCategoria = null;
	Endereco endereco = null;

	// @RequestMapping("/saveParticipanteAction_ParticipanteMaintenanceForm.html")
	public ModelAndView saveParticipanteAction_ParticipanteMaintenanceForm(
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println(getClass()
				+ ".saveParticipanteAction_ParticipanteMaintenanceForm");
		try {
			participante = bindParticipante(request);
			usuario = bindUsuario(request);
			
			if(participante.getId()>0){
				usuario.setLogin(request.getParameter("usuario.login2"));
				//System.out.println(usuario.getLogin());
				usuario.setPassword("123123");
				usuario.setPasswordConf("123123");
			}
			tipoPessoa = bindTipoPessoa(request);
			preferenciaCategoria = bindPreferenciaCategoria(request);
			endereco = bindEndereco(request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			boolean newUser = participante.getId()==0;
			participante = participanteService.saveAndMerge(participante,
					usuario, tipoPessoa, preferenciaCategoria, endereco);

			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.SucessDialog;
			request.setAttribute("msg", "SucessDialogParticipanteCadastre");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "SucessDialogTitle");
			if (newUser)
				adapitAutenticateUserMailService
						.sendAutenticateUserMsg(usuario);
			usuario.setNewUser(false);
			// Dados para editar o formulário
			participante = participanteService.load(participante);
			reverseBindParticipante(request);
			//if(request.getParameter("participante.logotipo") != null){
			//uploadFile(request);
			//}
		} catch (FieldMsgValidationException ex1) {
			try {
				reverseBindParticipante(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
			request.setAttribute("errorFields", ex1.getErrorFields());
			request.setAttribute("errorMessages", ex1.getErrorMessages());
		} catch (ValidationException ex2) {
			ex2.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		} catch (NonUniqueObjectException ex3) {
			ex3.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		} catch (ConstraintViolationException ex4) {
			ex4.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		} catch (DataException ex5) {
			ex5.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		} catch (Exception ex6) {
			ex6.printStackTrace();
			com.adapit.web.DialogKind kind = com.adapit.web.DialogKind.ErrorDialog;
			request.setAttribute("msg", "ErrorDialogMessage");
			request.setAttribute("kind", kind);
			request.setAttribute("title", "ErrorDialogTitle");
		}
		return new ModelAndView("participante/ParticipanteMaintenanceForm");
	}

	private ImagemService imagemService;
	
	private PessoaService pessoaService;
	
	private void uploadFile(HttpServletRequest request) {
		System.out.println("uploading file");
		if (FileUpload.isMultipartContent(request)) {
			ServletFileUpload servletFileUpload = new ServletFileUpload(
					new DiskFileItemFactory());
			System.out.println("é multipart");
			try {
				List fileItemsList = servletFileUpload.parseRequest(request);
				System.out.println("itens " + fileItemsList.size());
				Iterator it = fileItemsList.iterator();
				while (it.hasNext()) {
					FileItem fileItem = (FileItem) it.next();
					if (fileItem.isFormField()) {
						
						System.out.println(fileItem.getFieldName()+" - "+fileItem.getString()+"<br>");
					} else {
						System.out.println("Gravando arquivo");
						File f = new File(fileItem.getName());
						byte[] arr = fileItem.get();
						Imagem im = new Imagem();
						im.setFullImageBytes(arr);
						im.setAltText(participante.getNome() + " - fotografia");
						im.setDescription( participante.getNome() + " fotografia " + new Date());
						im.setImageData(f);
						Categoria cat = new Categoria();//) LocalServicesUtility.getInstance().load(Categoria.class, 4);
						cat.setId(4);
						im = imagemService.saveImagemMergeCategoria(im, cat);
						pessoaService.mergePersonalImage(im.getId(), participante.getId());
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				// out.println(ex.getMessage());
				// out.println("<br>Não foi possível cadastrar os dados no banco de dados!<br>"+ex);
			}
		}
	}

	/**
	 * @spring.property ref="imagemService" singleton="true"
	 */
	public ImagemService getImagemService() {
		return imagemService;
	}

	public void setImagemService(ImagemService imagemService) {
		this.imagemService = imagemService;
	}

	/**
	 * @spring.property ref="pessoaService" singleton="true"
	 */
	public PessoaService getPessoaService() {
		return pessoaService;
	}

	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}

}