package br.com.alura.cursos.screenmatch.model;

import java.util.ArrayList;
import java.util.List;

public class Temporadas {
    private String nomeSerie;
    private Integer temporada;
    private List<DadosDoEpisodio> episodios = new ArrayList<>();

    public Temporadas(DadosDaTemporada rTemporada) {
        this.nomeSerie = rTemporada.nomeSerie();
        this.temporada = rTemporada.numeroTemporada();
        this.episodios = rTemporada.episodiosTemporada();
    }

    public Temporadas() {
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

    @Override
    public String toString() {
        return """
                
                TEMPORADA: %d
                > EPISÓDIOS: %s""".formatted(getTemporada(), getEpisodios());
    }
}
