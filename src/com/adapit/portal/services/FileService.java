package com.adapit.portal.services;

import java.util.List;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Paper;



public interface FileService {
	
	public Arquivo save(Arquivo file) throws Exception;
	
	public Arquivo update(Arquivo file) throws Exception;
	
	public Arquivo delete(Arquivo file) throws Exception;
	
	public byte[] getFullBytesFromFile(int id) throws Exception;
	
	public Arquivo getArquivoById(int id) throws Exception;
	
	public Arquivo save(Arquivo file,ComercialSolution sd) throws Exception;

	public Arquivo unmerge(Arquivo newFile, ComercialSolution sd) throws Exception;

	public Arquivo deleteFromPaper(Arquivo file) throws Exception;

	public Arquivo getPaperFile(int idPaper) throws Exception;
	
	public Arquivo save(Arquivo file,Paper sd) throws Exception;

	public Arquivo unmerge(Arquivo newFile, Paper sd) throws Exception;

	/**
	 * Salva os bytes de um arquivo em um diretório do servidor de aplicação
	 * @param bytes o conteúdo do arquivo
	 * @param filename o nome do arquivo
	 * @param dir o diretório do servidor para colocar o arquivo ex: webapps/myapp/arquivos/
	 * @return um objeto confirmando a operação
	 * @throws Exception
	 */
	public Object saveFileToDirectory(byte[] bytes, String filename, String dir) throws Exception;

	/**
	 * Salva o conteúdo do arquivo em um diretório do servidor de aplicação
	 * @param bytes o conteúdo do arquivo
	 * @param filename o nome do arquivo
	 * @param dir o diretório do servidor para colocar o arquivo ex: webapps/myapp/arquivos/
	 * @return um objeto confirmando a operação
	 * @throws Exception
	 */
	public Object saveFileToDirectory(Arquivo arquivo, String dir) throws Exception;

	public List<Arquivo> listBy(String name, String format) throws Exception;
}
