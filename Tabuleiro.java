import classeJogadores.JogadorAzarado;
import classeJogadores.Jogadores;

import java.util.ArrayList;

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
		
		
	}
}
