package src.main.jogador;
import java.util.Random;


public abstract class Jogadores{
	protected String cor;
	protected int posicaoTabuleiro;
	protected int valorDado1;
	protected int valorDado2;
	protected int jogadas;
	protected boolean perdeProximaJogada;
	@SuppressWarnings("java:S2245")
	protected static final Random RANDOM = new Random(); //NOSONAR
	
	public Jogadores(String cor) {
		this.cor = cor;
		this.posicaoTabuleiro = 0;
		this.jogadas = 0;
		this.perdeProximaJogada = false;
	}

	public abstract int jogarDados();
	
	public abstract void atualizarJogador();
	
	public boolean verificarDadosIguais(){
		return valorDado1 == valorDado2;
	}

	public Jogadores mudarTipoJogadorPara(int tipo) {
		Jogadores novoJogador;

        switch (tipo) {
            case 1 -> novoJogador = new JogadorAzarado(this.cor);
            case 2 -> novoJogador = new JogadorSortudo(this.cor);
            case 3 -> novoJogador = new JogadorNormal(this.cor);
            default -> {
                System.out.println("Tipo inválido para mudança.");               return this;
            }
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
	public void setJogadas(int jogadas) {
		this.jogadas = jogadas;
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
