package com.adapit.portal.ui.forms.update.updatefile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Update;
import com.adapit.portal.entidades.UpdateFile;
import com.adapit.portal.entidades.UpdateFileKind;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.remote.RemoteUpdateService;
import com.adapit.portal.ui.forms.file.FileFilterPanel;
import com.adapit.portal.ui.forms.usuario.UsuarioListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.UIUtil;
import com.workcase.utils.DatePropertyEditor;

public class UpdateFileCadastreForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel versionLabel = null;
	private JTextField versionTextField = null;
	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private Update updateBean;
	private UpdateFile file = new UpdateFile();
	private Arquivo arq;
	private Arquivo inst;

	/**
	 * This is the default constructor
	 */
	public UpdateFileCadastreForm(Update up) {
		super();
		this.updateBean = up;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(621, 600);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		getTopPanel();
		add(getListPanel(), BorderLayout.CENTER);
		tableUpdated();

	}

	private JDialog editDialog;

	private void showEditDialog(String title) {
		editDialog = new JDialog(AdapitVirtualFrame.getInstance());
		editDialog.setSize(700, 400);
		editDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		editDialog.setTitle(title);
		editDialog.setModal(true);
		editDialog.getContentPane().add(getTopPanel(), BorderLayout.CENTER);
		editDialog.setLocation(UIUtil.getScreenCenter(editDialog));
		editDialog.setVisible(true);
	}

	private JPanel topPanel;

	private JPanel getTopPanel() {
		if (topPanel == null) {
			topPanel = new JPanel();
			topPanel.setLayout(new BorderLayout());
			topPanel.add(getButtonsPanel(), BorderLayout.SOUTH);
			topPanel.add(getTopGridPanel(), BorderLayout.NORTH);
			topPanel.add(getTopCenterPanel(), BorderLayout.CENTER);
			topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		}
		return topPanel;
	}

	private JLabel dtUpdateLabel;

	private JLabel getDtUpdateLabel() {
		dtUpdateLabel = new JLabel("Data da atualização:");
		dtUpdateLabel.setSize(113, 22);
		dtUpdateLabel.setLocation(227, 0);
		return dtUpdateLabel;
	}

	private DateHourChooser dataPublicacao;
	private JCheckBox publishedCheckBox = null;
	private JPanel obsScrollPane = null;
	private JPanel arquivoPanel = null;
	private JTextPane obsTextPane = null;
	private JTextField arquivoTextField = null;
	private JButton procurarArquivoButton = null;
	private JLabel tipoArquivoLabel = null;
	private JComboBox tipoComboBox = null;
	private JPanel listPanel = null;
	private JScrollPane listScrollPane = null;
	private JPanel buttonsListPanel = null;
	private JButton versionarButton = null;
	private JButton removerListaButton = null;
	private JButton novoButton = null;
	private JButton editarButton = null;
	private JButton listarVersaoButton = null;
	private JPanel buttonsPanel = null;
	private JButton salvarButton = null;

	private DateHourChooser getDataPublicacao() {
		if (dataPublicacao == null) {
			dataPublicacao = new DateHourChooser(messages.getCurrentLocale(),
					true, true, false);
			dataPublicacao.setBounds(new Rectangle(343, 0, 205, 22));

		}
		return dataPublicacao;
	}

	/**
	 * This method initializes versionTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getVersionTextField() {
		if (versionTextField == null) {
			versionTextField = new JTextField();
			versionTextField.setBounds(new Rectangle(182, 0, 39, 22));
		}
		return versionTextField;
	}

	/**
	 * This method initializes publishedCheckBox
	 * 
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getPublishedCheckBox() {
		if (publishedCheckBox == null) {
			publishedCheckBox = new JCheckBox();
			publishedCheckBox.setBounds(new Rectangle(0, 0, 211, 22));
			publishedCheckBox.setText("Publicar para download na internet");
		}
		return publishedCheckBox;
	}

	/**
	 * This method initializes obsScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JPanel getObsScrollPane() {
		if (obsScrollPane == null) {
			obsScrollPane = new JPanel(new BorderLayout());
			obsScrollPane.add(new JScrollPane(getObsTextPane()),
					BorderLayout.CENTER);
			obsScrollPane
					.setBorder(BorderFactory
							.createTitledBorder(
									null,
									"Informe uma observação sobre o arquivo, ex. sobre as modificações",
									TitledBorder.DEFAULT_JUSTIFICATION,
									TitledBorder.DEFAULT_POSITION, new Font(
											"Tahoma", Font.PLAIN, 11),
									Color.black));
		}
		return obsScrollPane;
	}

	/**
	 * This method initializes arquivoPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getArquivoPanel() {
		if (arquivoPanel == null) {
			arquivoPanel = new JPanel();
			arquivoPanel.setLayout(new BorderLayout());
			arquivoPanel.setBounds(new Rectangle(10, 265, 590, 50));
			arquivoPanel.setBorder(BorderFactory.createTitledBorder(null,
					"Selecione o arquivo de versão (Obrigatório)",
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Tahoma",
							Font.PLAIN, 11), Color.black));
			arquivoPanel.add(getArquivoTextField(), BorderLayout.CENTER);
			JPanel jp = new JPanel(new GridLayout(1, 2));
			jp.setPreferredSize(new Dimension(160, 24));
			jp.add(getProcurarArquivoButton());
			jp.add(getBuscarArquivoButton());
			arquivoPanel.add(jp, BorderLayout.EAST);
		}
		return arquivoPanel;
	}

	/**
	 * This method initializes obsTextPane
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextPane getObsTextPane() {
		if (obsTextPane == null) {
			obsTextPane = new JTextPane();
		}
		return obsTextPane;
	}

	/**
	 * This method initializes arquivoTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getArquivoTextField() {
		if (arquivoTextField == null) {
			arquivoTextField = new JTextField();
			arquivoTextField.setEnabled(true);
			arquivoTextField.setEditable(false);
		}
		return arquivoTextField;
	}

	/**
	 * This method initializes procurarArquivoButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getProcurarArquivoButton() {
		if (procurarArquivoButton == null) {
			procurarArquivoButton = new JButton();
			procurarArquivoButton.setText("Buscar");
			procurarArquivoButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							try {
								final JDialog jd = new JDialog(
										AdapitVirtualFrame.getInstance());

								jd.setModal(true);
								jd.setSize(800, 400);
								jd.setLocation(UIUtil.getScreenCenter(jd));
								jd
										.setTitle("Buscar arquivo para anexar em versão de arquivo");
								jd
										.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								FileFilterPanel ffp = new FileFilterPanel();
								jd.add(ffp, BorderLayout.CENTER);
								JPanel flPanel = new JPanel(new FlowLayout());
								JButton jb = new JButton(
										"Selecionar o arquivo para nova versão");
								jb.addActionListener(new ActionListener() {
									@Override
									public void actionPerformed(ActionEvent e) {
										jd.dispose();
									}

								});
								flPanel.add(jb);
								jd.add(flPanel, BorderLayout.SOUTH);
								jd.setVisible(true);

								arq = ffp.getSelectedArquivo();
								if (arq != null)
									updateSelectedArquive();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}

					});
		}
		return procurarArquivoButton;
	}

	private JButton buscarArquivoButton;

	private JButton getBuscarArquivoButton() {
		if (buscarArquivoButton == null) {
			buscarArquivoButton = new JButton();
			buscarArquivoButton.setText("Editar");
			buscarArquivoButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (checkSelectedFile()) {
								final JDialog jd = new JDialog(
										AdapitVirtualFrame.getInstance());
								jd.setModal(true);
								jd.setSize(800, 400);
								jd.setLocation(UIUtil.getScreenCenter(jd));
								jd.setTitle("Editar arquivo anexado na versão "
										+ file.getCurrentFile().getName());
								jd
										.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
								FileFilterPanel ffp = new FileFilterPanel();
								jd.add(ffp, BorderLayout.CENTER);
								ffp.editRegister(arq);
								JPanel flPanel = new JPanel(new FlowLayout());
								jd.add(flPanel, BorderLayout.SOUTH);
								jd.setVisible(true);
							}
						}

					});
		}
		return buscarArquivoButton;
	}

	/**
	 * This method initializes tipoComboBox
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getTipoComboBox() {
		if (tipoComboBox == null) {
			tipoComboBox = new JComboBox();
			tipoComboBox.setBounds(new Rectangle(343, 0, 256, 22));
			for (int i = 0; i < UpdateFileKind.values().length; i++) {
				tipoComboBox.addItem(UpdateFileKind.values()[i].name().replace(
						"_", " "));
			}
		}
		return tipoComboBox;
	}

	/**
	 * This method initializes listPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getListPanel() {
		if (listPanel == null) {
			listPanel = new JPanel();
			listPanel.setLayout(new BorderLayout());
			listPanel.setBounds(new Rectangle(10, 355, 590, 124));
			listPanel.setMinimumSize(new Dimension(590, 124));
			listPanel.setBorder(BorderFactory.createTitledBorder(null,
					"Lista das últimas versões dos arquivos de "
							+ updateBean.getTitulo(),
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Tahoma",
							Font.PLAIN, 11), Color.black));
			listPanel.add(getTopButtonPanel(), BorderLayout.NORTH);
			listPanel.add(getListScrollPane(), BorderLayout.CENTER);
			listPanel.add(getButtonsListPanel(), BorderLayout.SOUTH);
		}
		return listPanel;
	}

	/**
	 * This method initializes listScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getListScrollPane() {
		if (listScrollPane == null) {
			listScrollPane = new JScrollPane(getBaseTable());
		}
		return listScrollPane;
	}

	/**
	 * This method initializes buttonsListPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getButtonsListPanel() {
		if (buttonsListPanel == null) {
			buttonsListPanel = new JPanel();
			buttonsListPanel.setLayout(new FlowLayout());
			buttonsListPanel.add(getVersionarButton(), null);
			buttonsListPanel.add(getListarVersaoButton(), null);
			buttonsListPanel.add(getRemoverListaButton(), null);

			buttonsListPanel.add(getEditarButton(), null);

			buttonsListPanel.add(getAutorizacoesButton(), null);
			buttonsListPanel
					.setBorder(BorderFactory
							.createTitledBorder("Selecione o item para executar as seguintes operações:"));
		}
		return buttonsListPanel;
	}

	private JPanel topButtonPanel;

	private JPanel getTopButtonPanel() {
		if (topButtonPanel == null) {
			topButtonPanel = new JPanel();
			topButtonPanel.setLayout(new FlowLayout());
			topButtonPanel.add(getNovoButton(), null);
			topButtonPanel.add(getReportButton(), null);
		}
		return topButtonPanel;
	}

	/**
	 * This method initializes versionarButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getVersionarButton() {
		if (versionarButton == null) {
			versionarButton = new JButton();
			versionarButton.setText("Versionar");
			versionarButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (checkSelectedFile())
								try {
									UpdateFile uf = baseTable.getSelectedFile();
									// Arquivo arquivo = uf.getCurrentFile();
									// file = uf;
									// uf = uf.newVersion();
									// doReverseBind();
									// String oldVersion = uf.getVersion();
									UpdateFile ufnew = uf.newVersion();
									uf.setVersion(ufnew.getVersion() + "*");
									uf.setObs("O que mudou?" + '\n'
											+ "Por que mudou?" + '\n'
											+ "Quem fez a modificação?");
									uf.setDate(new Date());
									uf.setCurrentFile(null);
									// ufnew.setVersion(oldVersion);
									try {
										file = RemoteUpdateService
												.getInstance().saveOrUpdate(uf,
														updateBean, null, null,
														ufnew);
									} catch (Exception e1) {
										e1.printStackTrace();
									}
									// arq = arquivo;
									// file.setCurrentFile(arq);
									editRegister(file);

									tableUpdated();
								} catch (Exception ex) {
									ex.printStackTrace();
								}
						}
					});
		}
		return versionarButton;
	}

	/**
	 * This method initializes removerListaButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getRemoverListaButton() {
		if (removerListaButton == null) {
			removerListaButton = new JButton();
			removerListaButton.setText("Remover arquivo");
			removerListaButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (checkSelectedFile()) {
								int resp = JOptionPane
										.showConfirmDialog(
												UpdateFileCadastreForm.this,
												"Esta operação removerá permanentemente o arquivo selecionado!"
														+ '\n'
														+ "Confirmar a remoção do arquivo?",
												"Remover arquivo de "
														+ updateBean
																.getTitulo(),
												JOptionPane.YES_NO_OPTION);
								if (resp == JOptionPane.YES_OPTION) {
									UpdateFile uf = baseTable.getSelectedFile();
									try {
										RemoteUpdateService.getInstance()
												.delete(uf);
									} catch (Exception e1) {
										e1.printStackTrace();
									}
									tableUpdated();
								}
							}
						}
					});
		}
		return removerListaButton;
	}

	/**
	 * This method initializes novoButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getNovoButton() {
		if (novoButton == null) {
			novoButton = new JButton();
			novoButton.setText("Novo arquivo");
			novoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					newRegister();
				}

			});
		}
		return novoButton;
	}

	private void newRegister() {
		file.setId(0);
		file.setObs("");
		file.setDate(new Date());
		file.setUpdateFileKind(UpdateFileKind.Diversos);
		file.setVersion("0.1");
		file.setUpdateBean(updateBean);
		file.setCurrentFile(null);
		file.setPublished(false);
		file.setInstallationFile(null);
		file.setRestrict(true);
		file.setWhatChanged("");
		file.setWhoChanged("");
		file.setWhyChanged("");
		doBind();
		showEditDialog("Incluir novo arquivo na versão de "
				+ updateBean.getTitulo());
	}

	private void editRegister(UpdateFile f) {
		file.setId(f.getId());
		file.setObs(f.getObs());
		file.setDate(f.getDate());
		file.setUpdateFileKind(f.getUpdateFileKind());
		file.setVersion(f.getVersion());
		file.setUpdateBean(updateBean);
		arq = f.getCurrentFile();
		file.setCurrentFile(arq);
		inst = f.getInstallationFile();
		file.setInstallationFile(inst);
		file.setWhatChanged(f.getWhatChanged());
		file.setWhoChanged(f.getWhoChanged());
		file.setWhyChanged(f.getWhyChanged());
		file.setRestrict(f.isRestrict());
		file.setPublished(f.isPublished());
		doBind();
		if (arq != null && updateBean != null) {
			showEditDialog("Editar arquivo " + arq.getName() + " [versão de "
					+ updateBean.getTitulo() + "]");
		} else {
			showEditDialog("Editar arquivo " + updateBean.getTitulo() + "");
		}
	}

	private void doBind() {
		versionTextField.setText(file.getVersion());
		dataPublicacao.setDate(file.getDate());
		publishedCheckBox.setSelected(file.isPublished());
		tipoComboBox.setSelectedIndex(file.getUpdateFileKind().ordinal());
		obsTextPane.setText(file.getObs());
		if (file.getCurrentFile() != null)
			arquivoTextField.setText("ID: " + file.getCurrentFile().getId()
					+ "  NOME: " + file.getCurrentFile().getName());
		else
			arquivoTextField.setText("");

		if (file.getInstallationFile() != null)
			arquivoTextField1.setText("ID: "
					+ file.getInstallationFile().getId() + "  NOME: "
					+ file.getInstallationFile().getName());
		else
			arquivoTextField1.setText("");

		whatTextPane.setText(file.getWhatChanged());
		whoTextPane.setText(file.getWhoChanged());
		whyTextPane.setText(file.getWhyChanged());
		restrictCheckBox.setSelected(file.isRestrict());
	}

	private void updateSelectedArquive() {
		arquivoTextField.setText("ID: " + arq.getId() + "  NOME: "
				+ arq.getName());
	}

	private void updateSelectedArquive1() {
		arquivoTextField1.setText("ID: " + inst.getId() + "  NOME: "
				+ inst.getName());
	}

	private void doReverseBind() {
		file.setVersion(versionTextField.getText());
		file.setDate(dataPublicacao.getDate());
		file.setPublished(publishedCheckBox.isSelected());
		file.setUpdateFileKind(UpdateFileKind.values()[tipoComboBox
				.getSelectedIndex()]);
		file.setObs(obsTextPane.getText());
		file.setCurrentFile(arq);
		file.setInstallationFile(inst);
		file.setWhatChanged(whatTextPane.getText());
		file.setWhoChanged(whoTextPane.getText());
		file.setWhyChanged(whyTextPane.getText());
		file.setRestrict(restrictCheckBox.isSelected());
	}

	/**
	 * This method initializes editarButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getEditarButton() {
		if (editarButton == null) {
			editarButton = new JButton();
			editarButton.setText("Editar");
			editarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (checkSelectedFile())
						try {
							UpdateFile uf = baseTable.getSelectedFile();
							uf = RemoteUpdateService.getInstance().load(uf);
							editRegister(uf);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				}
			});
		}
		return editarButton;
	}

	private BaseTable baseTable;
	private JButton cancelButton = null;
	private JButton reportButton = null;
	private JButton autorizacoesButton = null;
	private JPanel arquivoPanel1 = null;
	private JTextField arquivoTextField1 = null;
	private JPanel jp1 = null;
	private JButton procurarArquivoButton1 = null;
	private JButton buscarArquivoButton1 = null;
	private JCheckBox restrictCheckBox = null;
	private JPanel whyPanel = null;
	private JScrollPane whyScrollPane = null;
	private JTextPane whyTextPane = null;
	private JPanel whoPanel = null;
	private JScrollPane whoScrollPane = null;
	private JTextPane whoTextPane = null;
	private JPanel whatPanel = null;
	private JScrollPane whatScrollPane = null;
	private JTextPane whatTextPane = null;
	private JTabbedPane textTabbedPane = null;
	private JPanel topGridPanel = null;
	private JPanel topCenterPanel = null;

	private BaseTable getBaseTable() {
		if (baseTable == null) {
			baseTable = new BaseTable();
		}
		return baseTable;
	}

	/**
	 * This method initializes listarVersaoButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getListarVersaoButton() {
		if (listarVersaoButton == null) {
			listarVersaoButton = new JButton();
			listarVersaoButton.setText("Visualizar as versões");
			listarVersaoButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (checkSelectedFile()) {
								versoes(baseTable.getSelectedFile());
							}
						}
					});
		}
		return listarVersaoButton;
	}

	private boolean checkSelectedFile() {
		if (baseTable.getSelectedFile() == null) {
			JOptionPane.showMessageDialog(this,
					"Você precisa selecionar o arquivo na lista",
					"Arquivo não selecionado", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private void versoes(UpdateFile file) {
		if (file != null)
			try {
				JDialog jd = new JDialog(AdapitVirtualFrame.getInstance());
				BaseTable bt = new BaseTable();
				jd.setTitle("Versões do arquivo " + file.getId()
						+ " modificado em " + file.getDate());
				jd.setSize(750, 300);
				jd.setLocation(UIUtil.getScreenCenter(jd));
				jd.setModal(true);
				jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				List<UpdateFile> list = RemoteUpdateService.getInstance()
						.listAllUpdateFiles(file, null);
				bt.setElements(list);
				if (list == null || list.size() == 0)
					System.out.println("Não possui nenhum item!!!");
				bt.updateTable();
				JPanel jp = new JPanel(new FlowLayout());
				RemoveButton remove = new RemoveButton(bt, "Remover o item",
						file, true);
				RemoveButton versoes = new RemoveButton(bt, "Versões do item",
						file, false);
				UndoButton undo = new UndoButton(bt,
						"Desfazer versionamento do item", updateBean, file);
				JButton reportButton = new JButton();
				reportButton.setText("Relatório");
				reportButton.setEnabled(false);
				reportButton
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(
									java.awt.event.ActionEvent e) {
								// TODO desenvolver o relatório das versões do
								// updatefile
							}
						});
				jp.add(remove);
				jp.add(versoes);
				jp.add(undo);
				jp.add(reportButton);
				jd.add(new JScrollPane(bt), BorderLayout.CENTER);
				jd.add(jp, BorderLayout.SOUTH);
				jd.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		else {
			AdapitVirtualFrame.getInstance().showErrorDialog(
					"Item não selecionado na tabela",
					"Selecione o item para verificar as versões");
		}
	}

	private class RemoveButton extends JButton implements ActionListener {

		private static final long serialVersionUID = -2808044070163319012L;
		public BaseTable baseTable;
		private UpdateFile file;
		private boolean remove;

		public RemoveButton(BaseTable bt, String text, UpdateFile file,
				boolean remove) {
			super(text);
			this.baseTable = bt;
			this.file = file;
			this.remove = remove;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkSelectedFile()) {
				UpdateFile uf = baseTable.getSelectedFile();
				if (remove)
					try {
						RemoteUpdateService.getInstance().delete(uf);
						List<UpdateFile> list = RemoteUpdateService
								.getInstance().listAllUpdateFiles(file, null);
						baseTable.setElements(list);
						baseTable.updateTable();
						// versoes(file);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				else {
					versoes(uf);
				}
			}
		}

		@SuppressWarnings("unused")
		protected void tableUpdated() {
			try {
				baseTable.setElements(RemoteUpdateService.getInstance()
						.listAllUpdateFiles(file, null));
			} catch (Exception e) {
				e.printStackTrace();
			}
			baseTable.updateTable();
		}
	}

	private class UndoButton extends JButton implements ActionListener {

		private static final long serialVersionUID = -2808062270163319012L;
		public BaseTable baseTable;
		private Update file;
		private UpdateFile fileOri;

		public UndoButton(BaseTable bt, String text, Update file,
				UpdateFile fileOri) {
			super(text);
			this.baseTable = bt;
			this.file = file;
			this.fileOri = fileOri;
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkSelectedFile()) {
				UpdateFile uf = baseTable.getSelectedFile();
				try {
					RemoteUpdateService.getInstance().undoVersion(uf, file);
					List<UpdateFile> list = RemoteUpdateService.getInstance()
							.listAllUpdateFiles(fileOri, null);
					baseTable.setElements(list);
					baseTable.updateTable();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				{
					UpdateFileCadastreForm.this.tableUpdated();
				}
			}
		}

	}

	/**
	 * This method initializes buttonsPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new FlowLayout());
			buttonsPanel.setBounds(new Rectangle(10, 320, 590, 36));
			buttonsPanel.add(getSalvarButton(), null);
			buttonsPanel.add(getCancelButton(), null);
		}
		return buttonsPanel;
	}

	/**
	 * This method initializes salvarButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSalvarButton() {
		if (salvarButton == null) {
			salvarButton = new JButton();
			salvarButton.setText("Salvar");
			salvarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (file == null || arq == null || arq.getFullBytes() == null) {
						JOptionPane.showMessageDialog(editDialog,
								"Você precisa selecionar o arquivo de versão!",
								"Arquivo de versão não selecionado",
								JOptionPane.ERROR_MESSAGE);
					} else
						try {
							doReverseBind();
							RemoteUpdateService.getInstance().saveOrUpdate(
									file, updateBean, arq, inst, null);
							tableUpdated();
							file.setId(0);
							editDialog.dispose();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
				}
			});
		}
		return salvarButton;
	}

	protected void tableUpdated() {
		try {
			getBaseTable().setElements(
					RemoteUpdateService.getInstance().listAllUpdateFiles(
							updateBean, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		getBaseTable().updateTable();
	}

	@SuppressWarnings("unchecked")
	private class BaseTable extends JXTable {

		private static final long serialVersionUID = 4149415162307104015L;
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
			initialize();
		}

		protected UpdateFile getSelectedFile() {
			int row = getSelectedRow();
			if (row > -1) {
				return (UpdateFile) getElements().get(row);
			}
			return null;
		}

		@SuppressWarnings("deprecation")
		private void initialize() {
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
			getColumnModel().getColumn(0).setPreferredWidth(10);
			getColumnModel().getColumn(1).setPreferredWidth(90);
			getColumnModel().getColumn(2).setPreferredWidth(240);

			getColumnModel().getColumn(3).setPreferredWidth(40);
			getColumnModel().getColumn(4).setPreferredWidth(40);
			getColumnModel().getColumn(5).setPreferredWidth(140);
			getColumnModel().getColumn(6).setPreferredWidth(40);
			getColumnModel().getColumn(7).setPreferredWidth(40);
		}

		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][8];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof UpdateFile) {
						UpdateFile update = (UpdateFile) obj;

						try {
							values[i][0] = update.getId();
							values[i][1] = update.getUpdateFileKind().name()
									.replace("_", " ");
							values[i][2] = update.getObs();
							values[i][3] = update.isPublished();
							DatePropertyEditor dt = new DatePropertyEditor();
							dt.setValue(update.getDate());
							values[i][4] = dt.getAsText();
							if (update.getCurrentFile() != null) {
								values[i][5] = update.getCurrentFile()
										.getName();
								values[i][6] = update.getCurrentFile()
										.getFormat();
							}
							values[i][7] = update.getVersion();
						} catch (Exception ex) {
							ex.printStackTrace();
						} finally {

						}

						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(10);
				getColumnModel().getColumn(1).setPreferredWidth(90);
				getColumnModel().getColumn(2).setPreferredWidth(240);
				getColumnModel().getColumn(3).setPreferredWidth(40);
				getColumnModel().getColumn(4).setPreferredWidth(40);

				getColumnModel().getColumn(5).setPreferredWidth(140);
				getColumnModel().getColumn(6).setPreferredWidth(40);
				getColumnModel().getColumn(7).setPreferredWidth(40);
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(10);
				getColumnModel().getColumn(1).setPreferredWidth(90);
				getColumnModel().getColumn(2).setPreferredWidth(240);
				getColumnModel().getColumn(3).setPreferredWidth(40);
				getColumnModel().getColumn(4).setPreferredWidth(40);

				getColumnModel().getColumn(5).setPreferredWidth(140);
				getColumnModel().getColumn(6).setPreferredWidth(40);
				getColumnModel().getColumn(7).setPreferredWidth(40);
				updateUI();
			}
		}

		private class BaseTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

			}

		}

		private class BaseTableModel extends DefaultTableModel {

			private static final long serialVersionUID = -3328037402501002522L;

			Class types[] = new java.lang.Class[] { Integer.class,
					String.class, String.class, Boolean.class, String.class,
					String.class, String.class, String.class };

			boolean canEdit[] = new boolean[] { false, false, false, false,
					false, false, false, false };

			public BaseTableModel(Object[][] values) {
				super(values, new String[] { "Cod", "Tipo do Arquivo",
						"Observação", "Publicar", "Data", "Arquivo", "Formato",
						"Versão" });
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
	 * This method initializes cancelButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Cancelar");
			cancelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					file.setId(0);
					editDialog.dispose();
				}
			});
		}
		return cancelButton;
	}

	/**
	 * This method initializes reportButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getReportButton() {
		if (reportButton == null) {
			reportButton = new JButton();
			reportButton.setText("Relatório");
			reportButton.setEnabled(false);
			reportButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// TODO desenvolver o relatório do updatefile
				}
			});
		}
		return reportButton;
	}

	/**
	 * This method initializes autorizacoesButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAutorizacoesButton() {
		if (autorizacoesButton == null) {
			autorizacoesButton = new JButton();
			autorizacoesButton.setText("Autorizações");
			autorizacoesButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							if (checkSelectedFile()) {
								try {
									JDialog jd = new JDialog(AdapitVirtualFrame
											.getInstance());
									jd.setSize(new Dimension(650, 480));
									jd
											.setTitle("Filtrar usuários para autorização de acesso à arquivo");
									jd.setLocation(UIUtil.getScreenCenter(jd));
									final UsuarioListForm listForm = new UsuarioListForm();
									UpdateFile uf = baseTable.getSelectedFile();
									List<Usuario> userList = RemoteUpdateService
											.getInstance().listAutorizedUsers(
													uf);
									listForm.listar(userList);
									jd.add(listForm, BorderLayout.CENTER);
									JPanel jp = new JPanel(new FlowLayout());
									JButton listbt = new JButton(
											"Listar Autorizados");
									listbt
											.addActionListener(new ActionListener() {
												@Override
												public void actionPerformed(
														ActionEvent arg0) {
													listarAutorizados(listForm);
												}
											});
									jp.add(listbt);
									listbt = new JButton("Autorizar Usuários");
									listbt
											.addActionListener(new ActionListener() {
												@Override
												public void actionPerformed(
														ActionEvent arg0) {
													try {
														Usuario[] users = listForm
																.getSelectedUsuarios();
														if (users != null
																&& users.length > 0) {
															UpdateFile uf = baseTable
																	.getSelectedFile();
															ArrayList<Participante> arr = new ArrayList<Participante>();
															for (Usuario user : users) {
																if (user
																		.getDadosPessoais() instanceof Participante)
																	arr
																			.add((Participante) user
																					.getDadosPessoais());
															}
															RemoteUpdateService
																	.getInstance()
																	.autorizeUsers(
																			arr,
																			true,
																			uf);
														}
														listarAutorizados(listForm);
													} catch (Exception e) {
														e.printStackTrace();
													}
												}
											});
									jp.add(listbt);
									listbt = new JButton(
											"Desautorizar Usuários");
									listbt
											.addActionListener(new ActionListener() {
												@Override
												public void actionPerformed(
														ActionEvent arg0) {
													try {
														Usuario[] users = listForm
																.getSelectedUsuarios();
														if (users != null
																&& users.length > 0) {
															UpdateFile uf = baseTable
																	.getSelectedFile();
															ArrayList<Participante> arr = new ArrayList<Participante>();
															for (Usuario user : users) {
																if (user
																		.getDadosPessoais() instanceof Participante)
																	arr
																			.add((Participante) user
																					.getDadosPessoais());
															}
															RemoteUpdateService
																	.getInstance()
																	.autorizeUsers(
																			arr,
																			false,
																			uf);
														}
														listarAutorizados(listForm);
													} catch (Exception e) {
														e.printStackTrace();
													}
												}
											});
									jp.add(listbt);
									jd.add(jp, BorderLayout.SOUTH);
									jd.setVisible(true);
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
					});
		}
		return autorizacoesButton;
	}

	public void listarAutorizados(UsuarioListForm listForm) {
		try {
			UpdateFile uf = baseTable.getSelectedFile();
			List<Usuario> userList = RemoteUpdateService.getInstance()
					.listAutorizedUsers(uf);
			listForm.listar(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes arquivoPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getArquivoPanel1() {
		if (arquivoPanel1 == null) {
			arquivoPanel1 = new JPanel();
			arquivoPanel1.setLayout(new BorderLayout());
			arquivoPanel1.setBounds(new Rectangle(10, 213, 590, 50));
			arquivoPanel1.setBorder(BorderFactory.createTitledBorder(null,
					"Selecione o arquivo de instruções",
					TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Tahoma",
							Font.PLAIN, 11), Color.black));
			arquivoPanel1.add(getArquivoTextField1(),
					java.awt.BorderLayout.CENTER);
			arquivoPanel1.add(getJp1(), java.awt.BorderLayout.EAST);
		}
		return arquivoPanel1;
	}

	/**
	 * This method initializes arquivoTextField1
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getArquivoTextField1() {
		if (arquivoTextField1 == null) {
			arquivoTextField1 = new JTextField();
			arquivoTextField1.setEnabled(true);
			arquivoTextField1.setEditable(false);
		}
		return arquivoTextField1;
	}

	/**
	 * This method initializes jp1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJp1() {
		if (jp1 == null) {
			jp1 = new JPanel(new GridLayout(1, 2));
			jp1.setPreferredSize(new Dimension(160, 24));
			jp1.add(getProcurarArquivoButton1(), getProcurarArquivoButton1()
					.getName());
			jp1.add(getBuscarArquivoButton1(), getBuscarArquivoButton1()
					.getName());
		}
		return jp1;
	}

	/**
	 * This method initializes procurarArquivoButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getProcurarArquivoButton1() {
		if (procurarArquivoButton1 == null) {
			procurarArquivoButton1 = new JButton();
			procurarArquivoButton1.setText("Buscar");
			procurarArquivoButton1
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							final JDialog jd = new JDialog(AdapitVirtualFrame
									.getInstance());

							jd.setModal(true);
							jd.setSize(800, 400);
							jd.setLocation(UIUtil.getScreenCenter(jd));
							jd
									.setTitle("Buscar arquivo para anexar em instruções");
							jd
									.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							FileFilterPanel ffp = new FileFilterPanel();
							jd.add(ffp, BorderLayout.CENTER);
							JPanel flPanel = new JPanel(new FlowLayout());
							JButton jb = new JButton(
									"Selecionar o arquivo para instruções");
							jb.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									jd.dispose();
								}

							});
							flPanel.add(jb);
							jd.add(flPanel, BorderLayout.SOUTH);
							jd.setVisible(true);

							inst = ffp.getSelectedArquivo();
							if (inst != null)
								updateSelectedArquive1();
						}

					});
		}
		return procurarArquivoButton1;
	}

	/**
	 * This method initializes buscarArquivoButton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBuscarArquivoButton1() {
		if (buscarArquivoButton1 == null) {
			buscarArquivoButton1 = new JButton();
			buscarArquivoButton1.setText("Editar");
			buscarArquivoButton1
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							final JDialog jd = new JDialog(AdapitVirtualFrame
									.getInstance());
							jd.setModal(true);
							jd.setSize(800, 400);
							jd.setLocation(UIUtil.getScreenCenter(jd));
							jd.setTitle("Editar arquivo de informação");
							jd
									.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							FileFilterPanel ffp = new FileFilterPanel();
							jd.add(ffp, BorderLayout.CENTER);
							ffp.editRegister(arq);
							JPanel flPanel = new JPanel(new FlowLayout());
							jd.add(flPanel, BorderLayout.SOUTH);
							jd.setVisible(true);

						}

					});
		}
		return buscarArquivoButton1;
	}

	/**
	 * This method initializes restrictCheckBox
	 * 
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getRestrictCheckBox() {
		if (restrictCheckBox == null) {
			restrictCheckBox = new JCheckBox();
			restrictCheckBox.setBounds(new Rectangle(10, 66, 330, 21));
			restrictCheckBox
					.setText("O arquivo é restrito para donwload de usuários específicos?");
		}
		return restrictCheckBox;
	}

	/**
	 * This method initializes whyPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getWhyPanel() {
		if (whyPanel == null) {
			whyPanel = new JPanel();
			whyPanel.setLayout(new BorderLayout());
			whyPanel.setBorder(BorderFactory.createTitledBorder(null,
					"Por Que Mudou?", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Tahoma",
							Font.PLAIN, 11), Color.black));
			whyPanel.add(getWhyScrollPane(), BorderLayout.CENTER);
		}
		return whyPanel;
	}

	/**
	 * This method initializes whyScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getWhyScrollPane() {
		if (whyScrollPane == null) {
			whyScrollPane = new JScrollPane();
			whyScrollPane.setViewportView(getWhyTextPane());
		}
		return whyScrollPane;
	}

	/**
	 * This method initializes whyTextPane
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextPane getWhyTextPane() {
		if (whyTextPane == null) {
			whyTextPane = new JTextPane();
		}
		return whyTextPane;
	}

	/**
	 * This method initializes whoPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getWhoPanel() {
		if (whoPanel == null) {
			whoPanel = new JPanel();
			whoPanel.setLayout(new BorderLayout());
			whoPanel.setBorder(BorderFactory.createTitledBorder(null,
					"Quem Mudou?", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Tahoma",
							Font.PLAIN, 11), Color.black));
			whoPanel.add(getWhoScrollPane(), BorderLayout.CENTER);
		}
		return whoPanel;
	}

	/**
	 * This method initializes whoScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getWhoScrollPane() {
		if (whoScrollPane == null) {
			whoScrollPane = new JScrollPane();
			whoScrollPane.setViewportView(getWhoTextPane());
		}
		return whoScrollPane;
	}

	/**
	 * This method initializes whoTextPane
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextPane getWhoTextPane() {
		if (whoTextPane == null) {
			whoTextPane = new JTextPane();
		}
		return whoTextPane;
	}

	/**
	 * This method initializes whatPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getWhatPanel() {
		if (whatPanel == null) {
			whatPanel = new JPanel();
			whatPanel.setLayout(new BorderLayout());
			whatPanel.setBorder(BorderFactory.createTitledBorder(null,
					"O Que Mudou?", TitledBorder.DEFAULT_JUSTIFICATION,
					TitledBorder.DEFAULT_POSITION, new Font("Tahoma",
							Font.PLAIN, 11), Color.black));
			whatPanel.add(getWhatScrollPane(), BorderLayout.CENTER);
		}
		return whatPanel;
	}

	/**
	 * This method initializes whatScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getWhatScrollPane() {
		if (whatScrollPane == null) {
			whatScrollPane = new JScrollPane();
			whatScrollPane.setViewportView(getWhatTextPane());
		}
		return whatScrollPane;
	}

	/**
	 * This method initializes whatTextPane
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextPane getWhatTextPane() {
		if (whatTextPane == null) {
			whatTextPane = new JTextPane();
		}
		return whatTextPane;
	}

	/**
	 * This method initializes textTabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getTextTabbedPane() {
		if (textTabbedPane == null) {
			textTabbedPane = new JTabbedPane();
			textTabbedPane.setBounds(new Rectangle(10, 90, 590, 118));
			textTabbedPane.addTab("Por que mudou?", getWhyPanel());
			textTabbedPane.addTab("O que mudou?", getWhatPanel());
			textTabbedPane.addTab("Quem mudou?", getWhoPanel());
			textTabbedPane.addTab("Observações", getObsScrollPane());
		}
		return textTabbedPane;
	}

	/**
	 * This method initializes topGridPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getTopGridPanel() {
		if (topGridPanel == null) {
			topGridPanel = new JPanel();
			topGridPanel.setLayout(new GridLayout(3, 1));
			topGridPanel.setPreferredSize(new Dimension(600, 80));
			// topGridPanel.setLayout(null);
			topGridPanel.setBounds(new Rectangle(11, 6, 606, 80));
			tipoArquivoLabel = new JLabel();
			tipoArquivoLabel.setBounds(new Rectangle(227, 0, 113, 22));
			tipoArquivoLabel.setText("Tipo do arquivo:");
			versionLabel = new JLabel();
			versionLabel.setBounds(new Rectangle(0, 0, 177, 22));
			versionLabel.setText("Versão do arquivo:");
			// P1
			JPanel p1 = new JPanel();
			p1.setLayout(null);
			topGridPanel.add(p1);
			p1.add(versionLabel, null);
			p1.add(getVersionTextField(), null);
			p1.add(getDtUpdateLabel());
			p1.add(getDataPublicacao());
			// END P1
			JPanel p2 = new JPanel();
			p2.setLayout(null);
			topGridPanel.add(p2);
			p2.add(getPublishedCheckBox(), null);
			p2.add(tipoArquivoLabel, null);
			p2.add(getTipoComboBox(), null);
			// END P2
			topGridPanel.add(getRestrictCheckBox(), null);
		}
		return topGridPanel;
	}

	/**
	 * This method initializes topCenterPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getTopCenterPanel() {
		if (topCenterPanel == null) {
			topCenterPanel = new JPanel();
			topCenterPanel.setLayout(new BorderLayout());
			topCenterPanel.setBounds(new Rectangle(3, 99, 600, 209));
			JPanel jp1 = new JPanel(new GridLayout(2, 1));
			jp1.add(getArquivoPanel(), null);
			jp1.add(getArquivoPanel1(), null);
			topCenterPanel.add(jp1, BorderLayout.SOUTH);
			topCenterPanel.add(getTextTabbedPane(), BorderLayout.CENTER);
		}
		return topCenterPanel;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
