package com.adapit.portal.services.remote;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

import org.springframework.beans.factory.xml.XmlBeanFactory;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Paper;
import com.adapit.portal.services.FileService;
import com.workcase.gui.utils.SwingContext;

public class RemoteFileService implements FileService{
	private FileService service;

	private static RemoteFileService instance;

	private RemoteFileService() {
		try {
			XmlBeanFactory beanFactory = SwingContext.getInstance()
					.getBeanFactory();
			service = (FileService) beanFactory
					.getBean("remoteFileServiceHttpInvokerProxy");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static RemoteFileService getInstance() {
		if (instance == null) {
			instance = new RemoteFileService();
		}
		return instance;
	}
	
	@Override
	public Arquivo delete(Arquivo file) throws Exception {
		return service.delete(file);
	}


	@Override
	public byte[] getFullBytesFromFile(int id) throws Exception {
		return service.getFullBytesFromFile(id);
	}

	

	@Override
	public Arquivo save(Arquivo file) throws Exception {
		return service.save(file);
	}

	@Override
	public Arquivo update(Arquivo file) throws Exception {
		return service.update(file);
	}

	@Override
	public Arquivo getArquivoById(int id) throws Exception {
		return service.getArquivoById(id);
	}
	
	@Override
	public Arquivo save(Arquivo file,
			ComercialSolution sd) throws Exception {
		return service.save(file, sd);
	}

	@Override
	public Arquivo unmerge(Arquivo newFile, ComercialSolution sd)
			throws Exception {
		return service.unmerge(newFile, sd);
	}

	@Override
	public Arquivo deleteFromPaper(Arquivo file) throws Exception {
		return service.deleteFromPaper(file);
	}

	@Override
	public Arquivo getPaperFile(int idPaper) throws Exception {
		return service.getPaperFile(idPaper);
	}

	@Override
	public Arquivo save(Arquivo file, Paper sd) throws Exception {
		return service.save(file, sd);
	}

	@Override
	public Arquivo unmerge(Arquivo newFile, Paper sd) throws Exception {
		return service.unmerge(newFile, sd);
	}

	@Override
	public Object saveFileToDirectory(byte[] bytes, String filename, String dir)
			throws Exception {
		return service.saveFileToDirectory(bytes, filename, dir);
	}

	@Override
	public Object saveFileToDirectory(Arquivo arquivo, String dir)
			throws Exception {
		return service.saveFileToDirectory(arquivo, dir);
	}

	@Override
	public List<Arquivo> listBy(String name, String format) throws Exception{
		return service.listBy(name,format);
	}
	
	
	public Object saveFileToLocalDirectory(Arquivo arquivo, JComponent jc) throws Exception{
		File f2 = new File(".");
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(f2);
		jfc.setMultiSelectionEnabled(false);
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int ret=jfc.showOpenDialog(jc);
		if(ret != JFileChooser.APPROVE_OPTION) return "canceled";
		
		File f = jfc.getCurrentDirectory();
		
		File outputFile = new File(f.getAbsolutePath()+"/"+arquivo.getName());
		
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
		
		return outputFile.getAbsolutePath();
	}
}
