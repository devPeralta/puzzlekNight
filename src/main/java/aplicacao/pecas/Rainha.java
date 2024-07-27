package aplicacao.pecas;

public class Rainha extends Peca{

    public Rainha(boolean cor, Pos posicao, char simbolo) {
        super(cor, simbolo, posicao);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino, Peca[][] tabuleiro) {
        if (estaNosLimites(destino)) {
            if(!(destino.getX() == posicaoAtual.getX() && destino.getY() == posicaoAtual.getY())) { //nao eh a mesma casa

                // vertical e horizontal
                if(destino.getX() == posicaoAtual.getX() || destino.getY() == posicaoAtual.getY()) {
                    if(casaValida(destino, tabuleiro) && pecaNaFrenteLinhaReta(destino, tabuleiro) == false) {
                        return true;
                    }
                }

                // diagonal
                if(Math.abs(destino.getX() - posicaoAtual.getX()) == Math.abs(destino.getX() - posicaoAtual.getX())) {
                    if(casaValida(destino, tabuleiro) && pecaNaFrenteDiagonal(destino, tabuleiro) == false) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
