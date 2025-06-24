package src.main;

import src.main.visao.TabuleiroConsole;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		TabuleiroConsole tabuleiroConsole = new TabuleiroConsole();
		
		System.out.println("Quantas casas voce deseja ter no seu tabuleiro? ");
		int qtdCasas = teclado.nextInt();
		
		tabuleiroConsole.montarTabuleiro(qtdCasas);
		tabuleiroConsole.jogar();
		
	}
}
