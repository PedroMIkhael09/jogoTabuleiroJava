package src.main.factory;

import src.main.jogador.Jogador;
import src.main.jogador.JogadorAzarado;
import src.main.jogador.JogadorNormal;
import src.main.jogador.JogadorSortudo;

public class JogadorFactory {
	public static Jogador criarJogador(int tipo, String cor) {
		switch (tipo) {
			case 1:
				return new JogadorAzarado(cor);
			case 2:
				return new JogadorSortudo(cor);
			case 3:
				return new JogadorNormal(cor);
			default:
				throw new IllegalArgumentException("Tipo de jogador inválido: " + tipo);
		}
	}
}