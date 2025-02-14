package aplicacao;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import xadrez.Cor;
import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.PosiXadrez;

public class UI {
	
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static PosiXadrez lerPosiXadrez(Scanner scn) {
		String s = scn.nextLine().trim();
		if (s.length() != 2 || s.charAt(0) < 'a' || s.charAt(0) > 'h' || !Character.isDigit(s.charAt(1)))
			throw new InputMismatchException("ERRO: Entrada inválida. Use o formato 'a1' até 'h8'.");

		int linha = Character.getNumericValue(s.charAt(1));
		if (linha < 1 || linha > 8)
			throw new InputMismatchException("ERRO: A linha deve estar entre 1 e 8!");

		return new PosiXadrez(s.charAt(0), linha);
	}
	
	public static void printPartida(Partida partida, List<PecaXadrez> capturada) {
		printTabuleiro(partida.getPecas());
		System.out.println();
		printPecaCapturtada(capturada);
		System.out.println();
		System.out.println("TURNO: " + partida.getTurno());
		System.out.println("ESPERANDO O JOGADOR: " + partida.getVezdoAd());
		if(partida.getCheck()) {
			System.out.println("CHECK!");
		}
	}

	public static void printTabuleiro(PecaXadrez[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPeca(PecaXadrez peca,boolean fundo) {
		if(fundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (peca.getCor() == Cor.WHITE) {
				System.out.print(ANSI_WHITE + peca + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
	
	private static void printPecaCapturtada(List<PecaXadrez> capturada) {
		List<PecaXadrez> branca = capturada.stream().filter(x -> x.getCor() == Cor.WHITE).collect(Collectors.toList());
		List<PecaXadrez> preta = capturada.stream().filter(x -> x.getCor() == Cor.BLACK).collect(Collectors.toList());
		System.out.println("PEÇAS CAPTURADAS: ");
		System.out.print("PEÇAS BRANCAS: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(branca.toArray()));
		System.out.println(ANSI_RESET);
		System.out.print("PEÇAS PRETAS: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(preta.toArray()));
		System.out.println(ANSI_RESET);
		
	}
	
	public static void printTabuleiro(PecaXadrez[][] pecas, boolean [][] movimentoPossiveis) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				printPeca(pecas[i][j], movimentoPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

}
