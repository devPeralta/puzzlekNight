package pecas;

public class Cavalo extends Peca{

    public Cavalo(boolean cor, Pos posicao, char simbolo) {
        super(cor, posicao, simbolo);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino) {
        if(estaNosLimites(destino)) {
            // para o movimento valido, os resultado vao ser 2:1 ou 1:2
            if(Math.abs(destino.x - posicaoAtual.x) * Math.abs(destino.x - posicaoAtual.x) == 2) {
                if(casaValida(destino)) {
                    return true;
                }
            }
        }
        return false;
    }
}
