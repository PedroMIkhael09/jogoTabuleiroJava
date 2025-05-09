package classesDados;
import java.util.Random;

public class DadoSortudo implements Dado{
	
	private Random random = new Random();
	
	@Override
	public int jogarDados() {
		int dado1, dado2;
		do {
			dado1 = random.nextInt(6) + 1;
			dado2 = random.nextInt(6) + 1;
		} while (dado1 + dado2 < 7);
			return dado1 + dado2;
		}
	}
	
