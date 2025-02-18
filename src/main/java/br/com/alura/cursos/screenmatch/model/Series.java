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

    public Series(DadosDaSerie rSerie) {
        this.titulo = rSerie.titulo();
        this.anoDeLancamento = rSerie.anoDeLancamento();
        this.totalDeTemporadas = rSerie.totalTemporadas();
        this.votosIMDB = rSerie.votos();

        try {
            this.avaliacaoIMDB = Double.parseDouble(rSerie.avaliacao());
        } catch (NumberFormatException e) {
            this.avaliacaoIMDB = 0.0;
        }
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
}