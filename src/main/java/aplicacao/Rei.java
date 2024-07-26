package pecas;

public class Rei extends Peca{

    public Rei(boolean cor, Pos posicao, char simbolo) {
        super(cor, posicao, simbolo);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino) {
        if(estaNosLimites(destino)) {
            if(Math.abs(destino.x - posicaoAtual.x) + Math.abs(destino.x - posicaoAtual.x) == 1 ||      // em linha reta
                    Math.abs(destino.x - posicaoAtual.x) * Math.abs(destino.x - posicaoAtual.x) == 1) { // diagonal
                if(casaValida(destino)){
                    return true;
                }
            }
        }
        return false;
    }
}
