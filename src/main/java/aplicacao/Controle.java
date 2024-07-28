package aplicacao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controle {
    @FXML
    private Label welcomeText;

    @FXML
    private void handleButtonClick(ActionEvent e) {
        Button button = (Button) e.getSource();
        button.setStyle("-fx-background-color: rgb(154,69,69);"); // Altera a cor de fundo para vermelho
        System.out.print(button.getId());

        switch(button.getId()){
            //TODO: implementar metodos de retorno para cada botao quando existirem
            case "A1":
                //implementacao clique A1
                break;
            case "B1":
                //implementacao clique B1
                break;

                // ...

        }
    }


}