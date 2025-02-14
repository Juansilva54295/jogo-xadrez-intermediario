package xadrez;

import tabuleiro.jogo.Posicao;

public class PosiXadrez {
	
	private char coluna;
	private int linha;
	
	public PosiXadrez(char coluna, int linha) {
		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadException("ERRO: AS POSIÇÕES SÃO SOMENTE DE a1 A h8!");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	protected Posicao posicaoXadrez() {
	    return new Posicao(8 - linha, coluna - 'a');
	}

	
	protected static PosiXadrez aPosicao(Posicao posicao) {
		if (posicao == null) {
			throw new IllegalArgumentException("A posição não pode ser nula.");
		}
		char coluna = (char) ('a' + posicao.getColuna());
		int linha = 8 - posicao.getLinha();

		if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new XadException("Posição inválida convertida: " + coluna + linha);
		}

		return new PosiXadrez(coluna, linha);
	}

	@Override
	public String toString() {
		return "" + coluna + linha;
	}
}
