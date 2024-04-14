package buscador;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consulta {

    public Endereco buscaEndereco(String cep) {
        if (!validarCep(cep)) {
            throw new IllegalArgumentException("CEP inválido. O CEP deve conter apenas números e ter 8 dígitos.");
        }

        URI end = URI.create("https://viacep.com.br/ws/" + cep + "/json/");
        HttpRequest request = HttpRequest.newBuilder().uri(end).build();
        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Endereco.class);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível encontrar o endereço.");
        }
    }

    private boolean validarCep(String cep) {
        if (cep.length() != 8) {
            return false;
        }
        for (char c : cep.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}

