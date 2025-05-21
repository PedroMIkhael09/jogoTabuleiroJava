
import src.main.jogo.Tabuleiro;
import src.main.jogador.JogadorAzarado;
import src.main.jogador.JogadorNormal;
import src.main.jogador.JogadorSortudo;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		Tabuleiro tabuleiro = new Tabuleiro();
		
		System.out.println("Seja bem-vindo ao jogo de Tabuleiro!!");
		System.out.println("Escolha o modo de jogo:");
		System.out.println("1 - Modo Normal");
		System.out.println("2 - Modo Debug");
		int modo = teclado.nextInt();
		
		switch (modo) {
			case 1:
				System.out.println("Modo Normal selecionado");
				System.out.println("Quantas pessoas irão jogar? Mínimo de 2 e máximo de 6 pessoas.");
				int qtdPessoasJogando = teclado.nextInt();
				
				if (qtdPessoasJogando < 2 || qtdPessoasJogando > 6) {
					System.out.println("Número inválido de jogadores. Tente novamente.");
					break;
				}
				
				int contadorTiposJogadoresNormal = 0;
				int contadorTiposJogadoresAzarado = 0;
				int contadorTiposJogadoresSortudo = 0;
				
				for (int i = 0; i < qtdPessoasJogando; i++) {
					String cor;
					do {
						System.out.println("Digite a cor do jogador " + (i + 1) + ":");
						cor = teclado.next();
						if (tabuleiro.verificarCores(cor)) {
							System.out.println("Essa cor já foi escolhida. Escolha uma cor diferente.");
						}
					} while (tabuleiro.verificarCores(cor));

					boolean tipoValido = false;
					while (!tipoValido) {
						System.out.println("Qual o tipo de jogador:");
						System.out.println("1 - Azarado");
						System.out.println("2 - Sortudo");
						System.out.println("3 - Normal");

						if (!teclado.hasNextInt()) {
							System.out.println("Entrada inválida. Digite um número.");
							teclado.next();
							continue;
						}

						int tipoJogador = teclado.nextInt();
						switch (tipoJogador) {
							case 1:
								tabuleiro.adicionarJogadores(new JogadorAzarado(cor));
								contadorTiposJogadoresAzarado++;
								tipoValido = true;
								break;
							case 2:
								tabuleiro.adicionarJogadores(new JogadorSortudo(cor));
								contadorTiposJogadoresSortudo++;
								tipoValido = true;
								break;
							case 3:
								tabuleiro.adicionarJogadores(new JogadorNormal(cor));
								contadorTiposJogadoresNormal++;
								tipoValido = true;
								break;
							default:
								System.out.println("Tipo inválido, tente novamente.");
						}
					}

				}
				
				int tiposDiferentes = 0;
				if (contadorTiposJogadoresAzarado > 0) tiposDiferentes++;
				if (contadorTiposJogadoresSortudo > 0) tiposDiferentes++;
				if (contadorTiposJogadoresNormal > 0) tiposDiferentes++;
				
				if (tiposDiferentes < 2) {
					System.out.println("Erro: O jogo precisa de pelo menos dois tipos diferentes de jogadores.");
					tabuleiro.limparJogadores();
					break;
				}
				
				
				boolean jogoFinalizado = false;
				while (!jogoFinalizado) {
					jogoFinalizado = tabuleiro.jogarRodada();
				}
				
				
				break;

			case 2:
				System.out.println("Modo Debug selecionado");
				System.out.println("Quantas pessoas irão jogar? Mínimo de 2 e máximo de 6 pessoas.");
				int qtdPessoasDebug = teclado.nextInt();

				if (qtdPessoasDebug < 2 || qtdPessoasDebug > 6) {
					System.out.println("Número inválido de jogadores. Tente novamente.");
					break;
				}

				for (int i = 0; i < qtdPessoasDebug; i++) {
					String cor;
					do {
						System.out.println("Digite a cor do jogador " + (i + 1) + ":");
						cor = teclado.next();
						if (tabuleiro.verificarCores(cor)) {
							System.out.println("Essa cor já foi escolhida. Escolha uma cor diferente.");
						}
					} while (tabuleiro.verificarCores(cor));

					System.out.println("Qual o tipo de jogador:");
					System.out.println("1 - Azarado");
					System.out.println("2 - Sortudo");
					System.out.println("3 - Normal");

					int tipoJogador = teclado.nextInt();

					switch (tipoJogador) {
						case 1:
							tabuleiro.adicionarJogadores(new JogadorAzarado(cor));
							break;
						case 2:
							tabuleiro.adicionarJogadores(new JogadorSortudo(cor));
							break;
						case 3:
							tabuleiro.adicionarJogadores(new JogadorNormal(cor));
							break;
						default:
							System.out.println("Tipo inválido, tente novamente.");
							i--;
							break;
					}
				}

				boolean jogoFinalizadoDebug = false;
				while (!jogoFinalizadoDebug) {
					jogoFinalizadoDebug = tabuleiro.jogarRodadaDebug(teclado);
				}
				break;
			default:
				System.out.println("Número inválido. Tente novamente.");
		}
	}
}
