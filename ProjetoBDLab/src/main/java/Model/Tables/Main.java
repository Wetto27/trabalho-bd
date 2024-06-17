package Model.Tables;

import Model.Classes.autores;
import Model.Classes.empregados;
import Model.Classes.livros;
import Model.Classes.pedidos;
import Model.Tables.DAO.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Boolean flag = true;

        livrosDAO livroDAO = new livrosDAO();
        pedidosDAO pedidoDAO = new pedidosDAO();
        autoresDAO autorDAO = new autoresDAO();
        empregadoDAO empregadosDAO = new empregadoDAO();

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

        // Informacoes do pedido
        int idPedido;
        String dataPedido;
        String livrosPedido;
        String clientePedido;


        while (flag) {
            System.out.println("\n+------------------------------------------------------------+");
            System.out.println("|                   Sistema da Biblioteca                    |");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("\nPor favor selecione uma das opções abaixo: ");
            System.out.println("1 - Menu de funcionarios");
            System.out.println("2 - Menu de livros");
            System.out.println("3 - Menu de autores");
            System.out.println("4 - Menu de clientes");
            System.out.println("5 - Menu de pedidos");
            System.out.println("6 - Sair");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:
                    System.out.println("\n==============================================================");
                    System.out.println("                     Menu de Funcionários                     ");
                    System.out.println("==============================================================");
                    System.out.println("1 - Adicionar funcionario");
                    System.out.println("2 - Deletar funcionario");
                    int op1 = sc.nextInt();
                    sc.nextLine();

                    switch (op1) {
                        case 1:
                            System.out.println("\n==============================================================");
                            System.out.println("                   Adicionar novo empregado                   ");
                            System.out.println("==============================================================");

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
                    }
                    break;

                case 2:
                    System.out.println("\n==============================================================");
                    System.out.println("                        Menu de Livros                        ");
                    System.out.println("==============================================================");
                    System.out.println("1 - Adicionar livro");
                    System.out.println("2 - Listar livros");
                    System.out.println("3 - Buscar livro pelo titulo");
                    int op2 = sc.nextInt();
                    sc.nextLine();

                    switch (op2) {
                        case 1:
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

                        case 2:
                            System.out.println("\n==============================================================");
                            System.out.println("                      Livros cadastrados                      ");
                            System.out.println("==============================================================");

                            ArrayList<livros> listaDeLivros = livroDAO.selectLivro();
                            for (livros livro : listaDeLivros) {
                                System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() + ", Autores: " + livro.getAutores() + ", Tema: " + livro.getTema() + ", CPF do Empregado: " + livro.getEmpregadoCPF());
                            }
                            break;

                        case 3:
                            System.out.println("\n=============================================================");
                            System.out.println("                       Pesquisar Livro                       ");
                            System.out.println("=============================================================");
                            String tituloProcurado;
                            tituloProcurado = sc.nextLine();

                            ArrayList<livros> listaDeLivrosProcura = livroDAO.selectLivro();
                            for (livros livro : listaDeLivrosProcura) {
                                if(livro.getTitulo().equalsIgnoreCase(tituloProcurado))
                                    System.out.println("ID: " + livro.getId() + ", Título: " + livro.getTitulo() + ", Autores: " + livro.getAutores() + ", Tema: " + livro.getTema() + ", CPF do Empregado: " + livro.getEmpregadoCPF());
                            }
                            break;
                    }
                    break;

                case 3:
                    System.out.println("\n=============================================================");
                    System.out.println("                       Menu de Autores                       ");
                    System.out.println("=============================================================");
                    System.out.println("1 - Adicionar autor");
                    System.out.println("2 - Deletar autor");
                    System.out.println("3 - Mostrar todos autores");
                    System.out.println("4 - Alterar email do autor");
                    int op3 = sc.nextInt();
                    sc.nextLine();

                    switch (op3) {
                        case 1:
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

                        case 2:
                            System.out.println("\n===========================================================");
                            System.out.println("                      Deletando Autor                      ");
                            System.out.println("===========================================================");
                            System.out.println("Nome do autor: ");
                            nomeAutor = sc.nextLine();
                            autorDAO.deleteAutor(nomeAutor);
                            break;

                        case 3:
                        System.out.println("\n===============================================================");
                        System.out.println("                      Autores Cadastrados                      ");
                        System.out.println("===============================================================");
                        autorDAO.selectAutor();
                        break;

                        case 4:
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
                    }
                    break;

                case 4:
                    System.out.println("\n==============================================================");
                    System.out.println("                       Menu de Clientes                       ");
                    System.out.println("==============================================================");
                    break;

                case 5:
                    System.out.println("\n=============================================================");
                    System.out.println("                       Menu de Pedidos                       ");
                    System.out.println("=============================================================");
                    System.out.println("1 - Adicionar pedido");
                    System.out.println("2 - Deletar pedido");
                    System.out.println("3 - Mostrar todos autores");
                    System.out.println("4 - Alterar email do autor");
                    int op5 = sc.nextInt();
                    sc.nextLine();

                    switch (op5) {
                        case 1:
                            System.out.println("\n===============================================================");
                            System.out.println("                    Adicionando novo pedido                    ");
                            System.out.println("===============================================================");

                            System.out.println("\nPor favor entre com as informações abaixo: ");

                            System.out.print("\nID: ");
                            idPedido = sc.nextInt(); //Entrando com o id do pedido
                            sc.nextLine();

                            System.out.println("Livros: ");
                            livrosPedido = sc.nextLine(); //Entrando com os livros do pedido

                            System.out.print("Data: ");
                            dataPedido = sc.nextLine(); //Entrando com a data do pedido

                            System.out.println("CPF do Cliente");
                            clientePedido = sc.nextLine();

                            pedidos pedido = new pedidos(idPedido, livrosPedido, dataPedido, clientePedido);
                            pedidoDAO.insertpedidos(pedido);  // Inserindo na tabela o empregado criado
                            break;

                        case 2:
                            System.out.println("\n============================================================");
                            System.out.println("                      Deletando Pedido                      ");
                            System.out.println("============================================================");
                            System.out.println("CPF do cliente: ");
                            clientePedido = sc.nextLine();
                            pedidoDAO.deletePedido(clientePedido);
                            break;
                    }
                    break;

                case 6:
                    flag = false;
                    break;
            }
        }
    }
}
