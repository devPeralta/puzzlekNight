package aplicacao;

public class Peca {
    private boolean cor; // 0 = branco, 1 = preto
    private char simbolo; // 'P' ou 'p' = peão 'R' ou 'r' = torre, 'K' ou 'k' = cavalo, 'B' ou 'b' = bispo, 'Q' ou 'q' = rainha, 'K' ou 'k' = rei
    private Pos posicao;

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
}
