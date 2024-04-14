import buscador.Consulta;
import buscador.Endereco;
import buscador.GeradorDeArquivo;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número de CEP para buscar: ");
        String cep = scanner.nextLine();
        Consulta consulta = new Consulta();

        try {
            Endereco endereco = consulta.buscaEndereco(cep);
            System.out.println(endereco);

            GeradorDeArquivo geradorDeArquivo = new GeradorDeArquivo();
            geradorDeArquivo.salvarJson(endereco);

        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
        }

    }
}