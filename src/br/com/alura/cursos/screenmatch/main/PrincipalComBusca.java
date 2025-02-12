package br.com.alura.cursos.screenmatch.main;

import br.com.alura.cursos.screenmatch.modelos.Titulo;
import br.com.alura.cursos.screenmatch.modelos.TituloOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        var busca = "";

        List<Titulo> listaDeTitulos = new ArrayList<>();

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.print("Escreva o(s) nome(s) do(s) filme(s) que você quer buscar/digite 'SAIR' para encerrar o programa: ");
            busca = input.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                System.out.println();
                System.out.println("Programa encerrado!");
                break;
            }

            try {
                var endereco = "https://www.omdbapi.com/?t=" + busca.replaceAll(" ", "+") + "&apikey=16aca434";

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                var json = response.body();

                TituloOMDB tituloOMDB = gson.fromJson(json, TituloOMDB.class);

                Titulo novoTitulo = new Titulo(tituloOMDB);
                System.out.println(novoTitulo);
                System.out.println();

                listaDeTitulos.add(novoTitulo);
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("ERRO: " + e.getMessage());
                break;
            }

        }

        FileWriter arquivo = new FileWriter("FilmesPesquisados.json");
        arquivo.write(gson.toJson(listaDeTitulos));
        arquivo.close();

        // System.out.println(json);
        // System.out.println("OMDB: " + tituloOMDB);
    }
}
