package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.ComercialSolutionHistory;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.CommercialSolutionDetailTab;
import com.adapit.portal.entidades.CommercialSolutionType;
import com.adapit.portal.entidades.CssDefinition;
import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.SoftwareDomainInterest;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.entidades.TrainingFormationItem;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.entidades.SoftwareSolution.SoftwareSolutionType;
import com.adapit.portal.entidades.TrainingSolution.TrainingSolutionType;
import com.adapit.portal.services.ComercialSolutionService;
import com.adapit.portal.services.ScheduleScheduledTrainingFilterType;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.local.LocalImagemService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.util.global.FilterResultSize;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;

/**
 * @spring.bean id="comercialSolutionServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */

public class ComercialSolutionServiceDAOHibernate extends GenericDAOHibernate implements
		ComercialSolutionService, GenericDAO {

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;
	//ublic static int maxResults = 15;
	
	private String entityName = ComercialSolution.class.getSimpleName();
	private String comSolItemEntityName = ComercialSolutionItem.class.getSimpleName();
	
	public ComercialSolutionServiceDAOHibernate() {

	}

	/**
	 * Retorna todos os comercialSolutions em que a descrição contenha o valor passado
	 * como argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByWithName(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" comercialSolution ");
			hql.append(" where ");
			hql.append(" lower(comercialSolution.descricao) like ? ");
			paramList.add("%" + descricao.toLowerCase() + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o comercialSolution em que a descrição inicia pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByNameBeginingWith(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" comercialSolution ");
			hql.append(" where ");
			hql.append(" lower(comercialSolution.descricao) like ? ");
			paramList.add(descricao.toLowerCase() + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o comercialSolution em que a descrição termina pelo valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByNameEndingWith(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" comercialSolution ");
			hql.append(" where ");
			hql.append(" lower(comercialSolution.descricao) like ? ");
			paramList.add("%" + descricao.toLowerCase());
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna o comercialSolution em que a descrição é igual ao valor passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByDescription(String descricao) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" comercialSolution ");
			hql.append(" where ");
			hql.append(" lower(comercialSolution.descricao) = ? ");
			paramList.add("" + descricao.toLowerCase() + "");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os comercialSolutions em que a categoria é igual ao id passado como
	 * argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByIdCategory(int id) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" comercialSolution ");
			hql.append(" where ");
			hql.append(" comercialSolution.categoria.id = " + id + " ");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os comercialSolutions em que a avaliação esteja na faixa de valores
	 * passados como argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsByValueRange(float valorA, float valorB) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" comercialSolution ");
			hql.append(" where ");
			hql.append(" comercialSolution.valorA between ? and ?");
			paramList.add("valorA");
			paramList.add("valorB");
			hql.append(" and ");
			hql.append(" comercialSolution.valorB between ? and ?");
			paramList.add(valorA);
			paramList.add(valorB);
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os comercialSolutions já loteados
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listScheduledTrainingSolutions() {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" comercialSolution ");
			hql.append(" where comercialSolution.comercialSolutionItem.treinamento is not null ");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * Retorna todos os comercialSolutions não loteados
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List listNotScheduledTrainingSolutions() {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" comercialSolution ");
			hql.append(" where comercialSolution.comercialSolutionItem.treinamento is null ");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
/*	public List listTrainingSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin,
			ScheduleScheduledTrainingFilterType loteKind, int firstResult) {
		int maxResults = FilterResultSize.solutionsListMaxSize;
		try {
			List paramList = new ArrayList();
			
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			hql.append(" from TrainingSolution solution ");
			
			if (loteKind != ScheduleScheduledTrainingFilterType.Todos_Treinamentos) hql.append(" join solution.trainingFormationItems ip ");
			
			fields.append(" solution.id, solution.nome");
			
			if (loteKind != ScheduleScheduledTrainingFilterType.Todos_Treinamentos)
				fields.append(", ip.id, ip.order");
			
			fields.append(", solution.categoria.id, solution.categoria.nome ");
			
					
			
			if ((descricao != null || categoria != null || ((valorInic > 0 && valorFin > 0)))
					|| !(loteKind == ScheduleScheduledTrainingFilterType.Todos_Treinamentos))
				hql.append(" where ");

						
			else {
				
				String query = "select " + fields.toString() + hql.toString() + " order by solution.dataCriacao DESC";
				
				
				Session s = LocalServicesUtility.getInstance().openSession();
				
				ArrayList solutions = new ArrayList();
				List<Object[]> list=null;
				try{
					list= s.createQuery(query).setMaxResults(maxResults).setFirstResult(firstResult).list();
							
				
					if (list != null) for(Object[] objs: list){
						TrainingSolution p = new TrainingSolution();
						p.setId((Integer)objs[0]);
						p.setNome((String)objs[1]);
						
						if (loteKind != ScheduleScheduledTrainingFilterType.Todos_Treinamentos){
							TrainingFormationItem ip = new TrainingFormationItem();
							ip.setId((Integer) objs[2]);
							ip.setOrder((Integer) objs[3]);
							
							p.getTrainingFormationItens().add(ip);
							ip.setTrainingSolution(p);
							
							Categoria c = new Categoria();
							c.setId((Integer)objs[5]);
							c.setNome((String)objs[6]);
							
							p.setCategoria(c);											
							
						}else{
							
								Categoria c = new Categoria();
								c.setId((Integer)objs[2]);
								c.setNome((String)objs[3]);
								
								p.setCategoria(c);	
								
								Integer ipid = null;
								TrainingFormationItem ip = null;
								
								try {
									Object[] ips = (Object[]) s.createQuery("select ip.id, ip.order from TrainingFormationItem ip where ip.trainingSolution.id="+p.getId()).uniqueResult();
									ipid = (Integer) ips[0];
									ip= new TrainingFormationItem();
									ip.setId(ipid);
									ip.setOrder((Integer)ips[1]);
								} catch (Exception e) {
									//e.printStackTrace();
								}
															
						}
						solutions.add(p);
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					if (s != null) s.close();
				}
				
				return solutions;
			}

			
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
	*/
	public List listTrainingSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin,
			ScheduleScheduledTrainingFilterType loteKind, int firstResult) {
		int maxResults = FilterResultSize.solutionsListMaxSize;
		try {
			List paramList = new ArrayList();
			
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			hql.append(" from TrainingSolution solution ");
			
			if (loteKind != ScheduleScheduledTrainingFilterType.Todos_Treinamentos) hql.append(" join solution.comercialSolutionItems ip ");
			
			fields.append(" solution.id, solution.nome");
			
			if (loteKind != ScheduleScheduledTrainingFilterType.Todos_Treinamentos)
				fields.append(", ip.id, ip.qtd, ip.valorTotal");
			
			fields.append(", solution.categoria.id, solution.categoria.nome ");
			
					
			
			if ((descricao != null || categoria != null || ((valorInic > 0 && valorFin > 0)))
					|| !(loteKind == ScheduleScheduledTrainingFilterType.Todos_Treinamentos))
				hql.append(" where ");

						
			else {
				
				String query = "select " + fields.toString() + hql.toString() + " order by solution.dataCriacao DESC";
				
				
				Session s = LocalServicesUtility.getInstance().openSession();
				
				ArrayList solutions = new ArrayList();
				List<Object[]> list=null;
				try{
					list= s.createQuery(query).setMaxResults(maxResults).setFirstResult(firstResult).list();
							
				
					if (list != null) for(Object[] objs: list){
						TrainingSolution p = new TrainingSolution();
						p.setId((Integer)objs[0]);
						p.setNome((String)objs[1]);
						
						if (loteKind != ScheduleScheduledTrainingFilterType.Todos_Treinamentos){
							ComercialSolutionItem ip = new ComercialSolutionItem();
							ip.setId((Integer) objs[2]);
							ip.setDesconto((Integer) objs[3]);
							ip.setValorAcertado((Float)objs[4]);
							
							p.getComercialSolutionItens().add(ip);
							
							
							Categoria c = new Categoria();
							c.setId((Integer)objs[5]);
							c.setNome((String)objs[6]);
							
							p.setCategoria(c);		
											
							
							if (objs.length > 7){
								Treinamento l = new Treinamento();
								l.setId((Integer) objs[7]);
								l.setCodigo((String) objs[8]);					
								ip.setTreinamento(l);
								
								TurmaTreinamento le = new TurmaTreinamento();
								le.setId((Integer) objs[9]);								
								le.setDataTreinamento((Date)objs[11]);
								l.setTurma(le);
							}else{
								try {
									String query2="select ip.treinamento.id, ip.treinamento.codigo"+
									", ip.treinamento.turma.id, ip.treinamento.turma.dataTreinamento" +
									" from "+comSolItemEntityName+" ip where ip.id="+ip.getId();
									Object[] objs2= (Object[]) s.createQuery(query2).uniqueResult();
									if(objs2 != null && objs2.length > 0) {								
										Treinamento l = new Treinamento();
										l.setId((Integer) objs2[0]);
										l.setCodigo((String) objs2[1]);					
										ip.setTreinamento(l);
										
										TurmaTreinamento le = new TurmaTreinamento();
										le.setId((Integer) objs2[2]);
										System.out.println(objs2[3]);
										le.setDataTreinamento((Date)objs2[3]);										
										l.setTurma(le);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								
							}
						}else{
							
								Categoria c = new Categoria();
								c.setId((Integer)objs[2]);
								c.setNome((String)objs[3]);
								
								p.setCategoria(c);	
								
								Integer ipid = null;
								ComercialSolutionItem ip = null;
								
								try {
									Object[] ips = (Object[]) s.createQuery("select ip.id, ip.qtd, ip.valorTotal from "+comSolItemEntityName+" ip where ip.solution.id="+p.getId()).uniqueResult();
									ipid = (Integer) ips[0];
									ip=new ComercialSolutionItem();
									ip.setId(ipid);
									ip.setDesconto((Integer)ips[1]);
									ip.setValorAcertado((Float) ips[2]);
								} catch (Exception e) {
									//e.printStackTrace();
								}
								if (ipid != null){
									String query2="select ip.treinamento.id, ip.treinamento.codigo"+
									", ip.treinamento.turma.id, ip.treinamento.turma.dataTreinamento" +
									" from "+comSolItemEntityName+" ip where ip.id="+ipid;
									Object[] objs2= (Object[]) s.createQuery(query2).uniqueResult();
									
									if(objs2 != null && objs2.length > 0) {								
										Treinamento l = new Treinamento();
										l.setId((Integer) objs2[0]);
										l.setCodigo((String) objs2[1]);					
										ip.setTreinamento(l);
										
										TurmaTreinamento le = new TurmaTreinamento();
										le.setId((Integer) objs2[2]);
										System.out.println(objs2[3]);
										le.setDataTreinamento((Date)objs2[3]);
										l.setTurma(le);
									}
									if (p.getComercialSolutionItens() == null) p.setComercialSolutionItens(new ArrayList());
									p.getComercialSolutionItens().add(ip);
								}
							
						}
						solutions.add(p);
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					if (s != null) s.close();
				}
				
				return solutions;
			}

			
			if (descricao != null) {
				if (descKind == StringQueryKind.LIKE
						|| descKind == StringQueryKind.BEGINS_WITH
						|| descKind == StringQueryKind.ENDS_WITH)
					hql.append(" upper(solution.descricao) like upper(?) ");
				else if (descKind == StringQueryKind.EQUALS)
					hql.append(" upper(solution.descricao) = upper(?) ");

				if (descKind == StringQueryKind.LIKE)
					paramList.add("%" + descricao + "%");
				else if (descKind == StringQueryKind.EQUALS)
					paramList.add(descricao);
				else if (descKind == StringQueryKind.BEGINS_WITH)
					paramList.add(descricao + "%");
				else if (descKind == StringQueryKind.ENDS_WITH)
					paramList.add("%" + descricao);				
			}
			if (descricao != null && categoria != null){
				hql.append(" and ");
				
			}

			if (categoria != null) {
				hql.append(" solution.categoria.id = ?");
				paramList.add(categoria.getId());
				
			}
			if ((descricao != null || categoria != null)
					&& (valorInic > 0 && valorFin > 0)){
				hql.append(" and ");

			}

			if ((valorInic > 0 && valorFin > 0)) {
				hql.append(" solution.avaliacao between ? ");
				paramList.add(valorInic);
				hql.append(" and ");
				hql.append(" ? ");
				paramList.add(valorFin);
			}

			if ((descricao != null || categoria != null || ((valorInic > 0 && valorFin > 0)))
					&& !(loteKind == ScheduleScheduledTrainingFilterType.Todos_Treinamentos)) {
				hql.append(" and ");
				
				if (loteKind == ScheduleScheduledTrainingFilterType.Agendados){
					hql.append(" ip.treinamento is not null ");
					fields.append(", ip.treinamento.id, ip.treinamento.codigo");
					fields.append(", ip.treinamento.turma.id," +
							" ip.treinamento.turma.dataTreinamento");
				}
				else{
					if (loteKind != ScheduleScheduledTrainingFilterType.Todos_Treinamentos)
						hql.append(" ip.treinamento is null ");
				}
			
			} else if (!(loteKind == ScheduleScheduledTrainingFilterType.Todos_Treinamentos)){
				if (loteKind == ScheduleScheduledTrainingFilterType.Agendados){
					
					fields.append(", ip.treinamento.id, ip.treinamento.codigo");
					fields.append(", ip.treinamento.turma.id, " +
							" ip.treinamento.turma.dataTreinamento");				
					hql.append(" ip.treinamento is not null ");
				}
				else if (loteKind != ScheduleScheduledTrainingFilterType.Todos_Treinamentos)
					hql.append(" ip.treinamento is null ");
			}
			
			hql.append("  order by solution.dataCriacao DESC");
			
			String query = "select " + fields.toString() + hql.toString();
	
			
			Session s = LocalServicesUtility.getInstance().openSession();
			
			ArrayList solutions = new ArrayList();
			List<Object[]> list=null;
			try{
				Query q = s.createQuery(query);
				if (paramList != null && paramList.size()>0){
					Object params[] = paramList.toArray();					
					for(int i=0; i < params.length; i++){
						Object obj = params[i];
						q.setParameter(i, obj);
					}
				}
				list = q.setMaxResults(maxResults).setFirstResult(firstResult).list();
			
			
				if (list != null) for(Object[] objs: list){
					TrainingSolution p = new TrainingSolution();
					p.setId((Integer)objs[0]);
					p.setNome((String)objs[1]);
					
					if (loteKind == null || loteKind == ScheduleScheduledTrainingFilterType.Todos_Treinamentos){
						Categoria c = new Categoria();
						c.setId((Integer)objs[2]);
						c.setNome((String)objs[3]);
						
						p.setCategoria(c);	
						
						Integer ipid = null;
						ComercialSolutionItem ip = null;
						
						try {
							Object[] ips = (Object[]) s.createQuery("select ip.id, ip.qtd, ip.valorTotal from "+comSolItemEntityName+" ip where ip.solution.id="+p.getId()).uniqueResult();
							ipid = (Integer) ips[0];
							ip=new ComercialSolutionItem();
							ip.setId(ipid);
							ip.setDesconto((Integer)ips[1]);
							ip.setValorAcertado((Float) ips[2]);
						} catch (Exception e) {
							//e.printStackTrace();
						}
						if (ipid != null){
							String query2="select ip.treinamento.id, ip.treinamento.codigo"+
							", ip.treinamento.turma.id, ip.treinamento.turma.dataTreinamento" +
							" from "+comSolItemEntityName+" ip where ip.id="+ipid;
							Object[] objs2= (Object[]) s.createQuery(query2).uniqueResult();
							
							if(objs2 != null && objs2.length > 0) {								
								Treinamento l = new Treinamento();
								l.setId((Integer) objs2[0]);
								l.setCodigo((String) objs2[1]);					
								ip.setTreinamento(l);
								
								TurmaTreinamento le = new TurmaTreinamento();
								le.setId((Integer) objs2[2]);
								System.out.println(objs2[3]);
								le.setDataTreinamento((Date)objs2[3]);
								l.setTurma(le);
							}
							if (p.getComercialSolutionItens() == null) p.setComercialSolutionItens(new ArrayList());
							p.getComercialSolutionItens().add(ip);
						}
					}
					else{
						ComercialSolutionItem ip = new ComercialSolutionItem();
						ip.setId((Integer) objs[2]);
						ip.setDesconto((Integer) objs[3]);
						ip.setValorAcertado((Float)objs[4]);
						
						p.getComercialSolutionItens().add(ip);
						
						
						Categoria c = new Categoria();
						c.setId((Integer)objs[5]);
						c.setNome((String)objs[6]);
						
						p.setCategoria(c);		
										
						
						if (objs.length > 7){
							Treinamento l = new Treinamento();
							l.setId((Integer) objs[7]);
							l.setCodigo((String) objs[8]);					
							ip.setTreinamento(l);
							
							TurmaTreinamento le = new TurmaTreinamento();
							le.setId((Integer) objs[9]);
							le.setDataTreinamento((Date)objs[10]);
							l.setTurma(le);
						}else{
							try {
								String query2="select ip.treinamento.id, ip.treinamento.codigo"+
								", ip.treinamentoe.turma.id, ip.treinamento.turma.dataTreinamento " +
								" from "+comSolItemEntityName+" ip where ip.id="+ip.getId();
								Object[] objs2= (Object[]) s.createQuery(query2).uniqueResult();
								if(objs2 != null && objs2.length > 0) {								
									Treinamento l = new Treinamento();
									l.setId((Integer) objs2[0]);
									l.setCodigo((String) objs2[1]);					
									ip.setTreinamento(l);
									
									TurmaTreinamento le = new TurmaTreinamento();
									le.setId((Integer) objs2[2]);
									System.out.println(objs2[3]);
									le.setDataTreinamento((Date)objs2[3]);
									l.setTurma(le);
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							
						}
					}
					solutions.add(p);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
			
			return solutions;
			
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List listCommercialSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin, int firstResult) {
		int maxResults = FilterResultSize.solutionsListMaxSize;
		try {
			List paramList = new ArrayList();
			
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			hql.append(" from "+entityName+" solution ");
			
			
			fields.append(" solution.id, solution.descricao ");
			
			
			
			fields.append(", solution.categoria.id, solution.categoria.nome," +
					" solution.nome, solution.keyWords, solution.resumo, solution.solutionType, solution.avaliacao, solution.publicar");
								
			
			if ((descricao != null || categoria != null || ((valorInic > 0 && valorFin > 0))))
				hql.append(" where ");

						
			else {
				
				String query = "select " + fields.toString() + hql.toString() + " order by solution.dataCriacao DESC";
				
				
				Session s = LocalServicesUtility.getInstance().openSession();
				
				ArrayList solutions = new ArrayList();
				List<Object[]> list=null;
				try{
					list= s.createQuery(query).setMaxResults(maxResults).setFirstResult(firstResult).list();
						
				
					if (list != null) for(Object[] objs: list){
						ComercialSolution p = new ComercialSolution();
						p.setId((Integer)objs[0]);
						p.setDescricao((String)objs[1]);
						
						Categoria c = new Categoria();
						c.setId((Integer)objs[2]);
						c.setNome((String)objs[3]);
						p.setCategoria(c);
						
						p.setNome((String)objs[4]);
						p.setKeyWords((String)objs[5]);
						p.setResumo((String) objs[6]);
						p.setSolutionType((CommercialSolutionType)objs[7]);
						p.setAvaliacao((Float)objs[8]);
						if (objs[9] != null) p.setPublicar((Boolean)objs[9]);
						solutions.add(p);
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					if (s != null) s.close();
				}
				
				return solutions;
			}

			
			if (descricao != null) {
				if (descKind == StringQueryKind.LIKE
						|| descKind == StringQueryKind.BEGINS_WITH
						|| descKind == StringQueryKind.ENDS_WITH)
					hql.append(" upper(solution.descricao) like upper(?) ");
				else if (descKind == StringQueryKind.EQUALS)
					hql.append(" upper(solution.descricao) = upper(?) ");

				if (descKind == StringQueryKind.LIKE)
					paramList.add("%" + descricao + "%");
				else if (descKind == StringQueryKind.EQUALS)
					paramList.add(descricao);
				else if (descKind == StringQueryKind.BEGINS_WITH)
					paramList.add(descricao + "%");
				else if (descKind == StringQueryKind.ENDS_WITH)
					paramList.add("%" + descricao);				
			}
			if (descricao != null && categoria != null){
				hql.append(" and ");
				
			}

			if (categoria != null) {
				hql.append(" solution.categoria.id = ?");
				paramList.add(categoria.getId());				
			}
			

			if ((valorInic > 0 && valorFin > 0)) {
				if (categoria != null) hql.append(" and ");
				hql.append(" solution.avaliacao between ? ");
				paramList.add(valorInic);
				hql.append(" and ");
				hql.append(" ? ");
				paramList.add(valorFin);
			}

						
			hql.append("  order by solution.dataCriacao DESC");
			
			String query = "select " + fields.toString() + hql.toString();
	
			
			Session s = LocalServicesUtility.getInstance().openSession();
			
			ArrayList solutions = new ArrayList();
			List<Object[]> list=null;
			try{
				Query q = s.createQuery(query);
				if (paramList != null && paramList.size()>0){
					Object params[] = paramList.toArray();					
					for(int i=0; i < params.length; i++){
						Object obj = params[i];
						q.setParameter(i, obj);
					}
				}
				list = q.setMaxResults(maxResults).setFirstResult(firstResult).list();
			
			
				if (list != null) for(Object[] objs: list){
					ComercialSolution p = new ComercialSolution();
					p.setId((Integer)objs[0]);
					p.setDescricao((String)objs[1]);
					
					Categoria c = new Categoria();
					c.setId((Integer)objs[2]);
					c.setNome((String)objs[3]);
					p.setCategoria(c);
					
					p.setNome((String)objs[4]);
					p.setKeyWords((String)objs[5]);
					p.setResumo((String) objs[6]);
					p.setSolutionType((CommercialSolutionType)objs[7]);
					p.setAvaliacao((Float)objs[8]);
					if (objs[9] != null) p.setPublicar((Boolean)objs[9]);
					solutions.add(p);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
			
			return solutions;
			
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List listSoftwareSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin, int firstResult) {
		int maxResults = FilterResultSize.solutionsListMaxSize;
		try {
			List paramList = new ArrayList();
			
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			hql.append(" from SoftwareSolution solution ");
			
			
			fields.append(" solution.id, solution.descricao ");
			
			
			
			fields.append(", solution.categoria.id, solution.categoria.nome," +
					" solution.nome, solution.keyWords, solution.resumo, solution.solutionType, solution.avaliacao, solution.publicar, solution.sigla");
								
			
			if ((descricao != null || categoria != null || ((valorInic > 0 && valorFin > 0))))
				hql.append(" where ");

						
			else {
				
				String query = "select " + fields.toString() + hql.toString() + " order by solution.dataCriacao DESC";
				
				
				Session s = LocalServicesUtility.getInstance().openSession();
				
				ArrayList solutions = new ArrayList();
				List<Object[]> list=null;
				try{
					list= s.createQuery(query).setMaxResults(maxResults).setFirstResult(firstResult).list();
						
				
					if (list != null) for(Object[] objs: list){
						SoftwareSolution p = new SoftwareSolution();
						p.setId((Integer)objs[0]);
						p.setDescricao((String)objs[1]);
						
						Categoria c = new Categoria();
						c.setId((Integer)objs[2]);
						c.setNome((String)objs[3]);
						p.setCategoria(c);
						
						p.setNome((String)objs[4]);
						p.setKeyWords((String)objs[5]);
						p.setResumo((String) objs[6]);
						p.setSolutionType((CommercialSolutionType)objs[7]);
						p.setAvaliacao((Float)objs[8]);
						if (objs[9] != null) p.setPublicar((Boolean)objs[9]);
						if(objs[10] != null)
							p.setSigla((String) objs[10]);
						solutions.add(p);
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					if (s != null) s.close();
				}
				
				return solutions;
			}

			
			if (descricao != null) {
				if (descKind == StringQueryKind.LIKE
						|| descKind == StringQueryKind.BEGINS_WITH
						|| descKind == StringQueryKind.ENDS_WITH)
					hql.append(" upper(solution.descricao) like upper(?) ");
				else if (descKind == StringQueryKind.EQUALS)
					hql.append(" upper(solution.descricao) = upper(?) ");

				if (descKind == StringQueryKind.LIKE)
					paramList.add("%" + descricao + "%");
				else if (descKind == StringQueryKind.EQUALS)
					paramList.add(descricao);
				else if (descKind == StringQueryKind.BEGINS_WITH)
					paramList.add(descricao + "%");
				else if (descKind == StringQueryKind.ENDS_WITH)
					paramList.add("%" + descricao);				
			}
			if (descricao != null && categoria != null){
				hql.append(" and ");
				
			}

			if (categoria != null) {
				hql.append(" solution.categoria.id = ?");
				paramList.add(categoria.getId());				
			}
			

			if ((valorInic > 0 && valorFin > 0)) {
				if (categoria != null) hql.append(" and ");
				hql.append(" solution.avaliacao between ? ");
				paramList.add(valorInic);
				hql.append(" and ");
				hql.append(" ? ");
				paramList.add(valorFin);
			}

						
			hql.append("  order by solution.dataCriacao DESC");
			
			String query = "select " + fields.toString() + hql.toString();
	
			
			Session s = LocalServicesUtility.getInstance().openSession();
			
			ArrayList solutions = new ArrayList();
			List<Object[]> list=null;
			try{
				Query q = s.createQuery(query);
				if (paramList != null && paramList.size()>0){
					Object params[] = paramList.toArray();					
					for(int i=0; i < params.length; i++){
						Object obj = params[i];
						q.setParameter(i, obj);
					}
				}
				list = q.setMaxResults(maxResults).setFirstResult(firstResult).list();
			
			
				if (list != null) for(Object[] objs: list){
					SoftwareSolution p = new SoftwareSolution();
					p.setId((Integer)objs[0]);
					p.setDescricao((String)objs[1]);
					
					Categoria c = new Categoria();
					c.setId((Integer)objs[2]);
					c.setNome((String)objs[3]);
					p.setCategoria(c);
					
					p.setNome((String)objs[4]);
					p.setKeyWords((String)objs[5]);
					p.setResumo((String) objs[6]);
					p.setSolutionType((CommercialSolutionType)objs[7]);
					p.setAvaliacao((Float)objs[8]);
					if (objs[9] != null) p.setPublicar((Boolean)objs[9]);
					if(objs[10] != null)
						p.setSigla((String) objs[10]);
					solutions.add(p);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
			
			return solutions;
			
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer countTrainingSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin,
			ScheduleScheduledTrainingFilterType loteKind) {
		try {
			List paramList = new ArrayList();
			
			StringBuffer hql = new StringBuffer();
			hql.append(" select count(solution) from TrainingSolution solution ");
			
			if (loteKind != ScheduleScheduledTrainingFilterType.Todos_Treinamentos) hql.append(" join solution.comercialSolutionItems ip ");
						
			if ((descricao != null || categoria != null || ((valorInic > 0 && valorFin > 0)))
					|| !(loteKind == ScheduleScheduledTrainingFilterType.Todos_Treinamentos)){
				hql.append(" where ");
			}else {				
				String query = hql.toString();			
				Session s = LocalServicesUtility.getInstance().openSession();				
				try{
					return (Integer) s.createQuery(query).uniqueResult();					
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					if (s != null) s.close();
				}				
			}
			
			if (descricao != null) {
				if (descKind == StringQueryKind.LIKE
						|| descKind == StringQueryKind.BEGINS_WITH
						|| descKind == StringQueryKind.ENDS_WITH)
					hql.append(" upper(solution.descricao) like upper(?) ");
				else if (descKind == StringQueryKind.EQUALS)
					hql.append(" upper(solution.descricao) = upper(?) ");

				if (descKind == StringQueryKind.LIKE)
					paramList.add("%" + descricao + "%");
				else if (descKind == StringQueryKind.EQUALS)
					paramList.add(descricao);
				else if (descKind == StringQueryKind.BEGINS_WITH)
					paramList.add(descricao + "%");
				else if (descKind == StringQueryKind.ENDS_WITH)
					paramList.add("%" + descricao);				
			}
			if (descricao != null && categoria != null){
				hql.append(" and ");				
			}

			if (categoria != null) {
				hql.append(" solution.categoria.id = ?");
				paramList.add(categoria.getId());
				
			}
			if ((descricao != null || categoria != null)
					&& (valorInic > 0 && valorFin > 0)){
				hql.append(" and ");

			}

			if ((valorInic > 0 && valorFin > 0)) {
				hql.append(" solution.avaliacao between ? ");
				paramList.add(valorInic);
				hql.append(" and ");
				hql.append(" ? ");
				paramList.add(valorFin);
			}

			if ((descricao != null || categoria != null || ((valorInic > 0 && valorFin > 0)))
					&& !(loteKind == ScheduleScheduledTrainingFilterType.Todos_Treinamentos)) {
				hql.append(" and ");
				
				if (loteKind == ScheduleScheduledTrainingFilterType.Agendados){
					hql.append(" ip.treinamento is not null ");				
				}
				else{
					if (loteKind != ScheduleScheduledTrainingFilterType.Todos_Treinamentos)
						hql.append(" ip.treinamento is null ");
				}
			
			} else if (!(loteKind == ScheduleScheduledTrainingFilterType.Todos_Treinamentos)){
				if (loteKind == ScheduleScheduledTrainingFilterType.Agendados){
					hql.append(" ip.treinamento is not null ");
				}
				else if (loteKind != ScheduleScheduledTrainingFilterType.Todos_Treinamentos)
					hql.append(" ip.treinamento is null ");
			}
						
			Session s = LocalServicesUtility.getInstance().openSession();			
			try{				
				Query q = s.createQuery(hql.toString());
				if (paramList != null && paramList.size()>0){
					Object params[] = paramList.toArray();					
					for(int i=0; i < params.length; i++){
						Object obj = params[i];
						q.setParameter(i, obj);
					}
				}
				return (Integer) q.uniqueResult();				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}			
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer countCommercialSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin) {
		try {
			List paramList = new ArrayList();
			
			StringBuffer hql = new StringBuffer();
			hql.append(" select count(solution) from "+entityName+" solution ");
			
						
			if ((descricao != null || categoria != null || ((valorInic > 0 && valorFin > 0)))){
				hql.append(" where ");
			}else {				
				String query = hql.toString();			
				Session s = LocalServicesUtility.getInstance().openSession();				
				try{
					return (Integer) s.createQuery(query).uniqueResult();					
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					if (s != null) s.close();
				}				
			}
			
			if (descricao != null) {
				if (descKind == StringQueryKind.LIKE
						|| descKind == StringQueryKind.BEGINS_WITH
						|| descKind == StringQueryKind.ENDS_WITH)
					hql.append(" upper(solution.descricao) like upper(?) ");
				else if (descKind == StringQueryKind.EQUALS)
					hql.append(" upper(solution.descricao) = upper(?) ");

				if (descKind == StringQueryKind.LIKE)
					paramList.add("%" + descricao + "%");
				else if (descKind == StringQueryKind.EQUALS)
					paramList.add(descricao);
				else if (descKind == StringQueryKind.BEGINS_WITH)
					paramList.add(descricao + "%");
				else if (descKind == StringQueryKind.ENDS_WITH)
					paramList.add("%" + descricao);				
			}
			if (descricao != null && categoria != null){
				hql.append(" and ");
				
			}

			if (categoria != null) {
				hql.append(" solution.categoria.id = ?");
				paramList.add(categoria.getId());				
			}
			

			if ((valorInic > 0 && valorFin > 0)) {
				if (categoria != null) hql.append(" and ");
				hql.append(" solution.avaliacao between ? ");
				paramList.add(valorInic);
				hql.append(" and ");
				hql.append(" ? ");
				paramList.add(valorFin);
			}

			
						
			Session s = LocalServicesUtility.getInstance().openSession();			
			try{				
				Query q = s.createQuery(hql.toString());
				if (paramList != null && paramList.size()>0){
					Object params[] = paramList.toArray();					
					for(int i=0; i < params.length; i++){
						Object obj = params[i];
						q.setParameter(i, obj);
					}
				}
				return (Integer) q.uniqueResult();				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}			
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer countSoftwareSolutionsAccordingTo(String descricao, StringQueryKind descKind,
			Categoria categoria, float valorInic, float valorFin) {
		try {
			List paramList = new ArrayList();
			
			StringBuffer hql = new StringBuffer();
			hql.append(" select count(solution) from SoftwareSolution solution ");
			
						
			if ((descricao != null || categoria != null || ((valorInic > 0 && valorFin > 0)))){
				hql.append(" where ");
			}else {				
				String query = hql.toString();			
				Session s = LocalServicesUtility.getInstance().openSession();				
				try{
					return (Integer) s.createQuery(query).uniqueResult();					
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					if (s != null) s.close();
				}				
			}
			
			if (descricao != null) {
				if (descKind == StringQueryKind.LIKE
						|| descKind == StringQueryKind.BEGINS_WITH
						|| descKind == StringQueryKind.ENDS_WITH)
					hql.append(" upper(solution.descricao) like upper(?) ");
				else if (descKind == StringQueryKind.EQUALS)
					hql.append(" upper(solution.descricao) = upper(?) ");

				if (descKind == StringQueryKind.LIKE)
					paramList.add("%" + descricao + "%");
				else if (descKind == StringQueryKind.EQUALS)
					paramList.add(descricao);
				else if (descKind == StringQueryKind.BEGINS_WITH)
					paramList.add(descricao + "%");
				else if (descKind == StringQueryKind.ENDS_WITH)
					paramList.add("%" + descricao);				
			}
			if (descricao != null && categoria != null){
				hql.append(" and ");
				
			}

			if (categoria != null) {
				hql.append(" solution.categoria.id = ?");
				paramList.add(categoria.getId());				
			}
			

			if ((valorInic > 0 && valorFin > 0)) {
				if (categoria != null) hql.append(" and ");
				hql.append(" solution.avaliacao between ? ");
				paramList.add(valorInic);
				hql.append(" and ");
				hql.append(" ? ");
				paramList.add(valorFin);
			}

			
						
			Session s = LocalServicesUtility.getInstance().openSession();			
			try{				
				Query q = s.createQuery(hql.toString());
				if (paramList != null && paramList.size()>0){
					Object params[] = paramList.toArray();					
					for(int i=0; i < params.length; i++){
						Object obj = params[i];
						q.setParameter(i, obj);
					}
				}
				return (Integer) q.uniqueResult();				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}			
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List listAll() {
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			return template.loadAll(ComercialSolution.class);

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@Override
	public ComercialSolution saveOrUpdate(ComercialSolution solution) throws Exception {
		/*try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			template.saveOrUpdate(solution);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("ComercialSolutionServiceDAOHibernate_saveOrUpdateError");
		}*/
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			ComercialSolution ret;
			try {
				s.beginTransaction();
				if(solution.getId()>0){
					System.out.println("Salvando alterações em " + solution.getId() + " " + solution.getClass().getSimpleName());
					ComercialSolution cm = (ComercialSolution)
					s.createQuery("select cm from "+solution.getClass().getSimpleName()+" cm where cm.id="+solution.getId()).uniqueResult();
					
					if(cm.getCategoria() != null)
						cm.getCategoria().getId();
					if(cm.getCssDefinition() != null)
						cm.getCssDefinition().getId();
					if(cm.getImagens() != null && cm.getImagens().size()>0){
						
					}
					if(cm.getArquivos() != null && cm.getArquivos().size()>0){
						
					}
					System.out.println(solution.getResumo());
					cm.copyProperties(solution);
					System.out.println(cm.getResumo());
					s.saveOrUpdate(cm);
					ret= cm;
				}else{
					s.save(solution);
					ret = solution;
				}
				s.getTransaction().commit();
				
				return ret;
			} catch (Exception e) {
				if (s.getTransaction() != null) s.getTransaction().rollback();
				e.printStackTrace();
				throw e;
			}finally{
				s.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * Remove o solution que contém o identificador passado como argumento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ComercialSolution deleteById(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" solution ");
			hql.append(" where ");
			hql.append(" solution.id = " + id + " ");
			ComercialSolution solution = (ComercialSolution) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(solution);
			return solution;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("ComercialSolutionServiceDAOHibernate_deleteByIdError");
		}
	}

	/**
	 * Remove todos os solutions que pertençam a um lote
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ComercialSolution deleteByTrainingSolutionItemId(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" solution ");
			hql.append(" where ");
			hql.append(" solution.comercialSolutionItem.id = " + id + " ");
			ComercialSolution solution = (ComercialSolution) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(solution);

			return solution;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"ComercialSolutionServiceDAOHibernate_deleteByIdLoteError");
		}
	}

	/**
	 * Remove todos os solutions de uma determinada categoria
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ComercialSolution deleteByIdCategoria(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" solution ");
			hql.append(" where ");
			hql.append(" solution.categoria.id = " + id + " ");
			ComercialSolution solution = (ComercialSolution) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(solution);
			return solution;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"ComercialSolutionServiceDAOHibernate_deleteByIdCategoriaError");
		}
	}

	/**
	 * Retorna um solution pelo identificador
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ComercialSolution getCommercialSolutionById(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" solution ");
			hql.append(" where ");
			hql.append(" solution.id = " + id + " ");
			return (ComercialSolution) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"ComercialSolutionServiceDAOHibernate_getComercialSolutionByIdError");
		}
	}

	/**
	 * Retorna um solution pela descrição
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ComercialSolution getCommercialSolutionByName(String descricao) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from "+entityName+" solution ");
			hql.append(" where ");
			hql.append(" solution.descricao = ? ");
			paramList.add("" + descricao + "");
			return (ComercialSolution) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"ComercialSolutionServiceDAOHibernate_getComercialSolutionByDescricaoError");
		}
	}
	
	/**
	 * Retorna um solution e também as propriedades em cascata
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ComercialSolution getCommercialSolutionByIdCascadingProperties(java.io.Serializable id, boolean images, boolean categoria, boolean comercialSolutionItem) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			ComercialSolution solution = null;			
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			hql.append(" from "+entityName+" solution where solution.id="+id);
			
			fields.append(" solution.id, solution.descricao");			
			if (categoria) fields.append(", solution.categoria.id, solution.categoria.nome," +
					" solution.avaliacao, solution.dataCriacao," +
					" solution.utilizarFormatador," +
					" solution.resumo, solution.nome," +
					" solution.keyWords, solution.solutionType");
			else fields.append(" , solution.dataCriacao," +
					" solution.utilizarFormatador," +
					" solution.resumo, solution.nome," +
					" solution.keyWords, solution.solutionType");
			
			String query = "select " + fields.toString() + hql.toString();
			System.out.println("EXECUTING QUERY " + query);			
			
			Object[] objs=null;
			try{
				objs= (Object[]) s.createQuery(query).uniqueResult();
				System.out.println("EXECUTED QUERY " + query);
			
			
				if (objs != null && objs.length > 0){
					ComercialSolution p = new ComercialSolution();
					p.setId((Integer)objs[0]);					
					p.setDescricao((String)objs[1]);
					
					
					if (categoria){
						Categoria c = new Categoria();
						c.setId((Integer)objs[2]);
						c.setNome((String)objs[3]);
						
						p.setCategoria(c);
						
						p.setAvaliacao((Float)objs[4]);
						p.setDataCriacao((Date)objs[5]);
						if (objs[6] != null) p.setUtilizarFormatador((Boolean) objs[6]);
						if (objs[7] != null) p.setResumo((String) objs[7]);
						if (objs[8] != null) p.setNome((String)objs[8]);
						if (objs[9] != null) p.setKeyWords((String) objs[9]);
						if (objs[10] != null) p.setSolutionType((CommercialSolutionType)objs[10]);
						
					}else{
						p.setAvaliacao((Float)objs[2]);
						p.setDataCriacao((Date)objs[3]);
						p.setUtilizarFormatador((Boolean) objs[4]);
						if (objs[5] != null) p.setResumo((String) objs[5]);
						if (objs[6] != null) p.setNome((String)objs[6]);
						if (objs[7] != null) p.setKeyWords((String) objs[7]);
						if (objs[8] != null) p.setSolutionType((CommercialSolutionType)objs[8]);
					}					
					
					if(images){
						try {
							System.out.println("buscando imagens");
							String query3="select prod.imagens from "+entityName+" prod where prod.id="+id;
							List<Imagem> imgs = (List) s.createQuery(query3).list();
							p.setImagens(imgs);
							System.out.println("Imagens retornadas " + imgs.size());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}					
					
					return p;
					
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
			
			
			return solution;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(""+entityName+" Cascading Load Exception");
		}
	}
	
	
	/**
	 * Retorna um solution e também as propriedades em cascata
	 */
	@SuppressWarnings("unchecked")
	@Override
	public TrainingSolution getTrainingSolutionByIdCascadingProperties(java.io.Serializable id, boolean images, boolean categoria, boolean instr) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			TrainingSolution solution = null;			
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			hql.append(" from TrainingSolution solution where solution.id="+id);
			
			fields.append(" solution.id, solution.descricao");			
			if (categoria) fields.append(", solution.categoria.id, solution.categoria.nome," +
					" solution.avaliacao, solution.dataCriacao," +
					" solution.utilizarFormatador," +
					" solution.resumo, solution.nome," +
					" solution.keyWords, solution.solutionType," +
					" solution.codigo, solution.conteudoProgramatico," +
					" solution.cargaHoraria, solution.tecnologias," +
					" solution.materialApoio, solution.softwares, " +
					" solution.tipoTreinamento, solution.objetivos");
			else fields.append(" , solution.dataCriacao," +
					" solution.utilizarFormatador," +
					" solution.resumo, solution.nome," +
					" solution.keyWords, solution.solutionType," +
					" solution.codigo, solution.conteudoProgramatico," +
					" solution.cargaHoraria, solution.tecnologias," +
					" solution.materialApoio, solution.softwares, " +
					" solution.tipoTreinamento, solution.objetivos");
			
			
			String query = "select " + fields.toString() + hql.toString();
			System.out.println("EXECUTING QUERY " + query);			
			
			Object[] objs=null;
			try{
				objs= (Object[]) s.createQuery(query).uniqueResult();
				System.out.println("EXECUTED QUERY " + query);
			
			
				if (objs != null && objs.length > 0){
					TrainingSolution p = new TrainingSolution();
					p.setId((Integer)objs[0]);					
					p.setDescricao((String)objs[1]);
					
					
					if (categoria){
						Categoria c = new Categoria();
						c.setId((Integer)objs[2]);
						c.setNome((String)objs[3]);
						
						p.setCategoria(c);
						
						p.setAvaliacao((Float)objs[4]);
						p.setDataCriacao((Date)objs[5]);
						if (objs[6] != null) p.setUtilizarFormatador((Boolean) objs[6]);
						if (objs[7] != null) p.setResumo((String) objs[7]);
						if (objs[8] != null) p.setNome((String)objs[8]);
						if (objs[9] != null) p.setKeyWords((String) objs[9]);
						if (objs[10] != null) p.setSolutionType((CommercialSolutionType)objs[10]);
						
						if (objs[11] != null) p.setCodigo((String)objs[11]);
						if (objs[12] != null) p.setConteudoProgramatico((String)objs[12]);
						if (objs[13] != null) p.setCargaHoraria((Integer)objs[13]);
						if (objs[14] != null) p.setTecnologias((String)objs[14]);
						if (objs[15] != null) p.setMaterialApoio((String)objs[15]);
						if (objs[16] != null) p.setSoftwares((String)objs[16]);
						if (objs[17] != null) p.setTipoTreinamento((TrainingSolutionType)objs[17]);
						if (objs[18] != null) p.setObjetivos((String)objs[18]);
						
					}else{
						p.setAvaliacao((Float)objs[2]);
						p.setDataCriacao((Date)objs[3]);
						p.setUtilizarFormatador((Boolean) objs[4]);
						if (objs[5] != null) p.setResumo((String) objs[5]);
						if (objs[6] != null) p.setNome((String)objs[6]);
						if (objs[7] != null) p.setKeyWords((String) objs[7]);
						if (objs[8] != null) p.setSolutionType((CommercialSolutionType)objs[8]);
						
						if (objs[9] != null) p.setCodigo((String)objs[9]);
						if (objs[10] != null) p.setConteudoProgramatico((String)objs[10]);
						if (objs[11] != null) p.setCargaHoraria((Integer)objs[11]);
						if (objs[12] != null) p.setTecnologias((String)objs[12]);
						if (objs[13] != null) p.setMaterialApoio((String)objs[13]);
						if (objs[14] != null) p.setSoftwares((String)objs[14]);
						if (objs[15] != null) p.setTipoTreinamento((TrainingSolutionType)objs[15]);
						if (objs[16] != null) p.setObjetivos((String)objs[16]);
					}					
					
					if(images){
						try {
							String query3="select prod.imagens from "+entityName+" prod where prod.id="+id;
							List<Imagem> imgs = (List) s.createQuery(query3).list();
							p.setImagens(imgs);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}					
					
					if(instr){
						try {
							String query3="select solution.autor.id, solution.autor.nome from TrainingSolution solution where solution.id="+id;
							Object[] autor= (Object[]) s.createQuery(query3).uniqueResult();
							if (autor != null && autor.length > 0){
								Instrutor i = new Instrutor();
								i.setId((Long)autor[0]);
								i.setNome((String)autor[1]);
								p.setAutor(i);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					return p;
					
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
			
			
			return solution;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(""+entityName+" Cascading Load Exception");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public SoftwareSolution getSoftwareSolutionByIdCascadingProperties(java.io.Serializable id, boolean images, boolean categoria) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			SoftwareSolution solution = null;			
			StringBuffer fields = new StringBuffer();
			StringBuffer hql = new StringBuffer();
			hql.append(" from SoftwareSolution solution where solution.id="+id);
			
			fields.append(" solution.id, solution.descricao");			
			if (categoria) fields.append(", solution.categoria.id, solution.categoria.nome," +
					" solution.avaliacao, solution.dataCriacao," +
					" solution.utilizarFormatador," +
					" solution.resumo, solution.nome," +
					" solution.keyWords, solution.solutionType," +
					
					" solution.sigla, solution.funcionalidades," +
					" solution.licencaUso, solution.plataforma," +
					" solution.urlProjeto, solution.versao, " +
					" solution.tipoSoftware, solution.sistemasOperacionais, solution.publicar");			
			else fields.append(" , solution.dataCriacao," +
					" solution.utilizarFormatador," +
					" solution.resumo, solution.nome," +
					" solution.keyWords, solution.solutionType," +
					
					" solution.sigla, solution.funcionalidades," +
					" solution.licencaUso, solution.plataforma," +
					" solution.urlProjeto, solution.versao, " +
					" solution.tipoSoftware, solution.sistemasOperacionais, solution.publicar");
			
			
			
			String query = "select " + fields.toString() + hql.toString();
			System.out.println("EXECUTING QUERY " + query);			
			
			Object[] objs=null;
			try{
				objs= (Object[]) s.createQuery(query).uniqueResult();
				System.out.println("EXECUTED QUERY " + query);
			
			
				if (objs != null && objs.length > 0){
					SoftwareSolution p = new SoftwareSolution();
					p.setId((Integer)objs[0]);					
					p.setDescricao((String)objs[1]);
					
					
					if (categoria){
						Categoria c = new Categoria();
						c.setId((Integer)objs[2]);
						c.setNome((String)objs[3]);
						
						p.setCategoria(c);
						
						p.setAvaliacao((Float)objs[4]);
						p.setDataCriacao((Date)objs[5]);
						if (objs[6] != null) p.setUtilizarFormatador((Boolean) objs[6]);
						if (objs[7] != null) p.setResumo((String) objs[7]);
						if (objs[8] != null) p.setNome((String)objs[8]);
						if (objs[9] != null) p.setKeyWords((String) objs[9]);
						if (objs[10] != null) p.setSolutionType((CommercialSolutionType)objs[10]);
											
						if (objs[11] != null) p.setSigla((String)objs[11]);
						if (objs[12] != null) p.setFuncionalidades((String)objs[12]);
						if (objs[13] != null) p.setLicencaUso((String)objs[13]);
						if (objs[14] != null) p.setPlataforma((String)objs[14]);
						if (objs[15] != null) p.setUrlProjeto((String)objs[15]);
						if (objs[16] != null) p.setVersao((String)objs[16]);
						if (objs[17] != null) p.setTipoSoftware((SoftwareSolutionType)objs[17]);
						if (objs[18] != null) p.setSistemasOperacionais((String)objs[18]);
						if (objs[19] != null) p.setPublicar((Boolean)objs[19]);
						
					}else{
						p.setAvaliacao((Float)objs[2]);
						p.setDataCriacao((Date)objs[3]);
						p.setUtilizarFormatador((Boolean) objs[4]);
						if (objs[5] != null) p.setResumo((String) objs[5]);
						if (objs[6] != null) p.setNome((String)objs[6]);
						if (objs[7] != null) p.setKeyWords((String) objs[7]);
						if (objs[8] != null) p.setSolutionType((CommercialSolutionType)objs[8]);
						
						if (objs[9] != null) p.setSigla((String)objs[9]);
						if (objs[10] != null) p.setFuncionalidades((String)objs[10]);
						if (objs[11] != null) p.setLicencaUso((String)objs[11]);
						if (objs[12] != null) p.setPlataforma((String)objs[12]);
						if (objs[13] != null) p.setUrlProjeto((String)objs[13]);
						if (objs[14] != null) p.setVersao((String)objs[14]);
						if (objs[15] != null) p.setTipoSoftware((SoftwareSolutionType)objs[15]);
						if (objs[16] != null) p.setSistemasOperacionais((String)objs[16]);
						if (objs[17] != null) p.setPublicar((Boolean)objs[17]);
					}					
					
					if(images){
						try {
							String query3="select prod.imagens from "+entityName+" prod where prod.id="+id;
							List<Imagem> imgs = (List) s.createQuery(query3).list();
							p.setImagens(imgs);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}				
					
					
					return p;
					
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
			
			
			return solution;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(""+entityName+" Cascading Load Exception");
		}
	}

	
	/**
	 * Retorna um solution e também as propriedades em cascata
	 */
	@Override
	public TrainingSolution mergeCascadingProperties(TrainingSolution solution, boolean images, boolean categoria, boolean comercialSolutionItem) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			try {
				s.beginTransaction();
				s.merge(solution);
				if (images) s.merge(solution.getImagens());
				if (categoria) s.merge(solution.getCategoria());
				if (comercialSolutionItem) s.merge(solution.getTrainingFormationItens());
				s.getTransaction().commit();
				
			} catch (HibernateException e) {
				if (s.getTransaction() != null) s.getTransaction().rollback();				
				throw e;
			}finally{
				s.close();
			}
			return solution;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(""+entityName+" Cascading Merge Exception");
		}
	}
	
	/**
	 * Retorna um solution e também as propriedades em cascata
	 */
	@Override
	public ComercialSolution mergeCascadingProperties(ComercialSolution solution, boolean images, boolean categoria) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			try {
				s.beginTransaction();
				s.merge(solution);
				if (images) s.merge(solution.getImagens());
				if (categoria) s.merge(solution.getCategoria());
				s.getTransaction().commit();
				
			} catch (HibernateException e) {
				if (s.getTransaction() != null) s.getTransaction().rollback();				
				throw e;
			}finally{
				s.close();
			}
			return solution;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(""+entityName+" Cascading Merge Exception");
		}
	}
	
	@Override
	public Imagem removeImageFromCommercialSolution(int idProd, int idImg) throws Exception{
		Session s = null;
		try {
			Imagem imgRemove=null;
			s=LocalServicesUtility.getInstance().openSession();
			ComercialSolution p = (ComercialSolution) s.get(ComercialSolution.class,idProd);
			Iterator<Imagem> it = p.getImagens().iterator();
			while(it.hasNext()){
				Imagem im = it.next();
				if (im.getId() == idImg) {
					imgRemove = im;
					break;
				}
			}
			if (imgRemove != null){
				p.getImagens().remove(imgRemove);
				s.beginTransaction();
				s.merge(p);
				s.getTransaction().commit();
				return imgRemove;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			if (s != null) s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null) s.close();
		}
		return null;
	}
	
	@Override
	public ComercialSolution loadCommercialSolutionEagerImages(ComercialSolution solution) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			s.load(solution,solution.getId());
			solution.getImagens().iterator().hasNext();	
			return solution;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			s.close();
		}
	}
	
	@Override
	public ComercialSolution attachImageIntoCommercialSolution(ComercialSolution solution, List<Imagem> selectedImgs) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.load(solution,solution.getId());
			
			s.beginTransaction();
			for(Imagem im : selectedImgs){
				solution.getImagens().add(im);
			}
			s.flush();
			s.getTransaction().commit();
			return solution;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@Override
	public ComercialSolutionItem removeTrainingSolutionItemFromScheduledTrainig(ComercialSolutionItem ip) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			
			ip = (ComercialSolutionItem) s.load(ComercialSolutionItem.class,ip.getId());			
			 
			s.beginTransaction();							
			ip.setTreinamento(null);							
			s.update(ip);
			s.delete(ip);
			
			s.getTransaction().commit();
			return ip;
		}catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}
		finally{
			s.close();
		}
	}
	
	
	
	@Override
	@Deprecated
	public ComercialSolutionHistory generateTrainingSolutionHistory(TrainingSolution solution, Treinamento lote, ComercialSolutionItem ip, boolean apagarComercialSolution) throws Exception{
		return null;
		/*Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();	
			s.beginTransaction();
			
			ComercialSolutionHistory hi = new ComercialSolutionHistory();
			hi.setAlteracao(new Date());
			hi.setDescicao(solution.getDescricao());
			hi.setTurma(lote.getTurma());
			hi.setTreinamento(lote);
			hi.setComercialSolution(solution);
			hi.setQtdItem(ip.getDesconto());
			
			s.save(hi);			
			
			if (apagarComercialSolution){
				Iterator<Imagem> it =ip.getComercialSolution().getImagens().iterator();
				TrainingSolution prod =ip.getComercialSolution(); 
				while(it.hasNext()){
					Imagem im = it.next();
					im.setComercialSolution(null);
					s.delete(im);			
				}
				prod.setTrainingFormationItens(null);
				ip.setComercialSolution(null);
				s.delete(prod);
				s.delete(ip);
			}
			s.getTransaction().commit();
			
			return hi;
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
			throw e1;
		}finally{
			s.close();
		}*/
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void deleteCommercialSolutionById(int id, ComercialSolution p) throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			s.beginTransaction();
			if (p instanceof TrainingSolution){
				if (((TrainingSolution)p).getTrainingFormationItens() != null){
					s.createQuery("delete from "+comSolItemEntityName+" ip where ip.comercialSolution.id="+id).executeUpdate();											
				}
			}
			s.createQuery("delete from ComercialSolutionHistory h where h.comercialSolution.id="+id).executeUpdate();
			
			//try {
				List<Imagem> imgs = s.createQuery("select prod.imagens from "+entityName+" prod where prod.id="+id).list();
				for(Imagem im : imgs){
					im.setComercialSolution(null);												
					//ImageListForm.removerReferenciaImagem(im);
					LocalImagemService.getInstance().removerReferenciaImagemComercialSolution(im.getId());
				}
			/*} catch (Exception e) {
				throw e;
			}*/
			
			
			s.createQuery("delete from "+entityName+" p where p.id="+id).executeUpdate();
			
			s.getTransaction().commit();									
			
		}catch(Exception e){
			s.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ComercialSolutionItem saveOrUpdateComercialSolutionItem(ComercialSolutionItem ip, Participante cliente, Treinamento trein) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try{			
			s.beginTransaction();
			s.createQuery("update "+ComercialSolutionItem.class.getSimpleName()
					+" ip set ip.trainingSolution=:ts, ip.treinamento=:tr, ip.inscrito=:ti, ip.desconto=:des, ip.valorAcertado=:value where ip.id=:id")
					.setParameter("ts", ip.getTrainingSolution())
					.setParameter("tr", trein)
					.setParameter("ti", cliente)
					.setParameter("des", ip.getDesconto())
					.setParameter("value", ip.getValorAcertado())
					.setParameter("id", ip.getId()).executeUpdate();
			/*cliente = (Participante) s.load(Participante.class, cliente.getId());
			TrainingSolution ts = null;
			
			if (ip.getTrainingSolution() != null){
				ts = ip.getTrainingSolution();				
				
			}
			
			ip = (ComercialSolutionItem) s.load(ComercialSolutionItem.class,ip.getId());
			trein = (Treinamento) s.load(Treinamento.class,trein.getId());
			
			if (ts != null){
				ts = (TrainingSolution) s.load(TrainingSolution.class, ts.getId());
				ip.setTrainingSolution(ts);
				if (ts.getComercialSolutionItens() == null) ts.setComercialSolutionItens(new ArrayList());
				ts.getComercialSolutionItens().add(ip);
			}
			
			ip.setInscrito(cliente);			
			if (cliente.getItensTreinamentos() == null) cliente.setItensTreinamentos(new ArrayList());
			cliente.getItensTreinamentos().add(ip);
			
			ip.setTreinamento(trein);
			if (trein.getComercialSolutionItens() == null) trein.setComercialSolutionItens(new ArrayList());
			trein.getComercialSolutionItens().add(ip);*/	
			
						
			/*
			if (ip.getId() != 0L ){
				if (cliente.getItensTreinamentos() == null) cliente.setItensTreinamentos(new ArrayList());
				cliente.getItensTreinamentos().add(ip);
				if (treinAgendadod.getComercialSolutionItens() == null) treinAgendadod.setComercialSolutionItens(new ArrayList());
				treinAgendadod.getComercialSolutionItens().add(ip);				
				s.update(ip);
			}else{				
				s.save(ip);							
			}*/
			//s.flush();
			
			s.getTransaction().commit();
			return ip;
			
		}catch(org.hibernate.exception.ConstraintViolationException e){
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TrainingFormationItem saveOrUpdateComercialSolutionItem(TrainingFormationItem ip, TrainingSolution solution, FormacaoTreinamento formacao) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try{			
			s.beginTransaction();

			ip.setTrainingSolution(solution);
			
			ip.setTrainingFormation(formacao);			
			
			if (ip.getId() != 0 ){
				if (solution.getTrainingFormationItens() == null) solution.setTrainingFormationItens(new ArrayList());
				solution.getTrainingFormationItens().add(ip);
				if (formacao.getTrainingFormationItens() == null) formacao.setTrainingFormationItens(new ArrayList());
				formacao.getTrainingFormationItens().add(ip);
				
				s.update(ip);
			}else{				
				s.save(ip);							
			}
			
			s.getTransaction().commit();
			return ip;
			
		}catch(org.hibernate.exception.ConstraintViolationException e){
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null) s.close();
		}
	}
	
	@Override
	public void publishComercialSolutionBySolutionId(int id, boolean value) throws Exception{
		Session s = null;
		try {
			if (id != 0) {
				s = LocalServicesUtility.getInstance().openSession();
				s.getTransaction().begin();
				
				s.createQuery("update "+entityName+" solution set solution.publicar=:pub" +
						" where solution.id=:id")
				.setParameter("id",id)
				.setParameter("pub",value).executeUpdate();
				
				s.getTransaction().commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
		} finally {
			if (s != null)
				s.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommercialSolutionDetailTab> listTabsByCommercialSolutionId(int id, String lang) throws Exception {
		Session s = null;
		try {
			if (id != 0) {
				s = LocalServicesUtility.getInstance().openSession();
				if(lang == null || lang.equals("")){
					return  (List<CommercialSolutionDetailTab>)
					s.createQuery("select tab from CommercialSolutionDetailTab tab" +
					" where tab.commercial_solution_id=:id order by tab.index")
					.setParameter("id", id).list();
				}else{
					return s.createQuery("select tab from CommercialSolutionDetailTab tab" +
					" where tab.commercial_solution_id=:id" +
					" and tab.detail_text_language=:lang  order by tab.index")
					.setParameter("id", id)
					.setParameter("lang", lang)
					.list();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (s != null)
				s.close();
		}
		return null;
	}
	
	@Override
	public SoftwareDomainInterest getSoftwareDomainInterestByName(String name)
			throws Exception {
		Session s = null;
		try {
			if (name != null) {
				s = LocalServicesUtility.getInstance().openSession();
				SoftwareDomainInterest dom = (SoftwareDomainInterest)
				s.createQuery("select dom from SoftwareDomainInterest dom" +
					" where dom.nome=:nome")
					.setParameter("nome", name)
					.uniqueResult();
				dom.getSoftwares();
				return dom;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (s != null)
				s.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SoftwareDomainInterest> listAllSoftwareDomainInterest(Integer swid, String sigle)
			throws Exception {
		Session s = null;
		try {
			//System.out.println("seraching domain interest");
			{
				s = LocalServicesUtility.getInstance().openSession();
				String query="select dom from SoftwareDomainInterest dom";
				if(swid != null)
					query+="  join dom.softwares as soft where soft.id=:id and soft member of dom.softwares";
				else if(sigle != null)
					query+="  join dom.softwares as soft where soft.sigla=:sigle and soft member of dom.softwares";
				Query q = s.createQuery(query);
				if(swid != null)
					q.setParameter("id", swid.intValue());
				else if(sigle != null)
					q.setParameter("sigle", sigle);
				
				List<SoftwareDomainInterest> list = q.list();
				if (list != null && list.size()>0)
					for(SoftwareDomainInterest ss: list)
						ss.getSoftwares();
				return list;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (s != null)
				s.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SoftwareSolution> listSoftwaresByDomainName(String name, Boolean pub)
			throws Exception {
		Session s = null;
		ArrayList<SoftwareSolution> arr = new ArrayList<SoftwareSolution>();
		try {
			List<SoftwareSolution> slit=null;
			if (name != null) {
				s = LocalServicesUtility.getInstance().openSession();
				String query="select dom.softwares from SoftwareDomainInterest dom" +
				" where dom.nome=:nome";
				
				Query q = s.createQuery(query);
				q.setParameter("nome", name);
				
				slit= q.list();
				
			}
			if(slit != null && slit.size()>0){
				for(SoftwareSolution ss: slit){
					if(pub == null){
						arr.add(ss);
					}
					else if(pub.booleanValue() && ss.isPublicar())
						arr.add(ss);
					else if(!pub.booleanValue() && !ss.isPublicar())
						arr.add(ss);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (s != null)
				s.close();
		}
		return arr;
	}

	@Override
	public SoftwareDomainInterest merge(SoftwareDomainInterest domain,
			SoftwareSolution ss) throws Exception {
		Session s = null;
		try {		
			System.out.println("mergin software on domain " + domain.getId());
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			
			domain = (SoftwareDomainInterest) s.load(SoftwareDomainInterest.class, domain.getId());
			
			//s.refresh(ss);
			
			domain.getSoftwares().add(ss);
			s.merge(domain);
			
			s.getTransaction().commit();
			
			return domain;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (s != null)
				s.close();
		}
	}

	@Override
	public SoftwareDomainInterest unmerge(SoftwareDomainInterest domain,
			SoftwareSolution ss) throws Exception {
		Session s = null;
		try {		
			s = LocalServicesUtility.getInstance().openSession();
			s.refresh(domain);
			
			SoftwareSolution sol = null;
			if(domain.getSoftwares()!= null && domain.getSoftwares().size()>0){
				for(SoftwareSolution soft: domain.getSoftwares()){
					if(soft.getId() == ss.getId()){
						sol = soft;
						break;
					}
				}
			}
			
			if(sol != null){
				s.beginTransaction();
				domain.getSoftwares().remove(sol);
				s.merge(domain);
				s.getTransaction().commit();
			}	
			return domain;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (s != null)
				s.close();
		}
		return null;
	}
	
	@Override
	public CssDefinition getCssDefinition(ComercialSolution solution)
			throws Exception {
		Session s = null;
		try {			
			s = LocalServicesUtility.getInstance().openSession();
			s = LocalServicesUtility.getInstance().openSession();
			String query="select s.cssDefinition from "+ComercialSolution.class.getSimpleName()+" s where s.id=:id";
			Query q = s.createQuery(query);
			q.setParameter("id", solution.getId());
			return (CssDefinition) q.uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (s != null)
				s.close();
		}
		return null;
	}

	@Override
	public List<CssDefinition> listCssDefinitions(String name) throws Exception {
		Session s = null;
		try {			
			s = LocalServicesUtility.getInstance().openSession();
			String query="select css from CssDefinition css ";
			if(name != null)
				query+="where lower(css.name) like:name";
			Query q = s.createQuery(query);
			if(name != null)
				q.setParameter("name", name.toLowerCase());
			return q.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (s != null)
				s.close();
		}
	}

	@Override
	public void merge(ComercialSolution solution, CssDefinition css,
			boolean merge) throws Exception {
		Session s = null;
		try {			
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			
			s.refresh(solution);
			
			s.refresh(css);
			
			solution.setCssDefinition(css);
			s.merge(solution);
			
			s.getTransaction().commit();	
			
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null)
				s.close();
		}
	}

	@Override
	public CssDefinition saveOrUpdate(CssDefinition css) throws Exception {
		Session s = null;
		try {			
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.saveOrUpdate(css);
			s.getTransaction().commit();	
			return css;
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null)
				s.close();
		}
	}

	@Override
	public void generateHtmlCatalog(SoftwareDomainInterest domain)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}