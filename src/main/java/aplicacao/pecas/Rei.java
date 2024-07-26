package aplicacao.pecas;

public class Rei extends Peca{

    public Rei(boolean cor, Pos posicao, char simbolo) {
        super(cor, simbolo, posicao);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino) {
        if(estaNosLimites(destino)) {
            if(Math.abs(destino.getX() - posicaoAtual.getX()) + Math.abs(destino.getX() - posicaoAtual.getX()) == 1 ||      // em linha reta
                    Math.abs(destino.getX() - posicaoAtual.getX()) * Math.abs(destino.getX() - posicaoAtual.getX()) == 1) { // diagonal
                if(casaValida(destino)){
                    return true;
                }
            }
        }
        return false;
    }
}
