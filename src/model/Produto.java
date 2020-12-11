package model;

public class Produto {

    public int id;
    public String NomeProduto;
    public int Qtd;
    public float Valor;

    public Produto(int id, String nomeProduto, int qtd, float valor) {
        super();
        this.id = id;
        NomeProduto = nomeProduto;
        Qtd = qtd;
        Valor = valor;
    }

    public Produto() {
        super();
    }

    public Produto(String nomeProduto, int qtd, float valor) {
        this.NomeProduto = nomeProduto;
        this.Qtd = qtd;
        this.Valor = valor;
    }

    public Produto(int id, String nomeProduto) {
        this.id = id;
        NomeProduto = nomeProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return NomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        NomeProduto = nomeProduto;
    }

    public int getQtd() {
        return Qtd;
    }

    public void setQtd(int qtd) {
        Qtd = qtd;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float valor) {
        Valor = valor;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", Produto='" + NomeProduto + '\'' +
                ", Qtd=" + Qtd +
                ", Valor=" + Valor;
    }



}
