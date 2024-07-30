package aplicacao;

import aplicacao.pecas.Peca;
import aplicacao.pecas.Pos;
import aplicacao.pecas.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static aplicacao.pecas.Cor.*;
import static java.nio.file.Files.readAllLines;

public class Jogo {
    //Problema problema;
    private static Peca[][] tabuleiroJogo = new Peca[8][8];
    private static ArrayList<Jogada> jogadas = new ArrayList<>();

    private static boolean cliqueDestino = false;  // false = clique é origem, true = clique é destino

    public static void carregaNovoProblema(){
        // TODO:Cria valor randômico para selecionar problema.

        clearJogo();
        // Lê problema.
        Path problemaTeste1 = Paths.get("src/main/java/problemas/m2.txt");

        List<String> linhas;
        try {
            linhas = readAllLines(problemaTeste1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String linhaFem = linhas.getFirst();
        String linhaProximasJogadas = linhas.get(2);
        int linhaTab = 0;
        int colunaTab = 0;

        leituraJogadasCorretas(linhaProximasJogadas);

        for(int posCharLido=0;posCharLido<linhaFem.length();posCharLido++){
            char caractere = linhaFem.charAt(posCharLido);
            // Digitos indicam quantia de casas vazias.
            if(Character.isDigit(caractere)){
                int casasVazias = Character.getNumericValue(caractere);
                colunaTab += casasVazias;
            }
            else{
                Cor cor;
                if(Character.isUpperCase(caractere))
                    cor = BRANCO;
                else
                    cor = PRETO;

                boolean fimLinha = false;
                Peca novaPeca = null;
                caractere = Character.toLowerCase(caractere);
                // Define qual peca esta sendo lida.
                switch(caractere) {
                    //TODO: se possivel, tentar descobrir uma forma de diminuir essa repeticao de codigo
                    case 'k':
                        novaPeca = new Rei(cor, new Pos(linhaTab, colunaTab), caractere);
                        break;
                    case 'r':
                        novaPeca = new Torre(cor, new Pos(linhaTab, colunaTab), caractere);
                        break;
                    case 'n':
                        novaPeca = new Cavalo(cor, new Pos(linhaTab, colunaTab), caractere);
                        break;
                    case 'b':
                        novaPeca = new Bispo(cor, new Pos(linhaTab, colunaTab), caractere);
                        break;
                    case 'q':
                        novaPeca = new Rainha(cor, new Pos(linhaTab, colunaTab), caractere);
                        break;
                    case 'p':
                        novaPeca = new Peao(cor, new Pos(linhaTab, colunaTab), caractere);
                        break;
                    // Indica que a linha acabou.
                    case '/':
                        linhaTab++;
                        colunaTab = 0;
                        fimLinha = true;
                        break;
                    default:
                        System.out.println("Leitura do tabuleiro nao implementada");
                        break;
                }
                // Insere nova peca no tabuleiro.
                if(!fimLinha){
                    tabuleiroJogo[linhaTab][colunaTab] = novaPeca;
                    colunaTab++;
                }
            }
        }
    }

    private static void leituraJogadasCorretas(String jogadas){
        String[] jogadasCorretas = jogadas.split(" ");
        int contMovComputador=0;
        char peca;
        boolean captura, xeque;
        boolean isPeao=false;

        for (String jogadasCorreta : jogadasCorretas) {
            Pos posDest = new Pos(0, 0);
            if (!Character.isDigit(jogadasCorreta.charAt(0))) {
                contMovComputador++;
                //System.out.println(jogada);
                switch (jogadasCorreta.charAt(0)) {
                    case 'K':
                        peca = 'K';
                        break;
                    case 'R':
                        peca = 'R';
                        break;
                    case 'N':
                        peca = 'N';
                        break;
                    case 'B':
                        peca = 'B';
                        break;
                    case 'Q':
                        peca = 'Q';
                        break;
                    //default é peão
                    default:
                        peca = 'P';
                        isPeao = true;
                }

                captura = jogadasCorreta.charAt(0) == 'x' || jogadasCorreta.charAt(1) == 'x';
                xeque = jogadasCorreta.charAt(jogadasCorreta.length() - 1) == '+';

                if (isPeao) {
                    if (captura) {
                        posDest.setLinha(jogadasCorreta.charAt(1) - 97);
                        posDest.setColuna(7 - (jogadasCorreta.charAt(2) - 49));
                    } else {
                        posDest.setLinha(jogadasCorreta.charAt(0) - 97);
                        posDest.setColuna(7 - (jogadasCorreta.charAt(1) - 49));
                    }
                } else {
                    if (captura) {
                        posDest.setLinha(jogadasCorreta.charAt(2) - 97);
                        posDest.setColuna(7 - (jogadasCorreta.charAt(3) - 49));
                    } else {
                        posDest.setLinha(jogadasCorreta.charAt(1) - 97);
                        posDest.setColuna(7 - (jogadasCorreta.charAt(2) - 49));
                    }
                }
                Jogo.jogadas.add(new Jogada(peca, posDest, contMovComputador % 2 == 0, captura, xeque));
            }
        }

        for(Jogada jogada : Jogo.jogadas){
            int posicao = Jogo.jogadas.indexOf(jogada) + 1;
            System.out.println(posicao + "º:" + jogada);
        }
    }

    private static void clearJogo() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Jogo.tabuleiroJogo[i][j] = null;
            }
        }
    }

    public static Peca[][] getJogo() {
        return Jogo.tabuleiroJogo;
    }

    public static Peca getJogo(Pos posicao) {
        return Jogo.tabuleiroJogo[posicao.getLinha()][posicao.getColuna()];
    }

    public static void apagaPosJogo(Pos posicaoDestino) {
        Jogo.tabuleiroJogo[posicaoDestino.getLinha()][posicaoDestino.getColuna()] = null;
        System.out.println("Apagou, linha: " + posicaoDestino.getLinha() + "coluna: " + posicaoDestino.getColuna());
    }

    public static void insereTabuleiroJogo(Peca[][] tabuleiroJogo) {
        Jogo.tabuleiroJogo = tabuleiroJogo;
    }

    public static void inserePecaJogo(Peca peca) {
        int linha = peca.getPosicao().getLinha();
        int coluna = peca.getPosicao().getColuna();

        if (linha >= 0 && linha <= 7 && coluna >= 0 && coluna <= 7)
            tabuleiroJogo[linha][coluna] = peca;
        else
            System.out.println(" Inserção de peça inválida.");
    }

    //-------------------------
    public static boolean getCliqueDestino() {
        return cliqueDestino;
    }

    public static void switchCliqueDestino() {
        cliqueDestino = !cliqueDestino;
    }
}
