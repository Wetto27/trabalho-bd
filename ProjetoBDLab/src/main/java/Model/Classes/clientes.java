package Model.Classes;

public class clientes {

    //Atributos dos clientes
    private String nome;
    private String email;
    private String cpf;
    private int empregado_id;
    private int historico_cpf_cliente;
    private String fk_empregado_cpf;

    //Construtor dos clientes


    public clientes(String nome, String email, String cpf, int empregado_id, int historico_cpf_cliente, String fk_empregado_cpf) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.empregado_id = empregado_id;
        this.historico_cpf_cliente = historico_cpf_cliente;
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

    public int getEmpregado_id() {
        return empregado_id;
    }

    public int getHistorico_cpf_cliente() {
        return historico_cpf_cliente;
    }

    public String getFk_empregado_cpf() {
        return fk_empregado_cpf;
    }
}
