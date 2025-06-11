package src.main.jogador;

public class JogadorNormal extends Jogadores{
	
	
	public JogadorNormal(String cor){
		super(cor);
	}
	
	public int jogarDados(){
		this.valorDado1 = RANDOM.nextInt(6) + 1;
		this.valorDado2 = RANDOM.nextInt(6) + 1;
		return valorDado1 + valorDado2;
	}
	
	public void atualizarJogador(){
		int soma = jogarDados();
		this.posicaoTabuleiro += soma;
		this.jogadas++;
	}
}
