package src.main.jogador;

public class JogadorAzarado extends Jogador {
	
	public JogadorAzarado(String cor){
		super(cor);
	}
	
	public int jogarDados(){
		do {
			this.valorDado1 = RANDOM.nextInt(6) + 1;
			this.valorDado2 = RANDOM.nextInt(6) + 1;
		} while ((valorDado1 + valorDado2) > 6);
		return valorDado1 + valorDado2;
	}
	
	public void atualizarJogador(){
		int soma = jogarDados();
		this.posicaoTabuleiro += soma;
		this.jogadas++;
	}
}