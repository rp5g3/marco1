package com.adapit.portal.ui.forms.destaque;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.adapit.portal.entidades.MessageFeedbackCounter;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.UpdateFile;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.entidades.MessageFeedbackCounter.FeedbackType;
import com.adapit.portal.services.remote.RemotePreferenciaService;
import com.adapit.portal.services.remote.RemoteUpdateService;
import com.adapit.portal.ui.forms.usuario.UsuarioListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;
import com.workcase.utils.DatePropertyEditor;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class MessageFeedbackFilter extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel filterFieldsPanel = null;
	private JPanel datePanel = null;
	private JCheckBox dataIntervalCheckBox = null;
	private JLabel inicLabel = null;
	private JTextField inicDateTextField = null;
	private JLabel dataFimLabel = null;
	private JTextField dataFinalTextField = null;
	private JCheckBox pessoaCheckBox = null;
	private JPanel participantePanel = null;
	private JTextField participanteTextField = null;
	private JButton buscarParticipanteButton = null;
	private JButton filtrarButton = null;
	
	private FeedbackType feedbackType;
	private Integer targetid;
	
	private Participante participante;

	/**
	 * This is the default constructor
	 */
	public MessageFeedbackFilter() {
		super();
		initialize();
	}
	
	DatePropertyEditor dt = new DatePropertyEditor();  //  @jve:decl-index=0:
	private JLabel resultSizeLabel = null;
	private JPanel resultadosPanel = null;
	private JScrollPane resultadosScrollPane = null;
	public MessageFeedbackFilter(FeedbackType type, int targ) {
		super();
		this.feedbackType=type;
		this.targetid=targ;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		resultSizeLabel = new JLabel();
		resultSizeLabel.setBounds(new Rectangle(10, 208, 643, 22));
		resultSizeLabel.setText("Resultados:");
		this.setSize(696, 473);
		this.setLayout(null);
		this.add(getFilterFieldsPanel(), null);
		this.add(resultSizeLabel, null);
		this.add(getResultadosPanel(), null);
	}

	/**
	 * This method initializes filterFieldsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFilterFieldsPanel() {
		if (filterFieldsPanel == null) {
			filterFieldsPanel = new JPanel();
			filterFieldsPanel.setLayout(null);
			filterFieldsPanel.setBounds(new Rectangle(10, 10, 642, 194));
			filterFieldsPanel.add(getDatePanel(), null);
			filterFieldsPanel.add(getDataIntervalCheckBox(), null);
			filterFieldsPanel.add(getPessoaCheckBox(), null);
			filterFieldsPanel.add(getParticipantePanel(), null);
			filterFieldsPanel.add(getButtonsPanel(), null);
		}
		return filterFieldsPanel;
	}

	/**
	 * This method initializes datePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDatePanel() {
		if (datePanel == null) {
			dataFimLabel = new JLabel();
			dataFimLabel.setText("Data Final:  ");
			dataFimLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			inicLabel = new JLabel();
			inicLabel.setText("Data Inicial:  ");
			inicLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			//GridLayout gridLayout = new GridLayout(2,2);
			
			datePanel = new JPanel();
			datePanel.setBorder(BorderFactory.createTitledBorder(null, "Filtrar por intervalo de data", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 11), Color.black));
			datePanel.setLayout(new GridLayout());
			datePanel.setBounds(new Rectangle(0, 25, 603, 46));
			datePanel.add(inicLabel, null);
			datePanel.add(getInicDateTextField(), null);
			datePanel.add(dataFimLabel, null);
			datePanel.add(getDataFinalTextField(), null);
		}
		return datePanel;
	}

	/**
	 * This method initializes dataIntervalCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getDataIntervalCheckBox() {
		if (dataIntervalCheckBox == null) {
			dataIntervalCheckBox = new JCheckBox();
			dataIntervalCheckBox.setBounds(new Rectangle(0, 0, 603, 21));
			dataIntervalCheckBox.setText("Incluir intervalo de data no filtro");
		}
		return dataIntervalCheckBox;
	}

	/**
	 * This method initializes inicDateTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getInicDateTextField() {
		if (inicDateTextField == null) {
			inicDateTextField = new JTextField();
			inicDateTextField.setText("01/01/2010");
		}
		return inicDateTextField;
	}

	/**
	 * This method initializes dataFinalTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDataFinalTextField() {
		if (dataFinalTextField == null) {
			dataFinalTextField = new JTextField();
			dataFinalTextField.setText("01/01/2010");
		}
		return dataFinalTextField;
	}

	/**
	 * This method initializes pessoaCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getPessoaCheckBox() {
		if (pessoaCheckBox == null) {
			pessoaCheckBox = new JCheckBox();
			pessoaCheckBox.setBounds(new Rectangle(0, 78, 597, 21));
			pessoaCheckBox.setText("Incluir participante no filtro");
		}
		return pessoaCheckBox;
	}

	/**
	 * This method initializes participantePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getParticipantePanel() {
		if (participantePanel == null) {
			participantePanel = new JPanel();
			participantePanel.setLayout(new BorderLayout());
			participantePanel.setBounds(new Rectangle(0, 99, 601, 53));
			participantePanel.setBorder(BorderFactory.createTitledBorder(null,
					"Filtrar pelo participante", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 11), Color.black));
			participantePanel.add(getParticipanteTextField(), BorderLayout.CENTER);
			participantePanel.add(getBuscarParticipanteButton(), BorderLayout.EAST);
		}
		return participantePanel;
	}

	/**
	 * This method initializes participanteTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getParticipanteTextField() {
		if (participanteTextField == null) {
			participanteTextField = new JTextField();
			participanteTextField.setEditable(false);
		}
		return participanteTextField;
	}

	/**
	 * This method initializes buscarParticipanteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscarParticipanteButton() {
		if (buscarParticipanteButton == null) {
			buscarParticipanteButton = new JButton();
			buscarParticipanteButton.setText("Buscar Participante");
			buscarParticipanteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					final JDialog jd = new JDialog(AdapitVirtualFrame.getInstance());
					jd.setSize(new Dimension(650,480));
					jd.setTitle("Filtrar usuários para incluir em pesquisa de feedback");
					jd.setLocation(UIUtil.getScreenCenter(jd));
					final UsuarioListForm listForm = new UsuarioListForm();
					jd.add(listForm,BorderLayout.CENTER);
					JPanel jp = new JPanel(new FlowLayout());
					JButton listbt=new JButton("Selecionar Usuario");
					listbt.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							Usuario user = listForm.getSelectedUsuario();
							if(user != null && user.getDadosPessoais() != null
									&& user.getDadosPessoais() instanceof Participante){
								participante = (Participante) user.getDadosPessoais();
								getParticipanteTextField().setText(participante.getNomeFormatado());
							}
							else getParticipanteTextField().setText("");
							jd.dispose();
						}							
					});
					jp.add(listbt);
					jd.add(jp,BorderLayout.SOUTH);
					jd.setModal(true);
					jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					jd.setVisible(true);
				}
			});
		}
		return buscarParticipanteButton;
	}

	/**
	 * This method initializes filtrarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getFiltrarButton() {
		if (filtrarButton == null) {
			filtrarButton = new JButton();
			filtrarButton.setText("Filtrar Feedback");
			filtrarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Date d1=null,d2=null;
					if(getDataIntervalCheckBox().isSelected()){
						dt.setAsText(getInicDateTextField().getText());
						d1 = (Date) dt.getValue();
						
						dt.setAsText(getDataFinalTextField().getText());
						d2 = (Date) dt.getValue();
					}
					Long partid=null;
					if(participante != null && getPessoaCheckBox().isSelected())
						partid=participante.getId();
					
					try {
						List<MessageFeedbackCounter> list = RemotePreferenciaService.getInstance().listMessageFeedbackCounterBy(feedbackType, d1, d2,
								partid, targetid);
						resultSizeLabel.setText("Resultados: " + list.size());
						getMsgFeedTable().setElements(list);
						getMsgFeedTable().updateTable();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return filtrarButton;
	}

	/**
	 * This method initializes resultadosPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getResultadosPanel() {
		if (resultadosPanel == null) {
			resultadosPanel = new JPanel();
			resultadosPanel.setLayout(new BorderLayout());
			resultadosPanel.setBounds(new Rectangle(10, 235, 641, 198));
			resultadosPanel.setBorder(BorderFactory.createTitledBorder(null, "Resultados encontrados na consulta", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 11), Color.black));
			resultadosPanel.add(getResultadosScrollPane(), BorderLayout.CENTER);
		}
		return resultadosPanel;
	}

	/**
	 * This method initializes resultadosScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getResultadosScrollPane() {
		if (resultadosScrollPane == null) {
			resultadosScrollPane = new JScrollPane(getMsgFeedTable());
		}
		return resultadosScrollPane;
	}

	private MsgFeedTable msgFeedTable;
	private JButton listAllButton = null;
	private JButton listNotTypeButton = null;
	private JPanel buttonsPanel = null;
	
	private MsgFeedTable getMsgFeedTable(){
		if(msgFeedTable == null){
			msgFeedTable = new MsgFeedTable();
		}
		return msgFeedTable;
	}

	/**
	 * This method initializes listAllButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getListAllButton() {
		if (listAllButton == null) {
			listAllButton = new JButton();
			listAllButton.setText("Ignorar Alvo");
			listAllButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						List<MessageFeedbackCounter> list = RemotePreferenciaService.getInstance().listMessageFeedbackCounterBy(
								feedbackType, null, null,
								null, null);
						resultSizeLabel.setText("Resultados: " + list.size());
						getMsgFeedTable().setElements(list);
						getMsgFeedTable().updateTable();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return listAllButton;
	}

	/**
	 * This method initializes listNotTypeButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getListNotTypeButton() {
		if (listNotTypeButton == null) {
			listNotTypeButton = new JButton();
			listNotTypeButton.setText("Listar Todos");
			listNotTypeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						List<MessageFeedbackCounter> list = RemotePreferenciaService.getInstance().listMessageFeedbackCounterBy(
								null, null, null,
								null, null);
						resultSizeLabel.setText("Resultados: " + list.size());
						getMsgFeedTable().setElements(list);
						getMsgFeedTable().updateTable();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return listNotTypeButton;
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
			buttonsPanel.setBounds(new Rectangle(0, 156, 602, 33));
			buttonsPanel.add(getFiltrarButton(), null);
			buttonsPanel.add(getListAllButton(), null);
			buttonsPanel.add(getListNotTypeButton(), null);
		}
		return buttonsPanel;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
