package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.PosiXadrez;
import xadrez.XadException;

public class Jogo {

	public static void main(String[] args) {


		Scanner scn = new Scanner(System.in);
		Partida partida = new Partida();
		
		while(true) {
			
		try {	
			UI.clearScreen();	
			UI.printTabuleiro(partida.getPecas());
			System.out.println();
			System.out.println("POSIÇÃO INICIAL: ");
			PosiXadrez inicio = UI.lerPosiXadrez(scn);
			
			System.out.println("POSIÇÃO FINAL: ");
			PosiXadrez finaly = UI.lerPosiXadrez(scn);
		
			PecaXadrez capturarPeca = partida.movimentarPeca(inicio, finaly);
		}catch(XadException e) {
			System.out.println(e.getMessage());
			scn.nextLine();
		}
		catch(InputMismatchException e) {
			System.out.println(e.getMessage());
			scn.nextLine();
		}
		}
	}

}
