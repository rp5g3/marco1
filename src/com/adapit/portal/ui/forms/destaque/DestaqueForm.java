package com.adapit.portal.ui.forms.destaque;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.adapit.portal.entidades.Destaque;
import com.adapit.portal.entidades.News;
import com.adapit.portal.entidades.Publication;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.services.remote.RemotePreferenciaService;
import com.adapit.portal.ui.forms.news.NewsListForm;
import com.adapit.portal.ui.forms.publication.PublicationListForm;
import com.adapit.portal.ui.forms.software.SoftwareSolutionListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;
import java.awt.Color;

public class DestaqueForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel destaqueLabel = null;
	private JLabel newsLabel = null;
	private JButton buscarNewsButton = null;
	private JLabel newsResumoLabel = null;
	private JLabel eventoLabel = null;
	private JLabel eventoNameLabel = null;
	private JButton buscarEventoButton = null;
	private JLabel publicationLabel = null;
	private JLabel publicationNameLabel = null;
	private JButton buscarPublicationjButton = null;
	private JLabel softwareLabel = null;
	private JLabel softwareNameLabel = null;
	private JButton buscarSoftwareButton = null;

	/**
	 * This is the default constructor
	 */
	public DestaqueForm() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		softwareNameLabel = new JLabel();
		softwareNameLabel.setBounds(new Rectangle(88, 118, 390, 24));
		softwareNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
		softwareNameLabel.setText("sem software");
		softwareLabel = new JLabel();
		softwareLabel.setBounds(new Rectangle(15, 118, 66, 24));
		softwareLabel.setText("Software:");
		publicationNameLabel = new JLabel();
		publicationNameLabel.setBounds(new Rectangle(88, 91, 390, 24));
		publicationNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
		publicationNameLabel.setText("sem publicacao");
		publicationLabel = new JLabel();
		publicationLabel.setBounds(new Rectangle(15, 91, 66, 24));
		publicationLabel.setText("Publicação:");
		eventoNameLabel = new JLabel();
		eventoNameLabel.setBounds(new Rectangle(88, 64, 390, 24));
		eventoNameLabel.setFont(new Font("Arial", Font.BOLD, 12));
		eventoNameLabel.setText("recurso não disponível");
		eventoLabel = new JLabel();
		eventoLabel.setBounds(new Rectangle(15, 64, 66, 24));
		eventoLabel.setText("Evento:");
		newsResumoLabel = new JLabel();
		newsResumoLabel.setBounds(new Rectangle(88, 37, 390, 24));
		newsResumoLabel.setFont(new Font("Arial", Font.BOLD, 12));
		newsResumoLabel.setText("sem notícia");
		newsLabel = new JLabel();
		newsLabel.setBounds(new Rectangle(15, 37, 66, 24));
		newsLabel.setText("Notícia:");
		destaqueLabel = new JLabel();
		destaqueLabel.setBounds(new Rectangle(13, 8, 546, 16));
		destaqueLabel.setFont(new Font("Arial", Font.BOLD, 14));
		destaqueLabel.setHorizontalAlignment(SwingConstants.CENTER);
		destaqueLabel.setForeground(new Color(102, 102, 255));
		destaqueLabel.setText("Especifique os itens em destaque na página");
		this.setSize(576, 197);
		this.setLayout(null);
		this.add(destaqueLabel, null);
		this.add(newsLabel, null);
		this.add(getBuscarNewsButton(), null);
		this.add(newsResumoLabel, null);
		this.add(eventoLabel, null);
		this.add(eventoNameLabel, null);
		this.add(getBuscarEventoButton(), null);
		this.add(publicationLabel, null);
		this.add(publicationNameLabel, null);
		this.add(getBuscarPublicationjButton(), null);
		this.add(softwareLabel, null);
		this.add(softwareNameLabel, null);
		this.add(getBuscarSoftwareButton(), null);
		this.add(getFecharButton(), null);
	}

	/**
	 * This method initializes buscarNewsButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscarNewsButton() {
		if (buscarNewsButton == null) {
			buscarNewsButton = new JButton();
			buscarNewsButton.setBounds(new Rectangle(484, 37, 75, 24));
			buscarNewsButton.setText("Buscar");
			buscarNewsButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final Dialog jd = new JDialog(AdapitVirtualFrame.getInstance());
					jd.setLayout(new java.awt.BorderLayout());
					jd.setTitle("Pesquise as notícias e selecione uma para colocá-la em destaque");
					final NewsListForm plf = new NewsListForm();
					jd.setSize(plf.getSize().width,plf.getSize().height-10);
					jd.add(plf, java.awt.BorderLayout.CENTER);	
					
					JButton anexar = new JButton("Selecionar como Destaque");
					anexar.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent evt) {
							news = plf.getSelectedNews();
							if(news != null)try {								
								RemotePreferenciaService.getInstance().saveAndMerge(news, publication, software);
							} catch (Exception e) {
								e.printStackTrace();
							}
							editRegister();
							jd.dispose();
						}						
					});
					JPanel jp = new JPanel();
					jp.setLayout(new FlowLayout());
					jp.add(anexar);
					jd.add(jp,BorderLayout.SOUTH);
					jd.setResizable(false);
					jd.setModal(true);
					jd.setLocation(UIUtil.getScreenCenter(jd));
					jd.setVisible(true);
				}
			});
		}
		return buscarNewsButton;
	}

	/**
	 * This method initializes buscarArtigoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscarEventoButton() {
		if (buscarEventoButton == null) {
			buscarEventoButton = new JButton();
			buscarEventoButton.setBounds(new Rectangle(484, 64, 75, 24));
			buscarEventoButton.setEnabled(false);
			buscarEventoButton.setText("Buscar");
		}
		return buscarEventoButton;
	}

	/**
	 * This method initializes buscarPublicationjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscarPublicationjButton() {
		if (buscarPublicationjButton == null) {
			buscarPublicationjButton = new JButton();
			buscarPublicationjButton.setBounds(new Rectangle(484, 91, 75, 24));
			buscarPublicationjButton.setText("Buscar");
			buscarPublicationjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final Dialog jd = new JDialog(AdapitVirtualFrame.getInstance());
					jd.setLayout(new java.awt.BorderLayout());
					jd.setTitle("Pesquise as publicações e selecione uma para colocá-la em destaque");
					final PublicationListForm plf = new PublicationListForm();
					jd.setSize(plf.getSize().width,plf.getSize().height-10);
					jd.add(plf, java.awt.BorderLayout.CENTER);	
					
					JButton anexar = new JButton("Selecionar como Destaque");
					anexar.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent evt) {
							publication = plf.getSelectedPublication();
							if(publication != null)try {								
								RemotePreferenciaService.getInstance().saveAndMerge(news, publication, software);
							} catch (Exception e) {
								e.printStackTrace();
							}
							editRegister();
							jd.dispose();
						}						
					});
					JPanel jp = new JPanel();
					jp.setLayout(new FlowLayout());
					jp.add(anexar);
					jd.add(jp,BorderLayout.SOUTH);
					jd.setResizable(false);
					jd.setModal(true);
					jd.setLocation(UIUtil.getScreenCenter(jd));
					jd.setVisible(true);
				}
			});
		}
		return buscarPublicationjButton;
	}

	/**
	 * This method initializes buscarSoftwareButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscarSoftwareButton() {
		if (buscarSoftwareButton == null) {
			buscarSoftwareButton = new JButton();
			buscarSoftwareButton.setBounds(new Rectangle(484, 118, 75, 24));
			buscarSoftwareButton.setText("Buscar");
			buscarSoftwareButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final Dialog jd = new JDialog(AdapitVirtualFrame.getInstance());
					jd.setLayout(new java.awt.BorderLayout());
					jd.setTitle("Pesquise os sistemas e selecione um para colocá-lo em destaque");
					final SoftwareSolutionListForm plf = new SoftwareSolutionListForm();
					jd.setSize(plf.getSize().width,plf.getSize().height-10);
					jd.add(plf, java.awt.BorderLayout.CENTER);	
					
					JButton anexar = new JButton("Selecionar como Destaque");
					anexar.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent evt) {
							software = plf.getSelectedSolução();
							if(software != null)try {								
								RemotePreferenciaService.getInstance().saveAndMerge(news, publication, software);
							} catch (Exception e) {
								e.printStackTrace();
							}
							editRegister();
							jd.dispose();
						}						
					});
					JPanel jp = new JPanel();
					jp.setLayout(new FlowLayout());
					jp.add(anexar);
					jd.add(jp,BorderLayout.SOUTH);
					jd.setResizable(false);
					jd.setModal(true);
					jd.setLocation(UIUtil.getScreenCenter(jd));
					jd.setVisible(true);
				}
			});
		}
		return buscarSoftwareButton;
	}
	
	private News news;
	private Publication publication;
	private SoftwareSolution software;
	private JButton fecharButton = null;
	
	public void editRegister(){
		try {
			Destaque d = RemotePreferenciaService.getInstance().loadDestaque();
			if(d.getNews() != null){
				newsResumoLabel.setText(d.getNews().getTitulo());
			}
			if(d.getPublication() != null){
				publicationNameLabel.setText(d.getPublication().getTitulo());
			}
			if(d.getSoftware() != null){
				softwareNameLabel.setText(d.getSoftware().getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes fecharButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getFecharButton() {
		if (fecharButton == null) {
			fecharButton = new JButton();
			fecharButton.setBounds(new Rectangle(235, 162, 85, 24));
			fecharButton.setText("Fechar");
		}
		return fecharButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
