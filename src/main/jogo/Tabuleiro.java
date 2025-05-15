package src.main.jogo;
import src.main.jogador.Jogadores;
import src.main.jogador.JogadorAzarado;
import src.main.jogador.JogadorNormal;
import src.main.jogador.JogadorSortudo;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Tabuleiro {
	protected ArrayList<Jogadores> jogadores;
	private int numeroRodada = 1;
	private final int[] perdeRodada = {10, 25, 38};
	private final int[] mudaTipoJogador = {13};
	private final int[] ganhaTresPosicoes = {5, 15, 30};
	private final int[] casasMagicas = {20, 35};
	private final int[] retrocederOutro = {17, 27};
	
	
	public Tabuleiro() {
		this.jogadores = new ArrayList<>();
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
		System.out.println("\n========= RODADA " + numeroRodada + " =========");
		
		for (int i = 0; i < jogadores.size(); i++) {
			System.out.println("<------------------------------------------------->");
			Jogadores jogador = jogadores.get(i);
			
			if (jogador.getPerdeProximaJogada()) {
				System.out.println("Jogador " + jogador.getCor() + " perdeu esta rodada!");
				jogador.setPerdeProximaJogada(false);
			} else {
				int posicaoAntes = jogador.getPosicaoTabuleiro();
				jogador.jogar();
				int posicaoDepois = jogador.getPosicaoTabuleiro();
				int avancou = posicaoDepois - posicaoAntes;
				
				System.out.println("Jogador " + jogador.getCor() +
						" está na rodada " + jogador.getJogadas() +
						", avançou " + avancou +
						" casas e está agora na posição " + posicaoDepois + ".");
				
				verificarCasaEspecial(jogador);
				if (verificarGanhador()) {
					return true;
				}
				
				while (jogador.getDado().isDadosIguais()) {
					System.out.println("🎲 Jogador " + jogador.getCor() + " tirou dados iguais! Joga novamente.");
					posicaoAntes = jogador.getPosicaoTabuleiro();
					jogador.jogar();
					posicaoDepois = jogador.getPosicaoTabuleiro();
					avancou = posicaoDepois - posicaoAntes;
					
					System.out.println("Jogador " + jogador.getCor() +
							" está na rodada " + jogador.getJogadas() +
							", avançou " + avancou +
							" casas e está agora na posição " + posicaoDepois + ".");
					
					verificarCasaEspecial(jogador);
					if (verificarGanhador()) {
						return true;
					}
				}
			}
			
			try {
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		numeroRodada++;
		return false;
	}
	
	public boolean verificarGanhador() {
		for (int i = 0; i < jogadores.size(); i++) {
			Jogadores jogador = jogadores.get(i);
			if (jogador.getPosicaoTabuleiro() >= 40) {
				System.out.println("O jogador " + jogador.getCor() + " ganhou a partida em " + jogador.getJogadas() + " rodadas!");
				
				System.out.println("\n--- RESUMO FINAL DOS JOGADORES ---");
				for (Jogadores j : jogadores) {
					System.out.println("Jogador " + j.getCor() + " - Posição: " + j.getPosicaoTabuleiro() + " - Rodadas jogadas: " + j.getJogadas());
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
		
		// Casas que fazem o jogador perder a próxima rodada
		if (contem(posicao, perdeRodada)) {
			System.out.println("Jogador " + jogador.getCor() + " caiu na casa " + posicao + ", por isso, não joga a próxima rodada");
			jogador.setPerdeProximaJogada(true);
		}
		
		// Casas que fazem o jogador mudar de tipo
		if (contem(posicao, mudaTipoJogador)) {
			Scanner teclado = new Scanner(System.in);
			System.out.println("Jogador " + jogador.getCor() + ", você terá que mudar de tipo, pois caiu na casa " + posicao + ".");
			System.out.println("Escolha a carta surpresa (1 - Azarado, 2 - Sortudo, 3 - Normal):");
			int numero = teclado.nextInt();
			
			Jogadores novoJogador = jogador.mudarTipoJogadorPara(numero);
			int index = jogadores.indexOf(jogador);
			jogadores.set(index, novoJogador);
			
			System.out.println("Jogador " + novoJogador.getCor() + " mudou para o tipo " + novoJogador.getClass().getSimpleName());
		}
		
		// Casas que fazem o jogador ganhar 3 posições exceto se for JogadorAzarado
		if (contem(posicao, ganhaTresPosicoes)) {
			if (!(jogador instanceof JogadorAzarado)) {
				System.out.println("Jogador " + jogador.getCor() + " ganhou 3 posições, pois caiu na casa " + posicao + ".");
				jogador.setPosicaoTabuleiro(jogador.getPosicaoTabuleiro() + 3);
			} else {
				System.out.println("Jogador " + jogador.getCor() + " deveria ganhar 3 pontos extras por estar na casa " + posicao + ", mas o tipo de jogador é Azarado e por isso não ganha as 3 posições extras");
			}
		}
		
		// Casas mágicas que trocam posição com o último jogador (que está na menor posição)
		if (contem(posicao, casasMagicas)) {
			int posicaoUltimoJogador = Integer.MAX_VALUE;
			int indiceUltimoJogador = -1;
			
			for (int i = 0; i < jogadores.size(); i++) {
				Jogadores outroJogador = jogadores.get(i);
				
				if (outroJogador != jogador && outroJogador.getPosicaoTabuleiro() < posicaoUltimoJogador) {
					posicaoUltimoJogador = outroJogador.getPosicaoTabuleiro();
					indiceUltimoJogador = i;
				}
			}
			
			if (indiceUltimoJogador != -1) {
				int posicaoAtual = jogador.getPosicaoTabuleiro();
				
				jogador.setPosicaoTabuleiro(posicaoUltimoJogador);
				jogadores.get(indiceUltimoJogador).setPosicaoTabuleiro(posicaoAtual);
				
				System.out.println("Jogador " + jogador.getCor() + " caiu na casa " + posicaoAtual +
						" e trocou de posição com o último jogador, que é o jogador " + jogadores.get(indiceUltimoJogador).getCor());
			}
		}
		// Casas que permitem escolher um adversário para voltar à casa 0
		if (contem(posicao, retrocederOutro)) {
			Scanner teclado = new Scanner(System.in);
			System.out.println("Jogador " + jogador.getCor() + " caiu na casa " + posicao +
					" e pode escolher um adversário para voltar ao início do jogo (casa 0).");
			
			ArrayList<Jogadores> adversarios = new ArrayList<>();
			for (Jogadores j : jogadores) {
				if (j != jogador) {
					adversarios.add(j);
				}
			}
			
			// Exibe as opções
			for (int i = 0; i < adversarios.size(); i++) {
				System.out.println((i + 1) + " - Jogador " + adversarios.get(i).getCor() +
						" (posição: " + adversarios.get(i).getPosicaoTabuleiro() + ")");
			}
			
			int escolha = -1;
			do {
				System.out.print("Escolha o número do adversário que voltará para a casa 0: ");
				escolha = teclado.nextInt();
			} while (escolha < 1 || escolha > adversarios.size());
			
			Jogadores escolhido = adversarios.get(escolha - 1);
			escolhido.setPosicaoTabuleiro(0);
			
			System.out.println("Jogador " + escolhido.getCor() + " foi escolhido e voltou para a casa 0.");
		}
		
	}
}



