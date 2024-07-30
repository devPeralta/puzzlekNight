package aplicacao;

import aplicacao.pecas.Peca;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

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

        // Cria e adiciona o menu à direita
        VBox menu = criaMenu();
        tela.setRight(menu);

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

        // Espaçamento da tela.
        tela.setPadding(new Insets(20,20,20,20));

        return tela;
    }

    private VBox criaMenu() {
        VBox menu = new VBox(20); // Espaçamento de 10 pixels entre os itens do menu
        menu.setPadding(new Insets(40)); // Padding ao redor do menu
        menu.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        menu.setAlignment(Pos.TOP_CENTER);

        // Adiciona os elementos do menu
        Label dificuldadeLabel = new Label("Difícil");
        dificuldadeLabel.setTextFill(Color.RED);
        dificuldadeLabel.setStyle("-fx-font-size: 34px;");

        Label pontuacaoLabel = new Label("Pontuação: 30");
        pontuacaoLabel.setTextFill(Color.WHITE);
        pontuacaoLabel.setStyle("-fx-font-size: 34px;");

        Label jogadaLabel = new Label("Brancas Jogam");
        jogadaLabel.setTextFill(Color.WHITE);
        jogadaLabel.setStyle("-fx-font-size: 28px;");

        dificuldadeLabel.setAlignment(Pos.CENTER);
        pontuacaoLabel.setAlignment(Pos.CENTER);
        jogadaLabel.setAlignment(Pos.CENTER);


        menu.getChildren().addAll(dificuldadeLabel, pontuacaoLabel, jogadaLabel);

        // Adiciona um botão para o menu
        Button menuButton = new Button("menu");
        menu.getChildren().add(menuButton);

        return menu;
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