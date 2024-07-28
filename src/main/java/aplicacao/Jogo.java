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
    private Peca[][] tabuleiro = new Peca[8][8];
    private ArrayList<Jogada> jogadas = new ArrayList<>();
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
        // TODO:Cria valor randômico para selecionar problema.

        // Lê problema.
        Path problemaTeste1 = Paths.get("src/main/java/problemas/m1.txt");

        List<String> linhas = readAllLines(problemaTeste1);
        String linhaFem = linhas.getFirst();
        String linhaProximasJogadas = linhas.get(2);
        int linhaTab = 0;
        int colunaTab = 0;

        leituraJogadasCorretas(linhaProximasJogadas);

        for(int j=0;j<linhaFem.length();j++){
            char caractere = linhaFem.charAt(j);
            if(Character.isDigit(caractere)){
                for(int k=0;k<Character.getNumericValue(caractere);k++){
                    Path origemNull = Paths.get("src/main/resources/aplicacao/pngPecas/null.png");
                    Path destinoNull = Paths.get("src/main/resources/aplicacao/pngTabuleiro/" + coordenadas[linhaTab][colunaTab+k] + ".png");
                    Files.copy(origemNull, destinoNull, StandardCopyOption.REPLACE_EXISTING);
                }
                colunaTab += Character.getNumericValue(caractere);
            }
            else{
                boolean cor = Character.isLowerCase(caractere);
                caractere = Character.toLowerCase(caractere);
                switch(caractere){
                    //TODO: se possivel, tentar descobrir uma forma de diminuir essa repeticao de codigo
                    case 'k':
                        tabuleiro[linhaTab][colunaTab] =
                                new Rei(cor, new Pos(linhaTab, colunaTab), caractere);
                        colunaTab++;
                        Path origemRei = Paths.get("src/main/resources/aplicacao/pngPecas/rei" + ((cor) ? "Preto":"Branco") + ".png");
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
                        Path origemCavalo = Paths.get("src/main/resources/aplicacao/pngPecas/cavalo" + ((cor) ? "Preto":"Branco") + ".png");
                        Path destinoCavalo = Paths.get("src/main/resources/aplicacao/pngTabuleiro/" + coordenadas[linhaTab][colunaTab-1] + ".png");
                        Files.copy(origemCavalo, destinoCavalo, StandardCopyOption.REPLACE_EXISTING);
                        break;
                    case 'b':
                        tabuleiro[linhaTab][colunaTab] =
                                new Bispo(cor, new Pos(linhaTab, colunaTab), caractere);
                        colunaTab++;
                        Path origemBispo = Paths.get("src/main/resources/aplicacao/pngPecas/bispo" + ((cor) ? "Preto":"Branco") + ".png");
                        Path destinoBispo = Paths.get("src/main/resources/aplicacao/pngTabuleiro/" + coordenadas[linhaTab][colunaTab-1] + ".png");
                        Files.copy(origemBispo, destinoBispo, StandardCopyOption.REPLACE_EXISTING);
                        break;
                    case 'q':
                        tabuleiro[linhaTab][colunaTab] =
                                new Rainha(cor, new Pos(linhaTab, colunaTab), caractere);
                        colunaTab++;
                        Path origemRainha = Paths.get("src/main/resources/aplicacao/pngPecas/rainha" + ((cor) ? "Branca":"Preta") + ".png");
                        Path destinoRainha = Paths.get("src/main/resources/aplicacao/pngTabuleiro/" + coordenadas[linhaTab][colunaTab-1] + ".png");
                        Files.copy(origemRainha, destinoRainha, StandardCopyOption.REPLACE_EXISTING);
                        break;
                    case 'p':
                        tabuleiro[linhaTab][colunaTab] =
                                new Peao(cor, new Pos(linhaTab, colunaTab), caractere);
                        colunaTab++;
                        Path origemPeao = Paths.get("src/main/resources/aplicacao/pngPecas/peao" + ((cor) ? "Preto":"Branco") + ".png");
                        Path destinoPeao = Paths.get("src/main/resources/aplicacao/pngTabuleiro/" + coordenadas[linhaTab][colunaTab-1] + ".png");
                        Files.copy(origemPeao, destinoPeao, StandardCopyOption.REPLACE_EXISTING);
                        break;
                    case '/':
                        linhaTab++;
                        colunaTab = 0;
                        break;
                    default: System.out.println("Leitura do tabuleiro nao implementada");
                }
            }
        }
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

    private void leituraJogadasCorretas(String jogadas){
        String[] jogadasCorretas = jogadas.split(" ");
        int contMovComputador=0;
        char peca=' ';
        boolean captura=false, xeque=false;
        boolean isPeao=false;
        for(int i=0;i<jogadasCorretas.length;i++){
            Pos posDest = new Pos(0,0);
            String jogada = jogadasCorretas[i];
            if(!Character.isDigit(jogada.charAt(0))){
                contMovComputador++;
                //System.out.println(jogada);
                switch(jogada.charAt(0)){
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

                if(jogada.charAt(0) == 'x' || jogada.charAt(1) == 'x'){
                    captura = true;
                }
                else{
                    captura=false;
                }

                if(jogada.charAt(jogada.length()-1) == '+'){
                    xeque = true;
                }
                else{
                    xeque = false;
                }
                if(isPeao) {
                    if(captura){
                        posDest.setX(jogada.charAt(1)-97);
                        posDest.setY(7 - (jogada.charAt(2)-49));
                    }
                    else{
                        posDest.setX(jogada.charAt(0)-97);
                        posDest.setY(7 - (jogada.charAt(1)-49));
                    }
                }
                else{
                    if(captura){
                        posDest.setX(jogada.charAt(2)-97);
                        posDest.setY(7 - (jogada.charAt(3)-49));
                    }
                    else{
                        posDest.setX(jogada.charAt(1)-97);
                        posDest.setY(7 - (jogada.charAt(2)-49));
                    }
                }
                this.jogadas.add(new Jogada(peca, posDest , contMovComputador % 2 == 0, captura, xeque));
            }
        }

        for(Jogada jogada : this.jogadas){
            int posicao = this.jogadas.indexOf(jogada) + 1;
            System.out.println(posicao + "º:" + jogada);
        }
    }

    public void setTabuleiro(Peca[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public Peca[][] getTabuleiro() {
        return tabuleiro;
    }
}
