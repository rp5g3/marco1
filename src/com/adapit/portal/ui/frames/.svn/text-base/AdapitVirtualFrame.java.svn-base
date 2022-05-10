package com.adapit.portal.ui.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.adapit.portal.dto.UsuarioDTO;
import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.ContatoTreinamento;
import com.adapit.portal.entidades.CssDefinition;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.Funcionario;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.News;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.Publication;
import com.adapit.portal.entidades.RepresentanteLegal;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.entidades.Update;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.PersonType;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.forms.categoria.CategoriaSelectable;
import com.adapit.portal.ui.forms.destaque.DestaqueForm;
import com.adapit.portal.ui.forms.file.FileFilterPanel;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.forms.usuario.TelaLoginUsuario;
import com.adapit.portal.ui.forms.usuario.TimerMessageFrame;
import com.adapit.portal.ui.forms.usuario.UsuarioListForm;
import com.adapit.portal.ui.forms.usuario.WelcomeDesktopPane;
import com.adapit.portal.ui.frames.categoria.CategoriaInternalFrame;
import com.adapit.portal.ui.frames.imagem.CadastrarImagemInternalFrame;
import com.adapit.portal.ui.frames.imagem.ListaImagemInternalFrame;
import com.adapit.portal.ui.frames.menus.LeilaoVirtualMenuBar;
import com.adapit.portal.ui.frames.menus.OutlookMenuPanel;
import com.adapit.portal.ui.frames.news.CadastrarNewsInternalFrame;
import com.adapit.portal.ui.frames.news.ListaNewsFrame;
import com.adapit.portal.ui.frames.pessoa.funcionario.FuncionarioCadastreInternalFrame;
import com.adapit.portal.ui.frames.pessoa.instrutor.InstrutorCadastreDialog;
import com.adapit.portal.ui.frames.pessoa.representantelegal.RepresentanteLegalCadastreDialog;
import com.adapit.portal.ui.frames.pessoa.rh.ComitenteCadastreDialog;
import com.adapit.portal.ui.frames.pessoa.rh.ParticipanteCadastreDialog;
import com.adapit.portal.ui.frames.publication.CadastrarPublicationInternalFrame;
import com.adapit.portal.ui.frames.publication.ListaPublicationFrame;
import com.adapit.portal.ui.frames.solution.CadastrarComercialSolutionInternalFrame;
import com.adapit.portal.ui.frames.solution.ListaComercialSolutionsFrame;
import com.adapit.portal.ui.frames.solution.sistema.CadastrarSoftwareSolutionInternalFrame;
import com.adapit.portal.ui.frames.solution.sistema.CadastroDomainInterestInternalFrame;
import com.adapit.portal.ui.frames.solution.sistema.ListaSistemaSolutionsFrame;
import com.adapit.portal.ui.frames.solution.treinamento.CadastrarTrainingSolutionInternalFrame;
import com.adapit.portal.ui.frames.solution.treinamento.ListaTrainingSolutionsFrame;
import com.adapit.portal.ui.frames.toolbar.BottomToolBar;
import com.adapit.portal.ui.frames.toolbar.TopToolBar;
import com.adapit.portal.ui.frames.treinamento.CadastroTreinamentoInternalFrame;
import com.adapit.portal.ui.frames.treinamento.RelatorioTreinamentosInternalFrame;
import com.adapit.portal.ui.frames.treinamento.contapagar.ContaPagarLoteInternalFrame;
import com.adapit.portal.ui.frames.treinamento.contato.CadastroContatoInternalFrame;
import com.adapit.portal.ui.frames.treinamento.contato.ListaContatosInternalFrame;
import com.adapit.portal.ui.frames.treinamento.encerramento.FechamentoTreinamentoInternalFrame;
import com.adapit.portal.ui.frames.treinamento.formacao.CadastroFormacaoInternalFrame;
import com.adapit.portal.ui.frames.treinamento.turma.RelatorioTurmasInternalFrame;
import com.adapit.portal.ui.frames.treinamento.turma.TurmaCadastreInternalFrame;
import com.adapit.portal.ui.frames.update.CadastrarUpdateInternalFrame;
import com.adapit.portal.ui.frames.update.ListaUpdateFrame;
import com.adapit.portal.ui.tree.CategoriaSelectableTreeController;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SwingWorker;
import com.workcase.gui.utils.UIUtil;
import com.workcase.utils.DatePropertyEditor;


public class AdapitVirtualFrame extends JFrame {

	private static final long serialVersionUID = 32461432436L;

	private JPanel jContentPane = null;

	private LeilaoVirtualMenuBar mainMenuBar = null;

	private TopToolBar mainToolBar = null;

	private JSplitPane mainSplitPane = null;

	private OutlookMenuPanel mainMenuExpandPanel = null;

	private JDesktopPane mainDesktopPane = null;

	private BottomToolBar bottomPanel = null;

	private ResourceMessage messages = ResourceMessage.getInstance();
	

	//private JButton manutencaoUsuarioButton = null;

	private static AdapitVirtualFrame instance;
	
	//private Pessoa pessoa=null;
	
	/**
	 * This is the default constructor
	 */
	public AdapitVirtualFrame() {
		super();	
		instance=this;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		TelaLoginUsuario login = TelaLoginUsuario.getInstance();		
		login.setVisible(true);
		
		this.setSize(827, 666);		
		this.setJMenuBar(getMainMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Sistema Adapit .: Administrador :. ");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			//jContentPane.add(getMainToolBar(), BorderLayout.NORTH);
			jContentPane.add(getMainSplitPane(), BorderLayout.CENTER);
			jContentPane.add(getBottomPanel(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes mainMenuBar
	 * 
	 * @return javax.swing.JMenuBar
	 */
	private LeilaoVirtualMenuBar getMainMenuBar() {
		if (mainMenuBar == null) {
			mainMenuBar = new LeilaoVirtualMenuBar();
		}
		return mainMenuBar;
	}

	/**
	 * This method initializes mainToolBar
	 * 
	 * @return javax.swing.JToolBar
	 */
	private TopToolBar getMainToolBar() {
		if (mainToolBar == null) {
			mainToolBar = new TopToolBar();
			
		}
		return mainToolBar;
	}

	/**
	 * This method initializes mainSplitPane
	 * 
	 * @return javax.swing.JSplitPane
	 */
	private JSplitPane getMainSplitPane() {
		if (mainSplitPane == null) {
			mainSplitPane = new JSplitPane();
			mainSplitPane.setOneTouchExpandable(true);
			mainSplitPane.setDividerLocation(200);
			mainSplitPane.setEnabled(true);
			mainSplitPane.setDividerSize(10);
			mainSplitPane.setRightComponent(getMainDesktopPane());
			mainSplitPane.setLeftComponent(getMainMenuExpandPanel());
		}
		return mainSplitPane;
	}
	
	/**
	 * This method initializes mainDesktopPane
	 * 
	 * @return javax.swing.JDesktopPane
	 */
	public JDesktopPane getMainDesktopPane() {
		if (mainDesktopPane == null) {
			mainDesktopPane = new WelcomeDesktopPane(TelaLoginUsuario.getInstance().getUsuarioDTO());
			//WelcomePanel welcome = new WelcomePanel(mainDesktopPane,TelaLoginUsuario.getInstance().getUsuarioDTO());
			
		}
		return mainDesktopPane;
	}

	/**
	 * This method initializes bottomPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private BottomToolBar getBottomPanel() {
		if (bottomPanel == null) {
			bottomPanel = new BottomToolBar();
		}
		return bottomPanel;
	}

	



	public static void main(String args[]) {
		new java.lang.Thread(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
					UIManager.put("OptionPane.cancelButtonText", "Cancelar");
				    UIManager.put("OptionPane.noButtonText", "Não");
				    UIManager.put("OptionPane.okButtonText", "Ok");
				    UIManager.put("OptionPane.yesButtonText", "Sim");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}

				javax.swing.JFrame gui = new AdapitVirtualFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setVisible(true);
			}
		}).run();
	}

	public static Icon getIcon(String name, int w, int h) {

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
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}// end of catch block
		return null;
	}

	public static AdapitVirtualFrame getInstance() {
		/*if (instance == null){
			
					try {
						UIManager.setLookAndFeel(UIManager
								.getSystemLookAndFeelClassName());
						UIManager.put("OptionPane.cancelButtonText", "Cancelar");
					    UIManager.put("OptionPane.noButtonText", "Não");
					    UIManager.put("OptionPane.okButtonText", "Ok");
					    UIManager.put("OptionPane.yesButtonText", "Sim");
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
						e.printStackTrace();
					}

					instance = new LeilaoVirtualFrame();
					instance.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					instance.setVisible(true);
				
		}*/
		return instance;
	}


	public void beginStatusBar(String msg){
		getBottomPanel().beginStatusBar(msg);		
	}
	public void endStatusBar(String msg){
		getBottomPanel().endStatusBar(msg);
	}
	public void showProgressBar() {
		getBottomPanel().showProgressBar();
	}

	/**
	 * This method initializes mainMenuExpandPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	public OutlookMenuPanel getMainMenuExpandPanel() {
		if (mainMenuExpandPanel == null) {
			mainMenuExpandPanel = new OutlookMenuPanel();			
		}
		return mainMenuExpandPanel;
	}
	
	private ListaImagemInternalFrame listaImagensFrame;
	
	public void listImagens(){		
		beginStatusBar("Listar Imagens");
		if (listaImagensFrame != null){
			listaImagensFrame.postInDesktopPane();
		}else{
			ListaImagemInternalFrame gui = new ListaImagemInternalFrame();
			gui.postInDesktopPane();
			listaImagensFrame = gui;
			if (mainDesktopPane.getComponentCount()>0){
				gui.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
		}
		endStatusBar("Listar Imagens");
	}
	
	public ImageListForm listarImagens(JButton selecionarButton){
		ListaImagemInternalFrame gui = new ListaImagemInternalFrame();
		if (mainDesktopPane.getComponentCount()>0){
			gui.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
		}
		JPanel jp = new JPanel(new FlowLayout());
		jp.add(selecionarButton);
		gui.add(jp,BorderLayout.SOUTH);
		gui.postInDesktopPane();
		return gui.getImageListForm();
	}
	
	private InternalFrameWithConsole console;
	public void showConsole(){		
		console = new InternalFrameWithConsole();
		mainDesktopPane.add(console);
		console.setVisible(true);
	}

	private JInternalFrame listaComercialSolutionsFrame;
	
	public void listCommercialSolutions(){
		//
		if (listaComercialSolutionsFrame == null){
			listaComercialSolutionsFrame = new ListaComercialSolutionsFrame();			
			mainDesktopPane.add(listaComercialSolutionsFrame);
			if (mainDesktopPane.getComponentCount()>0){
				listaComercialSolutionsFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			listaComercialSolutionsFrame.setVisible(true);
		}else{
			listaComercialSolutionsFrame.toFront();
		}		
	}
	
	private JInternalFrame listaTrainingSolutionsFrame;
	
	public void listTrainingSolutions(){
		//
		if (listaTrainingSolutionsFrame == null){
			listaTrainingSolutionsFrame = new ListaTrainingSolutionsFrame();			
			mainDesktopPane.add(listaTrainingSolutionsFrame);
			if (mainDesktopPane.getComponentCount()>0){
				listaTrainingSolutionsFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			listaTrainingSolutionsFrame.setVisible(true);
		}else{
			listaTrainingSolutionsFrame.toFront();
		}		
	}
	
	private JInternalFrame listaSistemaSolutionsFrame;
	
	public void listSystemSolutions(){
		if (listaSistemaSolutionsFrame == null){
			listaSistemaSolutionsFrame = new ListaSistemaSolutionsFrame();			
			mainDesktopPane.add(listaSistemaSolutionsFrame);
			if (mainDesktopPane.getComponentCount()>0){
				listaSistemaSolutionsFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			listaSistemaSolutionsFrame.setVisible(true);
		}else{
			listaSistemaSolutionsFrame.toFront();
		}		
	}
	
	private CadastrarImagemInternalFrame cadastrarImagemFrame;
	
	public void novaImagem() {
		
		beginStatusBar("Cadastrar imagem");
		if (cadastrarImagemFrame == null){
			cadastrarImagemFrame = new CadastrarImagemInternalFrame();
			mainDesktopPane.add(cadastrarImagemFrame);			
			if (mainDesktopPane.getComponentCount()>0){
				cadastrarImagemFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			cadastrarImagemFrame.getImagemCadastreForm().newRegister();
			cadastrarImagemFrame.setVisible(true);
		}else{
			cadastrarImagemFrame.getImagemCadastreForm().newRegister();
			cadastrarImagemFrame.toFront();
		}
		endStatusBar("Cadastrar imagem");
	}

	public void editarImagem(final Imagem im) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					
					beginStatusBar("Editar imagem");
					try {
						if (cadastrarImagemFrame == null){
							cadastrarImagemFrame = new CadastrarImagemInternalFrame();
							mainDesktopPane.add(cadastrarImagemFrame);			
							if (mainDesktopPane.getComponentCount()>0){
								cadastrarImagemFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
							}
							
							cadastrarImagemFrame.getImagemCadastreForm().editRegister(im);
							cadastrarImagemFrame.setVisible(true);
						}
						else{
							cadastrarImagemFrame.getImagemCadastreForm().editRegister(im);
							cadastrarImagemFrame.toFront();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					endStatusBar("Editar imagem");
					
					
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	private CadastrarComercialSolutionInternalFrame cadastrarComercialSolutionFrame;
	
	public void newComercialSolution() {
		beginStatusBar("Cadastrar Soluções");
		if (cadastrarComercialSolutionFrame == null){
			cadastrarComercialSolutionFrame = new CadastrarComercialSolutionInternalFrame();
			mainDesktopPane.add(cadastrarComercialSolutionFrame);			
			if (mainDesktopPane.getComponentCount()>0){
				cadastrarComercialSolutionFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			cadastrarComercialSolutionFrame.getComercialSolutionCadastreForm().newRegister();
			cadastrarComercialSolutionFrame.setVisible(true);
		}else{
			cadastrarComercialSolutionFrame.getComercialSolutionCadastreForm().newRegister();
			cadastrarComercialSolutionFrame.toFront();
		}
		endStatusBar("Cadastrar Soluções");
	}

	public void editComercialSolution(final ComercialSolution p) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);					

					beginStatusBar("Editar Solução");
					try {
						if (cadastrarComercialSolutionFrame == null){
							cadastrarComercialSolutionFrame = new CadastrarComercialSolutionInternalFrame();
							mainDesktopPane.add(cadastrarComercialSolutionFrame);			
							if (mainDesktopPane.getComponentCount()>0){
								cadastrarComercialSolutionFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
							}
							
							cadastrarComercialSolutionFrame.getComercialSolutionCadastreForm().editRegister(p);
							cadastrarComercialSolutionFrame.setVisible(true);
						}
						else{
							cadastrarComercialSolutionFrame.getComercialSolutionCadastreForm().editRegister(p);
							cadastrarComercialSolutionFrame.toFront();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					endStatusBar("Editar Solução");
					
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	private CadastrarSoftwareSolutionInternalFrame cadastrarSoftwareSolutionFrame;
	
	public void newSoftwareSolution() {
		beginStatusBar("Cadastrar Software");
		if (cadastrarSoftwareSolutionFrame == null){
			cadastrarSoftwareSolutionFrame = new CadastrarSoftwareSolutionInternalFrame();
			mainDesktopPane.add(cadastrarSoftwareSolutionFrame);			
			if (mainDesktopPane.getComponentCount()>0){
				cadastrarSoftwareSolutionFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			cadastrarSoftwareSolutionFrame.getComercialSolutionCadastreForm().newRegister();
			cadastrarSoftwareSolutionFrame.setVisible(true);
		}else{
			cadastrarSoftwareSolutionFrame.getComercialSolutionCadastreForm().newRegister();
			cadastrarSoftwareSolutionFrame.toFront();
		}
		endStatusBar("Cadastrar Software");
	}
	
	private CadastroDomainInterestInternalFrame dominioFrame;
	public void newSoftwareDomain() {
		beginStatusBar("Cadastrar Domínios de Software");
		//if (dominioFrame == null){
			dominioFrame = new CadastroDomainInterestInternalFrame();
			mainDesktopPane.add(dominioFrame);			
			if (mainDesktopPane.getComponentCount()>0){
				dominioFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			dominioFrame.newRegister();
			dominioFrame.setVisible(true);
		/*}else{
			dominioFrame.newRegister();
			dominioFrame.toFront();
		}*/
		endStatusBar("Cadastrar Domínios de Software");
	}

	public void editSoftwareSolution(final SoftwareSolution p) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					
					
					beginStatusBar("Editar Software");
					try {
						//if (cadastrarSoftwareSolutionFrame == null){
							cadastrarSoftwareSolutionFrame = new CadastrarSoftwareSolutionInternalFrame();
							mainDesktopPane.add(cadastrarSoftwareSolutionFrame);			
							if (mainDesktopPane.getComponentCount()>0){
								cadastrarSoftwareSolutionFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
							}
							cadastrarSoftwareSolutionFrame.getComercialSolutionCadastreForm().editRegister(p);
							cadastrarSoftwareSolutionFrame.setTitle("Editando o Software .:" + p.getNome()+":.");
							cadastrarSoftwareSolutionFrame.setVisible(true);
						/*}
						else{
							cadastrarSoftwareSolutionFrame.getComercialSolutionCadastreForm().editRegister(p);
							cadastrarSoftwareSolutionFrame.toFront();
						}*/
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					endStatusBar("Editar Software");		
				
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		
		};
		
		sw.start();
	}
	
	
	private CadastrarTrainingSolutionInternalFrame cadastrarTrainingSolutionFrame;
	
	public void newTrainingSolution() {
		beginStatusBar("Cadastrar Treinamentos");
		if (cadastrarTrainingSolutionFrame == null){
			cadastrarTrainingSolutionFrame = new CadastrarTrainingSolutionInternalFrame();
			mainDesktopPane.add(cadastrarTrainingSolutionFrame);			
			if (mainDesktopPane.getComponentCount()>0){
				cadastrarTrainingSolutionFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			cadastrarTrainingSolutionFrame.getComercialSolutionCadastreForm().newRegister();
			cadastrarTrainingSolutionFrame.setVisible(true);
		}else{
			cadastrarTrainingSolutionFrame.getComercialSolutionCadastreForm().newRegister();
			cadastrarTrainingSolutionFrame.toFront();
		}
		endStatusBar("Cadastrar Treinamentos");
	}

	public void editTrainingSolution(final TrainingSolution p) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					
					beginStatusBar("Editar Treinamento");
					try {
						if (cadastrarTrainingSolutionFrame == null){
							cadastrarTrainingSolutionFrame = new CadastrarTrainingSolutionInternalFrame();
							mainDesktopPane.add(cadastrarTrainingSolutionFrame);			
							if (mainDesktopPane.getComponentCount()>0){
								cadastrarTrainingSolutionFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
							}
							cadastrarTrainingSolutionFrame.getComercialSolutionCadastreForm().editRegister(p);
							cadastrarTrainingSolutionFrame.setVisible(true);
						}
						else{
							cadastrarTrainingSolutionFrame.getComercialSolutionCadastreForm().editRegister(p);
							cadastrarTrainingSolutionFrame.toFront();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					endStatusBar("Editar Treinamento");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public CadastroTreinamentoInternalFrame lastCadastroTreinamentoInternalFrame;
	
	public void novoTreinamento(){
		beginStatusBar("Cadastrar treinamentos");
		if (lastCadastroTreinamentoInternalFrame != null){
			lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().newRegister();
			lastCadastroTreinamentoInternalFrame.postInDesktopPane();
		}else{
			CadastroTreinamentoInternalFrame gui = new CadastroTreinamentoInternalFrame();
			//gui.getLoteCadastreForm().newRegister();
			gui.postInDesktopPane();
			lastCadastroTreinamentoInternalFrame = gui;
			if (mainDesktopPane.getComponentCount()>0){
				//gui.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
		}
		endStatusBar("Cadastrar treinamentos");
	}
	
	public void cadastrarContato() {
		beginStatusBar("Cadastrar contatos");
		if (lastCadastroContatoInternalFrame != null){
			lastCadastroContatoInternalFrame.newRegister();
			lastCadastroContatoInternalFrame.postInDesktopPane();
		}else{
			CadastroContatoInternalFrame gui = new CadastroContatoInternalFrame();
			gui.postInDesktopPane();
			lastCadastroContatoInternalFrame = gui;
			if (mainDesktopPane.getComponentCount()>0){
				gui.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
		}
		endStatusBar("Cadastrar contatos");
	}
	
	public void editarContatos(final ContatoTreinamento c) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					
					beginStatusBar("Editar contatos");
					if (lastCadastroContatoInternalFrame != null){
						lastCadastroContatoInternalFrame.editRegister(c);
						lastCadastroContatoInternalFrame.postInDesktopPane();
					}else{
						CadastroContatoInternalFrame gui = new CadastroContatoInternalFrame();
						gui.editRegister(c);
						gui.postInDesktopPane();
						lastCadastroContatoInternalFrame = gui;
						if (mainDesktopPane.getComponentCount()>0){
							gui.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
						}
					}
					endStatusBar("Editar contatos");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public CadastroContatoInternalFrame lastCadastroContatoInternalFrame;
	
	public void listarContatos() {
		beginStatusBar("Listar contatos");
		if (lastListaComarcasInternalFrame != null){
			lastListaComarcasInternalFrame.postInDesktopPane();
		}else{
			ListaContatosInternalFrame gui = new ListaContatosInternalFrame();
			gui.postInDesktopPane();
			lastListaComarcasInternalFrame = gui;
			if (mainDesktopPane.getComponentCount()>0){
				gui.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
		}
		endStatusBar("Listar contatos");
	}

	public ListaContatosInternalFrame lastListaComarcasInternalFrame;
	
	public void editarTreinamento(final Treinamento t) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					
					beginStatusBar("Editar treinamento");
					if (lastCadastroTreinamentoInternalFrame != null){
						lastCadastroTreinamentoInternalFrame.editRegister(t);
						lastCadastroTreinamentoInternalFrame.postInDesktopPane();
					}else{
						CadastroTreinamentoInternalFrame gui = new CadastroTreinamentoInternalFrame();
						gui.getTreinamentoCadastreForm().editRegister(t);
						gui.postInDesktopPane();
						lastCadastroTreinamentoInternalFrame = gui;
					}		
					endStatusBar("Editar treinamento");

					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
		
		};
		
		sw.start();
	}
	
	public void editarTreinamento(final Treinamento t, final String dates) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);					
					beginStatusBar("Editar treinamento");
					if (lastCadastroTreinamentoInternalFrame != null){
						lastCadastroTreinamentoInternalFrame.editRegister(t,dates);
						lastCadastroTreinamentoInternalFrame.postInDesktopPane();
					}else{
						CadastroTreinamentoInternalFrame gui = new CadastroTreinamentoInternalFrame();
						gui.editRegister(t,dates);
						gui.postInDesktopPane();
						lastCadastroTreinamentoInternalFrame = gui;
					}		
					endStatusBar("Editar treinamento");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public FechamentoTreinamentoInternalFrame fechamentoTreinamentoInternalFrame;
	
	public void editarFechamentoTreinamento() {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);		
					beginStatusBar("Fechamento de treinamento");
					if (fechamentoTreinamentoInternalFrame != null){
						fechamentoTreinamentoInternalFrame.postInDesktopPane();
					}else{
						FechamentoTreinamentoInternalFrame gui = new FechamentoTreinamentoInternalFrame();
						gui.postInDesktopPane();
						fechamentoTreinamentoInternalFrame = gui;
					}		
					endStatusBar("Fechamento de treinamento");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	
	public void editarFechamentoTreinamento(final Treinamento t) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);			
					beginStatusBar("Editar fechamento de treinamento");
					if (fechamentoTreinamentoInternalFrame != null){
						fechamentoTreinamentoInternalFrame.editRegister(t);
						fechamentoTreinamentoInternalFrame.postInDesktopPane();
					}else{
						FechamentoTreinamentoInternalFrame gui = new FechamentoTreinamentoInternalFrame();
						gui.getFechamentoTreinamentoForm().editRegister(t);
						gui.postInDesktopPane();
						fechamentoTreinamentoInternalFrame = gui;
					}		
					endStatusBar("Editar fechamento de treinamento");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public void editarFechamentoTreinamento(final Treinamento t, final String dates) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					beginStatusBar("Editar fechamento de treinamento");
					if (fechamentoTreinamentoInternalFrame != null){
						fechamentoTreinamentoInternalFrame.editRegister(t,dates);
						fechamentoTreinamentoInternalFrame.postInDesktopPane();
					}else{
						FechamentoTreinamentoInternalFrame gui = new FechamentoTreinamentoInternalFrame();
						gui.getFechamentoTreinamentoForm().editRegister(t,dates);
						gui.postInDesktopPane();
						fechamentoTreinamentoInternalFrame = gui;
					}		
					endStatusBar("Editar fechamento de treinamento");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public RelatorioTreinamentosInternalFrame lastRelatorioTreinamentos;
	
	public void relatorioTreinamentos(){
		beginStatusBar("Relatório de treinamentos");
		if (lastRelatorioTreinamentos != null){
			lastRelatorioTreinamentos.postInDesktopPane();
		}else{
			RelatorioTreinamentosInternalFrame gui = new RelatorioTreinamentosInternalFrame();
			gui.postInDesktopPane();
			lastRelatorioTreinamentos = gui;
		}
		endStatusBar("Relatório de treinamentos");
	}
	
	public RelatorioTurmasInternalFrame lastRelatorioTurmas;
	
	public void relatorioTurmas(){
		beginStatusBar("Relatório de turmas");
		if (lastRelatorioTurmas != null){
			lastRelatorioTurmas.postInDesktopPane();
		}else{
			RelatorioTurmasInternalFrame gui = new RelatorioTurmasInternalFrame();
			gui.postInDesktopPane();
			lastRelatorioTurmas = gui;
		}
		endStatusBar("Relatório de turmas");
	}
	
	public void comercialSolutionsList() {
		listCommercialSolutions();
	}

	public void comercialSolutionCadastre() {
		newComercialSolution();
	}
	
	private CategoriaInternalFrame categoriaInternalFrame;
	public void cadastrarCategorias() {
		beginStatusBar("Cadastrar Categoria");
		if (categoriaInternalFrame == null){
			categoriaInternalFrame = CategoriaInternalFrame.getInstance();//new CategoriaInternalFrame();		
			if (mainDesktopPane.getComponentCount()>0){
				categoriaInternalFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			mainDesktopPane.add(categoriaInternalFrame);			
		}
		if (!categoriaInternalFrame.isVisible()) categoriaInternalFrame.setVisible(true);
		categoriaInternalFrame.toFront();
		endStatusBar("Cadastrar Categoria");
	}
	
	public void editarCategorias() {
		cadastrarCategorias();
	}
	
	private FuncionarioCadastreInternalFrame funcionarioCadastreFrame;
	public void cadastrarFuncionario(){
		beginStatusBar("Cadastrar Funcionário");
		if (funcionarioCadastreFrame == null){
			funcionarioCadastreFrame = new FuncionarioCadastreInternalFrame();
			funcionarioCadastreFrame.getCadastrarUsuarioFuncionario().newRegister();
			if (mainDesktopPane.getComponentCount()>0){
				funcionarioCadastreFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			funcionarioCadastreFrame.postInDesktopPane();
			
		}else{
			funcionarioCadastreFrame.getCadastrarUsuarioFuncionario().newRegister();
			funcionarioCadastreFrame.postInDesktopPane();
		}
		endStatusBar("Cadastrar Funcionário");
	}
	
	
	
	public void editarFuncionario(final Usuario u) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					if (funcionarioCadastreFrame == null) {
						cadastrarFuncionario();
						funcionarioCadastreFrame.getCadastrarUsuarioFuncionario().setUsuario(u);
						funcionarioCadastreFrame.getCadastrarUsuarioFuncionario().editRegister();
					}else{
						beginStatusBar("Editar Funcionário");
						funcionarioCadastreFrame.getCadastrarUsuarioFuncionario().setUsuario(u);
						funcionarioCadastreFrame.getCadastrarUsuarioFuncionario().editRegister();
						funcionarioCadastreFrame.postInDesktopPane();
						endStatusBar("Editar Funcionário");
					}
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
		
	}
	
	
	private RepresentanteLegalCadastreDialog representanteCadastreFrame;
	public void cadastrarRepresentante(){
		//beginStatusBar("Cadastrar Representante Legal");
		if (representanteCadastreFrame == null){
			representanteCadastreFrame = new RepresentanteLegalCadastreDialog();
			representanteCadastreFrame.getCadastrarUsuarioRepresentanteLegal().newRegister();
			representanteCadastreFrame.setVisible(true);
			/*if (mainDesktopPane.getComponentCount()>0){
				representanteCadastreFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			representanteCadastreFrame.postInDesktopPane();*/
			
		}else{
			representanteCadastreFrame.getCadastrarUsuarioRepresentanteLegal().newRegister();
			representanteCadastreFrame.setVisible(true);
			//representanteCadastreFrame.postInDesktopPane();
		}
		//endStatusBar("Cadastrar Representante Legal");
	}
	
	public void editarRepresentante(final RepresentanteLegal u) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					try{
						RepresentanteLegal rl = (RepresentanteLegal) RemoteServicesUtility.getInstance().load(RepresentanteLegal.class, u.getId());
						Endereco ender = RemotePessoaService.getInstance().getEnderecoByPessoaId(rl.getId());
						rl.setEndereco(ender);
					}catch(Exception ex){
						ex.printStackTrace();
					}		
					if (representanteCadastreFrame == null) {
						cadastrarRepresentante();
						representanteCadastreFrame.getCadastrarUsuarioRepresentanteLegal().setRepresentante(u);
						representanteCadastreFrame.getCadastrarUsuarioRepresentanteLegal().editRegister();
					}else{
						beginStatusBar("Editar Representante Legal");
						representanteCadastreFrame.getCadastrarUsuarioRepresentanteLegal().setRepresentante(u);
						representanteCadastreFrame.getCadastrarUsuarioRepresentanteLegal().editRegister();
						representanteCadastreFrame.setVisible(true);			
					}
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}

	
	public void cadastrarInstrutor(){
		try {
			InstrutorCadastreDialog gui = new InstrutorCadastreDialog();
			gui.getCadastroInstrutorForm().newRegister();
			gui.setVisible(true);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void editarInstrutor(final Usuario u) {
		
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					InstrutorCadastreDialog gui = new InstrutorCadastreDialog();
					gui.getCadastroInstrutorForm().setUsuario(u);
					gui.getCadastroInstrutorForm().editRegister();
					jd.stop();
					gui.setVisible(true);				
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public void editarInstrutor(final Instrutor l) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					InstrutorCadastreDialog gui = new InstrutorCadastreDialog();
					gui.getCadastroInstrutorForm().setInstrutor(l);
					gui.getCadastroInstrutorForm().editRegister();
					jd.stop();
					gui.setVisible(true);	
			
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public void cadastrarComitente(final PersonType pt){
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					ComitenteCadastreDialog gui = new ComitenteCadastreDialog(pt);
					gui.getCadastrarComitenteForm().newRegister();
					gui.setVisible(true);
					/*gui.postInDesktopPane();
					if (mainDesktopPane.getComponentCount()>0){
						gui.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
					}*/
					
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public void editarComitente(final PessoaEmDivulgacao u, final PersonType pt) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					ComitenteCadastreDialog gui = new ComitenteCadastreDialog(pt);
					gui.getCadastrarComitenteForm().setComitente(u);
					gui.getCadastrarComitenteForm().editRegister(u);
					//gui.postInDesktopPane();
					gui.setVisible(true);
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	

	public void cadastrarComitenteParticipante(PersonType pt){
		ParticipanteCadastreDialog gui = new ParticipanteCadastreDialog(pt);
		gui.getCadastrarComitenteForm().newRegister();
		gui.setVisible(true);
		/*gui.postInDesktopPane();
		if (mainDesktopPane.getComponentCount()>0){
			gui.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
		}*/
	}
	
	public void editarParticipante(final Usuario u, final PersonType pt) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					ParticipanteCadastreDialog gui = new ParticipanteCadastreDialog(pt);
					gui.getCadastrarComitenteForm().setUsuario(u);
					gui.getCadastrarComitenteForm().editRegister();
					//gui.postInDesktopPane();
					jd.stop();
					gui.setVisible(true);
			
					//jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public void listarUsuarios() {
		beginStatusBar("Listar Usuários");
		javax.swing.JInternalFrame gui = new javax.swing.JInternalFrame("Listar Usuários");
		
		gui.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		gui.setLayout(new java.awt.BorderLayout());
		gui.setSize(new java.awt.Dimension(633,503));
		UsuarioListForm u = new UsuarioListForm();
		//u.pesquisar();
		gui.add(u,java.awt.BorderLayout.CENTER);
		gui.setIconifiable(true);
		gui.setResizable(true);
		gui.setClosable(true);
		
		mainDesktopPane.add(gui);
		gui.setVisible(true);
		gui.toFront();
		endStatusBar("Listar Usuários");
	}
	
	TurmaCadastreInternalFrame turmaCadastreFrame;
	public void cadastrarTurma(){
		if (turmaCadastreFrame == null){
			turmaCadastreFrame = new TurmaCadastreInternalFrame();
		}
		turmaCadastreFrame.postInDesktopPane();
	}
	
	public void editarTurma(final TurmaTreinamento l){
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					if (turmaCadastreFrame == null){
						turmaCadastreFrame = new TurmaCadastreInternalFrame();
						turmaCadastreFrame.editRegister(l);			
					}
					turmaCadastreFrame.postInDesktopPane();
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}

	public void displayErrorMsg(String message) {
		getBottomPanel().displayErrorMsg(message);
	}
	
	public void appendErrorMsg(String message){
		getBottomPanel().appendErrorMsg(message);
	}
	
	public void cleanErrorMsg(){
		getBottomPanel().cleanErrorMsg();
	}

	public void setOperationMessage(String string) {
		getBottomPanel().setOperationMessage(string);
	}
	
	public void showOperationSucess(){
		JOptionPane.showMessageDialog(this,"A operação foi realizada com sucesso!", "Operação realizada com sucesso", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showOperationSucess(String title){
		JOptionPane.showMessageDialog(this,"A operação foi realizada com sucesso!", title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showOperationSucess(String title, String msg){
		JOptionPane.showMessageDialog(this,msg, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void showErrorCamposInvalidos(){
		JOptionPane.showMessageDialog(this,"O formulário não foi preenchido corretamente!", "Dados incorretos", JOptionPane.ERROR_MESSAGE);
	}
	
	public void showErrorCamposInvalidosWithinTabs(){
		JOptionPane.showMessageDialog(this,"O formulário não foi preenchido corretamente!", "Dados incorretos nas abas em destaque", JOptionPane.ERROR_MESSAGE);
	}
	
	public void showErrorCamposInvalidos(String title){
		JOptionPane.showMessageDialog(this,"O formulário não foi preenchido corretamente!", title, JOptionPane.ERROR_MESSAGE);
	}
	
	public void showErrorDialog(String title, String msg){
		JOptionPane.showMessageDialog(this,msg, title, JOptionPane.ERROR_MESSAGE);
	}
	
	public void showWarningDialog(String title, String msg){
		JOptionPane.showMessageDialog(this,msg, title, JOptionPane.WARNING_MESSAGE);
	}

	public void reportWarning(String string) {
		getBottomPanel().showWarningMsg(string);
	}

	public CadastroFormacaoInternalFrame lastCadastroFormacaoInternalFrame;
	
	public void novaFormacao(){
		beginStatusBar("Cadastrar formações");
		if (lastCadastroFormacaoInternalFrame != null){
			lastCadastroFormacaoInternalFrame.getFormacaoCadastreForm().newRegister();
			lastCadastroFormacaoInternalFrame.postInDesktopPane();
		}else{
			CadastroFormacaoInternalFrame gui = new CadastroFormacaoInternalFrame();
			gui.postInDesktopPane();
			lastCadastroFormacaoInternalFrame = gui;			
		}
		endStatusBar("Cadastrar formações");
	}
	
	public void editarFormacao(final FormacaoTreinamento f){
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					beginStatusBar("Editar formações");
					if (lastCadastroFormacaoInternalFrame != null){
						lastCadastroFormacaoInternalFrame.getFormacaoCadastreForm().editRegister(f);
						lastCadastroFormacaoInternalFrame.postInDesktopPane();
					}else{
						CadastroFormacaoInternalFrame gui = new CadastroFormacaoInternalFrame();
						gui.getFormacaoCadastreForm().editRegister(f);
						gui.postInDesktopPane();
						lastCadastroFormacaoInternalFrame = gui;			
					}
					endStatusBar("Editar formações");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public JInternalFrame getListComercialSolutionsFrame() {
		return listaComercialSolutionsFrame;
	}

	public void setListComercialSolutionFrame(JInternalFrame listaProdutosFrame) {
		this.listaComercialSolutionsFrame = listaProdutosFrame;
	}
	
	public void setListTrainingSolutionFrame(JInternalFrame listaProdutosFrame) {
		this.listaTrainingSolutionsFrame = listaProdutosFrame;
	}
	
	public void setListSistemaSolutionFrame(JInternalFrame listaProdutosFrame) {
		this.listaSistemaSolutionsFrame = listaProdutosFrame;
	}

	public CadastrarComercialSolutionInternalFrame getCadastrarComercialSolutionFrame() {
		return cadastrarComercialSolutionFrame;
	}

	public void setCadastrarComercialSolutionFrame(
			CadastrarComercialSolutionInternalFrame cadastrarProdutoFrame) {
		this.cadastrarComercialSolutionFrame = cadastrarProdutoFrame;
	}
	
	public void setCadastrarTrainingSolutionFrame(
			CadastrarTrainingSolutionInternalFrame cadastrarProdutoFrame) {
		this.cadastrarTrainingSolutionFrame = cadastrarProdutoFrame;
	}
	
	public void setCadastrarSoftwareSolutionFrame(
			CadastrarSoftwareSolutionInternalFrame cadastrarProdutoFrame) {
		this.cadastrarSoftwareSolutionFrame = cadastrarProdutoFrame;
	}

	public CadastroTreinamentoInternalFrame getLastCadastroTreinamentoInternalFrame() {
		return lastCadastroTreinamentoInternalFrame;
	}

	public void setLastCadastroTreinamentoInternalFrame(
			CadastroTreinamentoInternalFrame lastCadastroInternalFrame) {
		this.lastCadastroTreinamentoInternalFrame = lastCadastroInternalFrame;
	}
	
	public void setLastCadastroFormacaoInternalFrame(
			CadastroFormacaoInternalFrame lastCadastroInternalFrame) {
		this.lastCadastroFormacaoInternalFrame = lastCadastroInternalFrame;
	}

	public RelatorioTreinamentosInternalFrame getLastRelatorioTreinamentos() {
		return lastRelatorioTreinamentos;
	}

	public void setLastRelatorioTreinamentos(RelatorioTreinamentosInternalFrame lastRelatorioLotes) {
		this.lastRelatorioTreinamentos = lastRelatorioLotes;
	}

	public CategoriaInternalFrame getCategoriaInternalFrame() {
		return categoriaInternalFrame;
	}

	public void setCategoriaInternalFrame(
			CategoriaInternalFrame categoriaInternalFrame) {
		this.categoriaInternalFrame = categoriaInternalFrame;
	}

	public FuncionarioCadastreInternalFrame getFuncionarioCadastreFrame() {
		return funcionarioCadastreFrame;
	}

	public void setFuncionarioCadastreFrame(
			FuncionarioCadastreInternalFrame funcionarioCadastreFrame) {
		this.funcionarioCadastreFrame = funcionarioCadastreFrame;
	}
	
	public void setRepresentanteLegalCadastreFrame(
			RepresentanteLegalCadastreDialog funcionarioCadastreFrame) {
		this.representanteCadastreFrame = funcionarioCadastreFrame;
	}

	public TurmaCadastreInternalFrame getTurmaCadastreFrame() {
		return turmaCadastreFrame;
	}

	public void setTurmaCadastreFrame(
			TurmaCadastreInternalFrame leilaoCadastreFrame) {
		this.turmaCadastreFrame = leilaoCadastreFrame;
	}

	public Date format(String date) {
		try {
			DatePropertyEditor dt = new DatePropertyEditor();
			
			dt.setFormat(messages.getMessage("formats.date"));
			dt.setAsText(date);			
			return ((Date)dt.getValue());
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			
		}
		return null;
	}

	public String format(Date date) {
		try {
			DatePropertyEditor dt = new DatePropertyEditor();
			dt.setFormat(messages.getMessage("formats.date"));
			dt.setValue(date);
			return dt.getAsText();
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			
		}
		return null;
	}
	
	public static String formatDateTime(Date date) {
		try {
			DatePropertyEditor dt = new DatePropertyEditor();
			dt.setFormat(ResourceMessage.getInstance().getMessage("formats.date"));
			dt.setValue(date);
			return dt.getAsText();
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			
		}
		return null;
	}
	
	public static String formatDateTime(Date date, String lang) {
		try {
			DatePropertyEditor dt = new DatePropertyEditor();
			if (lang.equalsIgnoreCase("EN-US")){
				dt.setFormat("MM/dd/yyyy");
				dt.setValue(date);
				return dt.getAsText().trim();
			}
			else dt.setFormat(ResourceMessage.getInstance().getMessage("formats.date"));
			dt.setValue(date);
			return dt.getAsText();
		} catch (RuntimeException e1) {
			e1.printStackTrace();
			
		}
		return null;
	}

	public ListaImagemInternalFrame getListaImagensFrame() {
		return listaImagensFrame;
	}

	private Instrutor leiloeiro;
	public Instrutor getLeiloeiro() {
		if (leiloeiro == null){
			TelaLoginUsuario login = TelaLoginUsuario.getInstance("Operação permitida somente para leiloeiros",true,false);			
			if (login.getUsuarioDTO() == null) login.setVisible(true);
			else try {
				leiloeiro = login.getLeiloeiro();
				if (leiloeiro == null){
					login.setVisible(true);
					try {
						leiloeiro = login.getLeiloeiro();
					} catch (Exception ex) {
						ex.printStackTrace();
						
					}
				}
			} catch (Exception e) {
				login.setVisible(true);
				try {
					leiloeiro = login.getLeiloeiro();
				} catch (Exception ex) {
					ex.printStackTrace();
					
				}
			}
		}
		return leiloeiro;
	}
	
	private Participante participante;
	public Participante getParticipante() {
		if (participante == null){
			TelaLoginUsuario login = TelaLoginUsuario.getInstance("Operação permitida somente para participantes",true,false);			
			if (login.getUsuarioDTO() == null) login.setVisible(true);
			else try {
				participante = login.getParticipante();
				if (participante == null){
					login.setVisible(true);
					try {
						participante = login.getParticipante();
					} catch (Exception ex) {
						ex.printStackTrace();
						
					}
				}
			} catch (Exception e) {
				login.setVisible(true);
				try {
					participante = login.getParticipante();
				} catch (Exception ex) {
					ex.printStackTrace();
					
				}
			}
		}
		return participante;
	}
	
	
	
	private Funcionario funcionario;
	public Funcionario getFuncionario() {
		if (funcionario == null){
			TelaLoginUsuario login = TelaLoginUsuario.getInstance("Operação permitida somente para funcionários",true,false);			
			if (login.getUsuarioDTO() == null) login.setVisible(true);
			else try {
				funcionario = login.getFuncionario();
				if (funcionario == null){
					login.setVisible(true);
					try {
						funcionario = login.getFuncionario();
					} catch (Exception ex) {
						ex.printStackTrace();
						
					}
				}
			} catch (Exception e) {
				login.setVisible(true);
				try {
					funcionario = login.getFuncionario();
				} catch (Exception ex) {
					ex.printStackTrace();
					
				}
			}
		}
		return funcionario;
	}
	
	private PessoaEmDivulgacao pessoaEmDivulgacao;
	public PessoaEmDivulgacao getComitente() {
		if (pessoaEmDivulgacao == null){
			TelaLoginUsuario login = TelaLoginUsuario.getInstance("Operação permitida somente para comitentes",true,false);			
			if (login.getUsuarioDTO() == null) login.setVisible(true);
			else try {
				pessoaEmDivulgacao = login.getComitente();
				if (pessoaEmDivulgacao == null){
					login.setVisible(true);
					try {
						pessoaEmDivulgacao = login.getComitente();
					} catch (Exception ex) {
						ex.printStackTrace();
						
					}
				}
			} catch (Exception e) {
				login.setVisible(true);
				try {
					pessoaEmDivulgacao = login.getComitente();
				} catch (Exception ex) {
					ex.printStackTrace();
					
				}
			}
		}
		return pessoaEmDivulgacao;
	}
	
	public void editarDadosPessoais(){
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					TelaLoginUsuario login = TelaLoginUsuario.getInstance();			
					UsuarioDTO udto = login.getUsuarioDTO();
					Usuario u = new Usuario();
					u.setLogin(udto.getId());
					u.setUserCadastreType(udto.getUserRole());
					u.setAutenticado(udto.isAuthenticated());
					u.setActive(udto.isActive());
					
					if (udto.getTipoPessoa() == PersonType.Fisica){
						if (u.getUserCadastreType().equals(UserCadastreType.Administrador_do_sistema)
							|| u.getUserCadastreType().equals(UserCadastreType.Funcionário)){
							u.setDadosPessoais(getFuncionario());
							AdapitVirtualFrame.getInstance().editarFuncionario(u);
						}else if (u.getUserCadastreType().equals(UserCadastreType.Instrutor)){
							u.setDadosPessoais(getLeiloeiro());
							AdapitVirtualFrame.getInstance().editarInstrutor(u);
						}else if  (u.getUserCadastreType().equals(UserCadastreType.Cliente)){
							try {
								u.setDadosPessoais(getParticipante());
								AdapitVirtualFrame.getInstance().editarParticipante(u,PersonType.Fisica);
								
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						else if (u.getUserCadastreType().equals(UserCadastreType.Comitente)){
						}
						else if (u.getUserCadastreType().equals(UserCadastreType.Representante_legal)){
							
						}
						else {
							u.setDadosPessoais(getParticipante());
							AdapitVirtualFrame.getInstance().editarParticipante(u,PersonType.Fisica);
						}
					}
					else{
							if  (u.getUserCadastreType().equals(UserCadastreType.Cliente)){
								try {
									u.setDadosPessoais(getParticipante());
									AdapitVirtualFrame.getInstance().editarParticipante(u,PersonType.Juridica);
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}else{
								u.setDadosPessoais(getParticipante());
								AdapitVirtualFrame.getInstance().editarParticipante(u,PersonType.Juridica);
							}
					}
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
/*	public void editarDadosPessoais(){
		TelaLoginUsuario login = TelaLoginUsuario.getInstance();			
		UsuarioDTO udto = login.getUsuarioDTO();
		Usuario u = new Usuario();
		u.setLogin(udto.getId());
		u.setRule(udto.getUserRule());
		u.setAutenticado(udto.isAuthenticated());
		u.setActive(udto.isActive());
		
		if (udto.getTipoPessoa() == PersonType.Fisica){
			if (u.getRule().equals(UserCadastreType.Administrador_do_sistema)
				|| u.getRule().equals(UserCadastreType.Funcionário)){
				u.setDadosPessoais(getFuncionario());
				LeilaoVirtualFrame.getInstance().editarFuncionario(u);
			}else if (u.getRule().equals(UserCadastreType.Leiloeiro)){
				u.setDadosPessoais(getLeiloeiro());
				LeilaoVirtualFrame.getInstance().editarLeiloeiro(u);
			}else if  (u.getRule().equals(UserCadastreType.Participante_que_efetua_lances)){
				Session s = LocalServicesUtility.getInstance().openSession();
				Object obj = s.createQuery("select es.id from ParticipanteEstrangeiro es where es.user.login=:login")
				.setParameter("login", u.getLogin()).uniqueResult();
				if (obj == null){
					u.setDadosPessoais(getParticipante());
					LeilaoVirtualFrame.getInstance().editarComitenteParticipante(u,PersonType.Fisica);
				}
				else {
					u.setDadosPessoais(getParticipanteEstrangeiro());
					LeilaoVirtualFrame.getInstance().editarEstrangeiro(u,PersonType.Fisica);
				}
			}
			else if (u.getRule().equals(UserCadastreType.Comitente)){
				//LeilaoVirtualFrame.getInstance().editarComitente(u,PersonType.Fisica);
			}
			else if (u.getRule().equals(UserCadastreType.Representante_legal)){
				
			}
			else {
				u.setDadosPessoais(getParticipante());
				LeilaoVirtualFrame.getInstance().editarComitenteParticipante(u,PersonType.Fisica);
			}
		}
		else{
				if  (u.getRule().equals(UserCadastreType.Participante_que_efetua_lances)){
					Session s = LocalServicesUtility.getInstance().openSession();
					Object obj = s.createQuery("select es.id from ParticipanteEstrangeiro es where es.user.login=:login")
					.setParameter("login", u.getLogin()).uniqueResult();
					if (obj == null){
						u.setDadosPessoais(getParticipante());
						LeilaoVirtualFrame.getInstance().editarComitenteParticipante(u,PersonType.Juridica);
					}
					else{
						u.setDadosPessoais(getParticipanteEstrangeiro());
						LeilaoVirtualFrame.getInstance().editarEstrangeiro(u,PersonType.Juridica);
					}
				}else{
					u.setDadosPessoais(getParticipante());
					LeilaoVirtualFrame.getInstance().editarComitenteParticipante(u,PersonType.Juridica);
				}
		}
	}*/

	private CategoriaSelectableTreeController treeController;
	/*public CategoriaSelectableTreeController getTreeController() {
		if (treeController == null){
			treeController = new CategoriaSelectableTreeController(this);
		}
		return treeController;
	}*/
	
	public CategoriaSelectableTreeController getTreeController(CategoriaSelectable selectable) {
		if (treeController == null){
			treeController = new CategoriaSelectableTreeController(selectable);
			return treeController;
		}
		else return new CategoriaSelectableTreeController(selectable,treeController);		
	}

	private JInternalFrame listaNewsFrame;
	
	public void listNews(){
		if (listaNewsFrame == null){
			listaNewsFrame = new ListaNewsFrame();			
			mainDesktopPane.add(listaNewsFrame);
			if (mainDesktopPane.getComponentCount()>0){
				listaNewsFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			listaNewsFrame.setVisible(true);
		}else{
			listaNewsFrame.toFront();
		}		
	}

	
	private CadastrarNewsInternalFrame cadastrarNewsFrame;
	
	public void novoNews() {
		beginStatusBar("Cadastrar news");
		if (cadastrarNewsFrame == null){
			cadastrarNewsFrame = new CadastrarNewsInternalFrame();
			mainDesktopPane.add(cadastrarNewsFrame);			
			if (mainDesktopPane.getComponentCount()>0){
				cadastrarNewsFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			cadastrarNewsFrame.getNewsCadastreForm().newRegister();
			cadastrarNewsFrame.setVisible(true);
		}else{
			cadastrarNewsFrame.getNewsCadastreForm().newRegister();
			cadastrarNewsFrame.toFront();
		}
		endStatusBar("Cadastrar news");
	}
	
	public void editarNews(final News p) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					beginStatusBar("Editar news");
					if (cadastrarNewsFrame == null){
						cadastrarNewsFrame = new CadastrarNewsInternalFrame();
						mainDesktopPane.add(cadastrarNewsFrame);			
						if (mainDesktopPane.getComponentCount()>0){
							cadastrarNewsFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
						}
						
						cadastrarNewsFrame.getNewsCadastreForm().editRegister(p);
						cadastrarNewsFrame.setVisible(true);
					}
					else{
						cadastrarNewsFrame.getNewsCadastreForm().editRegister(p);
						cadastrarNewsFrame.toFront();
					}
			
					endStatusBar("Editar news");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public void listarNews() {
		listNews();
	}

	public void cadastrarNews() {
		novoNews();
	}
	
	public JInternalFrame getListaNewsFrame() {
		return listaNewsFrame;
	}

	public void setListaNewsFrame(JInternalFrame listaNewsFrame) {
		this.listaNewsFrame = listaNewsFrame;
	}
	
	public void setCadastrarNewsFrame(
			CadastrarNewsInternalFrame cadastrarNewsFrame) {
		this.cadastrarNewsFrame = cadastrarNewsFrame;
	}

	public void setLastFechamentoLoteInternalFrame(FechamentoTreinamentoInternalFrame object) {
		this.fechamentoTreinamentoInternalFrame = object;
	}

	
	
	public ContaPagarLoteInternalFrame contaPagarLoteInternalFrame;
	
	public void editarContaPagarLote() {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					beginStatusBar("Editar Conta a Pagar Por Lote");
					if (contaPagarLoteInternalFrame != null){
						contaPagarLoteInternalFrame.postInDesktopPane();
					}else{
						ContaPagarLoteInternalFrame gui = new ContaPagarLoteInternalFrame();
						gui.postInDesktopPane();
						contaPagarLoteInternalFrame = gui;
					}		
					endStatusBar("Editar Conta a Pagar Por Lote");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	
	public void editarContaPagarLote(final Treinamento lote) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					beginStatusBar("Editar Conta a Pagar Por Lote");
					if (contaPagarLoteInternalFrame != null){
						contaPagarLoteInternalFrame.editRegister(lote);
						contaPagarLoteInternalFrame.postInDesktopPane();
					}else{
						ContaPagarLoteInternalFrame gui = new ContaPagarLoteInternalFrame();
						gui.getParticipanteContaPagarLoteForm().editRegister(lote);
						gui.postInDesktopPane();
						contaPagarLoteInternalFrame = gui;
					}		
					endStatusBar("Editar Conta a Pagar Por Lote");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public void editarContaPagarLote(final Treinamento lote, final String leilaoDates) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					beginStatusBar("Editar Conta a Pagar Por Lote");
					if (contaPagarLoteInternalFrame != null){
						contaPagarLoteInternalFrame.editRegister(lote,leilaoDates);
						contaPagarLoteInternalFrame.postInDesktopPane();
					}else{
						ContaPagarLoteInternalFrame gui = new ContaPagarLoteInternalFrame();
						gui.getParticipanteContaPagarLoteForm().editRegister(lote,leilaoDates);
						gui.postInDesktopPane();
						contaPagarLoteInternalFrame = gui;
					}		
					endStatusBar("Editar Conta a Pagar Por Lote");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public void setLastContaPagarLoteInternalFrame(ContaPagarLoteInternalFrame object) {
		this.contaPagarLoteInternalFrame = object;
	}
	
	/*public Categoria getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(Categoria selectedElement) {
		this.selectedElement = selectedElement;
	}

	
	private Categoria selectedElement;*/

	private JInternalFrame listaPublicationFrame;
	
	public void listPublication(){
		if (listaPublicationFrame == null){
			listaPublicationFrame = new ListaPublicationFrame();			
			mainDesktopPane.add(listaPublicationFrame);
			if (mainDesktopPane.getComponentCount()>0){
				listaPublicationFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			listaPublicationFrame.setVisible(true);
		}else{
			listaPublicationFrame.toFront();
		}		
	}

	
	private CadastrarPublicationInternalFrame cadastrarPublicationFrame;
	
	public void novoPublication() {
		beginStatusBar("Cadastrar Artigo");
		if (cadastrarPublicationFrame == null){
			cadastrarPublicationFrame = new CadastrarPublicationInternalFrame();
			mainDesktopPane.add(cadastrarPublicationFrame);			
			if (mainDesktopPane.getComponentCount()>0){
				cadastrarPublicationFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			cadastrarPublicationFrame.getPublicationCadastreForm().newRegister();
			cadastrarPublicationFrame.setVisible(true);
		}else{
			cadastrarPublicationFrame.getPublicationCadastreForm().newRegister();
			cadastrarPublicationFrame.toFront();
		}
		endStatusBar("Cadastrar Artigo");
	}
	
	public void editarPublication(final Publication p) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					beginStatusBar("Editar Artigo");
					if (cadastrarPublicationFrame == null){
						cadastrarPublicationFrame = new CadastrarPublicationInternalFrame();
						mainDesktopPane.add(cadastrarPublicationFrame);			
						if (mainDesktopPane.getComponentCount()>0){
							cadastrarPublicationFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
						}
						
						cadastrarPublicationFrame.getPublicationCadastreForm().editRegister(p);
						cadastrarPublicationFrame.setVisible(true);
					}
					else{
						cadastrarPublicationFrame.getPublicationCadastreForm().editRegister(p);
						cadastrarPublicationFrame.toFront();
					}
			
					endStatusBar("Editar Artigo");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public void listarPublication() {
		listPublication();
	}

	public void cadastrarPublication() {
		novoPublication();
	}
	
	public JInternalFrame getListaPublicationFrame() {
		return listaPublicationFrame;
	}

	public void setListaPublicationFrame(JInternalFrame listaPublicationFrame) {
		this.listaPublicationFrame = listaPublicationFrame;
	}
	
	public void setCadastrarPublicationFrame(
			CadastrarPublicationInternalFrame cadastrarPublicationFrame) {
		this.cadastrarPublicationFrame = cadastrarPublicationFrame;
	}

	public void destaque() {
		final JInternalFrame jif = new JInternalFrame();
		jif.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		jif.setClosable(true);
		jif.setTitle("Destaques da Página");
		DestaqueForm df = new DestaqueForm();
		jif.add(df,BorderLayout.CENTER);
		df.editRegister();
		df.getFecharButton().addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				jif.dispose();
			}
			
		});
		jif.setSize(df.getSize());
		mainDesktopPane.add(jif);			
		if (mainDesktopPane.getComponentCount()>0){
			jif.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
		}
		jif.setVisible(true);
	}

	public void showArquivosFrame() {
		final JInternalFrame jif = new JInternalFrame();
		jif.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		jif.setClosable(true);
		jif.setTitle("Manutenção de Arquivos do Servidor");
		FileFilterPanel df = new FileFilterPanel();
		jif.add(df,BorderLayout.CENTER);
		
		jif.setSize(df.getSize());
		mainDesktopPane.add(jif);			
		if (mainDesktopPane.getComponentCount()>0){
			jif.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
		}
		jif.setVisible(true);
	}
	private Imagem selectedImage = null;
	public Imagem selectImagem(){		
		final JDialog jif = new JDialog(this);
		jif.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		jif.setTitle("Buscar Imagem");
		final ImageListForm df = new ImageListForm();
		jif.add(df,BorderLayout.CENTER);
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		JButton jb = new JButton("Selecionar");
		jp.add(jb);
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				selectedImage = df.getSelectedImage();
				jif.dispose();
			}			
		});
		jif.add(jp,BorderLayout.SOUTH);
		jif.setSize(df.getSize());
		jif.setLocation(UIUtil.getScreenCenter(jif));
		jif.setModal(true);
		
		jif.setVisible(true);
		
		return selectedImage;
	}
	
	private Arquivo selectedArquivo = null;
	public Arquivo selectArquivo(){
		
		final JDialog jif = new JDialog(this);
		jif.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		jif.setTitle("Buscar Arquivo");
		final FileFilterPanel df = new FileFilterPanel();
		jif.add(df,BorderLayout.CENTER);
		JPanel jp = new JPanel();
		jp.setLayout(new FlowLayout());
		JButton jb = new JButton("Selecionar");
		jp.add(jb);
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				selectedArquivo = df.getSelectedFile();
				jif.dispose();
			}			
		});
		jif.add(jp,BorderLayout.SOUTH);
		jif.setSize(df.getSize());
		jif.setLocation(UIUtil.getScreenCenter(jif));
		jif.setModal(true);
		
		jif.setVisible(true);
		
		return selectedArquivo;
	}
	
	private CadastrarUpdateInternalFrame cadastrarUpdateFrame;
	
	public void novoUpdate(final ComercialSolution c) {
		beginStatusBar("Cadastrar versão");
		if (cadastrarUpdateFrame == null){
			cadastrarUpdateFrame = new CadastrarUpdateInternalFrame(c);
			mainDesktopPane.add(cadastrarUpdateFrame);			
			if (mainDesktopPane.getComponentCount()>0){
				cadastrarUpdateFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			cadastrarUpdateFrame.getUpdateCadastreForm().newRegister();
			cadastrarUpdateFrame.setVisible(true);
		}else{
			cadastrarUpdateFrame.getUpdateCadastreForm().newRegister();
			cadastrarUpdateFrame.toFront();
		}
		endStatusBar("Cadastrar versão");
	}
	
	public void editarUpdate(final Update p, final ComercialSolution c) {
		SwingWorker sw = new SwingWorker(){
			@Override
			public Object construct() {
				try {
					TimerMessageFrame jd = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jd.setSize(310,80);					
					jd.setVisible(true);
					beginStatusBar("Editar versão");
					if (cadastrarUpdateFrame == null){
						cadastrarUpdateFrame = new CadastrarUpdateInternalFrame(c);
						mainDesktopPane.add(cadastrarUpdateFrame);			
						if (mainDesktopPane.getComponentCount()>0){
							cadastrarUpdateFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
						}
						
						cadastrarUpdateFrame.getUpdateCadastreForm().editRegister(p);
						cadastrarUpdateFrame.setVisible(true);
					}
					else{
						cadastrarUpdateFrame.getUpdateCadastreForm().editRegister(p);
						cadastrarUpdateFrame.toFront();
					}
			
					endStatusBar("Editar versão");
			
					jd.stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			};
			
			sw.start();
	}
	
	public void listarUpdate(final ComercialSolution c) {
		listUpdate(c);
	}

	public void cadastrarUpdate(final ComercialSolution c) {
		novoUpdate(c);
	}
	
	public JInternalFrame getListaUpdateFrame() {
		return listaUpdateFrame;
	}

	public void setListaUpdateFrame(JInternalFrame listaUpdateFrame) {
		this.listaUpdateFrame = listaUpdateFrame;
	}
	
	public void setCadastrarUpdateFrame(
			CadastrarUpdateInternalFrame cadastrarUpdateFrame) {
		this.cadastrarUpdateFrame = cadastrarUpdateFrame;
	}

	private JInternalFrame listaUpdateFrame;
	
	public void listUpdate(final ComercialSolution c){
		if (listaUpdateFrame == null){
			listaUpdateFrame = new ListaUpdateFrame(c);			
			mainDesktopPane.add(listaUpdateFrame);
			if (mainDesktopPane.getComponentCount()>0){
				listaUpdateFrame.setLocation(mainDesktopPane.getComponentCount()*10,mainDesktopPane.getComponentCount()*10);
			}
			listaUpdateFrame.setVisible(true);
		}else{
			listaUpdateFrame.toFront();
		}		
	}
	
	private String css=null;
	
	public String getCss(){
		if(css == null){
			try {
				List<CssDefinition> list =  RemoteComercialSolutionService.getInstance().listCssDefinitions("default");
				if(list != null && list.size()>0){
					CssDefinition def=(CssDefinition) list.get(0);
					if(def != null)
						css = def.getStyle();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(css == null)
			return "";
		return css;
	}

	/**
	 * Cria um diálogo padrão
	 * @param title o título do diálogo
	 * @param content o painel de conteúdo
	 * @param buttons a lista dos botões
	 * @param listeners a lista dos listeners dos botões
	 * @param icons os ícones dos botões
	 * @param w width
	 * @param h heigth
	 * @param modal é modal
	 * @param center true é colocado no centro e false é maximizado
	 * @return
	 */
	public JDialog createDialog(String title, JComponent content, String buttons[], ActionListener listeners[], Icon icons[], int w, int h, boolean modal, boolean center) {
		JDialog jd = new JDialog(AdapitVirtualFrame.getInstance());
		try {
			jd.setTitle(title);
			jd.setModal(modal);
			jd.add(content,BorderLayout.CENTER);
			jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			if(buttons != null && buttons.length>0){
				JPanel jp = new JPanel(new FlowLayout());
				int i=0;
				for(String str: buttons){
					JButton jb = new JButton(str);
					jp.add(jb);
					if(listeners != null && listeners.length>= i){
						ActionListener al = listeners[i];
						if(al != null)
							jb.addActionListener(al);
					}
					if(icons != null && icons.length>= i){
						Icon icon = icons[i];
						if(icon != null)
							jb.setIcon(icon);
					}
					i++;
				}
				jd.add(jp,BorderLayout.SOUTH);
			}
			jd.setSize(w,h);
			if(!center)
				UIUtil.setFullScreen(jd);
			else jd.setLocation(UIUtil.getScreenCenter(jd));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jd;
	}
	
	/**
	 * Cria um diálogo padrão
	 * @param title o título do diálogo
	 * @param content o painel de conteúdo
	 * @param buttons a lista dos botões
	 * @param listeners a lista dos listeners dos botões
	 * @param icons os ícones dos botões
	 * @param w width
	 * @param h heigth
	 * @param modal é modal
	 * @param center true é colocado no centro e false é maximizado
	 * @return
	 */
	public JDialog createDialog(String title, JComponent content, String buttons[], ActionListener listeners[], Icon icons[], int w, int h, boolean modal, boolean center, boolean resizable) {
		JDialog jd = new JDialog(AdapitVirtualFrame.getInstance());
		try {
			jd.setTitle(title);
			jd.setModal(modal);
			jd.add(content,BorderLayout.CENTER);
			jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			if(buttons != null && buttons.length>0){
				JPanel jp = new JPanel(new FlowLayout());
				int i=0;
				for(String str: buttons){
					JButton jb = new JButton(str);
					jp.add(jb);
					if(listeners != null && listeners.length>= i){
						ActionListener al = listeners[i];
						if(al != null)
							jb.addActionListener(al);
					}
					if(icons != null && icons.length>= i){
						Icon icon = icons[i];
						if(icon != null)
							jb.setIcon(icon);
					}
					i++;
				}
				jd.add(jp,BorderLayout.SOUTH);
			}
			jd.setResizable(resizable);
			jd.setSize(w,h);
			if(!center)
				UIUtil.setFullScreen(jd);
			else jd.setLocation(UIUtil.getScreenCenter(jd));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jd;
	}
	
	/**
	 * Cria um internal frame padrão
	 * @param title o título do frame
	 * @param content o painel de conteúdo
	 * @param buttons a lista dos botões
	 * @param listeners a lista dos listeners dos botões
	 * @param icons os ícones dos botões
	 * @param w width
	 * @param h heigth
	 * @return
	 */
	public JInternalFrame createInternalFrame(String title, JComponent content, String buttons[], ActionListener listeners[], Icon icons[], int w, int h) {
		JInternalFrame jd = new JInternalFrame();
		try {
			jd.setTitle(title);
			jd.add(content,BorderLayout.CENTER);
			jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			if(buttons != null && buttons.length>0){
				JPanel jp = new JPanel(new FlowLayout());
				int i=0;
				for(String str: buttons){
					JButton jb = new JButton(str);
					jp.add(jb);
					if(listeners != null && listeners.length>= i){
						ActionListener al = listeners[i];
						if(al != null)
							jb.addActionListener(al);
					}
					if(icons != null && icons.length>= i){
						Icon icon = icons[i];
						if(icon != null)
							jb.setIcon(icon);
					}
					i++;
				}
				jd.add(jp,BorderLayout.SOUTH);
			}
			jd.setSize(w,h);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jd;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
