package br.com.alura.cursos.screenmatch.service.translator;

import br.com.alura.cursos.screenmatch.service.API;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URLEncoder;

public class ConversaoMyMemory {
    public static String obterTraducao(String text) {
        ObjectMapper mapper = new ObjectMapper();
        API api = new API();
        DadosDaTraducao traducao;

        String texto = URLEncoder.encode(text);
        String langpair = URLEncoder.encode("en|pt-br");

        String endereco = "https://api.mymemory.translated.net/get?q=" + texto + "&langpair=" + langpair;
        String json = api.buscaDados(endereco);

        try {
            traducao = mapper.readValue(json, DadosDaTraducao.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return traducao.dadosResposta().textoTraduzido();
    }
}
