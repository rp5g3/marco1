package com.adapit.portal.services.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.services.EnderecoService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;

/**
 * @spring.bean id="enderecoServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
public class EnderecoServiceDAOHibernate extends GenericDAOHibernate implements GenericDAO, EnderecoService {

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;


	public EnderecoServiceDAOHibernate() {

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> listEnderecoByTipo(AddressType tipo)
			throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		List<Endereco> arr = new ArrayList<Endereco>();
		try {
			s.beginTransaction();
			Iterator<Object[]> it = s
					.createQuery(
							"select endereco.id, endereco.cidade, endereco.rua from Endereco endereco where endereco.tipo="
									+ tipo.ordinal()
									+ " order by endereco.cidade ASC").list()
					.iterator();
			while (it.hasNext()) {
				Object obj[] = (Object[]) it.next();
				Endereco l = new Endereco();
				l.setId(((Integer) obj[0]).intValue());
				l.setCidade((String) obj[1]);
				l.setRua((String) obj[2]);
				arr.add(l);
			}

			return arr;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Endereco> listEnderecoEagerByTipo(AddressType tipo)
			throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();		
		try {
			s.beginTransaction();
			List<Endereco> list = s
					.createQuery(
							"select endereco from Endereco endereco where endereco.tipo="
									+ tipo.ordinal()
									+ " order by endereco.pais,endereco.estado,endereco.cidade ASC").list();
			for(Endereco ender: list){
				ender.getPais();
				ender.getCidade();
				ender.getEstado();
				ender.getLocal();
			}

			return list;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> listAllCidadesByTipo(AddressType tipo)	throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			List<String> list = s
					.createQuery(
							"select distinct endereco.cidade from Endereco endereco where endereco.tipo="
									+ tipo.ordinal()
									+ " order by endereco.cidade ASC").list()
					;
			return list;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> listAllBairrosByCidade(String value) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			List<String> list = s
					.createQuery(
							"select distinct endereco.bairro from Endereco endereco where lower(endereco.cidade) like '%"
									+ value.toLowerCase()
									+ "%' order by endereco.bairro ASC").list()
					;
			return list;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> listAllCidadesByEstado(String value) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			List<String> list = s
					.createQuery(
							"select distinct endereco.cidade from Endereco endereco where lower(endereco.estado) like '%"
									+ value.toLowerCase()
									+ "%' order by endereco.cidade ASC").list()
					;
			return list;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> listAllEstadosByPais(Pais pais) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			List<String> list = s
					.createQuery(
							"select distinct endereco.estado from Endereco endereco " +
							"where endereco.pais =:p order by endereco.estado ASC ")
							.setParameter("p", pais).list();
			return list;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> listAllRuasByBairro(String value) throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			List<String> list = s
					.createQuery(
							"select distinct endereco.rua from Endereco endereco where lower(endereco.bairro) like '%"
									+ value.toLowerCase()
									+ "%' order by endereco.rua ASC").list()
					;
			return list;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		} finally {
			s.close();
		}
	}

}
