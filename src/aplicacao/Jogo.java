package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.Partida;
import xadrez.PecaXadrez;
import xadrez.PosiXadrez;
import xadrez.XadException;

public class Jogo {

	public static void main(String[] args) {


		Scanner scn = new Scanner(System.in);
		Partida partida = new Partida();
		List<PecaXadrez> capturada = new ArrayList<>();
		
		while(true) {
			
		try {	
			UI.clearScreen();	
			UI.printPartida(partida,capturada); 
			System.out.println();
			System.out.println("POSIÇÃO INICIAL: ");
			PosiXadrez inicio = UI.lerPosiXadrez(scn);
			
			boolean [][] movimentoPossiveis = partida.movimentoPossiveis(inicio);
			UI.clearScreen();
			UI.printTabuleiro(partida.getPecas(),movimentoPossiveis);
			
			System.out.println("POSIÇÃO FINAL: ");
			PosiXadrez finaly = UI.lerPosiXadrez(scn);
		
			PecaXadrez capturarPeca = partida.movimentarPeca(inicio, finaly);
			
			if(capturarPeca != null) {
				capturada.add(capturarPeca);
			}
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
