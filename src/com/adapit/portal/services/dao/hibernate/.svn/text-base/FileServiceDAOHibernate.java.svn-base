package com.adapit.portal.services.dao.hibernate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Paper;
import com.adapit.portal.services.FileService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.util.global.GlobalVariables;
import com.workcase.hibernate.GenericDAO;
import com.workcase.hibernate.GenericDAOHibernate;

/**
 * @spring.bean id="fileServiceDAOHibernate" singleton="true"
 * @@org.springframework.transaction.interceptor.DefaultTransactionAttribute(propagationBehaviorName="PROPAGATION_REQUIRED")
 */
@SuppressWarnings({"unchecked"})
public class FileServiceDAOHibernate extends GenericDAOHibernate implements
		FileService, GenericDAO {
	
	private static final  String fileEntityName = Arquivo.class.getSimpleName();

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;


	@Override
	public Arquivo delete(Arquivo file) throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();	
			s.delete(file);
			s.getTransaction().commit();			
			return file;
		} catch (Exception e1) {
			if (s != null) s.getTransaction().rollback();
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null) s.close();
		}
	}
	


	@Override
	public Arquivo save(final Arquivo file)
			throws Exception {
		if (file.getFullBytes() == null)
			throw new Exception("File cannon have null bytes");
		Session s = null;

		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			//System.out.println("Antes Formato " + file.getFormat());
			if (file.getFormat() == null || file.getFormat().equals("")){
				int index = file.getPath().indexOf(".");		
				String format = file.getPath().substring(index+1);
				//System.out.println("Formato " + format);
				file.setFormat(format);
			}
			s.save(file);
			//String serverDir = GlobalVariables.DocumentServerFileDir;
			
			/*File f = new File(serverDir+(file.getName().equals("")?"file":file.getName()));
			try {				
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(file.getFullBytes());
				fos.close();
		    } catch (UnsupportedEncodingException ex) {
		    	ex.printStackTrace();
		    } catch (IOException ex) {
		    	ex.printStackTrace();
		    }
		    finally{
		    	System.runFinalization();
		    }*/
			s.getTransaction().commit();
			// return file;
		} catch (Exception e1) {
			if (s != null)
				s.getTransaction().rollback();
			e1.printStackTrace();
		} finally {
			if (s != null)
				s.close();
		}

		return file;
	}

	@Override
	public Arquivo save(final Arquivo newFile, ComercialSolution sd) throws Exception {
		if (newFile.getFullBytes() == null)
			throw new Exception("File cannot have null bytes");

		System.gc();
		Session s = null;

		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.refresh(sd);
			if (sd.getArquivos() == null)
				sd.setArquivos(new ArrayList());
			sd.getArquivos().add(newFile);
			
			s.merge(sd);
			
			s.getTransaction().commit();			
		} catch (Exception e1) {
			if (s != null)
				s.getTransaction().rollback();
			e1.printStackTrace();
		} finally {
			if (s != null)
				s.close();
			System.gc();
		}

		return newFile;
	}
	
	@Override
	public Arquivo unmerge(final Arquivo newFile, ComercialSolution sd) throws Exception {
		if (newFile.getFullBytes() == null)
			throw new Exception("File cannot have null bytes");

		System.gc();
		Session s = null;

		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.refresh(sd);
			
			sd.getArquivos().remove(newFile);
			
			s.merge(sd);
			
			s.getTransaction().commit();			
		} catch (Exception e1) {
			if (s != null)
				s.getTransaction().rollback();
			e1.printStackTrace();
		} finally {
			if (s != null)
				s.close();
			System.gc();
		}

		return newFile;
	}

	@Override
	public Arquivo update(Arquivo file) throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.update(file);
			s.getTransaction().commit();			
			return file;
		} catch (Exception e1) {
			if (s != null) s.getTransaction().rollback();
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null) s.close();
		}
	}

	@Override
	public byte[] getFullBytesFromFile(int id) throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select file.fullBytes from "+fileEntityName+" file where file.id="+id;
			return (byte[]) s.createQuery(query).uniqueResult();
			
		} catch (HibernateException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}
	}

	@Override
	public Arquivo getArquivoById(int id) throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Arquivo arq = (Arquivo) s.createQuery("select arq from Arquivo arq where arq.id=:id").setParameter("id", id).uniqueResult();		
			arq.getPath();
			arq.getFullBytes();
			arq.getPath();
			arq.getName();
			return arq;
		} catch (Exception e1) {
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null) s.close();
		}
	}

	
	@Override
	public Arquivo deleteFromPaper(Arquivo file) throws Exception {
		Session s = null;
		removeDependencies(file);
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();	
			s.delete(file);
			s.getTransaction().commit();			
			return file;
		} catch (Exception e1) {
			if (s != null) s.getTransaction().rollback();
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null) s.close();
		}
	}
	
	
	private void removeDependencies(Arquivo file) throws Exception{
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.createQuery("update Paper set editalFile=null where editalFile.id=:id")
			.setParameter("id",file.getId()).executeUpdate();			
			s.refresh(file);			
			s.getTransaction().commit();	
		} catch (Exception e1) {
			if (s != null) s.getTransaction().rollback();
			e1.printStackTrace();
			throw e1;
		}finally{
			if (s != null) s.close();
		}
	}

	@Override	
	public Arquivo getPaperFile(int idPaper)
			throws Exception {
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select l.editalFile.id, l.editalFile.name," +
					" l.editalFile.path, l.editalFile.format" +					
					" from Paper l " +
					" where l.id=:id ";			
			Object[] objs = (Object[]) s.createQuery(query)
			.setParameter("id",idPaper).uniqueResult();
			if (objs != null){
				Arquivo file = new Arquivo();
				file.setId((Integer) objs[0]);
				file.setName((String)objs[1]);
				file.setPath((String)objs[2]);
				file.setFormat((String)objs[3]);				
				return file;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (s != null && s.isOpen())
				s.close();
		}
	}
	
	@Override
	public Arquivo save(Arquivo newFile, Paper sd) throws Exception {
		if (newFile.getFullBytes() == null)
			throw new Exception("File cannot have null bytes");

		System.gc();
		Session s = null;

		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			/*s.refresh(sd);
			if (sd.getArquivos() == null)
				sd.setArquivos(new ArrayList());
			sd.getArquivos().add(newFile);*/
			if (newFile.getFormat() == null){
				int index = newFile.getPath().indexOf(".");		
				String format = newFile.getPath().substring(index+1);
				newFile.setFormat(format);
			}
			sd.setEditalFile(newFile);
			s.merge(sd);
			
			s.getTransaction().commit();			
		} catch (Exception e1) {
			if (s != null)
				s.getTransaction().rollback();
			e1.printStackTrace();
		} finally {
			if (s != null)
				s.close();
			System.gc();
		}

		return newFile;
	}

	@Override
	public Arquivo unmerge(Arquivo newFile, Paper sd) throws Exception {
		if (newFile.getFullBytes() == null)
			throw new Exception("File cannot have null bytes");

		System.gc();
		Session s = null;

		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.refresh(sd);
			
			sd.setEditalFile(null);
			
			s.merge(sd);
			
			s.getTransaction().commit();			
		} catch (Exception e1) {
			if (s != null)
				s.getTransaction().rollback();
			e1.printStackTrace();
		} finally {
			if (s != null)
				s.close();
			System.gc();
		}

		return newFile;
	}
	
	@Override
	public Object saveFileToDirectory(byte[] bytes, String fileName, String dir) throws Exception{
		//File f2 = new File(".");
		//String serverDir = f2.getCanonicalPath();
		String serverDir = GlobalVariables.getWebappsDir();
		
		//System.out.println("Salvando como: "+serverDir+dir+"/"+fileName);
		File f = new File(serverDir+dir);
		f.mkdirs();
		File outputFile = new File(serverDir+dir+"/"+fileName);
		
		try {	
			byte[] ba = bytes;
			try {
				FileOutputStream fos = new FileOutputStream(outputFile);				
				fos.write(ba);
				fos.close();
		    } catch (UnsupportedEncodingException ex) {
		    	ex.printStackTrace();
		    } catch (IOException ex) {
		    	ex.printStackTrace();
		    }
		    finally{
		    	System.gc();
		    }
		    
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return outputFile.getAbsolutePath();
	}
	
	@Override
	public Object saveFileToDirectory(Arquivo arquivo, String dir) throws Exception{
		String serverDir = GlobalVariables.getWebappsDir();
		//System.out.println("Salvando como: "+serverDir+dir+"/"+arquivo.getName());
		File f = new File(serverDir+dir);
		f.mkdirs();
		File outputFile = new File(serverDir+dir+"/"+arquivo.getName());
		
		try {	
			byte[] ba = getFullBytesFromFile(arquivo.getId());
			try {
				FileOutputStream fos = new FileOutputStream(outputFile);				
				fos.write(ba);
				fos.close();
		    } catch (UnsupportedEncodingException ex) {
		    	ex.printStackTrace();
		    } catch (IOException ex) {
		    	ex.printStackTrace();
		    }
		    finally{
		    	System.gc();
		    }
		    
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.refresh(arquivo);
			arquivo.setLocalPath(dir+"/"+arquivo.getName());
			s.update(arquivo);
			
			s.getTransaction().commit();			
		} catch (Exception e1) {
			if (s != null)
				s.getTransaction().rollback();
			e1.printStackTrace();
		} finally {
			if (s != null)
				s.close();
			System.gc();
		}
		
		return outputFile.getAbsolutePath();
	}
	
	@Override
	public List<Arquivo> listBy(String name, String format) throws Exception{
		Session s = null;
		List<Arquivo> arr = new ArrayList<Arquivo>();
		try {
			s = LocalServicesUtility.getInstance().openSession();
			String query = "select arq.id, arq.name, arq.format, arq.path, arq.localPath from Arquivo arq ";
			boolean whereused=false;
			if(name != null){
				query+=" where lower(arq.name) like :name";
				whereused=true;
			}if(format != null){
				if(!whereused)
					query+=" where ";
				else query+=" and lower(arq.format) like :format";
			}
			Query q = s.createQuery(query);
			if(name != null)
				q.setParameter("name", "%"+name.toLowerCase()+"%");
			if(format != null)
				q.setParameter("format", "%"+format.toLowerCase()+"%");
			
			List<Object[]> list = q.list();	
			for(Object[] objs: list){
				Arquivo arq = new Arquivo();
				arq.setId((Integer)objs[0]);
				arq.setName((String)objs[1]);
				arq.setFormat((String)objs[2]);
				arq.setPath((String)objs[3]);
				arq.setLocalPath((String) objs[4]);
				arr.add(arq);
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (s != null)
				s.close();
			
		}
		return arr;
	}
	
	public static Object saveFileToDirectory(byte[] bytes, String fileName) throws Exception{
		
		File outputFile = new File(fileName);
		
		try {	
			byte[] ba = bytes;
			try {
				FileOutputStream fos = new FileOutputStream(outputFile);				
				fos.write(ba);
				fos.close();
		    } catch (UnsupportedEncodingException ex) {
		    	ex.printStackTrace();
		    } catch (IOException ex) {
		    	ex.printStackTrace();
		    }
		    finally{
		    	System.gc();
		    }
		    
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		return outputFile.getAbsolutePath();
	}
}
