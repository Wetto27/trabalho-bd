package Model.Tables;

import Model.Classes.clientes;
import Model.Classes.empregados;
import Model.Classes.livros;
import Model.Tables.DAO.*;

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

        // Informacoes do cliente
        String nomeCliente;
        String emailCliente;
        String cpfCliente;
        int historico;
        String fk_empregadoCPF;

        // Informacoes do livro
        int idLivro;
        String titulo;
        String autor;
        String tema;
        String empregadoCPF;

        // Informacoes do autor
        int idAutor;


        while (flag) {
            System.out.println("\n+------------------------------------------------------------+");
            System.out.println("|                   Sistema da Biblioteca                    |");
            System.out.println("+------------------------------------------------------------+");
            System.out.println("\nPor favor selecione uma das opções abaixo: ");
            System.out.println("1 - Adicionar funcionario");
            System.out.println("2 - Deletar funcionario");
            System.out.println("3 - Sair");
            System.out.print("\nOpção: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:
                    System.out.println("\n==============================================================");
                    System.out.println("                    Criação de novo empregado                 ");
                    System.out.println("==============================================================");

                    System.out.println("\nPor favor entre com as informações abaixo: ");

                    System.out.print("\nNome: ");
                    nomeFunc = sc.nextLine(); //Entrando com o nome do empregado

                    System.out.println("Entre com o email: ");
                    emailFunc = sc.nextLine(); //Entrando com o email do empregado

                    System.out.print("Entre com o CPF: ");
                    cpfFunc = sc.nextLine(); //Entrando com o CPF do empregado

                    empregados empregado = new empregados(cpfFunc, nomeFunc, nomeFunc);
                    empregadosDAO.insertEmpregado(empregado); // Inserindo na tabela o empregado criado
                    break;

                case 2:
                    System.out.println("Digite o CPF do funcionario: ");
                    cpfFunc = sc.nextLine();
                    empregadosDAO.deleteEmpregado(cpfFunc);
                    break;

                case 3:
                    flag = false;
                    break;
            }


        }


    }
}
