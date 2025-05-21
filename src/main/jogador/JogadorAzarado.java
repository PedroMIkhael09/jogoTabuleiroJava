package src.main.jogador;

import src.main.dados.DadoAzarado;

public class JogadorAzarado extends Jogadores{
	
	public JogadorAzarado(String cor) {
		super(cor, new DadoAzarado());
	}
}
