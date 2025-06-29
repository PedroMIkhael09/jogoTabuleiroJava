package src.main.casas;

import src.main.jogador.Jogador;
import src.main.mensagem.EfeitoDaCasa;

import java.util.ArrayList;

public class CasaSurpresa extends Casa {
	public CasaSurpresa(int numeroCasa) {
		super(numeroCasa);
	}
	
	@Override
	public EfeitoDaCasa aplicarRegra(Jogador jogador, ArrayList<Jogador> jogadores, int escolhaJogador) {
		String tipoAntigo = jogador.getClass().getSimpleName();
		Jogador novoJogador = jogador.mudarTipoJogadorPara(escolhaJogador);
		int index = jogadores.indexOf(jogador);
		jogadores.set(index, novoJogador);
		String tipoNovo = novoJogador.getClass().getSimpleName();
		return new EfeitoDaCasa("Jogador " + novoJogador.getCor() + " mudou de tipo: de " + tipoAntigo + " para " + tipoNovo + ".");
	}
	
	@Override
	public String getTipo() {
		return "Casa surpresa";
	}
}
