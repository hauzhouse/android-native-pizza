package esensato.pizzaria;

public class PizzaBean {

    private String sabor;
    private double preco;
    private int imagem;

    public PizzaBean(String sabor, double preco, int imagem){
        this.sabor = sabor;
        this.preco = preco;
        this.imagem = imagem;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return getPreco() + " = " + getSabor();
    }
}

