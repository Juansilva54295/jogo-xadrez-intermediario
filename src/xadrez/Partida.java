package xadrez;

import java.util.ArrayList;
import java.util.List;

import tabuleiro.jogo.Peca;
import tabuleiro.jogo.Posicao;
import tabuleiro.jogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class Partida {

	
	private int turno;
	private Cor vezdoAd;
	private Tabuleiro tabuleiro;
	
	private List<Peca> pecaNoTabuleiro = new ArrayList<>();
	private List<Peca> pecascapturadas = new ArrayList<>();
	
	public Partida() {
		tabuleiro = new Tabuleiro(8,8);
		turno = 1;
		vezdoAd = Cor.WHITE;
		inicio();
	}
	
	
	
	public int getTurno() {
		return turno;
	}



	public Cor getVezdoAd() {
		return vezdoAd;
	}



	public PecaXadrez [][] getPecas(){
		 PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColuna()];
		 for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			 for (int j = 0; j < tabuleiro.getColuna();j++) {
				 mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			 }
		 }return mat;
	} 
	
	
	public boolean [][] movimentoPossiveis(PosiXadrez posiInicial){
		Posicao posicao = posiInicial.posicaoXadrez(null);
		validarInicio(posicao);
		return tabuleiro.peca(posicao).movimentosPosiveis();
	}
	
	public PecaXadrez movimentarPeca(PosiXadrez posiInicial, PosiXadrez posiFinal) {
		Posicao inicio = posiInicial.posicaoXadrez(null);
		Posicao finaly = posiFinal.posicaoXadrez(null);
		validarInicio(inicio);
		validarFinal(inicio,finaly);
		Peca capturarPeca = fazerMovimento(inicio,finaly);
		proxTurno();
		return (PecaXadrez)capturarPeca;
	}
	
	private Peca fazerMovimento (Posicao inicio, Posicao finaly){
		Peca p = tabuleiro.removePeca(inicio);
		Peca captuararPeca = tabuleiro.removePeca(finaly);
		tabuleiro.lugarPeca(p, finaly);
		
		if (captuararPeca != null) {
		    pecaNoTabuleiro.remove(captuararPeca);  
		    pecascapturadas.add(captuararPeca);   
		}

		return captuararPeca;
	}
	
	private void validarInicio (Posicao posicao) {
		if (!tabuleiro.haumaPeca(posicao)) {
			throw new XadException("NÃO EXISTE PEÇA NESSA POSIÇÃO");
		}
		if (vezdoAd != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new XadException("A PEÇA ESCOLHIDA NÃO É SUA");
		}
		
		if(!tabuleiro.peca(posicao).peloMenosumaPosi()) {
			throw new XadException("NÃO EXISTE MOVIMENTO POSSIVEL PARA ESTÁ "
					+ "PEÇA");
		}
		
	}

	private void proxTurno () {
		turno++;
		vezdoAd = (vezdoAd == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	private void validarFinal(Posicao inicio,Posicao finaly) {
		if (!tabuleiro.peca(inicio).movimentoPossivel(finaly)) {
			throw new XadException("A PEÇA NÃO PODE SER MOVIDA PARA A POSIÇÃO DE DESTINO");
		}
		
		
	}
	private void novolugarPeca(char coluna,int linha,PecaXadrez peca) {
		tabuleiro.lugarPeca(peca, new PosiXadrez(coluna, linha).posicaoXadrez(null));
		pecaNoTabuleiro.add(peca);
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
