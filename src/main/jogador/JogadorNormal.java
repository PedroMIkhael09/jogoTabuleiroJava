package src.main.jogador;
import java.util.Random;


public class JogadorNormal extends Jogadores{
	private final Random random = new Random();
	
	public JogadorNormal(String cor){
		super(cor);
	}
	
	public int jogarDados(){
		this.valorDado1 = random.nextInt(6) + 1;
		this.valorDado2 = random.nextInt(6) + 1;
		return valorDado1 + valorDado2;
	}
	
	public void atualizarJogador(){
		int soma = jogarDados();
		this.posicaoTabuleiro += soma;
		this.jogadas++;
	}
}
