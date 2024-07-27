package aplicacao;

import aplicacao.pecas.Peca;
import aplicacao.pecas.Pos;
import aplicacao.pecas.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.Files.readAllLines;
import java.util.Map;
import java.util.HashMap;

public class Jogo {
    //Problema problema;
    Peca[][] tabuleiro = new Peca[8][8];

    public Jogo() {

    }

    public void carregaNovoProblema() throws IOException {
        //Peca[][] tabuleiro = new Peca[8][8];

        // Cria valor randômico para selecionar problema.

        // Lê problema.
        Path diretorioProblemas = Paths.get("src/main/java/problemas");
        Path problemaTeste1 = Paths.get("src/main/java/problemas/m23194.txt");

        // Lê posição inicial do tabuleiro no formato FEN.
        // 5r1k/5q1p/1p6/1P1P4/P1R1P1P1/4Q3/6K1/8
        List<String> linhas = readAllLines(problemaTeste1);
        String linhaFem = linhas.getFirst();
        int linhaTab = 0;
        int colunaTab = 0;

        for(int j=0;j<linhaFem.length();j++){
            char caractere = linhaFem.charAt(j);
            if(Character.isDigit(caractere)){
                colunaTab += Character.getNumericValue(caractere);
            }
            else{
                boolean cor = Character.isLowerCase(caractere);
                switch(caractere){
                    case 'k':
                        tabuleiro[linhaTab][colunaTab] =
                                new Rei(cor, new Pos(linhaTab, colunaTab), caractere);
                        colunaTab++;
                        break;
                    case 'r':
                        tabuleiro[linhaTab][colunaTab] =
                                new Torre(cor, new Pos(linhaTab, colunaTab), caractere);
                        colunaTab++;
                        break;
                    case 'n':
                        tabuleiro[linhaTab][colunaTab] =
                                new Cavalo(cor, new Pos(linhaTab, colunaTab), caractere);
                        colunaTab++;
                        break;
                    case '/':
                        linhaTab++;
                        colunaTab = 0;
                        break;
                    default: System.out.println("Leitura do tabuleiro nao implementada");
                }
            }
        }
        /*
        // Lê PGN

        // Cria mapeamento entre letra representativa da coluna com número da coluna.
        Map<Character, Integer> letterToPos = new HashMap<>();
        letterToPos.put('a', 0);
        letterToPos.put('b', 1);
        letterToPos.put('c', 2);
        letterToPos.put('d', 3);
        letterToPos.put('e', 4);
        letterToPos.put('f', 5);
        letterToPos.put('g', 6);
        letterToPos.put('h', 7);

        // Lê arquivo.
        String linhaPGN = linhas.get(1);
        List<Jogada> jogadas = new ArrayList<>();

        boolean cor = false; // Temporário.
        Pos posDest = new Pos(0, 0);
        Peca pecaJogada = new Peca(false, 'K', new Pos(0, 0));
        boolean movComputador = false;

        for(int j=1;j<linhaPGN.length();j++) {
            boolean nextToPos = false;
            char caractere = linhaPGN.charAt(j);
            if(caractere == '.'){
                nextToPos = true;
                j++; // Pula espaço.
            } else if (caractere == ' ') {
                nextToPos = false;
            } else if (nextToPos) {
                switch (caractere) {
                    case 'K':
                        pecaJogada = new Rei(cor, new Pos(0, 0), caractere);
                        break;
                    case 'R':
                        pecaJogada = new Torre(cor, new Pos(0, 0), caractere);
                        break;
                    case 'N':
                        pecaJogada = new Cavalo(cor, new Pos(0, 0), caractere);
                        break;
                    default:
                        //Peão
                }

                // Obtém posição.
                caractere = linhaPGN.charAt(++j);
                int posColuna = letterToPos.get(caractere);
                caractere = linhaPGN.charAt(++j);
                int posLinha = Character.getNumericValue(caractere);

                pecaJogada.setPosicao(new Pos(posLinha, posColuna));
            }
        }

        jogadas.add(new Jogada(pecaJogada, posDest, movComputador));

        // Cria problema
        //this.problema = new Problema(atributos);
        */
    }
}
