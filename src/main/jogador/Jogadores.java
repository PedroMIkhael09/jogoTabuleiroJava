package src.main.jogador;
import src.main.dados.Dado;

public abstract class Jogadores{
	protected String cor;
	protected int posicaoTabuleiro;
	protected Dado dado;
	protected int jogadas;
	protected boolean perdeProximaJogada;
	
	public Jogadores(String cor, Dado dado) {
		this.cor = cor;
		this.posicaoTabuleiro = 0;
		this.dado = dado;
		this.jogadas = 0;
		this.perdeProximaJogada = false;
	}
	
	public abstract void jogar();
	
	public void mudarTipoJogador(Jogadores novoJogador) {
		this.cor = novoJogador.cor;
		this.dado = novoJogador.dado;
		this.posicaoTabuleiro = novoJogador.posicaoTabuleiro;
		this.jogadas = novoJogador.jogadas;
		this.perdeProximaJogada = novoJogador.perdeProximaJogada;
	}
	
	
	// Getters and Setters
	public int getJogadas() {
		return jogadas;
	}
	
	public Dado getDado() {
		return dado;
	}
	
	public String getCor() {
		return cor;
	}
	
	public int getPosicaoTabuleiro() {
		return posicaoTabuleiro;
	}
	
	public void setPosicaoTabuleiro(int posicaoTabuleiro) {
		this.posicaoTabuleiro = posicaoTabuleiro;
	}
	
	public boolean getPerdeProximaJogada() {
		return perdeProximaJogada;
	}
	
	public void setPerdeProximaJogada(boolean perdeProximaJogada) {
		this.perdeProximaJogada = perdeProximaJogada;
	}
	
	
}
