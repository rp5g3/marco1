package com.adapit.portal.ui.forms.usuario;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.PersonType;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.UserDataFilterType;
import com.adapit.portal.services.UserSystemAccessFilterType;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.util.global.FilterResultSize;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;

@SuppressWarnings("serial")
public class UsuarioListForm extends JPanel {

	private JTabbedPane filtersPanel;

	private JPanel filterFieldAndSearchButtonPanel;

	private JPanel filterByNamePanel;

	private JTextField nomePessoaTextField;

	@SuppressWarnings("unused")
	private SwingBinder binder = new SwingBinder();

	@SuppressWarnings( { "unchecked", "unused" })
	private Map hashComps = new java.util.HashMap();

	private JLabel nomePessoaTextFieldLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JCheckBox nomePessoaCheckBox;

	private JRadioButton byNameRadioButton;

	private JRadioButton byLoginRadioButton;

	private JPanel filterByNameRules;

	private JRadioButton queContenhaRadioButton;

	private JRadioButton exatamenteRadioButton;

	private JRadioButton terminadoEmRadioButton;

	private JRadioButton iniciadoEmRadioButton;

	private JPanel userRulefilterPanel;

	private JComboBox userRuleComboBox;

	private JLabel userRuleComboBoxLabel;

	private JButton listarUserRuleButton;

	private JCheckBox porFuncaoCheckBox;

	private JPanel activePanel;

	private JRadioButton ativosInativosRadioButton;

	private JRadioButton ativosRadioButton;

	private JRadioButton inativosRadioButton;

	private JButton searchUsuarioButton;

	private JTabbedPane basePanel;

	private JPanel cadastreUsuarioButtonsPanel;

	private JButton novoUsuarioButton;

	private JButton editarUsuarioButton;

	private JButton apagarUsuarioButton;

	private JPanel specializedFilterButtonsPanel;

	private JButton listarByContasAPagarButton;

	private JButton listarByDeactivatedButton;

	private JButton listarByItensPostadosVendaDiretaButton;

	private StringQueryKind byDescKind = StringQueryKind.LIKE;
	private UserSystemAccessFilterType byActive = UserSystemAccessFilterType.TODOS;

	public UsuarioListForm() {
		initialize();
	}

	private void initialize() {
		setSize(new java.awt.Dimension(633, 503));
		setLocation(new java.awt.Point(0, 0));
		setLayout(new BorderLayout());
		add(getTopPanel(),BorderLayout.NORTH);
		add(getBasePanel(),BorderLayout.CENTER);
		add(getCadastreUsuarioButtonsPanel(), BorderLayout.SOUTH);
		
		ativarByNameSearch(false);
	}
	
	private JPanel topPanel;
	
	private JPanel getTopPanel(){
		if(topPanel == null){
			topPanel = new JPanel();
			topPanel.setLayout(null);
			topPanel.add(getSearchUsuarioButton(), null);
			reportResultsLabel = new JLabel();
			reportResultsLabel.setBounds(new Rectangle(204, 107, 266, 20));
			reportResultsLabel.setText("");
			topPanel.add(reportResultsLabel, null);
			topPanel.add(getResultNumberPanel());
			topPanel.add(getPesquisaVancadaButton(), null);
			topPanel.add(getFiltersPanel());
			topPanel.setPreferredSize(new Dimension(625,165));
		}
		return topPanel;
	}

	protected JTabbedPane getFiltersPanel() {

		if (filtersPanel == null) {
			filtersPanel = new JTabbedPane();
			filtersPanel.setSize(new Dimension(610, 102));
			filtersPanel.setLocation(new Point(5, 5));
			filtersPanel.add(getFilterFieldAndSearchButtonPanel(),
					"Filtrar por Nome e Login");
			filtersPanel.add(getUserRulefilterPanel(),
					"Pelo Tipo de Acesso do Usuário");
			filtersPanel.add(getActivePanel(),
					"Pelo Estado da Conta do Usuário");
		}
		return filtersPanel;
	}

	protected JPanel getFilterFieldAndSearchButtonPanel() {

		if (filterFieldAndSearchButtonPanel == null) {
			filterFieldAndSearchButtonPanel = new JPanel();
			filterFieldAndSearchButtonPanel.setSize(new java.awt.Dimension(588,
					52));
			filterFieldAndSearchButtonPanel.setLocation(new Point(15, 67));
			filterFieldAndSearchButtonPanel.setLayout(null);
			filterFieldAndSearchButtonPanel.add(getFilterByNamePanel());
			filterFieldAndSearchButtonPanel.add(getFilterByNameRules());
		}
		return filterFieldAndSearchButtonPanel;
	}

	protected JPanel getFilterByNamePanel() {

		if (filterByNamePanel == null) {
			filterByNamePanel = new JPanel();
			filterByNamePanel.setSize(new java.awt.Dimension(586, 21));
			filterByNamePanel.setLocation(new java.awt.Point(10, 10));
			filterByNamePanel.setLayout(null);
			filterByNamePanel.add(getNomePessoaTextField());
			filterByNamePanel.add(getNomePessoaTextFieldLabel());
			filterByNamePanel.add(getNomePessoaCheckBox());
			filterByNamePanel.add(getByNameRadioButton());
			filterByNamePanel.add(getByLoginRadioButton());
			ButtonGroup bg = new ButtonGroup();
			bg.add(getByNameRadioButton());
			bg.add(getByLoginRadioButton());
		}
		return filterByNamePanel;
	}

	protected JTextField getNomePessoaTextField() {

		if (nomePessoaTextField == null) {
			nomePessoaTextField = new JTextField();
			nomePessoaTextField.setText("");
			nomePessoaTextField.setSize(new java.awt.Dimension(250, 20));
			nomePessoaTextField.setLocation(new java.awt.Point(60, 0));
			nomePessoaTextField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						pesquisar();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			return nomePessoaTextField;
		}
		return nomePessoaTextField;
	}

	private void ativarByNameSearch(boolean b) {
		getNomePessoaTextField().setEditable(b);
		getByLoginRadioButton().setEnabled(b);
		getByNameRadioButton().setEnabled(b);
		getQueContenhaRadioButton().setEnabled(b);
		getExatamenteRadioButton().setEnabled(b);
		getTerminadoEmRadioButton().setEnabled(b);
		getIniciadoEmRadioButton().setEnabled(b);
	}

	protected JLabel getNomePessoaTextFieldLabel() {

		if (nomePessoaTextFieldLabel == null) {
			nomePessoaTextFieldLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Nome"));
			nomePessoaTextFieldLabel.setSize(new java.awt.Dimension(60, 20));
			nomePessoaTextFieldLabel.setLocation(new java.awt.Point(0, 0));
			nomePessoaTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomePessoaTextFieldLabel;
	}

	protected JCheckBox getNomePessoaCheckBox() {

		if (nomePessoaCheckBox == null) {
			nomePessoaCheckBox = new JCheckBox(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Filtrarpelonome"));
			nomePessoaCheckBox.setSize(new java.awt.Dimension(120, 20));
			nomePessoaCheckBox.setLocation(new java.awt.Point(310, 0));
			nomePessoaCheckBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					ativarByNameSearch(evt.getStateChange() == ItemEvent.SELECTED);
					if (evt.getStateChange() == ItemEvent.SELECTED) {
						nomePessoaTextField.requestFocus();
					}
				}
			});
			return nomePessoaCheckBox;
		}
		return nomePessoaCheckBox;
	}

	protected JRadioButton getByNameRadioButton() {

		if (byNameRadioButton == null) {
			byNameRadioButton = new JRadioButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.DePessoa"));
			byNameRadioButton.setSize(new java.awt.Dimension(80, 20));
			byNameRadioButton.setLocation(new java.awt.Point(430, 0));
			byNameRadioButton.setSelected(true);
			return byNameRadioButton;
		}
		return byNameRadioButton;
	}

	protected JRadioButton getByLoginRadioButton() {

		if (byLoginRadioButton == null) {
			byLoginRadioButton = new JRadioButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.DeUsuário"));
			byLoginRadioButton.setSize(new java.awt.Dimension(80, 20));
			byLoginRadioButton.setLocation(new java.awt.Point(510, 0));
			return byLoginRadioButton;
		}
		return byLoginRadioButton;
	}

	protected JPanel getFilterByNameRules() {

		if (filterByNameRules == null) {
			filterByNameRules = new JPanel();
			filterByNameRules.setSize(new java.awt.Dimension(587, 30));
			filterByNameRules.setLocation(new java.awt.Point(10, 33));
			filterByNameRules.setLayout(new java.awt.FlowLayout());
			filterByNameRules.add(getQueContenhaRadioButton());
			filterByNameRules.add(getExatamenteRadioButton());
			filterByNameRules.add(getTerminadoEmRadioButton());
			filterByNameRules.add(getIniciadoEmRadioButton());

			ButtonGroup bg = new ButtonGroup();
			bg.add(getQueContenhaRadioButton());
			bg.add(getExatamenteRadioButton());
			bg.add(getTerminadoEmRadioButton());
			bg.add(getIniciadoEmRadioButton());
		}
		return filterByNameRules;
	}

	protected JRadioButton getQueContenhaRadioButton() {

		if (queContenhaRadioButton == null) {
			queContenhaRadioButton = new JRadioButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Quecontenha"));
			queContenhaRadioButton.setSize(new java.awt.Dimension(100, 20));
			queContenhaRadioButton.setLocation(new java.awt.Point(0, 0));
			queContenhaRadioButton.setSelected(true);
			queContenhaRadioButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
						byDescKind = StringQueryKind.LIKE;
				}
			});
			return queContenhaRadioButton;
		}
		return queContenhaRadioButton;
	}

	protected JRadioButton getExatamenteRadioButton() {

		if (exatamenteRadioButton == null) {
			exatamenteRadioButton = new JRadioButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Exatamentecomo"));
			exatamenteRadioButton.setSize(new java.awt.Dimension(120, 20));
			exatamenteRadioButton.setLocation(new java.awt.Point(100, 0));
			exatamenteRadioButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
						byDescKind = StringQueryKind.EQUALS;
				}
			});
			return exatamenteRadioButton;
		}
		return exatamenteRadioButton;
	}

	protected JRadioButton getTerminadoEmRadioButton() {

		if (terminadoEmRadioButton == null) {
			terminadoEmRadioButton = new JRadioButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Terminandoem"));
			terminadoEmRadioButton.setSize(new java.awt.Dimension(110, 20));
			terminadoEmRadioButton.setLocation(new java.awt.Point(220, 0));
			terminadoEmRadioButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
						byDescKind = StringQueryKind.ENDS_WITH;
				}
			});
			return terminadoEmRadioButton;
		}
		return terminadoEmRadioButton;
	}

	protected JRadioButton getIniciadoEmRadioButton() {

		if (iniciadoEmRadioButton == null) {
			iniciadoEmRadioButton = new JRadioButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Iniciandoem"));
			iniciadoEmRadioButton.setSize(new java.awt.Dimension(120, 20));
			iniciadoEmRadioButton.setLocation(new java.awt.Point(330, 0));
			iniciadoEmRadioButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
						byDescKind = StringQueryKind.BEGINS_WITH;
				}
			});
			return iniciadoEmRadioButton;
		}
		return iniciadoEmRadioButton;
	}

	protected JPanel getUserRulefilterPanel() {

		if (userRulefilterPanel == null) {
			userRulefilterPanel = new JPanel();
			userRulefilterPanel.setLayout(null);
			userRulefilterPanel.setBounds(new Rectangle(16, 120, 588, 22));
			userRulefilterPanel.add(getUserRuleComboBox());
			userRulefilterPanel.add(getUserRuleComboBoxLabel());
			// userRulefilterPanel.add(getListarUserRuleButton());
			userRulefilterPanel.add(getPorFuncaoCheckBox());
		}
		return userRulefilterPanel;
	}

	protected JComboBox getUserRuleComboBox() {

		if (userRuleComboBox == null) {
			userRuleComboBox = new JComboBox();
			userRuleComboBox.setSize(new Dimension(366, 20));
			userRuleComboBox.setLocation(new Point(124, 40));
			for (int i = 0; i < UserCadastreType.values().length; i++) {
				userRuleComboBox.addItem(UserCadastreType.values()[i]);
			}
			userRuleComboBox.setEnabled(false);
		}
		return userRuleComboBox;
	}

	protected JLabel getUserRuleComboBoxLabel() {

		if (userRuleComboBoxLabel == null) {
			userRuleComboBoxLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Cliente/Funcionário"));
			userRuleComboBoxLabel.setSize(new java.awt.Dimension(110, 20));
			userRuleComboBoxLabel.setLocation(new Point(10, 40));
			userRuleComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return userRuleComboBoxLabel;
	}

	protected JButton getListarUserRuleButton() {

		if (listarUserRuleButton == null) {
			listarUserRuleButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Listar"));
			listarUserRuleButton.setSize(new java.awt.Dimension(90, 22));
			listarUserRuleButton.setLocation(new java.awt.Point(325, 0));
		}
		return listarUserRuleButton;
	}

	protected JCheckBox getPorFuncaoCheckBox() {

		if (porFuncaoCheckBox == null) {
			porFuncaoCheckBox = new JCheckBox(
					"Listar pela função execida no sistema");
			porFuncaoCheckBox.setSize(new java.awt.Dimension(300, 20));
			porFuncaoCheckBox.setLocation(new Point(10, 16));
			porFuncaoCheckBox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED) {
						userRuleComboBox.setEnabled(true);
					} else
						userRuleComboBox.setEnabled(false);
				}

			});
		}
		return porFuncaoCheckBox;
	}

	protected JPanel getActivePanel() {
		if (activePanel == null) {
			activePanel = new JPanel();
			activePanel.setLayout(new java.awt.FlowLayout());
			activePanel.setBounds(new Rectangle(14, 153, 588, 32));
			activePanel.add(getAtivosInativosRadioButton());
			activePanel.add(getAtivosRadioButton());
			activePanel.add(getInativosRadioButton());

			ButtonGroup bg = new ButtonGroup();
			bg.add(getAtivosInativosRadioButton());
			bg.add(getAtivosRadioButton());
			bg.add(getInativosRadioButton());
		}
		return activePanel;
	}

	protected JRadioButton getAtivosInativosRadioButton() {

		if (ativosInativosRadioButton == null) {
			ativosInativosRadioButton = new JRadioButton(
					"Listar os "
							+ messages
									.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.AtivoseInativos"));
			ativosInativosRadioButton.setSize(new java.awt.Dimension(150, 20));
			ativosInativosRadioButton.setLocation(new java.awt.Point(0, 0));
			ativosInativosRadioButton.setSelected(true);
			ativosInativosRadioButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
						byActive = UserSystemAccessFilterType.TODOS;
				}
			});
			return ativosInativosRadioButton;
		}
		return ativosInativosRadioButton;
	}

	protected JRadioButton getAtivosRadioButton() {

		if (ativosRadioButton == null) {
			ativosRadioButton = new JRadioButton("Apenas os Ativos");
			ativosRadioButton.setSize(new java.awt.Dimension(150, 20));
			ativosRadioButton.setLocation(new java.awt.Point(216, 0));
			ativosRadioButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
						byActive = UserSystemAccessFilterType.ATIVOS;
				}
			});
			return ativosRadioButton;
		}
		return ativosRadioButton;
	}

	protected JRadioButton getInativosRadioButton() {

		if (inativosRadioButton == null) {
			inativosRadioButton = new JRadioButton("Apenas os Inativos");
			inativosRadioButton.setSize(new java.awt.Dimension(150, 20));
			inativosRadioButton.setLocation(new java.awt.Point(432, 0));
			inativosRadioButton.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
						byActive = UserSystemAccessFilterType.INATIVOS;
				}
			});
			return inativosRadioButton;
		}
		return inativosRadioButton;
	}

	protected JButton getSearchUsuarioButton() {

		if (searchUsuarioButton == null) {
			searchUsuarioButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Pesquisar"));
			searchUsuarioButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/read_obj.gif")));
			searchUsuarioButton.setBounds(new Rectangle(5, 106, 176, 24));
			searchUsuarioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						pesquisar();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		return searchUsuarioButton;
	}

	protected JTabbedPane getBasePanel() {
		if (basePanel == null) {
			basePanel = new JTabbedPane();
			basePanel.setSize(new Dimension(610, 252));
			basePanel.setLocation(new Point(5, 155));
			basePanel.add(getPessoaDataTableScrollPane(), "Dados de Pessoa");
			basePanel.add(getUserTableScrollPane(), "Dados de Usuário");
			basePanel.add(getPfTableScrollPane(), "Dados de Pessoa Fisica");
			basePanel.add(getPjTableScrollPane(), "Dados de Pessoa Jurídica");
		}
		return basePanel;
	}

	private JScrollPane pessoaDataTableScrollPane;

	private PessoaDataTable pessoaDataTable;

	protected JScrollPane getPessoaDataTableScrollPane() {
		if (pessoaDataTableScrollPane == null) {
			pessoaDataTableScrollPane = new JScrollPane();
			pessoaDataTableScrollPane.setSize(new Dimension(607, 196));
			pessoaDataTableScrollPane.setLocation(new Point(4, 5));

			pessoaDataTableScrollPane.add(getPessoaDataTable());
			pessoaDataTableScrollPane.setViewportView(getPessoaDataTable());
		}
		return pessoaDataTableScrollPane;
	}

	protected PessoaDataTable getPessoaDataTable() {
		if (pessoaDataTable == null) {
			pessoaDataTable = new PessoaDataTable();
			pessoaDataTable.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent evt) {
					getEditarUsuarioButton().setEnabled(true);
				}
			});
			return pessoaDataTable;
		}
		return pessoaDataTable;
	}

	private JScrollPane pjTableScrollPane;

	private UserPessoaJuridicaTable pjDataTable;

	protected JScrollPane getPjTableScrollPane() {
		if (pjTableScrollPane == null) {
			pjTableScrollPane = new JScrollPane();
			pjTableScrollPane.setSize(new Dimension(607, 196));
			pjTableScrollPane.setLocation(new Point(4, 5));

			pjTableScrollPane.add(getPjDataTable());
			pjTableScrollPane.setViewportView(getPjDataTable());
		}
		return pjTableScrollPane;
	}

	protected UserPessoaJuridicaTable getPjDataTable() {
		if (pjDataTable == null) {
			pjDataTable = new UserPessoaJuridicaTable();
			pjDataTable.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent evt) {
					getEditarUsuarioButton().setEnabled(true);
				}
			});
		}
		return pjDataTable;
	}

	private JScrollPane pfTableScrollPane;

	private UserPessoaFisicaTable pfDataTable;

	protected JScrollPane getPfTableScrollPane() {
		if (pfTableScrollPane == null) {
			pfTableScrollPane = new JScrollPane();
			pfTableScrollPane.setSize(new Dimension(607, 196));
			pfTableScrollPane.setLocation(new Point(4, 5));

			pfTableScrollPane.add(getPfDataTable());
			pfTableScrollPane.setViewportView(getPfDataTable());
		}
		return pfTableScrollPane;
	}

	protected UserPessoaFisicaTable getPfDataTable() {
		if (pfDataTable == null) {
			pfDataTable = new UserPessoaFisicaTable();
			pfDataTable.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent evt) {
					getEditarUsuarioButton().setEnabled(true);
				}
			});
		}
		return pfDataTable;
	}

	private JScrollPane userTableScrollPane;

	private UsuarioDadosTable userDataTable;

	protected JScrollPane getUserTableScrollPane() {
		if (userTableScrollPane == null) {
			userTableScrollPane = new JScrollPane();
			userTableScrollPane.setSize(new Dimension(607, 196));
			userTableScrollPane.setLocation(new Point(4, 5));

			userTableScrollPane.add(getUserDataTable());
			userTableScrollPane.setViewportView(getUserDataTable());
		}
		return userTableScrollPane;
	}

	protected UsuarioDadosTable getUserDataTable() {
		if (userDataTable == null) {
			userDataTable = new UsuarioDadosTable();
			userDataTable.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent evt) {
					getEditarUsuarioButton().setEnabled(true);
				}
			});
		}
		return userDataTable;
	}

	protected JPanel getCadastreUsuarioButtonsPanel() {

		if (cadastreUsuarioButtonsPanel == null) {
			cadastreUsuarioButtonsPanel = new JPanel();
			cadastreUsuarioButtonsPanel.setLayout(new java.awt.FlowLayout());
			cadastreUsuarioButtonsPanel.setBounds(new Rectangle(12, 417, 605,
					47));
			cadastreUsuarioButtonsPanel.add(getNovoUsuarioButton());
			cadastreUsuarioButtonsPanel.add(getEditarUsuarioButton());
			cadastreUsuarioButtonsPanel.add(getApagarUsuarioButton());
		}
		return cadastreUsuarioButtonsPanel;
	}

	protected JButton getNovoUsuarioButton() {

		if (novoUsuarioButton == null) {
			novoUsuarioButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Novo"));
			novoUsuarioButton.setSize(new java.awt.Dimension(150, 20));
			novoUsuarioButton.setLocation(new java.awt.Point(0, 0));
			novoUsuarioButton.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/New.gif", 18, 18));
			novoUsuarioButton.setEnabled(true);
			novoUsuarioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarFuncionario();
				}
			});

		}
		return novoUsuarioButton;
	}

	protected JButton getEditarUsuarioButton() {

		if (editarUsuarioButton == null) {
			editarUsuarioButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Editar"));
			editarUsuarioButton.setSize(new java.awt.Dimension(150, 20));
			editarUsuarioButton.setLocation(new java.awt.Point(0, 20));
			editarUsuarioButton.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/edit.jpg", 18, 18));
			editarUsuarioButton.setEnabled(false);
			editarUsuarioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						{
							Usuario u = getSelectedUsuario();
							if (u.getDadosPessoais().getTipoPessoa() instanceof Fisica) {
								if (u.getUserCadastreType().equals(
										UserCadastreType.Administrador_do_sistema)
										|| u.getUserCadastreType().equals(
												UserCadastreType.Funcionário)) {
									AdapitVirtualFrame.getInstance()
											.editarFuncionario(u);
								} else if (u.getUserCadastreType().equals(
										UserCadastreType.Instrutor)) {
									AdapitVirtualFrame.getInstance()
											.editarInstrutor(u);
								} else if (u.getUserCadastreType().equals(UserCadastreType.Cliente)) {
									AdapitVirtualFrame.getInstance()
											.editarParticipante(u,
													PersonType.Fisica);
								} else if (u.getUserCadastreType().equals(
										UserCadastreType.Comitente)) {
								} else if (u.getUserCadastreType().equals(
										UserCadastreType.Representante_legal)) {

								} else
									AdapitVirtualFrame.getInstance()
											.editarParticipante(u,
													PersonType.Fisica);
							} else {
								AdapitVirtualFrame.getInstance()
										.editarParticipante(u,
												PersonType.Juridica);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		return editarUsuarioButton;
	}

	protected JButton getApagarUsuarioButton() {

		if (apagarUsuarioButton == null) {
			apagarUsuarioButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Apagar"));
			apagarUsuarioButton.setSize(new java.awt.Dimension(150, 20));
			apagarUsuarioButton.setLocation(new java.awt.Point(0, 40));
			apagarUsuarioButton.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/delete.jpg", 18, 18));
			apagarUsuarioButton.setEnabled(true);
			apagarUsuarioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					try {
						Usuario users[] = getSelectedUsuarios();
						if (users == null || users.length <= 0)
							return;
						int resp = JOptionPane.showConfirmDialog(
								UsuarioListForm.this,
								"Apagar os usuarios selecionados?",
								"Remover usuário", JOptionPane.YES_NO_OPTION);
						if (resp == JOptionPane.YES_OPTION) {
							RemoteUserService.getInstance().remove(users);
						}
						AdapitVirtualFrame.getInstance().showOperationSucess();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		return apagarUsuarioButton;
	}

	private JPanel resultNumberPanel = null;

	private JLabel reportResultsLabel = null;

	/**
	 * This method initializes resultNumberPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getResultNumberPanel() {
		if (resultNumberPanel == null) {
			resultNumberPanel = new JPanel();
			GridLayout g = new GridLayout(1, 20);
			g.setHgap(1);
			g.setVgap(1);
			resultNumberPanel.setLayout(g);
			resultNumberPanel.setBounds(new Rectangle(203, 130, 405, 21));

		}
		return resultNumberPanel;
	}

	int buttons = 10;

	private void changeResultNumberPanel() {
		resultNumberPanel.removeAll();
		GridLayout g = new GridLayout(1, buttons);
		g.setHgap(1);
		g.setVgap(1);
		resultNumberPanel.setLayout(g);

	}

	String desc = null;
	UserCadastreType ur = null;
	UserDataFilterType un = null;

	public void pesquisar() throws Exception{

		if (getPorFuncaoCheckBox().isSelected())
			ur = (UserCadastreType) getUserRuleComboBox().getSelectedItem();
		else
			ur = null;

		if (getByNameRadioButton().isSelected())
			un = UserDataFilterType.PESSOA;
		else
			un = UserDataFilterType.USUARIO;

		if (getNomePessoaCheckBox().isSelected()) {
			desc = getNomePessoaTextField().getText();
		} else
			desc = null;

		try {
			countFirst = RemoteUserService.getInstance().countAccordingTo(desc,
					byDescKind, un, ur, byActive);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		criarListar();

	}

	@SuppressWarnings("unchecked")
	private void listar(int firstResult) {
		List userList = RemoteUserService.getInstance().listAccordingTo(desc,
				byDescKind, un, ur, byActive, firstResult);
		/*try {
			System.out.println(userList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		updateTable(userList);
	}

	private Integer countFirst = 0; // @jve:decl-index=0:

	int max = FilterResultSize.userListMaxSize;
	int total;
	int secBegin = 0;
	int secCount;

	private void criarListar() throws Exception{

		total = countFirst;
		final int number = total / max;
		int rest = total % max;
		secBegin = 0;
		secCount = 0;
		reportResultsLabel.setText("Encontrados " + total
				+ " itens. Mostrando 1 até " + max);
		getResultNumberPanel();
		changeResultNumberPanel();

		for (int i = 0; i < buttons; i++) {
			if (i < number) {
				JButton jb = new JButton((i + 1) + "");
				jb.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							JButton bt = (JButton) evt.getSource();
							Integer i = Integer.parseInt(bt.getText());
							int dif = (countFirst - ((i - 1) * max));
							if (countFirst > (i - 1) * max) {
								listar((i - 1) * max);
								reportResultsLabel.setText("Encontrados "
										+ total + " itens. Mostrando " + (i));
							} else {
								int oldmax = max;
								max = dif;
								listar((i - 1) * max);

								reportResultsLabel.setText("Encontrados "
										+ total + " itens. Mostrando " + (i));

								max = oldmax;
							}
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				resultNumberPanel.add(jb);
			} else if (rest > 0) {
				rest = 0;
				JButton jb = new JButton((i + 1) + "");
				jb.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							JButton bt = (JButton) evt.getSource();
							Integer i = Integer.parseInt(bt.getText());
							listar((i - 1) * max);

							reportResultsLabel.setText("Encontrados " + total
									+ " itens. Mostrando " + (i));

						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				resultNumberPanel.add(jb);
			} else {
				JLabel jl = new JLabel();
				resultNumberPanel.add(jl);
			}
		}

		if (total < max) {
			try {
				listar(0);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				listar(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resultNumberPanel.updateUI();
	}

	@SuppressWarnings("unchecked")
	public void updateTable(List userList) {
		setElements(userList);
		getPessoaDataTable().updateTable();
		getUserDataTable().updateTable();
		getPjDataTable().updateTable();
		getPfDataTable().updateTable();
	}

	protected JPanel getSpecializedFilterButtonsPanel() {

		if (specializedFilterButtonsPanel == null) {
			specializedFilterButtonsPanel = new JPanel();
			specializedFilterButtonsPanel.setBorder(javax.swing.BorderFactory
					.createTitledBorder(javax.swing.BorderFactory
							.createTitledBorder("Listar os usuários que:")));
			specializedFilterButtonsPanel.setSize(new java.awt.Dimension(586,
					84));
			specializedFilterButtonsPanel.setLocation(new java.awt.Point(15,
					218));
			specializedFilterButtonsPanel.setLayout(new java.awt.FlowLayout());
			specializedFilterButtonsPanel.add(getListarByContasAPagarButton());
			specializedFilterButtonsPanel.add(getListarByDeactivatedButton());
			specializedFilterButtonsPanel
					.add(getListarByItensPostadosVendaDiretaButton());
		}
		return specializedFilterButtonsPanel;
	}

	protected JButton getListarByContasAPagarButton() {

		if (listarByContasAPagarButton == null) {
			listarByContasAPagarButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Possuemcontasapagar"));
			listarByContasAPagarButton.setSize(new java.awt.Dimension(150, 20));
			listarByContasAPagarButton.setLocation(new java.awt.Point(0, 0));
		}
		return listarByContasAPagarButton;
	}

	protected JButton getListarByDeactivatedButton() {

		if (listarByDeactivatedButton == null) {
			listarByDeactivatedButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Jáforamdesativados"));
			listarByDeactivatedButton.setSize(new java.awt.Dimension(150, 20));
			listarByDeactivatedButton.setLocation(new java.awt.Point(0, 20));
		}
		return listarByDeactivatedButton;
	}

	protected JButton getListarByItensPostadosVendaDiretaButton() {

		if (listarByItensPostadosVendaDiretaButton == null) {
			listarByItensPostadosVendaDiretaButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Jápostaramitensparavendadireta"));
			listarByItensPostadosVendaDiretaButton
					.setSize(new java.awt.Dimension(150, 20));
			listarByItensPostadosVendaDiretaButton
					.setLocation(new java.awt.Point(0, 40));
		}
		return listarByItensPostadosVendaDiretaButton;
	}

	private AdvancedPersonSearchDialog advacedSearchDialog;

	private AdvancedPersonSearchDialog getAdvacedSearchDialog() {
		if (advacedSearchDialog == null) {
			advacedSearchDialog = new AdvancedPersonSearchDialog(this);
		}
		return advacedSearchDialog;
	}

	/**
	 * This method initializes pesquisaVancadaButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getPesquisaVancadaButton() {
		if (pesquisaVancadaButton == null) {
			pesquisaVancadaButton = new JButton();
			pesquisaVancadaButton.setBounds(new Rectangle(5, 131, 176, 24));
			pesquisaVancadaButton.setText("Pesquisa Avançada");
			pesquisaVancadaButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							getAdvacedSearchDialog().setVisible(true);
						}
					});
		}
		return pesquisaVancadaButton;
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
		}
		return null;
	}

	private Hashtable<String, Usuario> usuarios = new Hashtable<String, Usuario>();

	public Usuario getSelectedUsuario() {
		int row = 0;
		int index = getBasePanel().getSelectedIndex();
		if (index == 0)
			row = getPessoaDataTable().getSelectedRow();
		else if (index == 1)
			row = getUserDataTable().getSelectedRow();
		else if (index == 2)
			row = getPfDataTable().getSelectedRow();
		else if (index == 3)
			row = getPjDataTable().getSelectedRow();
		if (row >= 0) {
			String login = (String) getUserDataTable().getValueAt(row, 0);
			return usuarios.get(login);
		}
		return null;
	}

	public Usuario[] getSelectedUsuarios() {
		int rows[] = null;
		int index = getBasePanel().getSelectedIndex();
		if (index == 0)
			rows = getPessoaDataTable().getSelectedRows();
		else if (index == 1)
			rows = getUserDataTable().getSelectedRows();
		else if (index == 2)
			rows = getPfDataTable().getSelectedRows();
		else if (index == 3)
			rows = getPjDataTable().getSelectedRows();
		if (rows != null && rows.length > 0) {
			Usuario users[] = new Usuario[rows.length];
			for (int i = 0; i < rows.length; i++) {
				String login = (String) getUserDataTable().getValueAt(rows[i],
						0);
				users[i] = usuarios.get(login);
			}
			return users;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List elements;

	private JButton pesquisaVancadaButton = null;

	@SuppressWarnings("unchecked")
	public void setElements(List elements) {
		this.elements = elements;
	}

	@SuppressWarnings("unchecked")
	public List getElements() {
		return this.elements;
	}

	private class PessoaDataTable extends JTable {

		public PessoaDataTable() {

			super();
			this.setModel(new PessoaDataTableModel(null));
			this
					.addPropertyChangeListener(new PessoaDataTablePropertyChangeListener());
		}

		/*@SuppressWarnings("unchecked")
		public PessoaDataTable(List elements) {
			super();
			UsuarioListForm.this.elements = elements;
			this.setModel(new PessoaDataTableModel(null));
			this
					.addPropertyChangeListener(new PessoaDataTablePropertyChangeListener());
		}*/

		/*public void setDefineCellRenderers() {

		}
*/
		@SuppressWarnings("unchecked")
		public void updateTable() {
			usuarios.clear();
			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][7];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if(!(obj instanceof Usuario))
						System.out.println(i+" não é instância de usuario " + obj.getClass().getName());
					if (obj instanceof Usuario) {
						try {
							Usuario usuario = (Usuario) obj;

							usuarios.put(usuario.getLogin(), usuario);
							values[i][0] = usuario.getDadosPessoais().getNome();
							values[i][1] = usuario.getDadosPessoais()
									.getEmail();
							values[i][2] = usuario.getDadosPessoais()
									.getTelefone();

							try {
								TipoPessoa tp = usuario.getDadosPessoais()
										.getTipoPessoa();
								if (tp != null) {
									if (tp instanceof Juridica) {
										values[i][0] = usuario
												.getDadosPessoais().getNome()
												+ " " + ((Juridica) tp)
														.getRazaoSocial();
										values[i][3] = "Pessoa Jurídica";
									} else {
										values[i][0] = usuario
												.getDadosPessoais().getNome()
												+ " " + ((Fisica) tp).getSobrenome();
										values[i][3] = "Pessoa Física";
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							values[i][4] = (i + 1);
							i++;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}// End of while Loop
				setModel(new PessoaDataTableModel(values));
				updateUI();
			} else {
				setModel(new PessoaDataTableModel(null));
				updateUI();
			}
		}

		private class PessoaDataTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				com.adapit.portal.ui.forms.usuario.UsuarioListForm.PessoaDataTable jt = (com.adapit.portal.ui.forms.usuario.UsuarioListForm.PessoaDataTable) e
						.getSource();
				// int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (getElements() != null && row > -1)
					try {
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

		private class PessoaDataTableModel extends DefaultTableModel {

			@SuppressWarnings("unchecked")
			Class types[] = new java.lang.Class[] { String.class, String.class,
					String.class, String.class, Integer.class };

			boolean canEdit[] = new boolean[] { false, false, false, false,
					false };

			public PessoaDataTableModel(Object[][] values) {

				super(
						values,
						new String[] {
								messages
										.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Nome"),
								messages
										.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Email"),
								messages
										.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.Telefone"),
								messages
										.getMessage("com.adapit.portal.ui.forms.manutencaousuario.UsuarioListForm.TipodePessoa"),
								"" });
			}

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}

	private class UserPessoaFisicaTable extends JTable {

		public UserPessoaFisicaTable() {

			super();
			this.setModel(new UserPessoaFisicaTableModel(null));
		}

		@SuppressWarnings("unchecked")
		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof Usuario) {
						try {
							Usuario usuario = (Usuario) obj;
							if (usuario.getDadosPessoais().getTipoPessoa() instanceof Fisica) {
								Fisica f = (Fisica) usuario.getDadosPessoais()
										.getTipoPessoa();
								values[i][0] = f.getSobrenome();
								values[i][1] = f.getCpf();
								values[i][2] = f.getRg();
							}
							values[i][3] = (i + 1);
							i++;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}// End of while Loop
				setModel(new UserPessoaFisicaTableModel(values));
				updateUI();
			} else {
				setModel(new UserPessoaFisicaTableModel(null));
				updateUI();
			}
		}

		private class UserPessoaFisicaTableModel extends DefaultTableModel {

			@SuppressWarnings("unchecked")
			Class types[] = new java.lang.Class[] { String.class, String.class,
					String.class, Integer.class };

			boolean canEdit[] = new boolean[] { false, false, false, false };

			public UserPessoaFisicaTableModel(Object[][] values) {

				super(values, new String[] { "Sobrenome", "CPF",
						"Registro Geral", "" });
			}

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}

	private class UserPessoaJuridicaTable extends JTable {

		public UserPessoaJuridicaTable() {
			super();
			this.setModel(new UserPessoaJuridicaTableModel(null));
		}

		@SuppressWarnings("unchecked")
		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof Usuario) {
						try {
							Usuario usuario = (Usuario) obj;
							if (usuario.getDadosPessoais().getTipoPessoa() instanceof Juridica) {
								Juridica tp = (Juridica) usuario
										.getDadosPessoais().getTipoPessoa();
								values[i][0] = tp.getRazaoSocial();
								values[i][1] = tp.getCnpj();
								values[i][2] = tp.getInscricaoEstadual();
								values[i][3] = tp.getRamoAtividade();

							}
							values[i][4] = (i + 1);
							i++;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				setModel(new UserPessoaJuridicaTableModel(values));
				updateUI();
			} else {
				setModel(new UserPessoaJuridicaTableModel(null));
				updateUI();
			}
		}

		private class UserPessoaJuridicaTableModel extends DefaultTableModel {

			@SuppressWarnings("unchecked")
			Class types[] = new java.lang.Class[] { String.class, String.class,
					String.class, String.class, Integer.class };

			boolean canEdit[] = new boolean[] { false, false, false, false,
					false };

			public UserPessoaJuridicaTableModel(Object[][] values) {

				super(values, new String[] { "Razão Social", "CNPJ",
						"Inscrição Estadual", "Ramo de Atividade", "" });
			}

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}

	private class UsuarioDadosTable extends JTable {

		public UsuarioDadosTable() {

			super();
			this.setModel(new UsuarioDadossTableModel(null));
		}

		@SuppressWarnings("unchecked")
		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof Usuario) {
						try {
							Usuario usuario = (Usuario) obj;

							values[i][0] = usuario.getLogin();
							values[i][1] = usuario.getUserCadastreType().name().replace(
									"_", " ");
							values[i][2] = usuario.getActive();
							values[i][3] = usuario.isAutenticado();
							values[i][4] = (i + 1);

							i++;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				setModel(new UsuarioDadossTableModel(values));
				updateUI();
			} else {
				setModel(new UsuarioDadossTableModel(null));
				updateUI();
			}
		}

		private class UsuarioDadossTableModel extends DefaultTableModel {

			@SuppressWarnings("unchecked")
			Class types[] = new java.lang.Class[] { String.class, String.class,
					Boolean.class, Boolean.class, Integer.class };

			boolean canEdit[] = new boolean[] { false, false, false, false,
					false };

			public UsuarioDadossTableModel(Object[][] values) {

				super(values, new String[] { "Login", "Tipo de Acesso",
						"Ativo", "Autenticado", "" });
			}

			@SuppressWarnings("unchecked")
			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}
	
	public void listar(List userList) {
		updateTable(userList);
	}
}