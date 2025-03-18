package br.com.alura.cursos.screenmatch.main;

import br.com.alura.cursos.screenmatch.model.*;
import br.com.alura.cursos.screenmatch.repository.SerieRepository;
import br.com.alura.cursos.screenmatch.service.API;
import br.com.alura.cursos.screenmatch.service.Conversao;
import br.com.alura.cursos.screenmatch.service.IBuscador;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main implements IBuscador {
    private final String TEMPORADA = "&season=";
    private final String API_KEY = System.getenv("OMDB_APIKEY");
    private final String URL_BASE = "https://www.omdbapi.com/?t=";

    private API api = new API();
    private Conversao conversor = new Conversao();
    private Scanner input = new Scanner(System.in);

    private SerieRepository repositorio;
    private List<Serie> listaDeSeries = new ArrayList<>();

    public Main(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void rodaAplicacao() {
        exibeMenuPrincipal();
    }

    private String exibeMensagem(String mensagem) {
        System.out.print(mensagem);
        return input.nextLine();
    }

    private void exibeMenuPrincipal() {
        var opcao = 1;

        while (opcao != 0) {
            System.out.println("""
                
                -*-*-*-*-*-*-*-*-*-*-*-*-*-
                0 - SAIR DO PROGRAMA
                
                1 - BUSCAR SÉRIES
                2 - BUSCAR EPISÓDIOS 
                -*-*-*-*-*-*-*-*-*-*-*-*-*-
                """);
            opcao = Integer.parseInt(exibeMensagem("Selecione uma opção: "));

            switch (opcao) {
                case 0:
                    System.out.println("PROGRAMA ENCERRADO.");
                    break;
                case 1:
                    exibeMenuSerie();
                    break;
                case 2:
                    exibeMenuEpisodios();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
                    break;
            }
        }
    }

    private void exibeMenuSerie() {
        var opcao = 1;

        while (opcao != 0) {
            System.out.println("""
                
                - - - - - - - - - - - - - -
                0 - SAIR DO MENU
                
                1 - BUSCAR NOVA SÉRIE
                2 - PESQUISAR SÉRIE JÁ BUSCADA
                3 - EXIBIR TODAS SÉRIES JÁ BUSCADAS
                4 - EXIBIR SÉRIES POR ORDEM DE AVALIAÇÃO
                - - - - - - - - - - - - - -
                """);
            opcao = Integer.parseInt(exibeMensagem("Selecione uma opção: "));

            switch (opcao) {
                case 0:
                    System.out.println("VOLTANDO... ");
                    break;
                case 1:
                    exibeSerie();
                    break;
                case 2:
                    exibeMenuDePesquisa();
                    break;
                case 3:
                    listaSeriesBuscadas();
                    break;
                case 4:
                    exibeMelhoresSeries();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
                    break;
            }
        }
    }

    private Serie retornaSerieDaAPI() {
        var busca = exibeMensagem("Digite o nome da série que você quer procurar: ");
        var endereco = URL_BASE + busca.replaceAll(" ", "+") + API_KEY;

        var informacoes = retornaInformacao(endereco, DadosDaSerie.class);

        var serie = new Serie(informacoes);
        repositorio.save(serie);

        retornaTemporadas(serie);

        return serie;
    }

    private List<Temporada> retornaTemporadas(Serie serie) {
        List<Temporada> listaDeTemporadas = new ArrayList<>();

        for (var i = 1; i <= serie.getTotalDeTemporadas(); i++) {
            var endereco = URL_BASE + serie.getTitulo().replaceAll(" ", "+") + TEMPORADA + i + API_KEY;
            var informacoes = retornaInformacao(endereco, DadosDaTemporada.class);

            var temporada = new Temporada(informacoes);
            listaDeTemporadas.add(temporada);
        }

        var episodios = retornaEpisodios(listaDeTemporadas);

        serie.setEpisodios(episodios);
        repositorio.save(serie);

        return listaDeTemporadas;
    }

    private List<Episodio> retornaEpisodios(List<Temporada> listaDeTemporadas) {
        return listaDeTemporadas.stream()
                .flatMap(t -> t.getEpisodios().stream()
                        .map(e -> new Episodio(t.getTemporada(), e)))
                .collect(Collectors.toList());
    }

    private void exibeSerie() {
        var serie = retornaSerieDaAPI();

        System.out.println(serie);
    }

    private void exibeMenuDePesquisa() {
        var opcao = 1;

        while (opcao != 0) {
            System.out.println("""
                
                ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~  
                (0) - SAIR DO MENU
                
                (1) - POR TÍTULO
                (2) - POR ATOR E AVALIAÇÃO 
                (3) - POR CATEGORIA
                (4) - POR NÚMERO DE TEMPORADAS E AVALIAÇÃO MAIOR QUE 7,5
                ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~  
                """);
            opcao = Integer.parseInt(exibeMensagem("Selecione uma opção: "));

            switch (opcao) {
                case 0:
                    System.out.println("VOLTANDO... ");
                    break;
                case 1:
                    pesquisaSeriePorTitulo();
                    break;
                case 2:
                    pesquisaSeriePorAtor();
                    break;
                case 3:
                    pesquisaSeriePorCategoria();
                    break;
                case 4:
                    pesquisaSeriePorTemporadas();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
                    break;
            }
        }
    }

    private Optional<Serie> retornaSerieDoBanco() {
        var nome = exibeMensagem("\nDigite o nome da série: ");

        var serieBuscada = repositorio.achaPorTitulo(nome);

        if (serieBuscada.isPresent()) {
            System.out.println("\nSÉRIE ENCONTRADA! :D");
        } else {
            System.out.println("\nSÉRIE NÃO ENCONTRADA :(");
        }
        return serieBuscada;
    }

    private void pesquisaSeriePorTitulo() {
        var serieBuscada = retornaSerieDoBanco().get();
        System.out.println(serieBuscada);
    }

    private void pesquisaSeriePorAtor() {
        var nome = exibeMensagem("Digite o nome do ator: ");

        var avaliacao = Double.parseDouble(exibeMensagem("\nPesquisar séries a partir de qual avaliação? "));

        List<Serie> seriesEncontradas = repositorio.achaPorAtores(nome, avaliacao);
        seriesEncontradas.forEach(System.out::println);
    }

    private void pesquisaSeriePorCategoria() {
        var busca = exibeMensagem("Digite por qual gênero/categoria você quer procurar: ");
        System.out.println();

        var categoria = Categoria.fromPortugues(busca);

        List<Serie> seriesPorCategoria = repositorio.achaPorGenero(categoria);
        seriesPorCategoria.forEach(s -> System.out.println("SÉRIE: " + s.getTitulo() + " - GÊNERO: " + s.getGenero()));
    }

    private void pesquisaSeriePorTemporadas() {
        var temporadas = Integer.parseInt(exibeMensagem("Digite até quantas temporadas a série deve ter: "));
        System.out.println();

        List<Serie> seriesPorTemporada = repositorio.achaPorTemporadas(temporadas, 7.5);
        seriesPorTemporada.forEach(s -> System.out.println("SÉRIE: " + s.getTitulo() + " - TOTAL DE TEMPORADAS: " + s.getTotalDeTemporadas() + " (AVALIAÇÃO: " + s.getAvaliacaoIMDB() + ")"));
    }

    private void listaSeriesBuscadas() {
        System.out.println();

        listaDeSeries = repositorio.findAll();
        listaDeSeries.stream()
                .sorted(Comparator.comparing(Serie::getTitulo))
                .forEach(s -> System.out.println("SÉRIE: " + s.getTitulo() + " - DATA DE LANÇAMENTO: " + s.getAnoDeLancamento() + " (AVALIAÇÃO: " + s.getAvaliacaoIMDB() + ")"));
    }

    private void exibeMelhoresSeries() {
        System.out.println();

        List<Serie> melhoresSeries = repositorio.achaMelhoresSeries(5);
        melhoresSeries.stream()
                .forEach(s -> System.out.println("SÉRIE: " + s.getTitulo() + " - AVALIAÇÃO: " + s.getAvaliacaoIMDB()));
    }

    private void exibeMenuEpisodios() {
        var opcao = 1;

        while (opcao != 0) {
            System.out.println("""
                    
                    * * * * * * * * * * * * * *
                    0 - SAIR DO MENU
                    
                    1 - EXIBIR TODOS EPISÓDIOS 
                    2 - EXIBIR MAIORES AVALIAÇÕES
                    3 - EXIBIR EPISÓDIOS A PARTIR DE UMA CERTA DATA
                    4 - PROCURAR EPISÓDIO POR TRECHO DO NOME
                    5 - INFORMAÇÕES ADICIONAIS
                    * * * * * * * * * * * * * *
                    """);
            opcao = Integer.parseInt(exibeMensagem("Selecione uma opção: "));

            switch (opcao) {
                case 0:
                    System.out.println("VOLTANDO... ");
                    break;
                case 1:
                    exibeTodosEpisodios();
                    break;
                case 2:
                    exibeMelhoresEpisodios();
                    break;
                case 3:
                    exibeEpisodiosDaData();
                    break;
                case 4:
                    exibeTrechoDoEpisodio();
                    break;
                case 5:
                    exibeInformacaoAdicional();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
                    break;
            }
        }
    }

    private void exibeTodosEpisodios() {
        listaSeriesBuscadas();
        var serieBuscada = retornaSerieDoBanco();
        var serieEncontrada = serieBuscada.get();

        var episodios = retornaTemporadas(serieEncontrada);
        episodios.forEach(System.out::println);
    }

    private void exibeMelhoresEpisodios() {
        listaSeriesBuscadas();
        var serieBuscada = retornaSerieDoBanco();
        var serieEncontrada = serieBuscada.get();

        if (serieBuscada.isPresent()) {
            List<Episodio> melhoresEpisodios = repositorio.achaMelhoresEpisodios(serieEncontrada);

            System.out.println("\n- TOP 5 EPISÓDIOS COM MAIORES AVALIAÇÕES - \n");
            melhoresEpisodios.forEach(System.out::println);
        }
    }

    private void exibeEpisodiosDaData() {
        listaSeriesBuscadas();
        var serieBuscada = retornaSerieDoBanco();
        var serieEncontrada = serieBuscada.get();

        if (serieBuscada.isPresent()) {
            var data = exibeMensagem("A partir de que data você quer procurar? (Insira o dia, mês e ano, respectivamente) ").split(" ");
            System.out.println();

            var dia = Integer.parseInt(data[0]);
            var mes = Integer.parseInt(data[1]);
            var ano = Integer.parseInt(data[2]);

            LocalDate dataBuscada = LocalDate.of(ano, mes, dia);

            List<Episodio> episodiosAPartirDaData = repositorio.achaEpisodiosPorData(serieEncontrada, dataBuscada);

            episodiosAPartirDaData.forEach(System.out::println);
        }
    }

    private void exibeTrechoDoEpisodio() {
        var trecho = exibeMensagem("Digite um trecho do título de um episódio: ");

        var listaDeEpisodios = repositorio.achaPorTrecho(trecho);
        listaDeEpisodios.forEach(e -> System.out.println("""
                %s - (T%d E%d) %s""".formatted(e.getSerie().getTitulo(), e.getTemporada(), e.getNumero(), e.getTitulo())));
    }

    private void exibeInformacaoAdicional() {
        var serieEncontrada = retornaSerieDoBanco().get();
        var listaDeTemporadas = retornaTemporadas(serieEncontrada);
        var listaDeEpisodios = retornaEpisodios(listaDeTemporadas);

        DoubleSummaryStatistics est = listaDeEpisodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));

        System.out.println("""

                . . . . . . . . . . . . . .
                - SÉRIE: %s - 
                MÉDIA DA TEMPORADAS: %.1f
                TOTAL DE EPISÓDIOS: %d

                MELHOR NOTA: %.1f
                PIOR NOTA: %.1f
                . . . . . . . . . . . . . .""".formatted(serieEncontrada.getTitulo(), est.getAverage(), est.getCount(), est.getMax(), est.getMin()));
    }

    @Override
    public <T> T retornaInformacao(String endereco, Class<T> classe) {
        var json = api.buscaDados(endereco);
        return conversor.converteDados(json, classe);
    }
}