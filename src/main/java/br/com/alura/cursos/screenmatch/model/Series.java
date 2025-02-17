package br.com.alura.cursos.screenmatch.model;

import br.com.alura.cursos.screenmatch.service.API;
import br.com.alura.cursos.screenmatch.service.Conversao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Series {
    private String titulo;
    private String anoDeLancamento;
    private Integer totalDeTemporadas;
    private double avaliacaoIMDB;
    private String votosIMDB;

    private List<Temporadas> listaDeTemporadas = new ArrayList<>();

    private String busca = "";
    private final String TEMPORADA = "&season=";
    private final String API_KEY = "&apikey=16aca434";
    private final String ENDERECO = "https://www.omdbapi.com/?t=";

    private Episodios classeEpisodio = new Episodios();
    private API api = new API();
    private Conversao conversor = new Conversao();
    private Scanner input = new Scanner(System.in);

    public Series(DadosDaSerie rSerie) {
        this.titulo = rSerie.titulo();
        this.anoDeLancamento = rSerie.anoDeLancamento();
        this.totalDeTemporadas = rSerie.totalTemporadas();

        try {
            this.avaliacaoIMDB = Double.parseDouble(rSerie.avaliacao());
        } catch (NumberFormatException e) {
            this.avaliacaoIMDB = 0.0;
        }

        this.votosIMDB = rSerie.votos();
    }

    public Series() {
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public Integer getTotalDeTemporadas() {
        return totalDeTemporadas;
    }

    public double getAvaliacaoIMDB() {
        return avaliacaoIMDB;
    }

    public String getVotosIMDB() {
        return votosIMDB;
    }

    @Override
    public String toString() {
        return """
                
                SÉRIE: %s
                > ANO DE LANÇAMENTO: %s
                > TOTAL DE TEMPORADAS: %d
                > AVALIAÇÃO MÉDIA (IMDB): %.1f (%s VOTOS)""".formatted(getTitulo(), getAnoDeLancamento(), getTotalDeTemporadas(), getAvaliacaoIMDB(), getVotosIMDB());
    }

    public void exibeSerie() {
        System.out.printf("Escreva o temporada da série que você quer pesquisar: ");
        var nomeDaSerie = input.nextLine();

        busca = nomeDaSerie.replaceAll(" ", "+");

        var json = api.buscaDados(ENDERECO + busca + API_KEY);
        var informacoes = conversor.converteDados(json, DadosDaSerie.class);

        var serie = new Series(informacoes);
        System.out.println(serie);

        exibeTemporadas(serie.totalDeTemporadas, busca);

        classeEpisodio.exibeMaioresAvaliacoes(listaDeTemporadas);
    }

    public void exibeTemporadas(Integer totalDeTemporadas, String busca) {
        for (var i = 1; i <= totalDeTemporadas; i++) {
            var json = api.buscaDados(ENDERECO + busca + TEMPORADA + i + API_KEY);
            var informacoes = conversor.converteDados(json, DadosDaTemporada.class);

            var temporada = new Temporadas(informacoes);
            listaDeTemporadas.add(temporada);
        }
        listaDeTemporadas.forEach(System.out::println);
    }
}