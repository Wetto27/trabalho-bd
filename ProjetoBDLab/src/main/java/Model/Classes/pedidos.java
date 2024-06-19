package Model.Classes;

public class pedidos {
    //Atributos do historico

    private String Pk_cpfCliente;
    private int idPedido;
    private String livros;
    private String data;
    private String status;

    public pedidos(int idPedido, String livros, String data, String status, String pk_cpfCliente) {
        this.idPedido = idPedido;
        this.livros = livros;
        this.data = data;
        this.status = status;
        Pk_cpfCliente = pk_cpfCliente;
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

    public String getStatus() {
        return status;
    }
}


