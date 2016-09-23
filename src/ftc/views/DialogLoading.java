package ftc.views;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class DialogLoading extends JDialog {
	
	private static final long serialVersionUID = -3222186816391103861L;
	
	/*
	 * Logo Main
	 */
	private JLabel imageMain;
	
	/*
	 * Barra de Carregando
	 */
	private JLabel labelLoading;
	
	private JProgressBar progressLoading;
	
	/**
	 * Classe de visão do carregamento do sistema
	 */
	public DialogLoading() {
		super();
		setSize(300, 260);
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(true);
		setUndecorated(true);
		setModal(true);
		
		imageMain = new JLabel(new ImageIcon(this.getClass().getResource("/logo_main.png")), JLabel.CENTER);
		imageMain.setBounds(0, 0, 280, 200);
		add(imageMain);
		
		labelLoading = new JLabel("Carregando...");
		labelLoading.setBounds(10, 210, 100, 20);
		add(labelLoading);
		
		progressLoading = new JProgressBar(0, 100);
		progressLoading.setBounds(10, 230 , 280, 20);
		progressLoading.setForeground(Color.RED);
		add(progressLoading);
	}

	public JProgressBar getProgressLoading() {
		return progressLoading;
	}

	public JLabel getLabelLoading() {
		return labelLoading;
	}
	
	public JLabel getImageMain() {
		return imageMain;
	}
}
