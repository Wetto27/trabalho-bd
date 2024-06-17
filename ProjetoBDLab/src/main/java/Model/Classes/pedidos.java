package Model.Classes;

public class pedidos {

    //Atributos do pedido
    private String Pk_cpfCliente;
    private int idPedido;
    private String livros;
    private String data;

    public pedidos(int idPedido, String livros, String data,String pk_cpfCliente) {
        Pk_cpfCliente = pk_cpfCliente;
        this.idPedido = idPedido;
        this.livros = livros;
        this.data = data;
    }

    public String getPk_cpfCliente() {
        return Pk_cpfCliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public String getLivros() {
        return livros;
    }

    public String getData() {
        return data;
    }

}