package br.com.alura.cursos.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDoEpisodio(@JsonAlias("Title") String titulo,
                              @JsonAlias("Episode") Integer numeroEpisodio,
                              @JsonAlias("Released") String dataDeLancamento,
                              @JsonAlias("imdbRating") String avaliacao) {
    @Override
    public String toString() {
        return """
                
                --- EP %d: %s""".formatted(numeroEpisodio, titulo);
    }
}
