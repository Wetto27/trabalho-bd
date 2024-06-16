package Model.Classes;

public class livros {

    //Atributos dos livros
    private int id;
    private String titulo;
    private String autores;
    private String tema;;
    private String empregado_cpf;

    //Construtor dos livros


    public livros(int id, String titulo, String autores, String tema, String empregado_cpf) {
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
        this.tema = tema;
        this.empregado_cpf = empregado_cpf;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutores() {
        return autores;
    }

    public String getTema() {
        return tema;
    }

    public String getEmpregadoCPF() {
        return empregado_cpf;
    }
}
