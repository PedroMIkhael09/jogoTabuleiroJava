package src.main.dados;
import java.util.Random;

public class DadoAzarado extends Dado {
	private Random random = new Random();
	
	@Override
	public int jogarDados() {
		do {
			valorDado1 = random.nextInt(6) + 1;
			valorDado2 = random.nextInt(6) + 1;
		} while ((valorDado1 + valorDado2) > 6);
		return valorDado1 + valorDado2;
	}
	
	@Override
	public boolean isDadosIguais() {
		return valorDado1 == valorDado2;
	}
}
