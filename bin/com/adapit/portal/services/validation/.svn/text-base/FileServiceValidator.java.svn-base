package com.adapit.portal.services.validation;

import java.util.List;

import org.springmodules.validation.commons.DefaultBeanValidator;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Paper;
import com.adapit.portal.services.FileService;

/**
 * @spring.bean id="fileService" singleton="true"
 */
public class FileServiceValidator extends AbstractValidator implements FileService {


	private DefaultBeanValidator validator;

	private FileService service;

	public void setValidator(DefaultBeanValidator validator) {
		this.validator = validator;
	}

	public DefaultBeanValidator getValidator() {
		return this.validator;
	}

	public void setService(FileService imService) {
		this.service = imService;
	}

	/**
	 * @spring.property ref="fileServiceDAOHibernate" singleton="true"
	 */
	public FileService getService() {
		return this.service;
	}

	public FileServiceValidator() {
		super();
		setName("fileService");
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
}
