package br.com.alura.cursos.screenmatch.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Entity
@Table(name = "episodios")
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer temporada;
    private String titulo;
    private Integer numero;
    private LocalDate dataDeLancamento;
    private double avaliacao;

    @Transient
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @ManyToOne
    private Serie serie;

    public Episodio(Integer temporada, DadosDoEpisodio rEpisodio) {
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

    public Episodio() {
    }

    public Long getId() {
        return id;
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

    public Serie getSerie() {
        return serie;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setDataDeLancamento(LocalDate dataDeLancamento) {
        this.dataDeLancamento = dataDeLancamento;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return """
                (T%d E%d) %s - AVALIAÇÃO: %s - DATA DE LANÇAMENTO: %s""".formatted(getTemporada(), getNumero(), getTitulo(), getAvaliacao(), getDataDeLancamento().format(formato));
    }
}
