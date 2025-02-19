package br.com.alura.cursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDaSerie(@JsonAlias("Title") String titulo,
                           @JsonAlias("Year") String anoDeLancamento,
                           @JsonAlias("totalSeasons") Integer totalTemporadas,
                           @JsonAlias("Genre") String genero,
                           @JsonAlias("Actors") String atores,
                           @JsonAlias("Poster") String poster,
                           @JsonAlias("Plot") String sinopse,
                           @JsonAlias("imdbRating") String avaliacao,

                           @JsonProperty("imdbVotes") String votos){
}
