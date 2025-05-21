package src.main.dados;

public abstract class Dado {
	protected int valorDado1;
	protected int valorDado2;
	
	public abstract int jogarDados();
	public boolean isDadosIguais() {
		return valorDado1 == valorDado2;
	}
}

