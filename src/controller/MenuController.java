package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.Main;

public class MenuController {

    @FXML
    void btnProduto(ActionEvent event) {
        Main.changeScreen("Produto");
    }

    @FXML
    void btnVenda(ActionEvent event) {
        Main.changeScreen("Venda");
    }



}
