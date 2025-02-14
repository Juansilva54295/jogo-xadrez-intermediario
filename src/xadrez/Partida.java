package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.jogo.Peca;
import tabuleiro.jogo.Posicao;
import tabuleiro.jogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class Partida {

	
	private int turno;
	private Cor vezdoAd;
	private Tabuleiro tabuleiro;
	private boolean check;
	
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
	
	public boolean getCheck() {
		return check;
	}



	public PecaXadrez [][] getPecas(){
		 PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColuna()];
		 for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			 for (int j = 0; j < tabuleiro.getColuna();j++) {
				 mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
			 }
		 }return mat;
	} 
	
	
	public boolean [][] movimentoPossiveis(PosiXadrez posiInicial) {
	    Posicao posicao = posiInicial.posicaoXadrez(); // Sem argumento agora
	    validarInicio(posicao);
	    return tabuleiro.peca(posicao).movimentosPosiveis();
	}

	
	public PecaXadrez movimentarPeca(PosiXadrez posiInicial, PosiXadrez posiFinal) {
	    Posicao inicio = posiInicial.posicaoXadrez();
	    Posicao finaly = posiFinal.posicaoXadrez();
	    validarInicio(inicio);
	    validarFinal(inicio, finaly);
	    Peca capturarPeca = fazerMovimento(inicio, finaly);
	    
	    if (testeCheck(vezdoAd)) {
	        desfazerMovimento(inicio, finaly, capturarPeca);
	        throw new XadException("VC NÃO PODE SE COLOCAR EM CHECK");
	    }
	    
	    check = testeCheck(oponente(vezdoAd));
	    proxTurno();
	    return (PecaXadrez) capturarPeca;
	}
	
	private Peca fazerMovimento (Posicao inicio, Posicao finaly){
		Peca p = tabuleiro.removePeca(inicio);
		Peca capturarPeca = tabuleiro.removePeca(finaly);
		tabuleiro.lugarPeca(p, finaly);
		
		if (capturarPeca != null) {
		    pecaNoTabuleiro.remove(capturarPeca);  
		    pecascapturadas.add(capturarPeca);   
		}

		return capturarPeca;
	}
	
	private void desfazerMovimento(Posicao inicio,Posicao finaly, Peca capturarPeca) {
		Peca p = tabuleiro.removePeca(finaly);
		tabuleiro.lugarPeca(p, inicio);

		if (capturarPeca != null) {
            tabuleiro.lugarPeca(capturarPeca, finaly);
            pecascapturadas.remove(capturarPeca);
            pecaNoTabuleiro.add(capturarPeca);
		}
		
	
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
	
	private Cor oponente (Cor cor ) {
		return (cor == Cor.WHITE) ? Cor.BLACK : Cor.WHITE;
	}
	
	private PecaXadrez rei(Cor cor ) {
		List<Peca> list = pecaNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : list) {
        	if(p instanceof Rei) {
        		return (PecaXadrez)p;
        	}
        }
        throw new IllegalStateException("NÃO EXISTE REI DA COR " + cor + "NO TABULEIRO" );
	}
	
	private boolean testeCheck(Cor cor) {
	    // Obtém a posição do rei no formato PosiXadrez
	    PosiXadrez posiRei = rei(cor).getXdPosiXadrez();
	    
	    // Converte para a posição interna do tabuleiro
	    Posicao reiPosicaoCorrigida = posiRei.posicaoXadrez();

	    // Filtra as peças do oponente
	    List<Peca> pecaOponente = pecaNoTabuleiro.stream()
	        .filter(x -> ((PecaXadrez) x).getCor() == oponente(cor))
	        .collect(Collectors.toList());

	    // Verifica se alguma peça pode atacar o rei
	    for (Peca p : pecaOponente) {
	        boolean[][] mat = p.movimentosPosiveis();
	        if (mat[reiPosicaoCorrigida.getLinha()][reiPosicaoCorrigida.getColuna()]) {
	            return true;
	        }
	    }
	    return false;
	}


	
	
	private void novolugarPeca(char coluna, int linha, PecaXadrez peca) {
	    tabuleiro.lugarPeca(peca, new PosiXadrez(coluna, linha).posicaoXadrez());
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
