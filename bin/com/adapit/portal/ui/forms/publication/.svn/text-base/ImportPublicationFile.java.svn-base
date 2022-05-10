package com.adapit.portal.ui.forms.publication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.Publication;
import com.adapit.portal.services.remote.RemoteFileService;
import com.adapit.portal.ui.forms.file.FileFilterPanel;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.ResourceMessage;
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class ImportPublicationFile implements ActionListener {
	private static String currDir=null;
	private String filePath;
	//private DesignFileListener observer;
	private ResourceMessage messages = ResourceMessage.getInstance();
	private Publication publication;
	private static ImportPublicationFile instance;
	
	public static ImportPublicationFile getInstance(){
		if (instance == null)
			instance = new ImportPublicationFile();
		return instance;
	}
	private ImportPublicationFile(Publication leilao){
		this.publication = leilao;
	}
	private ImportPublicationFile(){
	}

	private static JFileChooser fc;
	
	public void actionPerformed(ActionEvent e) {
		if (filePath == null) {
					if (fc == null){
						fc = new JFileChooser();
						fc.setDialogType(JFileChooser.OPEN_DIALOG);
						fc.setApproveButtonText("Abrir");
						fc.setFileFilter(new DocumentFilter());
						fc.setDialogTitle("Buscar Arquivo de Publicação");
					}
					if (currDir != null)
						fc.setCurrentDirectory(new File(currDir));
					int returnVal = fc.showOpenDialog(AdapitVirtualFrame.getInstance());

					currDir = fc.getCurrentDirectory().getAbsolutePath();

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						//observer.save(file);
						Arquivo arq = new Arquivo();						
						try {
							arq.setFile(file);
							arq = RemoteFileService.getInstance().save(arq,publication);
							FileFilterPanel.saveOnServerDir(arq, null);
							/*String dir = JOptionPane.showInputDialog(null,"Informe o diretório",
									"/"+SpringResourceMessage.getInstance().getMessage("filesurl"));
							if(dir != null){
								dir = (String) RemoteFileService.getInstance().saveFileToDirectory(arq, dir);
								JOptionPane.showMessageDialog(null, "O arquivo foi salvo com sucesso no diretório:"
										+'\n'+dir);
							}*/
							publication.setEditalFile(arq);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
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
			if (ext.equalsIgnoreCase("pdf")) return true;
			if (ext.equalsIgnoreCase("xsl")) return true;
			if (ext.equalsIgnoreCase("rtf")) return true;
			if (ext.equalsIgnoreCase("ppt")) return true;
			if (ext.equalsIgnoreCase("tux")) return true;
			return false;
		}

		@Override
		public String getDescription() {
			return "Documentos [doc,pdf,xsl,rtf,ppt,tux]";			
		}
		
	}

	public Publication getPublication() {
		return publication;
	}
	public void setPublication(Publication leilao) {
		this.publication = leilao;
	}

}
