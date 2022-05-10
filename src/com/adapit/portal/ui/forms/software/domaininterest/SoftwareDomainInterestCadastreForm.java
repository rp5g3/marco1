package com.adapit.portal.ui.forms.software.domaininterest;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;

import com.adapit.portal.entidades.SoftwareDomainInterest;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.forms.software.SoftwareSolutionListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;
import java.awt.Point;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class SoftwareDomainInterestCadastreForm extends JPanel {

	private JComboBox nomeSoftwareDomainInterestComboBox;
	private JTextField nomeSoftwareDomainInterestTextField;

	private SwingBinder binder = new SwingBinder();

	private SoftwareDomainInterest domain = new SoftwareDomainInterest();  //  @jve:decl-index=0:
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap(); // @jve:decl-index=0:

	private boolean processFocus = true;


	protected LogErrorPanel logErrorPanel;

	private JLabel nomeTextFieldLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JTabbedPane tabbedPanePanel;

	private JPanel buttonsPanel;

	private JButton cadastrarButton;

	private JButton novoButton, removerButton, editarButton;

	private JButton adicionarProcessoButton, removerProcessoButton;
	
	private Hashtable<String, SoftwareDomainInterest> comarcas = new Hashtable<String,SoftwareDomainInterest>();
	private SoftwareDomainInterest vetSoftwareDomainInterests[];

	public SoftwareDomainInterestCadastreForm() {
		initialize();
		updateSoftwareDomainInterestsComboBox();
		podeInserir = true;
	}

	private void initialize() {

		nomeComLabel = new JLabel();
		//nomeComLabel.setBounds(new Rectangle(16, 62, 113, 22)); //46
		nomeComLabel.setBounds(15, 33,115,22);
		nomeComLabel.setText("Domínio:");
		setSize(new Dimension(472, 454));
		setLayout(null);
		add(getNomeSoftwareDomainInterestComboBox());
		add(getNomeSoftwareDomainInterestTextField());
		add(getNomeTextFieldLabel());
		add(getTabbedPanePanel());
		add(nomeComLabel);
		add(getNomeSoftwareDomainInterestComboBox());
		add(getNomeSoftwareDomainInterestTextField());
		add(getButtonsPanel());
		// Put such code into the end of initialize body
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		// End
		this.add(getDescScrollPane(), null);
		
	}

	private boolean podeInserir = false;


	
	protected JComponent getNomeSoftwareDomainInterestComboBox() {
		if (nomeSoftwareDomainInterestComboBox == null) {
			nomeSoftwareDomainInterestComboBox = new JComboBox();
			nomeSoftwareDomainInterestComboBox.setEditable(false);			
			nomeSoftwareDomainInterestComboBox.setBounds(new Rectangle(132, 160, 314, 22));
			nomeSoftwareDomainInterestComboBox.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED) {
						if (podeInserir)
							editRegister();
					}
				}
			});

		}
		return nomeSoftwareDomainInterestComboBox;
	}

	@SuppressWarnings("unchecked")
	protected JComponent getNomeSoftwareDomainInterestTextField() {
		if (nomeSoftwareDomainInterestTextField == null) {
			nomeSoftwareDomainInterestTextField = new JTextField();
			nomeSoftwareDomainInterestTextField.setBounds(132, 32,314, 22);
			this.binder.addBindProperty(this.domain,
					this.nomeSoftwareDomainInterestTextField, "nome");

			this.hashComps.put("nome", this.nomeSoftwareDomainInterestTextField);
			JWarningComponent warn = new JWarningComponent(
					this.nomeSoftwareDomainInterestTextField);
			warn.setBounds(132, 32,314, 22); 
			return warn;
		}
		return nomeSoftwareDomainInterestTextField;
	}

	@SuppressWarnings("unchecked")
	private void editRegister() {
		cadastrarButton.setText("Atualizar");
		removerButton.setEnabled(true);
		String nomeSoftwareDomainInterest = (String) nomeSoftwareDomainInterestComboBox.getSelectedItem();
		if (domain.getId() != 0 && !comarcas.containsKey(nomeSoftwareDomainInterest))
			return;
		
		try {			
			SoftwareDomainInterest com = RemoteComercialSolutionService.getInstance().getSoftwareDomainInterestByName(nomeSoftwareDomainInterest);
			nomeSoftwareDomainInterestTextField.setText(com.getNome());
			descTextPane.setText(com.getDescricao());
			domain.setId(com.getId());
			domain.setNome(com.getNome());
			domain.setDescricao(com.getDescricao());
			List<SoftwareSolution> list = RemoteComercialSolutionService.getInstance().listSoftwaresByDomainName(com.getNome(),null);
			try {
				if (list != null && list.size() > 0){
					domain.setSoftwares(list);
					ArrayList arr = new ArrayList();
					arr.addAll(list);
					baseTable.setElements(arr);
					baseTable.updateTable();
				}else{
					domain.setSoftwares(new ArrayList<SoftwareSolution>());
					baseTable.setElements(new ArrayList<SoftwareSolution>());
					baseTable.updateTable();
					getRemoverProcessoButton().setEnabled(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(SoftwareDomainInterestCadastreForm.this,
					"Erro ao editar domínio");
		} 
	}
	
	
	private void removeRegister() {
		if (comarcas == null) return;
		String nomeSoftwareDomainInterest = (String) nomeSoftwareDomainInterestComboBox.getSelectedItem();
		if (domain.getId() != 0 && !comarcas.containsKey(nomeSoftwareDomainInterest))
			return;		
		try {			
			SoftwareDomainInterest com = comarcas.get(domain.getNome());
			if (com == null) return;
			List<SoftwareSolution> list = RemoteComercialSolutionService.getInstance().listSoftwaresByDomainName(com.getNome(),null);
			for (SoftwareSolution pj: list){
				RemoteServicesUtility.getInstance().deleteById(SoftwareSolution.class, pj.getId());
			}
			RemoteServicesUtility.getInstance().delete(com);			
			JOptionPane.showMessageDialog(SoftwareDomainInterestCadastreForm.this,	"Domínio removido com sucesso");			
			newRegister();
			updateSoftwareDomainInterestsComboBox();
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(	SoftwareDomainInterestCadastreForm.this,"Erro ao deletar domínio");
			
		} 
	}
	

	public SoftwareDomainInterest validateSoftwareDomainInterestForm() throws Exception {
		setErrorIcon(false);
		binder.bind(domain);
		domain.setDescricao(descTextPane.getText());
		// Manual Code
		if (!validateSoftwareDomainInterestBean())
			throw new Exception("Bean Not Validated!");
		return domain;
	}

	
	public SoftwareDomainInterest cadastreSoftwareDomainInterest() throws Exception {		
		validateSoftwareDomainInterestForm();
		String str = (String) nomeSoftwareDomainInterestTextField.getText();

		int resp = 0;

		if (domain.getId() == 0) {
			resp = JOptionPane.showConfirmDialog(SoftwareDomainInterestCadastreForm.this,
					"Você quer inserir o domínio " + str);

			if (resp == JOptionPane.YES_OPTION) {				
				try {					
					SoftwareDomainInterest com = new SoftwareDomainInterest();
					com.setNome(str);
					com.setDescricao(descTextPane.getText());
					
					RemoteServicesUtility.getInstance().save(com);
					
					updateSoftwareDomainInterestsComboBox();
					JOptionPane.showMessageDialog(SoftwareDomainInterestCadastreForm.this,
							"Domínio cadastrado com sucesso");
					newRegister();
					return com;
				} catch (Exception ex) {
					//ex.printStackTrace();
					JOptionPane.showMessageDialog(SoftwareDomainInterestCadastreForm.this,
							"Erro ao cadastrar o domínio");					
				} 
			}
		}else{
			resp = JOptionPane.showConfirmDialog(SoftwareDomainInterestCadastreForm.this,
					"Você quer atualizar os dados desse domínio?");

			if (resp == JOptionPane.YES_OPTION) {				
				try {
					
					
					RemoteServicesUtility.getInstance().update(domain);
					
					updateSoftwareDomainInterestsComboBox();
					JOptionPane.showMessageDialog(SoftwareDomainInterestCadastreForm.this,
							"Domínio atualizado com sucesso");
					newRegister();
					return domain;
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(SoftwareDomainInterestCadastreForm.this,
							"Erro ao atualizar o domínio");					
				} 
			}
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	private void updateSoftwareDomainInterestsComboBox() {
		podeInserir = false;		
		nomeSoftwareDomainInterestComboBox.removeAllItems();
		comarcas.clear();
		try {			
			List list = RemoteComercialSolutionService.getInstance().listAllSoftwareDomainInterest(null,null);
			Iterator<SoftwareDomainInterest> it = list.iterator();
			vetSoftwareDomainInterests = new SoftwareDomainInterest[list.size()];
			int i = 0;
			while (it.hasNext()) {
				SoftwareDomainInterest c = it.next();
				nomeSoftwareDomainInterestComboBox.addItem(c.getNome());
				comarcas.put(c.getNome(), c);
				vetSoftwareDomainInterests[i] = c;
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		podeInserir = true;
	}
	
	@SuppressWarnings("unchecked")
	public boolean validateSoftwareDomainInterestBean() {
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.domain, "softwareDomainInterest");
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

		domain.setId(0);
		domain.setNome(null);
		domain.setDescricao(null);
		binder.reverseBind(this.domain);
		cadastrarButton.setText("Cadastrar");
		removerButton.setEnabled(false);
		nomeSoftwareDomainInterestTextField.requestFocus();
		nomeSoftwareDomainInterestTextField.setText("");
		if(descTextPane != null)
			descTextPane.setText("");
		this.setErrorIcon(false);
		updateUI();
	}

	public void editRegister(SoftwareDomainInterest objSoftwareDomainInterest) {
		// Nunca passar como argumento novos objetos!!!
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(this.domain,
					objSoftwareDomainInterest);
			domain.setDescricao(objSoftwareDomainInterest.getDescricao());
		} catch (Exception e) {
			e.printStackTrace();
		}

		binder.reverseBind(this.domain);
		this.setErrorIcon(false);

	}

	public LogErrorPanel getErrorPanel() {
		if (logErrorPanel == null) {
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setSize(new Dimension(429, 35));
			logErrorPanel.setLocation(15, 400);
		}
		return logErrorPanel;
	}

	public void setErrorIcon(boolean bool) {
		this.nomeSoftwareDomainInterestTextField.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}

	protected JLabel getNomeTextFieldLabel() {
		if (nomeTextFieldLabel == null) {
			nomeTextFieldLabel = new JLabel("Domínios:");
			nomeTextFieldLabel.setBounds(new Rectangle(16, 160, 113, 22));
			nomeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomeTextFieldLabel;
	}

	protected JTabbedPane getTabbedPanePanel() {
		if (tabbedPanePanel == null) {
			tabbedPanePanel = new JTabbedPane();
			tabbedPanePanel.setSize(new Dimension(430, 169));
			tabbedPanePanel.setLocation(new Point(15, 187)); 
			tabbedPanePanel.add(getProcessosPanel(), "Softwares do domínio");
			tabbedPanePanel.addChangeListener(new ChangeListener(){
				@Override
				public void stateChanged(ChangeEvent evt) {
					if (domain == null || domain.getId() <= 0){
						if (tabbedPanePanel.getSelectedIndex() == 1){
							JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(), 
									"Antes de inserir um software você precisa cadastrar ou editar ao domínio",
									"Tarefas pendentes",JOptionPane.WARNING_MESSAGE);
							tabbedPanePanel.setSelectedIndex(0);
						}
					}					
				}
				
			});
		}
		return tabbedPanePanel;
	}

	

	protected JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			buttonsPanel.setBounds(new Rectangle(15, 360, 430, 67));
			buttonsPanel.add(getCadastrarButton());
			buttonsPanel.add(getNovoButton());
			buttonsPanel.add(getEditarButton());
			buttonsPanel.add(getRemoverButton());			
			buttonsPanel.add(getGenHtmlButton());
		}
		return buttonsPanel;
	}
	
	private JButton genHtmlButton;
	
	private JButton getGenHtmlButton(){
		if(genHtmlButton == null){
			genHtmlButton = new JButton("HTML");
			genHtmlButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						RemoteComercialSolutionService.getInstance().generateHtmlCatalog(domain);
						AdapitVirtualFrame.getInstance().showOperationSucess();
					} catch (Exception e) {
						e.printStackTrace();
						AdapitVirtualFrame.getInstance().showErrorDialog("Problema ao gerar o catálogo html", "não foi possível gerar o catálogo do pacote");
					}
				}
			});
		}
		return genHtmlButton;
	}

	protected JButton getCadastrarButton() {
		if (cadastrarButton == null) {
			cadastrarButton = new JButton("Cadastrar");
			cadastrarButton.setSize(new java.awt.Dimension(150, 20));
			cadastrarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/page_save.png")));
			cadastrarButton.setLocation(new java.awt.Point(0, 0));
			cadastrarButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						cadastreSoftwareDomainInterest();
					} catch (Exception e1) {
						if (!e1.getMessage().equals("Bean Not Validated!"))
							e1.printStackTrace();
					}
				}
			});
		}
		return cadastrarButton;
	}

	protected JButton getNovoButton() {
		if (novoButton == null) {
			novoButton = new JButton("Novo Domínio");
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
			removerButton.setIcon(new ImageIcon(getClass().getResource("/imgs/delete.png")));
			removerButton.addActionListener(new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					String str = (String) nomeSoftwareDomainInterestTextField.getText();

					int resp = 0;

					if (domain != null) {
						resp = JOptionPane.showConfirmDialog(SoftwareDomainInterestCadastreForm.this,
								"Você quer remover o domínio " + str);

						if (resp == JOptionPane.YES_OPTION) {
							removeRegister();
						}
					}
				}
			});
		}
		return removerButton;
	}
	
	protected JButton getEditarButton() {

		if (editarButton == null) {
			editarButton = new JButton("Editar");
			editarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/page_edit.png")));
			editarButton.addActionListener(new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					editRegister();
				}
			});
		}
		return editarButton;
	}
	
	protected JButton getAdicionarProcessoButton() {
		if (adicionarProcessoButton == null) {
			adicionarProcessoButton = new JButton("Adicionar");
			adicionarProcessoButton.setSize(new java.awt.Dimension(150, 20));
			adicionarProcessoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/database_add.png")));
			adicionarProcessoButton.setLocation(new java.awt.Point(0, 40));
			adicionarProcessoButton.addActionListener(new ActionListener() {
					
				@Override
				public void actionPerformed(ActionEvent e) {
					final SoftwareSolutionListForm listForm = new SoftwareSolutionListForm(); 
					final JDialog jd = new JDialog(AdapitVirtualFrame.getInstance());
					//jd.setSize(listForm.getSize().width+10,listForm.getSize().width+80);
					jd.setSize(new Dimension(735, 590));
					jd.setModal(true);
					jd.setLocation(UIUtil.getScreenCenter(jd));
					jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					jd.add(listForm,BorderLayout.CENTER);
					
					JPanel btPanel = new JPanel();
					btPanel.setLayout(new FlowLayout());
					final JButton attach = new JButton("Anexar no Domínio " + domain.getNome());
					btPanel.add(attach);
					
					attach.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent evt) {
							int rows[] = listForm.getBaseTable().getSelectedRows();
							if (rows != null && rows.length > 0){
								for (int i=0; i < rows.length; i++){									
													
									try {
										SoftwareSolution p = (SoftwareSolution) listForm.getBaseTable().getElements().get(rows[i]);
										if (p == null || p.getId() == 0){
											System.out.println("Software is null or id == 0");
											return;	
										}
										
										RemoteComercialSolutionService.getInstance().merge(domain,p);
										JOptionPane.showMessageDialog(SoftwareDomainInterestCadastreForm.this,
											"Software adicionado com sucesso");										
										
										baseTable.setElements(RemoteComercialSolutionService.getInstance().listSoftwaresByDomainName(domain.getNome(),null));
										baseTable.updateTable();
										getRemoverProcessoButton().setEnabled(true);
										jd.dispose();
									}catch (Exception ex){
										ex.printStackTrace();		
										JOptionPane.showMessageDialog(SoftwareDomainInterestCadastreForm.this,
										"Software nâo adicionado");
									}
								}
							}
						}						
					});
					JButton fechar = new JButton("Fechar");
					fechar.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent evt) {
							jd.dispose();
						}						
					});
					
					btPanel.add(fechar);
					
					jd.add(btPanel,BorderLayout.SOUTH);
					jd.setVisible(true);					
				}
			});
			
		}
		return adicionarProcessoButton;
	}
	
	@SuppressWarnings("unchecked")
	protected JButton getRemoverProcessoButton() {
		if (removerProcessoButton == null) {
			removerProcessoButton = new JButton("Remover");
			removerProcessoButton.setSize(new java.awt.Dimension(150, 20));
			removerProcessoButton.setLocation(new java.awt.Point(0, 40));
			removerProcessoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/database_delete.png")));
			removerProcessoButton.addActionListener(new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					int row = baseTable.getSelectedRow();
					if (row > -1) {
						SoftwareSolution pj = (SoftwareSolution) baseTable
								.getElements().get(row);
						int resp = 0;
						resp = JOptionPane
								.showConfirmDialog(SoftwareDomainInterestCadastreForm.this,
										"Você tem certeza que deseja desvincular o software?");
						if (resp == JOptionPane.YES_OPTION) {
							try {
								SoftwareDomainInterest com =
									RemoteComercialSolutionService.getInstance().unmerge(domain,pj);
								
								ArrayList arrList = new ArrayList();
								arrList.addAll(com.getSoftwares());
								baseTable.setElements(arrList);
								baseTable.updateTable();
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}

					}
					else
						JOptionPane.showMessageDialog(SoftwareDomainInterestCadastreForm.this, "Selecione um dos softwares na tabela");
				}
			});
		}
		return removerProcessoButton;
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
			processosButtonsPanel.add(getRemoverProcessoButton());

		}
		return processosButtonsPanel;
	}

	private JScrollPane baseTableScrollPane;

	private BaseTable baseTable;

	private JLabel nomeComLabel = null;
	private JScrollPane descScrollPane = null;
	private JTextPane descTextPane = null;
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
		}

		@SuppressWarnings("unused")
		public BaseTable(List elements) {
			super();
			this.elements = elements;
			this.setModel(new BaseTableModel(null));
		}

		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][4];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.SoftwareSolution) {
						com.adapit.portal.entidades.SoftwareSolution procJud = (com.adapit.portal.entidades.SoftwareSolution) obj;
						
						values[i][0] = procJud.getSigla();
						values[i][1] = procJud.getNome();
						i++;
					}
				}// End of while Loop
				if (values != null && values.length>0)
					setModel(new BaseTableModel(values));
				else setModel(new BaseTableModel(null));
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				updateUI();
			}
		}

		
		private class BaseTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class,
					String.class };

			boolean canEdit[] = new boolean[] { false, false};

			public BaseTableModel(Object[][] values) {

				super(
						values,
						new String[] {"Sigla","Nome"});
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}
	/**
	 * This method initializes descScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getDescScrollPane() {
		if (descScrollPane == null) {
			descScrollPane = new JScrollPane();
			descScrollPane.setBounds(new Rectangle(15, 59, 429, 97));
			descScrollPane.setViewportView(getDescTextPane());
		}
		return descScrollPane;
	}

	/**
	 * This method initializes descTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getDescTextPane() {
		if (descTextPane == null) {
			descTextPane = new JTextPane();
		}
		return descTextPane;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
