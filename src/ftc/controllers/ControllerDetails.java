package ftc.controllers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import ftc.FtcException;
import ftc.FtcUtil;
import ftc.models.Afd;
import ftc.models.Afn;
import ftc.models.Estado;
import ftc.models.Simbolo;
import ftc.models.Transicao;
import ftc.views.FrameDetails;

public class ControllerDetails implements Runnable {
	/*
	 * View
	 */
	private FrameDetails frame;
	
	/*
	 * Autômatos Carregados na Exibição
	 */
	private Afn afn;
	private Afd afd;
	
	/*
	 * Métodos Úteis
	 */
	private FtcUtil util;
	
	/**
	 * Método que preenche os campos com as informações do AFN.
	 * @param afn Modelo de AFN
	 */
	private void tabAfn(Afn afn) {
		
		frame.getTextAfnAlfabeto().setText(afn.getAlfabeto().toString());
		frame.getTextAfnEstadoInicial().setText(afn.getEstadoInicial().toString());
		frame.getTextAfnEstadoFinal().setText(afn.getEstadoFinal().toString());
		
		/*
		 * Adiciona as Colunas com o Alfabeto
		 */
		((DefaultTableModel)frame.getTableAfnTransicoes().getModel()).setColumnCount(0);
		((DefaultTableModel)frame.getTableAfnTransicoes().getModel()).setNumRows(0);
		((DefaultTableModel)frame.getTableAfnTransicoes().getModel()).addColumn("Estado");
		for(Simbolo simbolo : afn.getAlfabeto()) {
			((DefaultTableModel)frame.getTableAfnTransicoes().getModel()).addColumn(simbolo.getValue());
		}
		
		/*
		 * Transforma as Linhas dos Estado em Colunas
		 */
		ArrayList<Estado> estado = null;
		ArrayList<String> list = new ArrayList<String>();
		for(Transicao transicao : afn.getTransicoes()) {
			if(estado != null && estado.toString().equals(transicao.getOrigens().toString())) {
				list.add(transicao.getDestinos().toString());
			} else {
				if(estado != null) {
					((DefaultTableModel)frame.getTableAfnTransicoes().getModel()).addRow(list.toArray());
					list.clear();
				}
				list.add(transicao.getOrigens().toString());
				list.add(transicao.getDestinos().toString());
				estado = transicao.getOrigens();
			}
		}
		((DefaultTableModel)frame.getTableAfnTransicoes().getModel()).addRow(list.toArray());
		list.clear();
	}
	
	/**
	 * Método que preenche os campos com as informações do AFD. 
	 * @param afd Modelo de AFD
	 */
	private void tabAfd(Afd afd) {
		frame.getTextAfdAlfabeto().setText(afd.getAlfabeto().toString());
		frame.getTextAfdEstadoInicial().setText(afd.getEstadoInicial().toString());
		frame.getTextAfdEstadoFinal().setText(afd.getEstadoFinal().toString());
		
		/*
		 * Adiciona as Colunas com o Alfabeto
		 */
		((DefaultTableModel)frame.getTableAfdTransicoes().getModel()).setColumnCount(0);
		((DefaultTableModel)frame.getTableAfdTransicoes().getModel()).setNumRows(0);
		((DefaultTableModel)frame.getTableAfdTransicoes().getModel()).addColumn("Estado");
		for(Simbolo simbolo : afd.getAlfabeto()) {
			((DefaultTableModel)frame.getTableAfdTransicoes().getModel()).addColumn(simbolo.getValue());
		}
		
		/*
		 * Transforma as Linhas dos Estado em Colunas
		 */
		ArrayList<Estado> estado = null;
		ArrayList<String> list = new ArrayList<String>();
		for(Transicao transicao : afd.getTransicoes()) {
			if(estado != null && estado.toString().equals(transicao.getOrigens().toString())) {
				list.add(transicao.getDestinos().toString());
			} else {
				if(estado != null) {
					((DefaultTableModel)frame.getTableAfdTransicoes().getModel()).addRow(list.toArray());
					list.clear();
				}
				list.add(transicao.getOrigens().toString());
				list.add(transicao.getDestinos().toString());
				estado = transicao.getOrigens();
			}
		}
		((DefaultTableModel)frame.getTableAfdTransicoes().getModel()).addRow(list.toArray());
		list.clear();
	}
	
	/**
	 * Classe de controle dos detalhamentos de AFN / AFD
	 */
	public ControllerDetails() {
		frame = new FrameDetails();
		
		util = new FtcUtil();
		
		/*
		 * Ação do Botão Arquivo -> Abrir
		 */
		frame.getMenuArquivoAbrir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				menuArquivoAbrir_click(event.getSource());
			}
		});
		
		/*
		 * Ação do Botão Arquivo -> Salvar -> Afn
		 */
		frame.getMenuArquivoSalvarAfn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				menuArquivoSalvarAfn_click(event.getSource());
			}
		});
		
		/*
		 * Ação do Botão Arquivo -> Salvar -> Afd
		 */
		frame.getMenuArquivoSalvarAfd().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				menuArquivoSalvarAfd_click(event.getSource());
			}
		});
		
		/*
		 * Ação do Botão Arquivo -> Sair
		 */
		frame.getMenuArquivoSair().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				menuArquivoSair_click(event.getSource());
			}
		});
		
		/*
		 * Ação do Botão Ajuda -> Sobre
		 */
		frame.getMenuAjudaSobre().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				menuAjudaSobre_click(event.getSource());
			}
		});
		
		/*
		 * Desativa o botão Arquivo -> Salvar
		 */
		frame.getMenuArquivoSalvar().setEnabled(false);
		
		/*
		 * Oculta o Tab
		 */
		frame.getTab().setVisible(false);
		
		/*
		 * Ativa o Frame.
		 */
		frame.setVisible(true);
		
		new Thread(this).start();
	}
	
	/**
	 * Método que é executado ao clicar no menu Arquivo -> Abrir
	 * @param sender Objeto que executou
	 */
	protected void menuArquivoAbrir_click(Object sender) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Selecione um Arquivo");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setFileFilter(new FileNameExtensionFilter("FTC or TXT Files", "ftc", "txt"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);
		if(fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			
			try {
				/*
				 * Preenche o AFN e o AFD
				 */
				afn = util.getAfn(fileChooser.getSelectedFile());
				afd = util.getAfd(afn);
				
				/*
				 * Oculta a Image Principal
				 */
				frame.getImageMain().setVisible(false);
				
				/*
				 * Exibe os Campos Ocultos
				 */
				frame.getMenuArquivoSalvar().setEnabled(true);
				frame.getTab().setVisible(true);
				
				/*
				 * Preenche o AFN e o AFD
				 */
				tabAfn(afn);
				tabAfd(afd);
				
			} catch(FtcException ex) {
				frame.getLabelMensagem().setText(ex.getFullMessage());
				frame.getLabelMensagem().setForeground(Color.RED);
				frame.getLabelMensagem().setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-193-circle-remove.png")));
			}
		}
	}
	
	/**
	 * Método que é executado ao clicar no menu Arquivo -> Salvar -> AFN
	 * @param sender Objeto que executou 
	 */
	protected void menuArquivoSalvarAfn_click(Object sender) {
		JFileChooser fileChooser = new JFileChooser() {
			private static final long serialVersionUID = 1080191503859530471L;
			public void approveSelection() {
		        if(getSelectedFile().exists() && getDialogType() == SAVE_DIALOG){
		            switch(JOptionPane.showConfirmDialog(null, getSelectedFile().getName() + " já existe.\nDeseja substituí-lo?", 
		            		"Confirmar Salvar Como", JOptionPane.YES_NO_OPTION)){
		                case JOptionPane.YES_OPTION:
		                    super.approveSelection();
		                    break;
		                case JOptionPane.NO_OPTION:
		                	break;
		                case JOptionPane.CLOSED_OPTION:
		                	break;
		            }
		        }
		        super.approveSelection();
			}
		};
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setDialogTitle("Selecione o Local para Salvar");
		fileChooser.setFileFilter(new FileNameExtensionFilter("FTC Files (*.ftc)", "ftc"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);
		
		if(fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			try {
				File file = fileChooser.getSelectedFile();
				if(!file.getName().endsWith(".ftc")) {
					file = new File(file.toString() + ".ftc");
				}
				util.saveAfn(afn, file);
				
				frame.getLabelMensagem().setText("Arquivo " + file.getName() + " Salvo com Sucesso!");
				frame.getLabelMensagem().setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-445-floppy-saved.png")));
				
			} catch (FtcException ex) {
				frame.getLabelMensagem().setText(ex.getFullMessage());
				frame.getLabelMensagem().setForeground(Color.RED);
				frame.getLabelMensagem().setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-193-circle-remove.png")));
			}
		}
	}
	
	/**
	 * Método que é executado ao clicar no menu Arquivo -> Salvar -> AFD
	 * @param sender Objeto que executou 
	 */	
	protected void menuArquivoSalvarAfd_click(Object sender) {
		JFileChooser fileChooser = new JFileChooser() {
			private static final long serialVersionUID = 5799429045614137446L;
			public void approveSelection() {
		        if(getSelectedFile().exists() && getDialogType() == SAVE_DIALOG){
		            switch(JOptionPane.showConfirmDialog(null, getSelectedFile().getName() + " já existe.\nDeseja substituí-lo?", 
		            		"Confirmar Salvar Como", JOptionPane.YES_NO_OPTION)){
		                case JOptionPane.YES_OPTION:
		                    super.approveSelection();
		                    break;
		                case JOptionPane.NO_OPTION:
		                	break;
		                case JOptionPane.CLOSED_OPTION:
		                	break;
		            }
		        }
		        super.approveSelection();
			}
		};
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setDialogTitle("Selecione o Local para Salvar");
		fileChooser.setFileFilter(new FileNameExtensionFilter("FTC Files (*.ftc)", "ftc"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.setMultiSelectionEnabled(false);
		
		if(fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
			try {
				
				File file = fileChooser.getSelectedFile();
				if(!file.getName().endsWith(".ftc")) {
					file = new File(file.toString() + ".ftc");
				}
				util.saveAfd(afd, file);
				
				frame.getLabelMensagem().setText("Arquivo " + file.getName() + " Salvo com Sucesso!");
				frame.getLabelMensagem().setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-445-floppy-saved.png")));
				
			} catch (FtcException ex) {
				frame.getLabelMensagem().setText(ex.getFullMessage());
				frame.getLabelMensagem().setForeground(Color.RED);
				frame.getLabelMensagem().setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-193-circle-remove.png")));
			}
		}
	}
	
	/**
	 * Método que é executado ao clicar no menu Sair
	 * @param sender Objeto que executou 
	 */	
	protected void menuArquivoSair_click(Object sender) {
		System.exit(0);
	}
	
	/**
	 * Método que é executado ao clicar no menu Ajuda -> Sobre
	 * @param sender Objeto que executou 
	 */
	protected void menuAjudaSobre_click(Object sender) {
		new ControllerAbout();
	}

	/**
	 * Execução da Thread 
	 */
	@Override
	public void run() {
		while(true) {
			try {
				if(!frame.getLabelMensagem().getText().equals("")) {
					Thread.sleep(5000);
					frame.getLabelMensagem().setText("");
					frame.getLabelMensagem().setForeground(Color.BLACK);
					frame.getLabelMensagem().setIcon(new ImageIcon());
				}
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				frame.getLabelMensagem().setText(ex.getMessage());
				frame.getLabelMensagem().setForeground(Color.RED);
				frame.getLabelMensagem().setIcon(new ImageIcon(this.getClass().getResource("/glyphicons-193-circle-remove.png")));
			}
		}
	}
}
