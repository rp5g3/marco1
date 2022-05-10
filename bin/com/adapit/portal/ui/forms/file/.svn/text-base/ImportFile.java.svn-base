package com.adapit.portal.ui.forms.file;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.services.remote.RemoteFileService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.ResourceMessage;

public class ImportFile implements ActionListener {
	private static String currDir=null;
	private String filePath;
	//private DesignFileListener observer;
	private ResourceMessage messages = ResourceMessage.getInstance();
	private static ImportFile instance;
	
	private Arquivo arquivo;
	
	public Arquivo getArquivo(){
		return arquivo;
	}
	
	public static ImportFile getInstance(){
		if (instance == null)
			instance = new ImportFile();
		return instance;
	}
	
	private ImportFile(){
	}

	private static JFileChooser fc;
	
	enum Status{Success,Fail,Cancel};
	
	private Status status;
	
	public void actionPerformed(ActionEvent e) {
		if (filePath == null) {
					if (fc == null){
						fc = new JFileChooser();
						fc.setDialogType(JFileChooser.OPEN_DIALOG);
						fc.setApproveButtonText("Abrir");
						fc.setFileFilter(new DocumentFilter());
						fc.setDialogTitle("Buscar Arquivo");
					}
					if (currDir != null)
						fc.setCurrentDirectory(new File(currDir));
					int returnVal = fc.showOpenDialog(AdapitVirtualFrame.getInstance());
					
					currDir = fc.getCurrentDirectory().getAbsolutePath();

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						System.out.println("Salvando o arquivo");
						File file = fc.getSelectedFile();
						System.out.println(file.getAbsolutePath());
						Arquivo arq = new Arquivo();						
						try {
							arq.setFile(file);
							arq = RemoteFileService.getInstance().save(arq);
							System.out.println(arq.getId());
							status = Status.Success;
							this.arquivo=arq;
						} catch (Exception e1) {
							e1.printStackTrace();
							status = Status.Fail;
						}
					}else{
						status = Status.Cancel;
					}
					fc = null;					
					System.runFinalization();					

		}		
	}
	
	public void actionPerformed(ActionEvent e, Arquivo arq) {
		if (filePath == null) {
					if (fc == null){
						fc = new JFileChooser();
						fc.setDialogType(JFileChooser.OPEN_DIALOG);
						fc.setApproveButtonText("Abrir");
						fc.setFileFilter(new DocumentFilter());
						fc.setDialogTitle("Buscar Arquivo");
					}
					if (currDir != null)
						fc.setCurrentDirectory(new File(currDir));
					int returnVal = fc.showOpenDialog(AdapitVirtualFrame.getInstance());
					
					currDir = fc.getCurrentDirectory().getAbsolutePath();

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						System.out.println("Atualizando o arquivo " + arq.getId());
						File file = fc.getSelectedFile();
						System.out.println(file.getAbsolutePath());
						try {
							arq.setFile(file);
							arq = RemoteFileService.getInstance().update(arq);
							System.out.println(arq.getId());
							status = Status.Success;
							this.arquivo=arq;
						} catch (Exception e1) {
							e1.printStackTrace();
							status = Status.Fail;
						}
					}else{
						status = Status.Cancel;
					}
					fc = null;					
					System.runFinalization();					

		}		
	}
	
	private class DocumentFilter extends FileFilter{

		public boolean accept(File f) {
			if (f.isDirectory()) return true;
			int index = f.getName().indexOf(".");
			String ext = f.getName().substring(index+1,f.getName().length());
			if (ext.equalsIgnoreCase("doc")) return true;
			if (ext.equalsIgnoreCase("docx")) return true;
			if (ext.equalsIgnoreCase("pdf")) return true;
			if (ext.equalsIgnoreCase("xsl")) return true;
			if (ext.equalsIgnoreCase("rtf")) return true;
			if (ext.equalsIgnoreCase("ppt")) return true;
			if (ext.equalsIgnoreCase("tux")) return true;
			return false;
		}

		@Override
		public String getDescription() {
			return "Documentos [doc,docx,pdf,xsl,rtf,ppt,tux]";			
		}
		
	}

	public Status getStatus() {
		return status;
	}

}
