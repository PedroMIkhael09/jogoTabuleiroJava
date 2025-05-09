import classeJogadores.Jogadores;

import java.util.ArrayList;

public class Tabuleiro {
	protected ArrayList<Jogadores> jogadores;
	private final int[] casasEspeciais = {10, 25, 38, 13, 5, 15, 30, 17, 27, 20, 35};
	
	
	public Tabuleiro() {
		this.jogadores = new ArrayList<>();
	}
	
	public void adicionarJogadores(Jogadores j) {
		jogadores.add(j);
	}
	
	public void jogarRodada(){
		for (int i = 0; i < jogadores.size(); i++) {
			jogadores.get(i).jogar();
			
		}
	}
	
	public void verificarCasaEspecial(Jogadores jogador, ArrayList<Jogadores> jogadores) {
		int posicao = jogador.getPosicaoTabuleiro();
		
		// Casas que fazem o jogador perder a próxima rodada
		if (posicao == 10 || posicao == 25 || posicao == 38) {
			jogador.setPerdeProximaJoagada(true);
		}
		
	}
	
	
}
