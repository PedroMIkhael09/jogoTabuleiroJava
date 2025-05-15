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
	
	public Jogadores mudarTipoJogadorPara(int tipo) {
		Jogadores novoJogador = null;
		
		switch (tipo) {
			case 1:
				novoJogador = new JogadorAzarado(this.cor); // dado azarado
				break;
			case 2:
				novoJogador = new JogadorSortudo(this.cor); // dado sortudo
				break;
			case 3:
				novoJogador = new JogadorNormal(this.cor);  // dado normal
				break;
			default:
				System.out.println("Tipo inválido para mudança.");
				return this;
		}
		
		
		novoJogador.posicaoTabuleiro = this.posicaoTabuleiro;
		novoJogador.jogadas = this.jogadas;
		novoJogador.perdeProximaJogada = this.perdeProximaJogada;
		
		return novoJogador;
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
