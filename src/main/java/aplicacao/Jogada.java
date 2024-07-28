package aplicacao;

import aplicacao.pecas.Peca;
import aplicacao.pecas.Pos;

public class Jogada {
    //obs: atributo peça foi mudada para char pois aqui não faz sentido ser do tipo peça
    private char peca;
    private Pos posDest;
    private boolean movComputador;
    //atributos novos:
    private boolean captura;
    private boolean xeque;

    //
    public Jogada(char peca, Pos posDest, boolean movComputador, boolean captura, boolean xeque) {
        this.peca = peca;
        this.posDest = posDest;
        this.movComputador = movComputador;
        this.captura = captura;
        this.xeque = xeque;
    }

    @Override
    public String toString() {
        return
                "peca=" + this.peca +
                ", posDest=(" + this.posDest.getX() + "," + this.posDest.getY() + ")" +
                ", movComputador=" + this.movComputador +
                ", captura=" + this.captura +
                ", xeque=" + this.xeque +
                '}';
    }
}
