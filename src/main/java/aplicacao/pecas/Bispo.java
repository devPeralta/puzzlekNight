package aplicacao.pecas;

public class Bispo extends Peca{

    public Bispo(boolean cor, Pos posicao, char simbolo) {
        super(cor, simbolo, posicao);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino, Peca[][] tabuleiro) {
        if (estaNosLimites(destino)) {
            if (!(destino.getX() == posicaoAtual.getX() && destino.getY() == posicaoAtual.getY())) {
                // para mover nas diagonais, as diferenças vao ser iguais
                if (Math.abs(destino.getX() - posicaoAtual.getX()) == Math.abs(destino.getY() - posicaoAtual.getY())) {
                    if (casaValida(destino, tabuleiro) && caminhoLivreDiagonal(destino,tabuleiro)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
