package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.PessoaService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.validation.FieldMsgValidation;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;


/**
 * @spring.bean id="pessoaServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
public class PessoaServiceDAOHibernate extends GenericDAOHibernate implements
		PessoaService, GenericDAO {

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;


	@Override
	public boolean cnpjExists(String cnpjstr) throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			int cnpj = (Integer) s.createQuery("select count(juridica) from Juridica juridica where juridica.cnpj=:cnpj")
			.setParameter("cnpj", cnpjstr)
			.uniqueResult();
			if (cnpj > 0) return true;
			else return false;
		}catch(Exception ex){
			ex.printStackTrace();			
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return false;
	}

	@Override
	public boolean cpfExists(String cpfstr) throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			int cpf = (Integer) s.createQuery("select count(fisica) from Fisica fisica where fisica.cpf=:cpf")
			.setParameter("cpf", cpfstr)
			.uniqueResult();
			if (cpf > 0) return true;
			else return false;
		}catch(Exception ex){
			ex.printStackTrace();			
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return false;
	}

	@Override
	public boolean emailExists(String email) throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			int emailc= (Integer) s.createQuery("select count(pessoa) from Pessoa pessoa where pessoa.email=:email")
			.setParameter("email", email)
			.uniqueResult();
			if (emailc > 0) return true;
			else return false;
		}catch(Exception ex){
			ex.printStackTrace();			
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return false;
	}

	@Override
	public boolean inscricaoEstadualExists(String inscricaoEstadual)
			throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			int inscricaoc = (Integer) s.createQuery("select count(juridica) from Juridica juridica where juridica.inscricaoEstadual=:inscricaoEstadual")
			.setParameter("inscricaoEstadual", inscricaoEstadual)
			.uniqueResult();
			if (inscricaoc > 0) return true;
			else return false;
		}catch(Exception ex){
			ex.printStackTrace();			
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return false;
	}

	@Override
	public boolean rgExists(String rg) throws Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			int rgc = (Integer) s.createQuery("select count(fisica) from Fisica fisica where fisica.rg=:rg")
			.setParameter("rg", rg)
			.uniqueResult();
			if (rgc > 0) return true;
			else return false;
		}catch(Exception ex){
			ex.printStackTrace();			
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return false;
	}

	@Override
	public FieldMsgValidation validateUnique(int tipo, int idTipo, String cpfcnpj, String rgie, long pId, String pemail){
		Pessoa p = new Participante();
		p.setId(pId);
		TipoPessoa tp = null;
		p.setEmail(pemail);
		if (tipo <=0){
			tp = new Fisica();
			((Fisica)tp).setCpf(cpfcnpj);
			((Fisica)tp).setRg(rgie);
		}else{
			tp = new Juridica();
			((Juridica)tp).setCnpj(cpfcnpj);
			((Juridica)tp).setInscricaoEstadual(rgie);
		}
		tp.setId(idTipo);
		p.setTipoPessoa(tp);
		
		Session s = LocalServicesUtility.getInstance().openSession();
		if (p.getTipoPessoa() instanceof Fisica){	
			
			if (p.getId() < 0){//é novo usuário	
				int cpf = (Integer) s.createQuery("select count(fisica) from Fisica fisica where fisica.cpf=:cpf")
				.setParameter("cpf", ((Fisica)p.getTipoPessoa()).getCpf())
				.uniqueResult();
				int rg = (Integer) s.createQuery("select count(fisica) from Fisica fisica where fisica.rg=:rg")
				.setParameter("rg", ((Fisica)p.getTipoPessoa()).getRg())
				.uniqueResult();
				int email= (Integer) s.createQuery("select count(pessoa) from Pessoa pessoa where pessoa.email=:email")
				.setParameter("email", p.getEmail())
				.uniqueResult();
				if (cpf > 0 || rg > 0 || email > 0){
					Hashtable<String,String> ht = new Hashtable<String,String>();
					if (cpf > 0) ht.put("cpf","está repetida em outro registro. Não pode haver valores repetidos.");
					if (rg > 0) ht.put("rg","está repetida em outro registro. Não pode haver valores repetidos.");
					if (email>0) ht.put("email","está repetida em outro registro. Não pode haver valores repetidos.");
					
					return new FieldMsgValidation(ht);
				}
			}else{//é update
				int cpf = (Integer) s.createQuery("select count(fisica) from Fisica fisica where fisica.cpf=:cpf and fisica.id <> :id")
				.setParameter("cpf", ((Fisica)p.getTipoPessoa()).getCpf())
				.setParameter("id",p.getTipoPessoa().getId())
				.uniqueResult();
				int rg = (Integer) s.createQuery("select count(fisica) from Fisica fisica where fisica.rg=:rg and fisica.id <> :id")
				.setParameter("rg", ((Fisica)p.getTipoPessoa()).getRg())
				.setParameter("id",p.getTipoPessoa().getId())
				.uniqueResult();
				int email= (Integer) s.createQuery("select count(pessoa) from Pessoa pessoa where pessoa.email=:email and pessoa.id <> :id")
				.setParameter("email", p.getEmail())
				.setParameter("id",p.getId())
				.uniqueResult();
				if (cpf > 0 || rg > 0 || email > 0){
					Hashtable<String,String> ht = new Hashtable<String,String>();
					if (cpf > 0) ht.put("cpf","está repetida em outro registro. Não pode haver valores repetidos.");
					if (rg > 0) ht.put("rg","está repetida em outro registro. Não pode haver valores repetidos.");
					if (email>0) ht.put("email","está repetida em outro registro. Não pode haver valores repetidos.");
					
					return new FieldMsgValidation(ht);
				}
			}
		}else{
			if (p.getId() < 0){//é novo usuário	
				int cnpj = (Integer) s.createQuery("select count(juridica) from Juridica juridica where juridica.cnpj=:cnpj")
				.setParameter("cnpj", ((Juridica)p.getTipoPessoa()).getCnpj())
				.uniqueResult();
				int inscricaoEstadual = (Integer) s.createQuery("select count(juridica) from Juridica juridica where juridica.inscricaoEstadual=:inscricaoEstadual")
				.setParameter("inscricaoEstadual", ((Juridica)p.getTipoPessoa()).getInscricaoEstadual())
				.uniqueResult();
				int email= (Integer) s.createQuery("select count(pessoa) from Pessoa pessoa where pessoa.email=:email")
				.setParameter("email", p.getEmail())
				.uniqueResult();
				
				if (cnpj > 0 || inscricaoEstadual > 0 || email > 0){
					Hashtable<String,String> ht = new Hashtable<String,String>();
					if (cnpj > 0) ht.put("cnpj","está repetida em outro registro. Não pode haver valores repetidos.");
					if (inscricaoEstadual > 0) ht.put("inscricaoEstadual","está repetida em outro registro. Não pode haver valores repetidos.");
					if (email>0) ht.put("email","está repetida em outro registro. Não pode haver valores repetidos.");
					
					return new FieldMsgValidation(ht);
				}
			}else{
				int cnpj = (Integer) s.createQuery("select count(juridica) from Juridica juridica where juridica.cnpj=:cnpj and juridica.id <> :id")
				.setParameter("cnpj", ((Juridica)p.getTipoPessoa()).getCnpj())
				.setParameter("id",p.getTipoPessoa().getId())
				.uniqueResult();
				int inscricaoEstadual = (Integer) s.createQuery("select count(juridica) from Juridica juridica where juridica.inscricaoEstadual=:inscricaoEstadual  and juridica.id <> :id")
				.setParameter("inscricaoEstadual", ((Juridica)p.getTipoPessoa()).getInscricaoEstadual())
				.setParameter("id",p.getTipoPessoa().getId())
				.uniqueResult();
				int email= (Integer) s.createQuery("select count(pessoa) from Pessoa pessoa where pessoa.email=:email and pessoa.id <> :id")
				.setParameter("email", p.getEmail())
				.setParameter("id",p.getId())
				.uniqueResult();
				
				if (cnpj > 0 || inscricaoEstadual > 0 || email > 0){
					Hashtable<String,String> ht = new Hashtable<String,String>();
					if (cnpj > 0) ht.put("cnpj","está repetida em outro registro. Não pode haver valores repetidos.");
					if (inscricaoEstadual > 0) ht.put("inscricaoEstadual","está repetida em outro registro. Não pode haver valores repetidos.");
					if (email>0) ht.put("email","está repetida em outro registro. Não pode haver valores repetidos.");
					
					return new FieldMsgValidation(ht);
				}
			}
		}
		return null;
	}
	
	@Override
	public Usuario saveOrUpdate(Usuario user, Pessoa p, Endereco ender)
			throws FieldMsgValidationException, ConstraintViolationException,
			DataException, NonUniqueObjectException, Exception {
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			
			//Checa se dados não serão replicados
			if (p.getTipoPessoa() instanceof Fisica){	
				
				if (p.getId() < 0){//é novo usuário	
					int cpf = (Integer) s.createQuery("select count(fisica) from Fisica fisica where fisica.cpf=:cpf")
					.setParameter("cpf", ((Fisica)p.getTipoPessoa()).getCpf())
					.uniqueResult();
					int rg = (Integer) s.createQuery("select count(fisica) from Fisica fisica where fisica.rg=:rg")
					.setParameter("rg", ((Fisica)p.getTipoPessoa()).getRg())
					.uniqueResult();
					int email= (Integer) s.createQuery("select count(pessoa) from Pessoa pessoa where pessoa.email=:email")
					.setParameter("email", p.getEmail())
					.uniqueResult();
					if (cpf > 0 || rg > 0 || email > 0){
						Hashtable<String,String> ht = new Hashtable<String,String>();
						if (cpf > 0) ht.put("cpf","está repetida em outro registro. Não pode haver valores repetidos.");
						if (rg > 0) ht.put("rg","está repetida em outro registro. Não pode haver valores repetidos.");
						if (email>0) ht.put("email","está repetida em outro registro. Não pode haver valores repetidos.");
						
						throw new FieldMsgValidationException(ht);
					}
				}else{//é update
					int cpf = (Integer) s.createQuery("select count(fisica) from Fisica fisica where fisica.cpf=:cpf and fisica.id <> :id")
					.setParameter("cpf", ((Fisica)p.getTipoPessoa()).getCpf())
					.setParameter("id",p.getTipoPessoa().getId())
					.uniqueResult();
					int rg = (Integer) s.createQuery("select count(fisica) from Fisica fisica where fisica.rg=:rg and fisica.id <> :id")
					.setParameter("rg", ((Fisica)p.getTipoPessoa()).getRg())
					.setParameter("id",p.getTipoPessoa().getId())
					.uniqueResult();
					int email= (Integer) s.createQuery("select count(pessoa) from Pessoa pessoa where pessoa.email=:email and pessoa.id <> :id")
					.setParameter("email", p.getEmail())
					.setParameter("id",p.getId())
					.uniqueResult();
					if (cpf > 0 || rg > 0 || email > 0){
						Hashtable<String,String> ht = new Hashtable<String,String>();
						if (cpf > 0) ht.put("cpf","está repetida em outro registro. Não pode haver valores repetidos.");
						if (rg > 0) ht.put("rg","está repetida em outro registro. Não pode haver valores repetidos.");
						if (email>0) ht.put("email","está repetida em outro registro. Não pode haver valores repetidos.");
						
						throw new FieldMsgValidationException(ht);
					}
				}
			}else{
				if (p.getId() < 0){//é novo usuário	
					int cnpj = (Integer) s.createQuery("select count(juridica) from Juridica juridica where juridica.cnpj=:cnpj")
					.setParameter("cnpj", ((Juridica)p.getTipoPessoa()).getCnpj())
					.uniqueResult();
					int inscricaoEstadual = (Integer) s.createQuery("select count(juridica) from Juridica juridica where juridica.inscricaoEstadual=:inscricaoEstadual")
					.setParameter("inscricaoEstadual", ((Juridica)p.getTipoPessoa()).getInscricaoEstadual())
					.uniqueResult();
					int email= (Integer) s.createQuery("select count(pessoa) from Pessoa pessoa where pessoa.email=:email")
					.setParameter("email", p.getEmail())
					.uniqueResult();
					
					if (cnpj > 0 || inscricaoEstadual > 0 || email > 0){
						Hashtable<String,String> ht = new Hashtable<String,String>();
						if (cnpj > 0) ht.put("cnpj","está repetida em outro registro. Não pode haver valores repetidos.");
						if (inscricaoEstadual > 0) ht.put("inscricaoEstadual","está repetida em outro registro. Não pode haver valores repetidos.");
						if (email>0) ht.put("email","está repetida em outro registro. Não pode haver valores repetidos.");
						
						throw new FieldMsgValidationException(ht);
					}
				}else{
					int cnpj = (Integer) s.createQuery("select count(juridica) from Juridica juridica where juridica.cnpj=:cnpj and juridica.id <> :id")
					.setParameter("cnpj", ((Juridica)p.getTipoPessoa()).getCnpj())
					.setParameter("id",p.getTipoPessoa().getId())
					.uniqueResult();
					int inscricaoEstadual = (Integer) s.createQuery("select count(juridica) from Juridica juridica where juridica.inscricaoEstadual=:inscricaoEstadual  and juridica.id <> :id")
					.setParameter("inscricaoEstadual", ((Juridica)p.getTipoPessoa()).getInscricaoEstadual())
					.setParameter("id",p.getTipoPessoa().getId())
					.uniqueResult();
					int email= (Integer) s.createQuery("select count(pessoa) from Pessoa pessoa where pessoa.email=:email and pessoa.id <> :id")
					.setParameter("email", p.getEmail())
					.setParameter("id",p.getId())
					.uniqueResult();
					
					if (cnpj > 0 || inscricaoEstadual > 0 || email > 0){
						Hashtable<String,String> ht = new Hashtable<String,String>();
						if (cnpj > 0) ht.put("cnpj","está repetida em outro registro. Não pode haver valores repetidos.");
						if (inscricaoEstadual > 0) ht.put("inscricaoEstadual","está repetida em outro registro. Não pode haver valores repetidos.");
						if (email>0) ht.put("email","está repetida em outro registro. Não pode haver valores repetidos.");
						
						throw new FieldMsgValidationException(ht);
					}
				}
			}
			//Salva os dados
			if (user.isNewUser()) {
				s.beginTransaction();
				s.save(ender);
				s.save(p);
	
				s.save(user);
	
				user.setDadosPessoais(p);
				if (p instanceof Participante)
					((Participante) p).setUser(user);
					
				s.merge(user);
				s.getTransaction().commit();
			} else {
				s.beginTransaction();
				user.setDadosPessoais(p);
				if (p instanceof Participante)
					((Participante) p).setUser(user);

				s.update(ender);
				s.update(p);

				s.getTransaction().commit();
			}
			return user;
		}catch(org.hibernate.NonUniqueObjectException ex){
			ex.printStackTrace();
			if (s.getTransaction().isActive()) s.getTransaction().rollback();
			s.evict(user);
			s.clear();
			throw ex;
		}catch(org.hibernate.exception.ConstraintViolationException ex){
			ex.printStackTrace();
			Hashtable<String,String> ht = new Hashtable<String,String>();
			ht.put("cnpj","pode estar repetida em outro registro. Não pode haver valores repetidos.");
			ht.put("inscricaoEstadual","pode estar repetida em outro registro. Não pode haver valores repetidos.");
			ht.put("cpf","pode estar repetida em outro registro. Não pode haver valores repetidos.");
			ht.put("rg","pode estar repetida em outro registro. Não pode haver valores repetidos.");
			ht.put("email","pode estar repetida em outro registro. Não pode haver valores repetidos.");
				
			throw new FieldMsgValidationException(ht);			
		}catch(FieldMsgValidationException ex){
			if (s.getTransaction().isActive()) s.getTransaction().rollback();
			throw ex;
		}catch(Exception ex){
			ex.printStackTrace();
			if (s.getTransaction().isActive()) s.getTransaction().rollback();
			throw ex;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public Pessoa saveOrUpdate(Pessoa p, Endereco ender)
			throws FieldMsgValidationException, ConstraintViolationException,
			DataException, NonUniqueObjectException, Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			if (p.getId() <= 0) {
				if (p.getTipoPessoa() instanceof Fisica) {
					int cpf = (Integer) s
							.createQuery(
									"select count(fisica) from Fisica fisica where fisica.cpf=:cpf")
							.setParameter("cpf",
									((Fisica) p.getTipoPessoa()).getCpf())
							.uniqueResult();
					int rg = (Integer) s
							.createQuery(
									"select count(fisica) from Fisica fisica where fisica.rg=:rg")
							.setParameter("rg",
									((Fisica) p.getTipoPessoa()).getRg())
							.uniqueResult();
					int email = (Integer) s
							.createQuery(
									"select count(pessoa) from Pessoa pessoa where pessoa.email=:email")
							.setParameter("email", p.getEmail()).uniqueResult();

					if (cpf > 0 || rg > 0 || email > 0) {
						Hashtable<String, String> ht = new Hashtable<String, String>();
						if (cpf > 0)
							ht
									.put("cpf",
											"está repetida em outro registro. Não pode haver valores repetidos.");
						if (rg > 0)
							ht
									.put("rg",
											"está repetida em outro registro. Não pode haver valores repetidos.");
						if (email > 0)
							ht
									.put("email",
											"está repetida em outro registro. Não pode haver valores repetidos.");

						throw new FieldMsgValidationException(ht);
					} else {
						s.beginTransaction();
						s.save(ender);
						s.save(p);						
						s.getTransaction().commit();
					}
				} else {
					int cpf = (Integer) s
							.createQuery(
									"select count(juridica) from Juridica fisica where juridica.cnpj=:cnpj")
							.setParameter("cnpj",
									((Juridica) p.getTipoPessoa()).getCnpj())
							.uniqueResult();
					int rg = (Integer) s
							.createQuery(
									"select count(juridica) from Juridica fisica where juridica.inscricaoEstadual=:inscricaoEstadual")
							.setParameter(
									"inscricaoEstadual",
									((Juridica) p.getTipoPessoa())
											.getInscricaoEstadual())
							.uniqueResult();
					int email = (Integer) s
							.createQuery(
									"select count(pessoa) from Pessoa pessoa where pessoa.email=:email")
							.setParameter("email", p.getEmail()).uniqueResult();

					if (cpf > 0 || rg > 0 || email > 0) {
						Hashtable<String, String> ht = new Hashtable<String, String>();
						if (cpf > 0)
							ht
									.put("cpf",
											"está repetida em outro registro. Não pode haver valores repetidos.");
						if (rg > 0)
							ht
									.put("rg",
											"está repetida em outro registro. Não pode haver valores repetidos.");
						if (email > 0)
							ht
									.put("email",
											"está repetida em outro registro. Não pode haver valores repetidos.");

						throw new FieldMsgValidationException(ht);
					} else {
						s.beginTransaction();
						s.save(ender);
						s.save(p);
						s.getTransaction().commit();
					}
				}
			} else {
				s.beginTransaction();
			
				s.update(ender);
				s.update(p);

				s.getTransaction().commit();
			}
			return p;
		} catch (org.hibernate.NonUniqueObjectException ex) {
			ex.printStackTrace();
			if (s.getTransaction().isActive())
				s.getTransaction().rollback();
			s.evict(p);
			s.clear();
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			if (s.getTransaction().isActive())
				s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}
	}
	
	@Override
	public Instrutor initializeInstrutor(Long id) throws Exception{
		Session s = null;
		try {
			s=LocalServicesUtility.getInstance().openSession();
			Instrutor instrutor = (Instrutor) s.load(Instrutor.class,id);
			instrutor.getApresentacao();
			if(instrutor.getUser() != null)
				instrutor.getUser().getLogin();
			if(instrutor.getTipoPessoa() != null)
				instrutor.getTipoPessoa().getId();
			if(instrutor.getEndereco() != null)
				instrutor.getEndereco().getId();
			/*instrutor.setEndereco((Endereco)s.createQuery("select p.endereco from Pessoa p where p.id=:id")
			.setParameter("id", id).uniqueResult());*/
			//instrutor.getEndereco().getRua();
			//instrutor.getLeiloes().getId();
			return instrutor;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public Participante initializeParticipante(Long id) throws Exception{
		Session s = null;
		try {
			s=LocalServicesUtility.getInstance().openSession();
			Object obj = s.createQuery("from Participante p where p.id="+id).uniqueResult();
			if (obj != null){
				((Participante)obj).getTipoPessoa();
				((Participante)obj).getUser();
				return (Participante) obj;
			}
			return null;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public Endereco getEnderecoByPessoaId(Long id) throws Exception{
		Session s = null;
		try {
			s=LocalServicesUtility.getInstance().openSession();			
			return (Endereco)s.createQuery("select p.endereco from Pessoa p where p.id=:id")
				.setParameter("id", id).uniqueResult();
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	private String getPessoaQuery(Class cl){
		return "select p from "+cl.getSimpleName()+" p where p.id=:id";
	}
	
	@Override
	public Instrutor getInstrutor(long id) throws Exception{		

		Session session = null;
		try{
			session =LocalServicesUtility.getInstance().openSession();
			Instrutor instr = (Instrutor) session.createQuery(getPessoaQuery(Instrutor.class)).setParameter("id", id).uniqueResult();			
			if (instr.getLogotipo() != null)
				instr.getLogotipo().getId();
			return instr;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (session != null && session.isOpen()) session.close();
		}
		
	}

	@Override
	public Participante getParticipante(long id)  throws Exception{
		Session session = null;
		try{
			session =LocalServicesUtility.getInstance().openSession();
			return (Participante) session.createQuery(getPessoaQuery(Participante.class)).setParameter("id", id).uniqueResult();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (session != null && session.isOpen()) session.close();
		}
		
	}
	
	@Override
	public Funcionario getFuncionario(long id)  throws Exception{		
		Session session = null;
		try{
			session =LocalServicesUtility.getInstance().openSession();			
			return (Funcionario) session.createQuery(getPessoaQuery(Funcionario.class)).setParameter("id", id).uniqueResult();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (session != null && session.isOpen()) session.close();
		}
		
	}
	
	@Override
	public PessoaEmDivulgacao getComitente(long id)  throws Exception{		
		Session session = null;
		try{
			session =LocalServicesUtility.getInstance().openSession();	
			return (PessoaEmDivulgacao) session.createQuery(getPessoaQuery(PessoaEmDivulgacao.class)).setParameter("id", id).uniqueResult();
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}finally{
			if (session != null && session.isOpen()) session.close();
		}
		
	}


	@Override
	public Fisica getPessoaFisicaByIdPessoa(long id) throws Exception {
		Session s =null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			return (Fisica) s.createQuery("from Fisica f where f.pessoa.id =" +id).uniqueResult();			
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public Juridica getPessoaJuridicaByIdPessoa(long id) throws Exception {
		Session s =null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			return (Juridica) s.createQuery("from Juridica j where j.pessoa.id =" +id).uniqueResult();			
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RepresentanteLegal> listRepresentanteLegalLazy() throws Exception{
		Session s = null;
		List<RepresentanteLegal> arr = new ArrayList<RepresentanteLegal>();
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List<Object[]> list = s.createQuery("select rep.id,rep.nome from RepresentanteLegal rep").list();
			
			for(Object[] objs: list){
				RepresentanteLegal rep = new RepresentanteLegal();
				rep.setNome((String)objs[1]);
				rep.setId((Long)objs[0]);
				Object fis[] = (Object[]) s.createQuery("select f.id, f.sobrenome from Fisica f where f.pessoa.id="+objs[0]).uniqueResult();
				Fisica f = new Fisica();
				f.setId((Integer) fis[0]);
				f.setSobrenome((String)fis[1]);
				rep.setTipoPessoa(f);
				f.setPessoa(rep);
				arr.add(rep);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
		return arr;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RepresentanteLegal> listRepresentanteLegalLoading() throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			return s.createQuery("select rep from RepresentanteLegal rep").list();			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}

	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public long getParticipanteIdByIdUsuario(Class clazz, String login) throws Exception{		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			String simpleName = clazz.getSimpleName();
			return (Long) s.createQuery("select participante.id from "+simpleName+" participante where participante.user.login = "+login).uniqueResult();			
		} catch (Exception e1) { 
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}			
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Participante getParticipanteByIdUsuario(Class clazz, String login) throws Exception{		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			String simpleName = clazz.getSimpleName();
			Participante p = (Participante) s.createQuery("from "+simpleName+" participante where participante.user.login = "+login).uniqueResult();
			return p;
		} catch (Exception e1) { 
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}			
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean isPessoaInstanceOf(long idPessoa, Class clazz){		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			String name = null;
			name = clazz.getSimpleName();
			String qu="select pessoa.id from "+name+" pessoa where pessoa.id="+idPessoa+"";
			s.createQuery(qu).uniqueResult();
			return true;
		} catch (Exception e1) { 
			e1.printStackTrace();
			return false;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}			
	}
	
	/*public void updateOutrasCategoriasByPreferenciaId(Integer id, String value) throws Exception{
		Session s = null;
		if (id == null) return;
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.getNamedQuery("preferencia.updatePreferencias")
			.setParameter("value",value)
			.setParameter("id",id.intValue()).executeUpdate();
			s.getTransaction().commit();						
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}*/
	
	@Override
	public Imagem mergePersonalImage(int imageid, long personid) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			
			PessoaEmDivulgacao c = (PessoaEmDivulgacao) s.load(PessoaEmDivulgacao.class, personid);
			
			Imagem im = (Imagem) s.load(Imagem.class, imageid);
			
			c.setLogotipo(im);
			s.merge(c);
			
			s.getTransaction().commit();	
			return im;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public Imagem getPersonalImage(long personid) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			Imagem im = (Imagem) s.createQuery("select p.logotipo from PessoaEmDivulgacao p where p.id="+personid).uniqueResult();
			if(im == null)
				return null;
			im.getId();
				
			return im;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public AtributoPessoa getAtributoPessoaByAttributeTypeAndPersonId(long personid, PersonAttributeType type) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			AtributoPessoa att = (AtributoPessoa) s.getNamedQuery("personAttribute.getByAttributeAndId")
			.setParameter("id",personid).setParameter("attribute",type).uniqueResult();
			return att;
		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<AtributoPessoa> listAtributoPessoaByPersonId(long personid) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			List<AtributoPessoa> att = (List<AtributoPessoa>) s.getNamedQuery("personAttribute.listById")
			.setParameter("id",personid).list();
			return att;
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Long> listPersonIdByAttribute(PersonAttributeType type) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			List<Long> att = (List<Long>) s.getNamedQuery("personAttribute.listPersonIdByAttribute")
			.setParameter("attribute",type).list();
			return att;
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	//@Override
	@SuppressWarnings("unchecked")
	public List<ComentarioCliente> listComentarioClienteByPersonId(long personid) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			List<ComentarioCliente> att = (List<ComentarioCliente>) s.getNamedQuery("comentarioCliente.listByIdPessoa")
			.setParameter("id",personid).list();
			return att;
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	//@Override
	@SuppressWarnings("unchecked")
	public List<ComentarioCliente> listWebComentarioClienteByCommercialSolutionId(int comid) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			List<ComentarioCliente> att = (List<ComentarioCliente>)
			s.getNamedQuery("comentarioCliente.listWebByCommercialSolution")
			.setParameter("id",comid).list();
			return att;
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	//@Override
	@SuppressWarnings("unchecked")
	public List<ComentarioCliente> listAnyComentarioClienteByCommercialSolutionId(int comid) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			List<ComentarioCliente> att = (List<ComentarioCliente>)
			s.getNamedQuery("comentarioCliente.listByCommercialSolution")
			.setParameter("id",comid).list();
			return att;
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	//@Override
	@SuppressWarnings("unchecked")
	public List<ComentarioCliente> listComentarioClienteByApproval(boolean ap) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			List<ComentarioCliente> att = (List<ComentarioCliente>)
			s.getNamedQuery("comentarioCliente.listByApproval")
			.setParameter("ap",ap).list();
			return att;
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ComentarioCliente> listComentarioClienteByValues(Boolean apresentar, Long idPessoa, Integer idCom, ClassificacaoComentarioCliente clif) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			String query="select c from ComentarioCliente c ";
			boolean whereused=false;
			if (apresentar != null){
				query+=" where ";
				whereused=true;
				query+=" c.aprovado=:ap ";
			}
			if (idPessoa != null){
				if (!whereused){
					query+=" where ";
					whereused=true;					
				} else query+=" and ";				
				query+=" c.pessoa.id=:idPessoa ";
			}
			if (idCom != null){
				if (!whereused){
					query+="where ";
					whereused=true;					
				} else query+=" and ";				
				query+=" c.commercialSolution.id=:idcom ";
			}
			if (clif != null){
				if (!whereused){
					query+="where ";
					whereused=true;					
				} else query+=" and ";				
				query+=" c.classificacao=:clif ";
			}
			Query q = s.createQuery(query);
			
			if (apresentar != null) q.setParameter("ap",apresentar);
			if (idPessoa != null) q.setParameter("idPessoa",idPessoa);
			if (idCom != null) q.setParameter("idCom",idCom);
			if (clif != null) q.setParameter("clif",clif);
			List<ComentarioCliente> att = (List<ComentarioCliente>) q.list();
			return att;
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public ComentarioCliente updateComentarioCliente(Boolean apresentar, String comentario, ClassificacaoComentarioCliente clif, ComentarioClientePk pk, Date data) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			ComentarioCliente cc = (ComentarioCliente) s.load(ComentarioCliente.class, pk);
			if (apresentar != null)
				cc.setAprovado(apresentar);
			if (comentario != null)
				cc.setComentario(comentario);
			if (clif != null)
				cc.setClassificacao(clif);
			if (data != null)
				cc.setDataHora(data);
			s.update(cc);
			
			s.getTransaction().commit();
			return cc;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}
	
	@Override
	public Pessoa saveOrUpdate(ComitenteSimples p)
			throws FieldMsgValidationException, ConstraintViolationException,
			DataException, NonUniqueObjectException, Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			if (p.getId() <= 0) {

				int email = (Integer) s
						.createQuery(
								"select count(pessoa) from Pessoa pessoa where pessoa.email=:email")
						.setParameter("email", p.getEmail()).uniqueResult();

				if (email > 0) {
					Hashtable<String, String> ht = new Hashtable<String, String>();
					if (email > 0)
						ht
								.put("email",
										"está repetida em outro registro. Não pode haver valores repetidos.");

					throw new FieldMsgValidationException(ht);
				} else {
					s.beginTransaction();
					s.save(p);
					s.getTransaction().commit();
				}

			} else {
				s.beginTransaction();

				s.update(p);

				s.getTransaction().commit();
			}
			return p;
		} catch (org.hibernate.NonUniqueObjectException ex) {
			ex.printStackTrace();
			if (s.getTransaction().isActive())
				s.getTransaction().rollback();
			s.evict(p);
			s.clear();
			throw ex;
		} catch (Exception ex) {
			ex.printStackTrace();
			if (s.getTransaction().isActive())
				s.getTransaction().rollback();
			throw ex;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}
	}
	
	@Override
	public AtributoPessoa save(AtributoPessoa att) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.saveOrUpdate(att);
			s.getTransaction().commit();
			return att;
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}

	@Override
	public void delete(AtributoPessoa att) throws Exception{
		Session s = null;		
		try {						
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.delete(att);
			s.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null && s.isOpen()) s.close();
		}

	}
	
	public boolean violatesUniqueOnPropertyEmail(Pessoa pessoa ) throws Exception{
		org.hibernate.Session session = getHibernateTemplate().getSessionFactory().openSession();
		try{

			String query="select pessoa.id from Pessoa pessoa where pessoa.email=:email";
			Long id = (Long) session.createQuery(query).setParameter("email", pessoa.getEmail()).uniqueResult();
			if(id == null) return false;
			if(id.equals(pessoa.getId())) return false;
			else return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
		finally{
			if(session != null && session.isOpen()) session.close();
		}

	}
}
