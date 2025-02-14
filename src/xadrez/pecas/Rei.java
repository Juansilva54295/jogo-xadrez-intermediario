package xadrez.pecas;

import tabuleiro.jogo.Posicao;
import tabuleiro.jogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean podemovimentar(Posicao posicao) {
		PecaXadrez p = ( PecaXadrez)getTabuleiro().peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] movimentosPosiveis() {
		boolean [][] mat = new boolean [getTabuleiro().getLinhas()][getTabuleiro().getColuna()];
		Posicao p = new Posicao(0, 0);

		// acima

		p.setValues(posicao.getLinha() - 1, posicao.getColuna());
		if (getTabuleiro().posiexistente(p) && !getTabuleiro().haumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// abaixo
		
		p.setValues(posicao.getLinha() + 1, posicao.getColuna());
		if (getTabuleiro().posiexistente(p) && !getTabuleiro().haumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// esquerda
		
		p.setValues(posicao.getLinha(), posicao.getColuna() - 1);
		if (getTabuleiro().posiexistente(p) && !getTabuleiro().haumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// direita
		
		p.setValues(posicao.getLinha(), posicao.getColuna() + 1);
		if (getTabuleiro().posiexistente(p) && !getTabuleiro().haumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// noroeste
		
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if (getTabuleiro().posiexistente(p) && !getTabuleiro().haumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// nordeste
		
		p.setValues(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if (getTabuleiro().posiexistente(p) && !getTabuleiro().haumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// sudoeste
		
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if (getTabuleiro().posiexistente(p) && !getTabuleiro().haumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		
		// sudeste
		
		p.setValues(posicao.getLinha() + 1, posicao.getColuna() + 1);
		if (getTabuleiro().posiexistente(p) && !getTabuleiro().haumaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}

		return mat;
	}

}
