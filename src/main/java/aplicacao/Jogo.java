package aplicacao;

import aplicacao.pecas.Peca;
import aplicacao.pecas.Pos;
import aplicacao.pecas.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static java.nio.file.Files.readAllLines;

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
        String linhaUnica = readAllLines(problemaTeste1).getFirst();

        int linhaTab = 0;
        int colunaTab = 0;

        for(int j=0;j<linhaUnica.length();j++){
            char caractere = linhaUnica.charAt(j);
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


        // Cria problema
        //this.problema = new Problema(atributos);

    }
}
