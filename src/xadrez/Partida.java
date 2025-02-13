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
		novolugarPeca('c', 1, new Torre(tabuleiro, Cor.WHITE));
        novolugarPeca('c', 2, new Torre(tabuleiro, Cor.WHITE));
        novolugarPeca('d', 2, new Torre(tabuleiro, Cor.WHITE));
        novolugarPeca('e', 2, new Torre(tabuleiro, Cor.WHITE));
        novolugarPeca('e', 1, new Torre(tabuleiro, Cor.WHITE));
        novolugarPeca('d', 1, new Rei(tabuleiro, Cor.WHITE));

        novolugarPeca('c', 7, new Torre(tabuleiro, Cor.BLACK));
        novolugarPeca('c', 8, new Torre(tabuleiro, Cor.BLACK));
        novolugarPeca('d', 7, new Torre(tabuleiro, Cor.BLACK));
        novolugarPeca('e', 7, new Torre(tabuleiro, Cor.BLACK));
        novolugarPeca('e', 8, new Torre(tabuleiro, Cor.BLACK));
        novolugarPeca('d', 8, new Rei(tabuleiro, Cor.BLACK));
	}
	
}
