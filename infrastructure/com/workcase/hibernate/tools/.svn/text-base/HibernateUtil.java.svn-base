/*
 * HibernateUtil.java
 *
 * Created on 9 de Dezembro de 2005, 14:48
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package com.workcase.hibernate.tools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.velocity.test.provider.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.dao.support.DataAccessUtils;

/**
 * <p><b>Descrição:</b> Classe que provem o mapeamento das classes persistentes anotadas,
 * além disso disponibiliza o objeto Session do hibernate e possui alguns metodos que executam
 * operações atômicas na base de dados.
 * OBS os mesmos não devem ser chamados dentro de uma transação.</p>
 * <p><b>Empresa..:</b> TargeTrust</p>
 * <p><b>Sistema..:</b> CursoHibernate</p>
 * <p><b>Criação..:</b> 07/12/2005 13:30:32</p>
 * @author juliocs
 * @version 1.0
 */
public class HibernateUtil {
    
    private static SessionFactory sessionFactory = null;
    private static AnnotationConfiguration cfg = null;
    private static Logger log = null;
    private static Transaction transaction = null;
    
    /** Creates a new instance of HibernateUtil */
    public HibernateUtil() {
        log = Logger.getLogger( HibernateUtil.class );
    }
    
    static {
        try {
            cfg = new AnnotationConfiguration();
            //.addPackage("test.animals") //the fully qualified package name
            /*cfg.addAnnotatedClass( Pessoa.class );
            cfg.addAnnotatedClass( Professor.class );
            cfg.addAnnotatedClass( Curso.class );
            cfg.addAnnotatedClass( Endereco.class );
            cfg.addAnnotatedClass( Aluno.class );
            cfg.addAnnotatedClass( DataBaseConnection.class );*/
            //cfg.addAnnotatedClass(Person.class);
            //HibernateConnectionDB hcdb = new Oracle10gConnectionDB();
            HibernateConnectionDB hcdb = new PostgreeHibernateConnection();
            //HibernateConnectionDB hcdb = new MySqlHibernateConnectionDB();
            hcdb.setConnection(cfg);
            sessionFactory = getCfg().buildSessionFactory();
            
        } catch (Exception ex) {
            //log.error( "Erro ao instanciar o objeto SessionFactory" );
            // Log exception!
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    /**
     *
     * <p><b>Autor....:</b> juliocs</p>
     * <p><b>Criação..:</b> 07/12/2005 13:40:22</p>
     * @return Session
     */
    public static Session getSession()throws HibernateException {
        return sessionFactory.openSession();
    }
    
    /**
     * Método responsável por fazer a inclusão de um novo objeto na base, possui trasação interna por isso não pode
     * ser chamado dentro de outras transações.
     * <p><b>Autor....:</b> juliocs</p>
     * <p><b>Criação..:</b> 07/12/2005 13:50:02</p>
     * @return void
     */
    public void persiste( Object objeto ){
        Session session = getSession();
        
        Transaction tx = session.getTransaction();
        try{
            tx.begin();
            session.persist( objeto );
            session.flush();
            tx.commit();
            log.info( "Objeto salvo com Sucesso !! " );
        }catch( Exception e ){
            log.info( "Holve problemas ao inserir o objeto: " + objeto.getClass().getName() );
            e.printStackTrace();
            tx.rollback();
        }finally{
            session.close();
        }
    }
    
    /**
     * Método responsável por fazer alteração do objeto recebido por parametro na base de dados, possui trasação interna por isso não pode
     * ser chamado dentro de outras transações.
     * <p><b>Autor....:</b> juliocs</p>
     * <p><b>Criação..:</b> 07/12/2005 14:00:22</p>
     * @return Object
     */
    public Object update( Object objeto ){
        Session session = getSession();
        Transaction tx = session.getTransaction();
        try{
            tx.begin();
            objeto = session.merge( objeto );
            session.flush();
            tx.commit();
            log.info( "Objeto alterado com Sucesso !! " );
        }catch( Exception e ){
            objeto = null;
            log.info( "Holve problemas ao alterar o objeto: " + objeto.getClass().getName() );
            e.printStackTrace();
            tx.rollback();
        }finally{
            session.close();
        }
        return objeto;
    }
    
    /**
     * Método resposável por fazer a exclusão do objeto recebido por prametro na base de dados, possui trasação interna por isso não pode
     * ser chamado dentro de outras transações.
     * <p><b>Autor....:</b> juliocs</p>
     * <p><b>Criação..:</b> 07/12/2005 14:10:00</p>
     * @return boolean
     */
    public boolean delete( Object objeto ){
        boolean sucesso = false;
        Session session = getSession();
        Transaction tx = session.getTransaction();
        try{
            tx.begin();
            session.delete( objeto );
            session.flush();
            tx.commit();
            log.info( "Objeto excluido com Sucesso !! " );
            sucesso = true;
        }catch( Exception e ){
            log.info( "Holve problemas ao excluir o objeto: " + objeto.getClass().getName() );
            e.printStackTrace();
            tx.rollback();
        }finally{
            session.close();
        }
        return sucesso;
    }
    
    /**
     * Método responsável por buscar o objeto correspondente a classe e id recebido por parametro na base de dados,
     * possui trasação interna por isso não pode ser chamado dentro de outras transações.
     * <p><b>Autor....:</b> juliocs</p>
     * <p><b>Criação..:</b> 07/12/2005 14:15:12</p>
     * @return Object
     */
    public Object load(Class classe, Serializable id ){
        Object objeto = null;
        Session session = getSession();
        Transaction tx = session.getTransaction();
        try{
            tx.begin();
            objeto = session.get( classe, id );
            session.flush();
            tx.commit();
            log.info( "Objeto carregado com Sucesso !! " );
        }catch( Exception e ){
            log.info( "Holve problemas ao buscar o objeto da classe: " + classe.getName() + " com o id " + id );
            e.printStackTrace();
            tx.rollback();
        }finally{
            session.close();
        }
        return objeto;
    }
    
    /**
     * Método responsável por buscar um Coleção de objetos baseado na query recebida por parametro, possui trasação interna por isso não pode
     * ser chamado dentro de outras transações.
     * <p><b>Autor....:</b> juliocs</p>
     * <p><b>Criação..:</b> 07/12/2005 14:20:06</p>
     * @return Collection
     */
    public Collection find(  String hql ){
        Collection objetos = null;
        Session session = getSession();
        Transaction tx = session.getTransaction();
        try{
            tx.begin();
            objetos = session.createQuery( hql ).list();
            session.flush();
            tx.commit();
            log.info( "Query executada com Sucesso !! " );
        }catch( Exception e ){
            log.info( "Holve problemas ao executar a query: " + hql );
            e.printStackTrace();
            tx.rollback();
        }finally{
            session.close();
        }
        return objetos;
    }
    
    public static AnnotationConfiguration getCfg() {
        return cfg;
    }
    
    public static Transaction getTransaction() {
        return getSession().getTransaction();
    }
    
    public Person getPersonByCpf(String cpf) throws Exception {
    	Session session = getSession();
        Transaction tx = session.getTransaction();
		try {
			/*tx.begin();*/
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append(" from Person person ");
			hql.append(" where ");
			hql.append(" person.cpf = "+"'" + cpf + "'"+" ");
			
			
			//paramList.add("'" + cpf + "'");
			/*Collection objetos = session.createQuery(hql.toString()).list();
			Person p= null;
			Iterator<Person> it = objetos.iterator();
			while(it.hasNext()){
				p = it.next();
				System.out.println("Collection contains person " + p.getName());
			}*/
			return (Person) DataAccessUtils.uniqueResult(session.createQuery(hql.toString()).list());
			/*session.flush();
            tx.commit();
            log.info( "Query executada com Sucesso !! " );
            return p;*/
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("PersonServiceDAOHibernate_getPersonByCpfError");
		}
	}
    
    
    public List getListPersonLike(String name) throws Exception {
    	Session session = getSession();
        Transaction tx = session.getTransaction();
		try {
			List paramList = new ArrayList();
			StringBuffer hql = new StringBuffer();
			hql.append("from Person person ");
			hql.append(" where ");
			hql.append(" person.name like "+"'%" + name + "%'"+" ");
			
			return session.createQuery(hql.toString()).list();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("PersonServiceDAOHibernate_getPersonByCpfError");
		}
	}
    
}
