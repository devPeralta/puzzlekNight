package aplicacao;

import aplicacao.pecas.Peca;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("visualizacao.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("aplicacao.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        Jogo.pngTabuleiroClear();
        Jogo jogo1 = new Jogo();
        jogo1.carregaNovoProblema();
        Peca[][] tabuleiro = jogo1.getTabuleiro();

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(tabuleiro[i][j] != null){
                    if(tabuleiro[i][j].getCor()) {
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


        launch();
    }
}