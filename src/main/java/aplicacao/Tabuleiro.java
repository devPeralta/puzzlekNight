package aplicacao;
import aplicacao.pecas.Peca;

import aplicacao.pecas.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;

import java.net.URL;

import static aplicacao.pecas.Cor.BRANCO;

public class Tabuleiro extends GridPane {
    public static final int tamCasa = 80;
    private StackPane[][] casas = new StackPane[8][8];

    public Tabuleiro() {
        setHgap(0);  // Sem espaçamento entre as células
        setVgap(0);  // Sem espaçamento entre as células

        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Rectangle quadrado = new Rectangle(tamCasa, tamCasa);
                quadrado.setFill((linha + coluna) % 2 == 0 ? Color.SANDYBROWN : Color.SADDLEBROWN);

                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(quadrado);
                stackPane.setId("casa_" + linha + "_" + coluna); // Identificador

                // Hanlder para quando uma casa é clicada.
                stackPane.setOnMouseClicked(event -> {
                    int linhaClicada = Integer.parseInt(stackPane.getId().split("_")[1]);
                    int colunaClicada = Integer.parseInt(stackPane.getId().split("_")[2]);
                    //System.out.println("Casa Clicado: " + linhaClicada + ", " + colunaClicada);
                    Controle.registraClique(new Pos(linhaClicada, colunaClicada));
                });

                add(stackPane, coluna, linha);
                casas[linha][coluna] = stackPane;
            }
        }
        mostraPecas();
    }

    public void mostraPecas() {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = Jogo.getJogo(new Pos(linha, coluna));
                atualizaCelula(linha, coluna, peca);
            }
        }
    }

    private void atualizaCelula(int linha, int coluna, Peca peca) {
        StackPane stackPane = casas[linha][coluna];
        stackPane.getChildren().clear();

        Rectangle quadrado = new Rectangle(tamCasa, tamCasa);
        quadrado.setFill((linha + coluna) % 2 == 0 ? Color.SANDYBROWN : Color.SADDLEBROWN);
        stackPane.getChildren().add(quadrado);

        if (peca != null) {
            String caminho = getCaminhoPeca(peca);
            if (!caminho.isEmpty()) {
                URL urlImagem = getClass().getResource(caminho);
                if (urlImagem != null) {
                    String url = urlImagem.toExternalForm();
                    Image image = new Image(url);
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(tamCasa);
                    imageView.setFitHeight(tamCasa);
                    imageView.setPreserveRatio(true);
                    imageView.setSmooth(true);

                    stackPane.getChildren().add(imageView);
                } else {
                    System.err.println("Imagem não encontrada: " + caminho);
                }
            }
        }
    }

    private String getCaminhoPeca(Peca peca) {
        String caminho = "";
        if (peca != null) {
            caminho = peca.getCaminhoImagem();
            if (!caminho.isEmpty()) {
                if (peca.getCor() == BRANCO)
                    caminho += "Branco.png";
                else
                    caminho += "Preto.png";
            }
        }
        return caminho;
    }
}