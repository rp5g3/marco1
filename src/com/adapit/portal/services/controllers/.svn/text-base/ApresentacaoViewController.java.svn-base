package com.adapit.portal.services.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.Destaque;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.MessageFeedbackCounter;
import com.adapit.portal.entidades.News;
import com.adapit.portal.entidades.Publication;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.entidades.SystemCounter;
import com.adapit.portal.entidades.TechDefinition;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.entidades.MessageFeedbackCounter.FeedbackType;
import com.adapit.portal.services.local.LocalPreferenciaService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.mail.GenericEmailSender;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;
import com.workcase.utils.IdGenerator;

public class ApresentacaoViewController extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;
	
	public final static int PAGE_MAX_NUMBER_TREINAMENTOS=6;

	public ApresentacaoViewController(){
		messages = WebResourceMessage.getInstance();
	}

	public ModelAndView loggoff(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".loggoff");
		if (request.getSession() != null){
			request.getSession().removeAttribute("user");
			request.getSession().invalidate();			
		}
		return new ModelAndView("welcome");
	}
	
	public ModelAndView showApresentacaoView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showApresentacaoView");
		String lang = request.getParameter("lang");
		   
	    if(lang != null){
		   // System.out.println("Language = "+lang);
		    request.getSession().setAttribute("lang", lang);
	    }
		return new ModelAndView("welcome");
	}
	
	/**
	 * portal.html
	 * conteudo_inicial.html
	 * 
	 * Operação chamada na incialização do portal. Identifica se o conteúdo central deve ser propaganda da empresa, ou um produto específico.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ModelAndView showPortalView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showPortalView");
		
		/*Locale locale = request.getLocale();
	    String language = locale.getLanguage();
	    String country = locale.getCountry();
	    System.out.println("Lang="+language+", country="+country);
		*/
	    String lang = request.getParameter("lang");
	   
	    if(lang != null){
		    //System.out.println("Language = "+lang);
		    request.getSession().setAttribute("lang", lang);
	    }
		
		ApresentacaoViewController.countAcess(request,null,"portal");
		String sistema = null;
		sistema = request.getParameter("sistema");
		if(sistema == null) sistema = request.getParameter("system");
		if(sistema == null) sistema = request.getParameter("sys");
		
		String news=null;
		news = request.getParameter("news");
		if(news == null) news = request.getParameter("notícia");
		if(news == null) news = request.getParameter("noticia");		
		
		String artigo=null;
		artigo = request.getParameter("art");
		if(artigo == null) artigo = request.getParameter("artigo");
		if(artigo == null) artigo = request.getParameter("paper");
		
		String update=null;
		update = request.getParameter("update");
		//System.out.println(update);
		if(update == null) update = request.getParameter("atualizacao");
		if(update == null) update = request.getParameter("updatefile");
		
		String register= request.getParameter("register");
		//System.out.println(register);
		if(register != null && !register.trim().equals("")){
			//System.out.println("register " + register);
			if(register.equals("new_register")){
				request.setAttribute("pageStr", "new_register");
			}
			return new ModelAndView("welcome");
		}
		
		String accountid= request.getParameter("account");
		if(accountid != null && !accountid.trim().equals("")){
			if(accountid.equals("new_register")){
				request.setAttribute("pageStr", "new_register");
			}
			else request.getSession().setAttribute("account", accountid);
			
			//return new ModelAndView("participante/ParticipanteMaintenanceForm");
			return new ModelAndView("welcome");
		}
		
		
		
		Destaque d = null;
		try {
			d = LocalPreferenciaService.getInstance().loadDestaque();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (d != null)
			request.setAttribute("destaque", d);
		
		if(sistema != null && !sistema.equals("")){
			request.setAttribute("sigla", sistema);
			System.out.println("Redirecting to welcome to show system");
			request.setAttribute("pageStr", "produtos");
			if(request.getParameter("tab") != null)
				request.getSession(true).setAttribute("tab", request.getParameter("tab"));
			
			return new ModelAndView("welcome");
		}else if(news != null && !news.equals("")){
			request.setAttribute("newsid", news);
			System.out.println("Redirecting to welcome to show news");
			request.setAttribute("pageStr", "news");
			try {
				String pid = request.getParameter("part_id");
				if(pid != null && !pid.trim().equals("")){
					long part_id=Long.parseLong(pid);
					MessageFeedbackCounter feed = new MessageFeedbackCounter(part_id,Integer.parseInt(news),new Date(),null,FeedbackType.Newsletters);
					LocalPreferenciaService.getInstance().saveOrUpdate(feed);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return new ModelAndView("welcome");
		}else if(artigo != null && !artigo.equals("")){
			request.setAttribute("paperid", artigo);
			System.out.println("Redirecting to welcome to show paper");
			request.setAttribute("pageStr", "paper");
			try {
				String pid = request.getParameter("part_id");
				if(pid != null && !pid.trim().equals("")){
					long part_id=Long.parseLong(pid);
					MessageFeedbackCounter feed = new MessageFeedbackCounter(part_id,Integer.parseInt(artigo),new Date(),null,FeedbackType.Papers);
					LocalPreferenciaService.getInstance().saveOrUpdate(feed);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("welcome");
		}else if(update != null && !update.equals("")){
			String strs[] = update.split(",");
			request.setAttribute("updateid", strs[0].trim());
			request.setAttribute("sigla", strs[1].trim());
			System.out.println("Redirecting to welcome to show update");
			request.setAttribute("pageStr", "update");
			try {
				String pid = request.getParameter("part_id");
				if(pid != null && !pid.trim().equals("")){
					long part_id=Long.parseLong(pid);
					MessageFeedbackCounter feed = new MessageFeedbackCounter(part_id,Integer.parseInt(update),new Date(),null,FeedbackType.Updates);
					LocalPreferenciaService.getInstance().saveOrUpdate(feed);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ModelAndView("welcome");
		}else{
			System.out.println("Redirecting to welcome");
			java.util.Enumeration parNames= request.getParameterNames();
			while(parNames.hasMoreElements()){
				String name = (String) parNames.nextElement();
				String param = request.getParameter(name);
				request.setAttribute(name, param);
			}
			return new ModelAndView("welcome");
		}
	}
	
	public static void countAcess(HttpServletRequest request, String paramName, String key){
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			String value="0";
			try {
				value = (String) s.createQuery("select lc.value from SystemCounter lc where lc.key='"+key+"'").uniqueResult();
			} catch (Exception e) {
				value="0";
			}
			if(value == null)
				value="0";
			SystemCounter lc = new SystemCounter();
			Integer i = Integer.parseInt(value);
			lc.setValue((i+1)+"");
			lc.setKey(key);
			s.beginTransaction();
			s.saveOrUpdate(lc);
			s.getTransaction().commit();
			if(paramName != null)
				request.getSession().setAttribute(paramName, lc);
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			if(s.isOpen())
				s.close();
		}
	}
	
	//wct
	public ModelAndView showWct(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showWct");
		Destaque d = null;
		try {
			d = LocalPreferenciaService.getInstance().loadDestaque();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (d != null)
			request.setAttribute("destaque", d);
		request.setAttribute("sigla","wct");
		return new ModelAndView("welcomeSystem");
	}
	
	public ModelAndView showAberturaView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showAberturaView");
		String lang = request.getParameter("lang");
		   
	    if(lang != null){
		    //System.out.println("Language = "+lang);
		    request.getSession().setAttribute("lang", lang);
	    }
		return new ModelAndView("welcome");
	}
	
	public ModelAndView showMainMenuView(HttpServletRequest request,  HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showMainMenuView");
		return new ModelAndView("menu/mainmenu");
	}
	
	public ModelAndView showHeaderView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showHeaderView");
		return new ModelAndView("header");
	}
	
	public ModelAndView showBottomView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showBottomView");
		return new ModelAndView("bottom");
	}
	
	public ModelAndView showContentView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showContentView");
		return new ModelAndView("content");
	}
		
	public ModelAndView showTreinamentoResearchFormView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showTreinamentoResearchFormView");
		return new ModelAndView("telaPesquisaTreinamentos");
	}
	
	public ModelAndView showPesquisaProdutoFormView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showPesquisaProdutoFormView");
		return new ModelAndView("telaPesquisaProdutos");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showProximosTurmasFormView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showProximosTurmasFormView");
		Vector turmas = new Vector();
		String strint = request.getParameter("pageNumber");
		int fresult = 0;
		int pnumber = 1;
		if(strint != null && !strint.equals("")){
			pnumber = Integer.parseInt(strint);
			fresult = (PAGE_MAX_NUMBER_TREINAMENTOS*pnumber);
		}
		
		int paginate = 0;
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			int tamanho = (Integer) s.createQuery("select count(l) from TurmaTreinamento l").uniqueResult();
			if (tamanho > PAGE_MAX_NUMBER_TREINAMENTOS){
				paginate = tamanho/(PAGE_MAX_NUMBER_TREINAMENTOS+1);
			}
			List li = s.createQuery("from TurmaTreinamento l where l.dataTreinamento > current_timestamp() order by l.dataTreinamento").setMaxResults(PAGE_MAX_NUMBER_TREINAMENTOS).setFirstResult(fresult).list();
			Iterator<TurmaTreinamento> it = li.iterator();
			while(it.hasNext()){
				TurmaTreinamento turma = it.next();
				if (turma.getComitente() != null){
					
				}
				turma.getEnderecoPresencial().getCidade();
				turma.getCondicaoParticipacao().getTexto();
				turma.getComitente().getNome();
				if (turma.getInstrutor() != null){
					Instrutor lei = turma.getInstrutor();
					lei.getNome();
					((Fisica)lei.getTipoPessoa()).getSobrenome();
					lei.getApresentacao();
				}
				turmas.add(turma);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		request.setAttribute("pageNumber",pnumber);
		request.setAttribute("paginate", paginate);
		request.setAttribute("turmas",turmas);
		return new ModelAndView("proximosTurmasForm");
	}
	

	public ModelAndView showProximosTurmasForm_View(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showProximosTurmasForm_View");
		showProximosTurmasFormView(request,response);
		return new ModelAndView("turma/agenda_turmas");
	}
	

	public ModelAndView showProximosTurmasIndexFormView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showProximosTurmasIndexFormView");
		showProximosTurmasFormView(request,response);
		return new ModelAndView("turma/index");
	}
	

	
	@SuppressWarnings("unchecked")
	public ModelAndView showHistoricoTurmasFormView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showHistoricoTurmasFormView");
		Vector turmas = new Vector();
		
		String strint = request.getParameter("pageNumber");
		int fresult = 0;
		int pnumber = 1;
		if(strint != null && !strint.equals("")){
			pnumber = Integer.parseInt(strint);
			fresult = (PAGE_MAX_NUMBER_TREINAMENTOS*pnumber);
		}
		
		int paginate = 0;
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			int tamanho = (Integer) s.createQuery("select count(l) from TurmaTreinamento l").uniqueResult();
			
			if (tamanho > PAGE_MAX_NUMBER_TREINAMENTOS){
				paginate = tamanho/(PAGE_MAX_NUMBER_TREINAMENTOS+1);
			}
			
			List li = s.createQuery("from TurmaTreinamento l order by l.dataTreinamento DESC ").setMaxResults(PAGE_MAX_NUMBER_TREINAMENTOS).setFirstResult(fresult).list();
			Iterator<TurmaTreinamento> it = li.iterator();
			while(it.hasNext()){
				TurmaTreinamento turma = it.next();
				if (turma.getComitente() != null){
					
				}
				turma.getEnderecoPresencial().getCidade();
				turma.getCondicaoParticipacao().getTexto();
				turma.getComitente().getNome();
				if (turma.getInstrutor() != null){
					Instrutor lei = turma.getInstrutor();
					lei.getNome();
					((Fisica)lei.getTipoPessoa()).getSobrenome();
					lei.getApresentacao();
				}
				turmas.add(turma);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		request.setAttribute("pageNumber",pnumber);
		request.setAttribute("paginate", paginate);
		request.setAttribute("turmas",turmas);
		return new ModelAndView("proximosTurmasForm");
	}
	

	public ModelAndView showHistoricoTurmasForm_View(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showHistoricoTurmasForm_View");
		showHistoricoTurmasFormView(request,response);
		return new ModelAndView("turma/agenda_turmas");
	}
	

	public ModelAndView showHistoricoTurmasIndexFormView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showHistoricoTurmasIndexFormView");
		showHistoricoTurmasFormView(request,response);
		request.setAttribute("pagina","proximosTurmasForm.jsp");
		return new ModelAndView("turma/index");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showMenuHorizontalView(HttpServletRequest request,
            HttpServletResponse response){	
		System.out.println(getClass().getSimpleName()+".showMenuHorizontalView");
		Session s =null;
		try {	
			            
			s= LocalServicesUtility.getInstance().openSession();
			String hql = "select f from FormacaoTreinamento f order by ordemPreferencia";
			List<FormacaoTreinamento> formacoes = s.createQuery(hql).list();
			request.setAttribute("todasFormacoes",formacoes);
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}		
		
		return new ModelAndView("layout/menuhoriz/horizmenu");
	}
	


	public ModelAndView showInicioContentView(HttpServletRequest request,
            HttpServletResponse response){	
		System.out.println(getClass().getSimpleName()+".showInicioContentView");
		showPortalView(request, response);
		String pageStr = request.getParameter("pageStr");
		if(pageStr != null){
			System.out.println(" redirecting to " + pageStr);
			return new ModelAndView(pageStr);
		}
		else System.out.println(" redirecting to layout/index");
		return new ModelAndView("layout/index");
	}
	
	
	public ModelAndView showInicioContentMenuView(HttpServletRequest request,
            HttpServletResponse response){	
		System.out.println(getClass().getSimpleName()+".showInicioContentMenuView redirecting to layout/index_red");
		return new ModelAndView("layout/index_red");
	}
	
	//private Hashtable<Integer,PessoaEmDivulgacao> comitentes = new Hashtable();
	
	public ModelAndView showComitenteImageView(HttpServletRequest request,HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showComitenteImageView");
		Session s =null;
		try {	
			            
			/*s= LocalServicesUtility.getInstance().openSession();
			String hql = "from PessoaEmDivulgacao c where c.id="+request.getParameter("comitente_id");
			
			PessoaEmDivulgacao c = null;
			c=(PessoaEmDivulgacao) s.createQuery(hql).uniqueResult();
			
			File f = (File)c.getLogotipo();
			Imagem im = new Imagem();
			im.setFullImageBytes(f);
			ImageIcon ii = im.getScaledImage(80);
			byte barr[] = Imagem.toByteArray(ii.getImage(), Imagem.getImageFormat(f));
			
			int index = f.getName().indexOf(".");
			
			String format = f.getName().substring(index+1);			
			
			if (format.equalsIgnoreCase("jpg")) response.setContentType("image/jpeg");
			else response.setContentType("image/"+format);			
			           
            InputStream in = new ByteArrayInputStream(barrgetImageBytes(f));
            OutputStream out = response.getOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
 
            in.close();
            out.flush();
            out.close(); */
			
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}		
		
		return null;
	}
	
	public ModelAndView showCategoriaImageView(HttpServletRequest request,HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showCategoriaImageView");
		Session s =null;
		try {	
			            
			s= LocalServicesUtility.getInstance().openSession();
			String hql = "from Categoria c where c.id="+request.getParameter("categoria_id");
			
			Categoria c = null;
			 c=(Categoria) s.createQuery(hql).uniqueResult();
			
			if (c.getCategoriaImagem() != null){
				File f = (File) c.getCategoriaImagem().getImagem();
				Imagem im = new Imagem();
				im.setFullImageBytes(f);
				ImageIcon ii = im.getScaledImage(16);
				byte barr[] = Imagem.toByteArray(ii.getImage(), Imagem.getImageFormat(f));
				
				if (barr != null){
					int index = f.getName().indexOf(".");
					
					String format = f.getName().substring(index+1);			
					
					if (format.equalsIgnoreCase("jpg")) response.setContentType("image/jpeg");
					else response.setContentType("image/"+format);
				
		            InputStream in = new ByteArrayInputStream(barr/*getImageBytes(f)*/);
		            OutputStream out = response.getOutputStream();
		            int b;
		            while ((b = in.read()) != -1) {
		                out.write(b);
		            }
		 
		            in.close();
		            out.flush();
		            out.close(); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}		
		
		return null;
	}
	
	public static byte[] getImageBytes(File file){
		byte[] b = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(b);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found.");
			e.printStackTrace();
		}
		catch (IOException e1)
		{
			System.out.println("Error Reading The File.");
			e1.printStackTrace();
		}
		return b;
	} 
	
	
private GenericEmailSender genericEmailSender;
	
	private String[] words={"NANOTEC","SOFIA","VITORIA","ACROPOLIS","KALAHARI","POLIGONO","EXTRATO","LIVRARIA","CALABRESA","TOMATE",
			"LEVEDO","AGRICOLA","CULTURA","SENTIDO","CENOURA","GUARANI","RETRATO","PINTURA","BANANA","COLHEITA",
			"CEVADA","INFORMA","TOALHA","CRATERA","SOMALIA","ENCANTO","LAZANHA","SORRIR","NOBRE","COBRE",
			"RELATIVO","VENCEDOR","TROIANO","IMPRIMIR","TROVAO","LETRADO","CRAVINHO","ZUMBI","CALAU","NEVASCA",
			"HERMAN","SENSOR","TROMPAR","CAMINHO","SERTAO","TRAFEGO","PRINCIPIO","ARVORE","GEOLOGO","PROFESSOR",
			"TRATO","NOTORIO","CAIXA","ANEDOTA","EMPREGO","SOLUCAO","URGENTE","DEDUCAO","SINUCA","REDONDO",
			"TELEFONE","ALEXANDRIA","VATICANO","SERVIA","ATENTO","BERINGELA","CATATAU","TRATOR","CANTOR","SERENA",
			"GAROA","SERMAO","NEBLINA","DISTANTE","CAVALGO","NOTURNO","TRATADO","ESTADO","GALGO","LEOPARDO",
			"ANALISE","RECARGA","CUME","JUSTO","WARNER","ROTATIVO","REATOR","MARTE","ANDROMEDA","SALARIO",
			"SENSITIVO","FUNDADOR","ELEVADOR","ESPETO","OCTAGONO","SEMAFORO","CONTENTE","SATURNO","ANTENA","GUARDA","REMEDIO"};
	
	public ModelAndView showContatoSendEmailView(HttpServletRequest request,HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		int rd = IdGenerator.getInstance().getRandomInt(100);
		String imageurl = "a"+rd+".jpg";
		System.out.println(getClass().getSimpleName()+".showContatoSendEmailView " + imageurl);
		request.getSession(true).setAttribute("imageid", imageurl);
		request.getSession(true).setAttribute("verif", rd);
		return new ModelAndView("contato/sendEmail");
	}
	
	public ModelAndView processContatoSendEmailView(HttpServletRequest request,HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".processContatoSendEmailView");
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		
		try {
			int rd = (Integer) request.getSession().getAttribute("verif");
			String word = request.getParameter("word");
			String msg = request.getParameter("mensagem");
			String nome = request.getParameter("nome");
			String assunto = request.getParameter("assunto");
			String email = request.getParameter("email");
			
			if (words[rd].equals(word.toUpperCase())){
				try {
					if (genericEmailSender == null){
						try {							
							FileSystemResource fsr = new FileSystemResource("webapps\\adapit\\WEB-INF\\applicationContext.xml");														
							XmlBeanFactory beanFactory = new XmlBeanFactory(fsr);
							genericEmailSender = (GenericEmailSender) beanFactory.getBean("genericMailService");
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					genericEmailSender.sendEmail(nome, email, assunto, msg, "fabiopbasso@gmail.com,rmpillat@gmail.com");
					System.out.println("Email enviado");
					return new ModelAndView("contato/confirmation");
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("mensagem",msg);
				request.setAttribute("nome",nome);
				request.setAttribute("assunto",assunto);
				request.setAttribute("email",email);
				return new ModelAndView("contato/sendEmail");
			}else{
				rd = IdGenerator.getInstance().getRandomInt(100);	
				request.getSession(true).setAttribute("imageid", "a"+rd+".jpg");
				request.getSession(true).setAttribute("verif", rd);
				request.setAttribute("mensagem",msg);
				request.setAttribute("nome",nome);
				request.setAttribute("assunto",assunto);
				request.setAttribute("email",email);
				return new ModelAndView("contato/sendEmail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return new ModelAndView("contato/sendEmail");
	}

	/**
	 * @spring.property ref="genericMailService" singleton="true"
	 */
	public GenericEmailSender getGenericEmailSender() {
		return genericEmailSender;
	}

	public void setGenericEmailSender(GenericEmailSender genericEmailSender) {
		this.genericEmailSender = genericEmailSender;
	}
	
	
	public void afterPropertiesSet() throws Exception {
		// if (person==null || personType==null) throw new
		// Exception("exceptions.local.BussinessNull");
	}

	
	@SuppressWarnings({ "unused", "unchecked" })
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
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView showPesquisaInternaView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showPesquisaInternaView");
		Session s =null;
		
		try {
			String keyword = request.getParameter("keyword");
			s= LocalServicesUtility.getInstance().openSession();
			
			List<News> news = s.createQuery("from News n where lower(n.descricao) like :kw")
				.setParameter("kw", "%"+keyword.toLowerCase()+"%").list();
			request.setAttribute("news", news);
			
			List<Publication> publications = s.createQuery("from Publication obj where lower(obj.descricao) like :kw")
				.setParameter("kw", "%"+keyword.toLowerCase()+"%").list();
			request.setAttribute("publications",publications);
			
			List<SoftwareSolution> softwareSolutions = s.createQuery("from SoftwareSolution obj where lower(obj.keyWords) like :kw " +
					"or lower(obj.descricao) like :kw "+
					"or lower(obj.funcionalidades) like :kw ")
			.setParameter("kw", "%"+keyword.toLowerCase()+"%").list();
			request.setAttribute("softwareSolutions",softwareSolutions);
			
			List<TrainingSolution> trainingSolutions = s.createQuery("from TrainingSolution obj where lower(obj.keyWords) like :kw " +
					"or lower(obj.descricao) like :kw")
			.setParameter("kw", "%"+keyword.toLowerCase()+"%").list();
			request.setAttribute("trainingSolutions",trainingSolutions);
			
			List<TechDefinition> definitions = s.createQuery("from TechDefinition obj where lower(obj.keywords) like :kw " +
				"or lower(obj.content) like :kw")
					.setParameter("kw", "%"+keyword.toLowerCase()+"%").list();
			request.setAttribute("definitions",definitions);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (s != null) s.close();
		}
		return new ModelAndView("internal/search");
	}
	
}