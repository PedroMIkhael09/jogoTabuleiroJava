package classesDados;

import java.util.Random;

public class DadoAzarado implements Dado {
	private Random random = new Random();
	
	@Override
	public int jogarDados() {
		int dado1, dado2;
		do {
			dado1 = random.nextInt(6) + 1;
			dado2 = random.nextInt(6) + 1;
		} while (dado1 + dado2 > 6);
		return dado1 + dado2;
	}
}
