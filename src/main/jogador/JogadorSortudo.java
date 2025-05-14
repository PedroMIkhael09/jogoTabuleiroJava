package src.main.jogador;
import src.main.dados.Dado;
import src.main.dados.DadoSortudo;


public class JogadorSortudo extends Jogadores {
	
	public JogadorSortudo(String cor){
		super(cor, new DadoSortudo());
		this.posicaoTabuleiro = 0;
		this.jogadas = 0;
	}
	
	public void jogar(){
		this.posicaoTabuleiro += dado.jogarDados();
		this.jogadas++;
	}
	
}
