package aplicacao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.nio.file.*;

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
        Path diretorioProblemas = Paths.get("src/main/java/problemas");
        Path problemaTeste1 = Paths.get("src/main/java/problemas/m23194.txt");
        //System.out.println(diretorioProblemas.toAbsolutePath());
        //System.out.println(problemaTeste1.toAbsolutePath());

        List<String> linhas = Files.readAllLines(problemaTeste1);
        for(int i=0;i<8;i++){
            String linha = linhas.get(i);
            for(int j=0;j<8;j++){
                char caractere = linha.charAt(j);
                switch(caractere){
                    case 'X':
                        break;
                    case 'K': System.out.println("rei");
                        break;
                    default: System.out.println("outro");
                }
            }
        }

        launch();
    }
}