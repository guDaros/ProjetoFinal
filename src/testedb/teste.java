package testedb;

import database.ConexaoHSQLDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class teste {

    public static void main(String[] args) {
        ConexaoHSQLDB connect = new ConexaoHSQLDB();
        Connection connection = connect.connectar();
        System.out.println(connection);
//INSERT Dados
        final String Insert_SQL = " INSERT INTO CARRO (NOME, ANO) VALUES (?,?) ";
        PreparedStatement pst;
        try {
            pst = connection.prepareStatement(Insert_SQL);
            pst.setString(1, "Fiat");
            pst.setString(2, "123");
            int quantidade = pst.executeUpdate();
            System.out.println("Quantidade de cadastros na tabela: " + quantidade);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //LISTA DADOS
        final String Select_Sql = "Select * FROM CARRO";
        try {
            pst = connection.prepareStatement(Select_Sql);
            ResultSet RS = pst.executeQuery();
            while(RS.next()) {
                int id = RS.getInt("ID");
                String nome = RS.getNString("NOME");
                String ano = RS.getNString("ANO");
                System.out.println(id+" "+ nome +" " + ano);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
