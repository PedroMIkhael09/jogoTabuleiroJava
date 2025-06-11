package src.main.jogador;
import java.util.Random;

public class JogadorSortudo extends Jogadores {
	
	public JogadorSortudo(String cor){
		super(cor);
	}
	
	public int jogarDados(){
		do {
			this.valorDado1 = RANDOM.nextInt(6) + 1;
			this.valorDado2 = RANDOM.nextInt(6) + 1;
		} while ((valorDado1 + valorDado2) <= 7);
		return valorDado1 + valorDado2;
	}
	
	public void atualizarJogador(){
		int soma = jogarDados();
		this.posicaoTabuleiro += soma;
		this.jogadas++;
	}
}
