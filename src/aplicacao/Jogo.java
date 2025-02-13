package aplicacao;

import xadrezcor.Partida;

public class Jogo {

	public static void main(String[] args) {


		Partida partida = new Partida();
		UI.printTabuleiro(partida.getPecas());
	}

}
