package src.main.jogo;
import src.main.jogador.Jogadores;
import src.main.jogador.JogadorAzarado;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;


public class Tabuleiro {
	protected ArrayList<Jogadores> jogadores;
	private int numeroRodada = 1;
	private final int[] perdeRodada = {10, 25, 38};
	private final int[] mudaTipoJogador = {13};
	private final int[] ganhaTresPosicoes = {5, 15, 30};
	private final int[] casasMagicas = {20, 35};
	private final int[] retrocederOutro = {17, 27};
	private static Tabuleiro instancia;


	private Tabuleiro() {
		this.jogadores = new ArrayList<>();
	}
	
	//PADRAO SINGLETON
	public static Tabuleiro getInstacia() {
		if (instancia == null) {
			instancia = new Tabuleiro();
		}
		return instancia;
	}

	public void adicionarJogadores(Jogadores j) {
		jogadores.add(j);
	}

	public void limparJogadores() {
		jogadores.clear();
	}

	public boolean verificarCores(String cor) {
		for (Jogadores j : jogadores) {
			if (j.getCor().equalsIgnoreCase(cor)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean jogarRodada() {
		System.out.println("\n========= RODADA " + numeroRodada + " ========="); // NOSONAR
		for (Jogadores jogador : jogadores) {
			imprimirSeparadorRodada();
			
			if (verificarSeJogadorPerdeuRodada(jogador)) {
				continue;
			}
			
			if (processarJogadaCompleta(jogador)) {
				return true; // Houve um ganhador
			}
			
			pausarEntreJogadas();
		}
		
		incrementarNumeroRodada();
		return false;
	}
	
	public void imprimirSeparadorRodada() {
		System.out.println("<------------------------------------------------->"); // NOSONAR
	}
	
	public boolean verificarSeJogadorPerdeuRodada(Jogadores jogador) {
		if (jogador.getPerdeProximaJogada()) {
			System.out.println("Jogador " + jogador.getCor() + " perdeu esta rodada!"); // NOSONAR
			jogador.setPerdeProximaJogada(false);
			return true;
		}
		return false;
	}
	
	public boolean processarJogadaCompleta(Jogadores jogador) {
		if (processarMovimentoJogador(jogador)) {
			return true;
		}
		
		return processarRodadasExtrasPorDadosIguais(jogador);
	}
	
	public boolean processarMovimentoJogador(Jogadores jogador) {
		int posicaoAntes = jogador.getPosicaoTabuleiro();
		jogador.atualizarJogador();
		int posicaoDepois = jogador.getPosicaoTabuleiro();
		
		imprimirResultadoJogada(jogador, posicaoAntes, posicaoDepois);
		
		verificarCasaEspecial(jogador);
		return verificarGanhador();
	}
	
	public void imprimirResultadoJogada(Jogadores jogador, int posicaoAntes,
								   int posicaoDepois) {
		System.out.println("Jogador " + jogador.getCor() +
				" está na rodada " + jogador.getJogadas() +
				", avançou " + (posicaoDepois - posicaoAntes) +
				" casas e está agora na posição " + posicaoDepois + "."); // NOSONAR
	}
	
	public boolean processarRodadasExtrasPorDadosIguais(Jogadores jogador) {
		while (jogador.verificarDadosIguais()) {
			System.out.println("🎲 Jogador " + jogador.getCor() + " tirou dados iguais! Joga novamente."); // NOSONAR
			
			if (processarMovimentoJogador(jogador)) {
				return true;
			}
		}
		return false;
	}
	
	public void pausarEntreJogadas() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			System.out.println("Jogo interrompido!"); // NOSONAR
			Thread.currentThread().interrupt();
		}
	}
	
	public void incrementarNumeroRodada() {
		numeroRodada++;
	}

	public boolean verificarGanhador() {
		for (int i = 0; i < jogadores.size(); i++) {
			Jogadores jogador = jogadores.get(i);
			if (jogador.getPosicaoTabuleiro() >= 40) {
				System.out.println("O jogador " + jogador.getCor() + " ganhou a partida em " + jogador.getJogadas() + " rodadas!"); // NOSONAR

				System.out.println("\n--- RESUMO FINAL DOS JOGADORES ---"); // NOSONAR
				for (int j = 0; j < jogadores.size(); j++) {
					System.out.println("Jogador " + jogadores.get(j).getCor() + " - " +
							"Posição : " + jogadores.get(j).getPosicaoTabuleiro() + " -" +
							" " +
							"Rodadas jogadas: " + jogadores.get(j).getJogadas()); // NOSONAR
				}

				return true;
			}
		}
		return false;
	}

	private boolean contem(int valor, int[] array) {
		for (int i : array) {
			if (i == valor) return true;
		}
		return false;
	}
	
	public void verificarCasaEspecial(Jogadores jogador) {
		int posicao = jogador.getPosicaoTabuleiro();
		
		verificarPerdaRodada(jogador, posicao);
		verificarMudancaTipo(jogador, posicao);
		verificarGanhoPosicoes(jogador, posicao);
		verificarCasaMagica(jogador, posicao);
		verificarRetrocederAdversario(jogador, posicao);
	}
	
	private void verificarPerdaRodada(Jogadores jogador, int posicao) {
		if (contem(posicao, perdeRodada)) {
			System.out.println("Jogador " + jogador.getCor() + " caiu na casa " + posicao +
					", por isso, não joga a próxima rodada"); // NOSONAR
			jogador.setPerdeProximaJogada(true);
		}
	}
	
	private void verificarMudancaTipo(Jogadores jogador, int posicao) {
		if (contem(posicao, mudaTipoJogador)) {
			Scanner teclado = new Scanner(System.in);
			System.out.println("Jogador " + jogador.getCor() +
					", você terá que mudar de tipo, pois caiu na casa " + posicao + "."); // NOSONAR
			System.out.println("Escolha a carta surpresa (1, 2, 3):"); // NOSONAR
			int numero = teclado.nextInt();
			
			Jogadores novoJogador = jogador.mudarTipoJogadorPara(numero);
			int index = jogadores.indexOf(jogador);
			jogadores.set(index, novoJogador);
			
			System.out.println("Jogador " + novoJogador.getCor() +
					" mudou para o tipo " + novoJogador.getClass().getSimpleName()); // NOSONAR
		}
	}
	
	private void verificarGanhoPosicoes(Jogadores jogador, int posicao) {
		if (contem(posicao, ganhaTresPosicoes)) {
			if (!(jogador instanceof JogadorAzarado)) {
				System.out.println("Jogador " + jogador.getCor() +
						" ganhou 3 posições, pois caiu na casa " + posicao + "."); // NOSONAR
				jogador.setPosicaoTabuleiro(jogador.getPosicaoTabuleiro() + 3);
			} else {
				System.out.println("Jogador " + jogador.getCor() +
						" deveria ganhar 3 pontos extras por estar na casa " + posicao +
						", mas o tipo de jogador é Azarado e por isso não ganha as 3 posições extras"); // NOSONAR
			}
		}
	}
	
	private void verificarCasaMagica(Jogadores jogador, int posicao) {
		if (contem(posicao, casasMagicas)) {
			Jogadores ultimoJogador = encontrarUltimoJogador(jogador);
			
			if (ultimoJogador != null) {
				int posicaoAtual = jogador.getPosicaoTabuleiro();
				int posicaoUltimo = ultimoJogador.getPosicaoTabuleiro();
				
				jogador.setPosicaoTabuleiro(posicaoUltimo);
				ultimoJogador.setPosicaoTabuleiro(posicaoAtual);
				
				System.out.println("Jogador " + jogador.getCor() + " caiu na casa " + posicaoAtual +
						" e trocou de posição com o último jogador, que é o jogador " + ultimoJogador.getCor()); // NOSONAR
			}
		}
	}
	
	private Jogadores encontrarUltimoJogador(Jogadores jogadorAtual) {
		Jogadores ultimoJogador = null;
		int menorPosicao = Integer.MAX_VALUE;
		
		for (Jogadores jogador : jogadores) {
			if (jogador != jogadorAtual && jogador.getPosicaoTabuleiro() < menorPosicao) {
				menorPosicao = jogador.getPosicaoTabuleiro();
				ultimoJogador = jogador;
			}
		}
		return ultimoJogador;
	}
	
	private void verificarRetrocederAdversario(Jogadores jogador, int posicao) {
		if (contem(posicao, retrocederOutro)) {
			Scanner teclado = new Scanner(System.in);
			System.out.println("Jogador " + jogador.getCor() + " caiu na casa " + posicao +
					" e pode escolher um adversário para voltar ao início do jogo (casa 0)."); // NOSONAR
			
			List<Jogadores> adversarios = obterAdversarios(jogador);
			exibirOpcoesAdversarios(adversarios);
			
			int escolha = obterEscolhaValida(teclado, adversarios.size());
			Jogadores escolhido = adversarios.get(escolha - 1);
			
			escolhido.setPosicaoTabuleiro(0);
			System.out.println("Jogador " + escolhido.getCor() + " foi escolhido e voltou para a casa 0."); // NOSONAR
		}
	}
	
	private List<Jogadores> obterAdversarios(Jogadores jogadorAtual) {
		List<Jogadores> adversarios = new ArrayList<>();
		for (Jogadores j : jogadores) {
			if (j != jogadorAtual) {
				adversarios.add(j);
			}
		}
		return adversarios;
	}
	
	private void exibirOpcoesAdversarios(List<Jogadores> adversarios) {
		for (int i = 0; i < adversarios.size(); i++) {
			System.out.println((i + 1) + " - Jogador " + adversarios.get(i).getCor() +
					" (posição: " + adversarios.get(i).getPosicaoTabuleiro() + ")"); // NOSONAR
		}
	}
	
	private int obterEscolhaValida(Scanner teclado, int maxOpcoes) {
		int escolha;
		do {
			System.out.print("Escolha o número do adversário que voltará para a casa 0: "); // NOSONAR
			escolha = teclado.nextInt();
		} while (escolha < 1 || escolha > maxOpcoes);
		return escolha;
	}

	public boolean jogarRodadaDebug(Scanner teclado) {
		System.out.println("\n========= RODADA " + numeroRodada + " ========="); // NOSONAR

		for (int i = 0; i < jogadores.size(); i++) {
			System.out.println("<------------------------------------------------->"); // NOSONAR
			Jogadores jogador = jogadores.get(i);

			System.out.println("É a vez do jogador " + jogador.getCor() + ", ele está " +
					"na " +
					"posição: " + jogador.getPosicaoTabuleiro()); // NOSONAR
			
			int novaPosicao;
			do {
				System.out.print("Digite o número da casa que o jogador deve ir: "); // NOSONAR
				novaPosicao = teclado.nextInt();
				
				if (novaPosicao < jogador.getPosicaoTabuleiro()) {
					System.out.println("Não é permitido voltar casas. Tente novamente."); // NOSONAR
				}
			} while (novaPosicao < jogador.getPosicaoTabuleiro());
			
			
			
			int posicaoAntes = jogador.getPosicaoTabuleiro();
			jogador.setPosicaoTabuleiro(novaPosicao);
			int avancou = novaPosicao - posicaoAntes;
			jogador.setJogadas(jogador.getJogadas() + 1);

			System.out.println("Jogador " + jogador.getCor() +
					" está na rodada " + jogador.getJogadas() +
					", avançou " + avancou +
					" casas e está agora na posição " + novaPosicao + "."); // NOSONAR

			verificarCasaEspecial(jogador);

			if (verificarGanhador()) {
				return true;
			}
		}

		numeroRodada++;
		return false;
	}
	
	public ArrayList<Jogadores> getJogadores() {
		return this.jogadores;
	}

}



