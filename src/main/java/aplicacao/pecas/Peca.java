package aplicacao.pecas;

public class Peca {
    private boolean cor; // 0 = branco, 1 = preto
    private char simbolo; // 'P' ou 'p' = peão 'R' ou 'r' = torre, 'K' ou 'k' = cavalo, 'B' ou 'b' = bispo, 'Q' ou 'q' = rainha, 'K' ou 'k' = rei
    private Pos posicao;
    private static final int DIMENSAO = 8;
    private Peca tabuleiro[][];

    public Peca(boolean cor, char simbolo, Pos posicao) {
        this.cor = cor;
        this.simbolo = simbolo;
        this.posicao = posicao;
    }

    public boolean isCor() {
        return cor;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public Pos getPosicao() {
        return posicao;
    }

    public void setCor(boolean cor) {
        this.cor = cor;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public void setPosicao(Pos posicao) {
        this.posicao = posicao;
    }

    public boolean testaMovimento (Pos posicaoAtual, Pos destino) {
        return false;
    }
    
    public boolean estaNosLimites(Pos destino) {
        if (destino.getX() >= 0 && destino.getX() <= DIMENSAO) {
            if (destino.getY() >= 0 && destino.getY() <= DIMENSAO) {
                return true;
            }
        }
        return false;
    }

    public Peca pecaAtingida(Pos destino) {
        for(int i=0; i < DIMENSAO; i++) {
            for (int j=0; j < DIMENSAO; j++) {
                if(tabuleiro[i][j].posicao.getX() == destino.getX() && tabuleiro[i][j].posicao.getY() == destino.getY() && tabuleiro[i][j] != this) {
                    return tabuleiro[i][j];
                }
            }
        }
        return null;
    }

    public boolean casaValida(Pos destino) {
        Peca pecaAtingida = pecaAtingida(destino);

        if(pecaAtingida == null) { // casa nao ocupada por nenhuma peca
            return true;
        }
        else if(this.cor =! pecaAtingida.cor) { //peca eh de cor diferente, entao pode capturar
            return true;
        }
        return false;
    }

    public boolean pecaNaFrenteLinhaReta(Pos destino) {
        // movendo p/ esquerda
        for(int c = posicao.getY()-1; c > destino.getY(); c--){
            for (int i = 0; i < DIMENSAO; i++) {
                for (int j = 0; j < DIMENSAO; i++) {
                    if(tabuleiro[i][j].posicao.getY() == c && tabuleiro[i][j].posicao.getX() == destino.getX()) {
                        return true;
                    }
                }
            }
        }

        // movendo p/ direita
        for(int c = posicao.getY() +1; c < destino.getY(); c++){
            for (int i = 0; i < DIMENSAO; i++) {
                for (int j = 0; j < DIMENSAO; i++) {
                    if(tabuleiro[i][j].posicao.getY() == c && tabuleiro[i][j].posicao.getX() == destino.getX()) {
                        return true;
                    }
                }
            }
        }

        // movendo p/ cima
        for(int r = posicao.getX()-1; r > destino.getX(); r--){
            for (int i = 0; i < DIMENSAO; i++) {
                for (int j = 0; j < DIMENSAO; i++) {
                    if(tabuleiro[i][j].posicao.getY() == destino.getY() && tabuleiro[i][j].posicao.getX() == r) {
                        return true;
                    }
                }
            }
        }

        // movendo p/ baixo
        for(int r = posicao.getX()+1; r < destino.getX(); r++){
            for (int i = 0; i < DIMENSAO; i++) {
                for (int j = 0; j < DIMENSAO; i++) {
                    if(tabuleiro[i][j].posicao.getY() == destino.getY() && tabuleiro[i][j].posicao.getX() == r) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
