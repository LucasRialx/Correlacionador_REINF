import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    // URL de conexão com o banco de dados MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/meu_banco_de_dados";
    // Credenciais de usuário e senha para acessar o banco de dados
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Lê os dados do banco de dados
        List<String[]> data = readDatabase();

        if (data == null) {
            System.out.println("Erro ao ler o banco de dados.");
            return;
        }

        while (true) {
            // Exibe o menu de opções
            System.out.println("Selecione uma opção:");
            System.out.println("1. Ordenar código de natureza de rendimento (BubbleSort)");
            System.out.println("2. Ordenar código de serviço (SelectionSort)");
            System.out.println("3. Retornar código de natureza e descrição por código de serviço");
            System.out.println("4. Encerrar o programa");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Ordena os dados pelo código de natureza de rendimento usando BubbleSort
                    bubbleSort(data, 0);
                    printData(data);
                    break;
                case 2:
                    // Ordena os dados pelo código de serviço usando SelectionSort
                    selectionSort(data, 2);
                    printData(data);
                    break;
                case 3:
                    // Busca o código de natureza e descrição pelo código de serviço fornecido
                    System.out.print("Digite o código de serviço: ");
                    String serviceCode = scanner.next();
                    String[] result = findNatureCodeAndDescriptionByServiceCode(data, serviceCode);
                    if (result != null) {
                        System.out.println("Código de natureza correspondente: " + result[0]);
                        System.out.println("Descrição correspondente: " + result[1]);
                    } else {
                        System.out.println("Código de serviço não encontrado.");
                    }
                    break;
                case 4:
                    // Encerra o programa
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    return;
                default:
                    // Mensagem de erro para opção inválida
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    // Método para ler os dados do banco de dados
    private static List<String[]> readDatabase() {
        List<String[]> data = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT cod_nat_rend, descricao, cod_serv FROM rendimentos")) {

            // Percorre o resultado da consulta e adiciona os dados à lista
            while (resultSet.next()) {
                String[] row = {
                        resultSet.getString("cod_nat_rend"),
                        resultSet.getString("descricao"),
                        resultSet.getString("cod_serv")
                };
                data.add(row);
            }
        } catch (SQLException e) {
            // Imprime o erro se ocorrer uma exceção SQL
            e.printStackTrace();
            return null;
        }
        return data;
    }

    // Método para ordenar os dados usando o algoritmo BubbleSort
    private static void bubbleSort(List<String[]> data, int columnIndex) {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data.get(j)[columnIndex].compareTo(data.get(j + 1)[columnIndex]) > 0) {
                    // Troca os elementos se estiverem fora de ordem
                    String[] temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                }
            }
        }
    }

    // Método para ordenar os dados usando o algoritmo SelectionSort
    private static void selectionSort(List<String[]> data, int columnIndex) {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (data.get(j)[columnIndex].compareTo(data.get(minIdx)[columnIndex]) < 0) {
                    // Encontra o índice do menor elemento
                    minIdx = j;
                }
            }
            // Troca os elementos
            String[] temp = data.get(minIdx);
            data.set(minIdx, data.get(i));
            data.set(i, temp);
        }
    }

    // Método para buscar o código de natureza e descrição pelo código de serviço
    private static String[] findNatureCodeAndDescriptionByServiceCode(List<String[]> data, String serviceCode) {
        for (String[] row : data) {
            // Procura pelo código de serviço na lista de dados
            if (row[2].equals(serviceCode)) {
                return new String[]{row[0], row[1]};
            }
        }
        return null;
    }

    // Método para imprimir os dados
    private static void printData(List<String[]> data) {
        for (String[] row : data) {
            // Imprime cada linha de dados formatada
            System.out.println(String.join(", ", row));
        }
    }
}
