package src.main.dados;
import java.util.Random;

public class DadoPadrao extends Dado {
	private final Random random = new Random();
	
	@Override
	public int jogarDados() {
		valorDado1 = random.nextInt(6) + 1;
		valorDado2 = random.nextInt(6) + 1;
		return valorDado1 + valorDado2;
	}

}
