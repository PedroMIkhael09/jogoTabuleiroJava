package src.main.dados;

public abstract class Dado {
	protected int valorDado1;
	protected int valorDado2;
	
	public abstract int jogarDados();
	public abstract boolean isDadosIguais();
}

