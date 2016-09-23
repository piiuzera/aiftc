package ftc;

import ftc.controllers.ControllerDetails;
import ftc.controllers.ControllerLoading;

public class Program {
	/**
	 * Classe principal
	 */
	public Program() {
		new ControllerLoading();
		new ControllerDetails();
	}
	
	/**
	 * Método Principal
	 * @param args
	 */
	public static void main(String [] args) {
		new Program();
	}
	
}
