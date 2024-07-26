package aplicacao.pecas;

public class Cavalo extends Peca{

    public Cavalo(boolean cor, Pos posicao, char simbolo) {
        super(cor, simbolo, posicao);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino) {
        if(estaNosLimites(destino)) {
            // para o movimento valido, os resultado vao ser 2:1 ou 1:2
            if(Math.abs(destino.getX() - posicaoAtual.getX()) * Math.abs(destino.getX() - posicaoAtual.getX()) == 2) {
                if(casaValida(destino)) {
                    return true;
                }
            }
        }
        return false;
    }
}
