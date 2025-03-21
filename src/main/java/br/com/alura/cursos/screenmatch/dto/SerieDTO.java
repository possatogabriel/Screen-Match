package br.com.alura.cursos.screenmatch.dto;

import br.com.alura.cursos.screenmatch.model.Categoria;

public record SerieDTO(Long id,
                       String titulo,
                       String anoDeLancamento,
                       Integer totalDeTemporadas,
                       Categoria genero,
                       String atores,
                       String poster,
                       String sinopse,
                       double avaliacaoIMDB,
                       String votosIMDB) {
}
