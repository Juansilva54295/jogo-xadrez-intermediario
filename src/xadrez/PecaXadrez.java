package xadrez;

import tabuleiro.jogo.Peca;
import tabuleiro.jogo.Posicao;
import tabuleiro.jogo.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Cor cor;
	
	public PecaXadrez(Tabuleiro tabuleiro,Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public PosiXadrez getXdPosiXadrez() {
		return PosiXadrez.aPosicao(posicao);
	}
	
	protected boolean existePecaAd ( Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}

	


}
