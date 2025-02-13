package xadrez;

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
	
	private void novolugarPeca(char coluna,int linha,PecaXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosiXadrez(coluna, linha).posicaoXadrez(null));
	}
	
	
	
	private void inicio() {
		novolugarPeca('b',6,new Torre(tabuleiro, Cor.WHITE));
		novolugarPeca('c',5,new Rei(tabuleiro, Cor.BLACK));
		novolugarPeca('d',2,new Rei(tabuleiro, Cor.BLACK));
	}
	
}
