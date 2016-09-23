package ftc.views;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class DialogAbout extends JDialog {
	
	private static final long serialVersionUID = -8382604540232145182L;
	
	private JLabel labelDisciplina;
	private JLabel labelTurma;
	private JLabel labelProfessor;
	private JLabel labelAlunos;
	
	private JTextArea textDisciplina;
	private JTextArea textTurma;
	private JTextArea textProfessor;
	private JTextArea textAlunos;
	
	private JButton buttonFechar;
	
	public DialogAbout() {
		super();
		setTitle("FTC - Alunos");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setLayout(null);
		setModal(true);
		setResizable(false);
		setIconImage(new ImageIcon(this.getClass().getResource("/logo.png")).getImage());
		
		labelDisciplina = new JLabel();
		labelDisciplina.setBounds(10, 10, 80, 20);
		add(labelDisciplina);
		
		textDisciplina = new JTextArea();
		textDisciplina.setBounds(10, 30, 275, 20);
		textDisciplina.setEditable(false);
		textDisciplina.setLineWrap(true);
		textDisciplina.setWrapStyleWord(true);
		textDisciplina.setBackground(this.getBackground());
		add(textDisciplina);

		labelTurma = new JLabel();
		labelTurma.setBounds(10, 50, 80, 20);
		add(labelTurma);
		
		textTurma = new JTextArea();
		textTurma.setBounds(10, 70, 275, 20);
		textTurma.setEditable(false);
		textTurma.setLineWrap(true);
		textTurma.setWrapStyleWord(true);
		textTurma.setBackground(this.getBackground());
		add(textTurma);
		
		labelProfessor = new JLabel();
		labelProfessor.setBounds(10, 90, 80, 20);
		add(labelProfessor);
		
		textProfessor = new JTextArea();
		textProfessor.setBounds(10, 110, 275, 20);
		textProfessor.setEditable(false);
		textProfessor.setLineWrap(true);
		textProfessor.setWrapStyleWord(true);
		textProfessor.setBackground(this.getBackground());
		add(textProfessor);
		
		labelAlunos = new JLabel();
		labelAlunos.setBounds(10, 130, 80, 20);
		add(labelAlunos);
		
		textAlunos = new JTextArea();
		textAlunos.setBounds(10, 150, 275, 80);
		textAlunos.setEditable(false);
		textAlunos.setLineWrap(true);
		textAlunos.setWrapStyleWord(true);
		textAlunos.setBackground(this.getBackground());
		add(textAlunos);
		
		buttonFechar = new JButton("Fechar");
		buttonFechar.setBounds(200, 230, 80, 20);
		add(buttonFechar);
		
		getRootPane().setDefaultButton(buttonFechar);
	}

	public JLabel getLabelDisciplina() {
		return labelDisciplina;
	}
	
	public JLabel getLabelTurma() {
		return labelTurma;
	}

	public JLabel getLabelProfessor() {
		return labelProfessor;
	}

	public JLabel getLabelAlunos() {
		return labelAlunos;
	}

	public JTextArea getTextDisciplina() {
		return textDisciplina;
	}
	
	public JTextArea getTextTurma() {
		return textTurma;
	}

	public JTextArea getTextProfessor() {
		return textProfessor;
	}

	public JTextArea getTextAlunos() {
		return textAlunos;
	}

	public JButton getButtonFechar() {
		return buttonFechar;
	}
}
