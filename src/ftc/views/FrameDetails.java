package ftc.views;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import ftc.FtcStatusBar;

public class FrameDetails extends JFrame {
	
	private static final long serialVersionUID = -731488297439883825L;

	private JMenuBar bar;
	
	/*
	 * Itens do Menu
	 */
	private JMenu menuArquivo;
	private JMenu menuAjuda;
	
	/*
	 * Itens Menu Arquivo
	 */
	private JMenuItem menuArquivoAbrir;
	private JMenu menuArquivoSalvar;
	private JMenuItem menuArquivoSair;
	
	/*
	 * Itens Menu Arquivo -> Salvar
	 */
	private JMenuItem menuArquivoSalvarAfn;
	private JMenuItem menuArquivoSalvarAfd;
	
	/*
	 * Itens Menu Ajuda
	 */
	private JMenuItem menuAjudaSobre;
	
	/*
	 * Tabela de Divisões
	 */
	private JTabbedPane tab;
	
	/*
	 * Panel do AFN
	 */
	private JPanel panelAfn;
	
	/*
	 * Panel do AFD
	 */
	private JPanel panelAfd;
	
	/*
	 * Label do Panel AFN
	 */
	private JLabel labelAfnAlfabeto;
	private JLabel labelAfnEstadoInicial;
	private JLabel labelAfnEstadoFinal;
	private JLabel labelAfnTransicoes;
	
	/*
	 * Texto do Panel AFN
	 */
	private JTextArea textAfnAlfabeto;
	private JTextArea textAfnEstadoInicial;
	private JTextArea textAfnEstadoFinal;
	
	/*
	 * Tabela do Panel AFN
	 */
	private JTable tableAfnTransicoes;
	private JScrollPane scrollTableAfnTransicoes;
	
	/*
	 * Label do Panel AFD
	 */
	private JLabel labelAfdAlfabeto;
	private JLabel labelAfdEstadoInicial;
	private JLabel labelAfdEstadoFinal;
	private JLabel labelAfdTransicoes;
	
	/*
	 * Texto do Panel AFD
	 */
	private JTextArea textAfdAlfabeto;
	private JTextArea textAfdEstadoInicial;
	private JTextArea textAfdEstadoFinal;
	
	/*
	 * Tabela do Panel AFD
	 */
	private JTable tableAfdTransicoes;
	private JScrollPane scrollTableAfdTransicoes;
	
	/*
	 * Imagem Principal
	 */
	private JLabel imageMain;
	
	/*
	 * Status Bar
	 */
	private FtcStatusBar statusBar;
	
	private JLabel labelMensagem;
	
	/**
	 * Classe de visão dos detalhamentos de de AFN / AFD
	 */
	public FrameDetails() {
		super("FTC - Detalhamento do Autômato");
		setSize(380, 400);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setIconImage(new ImageIcon(this.getClass().getResource("/logo.png")).getImage());
		
		bar = new JMenuBar();
		
		menuArquivo = new JMenu("Arquivo");
		bar.add(menuArquivo);
		
		menuArquivoAbrir = new JMenuItem("Abrir");
		menuArquivoAbrir.setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-145-folder-open.png")));
		menuArquivo.add(menuArquivoAbrir);
		
		menuArquivoSalvar = new JMenu("Salvar");
		menuArquivoSalvar.setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-444-floppy-disk.png")));
		menuArquivo.add(menuArquivoSalvar);
		
		menuArquivoSalvarAfn = new JMenuItem("AFN");
		menuArquivoSalvarAfn.setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-444-floppy-disk.png")));
		menuArquivoSalvar.add(menuArquivoSalvarAfn);
		
		menuArquivoSalvarAfd = new JMenuItem("AFD");
		menuArquivoSalvarAfd.setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-444-floppy-disk.png")));
		menuArquivoSalvar.add(menuArquivoSalvarAfd);
		
		menuArquivo.addSeparator();
		
		menuArquivoSair = new JMenuItem("Sair");
		menuArquivoSair.setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-389-exit.png")));
		menuArquivo.add(menuArquivoSair);
		
		menuAjuda = new JMenu("Ajuda");
		bar.add(menuAjuda);
		
		menuAjudaSobre = new JMenuItem("Sobre");
		menuAjudaSobre.setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-196-circle-info.png")));
		menuAjuda.add(menuAjudaSobre);
		
		setJMenuBar(bar);
		
		imageMain = new JLabel(new ImageIcon(this.getClass().getResource("/logo_main.png")), JLabel.CENTER);
		imageMain.setBounds(0, 0, 360, 300);
		add(imageMain);
		
		tab = new JTabbedPane();
		add(tab);
		
		panelAfn = new JPanel();
		panelAfn.setLayout(null);
		tab.add("AFN", panelAfn);
		
		labelAfnAlfabeto = new JLabel("Alfabeto:");
		labelAfnAlfabeto.setBounds(10, 10, 60, 20);
		panelAfn.add(labelAfnAlfabeto);
		
		textAfnAlfabeto = new JTextArea();
		textAfnAlfabeto.setBounds(10, 30, 110, 20);
		textAfnAlfabeto.setEditable(false);
		textAfnAlfabeto.setLineWrap(true);
		textAfnAlfabeto.setWrapStyleWord(true);
		panelAfn.add(textAfnAlfabeto);
		
		labelAfnEstadoInicial = new JLabel("Inicial:");
		labelAfnEstadoInicial.setBounds(130, 10, 60, 20);
		panelAfn.add(labelAfnEstadoInicial);
		
		textAfnEstadoInicial = new JTextArea();
		textAfnEstadoInicial.setBounds(130, 30, 110, 20);
		textAfnEstadoInicial.setEditable(false);
		textAfnEstadoInicial.setLineWrap(true);
		textAfnEstadoInicial.setWrapStyleWord(true);
		panelAfn.add(textAfnEstadoInicial);
		
		labelAfnEstadoFinal = new JLabel("Final:");
		labelAfnEstadoFinal.setBounds(250, 10, 60, 20);
		panelAfn.add(labelAfnEstadoFinal);
		
		textAfnEstadoFinal = new JTextArea();
		textAfnEstadoFinal.setBounds(250, 30, 110, 20);
		textAfnEstadoFinal.setEditable(false);
		textAfnEstadoFinal.setLineWrap(true);
		textAfnEstadoFinal.setWrapStyleWord(true);
		panelAfn.add(textAfnEstadoFinal);
		
		labelAfnTransicoes = new JLabel("Transições:");
		labelAfnTransicoes.setBounds(10, 60, 80, 20);
		panelAfn.add(labelAfnTransicoes);
		
		tableAfnTransicoes = new JTable() {
			private static final long serialVersionUID = 6100235448140506222L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}			
		};
		tableAfnTransicoes.getTableHeader().setReorderingAllowed(false);
		scrollTableAfnTransicoes = new JScrollPane(tableAfnTransicoes);
		scrollTableAfnTransicoes.setBounds(10, 80, 350, 200);
		panelAfn.add(scrollTableAfnTransicoes);
		
		panelAfd = new JPanel();
		panelAfd.setLayout(null);
		tab.add("AFD", panelAfd);
		
		labelAfdAlfabeto = new JLabel("Alfabeto:");
		labelAfdAlfabeto.setBounds(10, 10, 60, 20);
		panelAfd.add(labelAfdAlfabeto);
		
		textAfdAlfabeto = new JTextArea();
		textAfdAlfabeto.setBounds(10, 30, 110, 20);
		textAfdAlfabeto.setEditable(false);
		textAfdAlfabeto.setLineWrap(true);
		textAfdAlfabeto.setWrapStyleWord(true);
		panelAfd.add(textAfdAlfabeto);
		
		labelAfdEstadoInicial = new JLabel("Inicial:");
		labelAfdEstadoInicial.setBounds(130, 10, 60, 20);
		panelAfd.add(labelAfdEstadoInicial);
		
		textAfdEstadoInicial = new JTextArea();
		textAfdEstadoInicial.setBounds(130, 30, 110, 20);
		textAfdEstadoInicial.setEditable(false);
		textAfdEstadoInicial.setLineWrap(true);
		textAfdEstadoInicial.setWrapStyleWord(true);
		panelAfd.add(textAfdEstadoInicial);
		
		labelAfdEstadoFinal = new JLabel("Final:");
		labelAfdEstadoFinal.setBounds(250, 10, 60, 20);
		panelAfd.add(labelAfdEstadoFinal);
		
		textAfdEstadoFinal = new JTextArea();
		textAfdEstadoFinal.setBounds(250, 30, 110, 20);
		textAfdEstadoFinal.setEditable(false);
		textAfdEstadoFinal.setLineWrap(true);
		textAfdEstadoFinal.setWrapStyleWord(true);
		panelAfd.add(textAfdEstadoFinal);
		
		labelAfdTransicoes = new JLabel("Transições:");
		labelAfdTransicoes.setBounds(10, 60, 80, 20);
		panelAfd.add(labelAfdTransicoes);
		
		tableAfdTransicoes = new JTable() {
			private static final long serialVersionUID = 6100235448140506222L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableAfdTransicoes.getTableHeader().setReorderingAllowed(false);
		scrollTableAfdTransicoes = new JScrollPane(tableAfdTransicoes);
		scrollTableAfdTransicoes.setBounds(10, 80, 350, 200);
		panelAfd.add(scrollTableAfdTransicoes);
		
		statusBar = new FtcStatusBar();
		
		labelMensagem = new JLabel();
		statusBar.setLeftComponent(labelMensagem);
		
		add(statusBar, BorderLayout.SOUTH);
	}

	public JMenuBar getBar() {
		return bar;
	}

	public JMenu getMenuArquivo() {
		return menuArquivo;
	}

	public JMenu getMenuAjuda() {
		return menuAjuda;
	}

	public JMenuItem getMenuArquivoAbrir() {
		return menuArquivoAbrir;
	}

	public JMenu getMenuArquivoSalvar() {
		return menuArquivoSalvar;
	}
	
	public JMenuItem getMenuArquivoSalvarAfn() {
		return menuArquivoSalvarAfn;
	}

	public JMenuItem getMenuArquivoSalvarAfd() {
		return menuArquivoSalvarAfd;
	}

	public JMenuItem getMenuArquivoSair() {
		return menuArquivoSair;
	}

	public JMenuItem getMenuAjudaSobre() {
		return menuAjudaSobre;
	}

	public JTabbedPane getTab() {
		return tab;
	}

	public JPanel getPanelAfn() {
		return panelAfn;
	}

	public JPanel getPanelAfd() {
		return panelAfd;
	}

	public JLabel getLabelAfnAlfabeto() {
		return labelAfnAlfabeto;
	}

	public JLabel getLabelAfnEstadoInicial() {
		return labelAfnEstadoInicial;
	}

	public JLabel getLabelAfnEstadoFinal() {
		return labelAfnEstadoFinal;
	}

	public JLabel getLabelAfnTransicoes() {
		return labelAfnTransicoes;
	}

	public JTextArea getTextAfnAlfabeto() {
		return textAfnAlfabeto;
	}

	public JTextArea getTextAfnEstadoInicial() {
		return textAfnEstadoInicial;
	}

	public JTextArea getTextAfnEstadoFinal() {
		return textAfnEstadoFinal;
	}

	public JTable getTableAfnTransicoes() {
		return tableAfnTransicoes;
	}

	public JScrollPane getScrollTableTransicoes() {
		return scrollTableAfnTransicoes;
	}

	public JLabel getLabelAfdAlfabeto() {
		return labelAfdAlfabeto;
	}

	public JLabel getLabelAfdEstadoInicial() {
		return labelAfdEstadoInicial;
	}

	public JLabel getLabelAfdEstadoFinal() {
		return labelAfdEstadoFinal;
	}

	public JLabel getLabelAfdTransicoes() {
		return labelAfdTransicoes;
	}

	public JTextArea getTextAfdAlfabeto() {
		return textAfdAlfabeto;
	}

	public JTextArea getTextAfdEstadoInicial() {
		return textAfdEstadoInicial;
	}

	public JTextArea getTextAfdEstadoFinal() {
		return textAfdEstadoFinal;
	}

	public JTable getTableAfdTransicoes() {
		return tableAfdTransicoes;
	}

	public JScrollPane getScrollTableAfdTransicoes() {
		return scrollTableAfdTransicoes;
	}
	
	public JLabel getImageMain() {
		return imageMain;
	}

	public FtcStatusBar getStatusBar() {
		return statusBar;
	}

	public JLabel getLabelMensagem() {
		return labelMensagem;
	}

}
