import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Correlacionador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String[]> data = readCsv("E:\\VSCODE\\Correlacionador\\banco_de_dados.csv");
        //"E:\VSCODE\Correlacionador\banco_de_dados.csv"
        if (data == null) {
            System.out.println("Erro ao ler o arquivo CSV.");
            return;
        }

        while (true) {
            System.out.println("Selecione uma opção:");
            System.out.println("1. Ordenar código de natureza de rendimento (BubbleSort)");
            System.out.println("2. Ordenar código de serviço (SelectionSort)");
            System.out.println("3. Retornar código de natureza por código de serviço");
            System.out.println("4. Encerrar o programa");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    bubbleSort(data, 0);
                    printData(data);
                    break;
                case 2:
                    selectionSort(data, 1);
                    printData(data);
                    break;
                case 3:
                    System.out.print("Digite o código de serviço: ");
                    String serviceCode = scanner.next();
                    String natureCode = findNatureCodeByServiceCode(data, serviceCode);
                    if (natureCode != null) {
                        System.out.println("Código de natureza correspondente: " + natureCode);
                    } else {
                        System.out.println("Código de serviço não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Encerrando o programa.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static List<String[]> readCsv(String fileName) {
        List<String[]> data = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }

    private static void bubbleSort(List<String[]> data, int columnIndex) {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (data.get(j)[columnIndex].compareTo(data.get(j + 1)[columnIndex]) > 0) {
                    String[] temp = data.get(j);
                    data.set(j, data.get(j + 1));
                    data.set(j + 1, temp);
                }
            }
        }
    }

    private static void selectionSort(List<String[]> data, int columnIndex) {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (data.get(j)[columnIndex].compareTo(data.get(minIdx)[columnIndex]) < 0) {
                    minIdx = j;
                }
            }
            String[] temp = data.get(minIdx);
            data.set(minIdx, data.get(i));
            data.set(i, temp);
        }
    }

    private static String findNatureCodeByServiceCode(List<String[]> data, String serviceCode) {
        for (String[] row : data) {
            if (row[1].equals(serviceCode)) {
                return row[0];
            }
        }
        return null;
    }

    private static void printData(List<String[]> data) {
        for (String[] row : data) {
            System.out.println(String.join(",", row));
        }
    }
}
