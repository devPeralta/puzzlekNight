package aplicacao.pecas;

public class Torre extends Peca{

    public Torre(boolean cor, Pos posicao, char simbolo) {
        super(cor, simbolo, posicao);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino, Peca[][] tabuleiro) {
        // pode se mover para qualquer lugar desde que coluna ou linha seja a mesma, de que os dois nao sejam igual(mesma casa)
        if (estaNosLimites(destino)) {
            if(!(destino.getX() == posicaoAtual.getX() && destino.getY() == posicaoAtual.getY())) {
                if (destino.getX() == posicaoAtual.getX() || destino.getY() == posicaoAtual.getY()) {
                    if(casaValida(destino, tabuleiro) && pecaNaFrenteLinhaReta(destino, tabuleiro) == false) {
                        System.out.println("casaValida" + casaValida(destino, tabuleiro));
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

