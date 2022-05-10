package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.adapit.portal.dto.LoteReportDTO;
import com.adapit.portal.dto.UsuarioDTO;
import com.adapit.portal.entidades.Access;
import com.adapit.portal.entidades.AtributoPessoa;
import com.adapit.portal.entidades.CategoriaPreferidaEnum;
import com.adapit.portal.entidades.DeactivationReason;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Funcionario;
import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.RepresentanteLegal;
import com.adapit.portal.entidades.StatusAgenda;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.entidades.UserLayoutPreferences;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.PersonType;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.UserDataFilterType;
import com.adapit.portal.services.UserService;
import com.adapit.portal.services.UserSystemAccessFilterType;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.ui.forms.pessoa.PreferenciaUsuario;
import com.adapit.portal.util.global.FilterResultSize;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;
/**
 * @spring.bean id="userServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
@SuppressWarnings({"unchecked","unused"})
public class UserServiceDAOHibernate extends GenericDAOHibernate implements
		UserService, GenericDAO {

	private SessionFactory sessionFactory;
	
	//public static int maxResults = 15;

	public UserServiceDAOHibernate() {

	}

	public List listByActiveValue(boolean active) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.active = ? ");
			paramList.add("" + active + "");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * retorna todos os usuário que alguma vez já foram desativados
	 */
	public List listJustDeactivated(DeactivationReason inactivationReasons) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.inactivationReasons is not null ");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * retorna todos os usuários que são do tipo de pessoa juridica
	 */
	public List listByTipoPessoaJuridica(TipoPessoa joinTipoPessoa) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" joinTipoPessoa.dadosPessoais.tipoPessoa = ? ");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * retorna todos os usuários que são do tipo de pessoa física
	 */
	public List listByTipoPessoaFisica(TipoPessoa joinTipoPessoa) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" joinTipoPessoa.dadosPessoais.tipoPessoa = ? ");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public List listClientsByLastAccessDateInterval(Date valorA, Date valorB) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.valorA between ? and ?");
			paramList.add("valorA");
			paramList.add("valorB");
			hql.append(" and ");
			hql.append(" user.valorB between ? and ?");
			paramList.add("valorA");
			paramList.add("valorB");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public List listByLastAccessDateInterval(Date valorB, Date valorA) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.valorB between ? and ?");
			paramList.add("valorA");
			paramList.add("valorB");
			hql.append(" and ");
			hql.append(" user.valorA between ? and ?");
			paramList.add("valorA");
			paramList.add("valorB");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public List listByRole(UserCadastreType role) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.userCadastreType = ? ");
			paramList.add("" + role + "");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
	
	public List listNullPessoa(Pessoa dadosPessoais) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.dadosPessoais is null ");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public List listLikeNomePessoa(String joinPessoaNome) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" joinPessoaNome.dadosPessoais.nome like ? ");
			paramList.add("%" + joinPessoaNome + "%");
			return getHibernateTemplate().find(hql.toString(),
					paramList.toArray());

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public List listAll() {
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			return template.loadAll(Usuario.class);

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public boolean saveOrUpdate(Usuario user) throws Exception {
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			template.saveOrUpdate(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("UsuarioServiceDAOHibernate_saveOrUpdateError");
		}
	}

	public boolean save(Usuario user) throws Exception {
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			template.save(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("UsuarioServiceDAOHibernate_saveError");
		}
	}

	public boolean update(Usuario user) throws Exception {
		try {
			HibernateTemplate template = new HibernateTemplate(
					getSessionFactory());
			template.update(user);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("UsuarioServiceDAOHibernate_updateError");
		}
	}

	/**
	 * Remove o usuário pelo identificador da tabela
	 */
	public Usuario deleteById(String id) throws Exception {
		try {
			//List paramList = new ArrayList();
			//StringBuffer hql = new StringBuffer();
			Session s = LocalServicesUtility.getInstance().openSession();//sessionFactory.openSession();
			/*hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.id = " + id + " ");*/
			Usuario user = (Usuario) s.load(Usuario.class, id);
			//if (user.getAccessList() != null) s.delete(user.getAccessList());
			try {
				
				Fisica f = (Fisica) s.createQuery("select pf from Fisica pf where pf.pessoa.id="+user.getDadosPessoais().getId()).uniqueResult();
				Juridica j = (Juridica) s.createQuery("select pj from Juridica pj where pj.pessoa.id="+user.getDadosPessoais().getId()).uniqueResult();
				
				
				PessoaEmDivulgacao co = null;
				Participante pa = null;
				Instrutor le = null;
				
				
				Pessoa p = (Pessoa) s.load(Instrutor.class, user.getDadosPessoais().getId());
				if (p instanceof Instrutor) le = (Instrutor) p;
				else if (p instanceof Participante) pa = (Participante) p;
				else if (p instanceof PessoaEmDivulgacao) co = (PessoaEmDivulgacao) p;
				
				s.beginTransaction();
				if (f != null){
					s.delete(f);
				}else {
					s.delete(j);
				}
				if (p.getEndereco() != null){					
					Endereco end = p.getEndereco();
					le.setEndereco(null);
					s.merge(p);					
					s.delete(end);
				}			
				s.getTransaction().commit();
				
				s.beginTransaction(); 
				  if (le != null) {
					le.setUser(null);
					/*if (le.getContaPagar() != null && le.getContaPagar().size() >0){
						for (ParticipanteContaPagar conta: le.getContaPagar()){
							s.delete(conta);
						}
					}						
					le.setContaPagar(null);	*/				
					
				
					
					if (le.getTurmas() != null && le.getTurmas().size()>0){
						for(TurmaTreinamento l: le.getTurmas()){
							l.setInstrutor(null);
							if (l.getComitente().getId() == le.getId()){
								l.setComitente(null);
								le.getTurmas().remove(l);
							}
							s.merge(l);							
						}
					}
					
					/*le.setMeusTreinamentos(null);
					le.setParticipacoes(null);
					le.setPreferencias(null);*/
					le.setTipoPessoa(null);					
					
					user.setDadosPessoais(null);
					s.merge(le);
					s.merge(user);
					
				} else if (pa != null) {
					pa.setUser(null);
					pa.setContaPagar(null);
					pa.setEndereco(null);
					
					//pa.setLotes(null);
					pa.setMeusTreinamentos(null);
					pa.setParticipacoes(null);
					pa.setPreferencias(null);
					pa.setTipoPessoa(null);
					
					user.setDadosPessoais(null);
					
					s.merge(pa);
					s.merge(user);
					
				} else if (co != null) {

					co.setEndereco(null);
					
					//co.setLotes(null);
					co.setTipoPessoa(null);
					
					user.setDadosPessoais(null);
					s.merge(user);
					
				}else {
					
					user.setAccessList(null);
					user.setDeactivationReasonList(null);
					user.setDadosPessoais(null);					
					s.merge(user);
					
				}
				s.getTransaction().commit();
				
				
				
				
				s.beginTransaction();
				if (le != null) {					
					s.delete(le);
				} else if (pa != null) {					
					s.delete(pa);
				} else if (co != null) {
					s.delete(co);
				} else {
					s.delete(user.getDadosPessoais());
				}
				s.getTransaction().commit();
				
				s.beginTransaction();
				s.delete(user);												
				s.getTransaction().commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				s.getTransaction().rollback();
			}finally{
				if (s != null) s.close();
			}			
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;//new Exception("UsuarioServiceDAOHibernate_deleteByIdError");
		}
	}

	/**
	 * Remove o usuário pelo login de usuário
	 */
	public Usuario deleteByLogin(String login) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.login = ? ");
			paramList.add("" + login + "");
			Usuario user = (Usuario) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(user);
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("UsuarioServiceDAOHibernate_deleteByLoginError");
		}
	}

	/**
	 * Remove o usuário pela senha de usuário
	 */
	public Usuario deleteByPassword(String password) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.password = ? ");
			paramList.add("" + password + "");
			Usuario user = (Usuario) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

			getHibernateTemplate().delete(user);
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("UsuarioServiceDAOHibernate_deleteByPasswordError");
		}
	}

	/**
	 * verifica se o usuário existe no sistema
	 */
	public boolean isValid(String login, String password) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("Select user.id from Usuario user ");
			hql.append(" where ");
			hql.append(" user.login = ? ");
			paramList.add("" + login + "");
			hql.append(" and ");
			hql.append(" user.password = ? ");
			paramList.add("" + password + "");
			java.lang.Object obj = DataAccessUtils
			.uniqueResult(getHibernateTemplate().find(hql.toString(),
					paramList.toArray()));
			return (obj != null);

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	public boolean isValid(String login) {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("Select user.id from Usuario user ");
			hql.append(" where ");
			hql.append(" user.login = ? ");
			paramList.add("" + login + "");
			/*Usuario obj = (Usuario) DataAccessUtils
				.uniqueResult(getHibernateTemplate().find(hql.toString(),	paramList.toArray()));*/
			Object obj = DataAccessUtils
			.uniqueResult(getHibernateTemplate().find(hql.toString(),	paramList.toArray()));
			return (obj != null);
			

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	/**
	 * Retorna o usuário pelo identificador da tabela
	 */
	public Usuario getUserById(int id) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.id = " + id + " ");
			return (Usuario) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("UsuarioServiceDAOHibernate_getUsuarioByIdError");
		}
	}

	/**
	 * Retorna o usuário pelo login e senha e estado de ativação
	 */
	public Usuario getUserByLoginAndPasswordAndActive(String login,
			String password, boolean active) throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.login = ? ");
			paramList.add("" + login + "");
			hql.append(" and ");
			hql.append(" user.password = ? ");
			paramList.add("" + password + "");
			hql.append(" and ");
			hql.append(" user.active = ? ");
			paramList.add("" + active + "");
			return (Usuario) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"UsuarioServiceDAOHibernate_getUsuarioByLoginAndPasswordAndActiveError");
		}
	}

	/**
	 * Retorna o usuário pelo login e senha
	 */
	public Usuario getUserByLoginAndPassword(String login, String password)
			throws Exception {
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Usuario user ");
			hql.append(" where ");
			hql.append(" user.login = ? ");
			paramList.add("" + login + "");
			/*hql.append(" and ");
			hql.append(" user.password = ? ");
			paramList.add("" + password + "");*/
			return (Usuario) DataAccessUtils
					.uniqueResult(getHibernateTemplate().find(hql.toString(),
							paramList.toArray()));

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"UsuarioServiceDAOHibernate_getUsuarioByLoginAndPasswordError");
		}
	}
	
	/**
	 * Retorna o usuário pelo login e senha
	 */
	@Override
	public Usuario getUserByLogin(String login)	throws Exception {
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			return (Usuario) s.load(Usuario.class, login);					
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	public Integer countAccordingTo(String descricao, StringQueryKind descKind, UserDataFilterType un, UserCadastreType ur, UserSystemAccessFilterType byActive) throws Exception {
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("select count(usuario) from Usuario usuario ");
			if ((descricao != null || ur != null ) || !(byActive == UserSystemAccessFilterType.TODOS))
				hql.append(" where ");
			
			if (descricao != null){
				if (descKind == StringQueryKind.LIKE || descKind == StringQueryKind.BEGINS_WITH || descKind == StringQueryKind.ENDS_WITH){
					if (un == UserDataFilterType.PESSOA)	hql.append(" lower(usuario.dadosPessoais.nome) like ? ");
					else hql.append(" lower(usuario.login) like ? ");
				}
				else if (descKind == StringQueryKind.EQUALS) {
					if (un == UserDataFilterType.PESSOA)	hql.append(" lower(usuario.dadosPessoais.nome) = ? ");
					else hql.append(" lower(usuario.login) = ? ");
				}
				if (descKind == StringQueryKind.LIKE ) paramList.add("%" + descricao.toLowerCase() + "%");
				else if (descKind == StringQueryKind.EQUALS) paramList.add(descricao.toLowerCase() );
				else if (descKind == StringQueryKind.BEGINS_WITH) paramList.add(descricao.toLowerCase() + "%");
				else if (descKind == StringQueryKind.ENDS_WITH) paramList.add("%"+descricao.toLowerCase());
			}
			if (descricao != null && ur != null)
				hql.append(" and ");
			
			if (ur != null){
				hql.append(" usuario.userCadastreType = ?");
				paramList.add(ur);
			}
						
			if ((descricao != null || ur != null ) && !(byActive == UserSystemAccessFilterType.TODOS) ){
				hql.append(" and ");				
			}
			if (!(byActive == UserSystemAccessFilterType.TODOS)){
				hql.append(" usuario.active = ? ");
				if (byActive == UserSystemAccessFilterType.ATIVOS) paramList.add(new Boolean(true));
				else paramList.add(new Boolean(false));
			}
			
			Session s = null;
			try {
				s = LocalServicesUtility.getInstance().openSession();
				
				System.out.println("EXECUTING QUERY " + hql.toString());
				Query q = s.createQuery(hql.toString());
				if (paramList != null && paramList.size()>0){
					Object params[] = paramList.toArray();					
					for(int i=0; i < params.length; i++){
						Object obj = params[i];
						q.setParameter(i, obj);
					}
				}
				return (Integer) q.uniqueResult();				
			}catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				if (s != null && s.isOpen()) s.close();
			}	

		}catch(Exception ex){
			ex.printStackTrace();

		}
		return null;
	}
	

	public List listAccordingTo(String descricao, StringQueryKind descKind, UserDataFilterType un, UserCadastreType ur, UserSystemAccessFilterType byActive, int firstResult) throws Exception {
		int maxResults = FilterResultSize.userListMaxSize;
		try{
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("select usuario.login, usuario.userCadastreType, usuario.active, usuario.autenticado, usuario.dadosPessoais.id, usuario.dadosPessoais.nome, usuario.dadosPessoais.email, usuario.dadosPessoais.telefone from Usuario usuario ");
			if ((descricao != null || ur != null ) || !(byActive == UserSystemAccessFilterType.TODOS))
				hql.append(" where ");
			
			/*else {
				System.out.println("EXECUTING QUERY " + hql.toString());				
				return getHibernateTemplate().find(hql.toString(), paramList.toArray());
			}*/
			
			if (descricao != null){
				if (descKind == StringQueryKind.LIKE || descKind == StringQueryKind.BEGINS_WITH || descKind == StringQueryKind.ENDS_WITH){
					if (un == UserDataFilterType.PESSOA)	hql.append(" lower(usuario.dadosPessoais.nome) like ? ");
					else hql.append(" lower(usuario.login) like ? ");
				}
				else if (descKind == StringQueryKind.EQUALS) {
					if (un == UserDataFilterType.PESSOA)	hql.append(" lower(usuario.dadosPessoais.nome) = ? ");
					else hql.append(" lower(usuario.login) = ? ");
				}
				if (descKind == StringQueryKind.LIKE ) paramList.add("%" + descricao.toLowerCase() + "%");
				else if (descKind == StringQueryKind.EQUALS) paramList.add(descricao.toLowerCase() );
				else if (descKind == StringQueryKind.BEGINS_WITH) paramList.add(descricao.toLowerCase() + "%");
				else if (descKind == StringQueryKind.ENDS_WITH) paramList.add("%"+descricao.toLowerCase());
			}
			if (descricao != null && ur != null)
				hql.append(" and ");
			
			if (ur != null){
				hql.append(" usuario.userCadastreType = ?");
				paramList.add(ur);
			}
						
			if ((descricao != null || ur != null ) && !(byActive == UserSystemAccessFilterType.TODOS) ){
				hql.append(" and ");				
			}
			if (!(byActive == UserSystemAccessFilterType.TODOS)){
				hql.append(" usuario.active = ? ");
				if (byActive == UserSystemAccessFilterType.ATIVOS) paramList.add(new Boolean(true));
				else paramList.add(new Boolean(false));
			}
			
			Session s = null;
			try {
				s = LocalServicesUtility.getInstance().openSession();
				hql.append(" order by usuario.dadosPessoais.id DESC");
				System.out.println("EXECUTING QUERY " + hql.toString());
				Query q = s.createQuery(hql.toString());
				if (paramList != null && paramList.size()>0){
					Object params[] = paramList.toArray();					
					for(int i=0; i < params.length; i++){
						Object obj = params[i];
						q.setParameter(i, obj);
					}
				}
				List<Object[]> list = q.setMaxResults(maxResults).setFirstResult(firstResult).list();
				ArrayList<Usuario> arr = new ArrayList();
				if (list != null && list.size()>0){
					for(Object[] objs: list){
						try {
							String login = (String) objs[0];
							UserCadastreType r = (UserCadastreType) objs[1]; 
							if(r==null)
								r = UserCadastreType.Cliente;
							boolean ativo = (Boolean) objs[2];
							boolean autenticado = (Boolean) objs[3];
							Long idp = (Long) objs[4];
							String nome = (String) objs[5];
							String email = (String) objs[6];
							String telefone = (String) objs[7];
							Usuario user = new Usuario();
							user.setLogin(login);
							user.setUserCadastreType(r);
							user.setActive(ativo);
							user.setAutenticado(autenticado);
							Pessoa p = null;
							if (user.getUserCadastreType() == UserCadastreType.Comitente) p = new PessoaEmDivulgacao();
							else if (user.getUserCadastreType() == UserCadastreType.Instrutor) p = new Instrutor();
							else if (user.getUserCadastreType() == UserCadastreType.Representante_legal) p = new RepresentanteLegal();
							else if (user.getUserCadastreType() == UserCadastreType.Administrador_do_sistema ||
									user.getUserCadastreType() == UserCadastreType.Funcionário) p = new Funcionario();
							else if (user.getUserCadastreType() == UserCadastreType.Cliente) p = new Participante();
							p.setId(idp);
							p.setNome(nome);
							p.setEmail(email);
							p.setTelefone(telefone);
							user.setDadosPessoais(p);
							System.out.println(" "+nome + " " + email + " " + telefone + " " +login + " " + r.name());
							try{
								
								Object[] tp = (Object[]) s.createQuery(
										"select tp.id, tp.razaoSocial, tp.cnpj, tp.inscricaoEstadual, tp.ramoAtividade from Juridica tp where tp.pessoa.id="+ p.getId())
										.uniqueResult();
								if (tp != null){
									Juridica j = new Juridica();
									j.setId((Integer)tp[0]);
									j.setRazaoSocial((String)tp[1]);
									j.setCnpj((String) tp[2]);
									j.setInscricaoEstadual((String) tp[3]);		
									j.setRamoAtividade((String) tp[3]);
									p.setTipoPessoa(j);
								}
								else{
									tp = (Object[]) s.createQuery(
											"select tp.id, tp.sobrenome, tp.cpf, tp.rg from Fisica tp where tp.pessoa.id="+ p.getId())
											.uniqueResult();
									if (tp != null){
										Fisica f = new Fisica();
										f.setId((Integer)tp[0]);
										f.setSobrenome((String)tp[1]);
										f.setCpf((String) tp[2]);
										f.setRg((String) tp[3]);										
										p.setTipoPessoa(f);
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
								try {
									Object[] tp = (Object[]) s.createQuery(
											"select tp.id, tp.sobrenome, tp.cpf, tp.rg from Fisica tp where tp.pessoa.id="+ p.getId())
											.uniqueResult();
									if (tp != null){
										Fisica f = new Fisica();
										f.setId((Integer)tp[0]);
										f.setSobrenome((String)tp[1]);
										f.setCpf((String) tp[2]);
										f.setRg((String) tp[3]);
										
										p.setTipoPessoa(f);
									}
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
							arr.add(user);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				return arr;
			}catch (Exception e) {
				e.printStackTrace();
			}
			finally{
				if (s != null && s.isOpen()) s.close();
			}
			

		}catch(Exception ex){
			ex.printStackTrace();

		}
		return null;
	}

	public Usuario getUsuarioByIdCascadingProperties(java.io.Serializable id, boolean accessList, boolean deactivationReasons, boolean dadosPessoais) throws Exception {
		try {
			org.hibernate.Session s = getSessionFactory().openSession();			
			Usuario usuario = (Usuario) s.load(Usuario.class, id);
			org.hibernate.Hibernate.initialize(usuario);
			if (accessList) org.hibernate.Hibernate.initialize(usuario.getAccessList());
			if (dadosPessoais) org.hibernate.Hibernate.initialize(usuario.getDadosPessoais());
			if (deactivationReasons) org.hibernate.Hibernate.initialize(usuario.getDeactivationReasonList());			
			s.close();
			return usuario;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("Usuario Cascading Load Exception");
		}
	}
	
	public UsuarioDTO loggingUser(String login, String pass) throws Exception{
		Session s = getHibernateTemplate().getSessionFactory().openSession();
		try {
			String hql = "from Usuario user where user.login = :login and user.password = :password ";
			Usuario u = (Usuario) s.createQuery(hql)
				.setString("login", login)
				.setString("password", pass)
				.uniqueResult();
			
			UsuarioDTO udto = new UsuarioDTO();
			udto.setActive(u.getActive());
			udto.setAuthenticated(u.isAutenticado());
			udto.setId(u.getLogin());
			udto.setTicket(u.getTicket());
			
			if (u.getActive() && u.isAutenticado()){
				u.getDadosPessoais().getId();
				Pessoa p = (Pessoa) u.getDadosPessoais();
				TipoPessoa tp = (TipoPessoa) s.createQuery("from Fisica f where f.pessoa.id="+p.getId()).uniqueResult();
				if (tp == null) tp = (TipoPessoa) s.createQuery("from Juridica j where j.pessoa.id="+p.getId()).uniqueResult();
				p.setTipoPessoa(tp);
				if (p.getTipoPessoa() instanceof Fisica) udto.setTipoPessoa(PersonType.Fisica);
				else udto.setTipoPessoa(PersonType.Juridica);
				udto.setParticipanteId(p.getId());
				udto.setUserRole(u.getUserCadastreType());
				udto.setWelcomeName(p.getNomeFormatado());
				
				
				try {
					UserLayoutPreferences pref = (UserLayoutPreferences) s.createQuery("select pref from UserLayoutPreferences pref where pref.login=:log")
					.setParameter("log",u.getLogin()).uniqueResult();
					udto.setPreferences(pref);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					List<AtributoPessoa> aList = s.getNamedQuery("personAttribute.listById").setParameter("id", p.getId()).list();
					udto.setAtributos(aList);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				if (u.getPreferencia() != null){ 
					u.getPreferencia().getId();
					udto.setPreferencia(u.getPreferencia());
				}
				if (p instanceof Participante){
					Iterator<Treinamento> it = ((Participante)p).getMeusTreinamentos().iterator();
					List<LoteReportDTO> myLotes = new ArrayList();
					while(it.hasNext()){
						Treinamento l =it.next();
						LoteReportDTO lrd = new LoteReportDTO();
						lrd.setCodigo(l.getCodigo());
						lrd.setIdAgenda(l.getAgendaTreinamento().getId());
						lrd.setIdParticipante(p.getId());
						lrd.setIdLote(l.getId());
						StatusAgenda sa = l.getAgendaTreinamento().getStatus();
						lrd.setStatus(sa);
						myLotes.add(lrd);
						
					}
					udto.setLoteReports(myLotes);
				}
				
				s.beginTransaction();
				if (u.getLastAccess() != null){
					Access a = new Access();
					a.setUsuario(u);	
					a.setDataHora(u.getLastAccess());
					s.save(a);
					u.getAccessList().add(a);
					udto.setLastAcess(u.getLastAccess());
				}
				Date d = new Date();
				u.setLastAccess(d);
				
				s.getTransaction().commit();
			}
			
			return udto;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally{
			s.close();
		}
		
	}
	
	public List<LoteReportDTO> getLotesParticipacao(String userid) throws Exception{
		Session s = getHibernateTemplate().getSessionFactory().openSession();
		try {

			Participante p = //(Participante) s.load(Participante.class,new Integer(userid));
			(Participante) s.createQuery(
					"Select u.dadosPessoais from Usuario u where u.login=:log")
					.setParameter("log",userid).uniqueResult();
			if (p == null){
				System.out.println("Não encontrou o participante");
				return new ArrayList();
			}
			else if (p.getMeusTreinamentos() == null){
				System.out.println("Não existem lotes de preferência desse participante");
				return new ArrayList();
			}
			Iterator<Treinamento> it = p.getMeusTreinamentos().iterator();
			List<LoteReportDTO> myLotes = new ArrayList();
			while (it.hasNext()) {
				Treinamento l = it.next();
				LoteReportDTO lrd = new LoteReportDTO();
				lrd.setLoteSelecionado(true);
				lrd.setIdAgenda(l.getAgendaTreinamento().getId());
				lrd.setIdParticipante(p.getId());
				lrd.setIdLote(l.getId());
				
				lrd.setCodigo(l.getCodigo());
				/*lrd.setLanceInicial(l.getAgendaTreinamento().getLanceInicial());
				lrd.setIncremento(l.getAgendaTreinamento().getIncremento());*/
				lrd.setLeilaoIniciou(l.getAgendaTreinamento().isRecebendoInteressados());

				/*StatusAgenda sa = l.getAgendaLote().getStatus();
				if (sa == null){
					lrd.setLeilaoIniciou(false);
					lrd.setStatus(StatusAgenda.Em_execução_no_leilão_1);
				}
				else{
					if (sa != StatusAgenda.Cancelada &&
							sa != StatusAgenda.Em_execução_no_leilão_1 && 
							sa != StatusAgenda.Em_execução_no_leilão_2)
						lrd.setLeilaoIniciou(false);
					else lrd.setLeilaoIniciou(true);
					lrd.setStatus(sa);
				}*/
				/*String queryCount = "Select lance.valor From Lance lance, AgendaLote a where a.id="
						+ l.getAgendaLote().getId()
						+ " and a.lote.id="
						+ l.getId()
						+ " and a.status<>"
						+ StatusAgenda.Encerrada_final_leilão_1.ordinal()
						+ " and a.status<>"
						+ StatusAgenda.Encerrada_final_leilão_2.ordinal()
						+ " and lance member of a.lances order by valor DESC";*/
				
				myLotes.add(lrd);
				
			}
			return myLotes;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally{
			s.close();
		}
	}
	
/*	@SuppressWarnings("unchecked")
	public Lance getMaiorLanceByAgenda(AgendaLote agenda) throws Exception {
		Session s = getHibernateTemplate().getSessionFactory().openSession();
		try {
			if (agenda.getLances() != null && agenda.getLances().size() > 0) {
				List list = (List) agenda.getLances();
				Lance maior = getMaiorLance(list, s);
				return maior;
			}
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			s.close();
		}
	}*/

	
	@Override
	public List<LoteReportDTO> getAllLotesParticipacao(String userid) throws Exception{
		Session s = getHibernateTemplate().getSessionFactory().openSession();
		try {
			String query = "Select l from Lote l where l.agendaLote.encerrada=false and l.agendaLote.confirmada=true and l.agendaLote.recebendoLances=true";
			List<Treinamento> lotes = s.createQuery(query).list();
			Iterator<Treinamento> it = lotes.iterator();
			List<LoteReportDTO> myLotes = new ArrayList();
			while (it.hasNext()) {
				Treinamento l = it.next();
				LoteReportDTO lrd = new LoteReportDTO();
				if (userid != null){
					String existsQuery = "Select u.dadosPessoais.id from Usuario u, Participante p, Lote l where u.login='"
								+ userid+"' and p.id = u.dadosPessoais.id and l.id ="+l.getId()+" and l member of p.meusLotes";
					Object obj = s.createQuery(existsQuery).uniqueResult();
					if (obj != null) lrd.setLoteSelecionado(true);
					else lrd.setLoteSelecionado(false);
					if (obj instanceof Integer)lrd.setIdParticipante(((Integer)obj).intValue());
				}else{
					lrd.setLoteSelecionado(false);
				}	
				lrd.setIdAgenda(l.getAgendaTreinamento().getId());
				
				lrd.setIdLote(l.getId());
				
				lrd.setCodigo(l.getCodigo());
				/*lrd.setLanceInicial(l.getAgendaTreinamento().getLanceInicial());
				lrd.setIncremento(l.getAgendaTreinamento().getIncremento());*/
				lrd.setLeilaoIniciou(l.getAgendaTreinamento().isRecebendoInteressados());
				/*StatusAgenda sa = l.getAgendaLote().getStatus();
				if (sa == null){
					lrd.setLeilaoIniciou(false);
					lrd.setStatus(StatusAgenda.Em_execução_no_leilão_1);
				}
				else{
					if (sa != StatusAgenda.Cancelada &&
							sa != StatusAgenda.Em_execução_no_leilão_1 && 
							sa != StatusAgenda.Em_execução_no_leilão_2)
						lrd.setLeilaoIniciou(false);
					else lrd.setLeilaoIniciou(true);
					lrd.setStatus(sa);
				}*/
				String queryCount = "Select lance.valor From Lance lance, AgendaLote a where a.id="
						+ l.getAgendaTreinamento().getId()
						+ " and a.lote.id="
						+ l.getId()
						+ " and l.agendaLote.cancelada=false and l.agendaLote.confirmada=true and l.agendaLote.recebendoLances=true and lance member of a.lances order by valor DESC";
				List list = s.createQuery(queryCount).list();
				Integer num = (Integer) list.size();
				lrd.setNumeroLances(num.intValue());
				if (list.size() > 0) {
					Float f = (Float) list.get(0);
					if (f != null) {
						lrd.setLanceVencendo(f);
					}
				}
				
				
				myLotes.add(lrd);
			}
			return myLotes;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}finally{
			s.close();
		}
	}

	@Override
	public List<DeactivationReason> listDeactivationReasonByUserLogin(String login) throws Exception {
		Session s = null;
		
		List<DeactivationReason> list = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			list = s.getNamedQuery("usuario.listDeactivationByLogin").setParameter("userlogin", login).list();
			return list;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null) s.close();
		}
	}

	@Override
	public Usuario updateUsuario(Usuario user) throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();			
			s.createQuery("update Usuario user set " +
					"user.password=:pwd, " +
					"user.active=:active, " +
					"user.autenticado=:aut " +
					"where user.login=:login")
			.setParameter("pwd", user.getPassword())
			.setParameter("active", user.getActive())
			.setParameter("aut",user.isAutenticado())
			.setParameter("login", user.getLogin()).executeUpdate();
			s.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return user;
	}

	@Override
	public Usuario deactiveUsuario(Usuario usuario, DeactivationReason deactivationReason) throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			if (deactivationReason.getId() == 0)
				s.save(deactivationReason);
			else s.merge(deactivationReason);
			/*String query = "update Usuario u set u.active=:active where u.login=:userlogin";
			s.createQuery(query)*/
			s.getNamedQuery("usuario.updateActiveByLogin")
				.setParameter("active",false)
				.setParameter("userlogin", usuario.getLogin())
				.executeUpdate();
			s.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return usuario;
	}
	
	
	@Override
	public Usuario activeUsuario(Usuario usuario, DeactivationReason deactivationReason) throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			if (deactivationReason.getId() == 0)
				s.save(deactivationReason);
			else s.merge(deactivationReason);			
			s.getNamedQuery("usuario.updateActiveByLogin")
				.setParameter("active",true)
				.setParameter("userlogin", usuario.getLogin())
				.executeUpdate();
			s.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null) s.close();
		}
		return usuario;
	}
	
	@Override
	public Usuario autenticateUsuario(Usuario usuario, boolean value) throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			String str = usuario.getTicket();
			usuario = (Usuario) s.load(Usuario.class, usuario.getLogin());
			if(str.equals(usuario.getTicket())){
				s.beginTransaction();
				usuario.setAutenticado(value);
				s.update(usuario);
				s.getTransaction().commit();
			}
			return usuario;
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null) s.close();
		}
	}

	@Override
	public List<Usuario> listUsuariosAccordingTo(UserDataFilterType qKind, String value){
		Session s = LocalServicesUtility.getInstance().openSession();
		String whereStr="";
		if (qKind == UserDataFilterType.USUARIO) whereStr+=" usuario.login ";
		else if (qKind == UserDataFilterType.PESSOA) whereStr+=" usuario.dadosPessoais.nome ";
		else whereStr+=" usuario.dadosPessoais.email ";
		try {
			List<Usuario> l = s.createQuery("from Usuario usuario where "+whereStr+" like '%"+value+"%'").list();
			return l;
		} catch (Exception e1) { 
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		return null;
	}
	
	@Override
	public Usuario getByCpf(String cpf){		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			Long pid = (Long) s.createQuery("select fis.pessoa.id from Fisica fis where fis.cpf='"+cpf+"'").uniqueResult();
			if (pid != null){
				Usuario user = (Usuario) s.createQuery("select u from Usuario u where u.dadosPessoais.id="+pid).uniqueResult();
				return user;
			}			
		} catch (Exception e1) { 
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			if (s != null && s.isOpen()) s.close();
		}		
		return null;
	}
	
	@Override
	public Usuario getByCnpj(String cnpj){		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			Long pid = (Long) s.createQuery("select jur.pessoa.id from Juridica jur where jur.cnpj='"+cnpj+"'").uniqueResult();
			if (pid != null){
				Usuario user = (Usuario) s.createQuery("select u from Usuario u where u.dadosPessoais.id="+pid).uniqueResult();
				return user;
			}
		} catch (Exception e1) { 
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return null;
	}
	
	@Override
	public Usuario getByInscricaoEstadual(String inscEst){		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			Long pid = (Long) s.createQuery("select jur.pessoa.id from Juridica jur where jur.inscricaoEstadual='"+inscEst+"'").uniqueResult();
			if (pid != null){
				Usuario user = (Usuario) s.createQuery("select u from Usuario u where u.dadosPessoais.id="+pid).uniqueResult();
				return user;
			}			
		} catch (Exception e1) { 
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return null;
	}
	
	@Override
	public Usuario getByRg(String rg){		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			Long pid = (Long) s.createQuery("select fis.pessoa.id from Fisica fis where fis.rg='"+rg+"'").uniqueResult();
			if (pid != null){
				Usuario user = (Usuario) s.createQuery("select u from Usuario u where u.dadosPessoais.id="+pid).uniqueResult();
				return user;
			}	
		} catch (Exception e1) { 
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return null;
	}
	
	@Override
	public String getNomePessoaByUsuarioLogin(String login){		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			return (String) s.getNamedQuery("usuario.getNome").setParameter("login", login).uniqueResult();				
		} catch (Exception e1) { 
			e1.printStackTrace();
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return null;			
	}
	
	@Override
	public String getEmailPessoaByUsuarioLogin(String login){
		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			return (String) s.getNamedQuery("usuario.getEmail").setParameter("login", login).uniqueResult();				
		} catch (Exception e1) { 
			e1.printStackTrace();
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return null;			
	}
	
	public List<Access> listAccessByUsuarioLogin(String login) throws Exception{
		Session s = null;
		try{
			
			s = LocalServicesUtility.getInstance().openSession();
			List<Access> list = s.getNamedQuery("access.listAllByLogin")
			.setParameter("userlogin", login)
			.list();
			
			return list;			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public Object remove(Usuario[] users) throws Exception {
		try {
			Session s = LocalServicesUtility.getInstance().openSession();
			try{
				s.beginTransaction();
				for(Usuario u: users){
					u = (Usuario) s.get(Usuario.class,u.getLogin());
					/*if (u.getDadosPessoais().getTipoPessoa() instanceof Fisica){
						String delq="delete from Fisica fis where fis.pessoa.id="+u.getDadosPessoais().getId();
						s.createQuery(delq).executeUpdate();
					}else{
						String delq="delete from Juridica jur where jur.pessoa.id="+u.getDadosPessoais().getId();
						s.createQuery(delq).executeUpdate();
					}*/
					if(u.getDadosPessoais() instanceof Participante){
						Participante p = (Participante) u.getDadosPessoais();
						p.setUser(null);
						s.merge(p);
						
					}
					s.delete(u);
					s.delete(u.getDadosPessoais().getTipoPessoa());
					s.delete(u.getDadosPessoais().getEndereco());
					s.delete(u.getDadosPessoais());
				}
				s.getTransaction().commit();
				return true;
			}catch(Exception ex){
				ex.printStackTrace();
				s.getTransaction().rollback();
			}finally{
				s.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Usuario> listUsuariosByPreference(
			CategoriaPreferidaEnum cat)  throws Exception{
		Session s = null;
		try{
			String constraint=cat.name();
			s = LocalServicesUtility.getInstance().openSession();
			List<Usuario> list = (List<Usuario>)
				s.createQuery("select u from Usuario u where u.preferencia."+constraint+"=true")
			.list();
			
			ArrayList<Usuario> arr = new ArrayList<Usuario>();
			if(list != null && list.size()>0){
				for(Usuario user: list){
					System.out.println("Adicionando usuario " + user.getLogin());
					arr.add(user);
					if(user.getDadosPessoais() != null){
						user.getDadosPessoais().getEmail();
						System.out.println("Tem dados pessoais");
					}
				}
			}
			return arr;			
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
}