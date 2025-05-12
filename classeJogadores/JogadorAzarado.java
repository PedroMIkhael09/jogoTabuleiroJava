package classeJogadores;
import classesDados.DadoAzarado;

public class JogadorAzarado extends Jogadores{
	
	public JogadorAzarado(String cor) {
		super(cor, new DadoAzarado());
		this.posicaoTabuleiro = 0;
		thissssssss.jogadas = 0;
	}
	
	public void jogar(){
		this.posicaoTabuleiro = dado.jogarDados();
		this.jogadas++;
	}
}
