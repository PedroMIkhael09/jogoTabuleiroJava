import classeJogadores.JogadorAzarado;
import classeJogadores.JogadorNormal;
import classeJogadores.JogadorSortudo;
import classeJogadores.Jogadores;

import java.util.ArrayList;
import java.util.Random;
import java.util.Random;
import java.util.Scanner;

public class Tabuleiro {
	protected ArrayList<Jogadores> jogadores;
	private final int[] perdeRodada = {10, 25, 38};
	private final int[] mudaTipoJogador = {13};
	private final int[] ganhaTresPosicoes = {5, 15, 30};
	private final int[] casasMagicas = {20, 35};
	
	
	public Tabuleiro() {
		this.jogadores = new ArrayList<>();
	}
	
	public void adicionarJogadores(Jogadores j) {
		jogadores.add(j);
	}
	
	public void jogarRodada() {
		for (int i = 0; i < jogadores.size(); i++) {
			jogadores.get(i).jogar();
			
		}
	}
	
	public void verificarCasaEspecial(Jogadores jogador) {
		int posicao = jogador.getPosicaoTabuleiro();
		
		// Casas que fazem o jogador perder a próxima rodada
		if (posicao == 10 || posicao == 25 || posicao == 38) {
			jogador.setPerdeProximaJogada(true);
		}
		
		// Casas que fazem com que o jogador mude de tipo
		if (posicao == 13) {
			Random random = new Random();
			Scanner teclado = new Scanner(System.in);
			
			System.out.println("Escolha a carta surpresa: ");
			System.out.println("Escolha entre 1, 2 ou 3");
			int numero = teclado.nextInt();
			
			// Obter a cor do jogador atual
			String corJogador = jogador.getCor();
			
			if (numero == 1) {
				jogador.mudarTipoJogador(new JogadorAzarado(corJogador));
				System.out.println("Jogador mudou para o tipo Azarado");
			} else if (numero == 2) {
				jogador.mudarTipoJogador(new JogadorSortudo(corJogador));
				System.out.println("Jogador mudou para o tipo Sortudo");
			} else if (numero == 3) {
				jogador.mudarTipoJogador(new JogadorNormal(corJogador));
				System.out.println("Jogador mudou para o tipo Normal");
			}
			
		}
		
		
		
		//Casas em que o jogador ganha 3 posicoes, se nao for JogadorAzarado
		if (posicao == 5 || posicao == 15 || posicao == 30) {
			if (!(jogador instanceof JogadorAzarado)) {
				jogador.setPosicaoTabuleiro(jogador.getPosicaoTabuleiro() + 3);
			}
		}
		
		//Casas magicas, troca de posição com o ultimo
		if (posicao == 20 || posicao == 35) {
			int posicaoUltimoJogador = -1;
			int indiceUltimoJogador = -1;
			
			// Encontrar o jogador que está na posição mais avançada
			for(int i = 0; i < jogadores.size(); i++) {
				if(jogadores.get(i).getPosicaoTabuleiro() > posicaoUltimoJogador && 
				   jogadores.get(i) != jogador) {
					posicaoUltimoJogador = jogadores.get(i).getPosicaoTabuleiro();
					indiceUltimoJogador = i;
				}
			}
			
			if(indiceUltimoJogador != -1) {
				int posicaoAtual = jogador.getPosicaoTabuleiro();
				
				
				jogador.setPosicaoTabuleiro(posicaoUltimoJogador);
				jogadores.get(indiceUltimoJogador).setPosicaoTabuleiro(posicaoAtual);
			}
		}	
	}
}
