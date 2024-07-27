package aplicacao.pecas;

public class Cavalo extends Peca{

    public Cavalo(boolean cor, Pos posicao, char simbolo) {
        super(cor, simbolo, posicao);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino, Peca[][] tabuleiro) {
        if(estaNosLimites(destino)) {
            // para o movimento valido, os resultado vao ser 2:1 ou 1:2
            if(Math.abs(destino.getX() - posicaoAtual.getX()) * Math.abs(destino.getY() - posicaoAtual.getY()) == 2) {
                if(casaValida(destino, tabuleiro)) {
                    return true;
                }
            }
        }
        return false;
    }
}
