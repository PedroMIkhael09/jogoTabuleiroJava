package src.main.jogo;

import src.main.casas.Casa;
import src.main.casas.CasaRetroceder;
import src.main.casas.CasaReversa;
import src.main.casas.CasaSurpresa;
import src.main.factory.CasaFactory;
import src.main.factory.JogadorFactory;
import src.main.jogador.Jogador;
import src.main.mensagem.EfeitoDaCasa;
import src.main.visao.Jogo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tabuleiro {
	public ArrayList<Jogador> jogadores;
	public ArrayList<Casa> casas;
	private int numeroRodada = 1;
	private static Tabuleiro instancia;
	private Scanner teclado = new Scanner(System.in);
	
	private Tabuleiro() {
		this.jogadores = new ArrayList<>();
		this.casas = new ArrayList<>();
	}
	
	public static Tabuleiro getInstancia() {
		if (instancia == null) {
			instancia = new Tabuleiro();
		}
		return instancia;
	}
	
	public void adicionarJogador(int tipo, String cor) {
		Jogador jogador = JogadorFactory.criarJogador(tipo, cor);
		this.jogadores.add(jogador);
	}
	
	public void adicionarCasa(int tipoCasa, int posicao) {
		Casa casa = CasaFactory.criarCasa(tipoCasa, posicao);
		this.casas.add(casa);
	}
	
	public void limparJogadores() {
		jogadores.clear();
	}
	
	public boolean verificarCores(String cor) {
		for (Jogador j : jogadores) {
			if (j.getCor().equalsIgnoreCase(cor)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean jogarRodada() {
		System.out.println("\n========= RODADA " + numeroRodada + " =========");
		for (Jogador jogador : jogadores) {
			imprimirSeparadorRodada();
			if (verificarSeJogadorPerdeuRodada(jogador)) {
				continue;
			}
			if (processarJogadaCompleta(jogador)) {
				return true;
			}
			pausarEntreJogadas();
		}
		incrementarNumeroRodada();
		return false;
	}
	
	public void imprimirSeparadorRodada() {
		System.out.println("<------------------------------------------------->");
	}
	
	public boolean verificarSeJogadorPerdeuRodada(Jogador jogador) {
		if (jogador.getPerdeProximaJogada()) {
			System.out.println("Jogador " + jogador.getCor() + " perdeu esta rodada!");
			jogador.setPerdeProximaJogada(false);
			return true;
		}
		return false;
	}
	
	public boolean processarJogadaCompleta(Jogador jogador) {
		if (processarMovimentoJogador(jogador)) return true;
		if (processarRodadasExtrasPorDadosIguais(jogador)) return true;
		
		if (jogador.getJogaDeNovo()) {
			jogador.setJogaDeNovo(false);
			System.out.println("🔁 Jogador " + jogador.getCor() + " caiu numa casa de jogar de novo! Vai jogar outra vez.");
			if (processarJogadaCompleta(jogador)) return true;
		}
		return false;
	}
	
	public boolean processarMovimentoJogador(Jogador jogador) {
		int posicaoAntes = jogador.getPosicaoTabuleiro();
		jogador.atualizarJogador();
		int posicaoDepois = jogador.getPosicaoTabuleiro();
		imprimirResultadoJogada(jogador, posicaoAntes, posicaoDepois);
		verificarCasaEspecial(jogador);
		return verificarGanhador();
	}
	
	public void imprimirResultadoJogada(Jogador jogador, int posicaoAntes, int posicaoDepois) {
		System.out.println("Jogador " + jogador.getCor() +
				" está na rodada " + jogador.getJogadas() +
				", avançou " + (posicaoDepois - posicaoAntes) +
				" casas e está agora na posição " + posicaoDepois + ".");
	}
	
	public boolean processarRodadasExtrasPorDadosIguais(Jogador jogador) {
		while (jogador.verificarDadosIguais()) {
			System.out.println("🎲 Jogador " + jogador.getCor() + " tirou dados iguais! Joga novamente.");
			if (processarMovimentoJogador(jogador)) return true;
		}
		return false;
	}
	
	public void pausarEntreJogadas() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			System.out.println("Jogo interrompido!");
			Thread.currentThread().interrupt();
		}
	}
	
	public void incrementarNumeroRodada() {
		numeroRodada++;
	}
	
	public boolean verificarGanhador() {
		for (Jogador jogador : jogadores) {
			if (jogador.getPosicaoTabuleiro() >= casas.size()) {
				System.out.println("O jogador " + jogador.getCor() + " ganhou a partida em " + jogador.getJogadas() + " rodadas!");
				System.out.println("\n--- RESUMO FINAL DOS JOGADORES ---");
				
				List<Jogador> jogadoresOrdenados = new ArrayList<>(jogadores);
				
				jogadoresOrdenados.sort((j1, j2) -> Integer.compare(j2.getPosicaoTabuleiro(), j1.getPosicaoTabuleiro()));
				
				for (Jogador j : jogadoresOrdenados) {
					System.out.println("Jogador " + j.getCor() +
							" - Posição : " + j.getPosicaoTabuleiro() +
							" - Rodadas jogadas: " + j.getJogadas());
				}
				return true;
			}
		}
		return false;
	}
	
	
	public Casa getCasaNaPosicao(int posicao) {
		if (posicao > 0 && posicao <= casas.size()) {
			return casas.get(posicao - 1);
		}
		return null;
	}
	
	public void verificarCasaEspecial(Jogador jogador) {
		Casa casaAtual = getCasaNaPosicao(jogador.getPosicaoTabuleiro());
		if (casaAtual == null) return;
		
		
		EfeitoDaCasa efeito = processarCasa(casaAtual, jogador, teclado);
		Jogo.exibirMensagem(efeito);
		
	}
	
	public EfeitoDaCasa processarCasa(Casa casa, Jogador jogador, Scanner teclado) {
		if (casa instanceof CasaRetroceder) {
			return processarCasaRetroceder((CasaRetroceder) casa, jogador, teclado);
		}
		if (casa instanceof CasaSurpresa) {
			return processarCasaSurpresa((CasaSurpresa) casa, jogador, teclado);
		}
		if (casa instanceof CasaReversa) {
			return processarCasaReversa((CasaReversa) casa, jogador);
		}
		return casa.aplicarRegra(jogador, jogadores, 0);
	}
	
	public EfeitoDaCasa processarCasaRetroceder(CasaRetroceder casa, Jogador jogador, Scanner teclado) {
		List<Jogador> adversarios = obterAdversarios(jogador);
		exibirOpcoesAdversarios(adversarios);
		int escolha = obterEscolhaValida(teclado, adversarios.size());
		return aplicarRegraRetroceder(casa, jogador, adversarios, escolha);
	}
	
	public EfeitoDaCasa aplicarRegraRetroceder(CasaRetroceder casa, Jogador jogador, List<Jogador> adversarios, int escolha) {
		Jogador escolhido = adversarios.get(escolha - 1);
		int indiceReal = jogadores.indexOf(escolhido);
		return casa.aplicarRegra(jogador, jogadores, indiceReal);
	}
	
	public EfeitoDaCasa processarCasaSurpresa(CasaSurpresa casa, Jogador jogador, Scanner teclado) {
		int escolha = obterEscolhaSurpresa(teclado, jogador);
		validarJogadorPresente(jogador);
		return casa.aplicarRegra(jogador, jogadores, escolha);
	}
	
	public int obterEscolhaSurpresa(Scanner teclado, Jogador jogador) {
		int escolha;
		do {
			System.out.println("Jogador " + jogador.getCor() + ", escolha a carta surpresa (1, 2, 3):");
			escolha = teclado.nextInt();
		} while (escolha < 1 || escolha > 3);
		return escolha;
	}
	
	public void validarJogadorPresente(Jogador jogador) {
		if (!jogadores.contains(jogador)) {
			throw new IllegalStateException("Jogador " + jogador.getCor() + " não encontrado!");
		}
	}
	
	public EfeitoDaCasa processarCasaReversa(CasaReversa casa, Jogador jogador) {
		int indiceUltimo = encontrarIndiceUltimoJogador(jogador);
		if (indiceUltimo == -1) {
			return new EfeitoDaCasa("Nenhum outro jogador para trocar posições!");
		}
		return casa.aplicarRegra(jogador, jogadores, indiceUltimo);
	}
	
	
	public int encontrarIndiceUltimoJogador(Jogador jogadorAtual) {
		int indiceUltimo = -1;
		int menorPosicao = Integer.MAX_VALUE;
		for (int i = 0; i < jogadores.size(); i++) {
			Jogador jogador = jogadores.get(i);
			if (jogador != jogadorAtual && jogador.getPosicaoTabuleiro() < menorPosicao) {
				menorPosicao = jogador.getPosicaoTabuleiro();
				indiceUltimo = i;
			}
		}
		return indiceUltimo;
	}
	
	private List<Jogador> obterAdversarios(Jogador jogadorAtual) {
		List<Jogador> adversarios = new ArrayList<>();
		for (Jogador j : jogadores) {
			if (j != jogadorAtual) {
				adversarios.add(j);
			}
		}
		return adversarios;
	}
	
	private void exibirOpcoesAdversarios(List<Jogador> adversarios) {
		for (int i = 0; i < adversarios.size(); i++) {
			System.out.println((i + 1) + " - Jogador " + adversarios.get(i).getCor() +
					" (posição: " + adversarios.get(i).getPosicaoTabuleiro() + ")");
		}
	}
	
	private int obterEscolhaValida(Scanner teclado, int maxOpcoes) {
		int escolha;
		do {
			System.out.print("Escolha o número do adversário que voltará para a casa 0: ");
			escolha = teclado.nextInt();
		} while (escolha < 1 || escolha > maxOpcoes);
		return escolha;
	}
	
	public boolean jogarRodadaDebug(Scanner teclado) {
		System.out.println("\n========= RODADA " + numeroRodada + " =========");
		for (int i = 0; i < jogadores.size(); i++) {
			System.out.println("<------------------------------------------------->");
			Jogador jogador = jogadores.get(i);
			System.out.println("É a vez do jogador " + jogador.getCor() + ", ele está na posição: " + jogador.getPosicaoTabuleiro());
			
			int novaPosicao;
			do {
				System.out.print("Digite o número da casa que o jogador deve ir: ");
				novaPosicao = teclado.nextInt();
				if (novaPosicao < jogador.getPosicaoTabuleiro()) {
					System.out.println("Não é permitido voltar casas. Tente novamente.");
				}
			} while (novaPosicao < jogador.getPosicaoTabuleiro());
			
			int posicaoAntes = jogador.getPosicaoTabuleiro();
			jogador.setPosicaoTabuleiro(novaPosicao);
			int avancou = novaPosicao - posicaoAntes;
			jogador.setJogadas(jogador.getJogadas() + 1);
			
			System.out.println("Jogador " + jogador.getCor() +
					" está na rodada " + jogador.getJogadas() +
					", avançou " + avancou +
					" casas e está agora na posição " + novaPosicao + ".");
			
			verificarCasaEspecial(jogador);
			if (verificarGanhador()) return true;
		}
		numeroRodada++;
		return false;
	}
}
