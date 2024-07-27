package aplicacao;

import aplicacao.pecas.Peca;
import aplicacao.pecas.Pos;

public class Jogada {
    Peca peca;
    Pos posDest;
    boolean movComputador;

    public Jogada(Peca peca, Pos posDest, boolean movComputador) {
        this.peca = peca;
        this.posDest = posDest;
        this.movComputador = movComputador;
    }
}
