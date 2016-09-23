package ftc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import ftc.models.Afd;
import ftc.models.Afn;
import ftc.models.Estado;
import ftc.models.Simbolo;
import ftc.models.Transicao;

public class FtcUtil {
	private BufferedReader buffReader;
	
	/**
	 * Método que gera um AFN
	 * @param file Arquivo Selecionado
	 * @return Modelo de AFN
	 * @throws FtcException
	 */
	public Afn getAfn(File file) throws FtcException {
		try {
			if(file != null && file.isFile()) {
				Afn afn = new Afn();
				buffReader = new BufferedReader(new FileReader(file));
				for(String linha = ""; linha != null; linha = buffReader.readLine()) {
					if(!linha.isEmpty()) {
						/*
						 * Insere o Alfabeto
						 */
						if(linha.toUpperCase().contains("AB:")) {
							linha = getStringNotSpace(linha);
							char [] vetChar = linha.substring(linha.indexOf(":") + 1, linha.length()).toCharArray();
							for(char objChar : vetChar) {
								afn.getAlfabeto().add(new Simbolo(String.valueOf(objChar)));
							}
						}
						/*
						 * Insere os Estados Iniciais
						 */
						else if(linha.toUpperCase().contains("I:")) {
							linha = getStringNotSpace(linha);
							char [] vetChar = linha.substring(linha.indexOf(":") + 1, linha.length()).toCharArray();
							for(char objChar : vetChar) {
								afn.getEstadoInicial().add(new Estado(String.valueOf(objChar)));
							}
						}
						/*
						 * Insere os Estados Finais
						 */
						else if(linha.toUpperCase().contains("F:")) {
							linha = getStringNotSpace(linha);
							char [] vetChar = linha.substring(linha.indexOf(":") + 1, linha.length()).toCharArray();
							for(char objChar : vetChar) {
								afn.getEstadoFinal().add(new Estado(String.valueOf(objChar)));
							}
						}
						/*
						 * Insere as transições dos estados
						 */
						else {
							linha = getStringNotSpace(linha);
							char [] vetChar = linha.toCharArray();
							
							ArrayList<Estado> origens = new ArrayList<Estado>();
							Simbolo simbolo = new Simbolo("");
							ArrayList<Estado> destinos = new ArrayList<Estado>();
							
							for(int i = 0; i < vetChar.length; ++i) {
								/*
								 * Pega o Estado de Origem
								 */
								if(i == 0) {
									origens.add(new Estado(String.valueOf(vetChar[i])));
								}
								/*
								 * Pega o Simbolo do Alfabeto
								 */
								else if(i == 1) {
									simbolo.setValue(String.valueOf(vetChar[i]));
								}
								/*
								 * Pega o Estado de Destino
								 */
								else {
									destinos.add(new Estado(String.valueOf(vetChar[i])));
								}
							}
							afn.getTransicoes().add(new Transicao(origens, simbolo, destinos));
						}
					}
				}
				if(afn.getAlfabeto().size() == 0 ||
				   afn.getEstadoFinal().size() == 0 ||
				   afn.getEstadoInicial().size() == 0 ||
				   afn.getTransicoes().size() == 0) {
					throw new FtcException("Arquivo Informado está num formato Inválido!", "FTC8x100");
				}
				return afn;
			} else {
				throw new FtcException("Arquivo Informado está num formato Inválido!", "FTC8x101");
			}
		} catch(IOException ex) {
			throw new FtcException("Arquivo Informado é Inválido!", "FTC8x102");
		}
	}
	
	/**
	 * Método que Retorna o AFD
	 * @param afn Modelo AFN
	 * @return Modelo AFD
	 * @throws FtcException
	 */
	public Afd getAfd(Afn afn) throws FtcException {
		Afd afd = new Afd();
		for(Simbolo simbolo : afn.getAlfabeto()) {
			afd.getAlfabeto().add(simbolo);
		}
		
		/*
		 * Insere o Estado Inicial
		 */
		for(Estado estadoInicial : afn.getEstadoInicial()) {
			if(afd.getEstadoInicial().getValue().isEmpty()) {
				afd.getEstadoInicial().setValue(estadoInicial.getValue());
			} else {
				afd.getEstadoInicial().setValue(afn.getEstadoInicial().toString());
			}
		}
		
		/*
		 * Insere o Estado Inicial como Primeira Origem do AFD
		 */
		for(Estado estadoInicial : afn.getEstadoInicial()) {
			for(Transicao transicaoAfn : afn.getTransicoes()) {
				for(Estado estadoOrigem : transicaoAfn.getOrigens()) {
					ArrayList<Estado> origemEstadoInicial = new ArrayList<Estado>();
					ArrayList<Estado> destinoEstadoInicial = new ArrayList<Estado>();
					if(estadoOrigem.getValue().equals(estadoInicial.getValue())) {
						for(Estado estado : afn.getEstadoInicial()) {
							origemEstadoInicial.add(new Estado(estado.getValue()));
						}
						for(Estado estado : transicaoAfn.getDestinos()) {
							destinoEstadoInicial.add(new Estado(estado.getValue()));
						}
						afd.getTransicoes().add(new Transicao(origemEstadoInicial,
															  new Simbolo(transicaoAfn.getSimbolo().getValue()),
															  destinoEstadoInicial));
					}
				}
			}
		}
		
		/*
		 * Percorre Todas as Transições Para Verificar os Destinos que se tornam Origens
		 */
		for(int i=0; i< afd.getTransicoes().size(); ++i) {
			boolean isAdd = true;
			
			Transicao transicaoAfd = afd.getTransicoes().get(i);
			ArrayList<Estado> origens = new ArrayList<Estado>();
			
			/*
			 * Verifica se Existe uma Transição de Destino que se tornará Origem
			 */
			for(Transicao objOrigens : afd.getTransicoes()) {
				if( objOrigens.getOrigens().toString().equals(transicaoAfd.getDestinos().toString()) ) {
					isAdd = false;
				}
			}
			
			if(isAdd) {
				for(Estado destino : transicaoAfd.getDestinos()) {
					origens.add(new Estado(destino.getValue()));
				}
				
				if(!origens.isEmpty()) {
					/*
					 * Ao Inserir Uma Nova Origem, Verifica as Transições de Cada Origem.
					 */
					for(Simbolo simbolo : afd.getAlfabeto()) {
						ArrayList<Estado> destinos = new ArrayList<Estado>();
						Simbolo objSimbolo = new Simbolo();
						for(Estado origemAfd : origens) {
							for(Transicao transicaoAfn : afn.getTransicoes()) {
								for(Estado estadoOrigem : transicaoAfn.getOrigens()) {
									if(estadoOrigem.getValue().equals(origemAfd.getValue()) && 
											simbolo.getValue().equals(transicaoAfn.getSimbolo().getValue())) {
										for(Estado destinoAfn : transicaoAfn.getDestinos()) {
											destinos.add(new Estado(destinoAfn.getValue()));
											objSimbolo.setValue(simbolo.getValue());
										}
									}
								}
							}
						}
						afd.getTransicoes().add(new Transicao(origens,
														      objSimbolo,
														      destinos));
					}
				}
			}
			
		}
		
		/*
		 * Insere o Estado Final
		 */
		for(Transicao transicao : afd.getTransicoes()) {
			for(Estado estadoFinal : afn.getEstadoFinal()) {
				for(Estado estado : transicao.getOrigens()) {
					boolean exists = false;
					for(Estado objEstado : afd.getEstadoFinal()) {
						if(objEstado.getValue().equals(estado.getValue())) {
							exists = true;
						}
					}
					if(estado.getValue().equals(estadoFinal.getValue()) &&
							!exists) {
						afd.getEstadoFinal().add(new Estado(estado.getValue()));
					}
				}
			}
		}
		
		/*
		 * Manipula os Estados e os Ordena
		 */
		char [] seq = afd.getEstadoInicial().getValue().toCharArray();
		seq[seq.length - 1] = (char)(((int)seq[seq.length - 1]) - 1);
		ArrayList<Estado> origens = new ArrayList<Estado>();
		for(Transicao transicao : afd.getTransicoes()) {

			if(!origens.toString().equals(transicao.getOrigens().toString())) {
				seq[seq.length - 1] = (char)(((int)seq[seq.length - 1]) + 1);	
			}
			
			for(Transicao transicaoDestino : afd.getTransicoes()) {
				if(transicao.getOrigens().toString().equals(transicaoDestino.getDestinos().toString())) {
					transicaoDestino.getDestinos().clear();
					transicaoDestino.getDestinos().add(new Estado(String.valueOf(seq)));
				}
			}
			transicao.getOrigens().clear();
			transicao.getOrigens().add(new Estado(String.valueOf(seq)));
			
			origens = transicao.getOrigens();
		}
		
		if( afd.getAlfabeto().size() == 0 ||
			afd.getEstadoInicial().getValue().equals("") ||
			afd.getEstadoFinal().size() == 0 ||
			afd.getTransicoes().size() == 0 ) {
			throw new FtcException("AFN Informado não é válido!", "FTC8x103");
		}
		return afd;
	}
	
	/**
	 * Salva o AFN em formato ftc
	 * @param afn Modelo AFN
	 * @param file Local para Salvar
	 * @throws FtcException
	 */
	public void saveAfn(Afn afn, File file) throws FtcException {
		try {
			StringBuilder str = new StringBuilder();
			
			/*
			 * Escreve o Alfabeto
			 */
			str.append("AB: ");
			for(Simbolo simbolo : afn.getAlfabeto()) {
				str.append(simbolo.getValue() + " ");
			}
			str.append("\n");
			
			/*
			 * Escreve os Estados Iniciais
			 */
			str.append("i: ");
			for(Estado estado : afn.getEstadoInicial()) {
				str.append(estado.getValue() + " ");
			}
			str.append("\n");
			
			/*
			 * Escreve os Estados Finais
			 */
			str.append("f: ");
			for(Estado estado : afn.getEstadoFinal()) {
				str.append(estado.getValue() + " ");
			}
			str.append("\n");
			
			/*
			 * Escreve as Transições
			 */
			for(Transicao transicao : afn.getTransicoes()) {
				for(Estado origem : transicao.getOrigens()) {
					str.append(origem.getValue() + " ");
				}
				
				str.append(transicao.getSimbolo().getValue() + " ");
				
				for(Estado destino : transicao.getDestinos()) {
					str.append(destino.getValue() + " ");
				}
				
				str.append("\n");
			}
			
			/*
			 * Salva o Arquivo no Local Informado
			 */
			PrintWriter pw = new PrintWriter(new FileWriter(file));
			pw.println(str.toString());
			pw.close();
		} catch (IOException ex) {
			throw new FtcException("Local Informado para Salvar é Inválido!",
					"FTC8x104", ex.getMessage());
		}
	}
	
	/**
	 * Método que salva o AFD
	 * @param afd Modelo AFD
	 * @param file Local ded Salvar
	 * @throws FtcException
	 */
	public void saveAfd(Afd afd, File file) throws FtcException {
		try {
			StringBuilder str = new StringBuilder();
			
			/*
			 * Escreve o Alfabeto
			 */
			str.append("AB: ");
			for(Simbolo simbolo : afd.getAlfabeto()) {
				str.append(simbolo.getValue() + " ");
			}
			str.append("\n");
			
			/*
			 * Escreve os Estados Iniciais
			 */
			str.append("i: ");
			str.append(afd.getEstadoInicial().getValue());
			str.append("\n");
			
			/*
			 * Escreve os Estados Finais
			 */
			str.append("f: ");
			for(Estado estado : afd.getEstadoFinal()) {
				str.append(estado.getValue() + " ");
			}
			str.append("\n");
			
			/*
			 * Escreve as Transições
			 */
			for(Transicao transicao : afd.getTransicoes()) {
				for(Estado origem : transicao.getOrigens()) {
					str.append(origem.getValue() + " ");
				}
				
				str.append(transicao.getSimbolo().getValue() + " ");
				
				for(Estado destino : transicao.getDestinos()) {
					str.append(destino.getValue() + " ");
				}
				
				str.append("\n");
			}
			
			/*
			 * Salva o Arquivo no Local Informado
			 */
			PrintWriter pw = new PrintWriter(new FileWriter(file));
			pw.println(str.toString());
			pw.close();
		} catch (IOException ex) {
			throw new FtcException("Local Informado para Salvar é Inválido!",
					"FTC8x105", ex.getMessage());
		}
	}
	
	/**
	 * Elimina os Espaços de uma String
	 * @param value Texto
	 * @return Texto sem Espaço
	 */
	public String getStringNotSpace(String value) {
		String result = "";
		char [] vetChar = value.toCharArray();
		for(char objChar : vetChar) {
			String str = String.valueOf(objChar);
			if(!str.trim().isEmpty()) {
				result += str;
			}
		}
		return result;
	}
}
