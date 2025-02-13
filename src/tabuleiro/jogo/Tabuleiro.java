package tabuleiro.jogo;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca [][] pecas;
	
	public Tabuleiro(int linhas, int coluna) {
		if (linhas < 1 || coluna < 1) {
			throw new TabException("ERRO AO CRIAR TABULEIRO: TABULEIRO DEVE CONTER PELO MENOS UMA COLUNA E UMA LINHA "); 
		}
		this.linhas = linhas;
		this.colunas = coluna;
		pecas = new Peca [linhas][coluna];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColuna() {
		return colunas;
	}

	public Peca peca (int linhas,int colunas) {
		return pecas [linhas][colunas];
	}
	
	public Peca peca (Posicao posicao) {
		return pecas [posicao.getLinha()][posicao.getColuna()];
	}
	
	public void lugarPeca(Peca peca, Posicao posicao) {
			if (haumaPeca(posicao)) {
				throw new TabException("HÁ UMA PEÇA JÁ EXISTENTE NESSA POSIÇÃO " + posicao);
			}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	private boolean posiexistente(int linha,int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean posiexistente (Posicao posicao) {
		return posiexistente(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean haumaPeca(Posicao posicao) {
		if (!posiexistente(posicao)) {
			throw new TabException("POSIÇÃO NÃO EXISTENTE NO TABULEIRO");
		}
		return peca(posicao) != null;
	}
}
