package com.adapit.portal.services.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.adapit.portal.dto.UsuarioDTO;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.CommercialSolutionDetailTab;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.validation.TreinamentoServiceValidator;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.WebResourceMessage;

public class TreinamentosMultiActionController extends MultiActionController implements InitializingBean {

	private ResourceMessage messages;	

	public TreinamentosMultiActionController(){
		messages = WebResourceMessage.getInstance();
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showCatalogoLeilaoView(HttpServletRequest request,
            HttpServletResponse response){
		
		
		Hashtable<Integer,Vector<Treinamento>> leiloesHt = new Hashtable<Integer,Vector<Treinamento>>();
		Vector<Treinamento> lotesMarcados = new Vector();
		String strid = request.getParameter("leilao_id");
		Vector<Treinamento> lotes = new Vector<Treinamento>();
		Vector<TurmaTreinamento> leiloes = new Vector<TurmaTreinamento>();
		Session s =null;
		if (strid != null && !strid.equals(""))try {			
			s= LocalServicesUtility.getInstance().openSession();
			TurmaTreinamento leilao = (TurmaTreinamento) s.load(TurmaTreinamento.class, new Integer(strid));
			leilao.getComitente();
			leilao.getCondicaoParticipacao().getTexto();
			leilao.getEnderecoPresencial().getEstado();
			leilao.getInstrutor().getNome();
			leilao.getTreinamentos().iterator();
			leiloes.add(leilao);
		
			
			//and ((l.agendaLote is null) or (l.agendaLote.terminoPrevisto > current_timestamp()) or (l.leilao.dataPresencialSegunda > current_timestamp()))
			//List li = s.createQuery("from Lote l where l.leilao.id="+strid+" and ((l.agendaLote is not null and l.agendaLote.confirmada=true and l.agendaLote.encerrada=false and l.agendaLote.recebendoLances=true )  ) order by l.codLote").list();
			List li = s.createQuery("from Lote l where l.leilao.id="+strid+"  order by l.codLote").list();
			lotes.addAll(li);
			
			try {
				UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
				if (user != null){
					Iterator<Treinamento> it = lotes.iterator();
					while (it.hasNext()){
						Treinamento l = it.next();
						String queryMarked = "Select u.nome from Participante u where u.meusLotes.id="+l.getId();
						Object obj = s.createQuery(queryMarked).uniqueResult();
						if (obj != null){
							lotesMarcados.add(l);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		
		
		
		if (strid != null && !strid.equals("")) leiloesHt.put(new Integer(strid),lotes);
		
		request.setAttribute("pagina","catalogoLeilao.jsp");
		request.setAttribute("lotesMarcados",lotesMarcados);
		request.setAttribute("leiloes",leiloes);
		request.setAttribute("lotesLeilao",leiloesHt);
		
		return new ModelAndView("lote/index");
	}
	
	public ModelAndView showCatalogoLeilao_View(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println("showCatalogoLeilaoView");
		showCatalogoLeilaoView(request,response);
		//return new ModelAndView("leilao/lista_de_lotes");
		return new ModelAndView("lote/catalogoLeilao");
	}
	
	
	public ModelAndView showLotesLeilaoView(HttpServletRequest request,
            HttpServletResponse response){
		return new ModelAndView("lotesEmLeilaoTablePane");
	}
	
	public ModelAndView showMediumImageView(HttpServletRequest request,
            HttpServletResponse response){
		/*Imagem im = (Imagem) request.getAttribute("image");
		if (im != null){
			request.getSession().setAttribute("image", im);
		}*/
		setImageIntoSession(request,response);
		return new ModelAndView("treinamento/mediumImage");
	}
	
	public ModelAndView showLargeImageView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showLargeImageView");
		/*Imagem im = (Imagem) request.getAttribute("image");	
		if (im != null){
			System.out.println("im != null");
			request.getSession().setAttribute("image", im);
		}
		else{
			
			System.out.println("im == null");
		}*/
		setImageIntoSession(request,response);
		return new ModelAndView("treinamento/largeImage");
	}
	
	public void setImageIntoSession(HttpServletRequest request,
            HttpServletResponse response){
		String imid = request.getParameter("image_id");
		Session s =null;
		if(imid != null)try {	
			System.out.println("pegando uma imagem pelo id " + imid);
			s= LocalServicesUtility.getInstance().openSession();
			Imagem im = (Imagem) s.load(Imagem.class, Integer.parseInt(imid));
			im.getRotulo();
			im.getDescription();
			im.getId();
			request.getSession().setAttribute("image", im);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			s.close();
		}
	}
	
	public ModelAndView showFullImageView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showFullImageView");
		/*Imagem im = (Imagem) request.getAttribute("image");	
		if (im != null){
			System.out.println("im != null");
			request.getSession().setAttribute("image", im);
		}
		else System.out.println("im == null");*/
		setImageIntoSession(request,response);
		return new ModelAndView("treinamento/fullImage");
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showLotesByIdLeilaoView(HttpServletRequest request,
            HttpServletResponse response){
			
		String strid = request.getParameter("leilao_id");
		Vector<Treinamento> lotes = new Vector<Treinamento>();
		Vector<Treinamento> lotesMarcados = new Vector();
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			TurmaTreinamento leilao = (TurmaTreinamento) s.load(TurmaTreinamento.class, new Integer(strid));
			leilao.getComitente();
			leilao.getCondicaoParticipacao().getTexto();
			
			List li = s.createQuery("from Lote l where l.leilao.id="+strid+" and ((l.agendaLote is null and l.leilao.dataPresencialSegunda > current_timestamp())  ) order by l.codLote").list();
			
			lotes.addAll(li);
			
			try {
				UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
				if (user != null){
					Iterator<Treinamento> it = lotes.iterator();
					while (it.hasNext()){
						Treinamento l = it.next();
						String queryMarked = "Select u.nome from Participante u join u.meusLotes l where u.id="+user.getParticipanteId()+" and l.id="+l.getId();
						Object obj = s.createQuery(queryMarked).uniqueResult();
						if (obj != null){
							lotesMarcados.add(l);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

/*			Iterator<Lote> it = lotes.iterator();
			while (it.hasNext()){
				Lote l = it.next();
				String queryMarked = "Select u.nome from Participante u where u.meusLotes.id="+l.getId();
				Object obj = s.createQuery(queryMarked).uniqueResult();
				if (obj != null){
					lotesMarcados.add(l);
				}
			}
			
			*/
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		request.setAttribute("lotesMarcados",lotesMarcados);
		request.setAttribute("loteList",lotes);
		return new ModelAndView("lotesEmLeilaoTablePane");
	}
	
	
	@SuppressWarnings("unchecked")
	public ModelAndView showTreinamentosByCategoriaNomeView(HttpServletRequest request,
            HttpServletResponse response){		
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String catNames = request.getParameter("categoriaNomes");
		if (catNames == null) return null;
		String names[] = catNames.split(",");
		Hashtable<Integer,Treinamento> lotesHt = new Hashtable();
		Vector<Treinamento> lotesMarcados = new Vector();
		Vector<Treinamento> lotes = new Vector<Treinamento>();
		request.setAttribute("categorias", names);
		Session s =null;
		s= LocalServicesUtility.getInstance().openSession();
		try {
			String query="from ItemProduto i where (lower(i.produto.categoria.nome) IN (";
			for(int i=0; i < names.length; i++){
				query+="'"+names[i].toLowerCase()+"'";
				if (i == names.length-1) ;
				else query+=",";
			}
			query+=")) and ((i.lote.agendaLote is not null and i.lote.agendaLote.confirmada=true and i.lote.agendaLote.encerrada=false and i.lote.agendaLote.recebendoLances=true )  ) order by i.lote.codLote";
			System.out.println("Lista de lotes pela categoria: "+query);
			List li = s.createQuery(query).list();
			System.out.println("Econtrados " + li.size() + " itens");
			Iterator<ComercialSolutionItem> itp = li.iterator();
			while(itp.hasNext()){
				ComercialSolutionItem ip = itp.next();
				if (ip.getTreinamento() == null) continue;
				int loteCod = ip.getTreinamento().getId();
				
				if (lotesHt.containsKey(loteCod)){
					
				}else{
					/*if (ip.getLote().getComitente() != null)ip.getLote().getComitente().getId();*/
					if (ip.getTreinamento().getAgendaTreinamento() != null)ip.getTreinamento().getAgendaTreinamento().getId();
					if (ip.getTreinamento().getTurma() != null){
						if (ip.getTreinamento().getTurma().getCondicaoParticipacao() != null)
							ip.getTreinamento().getTurma().getCondicaoParticipacao().getId();
						if (ip.getTreinamento().getTurma().getEnderecoPresencial() != null){
							ip.getTreinamento().getTurma().getEnderecoPresencial().getId();
						}
					}
					lotesHt.put(new Integer(loteCod),ip.getTreinamento());
					lotes.add(ip.getTreinamento());
				}
			}
			
			UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
			if (user != null){
				Iterator<Treinamento> it = lotes.iterator();
				while (it.hasNext()){
					Treinamento l = it.next();
					String queryMarked = "Select u.nome from Participante u join u.meusLotes l where u.id="+user.getParticipanteId()
						+" and l.id="+l.getId();
					Object obj = s.createQuery(queryMarked).uniqueResult();
					if (obj != null){
						lotesMarcados.add(l);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		/*try {
			String query="from Lote l where (";
			for(int i=0; i < names.length; i++){
				query+="l.itensProduto.produto.categoria.nome="+names[i]+" ";
				if (i == names.length-1) ;
				else query+=" or ";
			}
			query+=")";
			List li = s.createQuery(query+" and ((l.agendaLote is null and l.leilao.dataPresencialSegunda > current_timestamp())  ) order by l.codLote").list();
			
			lotes.addAll(li);
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}*/
		
		request.setAttribute("lotesMarcados",lotesMarcados);		
		request.setAttribute("loteList",lotes);
		request.setAttribute("pagina","../lotesEmLeilaoByCategorias.jsp");
		//request.setAttribute("pagina","index_agenda_leiloes.jsp");
		return new ModelAndView("leilao/index");
		//return new ModelAndView("lotesEmLeilaoByCategorias");
		
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showDetalhesTrainingSolutionView(HttpServletRequest request,
            HttpServletResponse response){
		//Hashtable<Integer,Vector<ItemProduto>> lotesHt = new Hashtable<Integer,Vector<ItemProduto>>();
		ApresentacaoViewController.countAcess(request,null,"traindetails");
		String strid = request.getParameter("treinamento_id");
		List<CommercialSolutionDetailTab> v = null;		
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			TrainingSolution ts = null;
			if (strid != null){
				Treinamento lote = (Treinamento) s.load(Treinamento.class, new Integer(strid));
				ts = lote.getTreinamento();
				ts.getImagens().iterator();
				v = s.createQuery("select tab from CommercialSolutionDetailTab tab" +
						" where tab.commercial_solution_id=:id")/* +
						" and tab.detail_text_language=:lang")*/
						.setParameter("id", ts.getId())
						//.setParameter("lang", request.getLocale().getLanguage()+"-"+request.getLocale().getCountry())
						.list();
				System.out.println(request.getLocale().getLanguage()+"-"+request.getLocale().getCountry());
			}else{
				strid = request.getParameter("solution_id");
				if (strid != null){
					ts = (TrainingSolution) s.load(TrainingSolution.class, new Integer(strid));
					ts.getImagens().iterator();
					v = s.createQuery("select tab from CommercialSolutionDetailTab tab" +
							" where tab.commercial_solution_id=:id")/* +
							" and tab.detail_text_language=:lang")*/
							.setParameter("id", ts.getId())
							//.setParameter("lang", request.getLocale().getLanguage()+"-"+request.getLocale().getCountry())
							.list();
					System.out.println(request.getLocale().getLanguage()+"-"+request.getLocale().getCountry());
				}
			}
			request.setAttribute("tabList",v);
			request.setAttribute("training",ts);
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		return new ModelAndView("treinamento/detalhesTrainingSolution");
	}
	
	public ModelAndView showDetalhesTreinamentoTableView(HttpServletRequest request,
            HttpServletResponse response){
		//Hashtable<Integer,Vector<ItemProduto>> lotesHt = new Hashtable<Integer,Vector<ItemProduto>>();
		
		String strid = request.getParameter("treinamento_id");
		Vector<ComercialSolutionItem> v = new Vector<ComercialSolutionItem>();		
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			Treinamento lote = (Treinamento) s.load(Treinamento.class, new Integer(strid));
			Iterator<ComercialSolutionItem> it = lote.getComercialSolutionItens().iterator();
			while(it.hasNext()){
				ComercialSolutionItem item = it.next();
				item.getTreinamento().getTreinamento().getAvaliacao();
				item.getTreinamento().getTreinamento().getCategoria().getCategoriaImagem();
				v.add(item);
				
			}
			request.setAttribute("itensTreinamentoList",v);
			request.setAttribute("treinamentoId",new Integer(strid));
			request.setAttribute("treinamento",lote);
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		return new ModelAndView("itensTreinamentoTablePanel");
	}
	
	public ModelAndView participarTurmaTreinamentoView(HttpServletRequest request,
            HttpServletResponse response){	
		
		
		String userid = request.getParameter("usuario_id");
		String loteid = request.getParameter("treinamento_id");

		UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
		
		
		
		
		if (user != null && userid.equals(String.valueOf(user.getId()))){			
			Session s =null;
			try {			
				s= LocalServicesUtility.getInstance().openSession();
				s.getTransaction().begin();
				Usuario u = (Usuario) s.load(Usuario.class,userid);
				Pessoa p = (Pessoa) u.getDadosPessoais();
				if (!(p instanceof Participante)) return null;
				Treinamento lote = (Treinamento) s.load(Treinamento.class, new Integer(loteid));	
				
				/*String query = "Select u.nome from Participante u join u.meusTreinamentos l where u.id="+user.getParticipanteId()
				+" and l.id="+loteid;
				
				Object obj = s.createQuery(query).uniqueResult();
				System.out.println(obj);*/
				
				boolean participar = true;//!(obj != null);
				Participante par = (Participante) p;
				if (par.getMeusTreinamentos() != null && par.getMeusTreinamentos().size()>0){
					for (Treinamento t: par.getMeusTreinamentos()){
						if (t.getId() == lote.getId()){
							participar = false;
							break;
						}
					}
				}
					
			
				
				System.out.println(participar);
				request.setAttribute("marcado",participar);
				ComercialSolutionItem item = null;
				
				try {
					item = (ComercialSolutionItem) s.createQuery("select item from ComercialSolutionItem item where " +
							" item.treinamento.id="+lote.getId()+
							" and item.inscrito.id="+p.getId()).uniqueResult();
				} catch (RuntimeException e) {}
				if (participar){
					Participante part = (Participante)p;
					part.getMeusTreinamentos().add(lote);
					lote.getInteressados().add(part);
					if (item == null){
						item = new ComercialSolutionItem();
						item.setValorAcertado(lote.getTreinamento().getAvaliacao());
						item.setTreinamento(lote);
						item.setInscrito(part);
						item.setTrainingSolution(lote.getTreinamento());
						s.save(item);
					}
				}
				else{					
					Participante part = (Participante)p;
					part.getMeusTreinamentos().remove(lote);
					lote.getInteressados().remove(part);
					if (item != null) s.delete(item);
				}
				s.merge(lote);
				s.merge(p);
				
				
				s.getTransaction().commit();
				System.out.println(" Participante " + p.getNome() + " e Treinamento " + lote.getCodigo()+" " +
						"Atualizados com sucesso on check " + participar );
			} catch (Exception e) {
				e.printStackTrace();
				s.getTransaction().rollback();			
			}finally{
				if (s != null) s.close();
			}
		}else{
			System.err.println("Usuário e sessão estão em desconformidade!!");
			try {
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("login", user.getId());
		request.setAttribute("treinamentoid", new Integer(loteid));
		
		return new ModelAndView("treinamento/inscricaoCheckboxResult");
	}
	
	
	public ModelAndView showImagensCommercialSolutionView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showImagensCommercialSolutionView");
		String strid = request.getParameter("produto_id");
		if (strid == null || strid.equals(""))
			strid = request.getParameter("treinamento_id");
		
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			ComercialSolution produto = (ComercialSolution) s.get(ComercialSolution.class, new Integer(strid));
			Iterator<Imagem> it = produto.getImagens().iterator();
			while(it.hasNext()){
				Imagem im = it.next();
				im.getRotulo();
			}
			request.getSession().setAttribute("treinamento",produto);			
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		return new ModelAndView("treinamento/customImageSlideshow");
	}
	
	public ModelAndView showCommercialSolutionImageDetailsView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showCommercialSolutionImageDetailsView");
		//String strid = request.getParameter("image_id");
		
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			ComercialSolution t = (ComercialSolution) request.getSession().getAttribute("treinamento");
			Imagem im = t.getImagens().get(Integer.parseInt(request.getParameter("img_pos")));//(Imagem) s.load(Imagem.class, new Integer(request.getParameter("image_id")));
			//int next= Integer.parseInt(request.getParameter("next"));
			//int prev = Integer.parseInt(request.getParameter("prev"));
			//request.setAttribute("next", next);
			//request.setAttribute("prev", prev);
			request.getSession().setAttribute("image", im);
			/*ComercialSolution produto = (ComercialSolution) s.get(ComercialSolution.class, new Integer(strid));
			Iterator<Imagem> it = produto.getImagens().iterator();
			while(it.hasNext()){
				Imagem im = it.next();
				im.getRotulo();
			}
			request.setAttribute("treinamento",produto);*/			
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		return new ModelAndView("treinamento/imageDetails");
	}
	
	
	
	public ModelAndView showImageByIdView(HttpServletRequest request,HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showImageByIdView");
		Session s =null;
		try {	
			            
			s= LocalServicesUtility.getInstance().openSession();
			System.out.println("Getting image by id "+request.getParameter("image_id"));
			Imagem im =(Imagem) s.load(Imagem.class, new Integer(request.getParameter("image_id")));
			byte barr[] = im.getFullImageBytes();
			//ImageIcon ii = im.getScaledImage(450);
			///byte barr[] = Imagem.toByteArray(ii.getImage(), im.getFormat()/*Imagem.getImageFormat(im.getFullImageBytes())*/);
			
			String format = im.getFormat();		
			
			if (format.equalsIgnoreCase("jpg")) response.setContentType("image/jpeg");
			else response.setContentType("image/"+format);			
			           
            java.io.InputStream in = new java.io.ByteArrayInputStream(barr);
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
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}		
		
		return null;
	}

	
	public ModelAndView showCommercialSolutionImageDetailsSmallView(HttpServletRequest request,
            HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showCommercialSolutionImageDetailsSmallView");
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			//ComercialSolution t = (ComercialSolution) request.getSession().getAttribute("treinamento");
			//t = (ComercialSolution) s.load(ComercialSolution.class, t.getId());
			System.out.println(request.getParameter("img_pos"));
			/*if (t != null && request.getParameter("img_pos") != null && 
					!request.getParameter("img_pos").trim().equals("") &&
					t.getImagens() != null && t.getImagens().size()>0){
				Imagem im = t.getImagens().get(Integer.parseInt(request.getParameter("img_pos")));
				request.getSession().setAttribute("image", im);		
				
			}else*/{
				int id = Integer.parseInt(request.getParameter("image_id"));	
				System.out.println("tem image id");
				Imagem img =  (Imagem) s.load(Imagem.class, id);
				img.getDescription();
				img.getRotulo();
				
				request.getSession().setAttribute("image",img);
			}
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		return new ModelAndView("treinamento/imageDetailsSmall");
	}
	
	public ModelAndView showCommercialSolutionSmallImageView(HttpServletRequest request,HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showCommercialSolutionSmallImageView");
		Session s =null;
		try {	
			response.setHeader ("Pragma", "no-cache");
			response.setHeader ("Cache-Control", "no-cache");
			response.setDateHeader ("Expires",0);             
			s= LocalServicesUtility.getInstance().openSession();
			Imagem im =(Imagem) s.load(Imagem.class, new Integer(request.getParameter("image_id")));
			byte barr[] = im.getSmallImageBytes();
			String format = im.getFormat();			
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
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}		
		
		return null;//new ModelAndView("layout/servicos");
	}
	
	public ModelAndView showCommercialSolutionBigImageView(HttpServletRequest request,HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showCommercialSolutionBigImageView");
		Session s =null;
		/*if(true)
			return showCommercialSolutionImageDetailsSmallView(request,response);*/
		try {	
			            
			s= LocalServicesUtility.getInstance().openSession();
			Imagem im =(Imagem) s.load(Imagem.class, new Integer(request.getParameter("image_id")));
			ImageIcon ii = im.getScaledImage(450);
			byte barr[] = Imagem.toByteArray(ii.getImage(), im.getFormat()/*Imagem.getImageFormat(im.getFullImageBytes())*/);
			
			/*int index = im.getFullImageBytes().getName().indexOf(".");
			*/
			String format = im.getFormat();/*im.getFullImageBytes().getName().substring(index+1);		*/	
			
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
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}		
		
		return null;
	}
	
	public ModelAndView showCommercialSolutionMediumImageView(HttpServletRequest request,HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showCommercialSolutionMediumImageView");
		Session s =null;
		try {	
			            
			s= LocalServicesUtility.getInstance().openSession();
			Imagem im =(Imagem) s.load(Imagem.class, new Integer(request.getParameter("image_id")));
			ImageIcon ii = im.getScaledImage(300);
			byte barr[] = Imagem.toByteArray(ii.getImage(), im.getFormat());
			
			String format = im.getFormat();	
			
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
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}		
		
		return null;
	}
	
	public ModelAndView showCommercialSolutionFullImageView(HttpServletRequest request,HttpServletResponse response){
		System.out.println(getClass().getSimpleName()+".showCommercialSolutionFullImageView");
		Session s =null;
		try {	
			            
			s= LocalServicesUtility.getInstance().openSession();
			Imagem im =(Imagem) s.load(Imagem.class, new Integer(request.getParameter("image_id")));
			ImageIcon ii = (ImageIcon) im.getImageIcon();
			byte barr[] = Imagem.toByteArray(ii.getImage(), im.getFormat());
			
			String format = im.getFormat();	
			
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
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}		
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public ModelAndView showLoteLikeDescView(HttpServletRequest request,
            HttpServletResponse response){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		String desc = request.getParameter("descricaoProdutoTextField");
		System.out.println("DEscri'c~ao do lote: " + desc);
		Hashtable<Integer,Vector<Treinamento>> leiloesHt = new Hashtable<Integer,Vector<Treinamento>>();		
		
		Vector<TurmaTreinamento> leiloes = new Vector<TurmaTreinamento>();
		
		String likeQuery="";
		if (desc.indexOf('\"')>0){
			likeQuery="lower(l.descricao) like '%"+desc.replaceAll("\"", "").toLowerCase()+"%'";
			System.out.println(desc.replaceAll("\"", "").toLowerCase());
		}else{
			if (desc.indexOf(" ") > 0){
				String descvt[] = desc.split(" ");
				for (int i=0; i < descvt.length; i++){
					String value = descvt[i];
					if (i != 0){
						if (value.indexOf("+") > 0){
							likeQuery+=" and lower(l.descricao) like '%"+value.replaceAll("+","").toLowerCase()+"%'";
						}
						else likeQuery+=" or lower(l.descricao) like '%"+value.toLowerCase()+"%'";
					}
					else{
						likeQuery+="lower(l.descricao) like '%"+value.toLowerCase()+"%'";
					}
				}
			}else{
				likeQuery="lower(l.descricao) like '%"+desc.toLowerCase()+"%'";
			}
		}
		
		Vector<Treinamento> lotesMarcados = new Vector();	
		Session s =null;
		try {			
			s= LocalServicesUtility.getInstance().openSession();
			
			
			List li = s.createQuery("from Lote l where ("+likeQuery+") and ((l.agendaLote is null and l.leilao.dataPresencialSegunda > current_timestamp())  ) order by l.codLote").list();
			
			Iterator<Treinamento> it = li.iterator();
			while (it.hasNext()){
				Treinamento l = it.next();
				TurmaTreinamento leilao =  l.getTurma();
				int leilaoid=leilao.getId();
				if (leiloesHt.containsKey(new Integer(leilaoid))){
					Vector<Treinamento> lotes = leiloesHt.get(new Integer(leilaoid));
					lotes.add(l);
				}else{
					Vector<Treinamento> lotes = new Vector<Treinamento>();
					leiloesHt.put(new Integer(leilaoid),lotes);
					leiloes.add(leilao);
					leilao.getComitente();
					leilao.getCondicaoParticipacao().getTexto();
					leilao.getEnderecoPresencial().getEstado();
					leilao.getInstrutor().getNome();
					lotes.add(l);
				}			
			}
			
			List<Treinamento> lotes = li;
			UsuarioDTO user = (UsuarioDTO) request.getSession(true).getAttribute("user");
			if (user != null){
				Iterator<Treinamento> ite = lotes.iterator();
				while (ite.hasNext()){
					Treinamento l = ite.next();
					String queryMarked = "Select u.nome from Participante u join u.meusLotes l where u.id="+
						user.getParticipanteId()+" and l.id="+l.getId();
					Object obj = s.createQuery(queryMarked).uniqueResult();
					if (obj != null){
						lotesMarcados.add(l);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
		request.setAttribute("lotesMarcados",lotesMarcados);
		request.setAttribute("leiloes",leiloes);
		request.setAttribute("lotesLeilao",leiloesHt);
		return new ModelAndView("catalogoLeilao");
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
	
	
	private TreinamentoServiceValidator loteService;

	/**
	* @spring.property ref="loteService" singleton="true"
	*/
	public TreinamentoServiceValidator getLoteService() {
		return loteService;
	}

	public void setLoteService(TreinamentoServiceValidator loteService) {
		this.loteService = loteService;
	}
	
}