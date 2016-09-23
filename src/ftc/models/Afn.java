package ftc.models;

import java.util.ArrayList;

public class Afn {
	private ArrayList<Simbolo>   alfabeto;
	private ArrayList<Estado>    estadoInicial;
	private ArrayList<Estado>    estadoFinal;
	private ArrayList<Transicao> transicoes;
	
	public Afn() {
		setAlfabeto(new ArrayList<Simbolo>());
		setEstadoInicial(new ArrayList<Estado>());
		setEstadoFinal(new ArrayList<Estado>());
		setTransicoes(new ArrayList<Transicao>());
	}

	public ArrayList<Simbolo> getAlfabeto() {
		return alfabeto;
	}
	public void setAlfabeto(ArrayList<Simbolo> alfabeto) {
		this.alfabeto = alfabeto;
	}

	public ArrayList<Estado> getEstadoInicial() {
		return estadoInicial;
	}
	public void setEstadoInicial(ArrayList<Estado> estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public ArrayList<Estado> getEstadoFinal() {
		return estadoFinal;
	}
	public void setEstadoFinal(ArrayList<Estado> estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	public ArrayList<Transicao> getTransicoes() {
		return transicoes;
	}
	public void setTransicoes(ArrayList<Transicao> transicoes) {
		this.transicoes = transicoes;
	}
}
