package aplicacao;

import aplicacao.pecas.Peca;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static aplicacao.pecas.Cor.BRANCO;

public class Main extends Application {
    public static Tabuleiro tabuleiro;

    public static void main(String[] args) throws IOException {
        Jogo.carregaNovoProblema();
        //desenhaTabuleiro(Jogo.getJogo());
        launch(args);
    }


    @Override
    public void start(Stage estagio) {
        tabuleiro = new Tabuleiro();
        BorderPane tela = getTela();
        tela.setLeft(tabuleiro);

        // Cria imagem de icone.
        URL recurso = getClass().getResource("/aplicacao/pngPecas/cavaloPreto.png");
        if (recurso != null) {
            String urlIcone = recurso.toExternalForm();
            Image pecaImage = new Image(urlIcone);
            estagio.getIcons().add(pecaImage);
        } else {
            System.err.println("Ícone não encontrado.");
        }

        // Define o titulo da janela.
        estagio.setTitle("Puzzle Knight");
        // Impede o redimensionamento da janela
        estagio.setResizable(false);

        // Instancia scena
        Scene scena = new Scene(tela);
        estagio.setScene(scena);
        estagio.show();
    }

    private static BorderPane getTela() {
        BorderPane tela = new BorderPane();
        // Cor de fundo.
        BackgroundFill backgroundFill = new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        tela.setBackground(background);
        // Espaçamento entre tabuleiro e tela.
        tela.setPadding(new Insets(20,400,20,20));
        return tela;
    }

    public static void atualizaTabuleiro(){
        tabuleiro.mostraPecas();
    }

    public static void desenhaTabuleiro(Peca[][] tabuleiro) {
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(tabuleiro[i][j] != null){
                    if(tabuleiro[i][j].getCor() == BRANCO) {
                        System.out.print(" " + Character.toUpperCase(tabuleiro[i][j].getSimbolo()) + " ");
                    }
                    else{
                        System.out.print(" " + tabuleiro[i][j].getSimbolo() + " ");
                    }
                }
                else{
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
}