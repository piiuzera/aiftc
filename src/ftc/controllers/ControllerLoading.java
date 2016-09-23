package ftc.controllers;

import javax.swing.JOptionPane;

import ftc.views.DialogLoading;

public class ControllerLoading implements Runnable {
	private DialogLoading dialog;
	
	/**
	 * Classe de controle de carregamento inicial
	 */
	public ControllerLoading() {
		dialog = new DialogLoading();
		
		new Thread(this).start();
		
		dialog.setVisible(true);
	}

	@Override
	public void run() {
		int i = 0;
		while(true) {
			try {
				if(dialog.getProgressLoading().getValue() < dialog.getProgressLoading().getMaximum()) {
					dialog.getProgressLoading().setValue(i);
					i += 5;
					Thread.sleep(100);
				} else {
					dialog.dispose();
					break;
				}
			} catch(InterruptedException ex) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro e o Programa deverá ser fechado!",
						"Erro Inesperado", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
	}
}
