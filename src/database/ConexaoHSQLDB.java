package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoHSQLDB {
    private String usuario = "SA";
    private String senha = "";
    private String PathBase = "C:\\Projetoprova\\Dados\\Dados";
    private String URL = "jdbc:hsqldb:file:" + PathBase +";";


    public Connection connectar() {

        try {
            System.out.println("Connectando...");
            return DriverManager.getConnection(URL, usuario, senha);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("Falha na conexão");
            throw new Error("Sql Exception");

        }
    }


}
