package src.main.casas;

import src.main.jogador.Jogador;
import src.main.mensagem.EfeitoDaCasa;

import java.util.ArrayList;

public class CasaReversa extends Casa {
	public CasaReversa(int numeroCasa) {
		super(numeroCasa);
	}
	
	@Override
	public EfeitoDaCasa aplicarRegra(Jogador jogadorAtual, ArrayList<Jogador> jogadores, int indiceUltimo) {
		if (indiceUltimo < 0 || indiceUltimo >= jogadores.size()) {
			return new EfeitoDaCasa("Nenhum jogador válido para trocar posições.");
		}
		Jogador ultimoJogador = jogadores.get(indiceUltimo);
		int posAtual = jogadorAtual.getPosicaoTabuleiro();
		int posUltimo = ultimoJogador.getPosicaoTabuleiro();
		
		jogadorAtual.setPosicaoTabuleiro(posUltimo);
		ultimoJogador.setPosicaoTabuleiro(posAtual);
		
		return new EfeitoDaCasa("Jogador " + jogadorAtual.getCor() + " trocou de posição com jogador " + ultimoJogador.getCor() + ".");
	}
}
