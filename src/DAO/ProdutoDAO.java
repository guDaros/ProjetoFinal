package DAO;

import database.ConexaoHSQLDB;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends ConexaoHSQLDB {

    final String Insert_SQL = "INSERT INTO PRODUTO (NOMEPRODUTO, QUANTIDADE, VALOR) VALUES ( ?,?,?);";

    final String SQL_SELECT_PESSOA_ID = " SELECT * FROM PRODUTO WHERE ID =? ";

    final String SQL_ALTERA_PESSOA = " UPDATE PRODUTO SET NOMEPRODUTO = ?, QUANTIDADE =?, VALOR =? WHERE ID =? ";

    final String SQL_DELETA_PESSOA = "DELETE FROM PRODUTO WHERE ID = ?";

    final String SQL_SELECT = " SELECT * FROM PRODUTO ORDER BY ID ASC ";




    public int inserirPessoa(Produto produto){
        int quantidade =0;
        try (Connection connection = this.connectar();
             PreparedStatement pst = connection.prepareStatement(Insert_SQL);){

            pst.setString(1, produto.getNomeProduto());
            pst.setInt(2, produto.getQtd());
            pst.setFloat(3, produto.getValor());

            quantidade = pst.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
        } return quantidade;
    }

    public Produto findbyId(int id)  {
        Produto p = null;
        try(Connection connection = this.connectar();
            PreparedStatement pst = connection.prepareStatement(SQL_SELECT_PESSOA_ID);){

            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                p = new Produto();
                p.setId(rs.getInt("ID"));
                p.setNomeProduto(rs.getString("NOMEPRODUTO"));
                p.setQtd(rs.getInt("QUANTIDADE"));
                p.setValor(rs.getInt("VALOR"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return  p;
    }

    public List<Produto> listAll() {
        ArrayList<Produto> listarpessoa = new ArrayList<>();
        try (Connection connection = connectar();
             PreparedStatement pst = connection.prepareStatement(SQL_SELECT);) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("ID"));
                p.setNomeProduto(rs.getString("NOMEPRODUTO"));
                p.setQtd(rs.getInt("QUANTIDADE"));
                p.setValor(rs.getInt("VALOR"));

                listarpessoa.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listarpessoa;
    }

    public int Alterar(Produto produto){

        int quantidade = 0;
        try (Connection connection = this.connectar();
             PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_PESSOA);) {

            pst.setString(1, produto.getNomeProduto());
            pst.setInt(2, produto.getQtd());
            pst.setFloat(3, produto.getValor());
            pst.setInt(4,produto.getId());
            quantidade = pst.executeUpdate();



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return quantidade;
    }

    public int Deletar(int id){
        int quantidade = 0;
        try (Connection connection = this.connectar();
             PreparedStatement pst = connection.prepareStatement(SQL_DELETA_PESSOA);) {
            pst.setInt(1, id);
            quantidade = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return quantidade;
    }



}
