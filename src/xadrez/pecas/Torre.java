package xadrez.pecas;

import tabuleiro.jogo.Posicao;
import tabuleiro.jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "T";
	}

	@Override
	public boolean[][] movimentosPosiveis() {
		boolean [][] mat = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColuna()];
		Posicao p = new Posicao(0, 0);
		
		// acima
		
		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		while(getTabuleiro().posiexistente(p)&& !getTabuleiro().haumaPeca(p)){
			mat [p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if(getTabuleiro().posiexistente(p) && existePecaAd(p) ) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// esquerda
		
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		while(getTabuleiro().posiexistente(p)&& !getTabuleiro().haumaPeca(p)){
			mat [p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if(getTabuleiro().posiexistente(p) && existePecaAd(p) ) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// direita
		
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		while(getTabuleiro().posiexistente(p)&& !getTabuleiro().haumaPeca(p)){
			mat [p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if(getTabuleiro().posiexistente(p) && existePecaAd(p) ) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// abaixo
		
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		while(getTabuleiro().posiexistente(p)&& !getTabuleiro().haumaPeca(p)){
			mat [p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getTabuleiro().posiexistente(p) && existePecaAd(p) ) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		return mat;
	}

	
	
	

}
