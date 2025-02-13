package xadrez;

import tabuleiro.jogo.Posicao;
import tabuleiro.jogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class Partida {

	private Tabuleiro tabuleiro;
	
	public Partida() {
		tabuleiro = new Tabuleiro(8,8);
		inicio();
	}
	
	public PecaXadrez [][] getPecas(){
		 PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColuna()];
		 for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			 for (int j = 0; j < tabuleiro.getColuna();j++) {
				 mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			 }
		 }return mat;
	} 
	private void inicio() {
		tabuleiro.lugarPeca(new Torre(tabuleiro, Cor.WHITE), new Posicao(5, 1));
		tabuleiro.lugarPeca(new Rei(tabuleiro, Cor.BLACK), new Posicao(7, 4));
		tabuleiro.lugarPeca(new Rei(tabuleiro, Cor.BLACK), new Posicao(4, 4));
	}
	
}
