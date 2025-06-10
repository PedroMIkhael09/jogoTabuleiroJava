package src.main.visao;

import src.main.jogo.Tabuleiro;
import src.main.jogador.JogadorAzarado;
import src.main.jogador.JogadorNormal;
import src.main.jogador.JogadorSortudo;
import java.util.Scanner;

public class TabuleiroConsole {
	private final Tabuleiro tabuleiro;
	private final Scanner teclado;
	
	public TabuleiroConsole() {
		this.tabuleiro = new Tabuleiro();
		this.teclado = new Scanner(System.in);
	}
	
	public void jogar() {
		System.out.println("Seja bem-vindo ao jogo de Tabuleiro!!");
		
		int modo = selecionarModo();
		if (modo == 1 || modo == 2) {
			boolean configuracaoValida = iniciarJogo(modo);
			if (configuracaoValida) {
				if (modo == 1) {
					executarModoNormal();
				} else {
					executarModoDebug();
				}
			}
		}
	}
	
	private int selecionarModo() {
		System.out.println("Escolha o modo de jogo:");
		System.out.println("1 - Modo Normal");
		System.out.println("2 - Modo Debug");
		
		while (!teclado.hasNextInt()) {
			System.out.println("Entrada inválida. Digite 1 ou 2.");
			teclado.next();
		}
		
		int modo = teclado.nextInt();
		if (modo != 1 && modo != 2) {
			System.out.println("Número inválido. Tente novamente.");
			return -1;
		}
		return modo;
	}
	
	private boolean iniciarJogo(int modo) {
		System.out.println(modo == 1 ? "Modo Normal selecionado" : "Modo Debug selecionado");
		System.out.println("Quantas pessoas irão jogar? Mínimo de 2 e máximo de 6 pessoas.");
		
		int qtdJogadores;
		while (true) {
			
			qtdJogadores = teclado.nextInt();
			if (qtdJogadores >= 2 && qtdJogadores <= 6) {
					break;
			} else {
					System.out.println("Número inválido de jogadores. Digite um número entre 2 e 6.");
				System.out.println("Por favor, digite um número válido.");
			}
		}
		
		return configurarJogadores(qtdJogadores);
	}
	
	private boolean configurarJogadores(int qtdJogadores) {
		int contadorNormais = 0;
		int contadorAzarados = 0;
		int contadorSortudos = 0;
		
		for (int i = 0; i < qtdJogadores; i++) {
			String cor = selecionarCor(i);
			int tipoJogador = selecionarTipoJogador();
			
			switch (tipoJogador) {
				case 1:
					tabuleiro.adicionarJogadores(new JogadorAzarado(cor));
					contadorAzarados++;
					break;
				case 2:
					tabuleiro.adicionarJogadores(new JogadorSortudo(cor));
					contadorSortudos++;
					break;
				case 3:
					tabuleiro.adicionarJogadores(new JogadorNormal(cor));
					contadorNormais++;
					break;
				
				default:
					break;
			}
		}
		
		return verificarTiposJogadores(contadorAzarados, contadorSortudos, contadorNormais);
	}
	
	private String selecionarCor(int indiceJogador) {
		String cor;
		do {
			System.out.println("Digite a cor do jogador " + (indiceJogador + 1) + ":");
			cor = teclado.next().toLowerCase();
			if (tabuleiro.verificarCores(cor)) {
				System.out.println("Essa cor já foi escolhida. Escolha uma cor diferente.");
			}
		} while (tabuleiro.verificarCores(cor));
		return cor;
	}
	
	private int selecionarTipoJogador() {
		int tipoJogador;
		do {
			System.out.println("Qual o tipo de jogador:");
			System.out.println("1 - Azarado");
			System.out.println("2 - Sortudo");
			System.out.println("3 - Normal");
			
			while (!teclado.hasNextInt()) {
				System.out.println("Entrada inválida. Digite um número.");
				teclado.next();
			}
			
			tipoJogador = teclado.nextInt();
			if (tipoJogador < 1 || tipoJogador > 3) {
				System.out.println("Tipo inválido, tente novamente.");
			}
		} while (tipoJogador < 1 || tipoJogador > 3);
		return tipoJogador;
	}
	
	private boolean verificarTiposJogadores(int azarados, int sortudos, int normais) {
		int tiposDiferentes = 0;
		if (azarados > 0) tiposDiferentes++;
		if (sortudos > 0) tiposDiferentes++;
		if (normais > 0) tiposDiferentes++;
		
		if (tiposDiferentes < 2) {
			System.out.println("Erro: O jogo precisa de pelo menos dois tipos diferentes de jogadores.");
			tabuleiro.limparJogadores();
			return false;
		}
		return true;
	}
	
	private void executarModoNormal() {
		boolean jogoFinalizado = false;
		while (!jogoFinalizado) {
			jogoFinalizado = tabuleiro.jogarRodada();
		}
	}
	
	private void executarModoDebug() {
		boolean jogoFinalizado = false;
		while (!jogoFinalizado) {
			jogoFinalizado = tabuleiro.jogarRodadaDebug(teclado);
		}
	}
}