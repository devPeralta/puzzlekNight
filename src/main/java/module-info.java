module application {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;

    exports aplicacao.teste;
    opens aplicacao to javafx.fxml;
    exports aplicacao;
    exports aplicacao.pecas;
    opens aplicacao.pecas to javafx.fxml;
}