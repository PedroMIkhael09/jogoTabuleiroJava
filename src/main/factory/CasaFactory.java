package src.main.factory;
import src.main.casas.*;

public class CasaFactory {
	public static Casa criarCasa(int tipo, int posicao) {
		switch (tipo) {
			case 1: return new CasaPrisao(posicao);
			case 2: return new CasaSurpresa(posicao);
			case 3: return new CasaDaSorte(posicao);
			case 4: return new CasaReversa(posicao);
			case 5: return new CasaRetroceder(posicao);
			case 6: return new CasaJogarDeNovo(posicao);
			case 7: return new CasaDoAzar(posicao);
			default: return new CasaSimples(posicao);
		}
	}
}