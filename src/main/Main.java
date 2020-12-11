package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.ArrayList;


public class Main extends Application {
    private static Stage stage;
    private static Scene menuScene;
    private static Scene produtoScene;
    private static Scene vendaScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;

        primaryStage.setTitle("Prova Final");

        //puxa o fxml da main
        Parent fxmlProduto = FXMLLoader.load(getClass().getResource("/view/Produto.fxml"));
        //puxa o objeto da main(para aparecer a janela)
        produtoScene = new Scene(fxmlProduto);

        Parent fxmlMenu = FXMLLoader.load((getClass().getResource("/view/Menu.fxml")));
        menuScene = new Scene(fxmlMenu);

        Parent fxmlVenda = FXMLLoader.load((getClass().getResource("/view/Venda.fxml")));
        vendaScene = new Scene(fxmlVenda);

        primaryStage.setScene(menuScene);
        primaryStage.show();
    }
    public static void changeScreen(String scr, Object userData){

        switch (scr){
            case "Menu":
                stage.setScene(menuScene);
                notifyAllListeners("Menu", userData );
                break;
            case "Produto":
                stage.setScene(produtoScene);
                notifyAllListeners("Produto", userData );
                break;
            case "Venda":
                stage.setScene(vendaScene);
                notifyAllListeners("Venda", userData );
                break;

        }
    }


    public static void changeScreen(String scr){
        switch (scr){
            case "Menu":
                stage.setScene(menuScene);
                notifyAllListeners("Menu", null );
                break;
            case "Produto":
                stage.setScene(produtoScene);
                notifyAllListeners("Produto", null);
                break;
            case "Venda":
                stage.setScene(vendaScene);
                notifyAllListeners("Venda", null );
                break;
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    private static ArrayList<OnChangeScreen> listeners = new ArrayList<>();

    public  static interface OnChangeScreen{
        void  onScreenChanged(String newScreen, Object userData);
    }

    public static void addOnChangeScreenListener(OnChangeScreen newListener){
        listeners.add(newListener);
    }
    private static  void notifyAllListeners(String newScreen, Object userData){
        for(OnChangeScreen l : listeners){
            l.onScreenChanged(newScreen, userData);
        }
    }
}

