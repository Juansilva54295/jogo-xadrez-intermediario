package xadrez;

import tabuleiro.jogo.TabException;

public class XadException extends TabException{
	private static final long serialVersionUID = 1L;

	public XadException(String msg) {
		super(msg);
	}
}
