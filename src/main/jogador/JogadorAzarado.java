package src.main.jogador;

import src.main.dados.Dado;
import src.main.dados.DadoAzarado;

public class JogadorAzarado extends Jogadores{
	
	public JogadorAzarado(String cor) {
		super(cor, new DadoAzarado());
		this.posicaoTabuleiro = 0;
		this.jogadas = 0;
	}
	
	public void jogar(){
		this.posicaoTabuleiro += dado.jogarDados();
		this.jogadas++;
	}
}
