package aplicacao;

import aplicacao.pecas.Peca;
import aplicacao.pecas.Pos;
import aplicacao.pecas.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import static java.nio.file.Files.readAllLines;
import java.util.Map;
import java.util.HashMap;

public class Jogo {
    //Problema problema;
    Peca[][] tabuleiro = new Peca[8][8];
    private final String[][] coordenadas = {
            {"A8", "B8", "C8", "D8", "E8", "F8", "G8", "H8"},
            {"A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7"},
            {"A6", "B6", "C6", "D6", "E6", "F6", "G6", "H6"},
            {"A5", "B5", "C5", "D5", "E5", "F5", "G5", "H5"},
            {"A4", "B4", "C4", "D4", "E4", "F4", "G4", "H4"},
            {"A3", "B3", "C3", "D3", "E3", "F3", "G3", "H3"},
            {"A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2"},
            {"A1", "B1", "C1", "D1", "E1", "F1", "G1", "H1"}
    };

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
                        Path origemRei = Paths.get("src/main/resources/aplicacao/pngPecas/rei" + ((cor) ? "Branco":"Preto") + ".png");
                        Path destinoRei = Paths.get("src/main/resources/aplicacao/pngTabuleiro/" + coordenadas[linhaTab][colunaTab-1] + ".png");
                        Files.copy(origemRei, destinoRei, StandardCopyOption.REPLACE_EXISTING);
                        break;
                    case 'r':
                        tabuleiro[linhaTab][colunaTab] =
                                new Torre(cor, new Pos(linhaTab, colunaTab), caractere);
                        colunaTab++;
                        Path origemTorre = Paths.get("src/main/resources/aplicacao/pngPecas/torre" + ((cor) ? "Branca":"Preta") + ".png");
                        Path destinoTorre = Paths.get("src/main/resources/aplicacao/pngTabuleiro/" + coordenadas[linhaTab][colunaTab-1] + ".png");
                        Files.copy(origemTorre, destinoTorre, StandardCopyOption.REPLACE_EXISTING);
                        break;
                    case 'n':
                        tabuleiro[linhaTab][colunaTab] =
                                new Cavalo(cor, new Pos(linhaTab, colunaTab), caractere);
                        colunaTab++;
                        Path origemCavalo = Paths.get("src/main/resources/aplicacao/pngPecas/cavalo" + ((cor) ? "Branco":"Preto") + ".png");
                        Path destinoCavalo = Paths.get("src/main/resources/aplicacao/pngTabuleiro/" + coordenadas[linhaTab][colunaTab-1] + ".png");
                        Files.copy(origemCavalo, destinoCavalo, StandardCopyOption.REPLACE_EXISTING);
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

    // limpa o diretorio pngTabuleiro sempre que um novo jogo é iniciado
    public static void pngTabuleiroClear(){
        File directory = new File("src/main/resources/aplicacao/pngTabuleiro");
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                file.delete();
            }
        }
    }
}
