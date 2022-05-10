package com.adapit.portal.ui.forms.usuario;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import com.adapit.portal.entidades.DeactivationReason;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;
import com.workcase.remote.RemoteGenericService;

@SuppressWarnings("serial")
public class DeactivationReasonCadastreForm extends JPanel {

	private JTabbedPane tabbedPane;

	private JPanel deactivationDataPanel;

	private DateHourChooser dateDateFieldChooser;

	private SwingBinder binder = new SwingBinder();

	private DeactivationReason deactivationReason = new DeactivationReason();

	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();

	private boolean processFocus = true;

	protected LogErrorPanel logErrorPanel;

	private JLabel dateDateFieldChooserLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JPanel motivoDestaivacaoPanel;

	private JLabel reasonLabel;

	private JTextArea reasonTextArea;

	private JPanel confirmationButtonsPanel;

	private JButton confirmarButton;

	private JButton cancelButton;

	private JPanel historicoDesativacaoPanel;

	private JScrollPane historicoDesativacaoTableScrollPane;

	private HistoricoDesativacaoTable historicoDesativacaoTable;

	private JPanel tableButtons;

	private JButton editarTableButton;

	private JButton salvarButton;

	private JPanel tableInformationPanel;

	private JLabel motivoFromTableLabel;

	private JTextArea motivoFromTableTextArea;
	
	private Usuario usuario = null;
	
	public enum Status{Confirm,Cancel,Activate,Other};
	
	public Status status=Status.Other;

	public DeactivationReasonCadastreForm() {
		initialize();
	}

	private void initialize() {
		setSize(new Dimension(580, 418));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getTabbedPane());
		// Put such code into the end of initialize body
		newRegister();
		this.setErrorIcon(false);
		// End
	}

	protected JTabbedPane getTabbedPane() {

		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.setSize(new Dimension(537, 386));
			tabbedPane.setLocation(new java.awt.Point(20, 20));

			tabbedPane
					.add(getDeactivationDataPanel(), "Dados de desativação");
			tabbedPane.add(getHistoricoDesativacaoPanel(),
					"Histórico de desativações");
		}
		return tabbedPane;
	}

	protected JPanel getDeactivationDataPanel() {

		if (deactivationDataPanel == null) {
			deactivationDataPanel = new JPanel();
			/*deactivationDataPanel.setBorder(javax.swing.BorderFactory
					.createTitledBorder(javax.swing.BorderFactory
							.createTitledBorder("Dados para desativação")));*/
			deactivationDataPanel.setSize(new java.awt.Dimension(314, 222));
			deactivationDataPanel.setLocation(new java.awt.Point(5, 0));
			deactivationDataPanel.setLayout(null);
			deactivationDataPanel.add(getDateDateFieldChooser());
			deactivationDataPanel.add(getDateDateFieldChooserLabel());
			deactivationDataPanel.add(getMotivoDestaivacaoPanel());
			deactivationDataPanel.add(getConfirmationButtonsPanel());
			deactivationDataPanel.add(getErrorPanel(), null);
		}
		return deactivationDataPanel;
	}

	@SuppressWarnings("unchecked")
	protected JComponent getDateDateFieldChooser() {
		if (dateDateFieldChooser == null) {
			dateDateFieldChooser = new DateHourChooser(messages
					.getCurrentLocale(), false, true, false);
			// dateDateFieldChooser.setText("");
			dateDateFieldChooser.setSize(new java.awt.Dimension(180, 20));
			dateDateFieldChooser.setLocation(new java.awt.Point(150, 20));
			dateDateFieldChooser.setDate(new Date());
			this.binder.addBindProperty(this.deactivationReason, this.dateDateFieldChooser, "date");
			this.hashComps.put("date", this.dateDateFieldChooser);
			JWarningComponent warn = new JWarningComponent(
					this.dateDateFieldChooser);
			warn.setBounds(150, 20, 180, 20);
			return warn;
		}
		return dateDateFieldChooser;
	}

	public DeactivationReason validateDeactivationReasonForm() throws Exception {
		setErrorIcon(false);
		try {
			binder.bind(deactivationReason);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		deactivationReason.setReason(reasonTextArea.getText());
		deactivationReason.setDate(dateDateFieldChooser.getDate());
		if (deactivationReason.getDate() == null) System.out.println("A data é nula");
		// Manual Code
		if (!validateDeactivationReasonBean())
			throw new Exception("ErrorDeactivationReasonEmptyFields");
		return deactivationReason;
	}

	@SuppressWarnings("unchecked")
	public DeactivationReason cadastreDeactivationReason() throws Exception {
		DeactivationReason dr = validateDeactivationReasonForm();
		if (usuario != null){
			if (usuario.getDeactivationReasonList() == null) usuario.setDeactivationReasonList(new ArrayList());
			usuario.getDeactivationReasonList().add(dr);
			usuario.setActive(false);
			dr.setUsuario(usuario);
		}
		return dr;
	}

	@SuppressWarnings("unchecked")
	public boolean validateDeactivationReasonBean() {
		getErrorPanel().removeAllElements();
		if (processFocus) {
			try {
				if (UIUtil.processFocus(this)) {
					processFocus = false;
				}
			} catch (Exception e) {
				
			}
		}
		@SuppressWarnings("unused")
		Validate validate = new Validate();
		Map errors = null;/*validate.validate(this.deactivationReason,
				"deactivationReason");*/
		if (errors == null)
			return true;
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
					try {
						getErrorPanel()
								.addError(messages.getMessage(msg), comp);
						comp.setToolTipText(messages.getMessage(msg));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					List args = (List) obj[1];
					Object[][] params = new Object[args.size()][2];
					for (int j = 0; j < args.size(); j++) {
						String key = (String) args.get(j);
						params[j][0] = key;
						params[j][1] = null;
					}
					try {
						getErrorPanel().addError(
								messages.getMessage(msg, params), comp);
						comp.setToolTipText(messages.getMessage(msg, params));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}

	public void newRegister() {
		// Nunca definir um novo objeto entidade!!!
		deactivationReason.setId(0);
		deactivationReason.setReason(null);
		deactivationReason.setDate(null);
		deactivationReason.setDate(new Date());
		
		binder.reverseBind(this.deactivationReason);

		this.setErrorIcon(false);
		updateUI();
	}
	
	@SuppressWarnings("unchecked")
	public void newRegister(Usuario objUsuario) throws Exception{
		usuario = objUsuario;
		usuario.setPasswordConf(usuario.getPassword());
		try {
			inactivations = (List) usuario.getDeactivationReasonList();
			if (inactivations != null && inactivations.size()>0){
							
				getHistoricoDesativacaoTable().setElements(inactivations);
				getHistoricoDesativacaoTable().updateTable();
				//getEditarTableButton().setEnabled(true);
			}
		} catch (RuntimeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		deactivationReason.setId(0);
		deactivationReason.setReason(null);
		//deactivationReason.setDate(null);
		deactivationReason.setDate(new Date());
		
		try {
			binder.reverseBind(this.deactivationReason);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getActivateButton().setEnabled(false);
		getConfirmarButton().setEnabled(true);

		this.setErrorIcon(false);
		updateUI();
	}


	public void editRegister(DeactivationReason objDeactivationReason) {
		// Nunca passar como argumento novos objetos!!!
		if (objDeactivationReason != null)try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(
					this.deactivationReason, objDeactivationReason);
			getActivateButton().setEnabled(true);
			getConfirmarButton().setEnabled(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		binder.reverseBind(this.deactivationReason);
		this.setErrorIcon(false);
	}
	
	@SuppressWarnings("unchecked")
	private List inactivations=null;
	@SuppressWarnings("unchecked")
	public void editRegister(Usuario objUsuario) {
		// Nunca passar como argumento novos objetos!!!
		usuario = objUsuario;
		inactivations = (List) usuario.getDeactivationReasonList();
		if (inactivations != null && inactivations.size()>0){
			Iterator it = inactivations.iterator();
			DeactivationReason objDeactivationReason=null;
			while(it.hasNext()){
				DeactivationReason d = (DeactivationReason) it.next();
				if (objDeactivationReason == null) objDeactivationReason = d; 
				if (d.getDate().after(objDeactivationReason.getDate())){
					objDeactivationReason = d; 
				}
			}
			
			if (objDeactivationReason != null)try {
				org.apache.commons.beanutils.BeanUtils.copyProperties(
						this.deactivationReason, objDeactivationReason);
				getActivateButton().setEnabled(true);
				getConfirmarButton().setEnabled(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			binder.reverseBind(this.deactivationReason);
			
			getHistoricoDesativacaoTable().setElements(inactivations);
			getHistoricoDesativacaoTable().updateTable();
			//getEditarTableButton().setEnabled(true);
		}else{
			
		}
		this.setErrorIcon(false);
	}

	public LogErrorPanel getErrorPanel() {
		if (logErrorPanel == null) {
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setBounds(new Rectangle(15, 295, 502, 52));
		}
		return logErrorPanel;
	}

	public void setErrorIcon(boolean bool) {

		this.dateDateFieldChooser.firePropertyChange("warnBorder", !bool, bool);
		this.reasonTextArea.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}

	protected JLabel getDateDateFieldChooserLabel() {

		if (dateDateFieldChooserLabel == null) {
			dateDateFieldChooserLabel = new JLabel("* " +"Data de Desativação:");
			dateDateFieldChooserLabel.setSize(new java.awt.Dimension(130, 20));
			dateDateFieldChooserLabel.setLocation(new java.awt.Point(15, 20));
			dateDateFieldChooserLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return dateDateFieldChooserLabel;
	}

	protected JPanel getMotivoDestaivacaoPanel() {

		if (motivoDestaivacaoPanel == null) {
			motivoDestaivacaoPanel = new JPanel();
			motivoDestaivacaoPanel.setSize(new Dimension(501, 208));
			motivoDestaivacaoPanel.setLocation(new java.awt.Point(15, 43));
			motivoDestaivacaoPanel.setLayout(null);
			motivoDestaivacaoPanel.add(getReasonLabel());
			motivoDestaivacaoPanel.add(getReasonScrollPane());
		}
		return motivoDestaivacaoPanel;
	}

	protected JLabel getReasonLabel() {

		if (reasonLabel == null) {
			reasonLabel = new JLabel("* " +
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.DeactivationReasonCadastreForm.Motivo"));
			reasonLabel.setSize(new Dimension(277, 20));
			reasonLabel.setLocation(new Point(1, 3));
		}
		return reasonLabel;
	}
	
	protected JScrollPane reasonScrollPane;
	
	
	protected JComponent getReasonScrollPane() {

		if (reasonScrollPane == null) {
			reasonScrollPane = new JScrollPane();
			reasonScrollPane.setSize(new Dimension(497, 174));
			reasonScrollPane.setLocation(new Point(1, 26));
			JComponent jc = getReasonTextArea();
			reasonScrollPane.add(jc);
			reasonScrollPane.setViewportView(jc);
		}
		return reasonScrollPane;
	}

	@SuppressWarnings("unchecked")
	protected JComponent getReasonTextArea() {

		if (reasonTextArea == null) {
			reasonTextArea = new JTextArea();
			reasonTextArea.setSize(new java.awt.Dimension(278, 100));
			reasonTextArea.setLocation(new java.awt.Point(5, 26));
			this.binder.addBindProperty(this.deactivationReason,
					this.reasonTextArea, "reason");

			this.hashComps.put("reason", this.reasonTextArea);
			JWarningComponent warn = new JWarningComponent(this.reasonTextArea);
			warn.setBounds(5, 26, 278, 100);
			return warn;
		}
		return reasonTextArea;
	}

	protected JPanel getConfirmationButtonsPanel() {

		if (confirmationButtonsPanel == null) {
			confirmationButtonsPanel = new JPanel();
			confirmationButtonsPanel.setSize(new Dimension(502, 33));
			confirmationButtonsPanel.setLocation(new Point(15, 260));
			confirmationButtonsPanel.setLayout(new java.awt.FlowLayout());
			confirmationButtonsPanel.add(getConfirmarButton());
			confirmationButtonsPanel.add(getCancelButton());
			confirmationButtonsPanel.add(getActivateButton());
		}
		return confirmationButtonsPanel;
	}

	public JButton getConfirmarButton() {

		if (confirmarButton == null) {
			confirmarButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.DeactivationReasonCadastreForm.Confirmar"));
			confirmarButton.setSize(new java.awt.Dimension(80, 22));
			confirmarButton.setLocation(new java.awt.Point(0, 0));
			confirmarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/user_deactive.png")));
			confirmarButton.setToolTipText("Confirma a desativação do usuário");
			confirmarButton.addActionListener(new CadastrarAction());
		}
		return confirmarButton;
	}
	
	private class CadastrarAction extends AbstractAction{
		@Override
		protected void doAction(ActionEvent e) {			
			cadastrar();			
		}
	}
	
	public void cadastrar(){
		if (usuario != null){
			try {
				
				cadastreDeactivationReason();
				usuario.setActive(false);
				
				RemoteUserService.getInstance().deactiveUsuario(usuario, deactivationReason);
				
				status = Status.Confirm;
				AdapitVirtualFrame.getInstance().showOperationSucess();
			} catch (Exception e1) {
				e1.printStackTrace();
				AdapitVirtualFrame.getInstance().showErrorCamposInvalidos();
			}
		}else System.out.println("o usuario é nulo");
	}

	protected JButton getCancelButton() {

		if (cancelButton == null) {
			cancelButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.DeactivationReasonCadastreForm.Cancelar"));
			cancelButton.setSize(new java.awt.Dimension(80, 22));
			cancelButton.setLocation(new java.awt.Point(0, 22));			
			cancelButton.setIcon(new ImageIcon(getClass().getResource("/imgs/cancel.png")));
			cancelButton.setToolTipText("Cancela a desativação do usuário");
			cancelButton.addActionListener(new AbstractAction(){
				@Override
				protected void doAction(ActionEvent e) {
					status = Status.Cancel;
				}				
			});
		}
		return cancelButton;
	}
	
	protected JButton activateButton;
	
	public JButton getActivateButton() {
		if (activateButton == null) {
			activateButton = new JButton("Ativar");
			activateButton.setSize(new java.awt.Dimension(80, 22));
			activateButton.setLocation(new java.awt.Point(0, 22));			
			activateButton.setToolTipText("Ativa o usuário para que acesse o sistema de leilão");
			activateButton.setIcon(new ImageIcon(getClass().getResource("/imgs/user_go.png")));
			activateButton.addActionListener(new AbstractAction(){
				@Override
				protected void doAction(ActionEvent e) {					
					try {
						usuario.setActive(true);
						
						RemoteUserService.getInstance().activeUsuario(usuario, deactivationReason);
						
						status = Status.Activate;
						AdapitVirtualFrame.getInstance().showOperationSucess();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}				
			});
			activateButton.setEnabled(false);
		}
		return activateButton;
	}
	protected JPanel getHistoricoDesativacaoPanel() {

		if (historicoDesativacaoPanel == null) {
			historicoDesativacaoPanel = new JPanel();
			/*historicoDesativacaoPanel.setBorder(javax.swing.BorderFactory
					.createTitledBorder(javax.swing.BorderFactory
							.createTitledBorder("Histórico de desativações")));*/
			historicoDesativacaoPanel.setSize(new java.awt.Dimension(284, 222));
			historicoDesativacaoPanel.setLocation(new java.awt.Point(324, 0));
			historicoDesativacaoPanel.setLayout(null);
			historicoDesativacaoPanel.add(getHistoricoDesativacaoTableScrollPane());
			historicoDesativacaoPanel.add(getTableButtons());
			historicoDesativacaoPanel.add(getTableInformationPanel());
		}
		return historicoDesativacaoPanel;
	}

	protected JScrollPane getHistoricoDesativacaoTableScrollPane() {

		if (historicoDesativacaoTableScrollPane == null) {
			historicoDesativacaoTableScrollPane = new JScrollPane();
			historicoDesativacaoTableScrollPane.setSize(new Dimension(498, 135));
			historicoDesativacaoTableScrollPane.setLocation(new java.awt.Point(
					15, 20));

			historicoDesativacaoTableScrollPane.add(
					getHistoricoDesativacaoTable()/*,
					"Tabela Historico Desativação"*/);
			historicoDesativacaoTableScrollPane
					.setViewportView(getHistoricoDesativacaoTable());
		}
		return historicoDesativacaoTableScrollPane;
	}

	protected HistoricoDesativacaoTable getHistoricoDesativacaoTable() {

		if (historicoDesativacaoTable == null) {
			historicoDesativacaoTable = new HistoricoDesativacaoTable();
			historicoDesativacaoTable.addFocusListener(new FocusListener(){

				public void focusGained(FocusEvent arg0) {
					editarTableButton.setEnabled(true);
					salvarButton.setEnabled(false);
				}

				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}
		return historicoDesativacaoTable;
	}

	protected JPanel getTableButtons() {

		if (tableButtons == null) {
			tableButtons = new JPanel();
			tableButtons.setSize(new Dimension(499, 32));
			tableButtons.setLocation(new Point(14, 163));
			tableButtons.setLayout(new java.awt.FlowLayout());
			tableButtons.add(getEditarTableButton());
			tableButtons.add(getSalvarButton());
		}
		return tableButtons;
	}

	private DeactivationReason selectedTableReason;
	protected JButton getEditarTableButton() {

		if (editarTableButton == null) {
			editarTableButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.DeactivationReasonCadastreForm.Editar"));
			editarTableButton.setSize(new java.awt.Dimension(80, 22));
			editarTableButton.setLocation(new java.awt.Point(0, 0));
			editarTableButton.setIcon(new ImageIcon(getClass().getResource("/imgs/page_edit.png")));
			editarTableButton.setEnabled(false);
			editarTableButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					int r = getHistoricoDesativacaoTable().getSelectedRow();
					if (r >= 0){
						selectedTableReason =  (DeactivationReason) getHistoricoDesativacaoTable().getElements().get(r);
						motivoFromTableTextArea.setText(selectedTableReason.getReason());
						salvarButton.setEnabled(true);
						editarTableButton.setEnabled(false);
					}
				}				
			});
		}
		return editarTableButton;
	}

	protected JButton getSalvarButton() {

		if (salvarButton == null) {
			salvarButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.DeactivationReasonCadastreForm.Salvar"));
			salvarButton.setSize(new java.awt.Dimension(80, 22));
			salvarButton.setLocation(new java.awt.Point(0, 22));
			salvarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/page_save.png")));
			salvarButton.setEnabled(false);
			salvarButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					if (selectedTableReason != null){
						selectedTableReason.setReason(motivoFromTableTextArea.getText());
						RemoteGenericService.getInstance().saveOrUpdate(selectedTableReason);
						getHistoricoDesativacaoTable().updateTable();
						motivoFromTableTextArea.setText("");
					}
					salvarButton.setEnabled(false);
					editarTableButton.setEnabled(false);
					selectedTableReason=null;
				}				
			});
		}
		return salvarButton;
	}

	protected JPanel getTableInformationPanel() {

		if (tableInformationPanel == null) {
			tableInformationPanel = new JPanel();
			tableInformationPanel.setSize(new Dimension(499, 159));
			tableInformationPanel.setLocation(new Point(14, 195));
			tableInformationPanel.setLayout(null);
			tableInformationPanel.add(getMotivoFromTableLabel());
			tableInformationPanel.add(getMotivoFromTableScrollPane());
		}
		return tableInformationPanel;
	}

	protected JLabel getMotivoFromTableLabel() {

		if (motivoFromTableLabel == null) {
			motivoFromTableLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.DeactivationReasonCadastreForm.Motivo"));
			motivoFromTableLabel.setSize(new java.awt.Dimension(290, 24));
			motivoFromTableLabel.setLocation(new java.awt.Point(0, 3));
		}
		return motivoFromTableLabel;
	}
	
	protected JScrollPane motivoFromTableScrollPane;

	protected JScrollPane getMotivoFromTableScrollPane() {
		if (motivoFromTableScrollPane == null) {
			motivoFromTableScrollPane = new JScrollPane();
			motivoFromTableScrollPane.setSize(new Dimension(497, 123));
			motivoFromTableScrollPane.setLocation(new Point(0, 31));
			JComponent jc = getMotivoFromTableTextArea();
			motivoFromTableScrollPane.add(jc);
			motivoFromTableScrollPane.setViewportView(jc);
		}
		return motivoFromTableScrollPane;
	}
	
	protected JTextArea getMotivoFromTableTextArea() {

		if (motivoFromTableTextArea == null) {
			motivoFromTableTextArea = new JTextArea();
			motivoFromTableTextArea.setSize(new java.awt.Dimension(292, 50));
			motivoFromTableTextArea.setLocation(new java.awt.Point(5, 30));
			return motivoFromTableTextArea;
		}
		return motivoFromTableTextArea;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(387, 322));
				gui.add(new DeactivationReasonCadastreForm(),
						java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

	@SuppressWarnings("unused")
	private static Icon getIcon(String name) {

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();
				if (image != null) {
					image = image.getScaledInstance(18, 18,
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

	@SuppressWarnings("unchecked")
	private class HistoricoDesativacaoTable extends JTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public HistoricoDesativacaoTable() {

			super();
			this.setModel(new HistoricoDesativacaoTableModel(null));
			this
					.addPropertyChangeListener(new HistoricoDesativacaoTablePropertyChangeListener());
		}

		public HistoricoDesativacaoTable(List elements) {

			super();
			this.elements = elements;
			this.setModel(new HistoricoDesativacaoTableModel(null));
			this
					.addPropertyChangeListener(new HistoricoDesativacaoTablePropertyChangeListener());
		}

		public void setDefineCellRenderers() {

			/*
			 * LabelCellRenderer labelRenderer = new LabelCellRenderer();
			 * ComboBoxCellRenderer comboBoxRenderer = new
			 * ComboBoxCellRenderer();
			 */
		}

		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][2];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.DeactivationReason) {
						com.adapit.portal.entidades.DeactivationReason deactivationReason = (com.adapit.portal.entidades.DeactivationReason) obj;
						values[i][0] = deactivationReason.getReason();
						values[i][1] = deactivationReason.getDate();
						i++;
					}
				}// End of while Loop
				setModel(new HistoricoDesativacaoTableModel(values));
				updateUI();
			} else {
				setModel(new HistoricoDesativacaoTableModel(null));
				updateUI();
			}
		}

		private class HistoricoDesativacaoTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				HistoricoDesativacaoTable jt = (HistoricoDesativacaoTable) e
						.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.DeactivationReason) {
							com.adapit.portal.entidades.DeactivationReason deactivationReason = (com.adapit.portal.entidades.DeactivationReason) obj;
							if (col == 1)
								deactivationReason
										.setReason(((java.lang.String) jt
												.getValueAt(row, col)));
							// if (col ==2) deactivationReason.setDate((new
							// Date((java.lang.String)jt.getValueAt(row, col)));
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

		private class HistoricoDesativacaoTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, Date.class };

			boolean canEdit[] = new boolean[] { true, true };

			public HistoricoDesativacaoTableModel(Object[][] values) {

				super(
						values,
						new String[] {
								messages
										.getMessage("com.adapit.portal.ui.forms.manutencaousuario.DeactivationReasonCadastreForm.Motivo"),
								messages
										.getMessage("com.adapit.portal.ui.forms.manutencaousuario.DeactivationReasonCadastreForm.Data") });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"