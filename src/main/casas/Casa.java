package src.main.casas;

import src.main.jogador.Jogador;
import src.main.mensagem.EfeitoDaCasa;

import java.util.ArrayList;
public abstract class Casa {
	protected int numeroCasa;
	
	
	
	protected Casa(int numeroCasa) {
		this.numeroCasa = numeroCasa;
	}
	public abstract EfeitoDaCasa aplicarRegra(Jogador jogador, ArrayList<Jogador> jogadores,
											  int escolha);
	
	public int getNumero() {
		return this.numeroCasa;
	}
	
	public abstract String getTipo();
}

