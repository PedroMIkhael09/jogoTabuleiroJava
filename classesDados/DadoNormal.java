package classesDados;
import java.util.Random;

public class DadoNormal implements Dado {
	private Random random = new Random();
	
	@Override
	public int jogarDados() {
		return random.nextInt(6) + 1;
	}
}
