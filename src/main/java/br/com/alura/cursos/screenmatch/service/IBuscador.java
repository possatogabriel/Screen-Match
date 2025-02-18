package br.com.alura.cursos.screenmatch.service;

public interface IBuscador {
    <T> T retornaInformacao(String busca, Class<T> classe);
}
