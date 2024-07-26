package pecas;

public class Torre extends Peca{

    public Torre(boolean cor, Pos posicao, char simbolo) {
        super(cor, posicao, simbolo);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino) {
        // pode se mover para qualquer lugar desde que coluna ou linha seja a mesma, de que os dois nao sejam igual(mesma casa)
        if (estaNosLimites(destino)) {
            if(!(destino.x == posicaoAtual.x && destino.y == posicaoAtual.y)) {
                if (destino.x == posicaoAtual.x || destino.y == posicaoAtual.y) {
                    if(casaValida(destino) && pecaNaFrenteLinhaReta(destino) == false) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
