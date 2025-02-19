package br.com.alura.cursos.screenmatch.service.translator;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDaTraducao(@JsonAlias(value = "responseData") DadosDaResposta dadosResposta) {
}
