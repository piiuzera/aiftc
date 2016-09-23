package ftc.models;

import java.util.ArrayList;

public class Transicao {
	private ArrayList<Estado> origens;
	private Simbolo simbolo;
	private ArrayList<Estado> destinos;
	
	public Transicao(ArrayList<Estado> origens, Simbolo simbolo, ArrayList<Estado> destinos) {
		this.origens = origens;
		this.simbolo = simbolo;
		this.destinos = destinos;
	}

	public ArrayList<Estado> getOrigens() {
		return origens;
	}
	public void setOrigens(ArrayList<Estado> origens) {
		this.origens = origens;
	}

	public Simbolo getSimbolo() {
		return simbolo;
	}
	public void setSimbolo(Simbolo simbolo) {
		this.simbolo = simbolo;
	}

	public ArrayList<Estado> getDestinos() {
		return destinos;
	}
	public void setDestinos(ArrayList<Estado> destinos) {
		this.destinos = destinos;
	}
}
