package src.main.casas;

import src.main.jogador.Jogador;
import src.main.mensagem.EfeitoDaCasa;

import java.util.ArrayList;

public class CasaSimples extends Casa {
	public CasaSimples(int numeroCasa) {
		super(numeroCasa);
	}
	
	@Override
	public EfeitoDaCasa aplicarRegra(Jogador jogador, ArrayList<Jogador> jogadores, int escolha) {
		// Não faz nada, casa normal
		return new EfeitoDaCasa("Casa normal - sem efeito especial para o jogador " + jogador.getCor() + ".");
	}
	
	@Override
	public String getTipo() {
		return "Casa simples";
	}
}
