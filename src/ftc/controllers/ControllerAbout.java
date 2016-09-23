package ftc.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ftc.views.DialogAbout;

public class ControllerAbout {
	
	private DialogAbout dialog;
	
	/**
	 * Classe de controle dos alunos do trabalho
	 */
	public ControllerAbout() {
		dialog = new DialogAbout();
		
		dialog.getLabelDisciplina().setText("Disciplina:");
		dialog.getTextDisciplina().setText("FTC - Fundamentos Teóricos da Computação");
		dialog.getLabelTurma().setText("Turma:");
		dialog.getTextTurma().setText("12-5NA");
		dialog.getLabelProfessor().setText("Professor:");
		dialog.getTextProfessor().setText("Alair Dias Junior");
		dialog.getLabelAlunos().setText("Alunos:");
		dialog.getTextAlunos().setText("Eric Vilar Yankous Castanheira\n" +
				   					   "Gabriel Barbosa\n"+
				   					   "Luiz Paulo da Silva\n"+
				   					   "Matheus Neiva Amaro");
		
		dialog.getButtonFechar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				buttonFechar_click(evt.getSource());
			}
		});
		
		dialog.setVisible(true);
	}
	
	protected void buttonFechar_click(Object sender) {
		dialog.dispose();
	}
}
