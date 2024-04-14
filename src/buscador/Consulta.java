package buscador;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Consulta {

    public Endereco buscaEndereco(String cep){
        URI end = URI.create("https://viacep.com.br/ws/" + cep + "/json/");
        HttpRequest request = HttpRequest.newBuilder().uri(end).build();
        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Endereco.class);
        } catch (Exception e){
            throw new RuntimeException("Não foi possível encontrar o endereço.");
        }
    }
}
