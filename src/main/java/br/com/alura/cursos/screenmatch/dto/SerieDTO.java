package br.com.alura.cursos.screenmatch.dto;

import br.com.alura.cursos.screenmatch.model.Categoria;

public record SerieDTO(Long id,
                       String titulo,
                       String anoDeLancamento,
                       String frase,
                       String personagem,
                       Integer totalDeTemporadas,
                       Categoria genero,
                       String atores,
                       String poster,
                       String sinopse,
                       double avaliacaoIMDB,
                       String votosIMDB) {
}
