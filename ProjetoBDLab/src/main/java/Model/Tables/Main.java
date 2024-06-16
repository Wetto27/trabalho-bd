package Model.Tables;

import Model.Classes.autores;
import Model.Classes.empregados;
import Model.Classes.livros;
import Model.Tables.DAO.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Boolean flag = true;
        Boolean flag2;
        Boolean flag3;
        Boolean flag4;

        livrosDAO livroDAO = new livrosDAO();
        historicoDAO historicosDAO = new historicoDAO();
        autoresDAO autorDAO = new autoresDAO();
        empregadoDAO empregadosDAO = new empregadoDAO();
        autoresTemLivrosDAO autorTemLivroDAO = new autoresTemLivrosDAO();

        // Informacoes do funcionário
        String nomeFunc;
        String emailFunc;
        String cpfFunc;

        // Informacoes dos Autores
        String nomeAutor;
        String emailAutor;
        int idAutor;

        // Informacoes do cliente
        String nomeCliente;
        String emailCliente;
        String cpfCliente;
        int historico;
        String fk_empregadoCPF;

        // Informacoes do livro
        int idLivro;
        String titulo;
        String autores;
        String tema;
        String empregado_cpf;


        while (flag) {
            System.out.println("\n+------------------------------------------------------------+");
            System.out.println("|                   Sistema da Biblioteca                    |");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("\nPor favor selecione uma das opções abaixo: ");
            System.out.println("1 - Adicionar funcionario");
            System.out.println("2 - Deletar funcionario");
            System.out.println("3 - Adicionar livro");
            System.out.println("4 - Listar livros");
            System.out.println("5 - Buscar livro pelo titulo");
            System.out.println("6 - Adicionar autor");
            System.out.println("7 - Deletar autor");
            System.out.println("8 - Mostrar todos autores");
            System.out.println("9 - Alterar email do autor");
            System.out.println("10 - Sair");
            System.out.print("\nOpção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:
                    System.out.println("\n===============================================================");
                    System.out.println("                   Criação de novo empregado                   ");
                    System.out.println("===============================================================");

                    System.out.println("\nPor favor entre com as informações abaixo: ");

                    System.out.print("\nNome: ");
                    nomeFunc = sc.nextLine(); //Entrando com o nome do empregado

                    System.out.println("Entre com o email: ");
                    emailFunc = sc.nextLine(); //Entrando com o email do empregado

                    System.out.print("Entre com o CPF: ");
                    cpfFunc = sc.nextLine(); //Entrando com o CPF do empregado

                    empregados empregado = new empregados(cpfFunc, nomeFunc, emailFunc);
                    empregadosDAO.insertEmpregado(empregado); // Inserindo na tabela o empregado criado
                    break;

                case 2:
                    System.out.println("Digite o CPF do funcionario: ");
                    cpfFunc = sc.nextLine();
                    empregadosDAO.deleteEmpregado(cpfFunc);
                    break;

                case 3:
                    System.out.println("\n==============================================================");
                    System.out.println("                    Adicionar um novo livro                   ");
                    System.out.println("==============================================================");

                    System.out.print("\nID: ");
                    idLivro = sc.nextInt();
                    sc.nextLine(); // Consumir a nova linha

                    System.out.print("Título: ");
                    titulo = sc.nextLine();

                    System.out.print("Autores: ");
                    autores = sc.nextLine();

                    System.out.print("Tema: ");
                    tema = sc.nextLine();

                    System.out.print("CPF do Empregado: ");
                    empregado_cpf = sc.nextLine();

                    livros novoLivro = new livros(idLivro, titulo, autores, tema, empregado_cpf);
                    boolean sucesso = livroDAO.insertLivro(novoLivro);
                    if (sucesso) {
                        System.out.println("Livro adicionado com sucesso!");
                    } else {
                        System.out.println("Falha ao adicionar o livro.");
                    }
                    break;

                case 4:
                    System.out.println("==============================================================");

                    ArrayList<livros> listaDeLivros = livroDAO.selectLivro();
                    for (livros livro : listaDeLivros) {
                        System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() + ", Autores: " + livro.getAutores() + ", Tema: " + livro.getTema() + ", CPF do Empregado: " + livro.getEmpregadoCPF());
                    }
                    break;

                case 5:
                    System.out.println("==============================================================");
                    String tituloProcurado;
                    tituloProcurado = sc.nextLine();

                    ArrayList<livros> listaDeLivrosProcura = livroDAO.selectLivro();
                    for (livros livro : listaDeLivrosProcura) {
                        if(livro.getTitulo().equalsIgnoreCase(tituloProcurado))
                            System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() + ", Autores: " + livro.getAutores() + ", Tema: " + livro.getTema() + ", CPF do Empregado: " + livro.getEmpregadoCPF());
                    }
                    break;

                case 6:
                    System.out.println("\n==============================================================");
                    System.out.println("                    Adicionando novo Autor                    ");
                    System.out.println("==============================================================");

                    System.out.println("\nPor favor entre com as informações abaixo: ");

                    System.out.print("\nNome: ");
                    nomeAutor = sc.nextLine(); //Entrando com o nome do autor

                    System.out.println("Entre com o email: ");
                    emailAutor = sc.nextLine(); //Entrando com o email do autor

                    System.out.print("Entre com o ID: ");
                    idAutor = sc.nextInt(); //Entrando com o ID do autor

                    autores autor = new autores(idAutor, nomeAutor, emailAutor);
                    autorDAO.insertAutores(autor); // Inserindo na tabela o empregado criado
                    break;

                case 7:
                    System.out.println("\n===============================================================");
                    System.out.println("                      Autores Cadastrados                      ");
                    System.out.println("===============================================================");
                    autorDAO.selectAutor();
                    break;

                case 8:
                    System.out.println("\n===============================================================");
                    System.out.println("                     Alterar email do autor                    ");
                    System.out.println("===============================================================");
                    System.out.println("Informe o ID do autor");
                    idAutor = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Informe o novo email");
                    String novoEmail;
                    novoEmail = sc.nextLine();
                    autorDAO.updateAutor(idAutor,novoEmail);
                    break;

                case 9:
                    flag = false;
                    break;
            }


        }


    }
}
