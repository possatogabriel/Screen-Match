package br.com.alura.cursos.screenmatch.service.translator;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosDaResposta( @JsonAlias(value = "translatedText") String textoTraduzido) {
}
