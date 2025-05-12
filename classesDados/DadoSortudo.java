package classesDados;
import java.util.Random;

public class DadoSortudo extends Dado{
	
	private Random random = new Random();
	
	@Override
	public int jogarDados() {
		do {
			this.valorDado1 = random.nextInt(6) + 1;
			this.valorDado2 = random.nextInt(6) + 1;
		} while (valorDado1 + valorDado2 < 7);
			return valorDado1 + valorDado2;
	}
	
	public boolean isDadosIguais() {
		return this.valorDado1 == this.valorDado2;
	}
}
	
