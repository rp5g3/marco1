package com.adapit.portal.services.local;

import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Paper;
import com.adapit.portal.services.FileService;
import com.adapit.portal.services.ImagemService;
import com.adapit.portal.services.remote.RemoteFileService;
import com.workcase.gui.utils.SwingContext;

public class LocalFileService implements FileService{
	private FileService service;

	private static LocalFileService instance;

	private LocalFileService() {
		try {
			XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("localServices.xml"));
			service = (FileService) beanFactory
					.getBean("fileServiceDAOHibernate");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static LocalFileService getInstance() {
		if (instance == null) {
			instance = new LocalFileService();
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
		return service.listBy(name, format);
	}
}
