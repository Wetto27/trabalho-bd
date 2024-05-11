package Model.Classes;

public class historico {

    //Atributos do historico
    private int Pk_cpfCliente;
    private String livros;
    private String data;

    //Construtor do historico
    public historico(int cpfCliente, String livros, String data) {
        this.Pk_cpfCliente = cpfCliente;
        this.livros = livros;
        this.data = data;
    }

    public historico(int cpfCliente) {
        this.Pk_cpfCliente = cpfCliente;
    }

    //Getters do historico

    public int getCpfCliente() {
        return Pk_cpfCliente;
    }

    public String getLivros() {
        return livros;
    }

    public String getData() {
        return data;
    }
}
