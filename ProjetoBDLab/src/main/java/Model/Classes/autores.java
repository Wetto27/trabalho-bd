package Model.Classes;

public class autores {

    //Atributos de autores
    private int id;
    private String nome;
    private String email;

    public autores( int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters de autores

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

}
