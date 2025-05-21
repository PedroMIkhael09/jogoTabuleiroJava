package src.main.jogador;
import src.main.dados.DadoSortudo;


public class JogadorSortudo extends Jogadores {
	
	public JogadorSortudo(String cor){
		super(cor, new DadoSortudo());
	}
}
