package com.adapit.portal.ui.forms.software;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.adapit.portal.ui.forms.solution.ComercialSolutionDescricaoPropertyEditorDialog;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.tree.CategoriaSelectableTreeController;
import com.workcase.gui.custom.warning.JWarningComponent;

public class SoftwareDescriptionPanel extends JPanel{

	private SoftwareSolutionCadastreForm observer;
	private JPanel descricaoMainPanel = null;
	private JTextPane descricaoTextPane;
	protected JScrollPane descricaoScrollPane;
	private JLabel descricaoTextAreaLabel;
	protected JButton generateDescButton;
	JFormattedTextField avaliacaoTextField;
	private JLabel avaliacaoTextFieldLabel;
	private JPanel subCategoriasPanel;
	private JTextField categoriaTextField;
	private JLabel categoriaTextFieldLabel;
	private JScrollPane treeScrollPane;
	JCheckBox formatadorDescricaoCheckBox = null;
	protected JPanel treeButtonsPanel;
	protected JButton refreshTree;
	protected JButton editTree;
	
	public SoftwareDescriptionPanel(SoftwareSolutionCadastreForm observer){
		this.observer = observer;
		initialize();
	}
	
	private void initialize(){
		setLayout(new BorderLayout());
		add(getDescricaoMainPanel(),BorderLayout.CENTER);
		setBounds(new Rectangle(5, 6, 626, 111));
	}
	
	private JPanel getDescricaoMainPanel() {
		if (descricaoMainPanel == null) {
			descricaoMainPanel = new JPanel();
			descricaoMainPanel.setLayout(null);
			descricaoMainPanel.add(getDescricaoScrollPane());
			descricaoMainPanel.add(getDescricaoTextAreaLabel());
			descricaoMainPanel.add(getGenerateDescButton());
			descricaoMainPanel.add(getAvaliacaoCategoriaPanel());
			descricaoMainPanel.add(getSubCategoriasPanel());
			descricaoMainPanel.add(getFormatadorDescricaoCheckBox());
		}
		return descricaoMainPanel;
	}
	
	private JPanel avaliacaoCategoriaPanel;
	protected JPanel getAvaliacaoCategoriaPanel(){
		if(avaliacaoCategoriaPanel == null){
			avaliacaoCategoriaPanel = new JPanel();
			avaliacaoCategoriaPanel.setSize(new Dimension(130, 40));
			avaliacaoCategoriaPanel.setLocation(new Point(15, 99));
			avaliacaoCategoriaPanel.setLayout(new GridLayout(2,1));
			avaliacaoCategoriaPanel.add(getAvaliacaoTextFieldLabel());
			avaliacaoCategoriaPanel.add(getAvaliacaoTextField());
			
		}
		return avaliacaoCategoriaPanel;
	}
	
	protected JScrollPane getDescricaoScrollPane(){
		if(descricaoScrollPane == null){
			descricaoScrollPane = new JScrollPane();
			descricaoScrollPane.setSize(new Dimension(473, 131));
			descricaoScrollPane.setLocation(new Point(151, 7));
			descricaoScrollPane.add(getDescricaoTextPane());
			descricaoScrollPane.setViewportView(getDescricaoTextPane());
			return descricaoScrollPane;
		}
		return descricaoScrollPane;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getDescricaoTextPane(){
		if(descricaoTextPane == null){
			descricaoTextPane = new JTextPane();
			observer.binder.addBindProperty(observer.sol, this.descricaoTextPane, "descricao");
			
			observer.hashComps.put("descricao", this.descricaoTextPane);
			JWarningComponent warn = new JWarningComponent( this.descricaoTextPane);
			return warn;
		}
		return descricaoTextPane;
	}
	
	
	@SuppressWarnings("unchecked")
	protected JButton getGenerateDescButton(){
		if (generateDescButton == null){
			generateDescButton = new JButton("Gerar Descrição");
			generateDescButton.setSize(new Dimension(130, 52));
			generateDescButton.setHorizontalTextPosition(SwingConstants.CENTER);
			generateDescButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			generateDescButton.setPreferredSize(new Dimension(110, 45));
			generateDescButton.setIcon(new ImageIcon(getClass().getResource("/imgs/wand.png")));
			generateDescButton.setLocation(new Point(15, 35));
			generateDescButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					if (observer.getSelectedElement() != null){
						ComercialSolutionDescricaoPropertyEditorDialog p = new ComercialSolutionDescricaoPropertyEditorDialog(AdapitVirtualFrame.getInstance(), observer.getSelectedElement());
						p.processString(observer.getSelectedElement().getTemplate());
						p.setVisible(true);
						String desc="";
						Enumeration e = p.getKeys().elements();
						if (e != null){
							int i=0;
							while (e.hasMoreElements()){
								String str = (String) e.nextElement();
								desc+=((i!=0)?'\n':"") + str+": " + p.getValues().get(str);
								i++;
							}
						}
						((JTextPane)getDescricaoTextPane()).setText(desc);						
					}else{
						JOptionPane.showMessageDialog(observer, "Selecione primeiro a categoria do Software.",
								"Gerar descrição a partir da categoria", JOptionPane.ERROR_MESSAGE);
					}
				}				
			});
		}
		return generateDescButton;
	}
	
	@SuppressWarnings("unchecked")
	private JComponent getFormatadorDescricaoCheckBox() {
		if (formatadorDescricaoCheckBox == null) {
			formatadorDescricaoCheckBox = new JCheckBox();
			formatadorDescricaoCheckBox.setBounds(new Rectangle(15, 141, 607, 21));
			formatadorDescricaoCheckBox.setText("Utilizar o formatador de descrição para apresentar os dados do Software na web");
			observer.binder.addBindProperty(observer.sol, this.formatadorDescricaoCheckBox, "utilizarFormatador");
			
			observer.hashComps.put("utilizarFormatador", this.formatadorDescricaoCheckBox);
			JWarningComponent warn = new JWarningComponent( this.formatadorDescricaoCheckBox);
			warn.setBounds(new Rectangle(15, 141, 607, 21));
			return warn;
		}
		return formatadorDescricaoCheckBox;
	}
	
	protected JPanel getSubCategoriasPanel(){
		if(subCategoriasPanel == null){
			subCategoriasPanel = new JPanel();
			subCategoriasPanel.setSize(new Dimension(611, 118));
			//subCategoriasPanel.setLocation(new Point(16, 228));
			subCategoriasPanel.setLocation(new Point(11, 164));
			subCategoriasPanel.setLayout(null);
			subCategoriasPanel.add(getCategoriaTextField());
			subCategoriasPanel.add(getCategoriaTextFieldLabel());
			subCategoriasPanel.add(getTreeScrollPane());
			subCategoriasPanel.add(getTreeButtonsPanel());
		}
		return subCategoriasPanel;
	}
	
	protected JTextField getCategoriaTextField(){
		if(categoriaTextField == null){
			categoriaTextField = new JTextField();
			categoriaTextField.setText("");
			categoriaTextField.setEditable(false);
			categoriaTextField.setSize(new Dimension(473, 20));
			categoriaTextField.setLocation(new Point(136, 1));
			return categoriaTextField;
		}
		return categoriaTextField;
	}
	
	protected JLabel getCategoriaTextFieldLabel(){
		if(categoriaTextFieldLabel == null){
			categoriaTextFieldLabel = new JLabel("Selecione a Categoria:");
			categoriaTextFieldLabel.setSize(new Dimension(130, 20));
			categoriaTextFieldLabel.setLocation(new java.awt.Point(0,0));
			categoriaTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return categoriaTextFieldLabel;
	}
	
	protected JScrollPane getTreeScrollPane(){
		if(treeScrollPane == null){
			treeScrollPane = new JScrollPane();
			treeScrollPane.setSize(new Dimension(576, 85));
			treeScrollPane.setLocation(new Point(34, 23));
			treeScrollPane.setLayout(new javax.swing.ScrollPaneLayout());
			treeScrollPane.setViewportView(getTreeController().getTree());
		}
		return treeScrollPane;
	}
	
	protected JPanel getTreeButtonsPanel(){
		if (treeButtonsPanel == null){
			treeButtonsPanel = new JPanel();
			treeButtonsPanel.setLayout(new GridLayout(3,1));
			treeButtonsPanel.setSize(new Dimension(30, 71));
			treeButtonsPanel.setLocation(new Point(0, 21));
			treeButtonsPanel.add(getRefreshTree());
			treeButtonsPanel.add(getEditTree());
			treeButtonsPanel.add(new JPanel());
			//treeButtonsPanel.add(new JPanel());
		}
		return treeButtonsPanel;
	}
	
	
	
	
	protected JButton getRefreshTree(){
		if (refreshTree == null){
			refreshTree = new JButton();
			refreshTree.setSize(20,20);
			refreshTree.setToolTipText("Atualizar as categorias");
			refreshTree.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshTree.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {					
					treeController.newTree();
				}				
			});
		}
		return refreshTree;
	}
	
	
	
	protected JButton getEditTree(){
		if (editTree == null){
			editTree = new JButton();
			editTree.setSize(20,20);
			editTree.setToolTipText("Editar as categorias");
			editTree.setIcon(new ImageIcon(getClass().getResource("/imgs/note_edit.png")));
			editTree.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarCategorias();
				}				
			});
		}
		return editTree;
	}
	
	protected JLabel getDescricaoTextAreaLabel(){
		if(descricaoTextAreaLabel == null){
			descricaoTextAreaLabel = new JLabel(observer.messages.getMessage("Descrição"));
			descricaoTextAreaLabel.setSize(new Dimension(128, 20));
			descricaoTextAreaLabel.setLocation(new Point(15, 8));
			descricaoTextAreaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return descricaoTextAreaLabel;
	}
	
	

	
	@SuppressWarnings("unchecked")
	protected JComponent getAvaliacaoTextField(){

		if(avaliacaoTextField == null){
			avaliacaoTextField = new JFormattedTextField();			
			avaliacaoTextField.setHorizontalAlignment(JTextField.RIGHT);
			avaliacaoTextField.setText("");
			avaliacaoTextField.setSize(new java.awt.Dimension(60,20));
			avaliacaoTextField.setLocation(new java.awt.Point(105,0));
			
			observer.binder.addBindProperty(observer.sol, this.avaliacaoTextField, "avaliacao");
			
			observer.hashComps.put("avaliacao", this.avaliacaoTextField);
			JWarningComponent warn = new JWarningComponent( this.avaliacaoTextField);
			warn.setBounds(105, 0, 60, 20);
			return warn;
		}
		return avaliacaoTextField;
	}
	
	protected JLabel getAvaliacaoTextFieldLabel(){
		if(avaliacaoTextFieldLabel == null){
			avaliacaoTextFieldLabel = new JLabel("Avaliação:");
			avaliacaoTextFieldLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));			
		}
		return avaliacaoTextFieldLabel;
	}


	

	
	CategoriaSelectableTreeController treeController;
	
	protected CategoriaSelectableTreeController getTreeController(){
		if (treeController == null){
			treeController = AdapitVirtualFrame.getInstance().getTreeController(observer);
		}
		return treeController;
	}
}
