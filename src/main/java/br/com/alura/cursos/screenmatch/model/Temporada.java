package br.com.alura.cursos.screenmatch.model;

import java.util.ArrayList;
import java.util.List;

public class Temporada {
    private String nomeSerie;
    private Integer temporada;
    private List<DadosDoEpisodio> episodios = new ArrayList<>();

    public Temporada(DadosDaTemporada rTemporada) {
        this.nomeSerie = rTemporada.nomeSerie();
        this.temporada = rTemporada.numeroTemporada();
        this.episodios = rTemporada.episodiosTemporada();
    }

    public Temporada() {
    }

    public String getNomeSerie() {
        return nomeSerie;
    }

    public Integer getTemporada() {
        return temporada;
    }

    public List<DadosDoEpisodio> getEpisodios() {
        return episodios;
    }

    public void setNomeSerie(String nomeSerie) {
        this.nomeSerie = nomeSerie;
    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public void setEpisodios(List<DadosDoEpisodio> episodios) {
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return """
                
                TEMPORADA: %d
                > EPISÓDIOS: %s""".formatted(getTemporada(), getEpisodios());
    }
}
