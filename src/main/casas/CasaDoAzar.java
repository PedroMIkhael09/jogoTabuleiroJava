package src.main.casas;

import src.main.jogador.JogadorSortudo;
import src.main.jogador.Jogador;
import src.main.mensagem.EfeitoDaCasa;

import java.util.ArrayList;

public class CasaDoAzar extends Casa {
	public CasaDoAzar(int numeroCasa) {
		super(numeroCasa);
	}
	
	@Override
	public EfeitoDaCasa aplicarRegra(Jogador jogador, ArrayList<Jogador> jogadores, int escolha) {
		if (!(jogador instanceof JogadorSortudo)) {
			jogador.setPosicaoTabuleiro(jogador.getPosicaoTabuleiro() - 3);
			return new EfeitoDaCasa("Jogador " + jogador.getCor() + " teve azar e voltou 3 casas!");
		}
		return new EfeitoDaCasa("Jogador " + jogador.getCor() + " é sortudo e escapou do azar!");
	}
}
