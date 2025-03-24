package br.com.alura.cursos.screenmatch.dto;

import java.time.LocalDate;

public record EpisodioDTO(Integer temporada,
                          String titulo,
                          Integer numeroEpisodio,
                          LocalDate dataDeLancamento,
                          double avaliacao) {
}
