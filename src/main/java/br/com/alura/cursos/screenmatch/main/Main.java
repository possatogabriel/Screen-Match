package br.com.alura.cursos.screenmatch.main;

import br.com.alura.cursos.screenmatch.model.*;
import br.com.alura.cursos.screenmatch.repository.SerieRepository;
import br.com.alura.cursos.screenmatch.service.API;
import br.com.alura.cursos.screenmatch.service.Conversao;
import br.com.alura.cursos.screenmatch.service.IBuscador;

import java.util.*;
import java.util.stream.Collectors;

public class Main implements IBuscador {
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String TEMPORADA = "&season=";
    private final String API_KEY = "&apikey=16aca434";

    private API api = new API();
    private Conversao conversor = new Conversao();
    private Scanner input = new Scanner(System.in);

    private SerieRepository repositorio;

    public Main(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void rodaAplicacao() {
        exibeMenuPrincipal();
    }

    private void exibeMenuPrincipal() {
        var opcao = 1;

        while (opcao != 0) {
            System.out.println("""
                
                -*-*-*-*-*-*-*-*-*-*-*-*-
                       SCREEN-MATCH
                
                1 - BUSCAR SÉRIES
                
                9 - LIMPAR BANCO DE DADOS
                0 - FECHAR APLICAÇÃO
                -*-*-*-*-*-*-*-*-*-*-*-*-*-
                """);
            opcao = Integer.parseInt(exibeMensagem("Selecione uma opção: "));

            switch (opcao) {
                case 0:
                    System.out.println("PROGRAMA ENCERRADO.");
                    System.exit(0);
                    break;
                case 1:
                    buscaSerie();
                    break;
                case 9:
                    confirmaLimpezaNoBanco();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
                    break;
            }
        }
    }

    private String exibeMensagem(String mensagem) {
        System.out.print(mensagem);
        return input.nextLine();
    }

    private void buscaSerie() {
        var serie = retornaSerieDaAPI();

        System.out.println("SÉRIE ENCONTRADA! (NOME DA SÉRIE: " + serie.getTitulo() + ")");
    }

    private Serie retornaSerieDaAPI() {
        var busca = exibeMensagem("Digite o nome da série que você quer procurar: ");
        var endereco = URL_BASE + busca.replaceAll(" ", "+") + API_KEY;

        var informacoes = retornaInformacao(endereco, SerieDadosAPI.class);

        var serie = new Serie(informacoes);
        perguntaFrase(serie);
        var episodios = retornaEpisodios(retornaTemporadas(serie));
        serie.setEpisodios(episodios);

        repositorio.save(serie);
        return serie;
    }

    private void perguntaFrase(Serie serie) {
        var frase = exibeMensagem("Digite uma frase icônica da série: ");
        serie.setFrase(frase);

        var personagem = exibeMensagem("Digite o personagem que disse essa frase: ");
        serie.setPersonagem(personagem);
    }

    private List<Temporada> retornaTemporadas(Serie serie) {
        List<Temporada> listaDeTemporadas = new ArrayList<>();

        for (var i = 1; i <= serie.getTotalDeTemporadas(); i++) {
            var endereco = URL_BASE + serie.getTitulo().replaceAll(" ", "+") + TEMPORADA + i + API_KEY;
            var informacoes = retornaInformacao(endereco, TemporadaDadosAPI.class);

            var temporada = new Temporada(informacoes);
            listaDeTemporadas.add(temporada);
        }
        return listaDeTemporadas;
    }

    private List<Episodio> retornaEpisodios(List<Temporada> listaDeTemporadas) {
        return listaDeTemporadas.stream()
                .flatMap(t -> t.getEpisodios().stream()
                        .map(e -> new Episodio(t.getTemporada(), e)))
                .collect(Collectors.toList());
    }

    private void confirmaLimpezaNoBanco() {
        var resposta = exibeMensagem("Tem certeza que deseja deletar TODO banco de dados? ");

        if (resposta.contains("s")) {
            repositorio.deleteAll();
            System.out.println("VOCÊ ESCOLHEU: SIM");
            System.out.println("BANCO DE DADOS LIMPO COM SUCESSO!");
        } else {
            System.out.println("VOCÊ ESCOLHEU: NÃO");
        }
    }

    @Override
    public <T> T retornaInformacao(String endereco, Class<T> classe) {
        var json = api.buscaDados(endereco);
        return conversor.converteDados(json, classe);
    }
}