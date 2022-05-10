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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.adapit.portal.dto.UsuarioDTO;
import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Technologies;
import com.adapit.portal.entidades.TrainingFormationItem;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.services.ImagemService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.local.LocalTreinamentoService;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;
import com.workcase.utils.DatePropertyEditor;
@SuppressWarnings({"unchecked","unused"})
public class TrainingPresentationViewController  extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;
	
	public final static int PAGE_FORMATION_MAX_NUMBER=6;
	
	private String formationName = FormacaoTreinamento.class.getSimpleName();
	private String treinamentoName = Treinamento.class.getSimpleName();
	
	private ImagemService imagemService;

	public TrainingPresentationViewController(){
		messages = WebResourceMessage.getInstance();
	}

	public ModelAndView loggoff(HttpServletRequest request,  HttpServletResponse response){
		if (request.getSession() != null){
			request.getSession().removeAttribute("user");
			request.getSession().invalidate();			
		}
		return new ModelAndView("welcome");
	}
	
	public ModelAndView showApresentacaoView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("welcome");
	}
	
	public ModelAndView showPortalView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("welcome");
	}
	
	public ModelAndView showAberturaView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("welcome");
	}
	
	public ModelAndView showMainMenuView(HttpServletRequest request,  HttpServletResponse response){
		return new ModelAndView("menu/mainmenu");
	}
	
	public ModelAndView showHeaderView(HttpServletRequest request,
            HttpServletResponse response){
		return new ModelAndView("header");
	}
	
	public ModelAndView showBottomView(HttpServletRequest request,
            HttpServletResponse response){
		return new ModelAndView("bottom");
	}
	
	public ModelAndView showContentView(HttpServletRequest request,
            HttpServletResponse response){
		return new ModelAndView("content");
	}
		
	public ModelAndView showLoteResearchFormView(HttpServletRequest request,
            HttpServletResponse response){
		return new ModelAndView("telaPesquisaLotes");
	}
	
	public ModelAndView showPesquisaProdutoFormView(HttpServletRequest request,
            HttpServletResponse response){
		return new ModelAndView("telaPesquisaProdutos");
	}
	
	public ModelAndView showFormacaoFilterPanel(HttpServletRequest request, HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		
		return new ModelAndView("formacao/formacaoFilterPanel");
	}
	
	public ModelAndView showFormacoesFormViewByTecnologies(HttpServletRequest request,
            HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		Vector formacoes = new Vector();
		String strint = request.getParameter("pageNumber");
		int fresult = 0;
		int pnumber = 1;
		if(strint != null && !strint.equals("")){
			pnumber = Integer.parseInt(strint);
			fresult = (PAGE_FORMATION_MAX_NUMBER*pnumber);
		}
		
		int paginate = 0;
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			String tecid = request.getParameter("tec");			
			Technologies tech = Technologies.values()[Integer.parseInt(tecid)];
			int tamanho = (Integer) s.createQuery("select count(l) from "+formationName+" l where l.technology="+tech.ordinal()).uniqueResult();
			if (tamanho > PAGE_FORMATION_MAX_NUMBER){
				paginate = tamanho/(PAGE_FORMATION_MAX_NUMBER+1);
			}
			List li = s.createQuery("from "+formationName+" l  where l.technology="+tech.ordinal()+" order by l.ordemPreferencia").setMaxResults(PAGE_FORMATION_MAX_NUMBER).setFirstResult(fresult).list();
			Iterator<FormacaoTreinamento> it = li.iterator();
			while(it.hasNext()){
				FormacaoTreinamento formacao = it.next();
				if (formacao.getImagem() != null) formacao.getImagem().getId();
				List<Object[]> itens = s.createQuery("select t.id, t.nome, t.cargaHoraria, t.avaliacao, t.resumo" +
						" from TrainingSolution t" +
						" join t.trainingFormationItens item" +
						" join item.trainingFormation formation" +
						" where formation.id="+formacao.getId()+ " order by item.itemOrder").list();
				request.setAttribute("itens"+formacao.getId(),itens);
				formacoes.add(formacao);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		request.setAttribute("pageNumber",pnumber);
		request.setAttribute("paginate", paginate);
		request.setAttribute("formacoes",formacoes);
		return new ModelAndView("formacao/index_formacoes");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showFormacoesByKeywordFormView(HttpServletRequest request,
            HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		Vector formacoes = new Vector();
		String strint = request.getParameter("pageNumber");
		int fresult = 0;
		int pnumber = 1;
		if(strint != null && !strint.equals("")){
			pnumber = Integer.parseInt(strint);
			fresult = (PAGE_FORMATION_MAX_NUMBER*pnumber);
		}
		
		int paginate = 0;
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			String kw = request.getParameter("keywordTextField");	
			//String queryByFormation ="select count(l) from "+formationName+" l join l.trainingFormationItens tsi where lower(tsi.trainingSolution.keyWords) like '%"+kw.toLowerCase()+"%'";
			String kws[] = null;
			if (kw.indexOf(",")>0){
				kws = kw.split(",");
			}else kws = new String[]{kw};
			String queryByTrain ="select t.id from TrainingSolution t  where";
			for (int i=0; i < kws.length; i++){
				queryByTrain+=" lower(t.keyWords) like '%"+kws[i].toLowerCase().trim()+"%'";
				if (i < (kws.length - 1)) queryByTrain+=" or ";
			}
			queryByTrain+=")";
			List<Integer> trein = s.createQuery(queryByTrain).list();
			Hashtable<Integer, Integer> ht = new Hashtable();
			if (trein != null && trein.size()>0){
				for (Integer id: trein){
					//s.createQuery("select t")
					String queryByFormationId ="select l.trainingFormation.id from TrainingFormationItem l where l.trainingSolution.id="+id;
					List<Integer> forms = s.createQuery(queryByFormationId).list();
					if (forms != null && forms.size()>0)
						for (Integer id2: forms)
							ht.put(id2, id2);
				}
			}
			
			int tamanho = (Integer) ht.size();
			
			if (tamanho > PAGE_FORMATION_MAX_NUMBER){
				paginate = tamanho/(PAGE_FORMATION_MAX_NUMBER+1);
			}
			
			if (ht.size() == 0) return new ModelAndView("formacao/index_formacoes");
			Iterator<Integer> it = ht.keySet().iterator();
			while(it.hasNext()){
				Integer id = it.next();
				FormacaoTreinamento formacao = (FormacaoTreinamento) s.load(FormacaoTreinamento.class,id);
				if (formacao.getImagem() != null) formacao.getImagem().getId();
				List<Object[]> itens = s.createQuery("select t.id, t.nome, t.cargaHoraria, t.avaliacao, t.resumo" +
						" from TrainingSolution t" +
						" join t.trainingFormationItens item" +
						" join item.trainingFormation formation" +
						" where formation.id="+formacao.getId()+ " order by item.itemOrder").list();
				request.setAttribute("itens"+formacao.getId(),itens);
				formacoes.add(formacao);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		request.setAttribute("pageNumber",pnumber);
		request.setAttribute("paginate", paginate);
		request.setAttribute("formacoes",formacoes);
		return new ModelAndView("formacao/index_formacoes");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showFormacoesByKeywordFormView_(HttpServletRequest request,
            HttpServletResponse response){
		
		return new ModelAndView("formacao/index_formacoes");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showFormacoesFormView(HttpServletRequest request,
            HttpServletResponse response){
		ApresentacaoViewController.countAcess(request,null,"formation");
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String tecid = request.getParameter("tec");
		if (tecid != null && !tecid.equals("")){
			request.setAttribute("tec",tecid);
			System.out.println("Filtrando formações pela tecnologia " + tecid);
			return showFormacoesFormViewByTecnologies(request, response);
		}
		Vector formacoes = new Vector();
		String strint = request.getParameter("pageNumber");
		int fresult = 0;
		int pnumber = 1;
		if(strint != null && !strint.equals("")){
			pnumber = Integer.parseInt(strint);
			fresult = (PAGE_FORMATION_MAX_NUMBER*pnumber);
		}
		
		int paginate = 0;
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			int tamanho = (Integer) s.createQuery("select count(l) from "+formationName+" l").uniqueResult();
			if (tamanho > PAGE_FORMATION_MAX_NUMBER){
				paginate = tamanho/(PAGE_FORMATION_MAX_NUMBER+1);
			}
			List li = s.createQuery("from "+formationName+" l order by l.ordemPreferencia").setMaxResults(PAGE_FORMATION_MAX_NUMBER).setFirstResult(fresult).list();
			Iterator<FormacaoTreinamento> it = li.iterator();
			while(it.hasNext()){
				FormacaoTreinamento formacao = it.next();
				if (formacao.getImagem() != null) formacao.getImagem().getId();
				List<Object[]> itens = s.createQuery("select t.id, t.nome, t.cargaHoraria, t.avaliacao, t.resumo" +
						" from TrainingSolution t" +
						" join t.trainingFormationItens item" +
						" join item.trainingFormation formation" +
						" where formation.id="+formacao.getId()+ " order by item.itemOrder").list();
				request.setAttribute("itens"+formacao.getId(),itens);
				formacoes.add(formacao);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		request.setAttribute("pageNumber",pnumber);
		request.setAttribute("paginate", paginate);
		request.setAttribute("formacoes",formacoes);
		return new ModelAndView("formacao/index_formacoes");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showFormacoesForm_View(HttpServletRequest request,
            HttpServletResponse response){
		String pageUrl = request.getParameter("pageUrl");
		if (pageUrl == null || pageUrl.length()<= 5) request.setAttribute("pageUrl", "showFormacoes.html");
		else{
			String tec = request.getParameter("tec");
			if (tec!= null) pageUrl+="?tec="+tec;
			request.setAttribute("pageUrl", pageUrl);
		}
		return new ModelAndView("telaTreinamentos");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showFormacoesIndexFormView(HttpServletRequest request,
            HttpServletResponse response){
		showFormacoesFormView(request,response);
		return new ModelAndView("formacao/index");
	}
	

	@SuppressWarnings("unchecked")
	public ModelAndView showScheduledTrainingsFormView(HttpServletRequest request,
            HttpServletResponse response){		
		
		DatePropertyEditor dt = new DatePropertyEditor();
		dt.setFormat(messages.getMessage("formats.date"));
		ApresentacaoViewController.countAcess(request,null,"schedtrain");			
		
		Hashtable<String,List<Treinamento>> treinamentosHt = new Hashtable<String,List<Treinamento>>();
		Vector<Treinamento> lotesMarcados = new Vector();
		Vector<Treinamento> todosTreinamentos = new Vector();
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();			
			List li = s.createQuery("select l from "+treinamentoName+" l where l.agendaTreinamento.inicioPrevisto > current_date ").setMaxResults(PAGE_FORMATION_MAX_NUMBER).setFirstResult(0).list();
			//List li = s.createQuery("select at.id from AgendaTreinamento at where at.inicioPrevisto > current_date group by at.id, date(at.inicioPrevisto)").setMaxResults(PAGE_FORMATION_MAX_NUMBER).setFirstResult(0).list();
			Iterator it = li.iterator();
			System.out.println("encontrados " + li.size());
			boolean groupby=false;
			while(it.hasNext()){
				Object obj = it.next();
				System.out.println(obj);
				if (obj instanceof Object[]){ System.out.println("é um vetor");
					Object[] objs = (Object[]) obj;
					if (objs[1] != null && objs[1] instanceof String){
						System.out.println(objs[1]);
						System.out.println(objs[0].getClass().getSimpleName());
					}
					Date date = (Date) objs[1];
					List<Treinamento> treinamentos = (List) objs[0];
					try {
						UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
						if (user != null){
							Iterator<Treinamento> ituser = treinamentos.iterator();
							while (ituser.hasNext()){
								Treinamento l = ituser.next();
								String queryMarked = "Select u.nome from Participante u where u.meusTreinamentos.id="+l.getId();
								Object objuser = s.createQuery(queryMarked).uniqueResult();
								if (objuser != null){
									lotesMarcados.add(l);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					dt.setValue(date);					
					treinamentosHt.put(dt.getAsText(), treinamentos);
				}else if (obj instanceof Treinamento){
					System.out.println("Treinamento instance");
					Treinamento t = (Treinamento) obj;
					t.getAgendaTreinamento().getId();
					todosTreinamentos.add(t);
					Date date = t.getAgendaTreinamento().getInicioPrevisto();
					dt.setValue(date);
					if (treinamentosHt.containsKey(dt.getAsText())){
						List<Treinamento> list = treinamentosHt.get(dt.getAsText());
						list.add(t);
					}else{
						List<Treinamento> list = new ArrayList<Treinamento>();
						list.add(t);
						treinamentosHt.put(dt.getAsText(),list);
					}
				}
			}
		
			if (!groupby){				
				List<Treinamento> treinamentos = todosTreinamentos;
				try {
					UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
					if (user != null){
						Iterator<Treinamento> ituser = treinamentos.iterator();
						while (ituser.hasNext()){
							Treinamento l = ituser.next();
							if (l.getInteressados() != null && l.getInteressados().size()>0){
								for(Participante p : l.getInteressados()){
									if (p.getUser().getLogin().equals(user.getId())){
										lotesMarcados.add(l);
										break;
									}
								}
							}
							/*String queryMarked = "Select u.nome from Participante u where u.meusTreinamentos.id="+l.getId();
							Object objuser = s.createQuery(queryMarked).uniqueResult();
							if (objuser != null){
								lotesMarcados.add(l);
							}*/
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		
		request.setAttribute("treinamentosMarcados",lotesMarcados);
		request.setAttribute("treinamentosHtList",treinamentosHt);		
		return new ModelAndView("turma/index_treinamentos_turmas");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showShoppingCartFormView(HttpServletRequest request,
            HttpServletResponse response){		
		
		DatePropertyEditor dt = new DatePropertyEditor();
		dt.setFormat(messages.getMessage("formats.date"));
		UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
		if (user == null) return new ModelAndView("emptyKart");
		
		List<Treinamento> meusTreinamentos = null;
		List<Treinamento> marked=new Vector<Treinamento>();
		Hashtable<Treinamento,ComercialSolutionItem> ht = new Hashtable();
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			Participante p = (Participante) s.createQuery("select p from Participante p where p.user.login='"+user.getId()+"'").uniqueResult();
			meusTreinamentos = (List<Treinamento>) p.getMeusTreinamentos();
			/*meusTreinamentos = s.createQuery("select l from "+treinamentoName+" l " +
					" where l.agendaTreinamento.inicioPrevisto > current_date" +
					" and l in user.dadosPessoais.id")
					.setMaxResults(PAGE_FORMATION_MAX_NUMBER).setFirstResult(0).list();	*/
			Iterator<Treinamento> ituser = meusTreinamentos.iterator();
			while (ituser.hasNext()){
				Treinamento l = ituser.next();
				l.getAgendaTreinamento().getId();
				try {
					ComercialSolutionItem item = (ComercialSolutionItem) s.createQuery("select item from ComercialSolutionItem item where " +
							" item.treinamento.id="+l.getId()+
							" and item.inscrito.id="+p.getId()).uniqueResult();
					if (item != null){
						item.getValorAcertado();
						ht.put(l, item);
					}
				} catch (NonUniqueObjectException e) {
					
				}
				catch (Exception e) {e.printStackTrace();}
				String queryMarked = "Select u.nome from Participante u" +
						" join u.itensTreinamentos item" +
						" where item.treinamento.id="+l.getId()+
						" and item.confirmada=true";
				Object objuser = s.createQuery(queryMarked).uniqueResult();
				if (objuser != null){
					marked.add(l);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		request.setAttribute("itens", ht);	
		request.setAttribute("meusTreinamentos", meusTreinamentos);	
		request.setAttribute("treinamentosCondirmados", marked);	
		return new ModelAndView("treinamento/meusTreinamentos");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showScheduledTrainingsForm_View(HttpServletRequest request,
            HttpServletResponse response){
		showScheduledTrainingsFormView(request,response);
		return new ModelAndView("formacao/agenda_formacoes");
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public ModelAndView showCatalogoFormacaoView(HttpServletRequest request,
            HttpServletResponse response){
		
		ApresentacaoViewController.countAcess(request,null,"formationcatalog");
		Hashtable<Integer,Vector<Treinamento>> leiloesHt = new Hashtable<Integer,Vector<Treinamento>>();
		Vector<Treinamento> lotesMarcados = new Vector();
		String strid = request.getParameter("formacao_id");
		Vector<Treinamento> treinamentos = new Vector<Treinamento>();
		Vector<TurmaTreinamento> turmas = new Vector<TurmaTreinamento>();
		Vector<TrainingSolution> solutions = new Vector<TrainingSolution>();
		Session s =null;
		if (strid != null && !strid.equals(""))try {			
			s= LocalServicesUtility.getInstance().openSession();
			
			//FormacaoTreinamento f = (FormacaoTreinamento) s.load(FormacaoTreinamento.class, Integer.parseInt(strid));
			FormacaoTreinamento f = (FormacaoTreinamento) s.createQuery("select f from FormacaoTreinamento f where f.id="+strid).uniqueResult();
			//f.getTrainingFormationItens().iterator();
			
			{
				
				List list = LocalTreinamentoService.getInstance()
				.getTrainingFormationItensByFormationId(f.getId());
				f.setTrainingFormationItens(new ArrayList<TrainingFormationItem>());
			
				if (list != null && list.size() > 0) {
					int ne = list.size();
					java.util.Iterator<TrainingFormationItem> it = list.iterator();
									
					while (it.hasNext()) {						
						TrainingFormationItem ip = (TrainingFormationItem) it.next();
						
						Object[] prods = LocalTreinamentoService.getInstance()
							.getTrainingFormationItemPropertiesByItemId(ip.getId());
						String catname = (String) prods[prods.length-1];
											
						TrainingSolution prod = new TrainingSolution();
						prod.setId((Integer) prods[0]);
						prod.setCargaHoraria((Integer)prods[4]);					
						prod.setAvaliacao((Float)prods[3]);
						prod.setNome((String)prods[2]);
						prod.setDataCriacao((Date)prods[1]);
						
						Categoria cat = new Categoria();
						cat.setNome(catname);
						prod.setCategoria(cat);
						
						ip.setTrainingSolution(prod);
						solutions.add(prod);
					}
				}
			}
			
			/*if (f.getTrainingFormationItens() != null && f.getTrainingFormationItens().size()>0){
				System.out.println("Tem itens");
				Iterator<TrainingFormationItem> items = f.getTrainingFormationItens().iterator();
				while(items.hasNext()){
					TrainingFormationItem item  = items.next();
					System.out.println(item.getTrainingSolution().getCodigo());
					solutions.add(item.getTrainingSolution());
				}
			}*/
			
			TurmaTreinamento turma = null;
			
			try {
				turma = (TurmaTreinamento) s.createQuery(
						"select turma from TurmaTreinamento turma" +
						" where turma.formacao.id=:idform" +
						" and turma.dataTreinamento > current_date")
				.setParameter("idform", new Integer(strid)).uniqueResult();
			} catch (RuntimeException e1) {
				e1.printStackTrace();
			}
			if (turma != null){
				turma.getImagem();
				
				turma.setFormacao(f);
				
				
				if (turma.getFormacao()!= null){
					turma.getFormacao().getId();
					request.setAttribute("formacao",turma.getFormacao());
					turma.getFormacao().getTechnology().name().replace("_", " ");
				}
				turma.getEnderecoPresencial().getEstado();
				turma.getInstrutor().getNome();
				turma.getTreinamentos().iterator();
				turmas.add(turma);
			
				
				List li = s.createQuery("from Treinamento l where l.turma.id="+strid+"  order by l.agendaTreinamento.inicioPrevisto").list();
				
				treinamentos.addAll(li);
				try{
					Iterator<Treinamento> it = treinamentos.iterator();
					while (it.hasNext()){
						Treinamento l = it.next();
						l.getTreinamento().getDescricao();
						if (l.getTreinamento().getUtilizarFormatador())
							l.getTreinamento().getCategoria().getNome();
						if (l.getAgendaTreinamento() != null)
							l.getAgendaTreinamento().getInicioPrevisto().getDate();
						if (l.getAgendaTreinamento() != null)
							l.getAgendaTreinamento().getTerminoPrevisto().getDate();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				
				try {
					UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
					if (user != null){
						Iterator<Treinamento> it = treinamentos.iterator();
						while (it.hasNext()){
							Treinamento l = it.next();
							String queryMarked = "Select u.nome from Participante u where u.meusTreinamentos.id="+l.getId();
							Object obj = s.createQuery(queryMarked).uniqueResult();
							if (obj != null){
								lotesMarcados.add(l);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		
		
		
		if (strid != null && !strid.equals("")) leiloesHt.put(new Integer(strid),treinamentos);
		
		request.setAttribute("pagina","catalogoTurma.jsp");
		request.setAttribute("treinamentosMarcados",lotesMarcados);
		request.setAttribute("treinamentosList",treinamentos);
		request.setAttribute("turmas",turmas);
		request.setAttribute("treinamentosTurma",leiloesHt);
		request.setAttribute("solutions", solutions);
		
		return new ModelAndView("treinamento/index");
	} 
	
	
	public ModelAndView showCatalogoFormacao_View(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println("showCatalogoFormacao_View");
		showCatalogoFormacaoView(request,response);
		return new ModelAndView("treinamento/catalogoTreinamentosFormacao");
	}
	
	/*@SuppressWarnings("unchecked")
	public ModelAndView showTreinamentosCatalogoFormacao_View(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println("showCompleteCatalogoFormacao_View");
		Hashtable<Integer,Vector<Treinamento>> leiloesHt = new Hashtable<Integer,Vector<Treinamento>>();
		Vector<Treinamento> lotesMarcados = new Vector();
		String strid = request.getParameter("formacao_id");
		Vector<Treinamento> treinamentos = new Vector<Treinamento>();
		Vector<TurmaTreinamento> turmas = new Vector<TurmaTreinamento>();
		Session s =null;
		if (strid != null && !strid.equals(""))try {			
			s= LocalServicesUtility.getInstance().openSession();
			TurmaTreinamento turma = (TurmaTreinamento) s.createQuery(
					"select turma from TurmaTreinamento turma" +
					" where turma.formacao.id=:idform" +
					" and turma.dataTreinamento > current_date")
			.setParameter("idform", new Integer(strid)).uniqueResult();
			FormacaoTreinamento f = (FormacaoTreinamento) s.createQuery("select f from FormacaoTreinamento f where f.id="+strid).uniqueResult();
			if (turma != null){
				turma.getImagem();				
				turma.setFormacao(f);
				
				if (turma.getFormacao()!= null){
					turma.getFormacao().getId();
					request.setAttribute("formacao",turma.getFormacao());
					turma.getFormacao().getTechnology().name().replace("_", " ");
				}
				turma.getEnderecoPresencial().getEstado();
				turma.getInstrutor().getNome();
				turma.getTreinamentos().iterator();
				turmas.add(turma);
			
				
				List li = s.createQuery("from Treinamento l where l.turma.id="+strid+"  order by l.agendaTreinamento.inicioPrevisto").list();
				
				treinamentos.addAll(li);
				try{
					Iterator<Treinamento> it = treinamentos.iterator();
					while (it.hasNext()){
						Treinamento l = it.next();
						l.getTreinamento().getDescricao();
						if (l.getTreinamento().getUtilizarFormatador())
							l.getTreinamento().getCategoria().getNome();
						if (l.getAgendaTreinamento() != null)
							l.getAgendaTreinamento().getInicioPrevisto().getDate();
						if (l.getAgendaTreinamento() != null)
							l.getAgendaTreinamento().getTerminoPrevisto().getDate();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}
				
				try {
					UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
					if (user != null){
						Iterator<Treinamento> it = treinamentos.iterator();
						while (it.hasNext()){
							Treinamento l = it.next();
							String queryMarked = "Select u.nome from Participante u where u.meusTreinamentos.id="+l.getId();
							Object obj = s.createQuery(queryMarked).uniqueResult();
							if (obj != null){
								lotesMarcados.add(l);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		
		
		
		if (strid != null && !strid.equals("")) leiloesHt.put(new Integer(strid),treinamentos);
		
		request.setAttribute("pagina","catalogoTurma.jsp");
		request.setAttribute("treinamentosMarcados",lotesMarcados);
		request.setAttribute("treinamentosList",treinamentos);
		request.setAttribute("turmas",turmas);
		request.setAttribute("treinamentosTurma",leiloesHt);
		return new ModelAndView("treinamento/catalogoTreinamentosFormacao");
	}*/
	
	 
	@SuppressWarnings("unchecked")
	public ModelAndView showScheduledTrainingsIndexFormView(HttpServletRequest request,
            HttpServletResponse response){
		showScheduledTrainingsFormView(request,response);
		return new ModelAndView("formacao/index");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showAboutTrainingsView(HttpServletRequest request,
            HttpServletResponse response){
		showScheduledTrainingsFormView(request,response);
		ApresentacaoViewController.countAcess(request,null,"train_about");
		return new ModelAndView("treinamento/sobre");
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public ModelAndView showTreinamentosByTurmaView(HttpServletRequest request,
            HttpServletResponse response){
		ApresentacaoViewController.countAcess(request,null,"train_by_class");
		String strid = request.getParameter("turma_id");
		Vector<Treinamento> treinamentos = new Vector<Treinamento>();
		
		Session s =null;
		if (strid != null && !strid.equals(""))try {			
			s= LocalServicesUtility.getInstance().openSession();
			TurmaTreinamento turma = (TurmaTreinamento) s.createQuery(
					"select turma from TurmaTreinamento turma" +
					" where turma.id=:idform")
			.setParameter("idform", new Integer(strid)).uniqueResult();
			
			List<Treinamento>  trs = s.createQuery(
					"select t from Treinamento t " +
					" where t.turma.id=:idform order by t.agendaTreinamento.inicioPrevisto")
			.setParameter("idform", new Integer(strid)).list();
			
			turma.setTreinamentos(trs);
			if (turma != null){
				turma.getTreinamentos().iterator().hasNext();
				treinamentos.addAll(turma.getTreinamentos());
				try{
					Iterator<Treinamento> it = treinamentos.iterator();
					while (it.hasNext()){
						Treinamento l = it.next();
						l.getTreinamento().getDescricao();
						if (l.getTreinamento().getUtilizarFormatador())
							l.getTreinamento().getCategoria().getNome();
						if (l.getAgendaTreinamento() != null)
							l.getAgendaTreinamento().getInicioPrevisto().getDate();
						if (l.getAgendaTreinamento() != null)
							l.getAgendaTreinamento().getTerminoPrevisto().getDate();
					}
				}
				catch(Exception ex){
					ex.printStackTrace();
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		
		request.setAttribute("treinamentosList",treinamentos);
		
		return new ModelAndView("treinamento/treinamentosAgendadosInside");
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public ModelAndView showCatalogoTurmasView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println("showCatalogoTurmasView");
		ApresentacaoViewController.countAcess(request,null,"classcatalog");
		Hashtable<Integer,Vector<Treinamento>> leiloesHt = new Hashtable<Integer,Vector<Treinamento>>();
		Vector<Treinamento> lotesMarcados = new Vector();
		String strid = request.getParameter("formacao_id");
		Session s =null;
		Vector<Treinamento> treinamentos = new Vector<Treinamento>();
		Vector<TurmaTreinamento> turmas = new Vector<TurmaTreinamento>();
		if (strid != null && !strid.equals(""))
			return showCatalogoFormacaoView(request,response);
		else try {	
			
			
			s= LocalServicesUtility.getInstance().openSession();
			List turmasList= s.createQuery(
					"select turma from TurmaTreinamento turma" +
					" where turma.dataEncerramento > current_date order by turma.dataTreinamento").list();
			Iterator turmasIterator = turmasList.iterator();
			//System.out.println(turmasList.size());
			while(turmasIterator.hasNext()){
				TurmaTreinamento turma = (TurmaTreinamento) turmasIterator.next();
				if (turma != null){
					turma.getImagem();					
					/*if (turma.getFormacao()!= null){
						turma.getFormacao().getId();
						request.setAttribute("formacao",turma.getFormacao());
						turma.getFormacao().getTechnology().name().replace("_", " ");
					}*/
					//turma.getEnderecoPresencial().getEstado();
					/*turma.getInstrutor().getNome();
					try {
						turma.getInstrutor().getTipoPessoa().getId();
						turma.getInstrutor().getLogotipo();
					} catch (Exception e) {
						e.printStackTrace();
					}*/
					turma.getTreinamentos().iterator();
					turmas.add(turma);
				
					/*List li = s.createQuery("select l from Treinamento l where l.turma.id="+strid+"  order by l.agendaTreinamento.inicioPrevisto").list();
					
					treinamentos.addAll(li);*/
					try{
						Iterator<Treinamento> it = turma.getTreinamentos().iterator();
						while (it.hasNext()){
							Treinamento l = it.next();
							l.setTurma(turma);
							System.out.println(l.getResumo());
							l.getTreinamento().getDescricao();
							if (l.getTreinamento().getUtilizarFormatador())
								l.getTreinamento().getCategoria().getNome();
							if (l.getAgendaTreinamento() != null)
								l.getAgendaTreinamento().getInicioPrevisto().getDate();
							if (l.getAgendaTreinamento() != null)
								l.getAgendaTreinamento().getTerminoPrevisto().getDate();
						}
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		
		
		
		if (strid != null && !strid.equals("")) leiloesHt.put(new Integer(strid),treinamentos);
		
		request.setAttribute("pagina","catalogoTurma.jsp");
		request.setAttribute("treinamentosMarcados",lotesMarcados);
		request.setAttribute("turmas",turmas);
		request.setAttribute("treinamentosTurma",leiloesHt);
		
		return new ModelAndView("treinamento/turmas");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showDetalhesInstrutorView(HttpServletRequest request,
            HttpServletResponse response){
		String strid = request.getParameter("turma_id");
		Session s =null;
		try {
			s= LocalServicesUtility.getInstance().openSession();
			Instrutor instrutor= (Instrutor)s.createQuery(	"select turma.instrutor from TurmaTreinamento turma" +
					" where turma.id="+strid+"").uniqueResult();
			instrutor.getNome();
			try {
				instrutor.getTipoPessoa().getId();
				if (instrutor.getLogotipo() != null)
					instrutor.getLogotipo().getId();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("instrutor",instrutor);
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
				
		return new ModelAndView("treinamento/dados_instrutor");
	}
	
	public ModelAndView showDetalhesEnderecoView(HttpServletRequest request,
            HttpServletResponse response){
		String strid = request.getParameter("turma_id");
		Session s =null;
		try {
			s= LocalServicesUtility.getInstance().openSession();
			Endereco ender= (Endereco)s.createQuery(	"select turma.enderecoPresencial from TurmaTreinamento turma" +
					" where turma.id="+strid+"").uniqueResult();
			ender.getRua();			
			request.setAttribute("endereco",ender);
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
				
		return new ModelAndView("treinamento/dados_endereco");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showMenuHorizontalView(HttpServletRequest request,
            HttpServletResponse response){	
		System.out.println("menu horiz");
		return new ModelAndView("layout/menuhoriz/horizmenu");
	}
	

	@SuppressWarnings("unchecked")
	public ModelAndView showInicioContentView(HttpServletRequest request,
            HttpServletResponse response){	
		return new ModelAndView("layout/index");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showInicioContentMenuView(HttpServletRequest request,
            HttpServletResponse response){	
		return new ModelAndView("layout/index_red");
	}
	
	
	public ModelAndView showImageByImageIdView(HttpServletRequest request,HttpServletResponse response){		
		try {				            
			
			byte barr[] = imagemService.getSmallImageBytesFromImage(Integer.parseInt(request.getParameter("image_id")));
			
			String format = request.getParameter("image_format");		
			
			if (format.equalsIgnoreCase("jpg")) response.setContentType("image/jpeg");
			else response.setContentType("image/"+format);			
			           
            InputStream in = new ByteArrayInputStream(barr);
            OutputStream out = response.getOutputStream();
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
 
            in.close();
            out.flush();
            out.close(); 
			
		} catch (Exception e) {
			e.printStackTrace();			
		}		
		
		return null;
	}
	
	public ModelAndView showCategoriaImageView(HttpServletRequest request,HttpServletResponse response){
		
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
	
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
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

	/**
	* @spring.property ref="imagemService" singleton="true"
	*/
	public ImagemService getImagemService() {
		return imagemService;
	}


	public void setImagemService(ImagemService imageService) {
		this.imagemService = imageService;
	}
	
	
}