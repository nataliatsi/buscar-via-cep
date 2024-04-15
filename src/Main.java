import buscador.Consulta;
import buscador.Endereco;
import buscador.GeradorDeArquivo;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o(s) número(s) de CEP para buscar (separe múltiplos CEPs por vírgula): ");
        String input = scanner.nextLine();

        String[] ceps = input.split("[,\\s]+");

        Consulta consulta = new Consulta();

        for (String cep : ceps) {
            try {
                cep = cep.trim();
                Endereco endereco = consulta.buscaEndereco(cep);

                System.out.println("------------------------");
                System.out.printf("%-12s: %s\n", "CEP", endereco.cep());
                System.out.printf("%-12s: %s\n", "Logradouro", endereco.logradouro());
                System.out.printf("%-12s: %s\n", "Complemento", endereco.complemento());
                System.out.printf("%-12s: %s\n", "Bairro", endereco.bairro());
                System.out.printf("%-12s: %s\n", "Localidade", endereco.localidade());
                System.out.printf("%-12s: %s\n", "UF", endereco.uf());
                System.out.println("------------------------");

                GeradorDeArquivo geradorDeArquivo = new GeradorDeArquivo();
                geradorDeArquivo.salvarJson(endereco);

            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage() + " Ignorando o CEP: " + cep);
            } catch (RuntimeException | IOException e) {
                System.out.println("Erro ao buscar o endereço para o CEP " + cep + ": " + e.getMessage());
            }

        }
    }
}