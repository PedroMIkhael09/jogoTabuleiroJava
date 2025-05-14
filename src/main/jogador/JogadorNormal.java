package src.main.jogador;


import src.main.dados.Dado;
import src.main.dados.DadoPadrao;

public class JogadorNormal extends Jogadores{
	
	public JogadorNormal(String cor){
		super(cor, new DadoPadrao());
		this.posicaoTabuleiro = 0;
		this.jogadas = 0;
	}
	
	public void jogar(){
		this.posicaoTabuleiro += dado.jogarDados();
		this.jogadas++;
	}
	
}
