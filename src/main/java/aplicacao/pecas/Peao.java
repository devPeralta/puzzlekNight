package aplicacao.pecas;

public class Peao extends Peca {

    public boolean moveu = false;
    public Peao(boolean cor, Pos posicao, char simbolo) {
        super(cor, simbolo, posicao);
    }

    @Override
    public boolean testaMovimento (Pos posicaoAtual, Pos destino, Peca[][] tabuleiro) {

        if(estaNosLimites(destino)) {
            if(!(destino.getX() == posicaoAtual.getX() && destino.getY() == posicaoAtual.getY())) { //nao eh a mesma casa

                // define o movimento de acordo com a cor
                int valorMovimento;

                // branco
                if(isCor()) {
                    valorMovimento = -1;
                }
                // preto
                else {
                    valorMovimento = 1;
                }
                System.out.println("mov " + valorMovimento);

                Peca pecaAtingida = pecaAtingida(destino, tabuleiro);

                // movimento de uma casa
                if(destino.getY() == posicaoAtual.getY() && destino.getX() == posicaoAtual.getX() + valorMovimento && pecaAtingida == null) {
                    moveu = true;
                    return true;
                }

                // duas casas
                if(destino.getY() == posicaoAtual.getY() && destino.getX() == posicaoAtual.getX() + valorMovimento*2) {
                    if(moveu == false && pecaNaFrenteLinhaReta(destino, tabuleiro) == false) {
                        moveu = true;
                        return true;
                    }
                }

                // diagonal capturando uma peça
                if(Math.abs(destino.getY() - posicaoAtual.getY()) == 1 && destino.getX() == posicaoAtual.getX() + valorMovimento) {
                    if(pecaAtingida != null && pecaAtingida.isCor() != isCor()) {
                        moveu = true;
                        return true;
                    }
                }

            }
        }
        return false;
    }

}
