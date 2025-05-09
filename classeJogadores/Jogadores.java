package classeJogadores;
import classesDados.Dado;

public abstract class Jogadores{
	protected String cor;
	protected int posicaoTabuleiro;
	protected Dado dado;
	protected int jogadas;
	protected boolean perdeProximaJoagada;
	
	public Jogadores(String cor, Dado dado) {
		this.cor = cor;
		this.posicaoTabuleiro = 0;
		this.dado = dado;
	}
	
	public abstract void jogar();
	
	
	public String getCor() {
		return cor;
	}
	
	public int getPosicaoTabuleiro() {
		return posicaoTabuleiro;
	}
	
	public void setPosicaoTabuleiro(int posicaoTabuleiro) {
		this.posicaoTabuleiro = posicaoTabuleiro;
	}
	
	public boolean getPerdeProximaJoagada() {
		return perdeProximaJoagada;
	}
	
	public void setPerdeProximaJoagada(boolean perdeProximaJoagada) {
		this.perdeProximaJoagada = perdeProximaJoagada;
	}
	
	
}
