package aplicacao;

import java.util.Scanner;

import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.PosiXadrez;

public class Jogo {

	public static void main(String[] args) {


		Scanner scn = new Scanner(System.in);
		Partida partida = new Partida();
		
		while(true) {
		UI.printTabuleiro(partida.getPecas());
		System.out.println();
		System.out.println("POSIÇÃO INICIAL: ");
		PosiXadrez inicio = UI.lerPosiXadrez(scn);
		
		System.out.println("POSIÇÃO FINAL: ");
		PosiXadrez finaly = UI.lerPosiXadrez(scn);
		
		PecaXadrez capturarPeca = partida.movimentarPeca(inicio, finaly);
		}
	}

}
