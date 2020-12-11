package controller;

import DAO.ProdutoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.Main;
import model.Produto;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControllerProduto implements Initializable {

    @FXML
    private Button btnVoltar;

    @FXML
    private TextArea TextAreaLista;

    @FXML
    private TextField TextFieldQtd;

    @FXML
    private TextField TextFieldID;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnID;

    @FXML
    private TextField TextFieldValor;

    @FXML
    private TextField TextFieldNome;

    @FXML
    private Button btnCriar;

    @FXML
    void FindbyID(ActionEvent event) {
        String idString = TextFieldID.getText();
        Produto produto = null;
        if (!idString.equals("")) {
            try {
                int id = Integer.parseInt(idString);
                produto = new ProdutoDAO().findbyId(id);
            } catch (Exception e) {

            }
            if (produto != null) {
                TextFieldID.setText(produto.getId() + "");
                TextFieldNome.setText(produto.getNomeProduto());
                TextFieldQtd.setText(produto.getQtd()+"");
                TextFieldValor.setText(produto.getValor()+"");

            }
        }


    }
    private void limpaCampos() {

        TextFieldNome.clear();
        TextFieldQtd.clear();
        TextFieldValor.clear();
        TextFieldID.clear();

    }

    public Produto pegaProdutoID(){
        return new Produto(
                Integer.parseInt(TextFieldID.getText()),
                TextFieldNome.getText(),
                Integer.parseInt(TextFieldQtd.getText()),
                Float.parseFloat(TextFieldValor.getText()));

    }
    private Produto pegaPessoa(){
        return new Produto(TextFieldNome.getText(),
                (Integer.parseInt(TextFieldQtd.getText())),
                (Float.parseFloat(TextFieldValor.getText())));

    }

    @FXML
    void btnEditar(ActionEvent event) {
        Produto p = pegaProdutoID();
        limpaCampos();
        int qtde = new ProdutoDAO().Alterar(p);
        listarProduto();
    }

    @FXML
    void btnCriar(ActionEvent event){
        Produto pessoa = pegaPessoa();
        limpaCampos();
        int qtd = new ProdutoDAO().inserirPessoa(pessoa);
        limpaCampos();
        listarProduto();

    }
    @FXML
    void btnListar(ActionEvent event){
        listarProduto();
    }


    private void listarProduto(){
        TextAreaLista.clear();
        List<Produto> listarProduto = new ProdutoDAO().listAll();
        listarProduto.forEach(produto ->{
            TextAreaLista.appendText(produto.toString()+ "\n");
        });

    }

    @FXML
    protected void btnVoltar(ActionEvent e){
       Main.changeScreen("Menu");
    }

    @FXML
    void btnExcluir(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Excluir cadastro deste Produto?");
        alert.setHeaderText("ctz?");
        alert.setContentText("OK???");
        Optional<ButtonType> resultado = alert.showAndWait();
        if (resultado.get() == ButtonType.OK){
            Produto produto = pegaProdutoID();
            int qtde = new ProdutoDAO().Deletar(produto.getId());
            listarProduto();
            TextFieldID.clear();
            limpaCampos();
        }
    }

   public void initialize(URL location, ResourceBundle resources) {


       Main.addOnChangeScreenListener(new Main.OnChangeScreen() {
           @Override
           public void onScreenChanged(String newScreen, Object userData) {
               if(newScreen.equals("Produto")){
                   listarProduto();

               }
           }
       });
   }


}
