package xadrez;

import tabuleiro.jogo.Peca;
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
	


}
