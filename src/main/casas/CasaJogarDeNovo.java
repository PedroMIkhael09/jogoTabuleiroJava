package src.main.casas;

import src.main.jogador.Jogador;
import src.main.mensagem.EfeitoDaCasa;

import java.util.ArrayList;
public class CasaJogarDeNovo extends Casa {
	public CasaJogarDeNovo(int numeroCasa) {
		super(numeroCasa);
	}
	
	@Override
	public EfeitoDaCasa aplicarRegra(Jogador jogador, ArrayList<Jogador> jogadores, int escolha) {
		jogador.setJogaDeNovo(true);
		return new EfeitoDaCasa("Jogador " + jogador.getCor() + " jogou de novo!");
	}
	
	
	@Override
	public String getTipo() {
		return "Casa para jogar de novo";
	}
}