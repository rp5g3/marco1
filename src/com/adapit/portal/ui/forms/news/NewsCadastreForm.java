package com.adapit.portal.ui.forms.news;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

import com.adapit.portal.entidades.CssDefinition;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.News;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.services.remote.RemoteImagemService;
import com.adapit.portal.services.remote.RemoteNewsService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.forms.HtmlEditorEventListener;
import com.adapit.portal.ui.forms.HtmlEditorPanel;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.ImageFileChooser;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;


@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class NewsCadastreForm extends JPanel {
	
	private JTextPane descricaoTextArea;
	
	private SwingBinder binder = new SwingBinder();
	
	private News news = new News();  //  @jve:decl-index=0:
	
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();  //  @jve:decl-index=0:
	
	private boolean processFocus = true;
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel descricaoTextAreaLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JPanel cadastreButtonsPanel;
	
	private JButton atualizarButton;
	
	private JButton novoButton;
	
	private JButton listarNewssButton;
	
	private JTabbedPane contentTabbedPane;
	private JPanel contentPanel;
	
	private int scale=800;
	private int smallScale=80;
	
	public NewsCadastreForm(){
		initialize();
	}
	
	private void initialize(){
		setSize(new Dimension(700, 600));
		setLocation(new java.awt.Point(0,0));
		setLayout(new BorderLayout());		
		this.add(getTabbedPane(), BorderLayout.CENTER);
		this.add(getErrorPanel(), BorderLayout.SOUTH);		
		
		newRegister();
		
	}
	
	private JPanel newsContentPanel;
	
	private JPanel getNewsContentPanel(){
		if (newsContentPanel == null){
			newsContentPanel = new JPanel();
			newsContentPanel.setLayout(new BorderLayout());
			newsContentPanel.add(getContentTabbedPane(), BorderLayout.CENTER);
			newsContentPanel.add(getCadastreButtonsPanel(), BorderLayout.SOUTH);
		}
		return newsContentPanel;
	}
		
	private JTabbedPane getContentTabbedPane(){
		if (contentTabbedPane == null){
			contentTabbedPane = new JTabbedPane();	
			contentTabbedPane.setSize(466, 348);
			contentTabbedPane.setLocation(5,5);
			contentTabbedPane.add(getContentPanel(),"Resumo");
			contentTabbedPane.add(/*getContentDescricaoPanel()*/getDescricaoEditor(),"Descrição");
			//contentTabbedPane.setTabLayoutPolicy(JTabbedPane.BOTTOM_ALIGNMENT);
			
			
		}
		return contentTabbedPane;
	}
	

	 private JPanel getContentPanel(){
		if (contentPanel == null){
			resumoLabel = new JLabel();
			resumoLabel.setBounds(new Rectangle(15, 60, 659, 22));
			resumoLabel.setText("Resumo Que Aparece na Abertura do Site:");
			tituloLabel = new JLabel();
			tituloLabel.setBounds(new Rectangle(15, 5, 112, 22));
			tituloLabel.setText("Título/Nome:");
			contentPanel = new JPanel();
			contentPanel.setLayout(null);
			
			contentPanel.add(tituloLabel, null);
			contentPanel.add(getTituloTextField(), null);
			contentPanel.add(resumoLabel, null);
			contentPanel.add(getResumoNewsEditor(), null);
			
			dtLeilaoLabel= new JLabel("Data da Publicação:");
			dtLeilaoLabel.setSize(113, 22);
			dtLeilaoLabel.setLocation(15, 30);
			contentPanel.add(dtLeilaoLabel);

			contentPanel.add(getDataPublicacao(), null);
			this.setErrorIcon(false);
		}
		return contentPanel;
	}
	private JLabel dtLeilaoLabel;
/*	private JPanel contentDescricaoPanel; 
	 
	private JPanel getContentDescricaoPanel() {
		if (contentDescricaoPanel == null) {
			contentDescricaoPanel = new JPanel();
			contentDescricaoPanel.setLayout(null);
			contentDescricaoPanel.add(getDescricaoScrollPane());
			contentDescricaoPanel.add(getDescricaoTextAreaLabel());			

		}
		return contentDescricaoPanel;
	}*/

	
/*	protected JScrollPane descricaoScrollPane;
	
	protected JScrollPane getDescricaoScrollPane(){
		if(descricaoScrollPane == null){
			descricaoScrollPane = new JScrollPane();
			descricaoScrollPane.setSize(new Dimension(444, 202));
			descricaoScrollPane.setLocation(new Point(17, 32));
			descricaoScrollPane.add(getDescricaoTextArea());
			descricaoScrollPane.setViewportView(getDescricaoTextArea());
			return descricaoScrollPane;
		}
		return descricaoScrollPane;
	}*/
	
	private HtmlEditorPanel descricaoEditor;
	private HtmlEditorPanel getDescricaoEditor() {
		if (descricaoEditor == null) {
			getDescricaoTextArea();
			descricaoEditor = new HtmlEditorPanel(
					(JTextComponent)descricaoTextArea,descricaoEventListener,
					AdapitVirtualFrame.getInstance().getCss());
			descricaoEditor.setBounds(new Rectangle(17, 32, 444, 202));
			
		}
		return descricaoEditor;
	}

	private HtmlEditorEventListener descricaoEventListener = null;
	//descricaoEventListener = new HtmlEditorEventListener(descricaoTextArea,AdapitVirtualFrame.getInstance(),"Texto da Notícia");
	
	
	@SuppressWarnings("unchecked")
	protected JComponent getDescricaoTextArea(){
		if(descricaoTextArea == null){
			descricaoTextArea = new JTextPane();
			descricaoEventListener = new HtmlEditorEventListener(descricaoTextArea,AdapitVirtualFrame.getInstance(),"Texto da Notícia");
			this.binder.addBindProperty(this.news, this.descricaoTextArea, "descricao");
			
			this.hashComps.put("descricao", this.descricaoTextArea);
			JWarningComponent warn = new JWarningComponent( this.descricaoTextArea);
			return warn;
		}
		return descricaoTextArea;
	}
	
	public News validateNewsForm() throws Exception{
		setErrorIcon(false);
		try {
			binder.bind(news);
		} catch (RuntimeException e) {
			e.printStackTrace();
			
		}		
		
		if (!validateNewsBean()) throw new Exception("Bean Not Validated!");
		
		
		return news;
	}
	
	public News cadastreNews() throws Exception{
		News p = validateNewsForm();
		p = RemoteNewsService.getInstance().saveOrUpdate(p);
		return p;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean validateNewsBean() throws Exception{
		try {
			getErrorPanel().removeAllElements();
			try {
				if (processFocus) {
					if (UIUtil.processFocus(this)) {
						processFocus = false;
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Validate validate = new Validate();
			Map errors = validate.validate(this.news, "news");
			/*if (errors == null && news.getAvaliacao() > 0.0f) return true;*/
			if (errors == null) return true;
			if (errors != null){
				Map.Entry[] entrys = Validate.getOrder(errors, this.hashComps);
				String name;
				JComponent comp;
				for (int i = 0; i < entrys.length; i++) {
					name = (String) entrys[i].getKey();
					comp = (JComponent) this.hashComps.get(name);
					if (comp != null) {
						comp.firePropertyChange("warnBorder", false, true);
						Object[] obj = (Object[]) entrys[i].getValue();
						String msg = (String) obj[0];
						if (obj[1] == null) {
							try{
								getErrorPanel().addError(messages.getMessage(msg),comp);
								comp.setToolTipText(messages.getMessage(msg));
							}catch(Exception e){
								e.printStackTrace();
							}
						} else {
							List args = (List) obj[1];
							Object[][] params = new Object[args.size()][2];
							for(int j=0; j < args.size(); j++) {
							   String key = (String) args.get(j);
							   params[j][0] = key;
							   params[j][1] = null;
							}
							try{
								getErrorPanel().addError(messages.getMessage(msg, params),comp);
								comp.setToolTipText(messages.getMessage(msg, params));
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
			}
			
			
			getErrorPanel().updateErrorList();
			getErrorPanel().setVisible(true);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public void newRegister(){
		//Nunca definir um novo objeto entidade!!!
		
		news.setId(0);
		news.setDescricao(null);

		news.setImagens(new ArrayList<Imagem>());
		news.setResumo(null);
		news.setTitulo(null);
		news.setDataPublicacao(null);
		
		imgIndex=0;
		updateImage();
		binder.reverseBind(this.news);
		
		this.setErrorIcon(false);
		updateUI();
	}
	@SuppressWarnings("unchecked")
	public void editRegister(News objNews ){
		try {			
			objNews = RemoteNewsService.getInstance().
				getNewsById(objNews.getId());
			List imagens = RemoteNewsService.getInstance().
			getImagensByNewsId(objNews.getId());			
			objNews.setImagens(imagens);			
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
		
		try{			
			this.news.setId(objNews.getId());			
			this.news.setDescricao(objNews.getDescricao());			
			this.news.setImagens(objNews.getImagens());
			this.news.setResumo(objNews.getResumo());
			this.news.setTitulo(objNews.getTitulo());
			this.news.setDataPublicacao(objNews.getDataPublicacao());			
		}catch(Exception e){
			e.printStackTrace();
		}
			
		
		if (news.getImagens() != null){
			try {
				news.getImagens().iterator().hasNext();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}	
			imgIndex=0;
			updateImage();
		}
		
		this.tituloTextField.setText(news.getTitulo());
		this.resumoTextArea.setText(news.getResumo());
				
		binder.reverseBind(this.news);
		this.setErrorIcon(false);
		
		getDescricaoEditor().updateByHtml();
		getResumoNewsEditor().updateByHtml();
	}
	
	

	public LogErrorPanel getErrorPanel(){
		if (logErrorPanel == null){
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setBounds(new Rectangle(0, 10, 600, 52));
		}
		return logErrorPanel;
	}
	
	public void setErrorIcon(boolean bool ){

		try {
			this.descricaoTextArea.firePropertyChange("warnBorder", !bool, bool);			
			this.getErrorPanel().setVisible(false);
		} catch (RuntimeException e) {
			
		}
	}
	
	protected JLabel getDescricaoTextAreaLabel(){

		if(descricaoTextAreaLabel == null){
			descricaoTextAreaLabel = new JLabel(messages.getMessage("Descrição"));
			descricaoTextAreaLabel.setSize(new Dimension(90, 20));
			descricaoTextAreaLabel.setLocation(new Point(16, 3));
			descricaoTextAreaLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return descricaoTextAreaLabel;
	}

	
	protected JPanel getCadastreButtonsPanel(){

		if(cadastreButtonsPanel == null){
			cadastreButtonsPanel = new JPanel();
			cadastreButtonsPanel.setLayout(new java.awt.FlowLayout());
			cadastreButtonsPanel.setBounds(new Rectangle(6, 356, 463, 50));
			cadastreButtonsPanel.add(getAtualizarButton());
			cadastreButtonsPanel.add(getNovoButton());
			cadastreButtonsPanel.add(getListarNewssButton());
		}
		return cadastreButtonsPanel;
	}
	
	protected JButton getAtualizarButton(){

		if(atualizarButton == null){
			atualizarButton = new JButton(messages.getMessage("Cadastrar"));
			atualizarButton.setSize(new java.awt.Dimension(100,24));
			atualizarButton.setLocation(new java.awt.Point(0,0));
			atualizarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					atualizarNews();
				}				
			});
			atualizarButton.setIcon(getIcon("/imgs/package_save.png",18,18));
		}
		return atualizarButton;
	}
	
	public void atualizarNews(){
		try {
			News p= cadastreNews();
			JOptionPane.showMessageDialog(NewsCadastreForm.this, "Cadastro realizado com sucesso.",
					"Cadastro de news", JOptionPane.INFORMATION_MESSAGE);
			//News p = RemoteNewsService.getInstance().getNewsByDescricao(news.getDescricao());
			
			editRegister(p);
			
			getAtualizarButton().setText("Atualizar");

		} catch (Exception e) {
			if (e.getMessage() != null && e.getMessage().equals("news.categoria")){
				JOptionPane.showMessageDialog(NewsCadastreForm.this, "É necessário selecionar uma categoria.",
						"Categoria não selecionada", JOptionPane.ERROR_MESSAGE);
			}else if (e.getMessage() != null && e.getMessage().equals("news.lote")){
				JOptionPane.showMessageDialog(NewsCadastreForm.this, "É necessário selecionar o lote.",
						"Lote não selecionado", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(NewsCadastreForm.this, "Algums campos não foram preenchidos corretamente.",
						"Campos não preenchidos corretamente", JOptionPane.ERROR_MESSAGE);
			}
			e.printStackTrace();
		}
	}
	
	

	/*public void atualizarNewsNotItem(){
		try {

			cadastreNewsNotItem();
			JOptionPane.showMessageDialog(UpdateCadastreForm.this, "Cadastro realizado com sucesso.",
					"Cadastro de news", JOptionPane.INFORMATION_MESSAGE);
			//News p = RemoteNewsService.getInstance().getNewsByDescricao(news.getDescricao());
			
			editRegister(news);

			getAtualizarButton().setText("Atualizar");

		} catch (Exception e) {
			if (e.getMessage() != null && e.getMessage().equals("news.categoria")){
				JOptionPane.showMessageDialog(UpdateCadastreForm.this, "É necessário selecionar uma categoria.",
						"Categoria não selecionada", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(UpdateCadastreForm.this, "Algums campos não foram preenchidos corretamente.",
						"Campos não preenchidos corretamente", JOptionPane.ERROR_MESSAGE);
			}
			e.printStackTrace();
		}
	}*/
	
	
	
	protected JButton getNovoButton(){
		if(novoButton == null){
			novoButton = new JButton(messages.getMessage("Novo"));
			novoButton.setSize(new java.awt.Dimension(100,24));
			novoButton.setLocation(new java.awt.Point(0,24));
			novoButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					newRegister();									
					getAtualizarButton().setText("Cadastrar");

				}				
			});
			novoButton.setIcon(getIcon("/imgs/package_add.png",18,18));
		}
		return novoButton;
	}
	
	
	
	protected JButton getListarNewssButton(){

		if(listarNewssButton == null){
			listarNewssButton = new JButton("Listar News");
			listarNewssButton.setIcon(getIcon("/imgs/package_table.png",18,18));
			listarNewssButton.setSize(new java.awt.Dimension(150,20));
			listarNewssButton.setLocation(new java.awt.Point(0,72));
			listarNewssButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listarNews();
				}				
			});
		}
		return listarNewssButton;
	}
	
	/**
	 * This method initializes tabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.setBounds(new Rectangle(0, 64, 700, 536));
			tabbedPane.addTab("Dados do News", getNewsContentPanel());
			tabbedPane.addTab("Imagens do News", getImageComponentsPanel());
			
			tabbedPane.addChangeListener(new ChangeListener(){
				@Override
				public void stateChanged(ChangeEvent evt) {
					if (news.getId() <= 0){
						if (tabbedPane.getSelectedIndex() == 1){
							JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(), 
									"Antes de inserir a imagem você precisa cadastrar o news",
									"Tarefas pendentes",JOptionPane.WARNING_MESSAGE);
							tabbedPane.setSelectedIndex(0);
						}
					}
					
					System.out.println("Elements changed");
				}
				
			});
		}
		return tabbedPane;
	}

	/**
	 * This method initializes nextButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNextButton() {
		if (nextButton == null) {
			nextButton = new JButton();
			nextButton.setBounds(new Rectangle(398, 158, 20, 40));
			nextButton.setIcon(new ImageIcon(getClass().getResource("/imgs/eastResize.png")));
			nextButton.setText("");
			nextButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					imgIndex = imgIndex+1;
					img = news.getImagens().get(imgIndex);
					//getImgLabelImage().setIcon(img.getBigIcon(true));
					updateImage();
				}				
			});
		}
		return nextButton;
	}

	/**
	 * This method initializes previousButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPreviousButton() {
		if (previousButton == null) {
			previousButton = new JButton();
			previousButton.setBounds(new Rectangle(100, 158, 20, 40));
			previousButton.setIcon(new ImageIcon(getClass().getResource("/imgs/westResize.png")));
			previousButton.setText("");
			previousButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					imgIndex = imgIndex-1;
					img = news.getImagens().get(imgIndex);
					//getImgLabelImage().setIcon(img.getBigIcon(true));
					updateImage();
				}				
			});
		}
		return previousButton;
	}

	
	private Object removeOptions[] = {"Retirar a imagem desse news",
			"Apagar a imagem definitivamente",
			"Cancelar"};
	/**
	 * This method initializes removerImagemButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRemoverImagemButton() {
		if (removerImagemButton == null) {
			removerImagemButton = new JButton();
			removerImagemButton.setBounds(new Rectangle(261, 5, 130, 45));
			removerImagemButton.setText("Remover Imagem");
			removerImagemButton.setHorizontalTextPosition(JButton.CENTER);
			removerImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			removerImagemButton.setIcon(getIcon("/imgs/picture_delete.png"));
			removerImagemButton.setEnabled(false);
			removerImagemButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (news.getImagens().size() <=0){						
						return;
					}
					try {
						
						int resp = JOptionPane.showOptionDialog(
								NewsCadastreForm.this, 
								"Selecione a opção para remover a imagem",
								"Remover Imagem",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.WARNING_MESSAGE,
								null, removeOptions, removeOptions[0]);
						if (resp == 1){
							
							removerImagemDefinitivamente();
							
										
							updateImage();
							updateImagensIndice();
						}else if (resp == 0){
							if (news.getImagens().size() == 1){
								try {
									removerImagemNews();								
									
									news.getImagens().remove(img);
									img = null;
									imgIndex = 0;
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							else{
								try {
									removerImagemNews();	
									news.getImagens().remove(imgIndex);
									if (news.getImagens().size() == 0) imgIndex = 0;
									else if (imgIndex > 1) imgIndex = imgIndex-1;
									else imgIndex = 0;
									img = news.getImagens().get(imgIndex);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							
							//Imagem im = img;
										
							updateImage();
							updateImagensIndice();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(NewsCadastreForm.this, "Este valor não é um número!", "O campo só aceita números", JOptionPane.ERROR_MESSAGE);
						imgNumTextField.setText(""+(imgIndex+1));
					}
				}				
			});
			
		}
		return removerImagemButton;
	}
	
	private void removerImagemDefinitivamente(){
		try {
			if (news.getImagens().size() == 1){
				try {
					Imagem im = removerImagemNews();
					RemoteServicesUtility.getInstance().delete(im);				
					news.getImagens().remove(img);
					img = null;
					imgIndex = 0;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else{
				try {
					Imagem im = removerImagemNews();
					news.getImagens().remove(imgIndex);
					RemoteServicesUtility.getInstance().delete(im);
					if (news.getImagens().size() == 0) imgIndex = 0;
					else if (imgIndex > 1) imgIndex = imgIndex-1;
					else imgIndex = 0;
					img = news.getImagens().get(imgIndex);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	private Imagem removerImagemNews() throws Exception{		
		return RemoteNewsService.getInstance().removeImagemFromNews(news.getId(),img.getId());
		/*Session s = null;
		try {
			Imagem imgRemove=null;
			s=LocalServicesUtility.getInstance().openSession();
			News p = (News) s.get(News.class,news.getId());
			Iterator<Imagem> it = p.getImagens().iterator();
			while(it.hasNext()){
				Imagem im = it.next();
				if (im.getId() == img.getId()) {
					imgRemove = im;
					break;
				}
			}
			if (imgRemove != null){
				p.getImagens().remove(imgRemove);
				s.beginTransaction();
				s.merge(p);
				s.getTransaction().commit();
				return imgRemove;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			if (s != null) s.getTransaction().rollback();
			
		}finally{
			if (s != null) s.close();
		}
		return null;*/
	}

	/**
	 * This method initializes descricaoImagemTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDescricaoImagemTextField() {
		if (descricaoImagemTextField == null) {
			descricaoImagemTextField = new JTextField();
			descricaoImagemTextField.setBounds(new Rectangle(60, 319, 331, 20));
			descricaoImagemTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				@Override
				public void keyReleased(java.awt.event.KeyEvent e) {
					if (img != null) img.setRotulo(descricaoImagemTextField.getText());
				}
			});
			descricaoImagemTextField.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent evt) {
					
				}
				@Override
				public void focusLost(FocusEvent evt) {
					
					try {
						updateRotuloImage();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		return descricaoImagemTextField;
	}

	/**
	 * This method initializes numImgsTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNumImgsTextField() {
		if (numImgsTextField == null) {
			numImgsTextField = new JTextField();
			numImgsTextField.setEditable(false);
			numImgsTextField.setText("0");
			numImgsTextField.setHorizontalAlignment(JTextField.CENTER);
		}
		return numImgsTextField;
	}

	/**
	 * This method initializes imgNumTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getImgNumTextField() {
		if (imgNumTextField == null) {
			imgNumTextField = new JTextField();
			//imgNumTextField.setEditable(false);
			imgNumTextField.setText("0");
			imgNumTextField.setHorizontalAlignment(JTextField.CENTER);
			imgNumTextField.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (news.getImagens().size() <=0){						
						return;
					}
					try {
						int n = Integer.parseInt(imgNumTextField.getText());
						if (n == 0){
							JOptionPane.showMessageDialog(NewsCadastreForm.this, "Você deve informar um valor maior que zero!", "O campo só aceita números maiores que zero", JOptionPane.ERROR_MESSAGE);
							imgNumTextField.setText(""+(imgIndex+1));
						}else if (n > news.getImagens().size()){
							JOptionPane.showMessageDialog(NewsCadastreForm.this, "Você deve informar um valor menor que "+n+"!", "Ordem incorreta das imagens", JOptionPane.ERROR_MESSAGE);
							imgNumTextField.setText(""+(imgIndex+1));
						}else{
							img = news.getImagens().remove(imgIndex);
							img.setIndice(n-1);
							news.getImagens().add(n-1,img);
							imgIndex = n-1;
							updateImage();
							updateImagensIndice();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(NewsCadastreForm.this, "Este valor não é um número!", "O campo só aceita números", JOptionPane.ERROR_MESSAGE);
						imgNumTextField.setText(""+(imgIndex+1));
					}
				}				
			});
		}
		return imgNumTextField;
	}

	/**
	 * This method initializes dadosImgPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDadosImgPanel() {
		if (dadosImgPanel == null) {
			dadosImgPanel = new JPanel();
			dadosImgPanel.setLayout(new GridLayout(4,1));
			dadosImgPanel.setBounds(new Rectangle(2, 234, 125, 80));
			
			dadosImgPanel.add(numImgsLabel, null);
			dadosImgPanel.add(getNumImgsTextField(), null);
			dadosImgPanel.add(imgNumLabel, null);
			dadosImgPanel.add(getImgNumTextField(), null);
			
		}
		return dadosImgPanel;
	}

	/**
	 * This method initializes trocarImagemButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getTrocarImagemButton() {
		if (trocarImagemButton == null) {
			trocarImagemButton = new JButton();
			trocarImagemButton.setBounds(new Rectangle(5, 55, 115, 45));
			trocarImagemButton.setEnabled(false);
			trocarImagemButton.setText("Trocar Imagem");
			trocarImagemButton.setHorizontalTextPosition(JButton.CENTER);
			trocarImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			trocarImagemButton.setIcon(getIcon("/imgs/picture_edit.png"));
			trocarImagemButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					browseChangeImage();
				}				
			});
		}
		return trocarImagemButton;
	}
	
	
	private JButton anexarImagensButton;

	private JButton getAnexarImagensButton() {
		if (anexarImagensButton == null) {
			anexarImagensButton = new JButton();
			anexarImagensButton.setBounds(new Rectangle(5, 5, 115, 45));
			anexarImagensButton.setEnabled(true);
			anexarImagensButton.setText("Anexar Imagens");
			anexarImagensButton.setHorizontalTextPosition(JButton.CENTER);
			anexarImagensButton.setVerticalTextPosition(JButton.BOTTOM);
			anexarImagensButton.setIcon(getIcon("/imgs/pictures.png"));
			anexarImagensButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if (news.getId() != 0){
						anexarImagem();
					}
					else{
						JOptionPane.showMessageDialog(NewsCadastreForm.this, "Primeiro é preciso cadastrar o news!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);
						getTabbedPane().setSelectedIndex(0);
					}
				}				
			});
		}
		return anexarImagensButton;
	}
	
	/**
	 * This method initializes salvarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getViewFullImageButton() {
		if (viewFullImageButton == null) {
			viewFullImageButton = new JButton();
			//viewFullImageButton.setEnabled(false);
			viewFullImageButton.setBounds(new Rectangle(396, 55, 70, 45));
			viewFullImageButton.setText("Exibir");
			viewFullImageButton.setHorizontalTextPosition(JButton.CENTER);
			viewFullImageButton.setVerticalTextPosition(JButton.BOTTOM);
			viewFullImageButton.setIcon(getIcon("/imgs/zoom.png"));
			viewFullImageButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if (img != null){
						try {
							JDialog jd = new JDialog(AdapitVirtualFrame.getInstance());
							jd.setTitle(img.getRotulo());
							JLabel jl = new JLabel();
							jl.setSize(scale,scale);
							jd.setSize(600,600);
							jd.setModal(true);
							JScrollPane js = new JScrollPane();
							js.add(jl);
							js.setViewportView(jl);
							jd.add(js);
							jl.setIcon(img.getImageIcon());
							jd.setLocation(UIUtil.getScreenCenter(jd));
							jd.setVisible(true);
						} catch (Exception e) {
							// 
							e.printStackTrace();
						}
					}					
				}
			});
		}
		return viewFullImageButton;
	}

	

	/**
	 * This method initializes detalhesImagemButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDetalhesImagemButton() {
		if (detalhesImagemButton == null) {
			detalhesImagemButton = new JButton();
			detalhesImagemButton.setBounds(new Rectangle(392, 315, 74, 25));
			detalhesImagemButton.setText("Detalhes");
		}
		return detalhesImagemButton;
	}

	
	
	private static Icon getIcon(String name ){

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL).getImage();
				if (image != null) {
					image = image.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			 
			e.printStackTrace(); 
		} catch (java.lang.Exception e) {
			// 
			e.printStackTrace(); 
		}//end of catch block
		return null;
	}

	
	
	private JPanel imageComponentsPanel;

	//private JPanel imageTitleButtonPanel;

	private JLabel imageLabel;

	private JButton buscarImagemButton;

	private JTextField caminhoImgTextField;

	private JLabel caminhoImgTextFieldLabel;

	private JLabel imgLabelImage;

	private JPanel imgPanelImage;
	
	protected JPanel getImgPanelImage(){
		if (imgPanelImage == null){
			imgPanelImage = new JPanel();
			imgPanelImage.setLayout(new BorderLayout());
			imgPanelImage.setBorder(BorderFactory.createLineBorder(Color.lightGray));
			imgPanelImage.setLocation(new Point(130, 55));
			imgPanelImage.setSize(new Dimension(260, 260));
			imgPanelImage.add(getImgLabelImage(), BorderLayout.CENTER);
		}
		return imgPanelImage;
	}
	
	private JPanel smallPanelImage;
	
	protected JPanel getSmallPanelImage(){
		if (smallPanelImage == null){
			smallPanelImage = new JPanel();
			smallPanelImage.setLayout(new BorderLayout());
			smallPanelImage.setBorder(BorderFactory.createLineBorder(Color.lightGray));
			smallPanelImage.setLocation(new Point(5, 140));
			smallPanelImage.setSize(new Dimension(smallScale, smallScale));
			smallPanelImage.add(getSmallLabelImage(), BorderLayout.CENTER);
		}
		return smallPanelImage;
	}
	
	

	protected JPanel getImageComponentsPanel() {
		if (imageComponentsPanel == null) {
			descricaoLabel = new JLabel();
			descricaoLabel.setBounds(new Rectangle(2, 343, 57, 22));
			descricaoLabel.setText("Descrição:");
			imgNumLabel = new JLabel();
			imgNumLabel.setText("Imagem Número:");
			imgNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
			imgNumLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			numImgsLabel = new JLabel();
			numImgsLabel.setText("Número de Imagens:");
			numImgsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			descriçãoLabel = new JLabel();
			descriçãoLabel.setBounds(new Rectangle(2, 319, 55, 20));
			descriçãoLabel.setText("Rótulo:");
			imageComponentsPanel = new JPanel();
			imageComponentsPanel.setLayout(null);
			imageComponentsPanel.add(getBuscarImagemButton());
			imageComponentsPanel.add(getCaminhoImgTextField());
			imageComponentsPanel.add(getCaminhoImgTextFieldLabel());
			imageComponentsPanel.add(getImgPanelImage(), null);
			imageComponentsPanel.add(getSmallPanelImage(), null);
			imageComponentsPanel.add(getNextButton(), null);
			imageComponentsPanel.add(getPreviousButton(), null);
			imageComponentsPanel.add(getRemoverImagemButton(), null);
			imageComponentsPanel.add(descriçãoLabel, null);
			imageComponentsPanel.add(getDescricaoImagemTextField(), null);
			imageComponentsPanel.add(getDadosImgPanel(), null);
			imageComponentsPanel.add(getTrocarImagemButton(), null);
			imageComponentsPanel.add(getAnexarImagensButton(),null);
			imageComponentsPanel.add(getViewFullImageButton(), null);
			imageComponentsPanel.add(getDetalhesImagemButton(), null);
			imageComponentsPanel.add(descricaoLabel, null);
			imageComponentsPanel.add(getDescricaoImgScrollPane(), null);
			imageComponentsPanel.addHierarchyListener(new HierarchyListener(){
				@Override
				public void hierarchyChanged(HierarchyEvent evt) {
					if (evt.getChanged() == imageComponentsPanel && evt.getID() == HierarchyEvent.HIERARCHY_CHANGED){
						if (news.getImagens() != null &&
								news.getImagens().iterator() != null
								&& news.getImagens().iterator().hasNext() && news.getId() >0){
							AdapitVirtualFrame.getInstance().beginStatusBar("Importando as imagens! Essa operação pode ser demorada");
							try{
								News n = RemoteNewsService.getInstance().loadNewsEagerImagens(news);
								news.setImagens(n.getImagens());
								imgIndex=0;
								updateImage();
							}catch(Exception ex){
								ex.printStackTrace();
							}
							/*Session s = LocalServicesUtility.getInstance().openSession();
							try{
								s.load(news,news.getId());
								news.getImagens().iterator().hasNext();	
								imgIndex=0;
								updateImage();
							}catch(Exception ex){
								ex.printStackTrace();
							}finally{
								s.close();
							}*/
							AdapitVirtualFrame.getInstance().endStatusBar("Importando as imagens! Essa operação pode ser demorada");
						}
					}
				}
				
			});			
			
		}
		return imageComponentsPanel;
	}


	protected JLabel getImageLabel() {

		if (imageLabel == null) {
			imageLabel = new JLabel(
					messages
							.getMessage("Imagem"));
			imageLabel.setSize(new java.awt.Dimension(100, 20));
			imageLabel.setHorizontalAlignment(JLabel.RIGHT);
			imageLabel.setLocation(new java.awt.Point(188, 11));
		}
		return imageLabel;
	}

	protected JButton getBuscarImagemButton() {

		if (buscarImagemButton == null) {
			buscarImagemButton = new JButton("Buscar Imagem");
			buscarImagemButton.setSize(new Dimension(130, 45));
			buscarImagemButton.setLocation(new Point(129, 5));
			buscarImagemButton.setHorizontalTextPosition(JButton.CENTER);
			buscarImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			buscarImagemButton.setText("Adicionar Imagem");
			buscarImagemButton.setIcon(getIcon("/imgs/picture_add.png"));
			buscarImagemButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if (news.getId() != 0){
						AdapitVirtualFrame.getInstance().beginStatusBar("Carregando Imagem ...");
						browseImage();
						AdapitVirtualFrame.getInstance().endStatusBar("Carregando Imagem ...");
					}
					else{
						JOptionPane.showMessageDialog(NewsCadastreForm.this, "Primeiro é preciso cadastrar o news!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);
						getTabbedPane().setSelectedIndex(0);
					}
				}
				
			});
		}
		return buscarImagemButton;
	}

	protected JTextField getCaminhoImgTextField() {
		if (caminhoImgTextField == null) {
			caminhoImgTextField = new JTextField();
			caminhoImgTextField.setEditable(false);
			caminhoImgTextField.setText("");
			caminhoImgTextField.setSize(new Dimension(406, 20));
			caminhoImgTextField.setLocation(new Point(60, 380));
			return caminhoImgTextField;
		}
		return caminhoImgTextField;
	}

	protected JLabel getCaminhoImgTextFieldLabel() {

		if (caminhoImgTextFieldLabel == null) {
			caminhoImgTextFieldLabel = new JLabel(
					messages
							.getMessage("Caminho"));
			caminhoImgTextFieldLabel.setSize(new java.awt.Dimension(55, 20));
			caminhoImgTextFieldLabel.setLocation(new Point(2, 380));
			caminhoImgTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return caminhoImgTextFieldLabel;
	}

	protected JLabel getImgLabelImage() {

		if (imgLabelImage == null) {
			imgLabelImage = new JLabel();
			imgLabelImage.setHorizontalTextPosition(SwingConstants.CENTER);
			imgLabelImage.setHorizontalAlignment(SwingConstants.CENTER);
			try {
				/*imgLabelImage
						.setIcon(getIcon("/C:/Documents and Settings/user/Meus documentos/imgs/wizardicon.gif",102,102));*/
			} catch (RuntimeException e) {
				// 
				e.printStackTrace();
			}
			
		}
		return imgLabelImage;
	}
	
	private JLabel smallLabelImage;
	
	protected JLabel getSmallLabelImage() {

		if (smallLabelImage == null) {
			smallLabelImage = new JLabel();
			smallLabelImage.setHorizontalTextPosition(SwingConstants.CENTER);
			smallLabelImage.setHorizontalAlignment(SwingConstants.CENTER);						
		}
		return smallLabelImage;
	}
	
	private JFileChooser jfc;

	private JTabbedPane tabbedPane = null;

	private JButton nextButton = null;

	private JButton previousButton = null;

	private JButton removerImagemButton = null;

	private JLabel descriçãoLabel = null;

	private JTextField descricaoImagemTextField = null;

	private JLabel numImgsLabel = null;

	private JTextField numImgsTextField = null;

	private JLabel imgNumLabel = null;

	private JTextField imgNumTextField = null;

	private JPanel dadosImgPanel = null;
	
	protected JFileChooser getJfc(){
		if (jfc == null){
			jfc = new ImageFileChooser();
			/*jfc.setDialogTitle("Escolha a imagem da Categoria");
			jfc.setLocation(UIUtil.getScreenCenter(jfc));*/
			
		}
		return jfc;
	}
	
	public void browseImage(){
		
		try {
			getJfc().showOpenDialog(this);
			File f = getJfc().getSelectedFile();
			if (f == null ) return;
			
			this.getCaminhoImgTextField().setText(f.toURI().getPath());	
			img = new Imagem();
			img.setFullImageBytes(f,scale);
			img.setSmallImageScallingAs(this.smallScale);
			
			
			getDescricaoImagemTextField().setText("");
			getDescricaoImageTextArea().setText("");
			
			getImgLabelImage().setIcon(img.getMediumImageIcon(true));
			getSmallLabelImage().setIcon(img.getSmallImageIcon(true));
			
			
			try {
				
								
				if (news.getImagens() == null)
					news.setImagens(new ArrayList<Imagem>());
				imgIndex = news.getImagens().size();
				img.setIndice(imgIndex);
				
				img.setPath(f.getAbsolutePath());
				
				
				updateImageComponents();
				
				
				getImgLabelImage().updateUI();
				getSmallLabelImage().updateUI();
				
				img = RemoteNewsService.getInstance().createNewsImage(news, img);
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
			
			/*Session s = null;
			try {
				s = LocalServicesUtility.getInstance().openSession();
				s.beginTransaction();
								
				if (news.getImagens() == null)
					news.setImagens(new ArrayList<Imagem>());
				imgIndex = news.getImagens().size();
				img.setIndice(imgIndex);
				
				img.setPath(f.getAbsolutePath());
				
				
				updateImageComponents();
				
				
				getImgLabelImage().updateUI();
				getSmallLabelImage().updateUI();
				
				s.persist(img);
				
				s.load(news,news.getId());
				news.getImagens().add(img);				
				
				s.flush();				
				
				s.getTransaction().commit();
			} catch (Exception e1) {
				if (s != null) s.getTransaction().rollback();
				e1.printStackTrace();
			}finally{
				if (s != null) s.close();
			}	*/		
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
		
		updateImageComponents();
	}
	
	public void saveImage() throws Exception{
		if(img != null){
			if(img.getId() != 0){
				RemoteServicesUtility.getInstance().createOrUpdate(img);			
			}
			else{
				Imagem i = (Imagem) RemoteServicesUtility.getInstance().createOrUpdate(img);
				editRegister(i);
				//img = i;
			}
		}
	}
	
	public void updateDescricaoImage() throws Exception{
		RemoteImagemService.getInstance().updateImageDescriptionByImageId(img.getId(), img.getDescription());
	}
	
	public void updateRotuloImage() throws Exception{
		RemoteImagemService.getInstance().updateImageRotuloByImageId(img.getId(), img.getRotulo());
	}
	
	/*public void updateDescricaoImage() throws Exception{
		Session s = null;
		try {
			if (img != null && img.getId() != 0) {
				s = LocalServicesUtility.getInstance().openSession();
				s.getTransaction().begin();
				
				//s.merge(img);
				s.createQuery("update Imagem im set im.description='"+img.getDescription()+"' where im.id="+img.getId()).executeUpdate();
				
				s.getTransaction().commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
		} finally {
			if (s != null)
				s.close();
		}
	}
	
	public void updateRotuloImage() throws Exception{
		Session s = null;
		try {
			if (img != null && img.getId() != 0) {
				s = LocalServicesUtility.getInstance().openSession();
				s.getTransaction().begin();
				
				//s.merge(img);
				s.createQuery("update Imagem im set im.rotulo='"+img.getRotulo()+"' where im.id="+img.getId()).executeUpdate();
				
				s.getTransaction().commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
		} finally {
			if (s != null)
				s.close();
		}
	}*/
	
	public void editRegister(Imagem objImg){
		img.setId(objImg.getId());	
		img.setFullImageBytes(objImg.getFullImageBytes());
		img.setIndice(objImg.getIndice());
		img.setAltText(objImg.getAltText());
		img.setDescription(objImg.getDescription());
		img.setFormat(objImg.getFormat());
		img.setHeight(objImg.getHeight());
		img.setPath(objImg.getPath());
		img.setRotulo(objImg.getRotulo());
		
		img.setSmallImageBytes(objImg.getSmallImageBytes());
		img.setWidth(objImg.getWidth());
	}
	
	private JButton anexar;
	public void anexarImagem(){		
		try {
				
			if (anexar == null){				 
				anexar = new JButton("Anexar imagens selecionadas no news " + news.getId());
				anexar.addActionListener(new AnexarImagemActionListener());	
				anexar.setIcon(getIcon("/imgs/picture_link.png"));
			}			
			AdapitVirtualFrame.getInstance().getListaImagensFrame().getImageListForm().getButtonsPanel().add(anexar,0);
			AdapitVirtualFrame.getInstance().listImagens();
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}
	
	private class AnexarImagemActionListener implements ActionListener{
		
		public AnexarImagemActionListener(){
			AdapitVirtualFrame.getInstance().listImagens();		
			ilf = AdapitVirtualFrame.getInstance().getListaImagensFrame().getImageListForm();
			buttonsPanel = ilf.getButtonsPanel();
		}
		private ImageListForm ilf;
		private JPanel buttonsPanel;
		
		@Override
		public void actionPerformed(ActionEvent evt) {
					
			int rows[] = ilf.getBaseTable().getSelectedRows();
			if (rows != null){
				try {
					List<Imagem> list = new ArrayList<Imagem>();
					for (int i=0; i < rows.length; i++){
						Imagem im = (Imagem) ilf.getBaseTable().getElements().get(rows[i]);
						list.add(im);
					}
					News prod = RemoteNewsService.getInstance().anexarImagemNews(news, list);
					news.setImagens(prod.getImagens());
					AdapitVirtualFrame.getInstance().showOperationSucess("Anexar Imagens em Produtos", "Imagens anexadas com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();					
				}
				/*Session s = null;
				try {
					s = LocalServicesUtility.getInstance().openSession();
					s.load(news,news.getId());
					
					s.beginTransaction();
					for (int i=0; i < rows.length; i++){
						Imagem im = (Imagem) ilf.getBaseTable().getElements().get(rows[i]);
						news.getImagens().add(im);
					}
					s.flush();//merge(news);
					s.getTransaction().commit();
					LeilaoVirtualFrame.getInstance().showOperationSucess("Anexar Imagens em News", "Imagens anexadas com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();
					s.getTransaction().rollback();
				}finally{
					if (s != null) s.close();
				}*/
				buttonsPanel.remove(anexar);				
			}
			AdapitVirtualFrame.getInstance().getListaImagensFrame().dispose();
			updateImageComponents();
		}
	}
	
	public void browseChangeImage(){		
		try {
			getJfc().showOpenDialog(this);
			File f = getJfc().getSelectedFile();
			this.getCaminhoImgTextField().setText(f.toURI().getPath());	
			img.setFullImageBytes(f,scale);
			img.setSmallImageScallingAs(this.smallScale);
			
			getImgLabelImage().setIcon(img.getMediumImageIcon(true));
			getSmallLabelImage().setIcon(img.getMediumImageIcon(true));
			
			updateImageComponents();
			getImgLabelImage().updateUI();
			getSmallLabelImage().updateUI();
			
			try {
				saveImage();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}
	
	public void updateImageComponents(){
		try{
			news.getImagens().size();
		}catch(Exception ex){			
			ex.printStackTrace();			
		}
		if (news.getImagens() != null && news.getImagens().size()>0){
			getNumImgsTextField().setText(news.getImagens().size()+"");
			getImgNumTextField().setText(""+(imgIndex+1));
			if (news.getImagens().size() == 1){
				getPreviousButton().setEnabled(false);
				getNextButton().setEnabled(false);
				getImgNumTextField().setEnabled(false);
				getImgNumTextField().setText("1");
			}else{
				getPreviousButton().setEnabled(true);
				getNextButton().setEnabled(true);
				if (imgIndex == 0) getPreviousButton().setEnabled(false);
				else if (imgIndex + 1 == news.getImagens().size()) getNextButton().setEnabled(false);
				getImgNumTextField().setEnabled(true);
			}
			getTrocarImagemButton().setEnabled(true);
			getRemoverImagemButton().setEnabled(true);
		}else{
			getNumImgsTextField().setText("0");
			getImgNumTextField().setText("");
			getImgNumTextField().setEnabled(false);
			getPreviousButton().setEnabled(false);
			getNextButton().setEnabled(false);
			getTrocarImagemButton().setEnabled(false);
			getRemoverImagemButton().setEnabled(false);
		}
	}
	
	int imgIndex=0;
	Imagem img;

	private JButton trocarImagemButton = null;

	private JButton viewFullImageButton = null;


	private JButton detalhesImagemButton = null;

	private JLabel descricaoLabel = null;

	private JTextPane descricaoImageTextArea = null;

	private JScrollPane descricaoImgScrollPane = null;

	private JLabel tituloLabel = null;

	private JTextField tituloTextField = null;

	private JLabel resumoLabel = null;

	private JTextPane resumoTextArea = null;
	private void updateImage(){

		updateImageComponents();
		if (news.getImagens() != null && news.getImagens().size() > 0)try {
			img = news.getImagens().get(imgIndex);
			this.getCaminhoImgTextField().setText(img.getPath());
			if (img.getRotulo() != null) this.getDescricaoImagemTextField().setText(img.getRotulo());
			else this.getDescricaoImagemTextField().setText("");
			
			if (img.getDescription() != null) this.getDescricaoImageTextArea().setText(img.getDescription());
			else this.getDescricaoImageTextArea().setText("");
			
			getImgLabelImage().setIcon(img.getMediumImageIcon(false));
			getImgLabelImage().updateUI();
			
			getSmallLabelImage().setIcon(img.getSmallImageIcon(false));
			getSmallLabelImage().updateUI();
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
		else try {
			this.getCaminhoImgTextField().setText("");
			getImgLabelImage().setIcon(null);
			getImgLabelImage().updateUI();
			
			getSmallLabelImage().setIcon(null);
			getSmallLabelImage().updateUI();
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}
	
	private static Icon getIcon(String name, int w, int h) {

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();
				if (image != null) {
					image = image.getScaledInstance(w, h,
							java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			// 
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			
			e.printStackTrace();
		}// end of catch block
		return null;
	}

	private void updateImagensIndice(){
		if (news.getImagens() != null && news.getImagens().size() > 0){
			{
				Iterator<Imagem> it = news.getImagens().iterator();
				int i=0;
				while(it.hasNext()){
					Imagem im = it.next();
					im.setIndice(i);
					i++;				
				}
			}
			
				Iterator<Imagem> it = news.getImagens().iterator();
				while (it.hasNext()) {
					Imagem im = it.next();
					RemoteServicesUtility.getInstance().createOrUpdate(im);
					
				}
			
		}
	}

	/**
	 * This method initializes descricaoImageTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextPane getDescricaoImageTextArea() {
		if (descricaoImageTextArea == null) {
			descricaoImageTextArea = new JTextPane();
			
			descricaoImageTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
				@Override
				public void keyReleased(java.awt.event.KeyEvent e) {
					if (img != null) img.setDescription(descricaoImageTextArea.getText());
				}
			});
			descricaoImageTextArea.addFocusListener(new FocusAdapter(){
				@Override
				public void focusLost(FocusEvent evt) {
					try {
						updateDescricaoImage();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		return descricaoImageTextArea;
	}

	/**
	 * This method initializes descricaoImgScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getDescricaoImgScrollPane() {
		if (descricaoImgScrollPane == null) {
			descricaoImgScrollPane = new JScrollPane();
			descricaoImgScrollPane.setBounds(new Rectangle(60, 342, 405, 36));
			descricaoImgScrollPane.setViewportView(getDescricaoImageTextArea());
		}
		return descricaoImgScrollPane;
	}
	


	/**
	 * This method initializes tituloTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */@SuppressWarnings("unchecked")
	private JComponent getTituloTextField() {
		if (tituloTextField == null) {
			tituloTextField = new JTextField();
			tituloTextField.setBounds(new Rectangle(130, 5, 544, 22));
			this.binder.addBindProperty(this.news, this.tituloTextField, "titulo");
			
			this.hashComps.put("titulo", this.tituloTextField);
			JWarningComponent warn = new JWarningComponent( this.tituloTextField);
			warn.setBounds(new Rectangle(130, 5, 544, 22));
			return warn;
		}
		return tituloTextField;
	}

	/**
	 * This method initializes resumoTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */@SuppressWarnings("unchecked")
	private JComponent getResumoTextArea() {
		if (resumoTextArea == null) {
			resumoTextArea = new JTextPane();			
			resumoNewsEventListener = new HtmlEditorEventListener(resumoTextArea,AdapitVirtualFrame.getInstance(),"Resumo da Notícia");
			resumoTextArea.setBounds(new Rectangle(121, 30, 337, 40));
			this.binder.addBindProperty(this.news, this.resumoTextArea, "resumo");
			
			this.hashComps.put("resumo", this.resumoTextArea);
			JWarningComponent warn = new JWarningComponent( this.resumoTextArea);
			warn.setBounds(new Rectangle(121, 30, 337, 40));
			return warn;
		}
		return resumoTextArea;
	}
	
	//private JScrollPane resumoScrollPane;

	private DateHourChooser dataPublicacao = null;
	
	/*private JScrollPane getResumoScrollPane() {
		if (resumoScrollPane == null) {
			resumoScrollPane = new JScrollPane();
			resumoScrollPane.setBounds(new Rectangle(17, 87, 657, 329));
			resumoScrollPane.setViewportView(getResumoTextArea());
		}
		return resumoScrollPane;
	}*/
	
	private HtmlEditorPanel resumoNewsEditor;
	private HtmlEditorPanel getResumoNewsEditor() {
		if (resumoNewsEditor == null) {
			getResumoTextArea();
			resumoNewsEditor = new HtmlEditorPanel(
					(JTextComponent)resumoTextArea,resumoNewsEventListener,
					AdapitVirtualFrame.getInstance().getCss());
			resumoNewsEditor.setBounds(new Rectangle(17, 87, 657, 329));
		}
		return resumoNewsEditor;
	}

	private HtmlEditorEventListener resumoNewsEventListener = null;

	/**
	 * This method initializes dataPublicacaoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */@SuppressWarnings("unchecked")
	private JComponent getDataPublicacao() {
		if (dataPublicacao == null) {
			dataPublicacao = new DateHourChooser(messages
					.getCurrentLocale(), true, true, false);
			dataPublicacao.setBounds(new Rectangle(130, 30, 205, 22));
			
			this.binder.addBindProperty(this.news,
					dataPublicacao, "dataPublicacao");

			this.hashComps.put("dataPublicacao", dataPublicacao);
			JWarningComponent warn = new JWarningComponent(dataPublicacao);
			warn.setBounds(new Rectangle(130, 30, 205, 22));
			return warn;
		}
		return dataPublicacao;
	}
}  // @jve:decl-index=0:visual-constraint="10,10"
