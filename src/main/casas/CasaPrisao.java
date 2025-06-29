package src.main.casas;

import src.main.jogador.Jogador;
import src.main.mensagem.EfeitoDaCasa;

import java.util.ArrayList;
public class CasaPrisao extends Casa {
	
	public CasaPrisao(int numeroCasa) {
		super(numeroCasa);
	}
	
	@Override
	public EfeitoDaCasa aplicarRegra(Jogador jogador, ArrayList<Jogador> jogadores, int escolha) {
		jogador.setPerdeProximaJogada(true);
		return new EfeitoDaCasa("Jogador " + jogador.getCor() + " não joga a proxima " +
				"jogada!");
	}
	
	@Override
	public String getTipo() {
		return "Casa prisão";
	}
	
	
}