package br.com.alura.cursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDaTemporada(@JsonAlias("Title") String nomeSerie,
                               @JsonAlias("Season") Integer numeroTemporada,
                               @JsonAlias("Episodes") List<DadosDoEpisodio> episodiosTemporada) {
}
