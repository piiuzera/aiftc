package ftc.models;

import java.util.ArrayList;

public class Afd {
	private ArrayList<Simbolo>   alfabeto;
	private Estado			     estadoInicial;
	private ArrayList<Estado>    estadoFinal;
	private ArrayList<Transicao> transicoes;
	
	public Afd() {
		setAlfabeto(new ArrayList<Simbolo>());
		setEstadoInicial(new Estado(""));
		setEstadoFinal(new ArrayList<Estado>());
		setTransicoes(new ArrayList<Transicao>());
	}

	public ArrayList<Simbolo> getAlfabeto() {
		return alfabeto;
	}
	public void setAlfabeto(ArrayList<Simbolo> alfabeto) {
		this.alfabeto = alfabeto;
	}

	public Estado getEstadoInicial() {
		return estadoInicial;
	}
	public void setEstadoInicial(Estado estadoInicial) {
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
