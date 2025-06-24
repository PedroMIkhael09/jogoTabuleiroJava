package src.main.casas;

import src.main.jogador.JogadorAzarado;
import src.main.jogador.Jogador;
import src.main.mensagem.EfeitoDaCasa;

import java.util.ArrayList;

public class CasaDaSorte extends Casa {
	public CasaDaSorte(int numeroCasa) {
		super(numeroCasa);
	}
	
	@Override
	public EfeitoDaCasa aplicarRegra(Jogador jogador, ArrayList<Jogador> jogadores, int escolha) {
		if (!(jogador instanceof JogadorAzarado)) {
			jogador.setPosicaoTabuleiro(jogador.getPosicaoTabuleiro() + 3);
			return new EfeitoDaCasa("Jogador " + jogador.getCor() + " teve sorte e avançou 3 casas!");
		}
		return new EfeitoDaCasa("Jogador " + jogador.getCor() + " é azarado e não teve sorte nesta casa.");
	}
}
