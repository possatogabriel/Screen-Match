package br.com.alura.cursos.screenmatch.service;

public interface IConversor {
    <T> T converteDados(String json, Class<T> classe);
}
