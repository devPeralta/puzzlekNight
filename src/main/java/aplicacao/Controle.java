package aplicacao;

import aplicacao.pecas.Peca;
import aplicacao.pecas.PecaNula;
import aplicacao.pecas.Pos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controle {
    @FXML
    private GridPane gridpane; // corresponde ao fx:id no FXML

    @FXML
    private void handleButtonClick(ActionEvent e) {

        Jogo.setCliqueOrigemDestino(!Jogo.getCliqueOrigemDestino());
        Button button = (Button) e.getSource();
        if(Jogo.getCliqueOrigemDestino()) { // clique origem
            if (button.getStyleClass().contains("darkButtons")) {
                button.setStyle("-fx-background-color: rgba(167,103,103,0.75);"); // Altera a cor de fundo para vermelho
            } else {
                button.setStyle("-fx-background-color: rgba(110,56,56,0.75);"); // Altera a cor de fundo para vermelho
            }

            int linhaClique = 8 - Character.getNumericValue(button.getId().charAt(1));
            int colunaClique = (Character.getNumericValue(button.getId().charAt(0))) - 10;



            if(Jogo.getTabuleiro()[linhaClique][colunaClique] != null) {
                String cor = Jogo.getTabuleiro()[linhaClique][colunaClique].getCor() ? "preto" : "branco";
                char pecaClique = Jogo.getTabuleiro()[linhaClique][colunaClique].getSimbolo();
                System.out.println("Clique: " + pecaClique + " " + cor);
            }

            Button botaoA8 = (Button) gridpane.lookup("#A8");
            if (botaoA8 != null) {
                // Altere o plano de fundo do botão A8

                botaoA8.setStyle("-fx-background-color: yellow;"); // Altere a cor conforme necessário
            }
        }
        else{// clique destino

        }
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

    //TODO: nao testado
    private void movePeca(Peca peca, Pos posicaoDestino){
        // Apaga posição original.
        PecaNula apagaPeca =
                new PecaNula(true,'.',new Pos(peca.getPosicao().getX(),peca.getPosicao().getY()));
        Jogo.insereTabuleiro(apagaPeca);

        // Atualiza posição de destino.
        peca.setPosicao(posicaoDestino);
        Jogo.insereTabuleiro(peca);
    }

}