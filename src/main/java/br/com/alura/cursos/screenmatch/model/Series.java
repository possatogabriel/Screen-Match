package br.com.alura.cursos.screenmatch.model;

import br.com.alura.cursos.screenmatch.service.translator.ConversaoMyMemory;

import java.util.*;

public class Series {
    private String titulo;
    private String anoDeLancamento;
    private Integer totalDeTemporadas;
    private Categoria genero;
    private String atores;
    private String poster;
    private String sinopse;
    private double avaliacaoIMDB;
    private String votosIMDB;

    public Series(DadosDaSerie rSerie) {
        this.titulo = rSerie.titulo();
        this.anoDeLancamento = rSerie.anoDeLancamento();
        this.totalDeTemporadas = rSerie.totalTemporadas();
        this.genero = Categoria.fromString(rSerie.genero().split(",")[0].trim());
        this.atores = rSerie.atores();
        this.poster = rSerie.poster();
        this.sinopse = ConversaoMyMemory.obterTraducao(rSerie.sinopse().trim());
        this.avaliacaoIMDB = OptionalDouble.of(Double.valueOf(rSerie.avaliacao())).orElse(0.0);
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

    public Categoria getGenero() {
        return genero;
    }

    public String getAtores() {
        return atores;
    }

    public String getPoster() {
        return poster;
    }

    public String getSinopse() {
        return sinopse;
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
                > GÊNERO: %s
                > ATORES: %s
                > SINOPSE: %s
                > AVALIAÇÃO MÉDIA (IMDB): %.1f (%s VOTOS)""".formatted(getTitulo(), getAnoDeLancamento(), getTotalDeTemporadas(), getGenero(), getAtores(), getSinopse(), getAvaliacaoIMDB(), getVotosIMDB());
    }
}