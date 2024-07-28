package aplicacao.pecas;

public class Peca {
    private boolean cor; // 0 = branco, 1 = preto
    private char simbolo; // 'P' ou 'p' = peão 'R' ou 'r' = torre, 'K' ou 'k' = cavalo, 'B' ou 'b' = bispo, 'Q' ou 'q' = rainha, 'K' ou 'k' = rei
    private Pos posicao;
    private static final int DIMENSAO = 8;
    //private Peca tabuleiro[][];

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

    public boolean getCor(){
        return this.cor;
    }

    public void setSimbolo(char simbolo) {
        this.simbolo = simbolo;
    }

    public void setPosicao(Pos posicao) {
        this.posicao = posicao;
    }

    public boolean testaMovimento (Pos posicaoAtual, Pos destino, Peca[][] tabuleiro) {
        return false;
    }

    public boolean estaNosLimites(Pos destino) {
        if (destino.getX() >= 0 && destino.getX() < DIMENSAO) {
            if (destino.getY() >= 0 && destino.getY() < DIMENSAO) {
                return true;
            }
        }
        return false;
    }

    public Peca pecaAtingida(Pos destino, Peca[][] tabuleiro) {
        for(int i=0; i < DIMENSAO; i++) {
            for (int j=0; j < DIMENSAO; j++) {
                if(tabuleiro[i][j] != null){
                    if(tabuleiro[i][j].posicao.getX() == destino.getX() && tabuleiro[i][j].posicao.getY() == destino.getY() && tabuleiro[i][j] != this) {
                        return tabuleiro[i][j];
                    }
                }
            }
        }
        return null;
    }

    public boolean casaValida(Pos destino, Peca[][] tabuleiro) {
        Peca pecaAtingida = pecaAtingida(destino, tabuleiro);

        if(pecaAtingida == null) { // casa nao ocupada por nenhuma peca
            return true;
        }
        else if(this.cor != pecaAtingida.cor) { //peca eh de cor diferente, entao pode capturar
            return true;
        }
        return false;
    }

    public boolean pecaNaFrenteLinhaReta(Pos destino, Peca[][] tabuleiro) {
        // movendo p/ esquerda
        for(int c = posicao.getY()-1; c > destino.getY(); c--){
            for (int i = 0; i < DIMENSAO; i++) {
                for (int j = 0; j < DIMENSAO; j++) {
                    if(tabuleiro[i][j] != null) {
                        if(tabuleiro[i][j].posicao.getY() == c && tabuleiro[i][j].posicao.getX() == destino.getX()) {
                            return true;
                        }
                    }
                }
            }
        }

        // movendo p/ direita
        for(int c = posicao.getY() +1; c < destino.getY(); c++){
            for (int i = 0; i < DIMENSAO; i++) {
                for (int j = 0; j < DIMENSAO; j++) {
                    if(tabuleiro[i][j] != null) {
                        if(tabuleiro[i][j].posicao.getY() == c && tabuleiro[i][j].posicao.getX() == destino.getX()) {
                            return true;
                        }
                    }
                }
            }
        }

        // movendo p/ cima
        for(int r = posicao.getX()-1; r > destino.getX(); r--){
            for (int i = 0; i < DIMENSAO; i++) {
                for (int j = 0; j < DIMENSAO; j++) {
                    if(tabuleiro[i][j] != null) {
                        if(tabuleiro[i][j].posicao.getY() == destino.getY() && tabuleiro[i][j].posicao.getX() == r) {
                            return true;
                        }
                    }
                }
            }
        }

        // movendo p/ baixo
        for(int r = posicao.getX()+1; r < destino.getX(); r++){
            for (int i = 0; i < DIMENSAO; i++) {
                for (int j = 0; j < DIMENSAO; j++) {
                    if(tabuleiro[i][j] != null) {
                        if(tabuleiro[i][j].posicao.getY() == destino.getY() && tabuleiro[i][j].posicao.getX() == r) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean pecaNaFrenteDiagonal(Pos destino, Peca[][] tabuleiro) {

        if (destino.getX() < posicao.getX()) {
            // cima e esquerda
            for (int c = posicao.getY() - 1; c > destino.getY(); c--) {
                int dif = Math.abs(c - posicao.getY());
                for (int i = 0; i < DIMENSAO; i++) {
                    for (int j = 0; j < DIMENSAO; j++) {
                        if (tabuleiro[i][j].getPosicao().getY() == c && tabuleiro[i][j].getPosicao().getX() == posicao.getX() - dif) {
                            return true;
                        }
                    }
                }
            }

            // cima e direita
            for (int c = posicao.getY() + 1; c < destino.getY(); c++) {
                int dif = Math.abs(c - posicao.getY());
                for (int i = 0; i < DIMENSAO; i++) {
                    for (int j = 0; j < DIMENSAO; j++) {
                        if (tabuleiro[i][j].getPosicao().getY() == c && tabuleiro[i][j].getPosicao().getX() == posicao.getX() - dif) {
                            return true;
                        }
                    }
                }
            }
        }

        if (destino.getX() > posicao.getX()) {
            // baixo e esquerda
            for (int c = posicao.getY() - 1; c > destino.getY(); c--) {
                int dif = Math.abs(c - posicao.getY());
                for (int i = 0; i < DIMENSAO; i++) {
                    for (int j = 0; j < DIMENSAO; j++) {
                        if (tabuleiro[i][j].getPosicao().getY() == c && tabuleiro[i][j].getPosicao().getX() == posicao.getX() + dif) {
                            return true;
                        }
                    }
                }
            }

            // baixo e direita
            for (int c = posicao.getY() + 1; c < destino.getY(); c++) {
                int dif = Math.abs(c - posicao.getY());
                for (int i = 0; i < DIMENSAO; i++) {
                    for (int j = 0; j < DIMENSAO; j++) {
                        if (tabuleiro[i][j].getPosicao().getY() == c && tabuleiro[i][j].getPosicao().getX() == posicao.getX() + dif) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    boolean caminhoLivreDiagonal(Pos destino, Peca[][] tabuleiro) {
        int dx = Math.abs(destino.getX() - posicao.getX());
        int dy = Math.abs(destino.getY() - posicao.getY());
        if (dx != dy) return false; // Movimento inválido

        int stepX = (destino.getX() > posicao.getX()) ? 1 : -1;
        int stepY = (destino.getY() > posicao.getY()) ? 1 : -1;
        for (int x = posicao.getX() + stepX, y = posicao.getY() + stepY; x != destino.getX(); x += stepX, y += stepY) {
            if (tabuleiro[x][y] != null) {
                return false; // Há uma peça no caminho
            }
        }
        return true; // Caminho está livre
    }

}