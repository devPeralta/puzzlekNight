package aplicacao;

import aplicacao.pecas.Peca;
import aplicacao.pecas.Pos;

import static aplicacao.pecas.Cor.BRANCO;
import static aplicacao.pecas.Cor.PRETO;

public class Controle {
    private static Peca pecaSelecionada = null;
    private static boolean prontoMov = false;
    private static boolean perdeu = false;
    private static boolean movValido = true;

    //TODO: nao testado
    private static void movePeca(Pos posicaoDestino){
        Controle.movValido = pecaSelecionada.testaMovimento(pecaSelecionada.getPosicao(), posicaoDestino, Jogo.getJogo());
        if(movValido && !isPerdeu()){
            // Apaga posição original.
            Jogo.apagaPosJogo(pecaSelecionada.getPosicao());

            // Atualiza posição de destino.
            pecaSelecionada.setPosicao(posicaoDestino);
            Jogo.inserePecaJogo(pecaSelecionada);
            // Reset da selecao de peca.
            prontoMov = false;
            pecaSelecionada = null;

            Main.desenhaTabuleiro(Jogo.getJogo());
            // Atualiza movimento no tabuleiro.
            Main.atualizaTabuleiro();

            String femAtual = Jogo.getTurnos().get(Jogo.getTurnoAtual());
            String femTabuleiro = Tabuleiro.getFem(Jogo.getTabuleiroJogo());
            //System.out.println(femAtual);
            //System.out.println(femTabuleiro);
            if(Tabuleiro.getFem(Jogo.getTabuleiroJogo()).equals(femAtual)){
                setPerdeu(false);
            }
            else{
                setPerdeu(true);
                System.out.println("!!!! PERDEU !!!!!!");
            }
            System.out.println("!!!!! Movimento valido");
        }
        else{
            System.out.println("!!!!! Movimento invalido: " + posicaoDestino);
        }

    }

    public static void registraClique(Pos posicao){
        Peca pecaClicada = null;
        boolean casaOcupada = Jogo.getJogo(posicao) != null;
        if (casaOcupada)
            pecaClicada = Jogo.getJogo(posicao);

        // Segundo clique, se já tiver feito um primeiro clique válido.
        if (Controle.prontoMov) {
            //Pos posJogadaCorreta = Jogo.getJogadas().get(Jogo.getJogadaAtual()).getPosDest();

                boolean posDiferentes = !posicao.equals(pecaSelecionada.getPosicao());

                if (posDiferentes) {
                    if (casaOcupada){
                        // Captura.
                        if(pecaClicada.getCor() == PRETO){
                            movePeca(posicao);
                        }
                        // Troca a peca selecionada
                        else{
                            pecaSelecionada = pecaClicada;
                        }
                    }
                    // Movimento para casa vazia.
                    if (!casaOcupada) {
                        movePeca(posicao);
                    }

                    if(!isPerdeu() && movValido){
                        Tabuleiro.atualizaTabuleiroJogadaBot();
                        Jogo.setTurnoAtual(Jogo.getTurnoAtual() + 1);
                    }
                }
        }
        // Primeiro Clique.
        else {
            // Primeiro clique deve ser de uma peça branca.
            if (casaOcupada && pecaClicada.getCor() == BRANCO) {
                Controle.pecaSelecionada = pecaClicada;
                prontoMov = true;
            }
        }
    }

    public static boolean isPerdeu() {
        return perdeu;
    }

    public static void setPerdeu(boolean perdeu) {
        Controle.perdeu = perdeu;
    }
}