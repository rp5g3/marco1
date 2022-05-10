package com.adapit.portal.ui.forms.endereco;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.BrasilEstado;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.services.remote.RemoteEnderecoService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class EnderecoFilterDialog extends JDialog {

private JPanel enderecoPanel;
private JLabel titleLabel = null;

private JButton selecionarEnderecoButton = null;
	
	private JComboBox cidadeComboBox;

	private Endereco endereco = new Endereco();  //  @jve:decl-index=0:

	private JLabel cidadeComboBoxLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JComboBox ruaComboBox;
	
	private JLabel ruaComboBoxLabel;
	
	private JComboBox bairroComboBox;
	
	private JLabel bairroComboBoxLabel;
	
	private JComboBox estadoComboBox;
	
	private JLabel estadoComboBoxLabel;
	
	private AddressType tipoEndereco= AddressType.Residencial;  //  @jve:decl-index=0:

	private JLabel paisLabel = null;

	private SpringResourceMessage messages1 = null;

	private JComboBox paisComboBox = null;

	
	
	public EnderecoFilterDialog(){
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}
	
	private void initialize(){
		setSize(new Dimension(375, 244));
		setTitle("Dados de endereços já cadastrados");
		this.add(getContentPanel(), BorderLayout.CENTER);
		JPanel jp  = new JPanel();
		jp.setLayout(new FlowLayout());
		jp.add(getSelecionarEnderecoButton());
		this.add(jp, BorderLayout.SOUTH);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocation(UIUtil.getScreenCenter(this));
	}
	
	private JPanel contentPanel;
	
	private JPanel getContentPanel(){
		if (contentPanel == null){
			titleLabel = new JLabel();
			titleLabel.setBounds(new Rectangle(12, 13, 337, 20));
			titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			titleLabel.setText("Selecione os parâmetros do endereço");
			contentPanel = new JPanel();
			contentPanel.setSize(new Dimension(361, 214));
			contentPanel.setLocation(new java.awt.Point(0,0));
			contentPanel.setLayout(null);
			contentPanel.add(getEnderecoPanel());
			contentPanel.add(titleLabel, null);
			
		}
		return contentPanel;
	}

	public JPanel getEnderecoPanel(){
		if(enderecoPanel == null){
			paisLabel = new JLabel("* " +getMessages1().getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Cidade"));
			paisLabel.setBounds(new Rectangle(15, 0, 100, 20));
			paisLabel.setText("País:");
			paisLabel.setHorizontalAlignment(JLabel.LEFT);
			enderecoPanel = new JPanel();
			enderecoPanel.setSize(new Dimension(380, 130));
			enderecoPanel.setLocation(new Point(10, 40));
			enderecoPanel.setLayout(null);
			enderecoPanel.add(paisLabel);
			enderecoPanel.add(getPaisComboBox());
			enderecoPanel.add(getCidadeComboBox());
			enderecoPanel.add(getCidadeComboBoxLabel());
			enderecoPanel.add(getRuaComboBox());
			enderecoPanel.add(getRuaComboBoxLabel());
			enderecoPanel.add(getBairroComboBox());
			enderecoPanel.add(getBairroComboBoxLabel());
			enderecoPanel.add(getEstadoComboBoxLabel(), null);
			enderecoPanel.add(getEstadoComboBox(), null);
			enderecoPanel.add(getRefreshEstadosByPaisButton(), null);
			enderecoPanel.add(getRefreshCidadesByEstadoButton(), null);
			enderecoPanel.add(getRefreshBairrosByCidadeButton(), null);
			enderecoPanel.add(getRefreshRuasByBairroButton(), null);
			enderecoPanel.add(getClearEstadosByPaisButton(), null);
			enderecoPanel.add(getClearCidadesByEstadoButton(), null);
			enderecoPanel.add(getClearBairrosByCidadeButton(), null);
			enderecoPanel.add(getClearRuasByBairroButton(), null);
		}
		return enderecoPanel;
	}
	@SuppressWarnings("unchecked")
	protected JComboBox getCidadeComboBox(){
		if(cidadeComboBox == null){
			cidadeComboBox = new JComboBox();
			cidadeComboBox.setEditable(true);
			cidadeComboBox.setSize(new java.awt.Dimension(180,22));
			cidadeComboBox.setLocation(new Point(120, 49));
			cidadeComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
					refreshBairroByCidade();
				}				
			});
		}
		return cidadeComboBox;
	}
	

	protected JLabel getCidadeComboBoxLabel(){
		if(cidadeComboBoxLabel == null){
			cidadeComboBoxLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Cidade"));
			cidadeComboBoxLabel.setSize(new Dimension(100, 22));
			cidadeComboBoxLabel.setLocation(new Point(15, 49));
			cidadeComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return cidadeComboBoxLabel;
	}
	@SuppressWarnings("unchecked")
	protected JComboBox getRuaComboBox(){
		if(ruaComboBox == null){
			ruaComboBox = new JComboBox();
			ruaComboBox.setEditable(true);
			ruaComboBox.setSize(new java.awt.Dimension(180,22));
			ruaComboBox.setLocation(new Point(120, 99));			
		}
		return ruaComboBox;
	}
	
	protected JLabel getRuaComboBoxLabel(){
		if(ruaComboBoxLabel == null){
			ruaComboBoxLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Rua"));
			ruaComboBoxLabel.setSize(new java.awt.Dimension(100,22));
			ruaComboBoxLabel.setLocation(new Point(15, 99));
			ruaComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return ruaComboBoxLabel;
	}
	@SuppressWarnings("unchecked")
	protected JComboBox getBairroComboBox(){
		if(bairroComboBox == null){
			bairroComboBox = new JComboBox();
			bairroComboBox.setEditable(true);
			bairroComboBox.setSize(new Dimension(180, 22));
			bairroComboBox.setLocation(new Point(120, 74));	
			bairroComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
					refreshRuaByBairro();
				}				
			});
		}
		return bairroComboBox;
	}
	
	protected JLabel getBairroComboBoxLabel(){
		if(bairroComboBoxLabel == null){
			bairroComboBoxLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Bairro"));
			bairroComboBoxLabel.setSize(new Dimension(100, 22));
			bairroComboBoxLabel.setLocation(new Point(15, 74));
			bairroComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return bairroComboBoxLabel;
	}
	
	
	@SuppressWarnings("unchecked")
	protected JComboBox getEstadoComboBox(){
		if(estadoComboBox == null){
			estadoComboBox = new JComboBox();
			estadoComboBox.setEditable(true);
			estadoComboBox.setBounds(new Rectangle(120, 24, 180, 22));
			estadoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
					refreshCidadeByEstado();
				}				
			});
		}
		return estadoComboBox;
	}
	
	protected JLabel getEstadoComboBoxLabel(){
		if(estadoComboBoxLabel == null){
			estadoComboBoxLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Estado"));
			estadoComboBoxLabel.setBounds(new Rectangle(15, 24, 100, 22));
		}
		return estadoComboBoxLabel;
	}
	

	private SpringResourceMessage getMessages1() {
		if (messages1 == null) {
			messages1 = (SpringResourceMessage) SpringResourceMessage.getInstance();
		}
		return messages1;
	}

	
	private JComboBox getPaisComboBox() {
		if (paisComboBox == null) {
			paisComboBox = new JComboBox();
			paisComboBox.setBounds(new Rectangle(120, 0, 180, 22));
			for(int i=0; i< Pais.values().length; i++){
				paisComboBox.addItem(Pais.values()[i].name().replace("_"," "));
			}
			paisComboBox.setSelectedItem(Pais.Brasil.name().replace("_"," "));
			paisComboBox.addItem(" ");
			paisComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						try {
							String str = (String) paisComboBox.getSelectedItem();
							if (str.equalsIgnoreCase("brasil")) populateStates();
							else clearStates();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}				
			});
		}
		return paisComboBox;
	}

	public AddressType getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(AddressType tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void clearStates() {
		try {
			estadoComboBox.removeAllItems();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void populateStates(){
		try {
			for (int i=0; i < BrasilEstado.values().length;i++){
				estadoComboBox.addItem(BrasilEstado.values()[i].name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void emptyCowntry() {
		try {
			paisComboBox.setSelectedItem(" ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JButton clearEstadosByPaisButton = null;
	private JButton getClearEstadosByPaisButton() {
		if (clearEstadosByPaisButton == null) {
			clearEstadosByPaisButton = new JButton();
			clearEstadosByPaisButton.setBounds(new Rectangle(330, 24, 22, 22));
			clearEstadosByPaisButton.setToolTipText("Limpa a lista de estados");
			clearEstadosByPaisButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_mode.png")));
			clearEstadosByPaisButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getEstadoComboBox().removeAllItems();
				}
			});
		}
		return clearEstadosByPaisButton;
	}
	
	private JButton refreshEstadosByPaisButton = null;
	private JButton getRefreshEstadosByPaisButton() {
		if (refreshEstadosByPaisButton == null) {
			refreshEstadosByPaisButton = new JButton();
			refreshEstadosByPaisButton.setBounds(new Rectangle(305, 24, 22, 22));
			refreshEstadosByPaisButton.setToolTipText("Atualiza a lista de estados pelo País Selecionado");
			refreshEstadosByPaisButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshEstadosByPaisButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					refreshEstadoByPais();
				}
			});
		}
		return refreshEstadosByPaisButton;
	}
	
	private void refreshEstadoByPais(){
		try {
			List<String> list = RemoteEnderecoService.getInstance().listAllEstadosByPais(Pais.values()[getPaisComboBox().getSelectedIndex()]);
			if (list != null && list.size()>0){
				getEstadoComboBox().removeAllItems();
				for (String str: list)
					getEstadoComboBox().addItem(str);
					
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private JButton clearCidadesByEstadoButton = null;
	private JButton getClearCidadesByEstadoButton() {
		if (clearCidadesByEstadoButton == null) {
			clearCidadesByEstadoButton = new JButton();
			clearCidadesByEstadoButton.setBounds(new Rectangle(330, 49, 22, 22));
			clearCidadesByEstadoButton.setToolTipText("Limpa a Lista de cidades");
			clearCidadesByEstadoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_mode.png")));
			clearCidadesByEstadoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getCidadeComboBox().removeAllItems();
				}
			});
		}
		return clearCidadesByEstadoButton;
	}
	
	private JButton refreshCidadesByEstadoButton = null;
	private JButton getRefreshCidadesByEstadoButton() {
		if (refreshCidadesByEstadoButton == null) {
			refreshCidadesByEstadoButton = new JButton();
			refreshCidadesByEstadoButton.setBounds(new Rectangle(305, 49, 22, 22));
			refreshCidadesByEstadoButton.setToolTipText("Atualiza a Lista de cidades pelo estado selecionado");
			refreshCidadesByEstadoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshCidadesByEstadoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					refreshCidadeByEstado();
				}
			});
		}
		return refreshCidadesByEstadoButton;
	}
	
	private void refreshCidadeByEstado(){
		try {
			String value = "";
			if (getEstadoComboBox().getSelectedItem() != null)
			value = (String) getEstadoComboBox().getSelectedItem();
			List<String> list = RemoteEnderecoService.getInstance().listAllCidadesByEstado(value.toLowerCase());
			if (list != null && list.size()>0){
				getCidadeComboBox().removeAllItems();
				for (String str: list)
					getCidadeComboBox().addItem(str);
					
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private JButton clearBairrosByCidadeButton = null;
	private JButton getClearBairrosByCidadeButton() {
		if (clearBairrosByCidadeButton == null) {
			clearBairrosByCidadeButton = new JButton();
			clearBairrosByCidadeButton.setBounds(new Rectangle(330, 74, 22, 22));
			clearBairrosByCidadeButton.setToolTipText("Limpa a lista de bairros");
			clearBairrosByCidadeButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_mode.png")));
			clearBairrosByCidadeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getBairroComboBox().removeAllItems();
				}
			});
		}
		return clearBairrosByCidadeButton;
	}
	
	private JButton refreshBairrosByCidadeButton = null;
	private JButton getRefreshBairrosByCidadeButton() {
		if (refreshBairrosByCidadeButton == null) {
			refreshBairrosByCidadeButton = new JButton();
			refreshBairrosByCidadeButton.setBounds(new Rectangle(305, 74, 22, 22));
			refreshBairrosByCidadeButton.setToolTipText("Atualiza a lista de bairros pela cidade selecionada");
			refreshBairrosByCidadeButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshBairrosByCidadeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					refreshBairroByCidade();
				}
			});
		}
		return refreshBairrosByCidadeButton;
	}
	
	private void refreshBairroByCidade(){
		try {
			String value = "";
			if (getCidadeComboBox().getSelectedItem() != null)
			value = (String) getCidadeComboBox().getSelectedItem();
			List<String> list = RemoteEnderecoService.getInstance().listAllBairrosByCidade(value.toLowerCase());
			if (list != null && list.size()>0){
				getBairroComboBox().removeAllItems();
				for (String str: list)
					getBairroComboBox().addItem(str);
					
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	private JButton clearRuasByBairroButton = null;
	private JButton getClearRuasByBairroButton() {
		if (clearRuasByBairroButton == null) {
			clearRuasByBairroButton = new JButton();
			clearRuasByBairroButton.setBounds(new Rectangle(330, 99, 22, 22));
			clearRuasByBairroButton.setToolTipText("Limpa a lista de ruas");
			clearRuasByBairroButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_mode.png")));
			clearRuasByBairroButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getRuaComboBox().removeAllItems();
				}
			});
		}
		return clearRuasByBairroButton;
	}
	
	private JButton refreshRuasByBairroButton = null;
	private JButton getRefreshRuasByBairroButton() {
		if (refreshRuasByBairroButton == null) {
			refreshRuasByBairroButton = new JButton();
			refreshRuasByBairroButton.setBounds(new Rectangle(305, 99, 22, 22));
			refreshRuasByBairroButton.setToolTipText("Atualiza a lista de ruas pelo bairro");
			refreshRuasByBairroButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshRuasByBairroButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					refreshRuaByBairro();
				}
			});
		}
		return refreshRuasByBairroButton;
	}
	
	private void refreshRuaByBairro(){
		try {
			String value = "";
			if (getBairroComboBox().getSelectedItem() != null)
			value = (String) getBairroComboBox().getSelectedItem();
			List<String> list = RemoteEnderecoService.getInstance().listAllRuasByBairro(value.toLowerCase());
			if (list != null && list.size()>0){
				getRuaComboBox().removeAllItems();
				for (String str: list)
					getRuaComboBox().addItem(str);
					
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void bind() throws Exception{
		if (endereco != null){
			endereco.setPais(Pais.values()[paisComboBox.getSelectedIndex()]);
			String str = "";
			str = (String) estadoComboBox.getSelectedItem();
			endereco.setEstado(str);
			str = (String) cidadeComboBox.getSelectedItem();
			endereco.setCidade(str);
			str = (String) bairroComboBox.getSelectedItem();
			endereco.setBairro(str);
			str = (String) ruaComboBox.getSelectedItem();
			endereco.setRua(str);
		}
	}
	
	public JButton getSelecionarEnderecoButton() {
		if (selecionarEnderecoButton == null) {
			selecionarEnderecoButton = new JButton();
			selecionarEnderecoButton.setBounds(new Rectangle(132, 178, 103, 27));
			selecionarEnderecoButton.setText("Selecionar");
			selecionarEnderecoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						bind();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					dispose();
				}
			});
		}
		return selecionarEnderecoButton;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
