package aplicacao.pecas;

public class Rainha extends Peca{

    public Rainha(boolean cor, Pos posicao, char simbolo) {
        super(cor, simbolo, posicao);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino) {
        if (estaNosLimites(destino)) {
            if(!(destino.getX() == posicaoAtual.getX() && destino.getY() == posicaoAtual.getY())) { //nao eh a mesma casa

                // vertical e horizontal
                if(destino.getX() == posicaoAtual.getX() || destino.getY() == posicaoAtual.getY()) {
                    if(casaValida(destino) && pecaNaFrenteLinhaReta(destino) == false) {
                        return true;
                    }
                }

                // diagonal
                if(Math.abs(destino.getX() - posicaoAtual.getX()) == Math.abs(destino.getX() - posicaoAtual.getX())) {
                    if(casaValida(destino) && pecaNaFrenteDiagonal(destino) == false) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
