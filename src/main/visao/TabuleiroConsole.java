package src.main.visao;

import src.main.jogo.Tabuleiro;
import src.main.mensagem.EfeitoDaCasa;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TabuleiroConsole {
	
	private final Tabuleiro tabuleiro;
	private final Scanner teclado;
	
	public TabuleiroConsole() {
		this.tabuleiro = Tabuleiro.getInstacia();
		this.teclado = new Scanner(System.in);
	}
	
	public void jogar() {
		exibirMensagem("Seja bem-vindo ao jogo de Tabuleiro!!");
		
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
		exibirMensagem("Escolha o modo de jogo:");
		exibirMensagem("1 - Modo Normal");
		exibirMensagem("2 - Modo Debug");
		
		while (true) {
			int entrada = lerInt("Digite 1 ou 2: ");
			if (entrada == 1 || entrada == 2) {
				return entrada;
			}
			exibirMensagem("Número inválido. Tente novamente.");
		}
	}
	
	public boolean montarTabuleiro(int qtdCasas) {
		exibirMensagem("Vamos montar o tabuleiro com " + qtdCasas + " casas!");
		imprimirDescricaoDasCasas();
		
		for (int i = 0; i < qtdCasas; i++) {
			int escolhaTabuleiro = -1;
			while (true) {
				escolhaTabuleiro = lerInt("\nDeseja colocar algo específico na casa " + (i + 1) +
						"? Digite o número correspondente (0-7): ");
				if (escolhaTabuleiro >= 0 && escolhaTabuleiro <= 7) {
					break;
				}
				exibirMensagem("Número inválido! Digite um valor entre 0 e 7.");
			}
			tabuleiro.adicionarCasa(escolhaTabuleiro, i + 1);
			exibirMensagem("Casa " + (i + 1) + " adicionada ao tabuleiro!");
		}
		return true;
	}
	
	private void imprimirDescricaoDasCasas() {
		exibirMensagem("0. Casa Simples: Não aplica nenhuma regra especial");
		exibirMensagem("1. Casa Prisão: Jogador perde a próxima rodada");
		exibirMensagem("2. Casa Surpresa: Jogador tira carta aleatória que muda seu tipo conforme a carta");
		exibirMensagem("3. Casa Sorte: Avança 3 casas, exceto se for jogador Azarado");
		exibirMensagem("4. Casa Reversa: Troca posição com o último jogador");
		exibirMensagem("5. Casa Retroceder: Envia um adversário para a casa 0");
		exibirMensagem("6. Casa JogaDeNovo: Rola os dados novamente");
		exibirMensagem("7. Casa Azar: Volta 3 casas, exceto se for Jogador Sortudo");
	}
	
	private boolean iniciarJogo(int modo) {
		exibirMensagem(modo == 1 ? "Modo Normal selecionado" : "Modo Debug selecionado");
		exibirMensagem("Quantas pessoas irão jogar? Mínimo de 2 e máximo de 6 pessoas.");
		
		int qtdJogadores;
		while (true) {
			qtdJogadores = lerInt("");
			if (qtdJogadores >= 2 && qtdJogadores <= 6) {
				break;
			}
			exibirMensagem("Número inválido de jogadores. Digite um número entre 2 e 6.");
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
			
			tabuleiro.adicionarJogador(tipoJogador, cor);
			
			switch (tipoJogador) {
				case 1 -> contadorAzarados++;
				case 2 -> contadorSortudos++;
				case 3 -> contadorNormais++;
			}
		}
		
		return verificarTiposJogadores(contadorAzarados, contadorSortudos, contadorNormais);
	}
	
	private String selecionarCor(int indiceJogador) {
		String cor;
		do {
			cor = lerString("Digite a cor do jogador " + (indiceJogador + 1) + ": ").toLowerCase();
			if (tabuleiro.verificarCores(cor)) {
				exibirMensagem("Essa cor já foi escolhida. Escolha uma cor diferente.");
				cor = null;
			}
		} while (cor == null || cor.isBlank());
		return cor;
	}
	
	private int selecionarTipoJogador() {
		exibirMensagem("Qual o tipo de jogador:");
		exibirMensagem("1 - Azarado");
		exibirMensagem("2 - Sortudo");
		exibirMensagem("3 - Normal");
		
		while (true) {
			int tipoJogador = lerInt("");
			if (tipoJogador >= 1 && tipoJogador <= 3) {
				return tipoJogador;
			}
			exibirMensagem("Tipo inválido, tente novamente.");
		}
	}
	
	private boolean verificarTiposJogadores(int azarados, int sortudos, int normais) {
		int tiposDiferentes = 0;
		if (azarados > 0) tiposDiferentes++;
		if (sortudos > 0) tiposDiferentes++;
		if (normais > 0) tiposDiferentes++;
		
		if (tiposDiferentes < 2) {
			exibirMensagem("Erro: O jogo precisa de pelo menos dois tipos diferentes de jogadores.");
			tabuleiro.limparJogadores();
			return false;
		}
		return true;
	}
	
	public static void exibirMensagem(EfeitoDaCasa efeito) {
		if (efeito != null && efeito.getMensagem() != null && !efeito.getMensagem().isEmpty()) {
			System.out.println(efeito.getMensagem());
		}
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
	
	// Métodos utilitários para leitura de dados e mensagens
	
	private int lerInt(String mensagem) {
		while (true) {
			if (!mensagem.isEmpty()) {
				System.out.print(mensagem);
			}
			try {
				int valor = teclado.nextInt();
				teclado.nextLine(); // consumir quebra de linha
				return valor;
			} catch (InputMismatchException e) {
				exibirMensagem("Entrada inválida! Digite um número inteiro.");
				teclado.nextLine(); // limpar buffer
			}
		}
	}
	
	private String lerString(String mensagem) {
		System.out.print(mensagem);
		return teclado.nextLine();
	}
	
	private void exibirMensagem(String mensagem) {
		System.out.println(mensagem);
	}
}
