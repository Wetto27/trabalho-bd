package Model.Classes;

public class empregados {

    // Atributos do empregado
    private String cpf;
    private String nome;
    private String email;

    // Construtor de empregado
    public empregados(String cpf, String nome, String email) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }

    public empregados(String cpf) {
        this.cpf = cpf;
    }

    // Getters dos empregados

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
