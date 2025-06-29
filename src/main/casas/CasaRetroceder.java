package src.main.casas;

import src.main.jogador.Jogador;
import src.main.mensagem.EfeitoDaCasa;

import java.util.ArrayList;

public class CasaRetroceder extends Casa {
	public CasaRetroceder(int numeroCasa) {
		super(numeroCasa);
	}
	
	@Override
	public EfeitoDaCasa aplicarRegra(Jogador jogador, ArrayList<Jogador> jogadores, int indiceAdversario) {
		Jogador escolhido = jogadores.get(indiceAdversario);
		if (escolhido == jogador) {
			return new EfeitoDaCasa("Jogador " + jogador.getCor() + " não pode escolher a si mesmo para retroceder.");
		}
		escolhido.setPosicaoTabuleiro(0);
		return new EfeitoDaCasa("Jogador " + escolhido.getCor() + " foi obrigado a voltar para a casa 0 pelo jogador " + jogador.getCor() + ".");
	}
	
	@Override
	public String getTipo() {
		return "Casa retroceder";
	}
}
