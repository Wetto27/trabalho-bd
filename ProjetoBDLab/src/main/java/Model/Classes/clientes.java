package Model.Classes;

public class clientes {

    //Atributos dos clientes
    private String nome;
    private String email;
    private String cpf;
    private String fk_empregado_cpf;

    //Construtor dos clientes
    public clientes(String nome, String email, String cpf, String fk_empregado_cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.fk_empregado_cpf = fk_empregado_cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getFk_empregado_cpf() {
        return fk_empregado_cpf;
    }
}
