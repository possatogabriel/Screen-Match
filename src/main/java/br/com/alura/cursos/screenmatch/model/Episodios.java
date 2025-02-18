package br.com.alura.cursos.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Episodios {
    private Integer temporada;
    private String titulo;
    private Integer numero;
    private LocalDate dataDeLancamento;
    private double avaliacao;

    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Episodios(Integer temporada, DadosDoEpisodio rEpisodio) {
        this.temporada = temporada;
        this.titulo = rEpisodio.titulo();
        this.numero = rEpisodio.numeroEpisodio();

        try {
            this.dataDeLancamento = LocalDate.parse(rEpisodio.dataDeLancamento());
        } catch (DateTimeParseException e) {
            this.dataDeLancamento = null;
        }

        try {
            this.avaliacao = Double.parseDouble(rEpisodio.avaliacao());
        } catch (NumberFormatException e) {
            this.avaliacao = 0.0;
        }
    }

    public Episodios() {
    }

    public Integer getTemporada() {
        return temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNumero() {
        return numero;
    }

    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    @Override
    public String toString() {
        return """
                (T%d E%d) %s - AVALIAÇÃO: %s - DATA DE LANÇAMENTO: %s""".formatted(getTemporada(), getNumero(), getTitulo(), getAvaliacao(), getDataDeLancamento().format(formato));
    }
}
