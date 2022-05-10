package com.adapit.portal.ui.forms.treinamento.contato;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.ContatoProcessoTreinamento;
import com.adapit.portal.entidades.ContatoTreinamento;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.services.remote.RemoteContatoService;
import com.adapit.portal.ui.forms.endereco.EnderecoCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class ContatoCadastreForm extends JPanel {

	private JComboBox nomeContatoComboBox;
	private JTextField nomeContatoTextField;

	private SwingBinder binder = new SwingBinder();

	private ContatoTreinamento contato = new ContatoTreinamento();
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap(); // @jve:decl-index=0:

	private boolean processFocus = true;

	protected Endereco endereco = new Endereco();

	protected LogErrorPanel logErrorPanel;

	private JLabel nomeTextFieldLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JTabbedPane tabbedPanePanel;

	private JPanel enderecoPanel;

	private EnderecoCadastreForm enderecoCadastreForm;

	private JPanel buttonsPanel;

	private JButton cadastrarButton;

	private JButton novoButton, removerButton, editarButton;

	private JButton adicionarProcessoButton, editarProcessoButton,
			removerProcessoButton;
	
	private Hashtable<String, ContatoTreinamento> contatos = new Hashtable<String,ContatoTreinamento>();
	private ContatoTreinamento vetComarcas[];

	public ContatoCadastreForm() {
		initialize();
		updateComarcasComboBox();
		podeInserir = true;
	}

	private void initialize() {

		nomeComLabel = new JLabel();
		nomeComLabel.setBounds(new Rectangle(16, 46, 113, 22));
		nomeComLabel.setText("Contato:");
		setSize(new Dimension(470, 400));
		setLayout(null);
		add(getNomeContatoComboBox());
		add(getNomeContatoTextField());
		add(getNomeTextFieldLabel());
		add(getTabbedPanePanel());
		add(getButtonsPanel());
		
		
		// Put such code into the end of initialize body
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		// End

		
	}

	private boolean podeInserir = false;

	protected JComponent getNomeContatoComboBox() {
		if (nomeContatoComboBox == null) {
			nomeContatoComboBox = new JComboBox();
			nomeContatoComboBox.setEditable(false);
			nomeContatoComboBox.setSize(new Dimension(314, 22));
			nomeContatoComboBox.setLocation(new Point(132, 16));

			nomeContatoComboBox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED) {
						if (podeInserir)
							editRegister();
					}
				}
			});

		}
		return nomeContatoComboBox;
	}

	@SuppressWarnings("unchecked")
	protected JComponent getNomeContatoTextField() {
		if (nomeContatoTextField == null) {
			nomeContatoTextField = new JTextField();
			nomeContatoTextField.setSize(new java.awt.Dimension(314, 22));
			nomeContatoTextField.setLocation(new java.awt.Point(132, 46));
			this.binder.addBindProperty(this.contato,
					this.nomeContatoTextField, "nome");

			this.hashComps.put("nome", this.nomeContatoTextField);
			JWarningComponent warn = new JWarningComponent(
					this.nomeContatoTextField);
			warn.setBounds(new Rectangle(132, 46, 314, 22));
			return warn;
		}
		return nomeContatoTextField;
	}

	@SuppressWarnings("unchecked")
	private void editRegister() {
		cadastrarButton.setText("Atualizar");
		removerButton.setEnabled(true);
		String nomeComarca = (String) nomeContatoComboBox.getSelectedItem();
		if (contato.getId() != 0 && !contatos.containsKey(nomeComarca))
			return;
		enderecoCadastreForm.getEndereco().setTipo(AddressType.Presencial);
		
		try {			
			ContatoTreinamento com = RemoteContatoService.getInstance().getContatoByNomeCompleto(nomeComarca);
			nomeContatoTextField.setText(com.getNome());
			Endereco e = com.getEndereco();
			contato.setId(com.getId());
			contato.setNome(com.getNome());
			contato.setEndereco(com.getEndereco());
			if (com.getProcessos() != null && com.getProcessos().size() > 0){
				contato.setProcessos(com.getProcessos());
				ArrayList arr = new ArrayList();
				arr.addAll(com.getProcessos());
				baseTable.setElements(arr);
				baseTable.updateTable();
			}else{
				contato.setProcessos(new ArrayList<ContatoProcessoTreinamento>());
				baseTable.setElements(new ArrayList<ContatoProcessoTreinamento>());
				baseTable.updateTable();
			}
			
			
			if (e != null)
				enderecoCadastreForm.editRegister(e);
			else enderecoCadastreForm.newRegister();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(ContatoCadastreForm.this,
					"Erro ao editar contato");
		} 
	}

	private void removeRegister() {
		if (contatos == null) return;
		String nomeComarca = (String) nomeContatoComboBox.getSelectedItem();
		if (contato.getId() != 0 && !contatos.containsKey(nomeComarca))
			return;		
		try {			
			ContatoTreinamento com = contatos.get(contato.getNome());
			if (com == null) return;
			RemoteContatoService.getInstance().delete(com);			
			JOptionPane.showMessageDialog(
					ContatoCadastreForm.this,
					"Contato removido com sucesso");			
			newRegister();
			updateComarcasComboBox();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(
					ContatoCadastreForm.this,
					"Erro ao deletar contato");
			
		} 
	}
	

	public ContatoTreinamento validateComarcaForm() throws Exception {
		setErrorIcon(false);
		binder.bind(contato);
		// Manual Code
		if (!validateContatoBean())
			throw new Exception("Bean Not Validated!");
		return contato;
	}

	
	public ContatoTreinamento cadastreComarca() throws Exception {		
		validateComarcaForm();
		String str = (String) nomeContatoTextField.getText();

		int resp = 0;

		if (contato.getId() == 0) {
			resp = JOptionPane.showConfirmDialog(ContatoCadastreForm.this,
					"Você quer inserir o contato " + str);

			if (resp == JOptionPane.YES_OPTION) {				
				try {					
					ContatoTreinamento com = new ContatoTreinamento();
					com.setNome(str);
					Endereco end = enderecoCadastreForm
							.cadastreEndereco();
					end.setTipo(AddressType.Presencial);
					
					RemoteContatoService.getInstance().save(com, end);
					
					updateComarcasComboBox();
					JOptionPane.showMessageDialog(ContatoCadastreForm.this,
							"Cntato cadastrado com sucesso");
					newRegister();
					return com;
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(ContatoCadastreForm.this,
							"Erro ao cadastrar contato");					
				} 
			}
		}else{
			resp = JOptionPane.showConfirmDialog(ContatoCadastreForm.this,
					"Você quer atualizar os dados desse contato?");

			if (resp == JOptionPane.YES_OPTION) {				
				try {
					Endereco end = enderecoCadastreForm
							.cadastreEndereco();
					end.setId(enderecoCadastreForm.getEndereco().getId());
					
					RemoteContatoService.getInstance().update(contato, end);
					
					updateComarcasComboBox();
					JOptionPane.showMessageDialog(ContatoCadastreForm.this,
							"Cntato atualizado com sucesso");
					newRegister();
					return contato;
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(ContatoCadastreForm.this,
							"Erro ao atualizar contato");					
				} 
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void updateComarcasComboBox() {
		podeInserir = false;		
		nomeContatoComboBox.removeAllItems();
		contatos.clear();
		try {			
			List list = RemoteContatoService.getInstance().listAllContatos();
			Iterator<ContatoTreinamento> it = list.iterator();
			vetComarcas = new ContatoTreinamento[list.size()];
			int i = 0;
			while (it.hasNext()) {
				ContatoTreinamento c = it.next();
				nomeContatoComboBox.addItem(c.getNome());
				contatos.put(c.getNome(), c);
				vetComarcas[i] = c;
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		podeInserir = true;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean validateContatoBean() {
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.contato, "contatoTreinamento");
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
		contato.setEndereco(null);
		contato.setId(0);
		contato.setNome(null);
		enderecoCadastreForm.newRegister();
		enderecoCadastreForm.getEndereco().setTipo(AddressType.Presencial);
		
		binder.reverseBind(this.contato);
		cadastrarButton.setText("Cadastrar");
		removerButton.setEnabled(false);
		nomeContatoTextField.requestFocus();
		this.setErrorIcon(false);
		updateUI();
	}

	public void editRegister(ContatoTreinamento objComarca, Endereco objEndereco) {
		// Nunca passar como argumento novos objetos!!!
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(this.contato,
					objComarca);
			org.apache.commons.beanutils.BeanUtils.copyProperties(
					this.endereco, objEndereco);
		} catch (Exception e) {
			e.printStackTrace();
		}

		binder.reverseBind(this.contato);
		this.setErrorIcon(false);

		binder.reverseBind(this.endereco);
		this.setErrorIcon(false);
	}

	public LogErrorPanel getErrorPanel() {
		if (logErrorPanel == null) {
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setSize(new Dimension(429, 50));
			logErrorPanel.setLocation(15, 346);
		}
		return logErrorPanel;
	}

	public void setErrorIcon(boolean bool) {
		this.nomeContatoTextField.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}

	protected JLabel getNomeTextFieldLabel() {

		if (nomeTextFieldLabel == null) {
			nomeTextFieldLabel = new JLabel("Comarcas");
			nomeTextFieldLabel.setSize(new Dimension(115, 22));
			nomeTextFieldLabel.setLocation(new Point(15, 17));
			nomeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomeTextFieldLabel;
	}

	protected JTabbedPane getTabbedPanePanel() {

		if (tabbedPanePanel == null) {
			tabbedPanePanel = new JTabbedPane();
			tabbedPanePanel.setSize(new Dimension(430, 242));
			tabbedPanePanel.setLocation(new java.awt.Point(15, 68));
			tabbedPanePanel.add(getEnderecoPanel(), "Endereço");
			tabbedPanePanel.add(getProcessosPanel(), "Processos da Comarca");

		}
		return tabbedPanePanel;
	}

	protected JPanel getEnderecoPanel() {
		if (enderecoPanel == null) {
			enderecoPanel = new JPanel();
			enderecoPanel.setSize(new java.awt.Dimension(150, 20));
			enderecoPanel.setLocation(new java.awt.Point(0, 161));
			enderecoPanel.setLayout(null);
			enderecoPanel.add(getEnderecoCadastreForm());
		}
		return enderecoPanel;
	}

	protected EnderecoCadastreForm getEnderecoCadastreForm() {

		if (enderecoCadastreForm == null) {
			enderecoCadastreForm = new EnderecoCadastreForm();
			enderecoCadastreForm.setSize(new Dimension(322, 212));
			enderecoCadastreForm.setLocation(new Point(44, 0));
			enderecoCadastreForm.setLayout(null);
		}
		return enderecoCadastreForm;
	}

	protected JPanel getButtonsPanel() {

		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setSize(new Dimension(429, 36));
			buttonsPanel.setLocation(new Point(15, 310));
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			buttonsPanel.add(getNovoButton());
			buttonsPanel.add(getCadastrarButton());
			buttonsPanel.add(getEditarButton());
			buttonsPanel.add(getRemoverButton());
			
		}
		return buttonsPanel;
	}

	protected JButton getCadastrarButton() {

		if (cadastrarButton == null) {
			cadastrarButton = new JButton(
					messages
							.getMessage("com.adapit.portal.layout.ComarcaCadastreForm.Cadastrar"));
			cadastrarButton.setSize(new java.awt.Dimension(150, 20));
			cadastrarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/page_save.png")));
			cadastrarButton.setLocation(new java.awt.Point(0, 0));
			cadastrarButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						cadastreComarca();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return cadastrarButton;
	}

	protected JButton getNovoButton() {

		if (novoButton == null) {
			novoButton = new JButton("Novo Contato");
			novoButton.setSize(new java.awt.Dimension(150, 20));
			novoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/new.png")));
			novoButton.setLocation(new java.awt.Point(0, 20));
			novoButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					newRegister();
				}
			});
		}
		return novoButton;
	}

	protected JButton getRemoverButton() {

		if (removerButton == null) {
			removerButton = new JButton("Remover");
			removerButton.setSize(new java.awt.Dimension(150, 20));
			removerButton.setLocation(new java.awt.Point(0, 20));
			removerButton.addActionListener(new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					removeRegister();
				}
			});
		}
		return removerButton;
	}
	
	protected JButton getEditarButton() {

		if (editarButton == null) {
			editarButton = new JButton("Editar");
			editarButton.setSize(new java.awt.Dimension(150, 20));
			editarButton.setLocation(new java.awt.Point(0, 20));
			editarButton.addActionListener(new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					editRegister();
				}
			});
		}
		return editarButton;
	}
	@SuppressWarnings("unchecked")
	protected JButton getAdicionarProcessoButton() {

		if (adicionarProcessoButton == null) {
			adicionarProcessoButton = new JButton("Adicionar");
			adicionarProcessoButton.setSize(new java.awt.Dimension(150, 20));
			adicionarProcessoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/database_add.png")));
			adicionarProcessoButton.setLocation(new java.awt.Point(0, 40));
			adicionarProcessoButton.addActionListener(new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					ProcessoTreinamentoCadastreForm proc = new ProcessoTreinamentoCadastreForm(); 
					proc.setModal(true);
					proc.setLocation(UIUtil.getScreenCenter(proc));
					proc.setVisible(true);
					
					ContatoProcessoTreinamento p = proc.getProcesso();
					if (p == null || p.getId() == 0) return;					
					try {
						
						Object objs[] = RemoteContatoService.getInstance().merge(contato, p);
						
						JOptionPane.showMessageDialog(ContatoCadastreForm.this,
							"Processo adicionado com sucesso");
						
						ContatoTreinamento com = (ContatoTreinamento) objs[0];
						if (com.getProcessos() != null && com.getProcessos().size() > 0){							
							ArrayList arr = new ArrayList();
							arr.addAll(com.getProcessos());
							baseTable.setElements(arr);
							baseTable.updateTable();
						}
						
					}catch (Exception ex){
						ex.printStackTrace();		
						JOptionPane.showMessageDialog(ContatoCadastreForm.this,
						"Processo nâo adicionado");
					}
					
				}
			});
			
		}
		return adicionarProcessoButton;
	}
	@SuppressWarnings("unchecked")
	protected JButton getEditarProcessoButton() {

		if (editarProcessoButton == null) {
			editarProcessoButton = new JButton("Editar");
			editarProcessoButton.setSize(new java.awt.Dimension(150, 20));
			editarProcessoButton.setLocation(new java.awt.Point(0, 40));
			editarProcessoButton.addActionListener(new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = baseTable.getSelectedRow();
					if (row > -1){						
						ContatoProcessoTreinamento pj = (ContatoProcessoTreinamento) baseTable.getElements().get(row);
						ProcessoTreinamentoCadastreForm procJudForm = new ProcessoTreinamentoCadastreForm();
						procJudForm.editRegister(pj);
						procJudForm.getCadastrarButton().setText("Alterar");
						procJudForm.setModal(true);
						procJudForm.setLocation(UIUtil.getScreenCenter(procJudForm));
						procJudForm.setVisible(true);						
						try {							
							ContatoTreinamento com = (ContatoTreinamento) RemoteContatoService.getInstance().loadContato(contato.getId());
							ArrayList arrList = new ArrayList();
							arrList.addAll(com.getProcessos());
							baseTable.setElements(arrList);							
							baseTable.updateTable();						
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}
					else
						JOptionPane.showMessageDialog(ContatoCadastreForm.this, "Nenhuma linha selecionada");
				}
			});
		}
		return editarProcessoButton;
	}
	@SuppressWarnings("unchecked")
	protected JButton getRemoverProcessoButton() {

		if (removerProcessoButton == null) {
			removerProcessoButton = new JButton("Remover");
			removerProcessoButton.setSize(new java.awt.Dimension(150, 20));
			removerProcessoButton.setLocation(new java.awt.Point(0, 40));
			removerProcessoButton.addActionListener(new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = baseTable.getSelectedRow();
					if (row > -1) {
						ContatoProcessoTreinamento pj = (ContatoProcessoTreinamento) baseTable
								.getElements().get(row);
						int resp = 0;
						resp = JOptionPane
								.showConfirmDialog(ContatoCadastreForm.this,
										"Você tem certeza que deseja deletar esse processo?");
						if (resp == JOptionPane.YES_OPTION) {
							try {
								RemoteContatoService.getInstance().delete(pj);
								ContatoTreinamento com = RemoteContatoService
										.getInstance().loadContato(
												contato.getId());
								ArrayList arrList = new ArrayList();
								arrList.addAll(com.getProcessos());
								baseTable.setElements(arrList);
								baseTable.updateTable();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}

					}
					else
						JOptionPane.showMessageDialog(ContatoCadastreForm.this, "Nenhuma linha selecionada");
				}
			});
		}
		return removerProcessoButton;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(470, 420));
				gui
						.add(new ContatoCadastreForm(),
								java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

	

	private JPanel processosPanel;

	private JPanel getProcessosPanel() {
		if (processosPanel == null) {
			processosPanel = new JPanel();
			processosPanel.setLayout(new BorderLayout());
			processosPanel.add(getBaseTableScrollPane(), BorderLayout.CENTER);
			processosPanel.add(getProcessosButtonsPanel(), BorderLayout.SOUTH);
		}
		return processosPanel;
	}

	private JPanel processosButtonsPanel;

	private JPanel getProcessosButtonsPanel() {
		if (processosButtonsPanel == null) {
			processosButtonsPanel = new JPanel();
			processosButtonsPanel.setLayout(new FlowLayout());
			processosButtonsPanel.add(getAdicionarProcessoButton());
			processosButtonsPanel.add(getEditarProcessoButton());
			processosButtonsPanel.add(getRemoverProcessoButton());

		}
		return processosButtonsPanel;
	}

	private JScrollPane baseTableScrollPane;

	private BaseTable baseTable;

	private JLabel nomeComLabel = null;

	protected JScrollPane getBaseTableScrollPane() {
		if (baseTableScrollPane == null) {
			baseTableScrollPane = new JScrollPane();
			baseTableScrollPane.setSize(new java.awt.Dimension(440, 192));
			baseTableScrollPane.setLocation(new java.awt.Point(5, 59));

			baseTableScrollPane.add(getBaseTable());
			baseTableScrollPane.setViewportView(getBaseTable());
		}
		return baseTableScrollPane;
	}

	public BaseTable getBaseTable() {

		if (baseTable == null) {
			baseTable = new BaseTable();
			baseTable.setSize(new java.awt.Dimension(446, 194));
			return baseTable;
		}
		return baseTable;
	}
	@SuppressWarnings("unchecked")
	private class BaseTable extends JXTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public BaseTable() {

			super();
			this.setModel(new BaseTableModel(null));
			this
					.addPropertyChangeListener(new BaseTablePropertyChangeListener());
		}

		public BaseTable(List elements) {
			super();
			this.elements = elements;
			this.setModel(new BaseTableModel(null));
			this
					.addPropertyChangeListener(new BaseTablePropertyChangeListener());
		}


		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][4];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.ContatoProcessoTreinamento) {
						com.adapit.portal.entidades.ContatoProcessoTreinamento procJud = (com.adapit.portal.entidades.ContatoProcessoTreinamento) obj;
						
						values[i][0] = procJud.getTecnologias();
						values[i][1] = procJud.getLocalRealizacao();
						values[i][2] = procJud.getRequerente();
						values[i][3] = AdapitVirtualFrame.getInstance().format(procJud.getDataPlanejada());
						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				updateUI();
			}
		}

		private class BaseTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				BaseTable jt = (BaseTable) e.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.ContatoProcessoTreinamento) {
							com.adapit.portal.entidades.ContatoProcessoTreinamento comarca = (com.adapit.portal.entidades.ContatoProcessoTreinamento) obj;

							if (col == 3)
								comarca.setTecnologias(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 4)
								comarca.setDetalhes(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 5)
								comarca.setRequerente(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 6)
								comarca.setLocalRealizacao(((java.lang.String) jt
										.getValueAt(row, col)));
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

		private class BaseTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class,
					String.class, String.class, String.class };

			boolean canEdit[] = new boolean[] { false, false, false,
					false };

			public BaseTableModel(Object[][] values) {

				super(
						values,
						new String[] {"Tecnologias","Local","Requerente","Data" });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}
} // @jve:decl-index=0:visual-constraint="10,10"
