package controller;

import DAO.ProdutoDAO;
import database.ConexaoHSQLDB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.Main;
import model.Produto;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VendaController extends ConexaoHSQLDB implements Initializable{

    @FXML
    private TextArea TextAreaLista;

    @FXML
    private ComboBox<String > cbCategorias;


    @FXML
    private TextField TextFieldQtd;

    @FXML
    private TextArea TextAreaProduto;


    int count = 15;

    @FXML
    protected void btnVoltar(ActionEvent e){
        Main.changeScreen("Menu");
    }


    @FXML
    void btnVender(ActionEvent event) {
        //String produto = cbCategorias.getValue();



        //System.out.println(cbCategorias.getValue());
        System.out.println("numero de vendas:" + count++);
    }


    private void listarProduto(){
        TextAreaLista.clear();
        List<Produto> listarProduto = new ProdutoDAO().listAll();
        listarProduto.forEach(produto ->{
            TextAreaLista.appendText(produto.toString()+ "\n");
        });

    }




    private List<String> setData(){
        List<String> options = new ArrayList<>();
        try (Connection connection = this.connectar();
        PreparedStatement pst = connection.prepareStatement("SELECT NOMEPRODUTO FROM PRODUTO");){
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                options.add(rs.getString("NOMEPRODUTO"));
            }
            return options;
        }catch (SQLException e){
            e.printStackTrace();
        }
            return options;

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
            @Override
            public void onScreenChanged(String newScreen, Object userData) {
                if(newScreen.equals("Venda")){
                    cbCategorias.setItems(FXCollections.observableArrayList(setData()));
                    listarProduto();


                    cbCategorias.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

                        @Override
                        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                            System.out.println("Selected value : " + newValue);
                        }


                    });


                }
            }
        });


    }



}
