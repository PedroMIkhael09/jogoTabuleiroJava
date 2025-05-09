package classeJogadores;

import classesDados.DadoNormal;

public class JogadorNormal extends Jogadores{
	
	public JogadorNormal(String cor){
		super(cor, new DadoNormal());
		this.posicaoTabuleiro = 0;
		this.jogadas = 0;
	}
	
	public void jogar(){
		this.posicaoTabuleiro = dado.jogarDados();
		this.jogadas++;
	}
	
}
