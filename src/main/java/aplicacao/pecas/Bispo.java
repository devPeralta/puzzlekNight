package aplicacao.pecas;

public class Bispo extends Peca{

    public Bispo(boolean cor, Pos posicao, char simbolo) {
        super(cor, simbolo, posicao);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino) {
        if (estaNosLimites(destino)) {
            if (!(destino.getX() == posicaoAtual.getX() && destino.getY() == posicaoAtual.getY())) {
                // para mover nas diagonais, as diferenças vao ser iguais
                if (Math.abs(destino.getX() - posicaoAtual.getX()) == Math.abs(destino.getX() - posicaoAtual.getX())) {
                    if (casaValida(destino) && pecaNaFrenteDiagonal(destino) == false) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
