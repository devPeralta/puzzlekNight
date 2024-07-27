package aplicacao;

import aplicacao.pecas.Peca;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("visualizacao.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        scene.getStylesheets().add(getClass().getResource("aplicacao.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        Jogo jogo1 = new Jogo();
        //Controle controle = new Controle(jogo1);
        jogo1.carregaNovoProblema();
        Peca[][] tabuleiro = jogo1.tabuleiro;


        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(tabuleiro[i][j] != null){
                    System.out.print(tabuleiro[i][j].getSimbolo());
                }
                else{
                    System.out.print(" x ");
                }
            }
            System.out.println();
        }

        launch();
    }
}