package br.com.alura.cursos.screenmatch.main;

import br.com.alura.cursos.screenmatch.model.*;
import br.com.alura.cursos.screenmatch.service.API;
import br.com.alura.cursos.screenmatch.service.Conversao;
import br.com.alura.cursos.screenmatch.service.IBuscador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main implements IBuscador {
    private final String TEMPORADA = "&season=";
    private final String API_KEY = "&apikey=16aca434";
    private final String URL_BASE = "https://www.omdbapi.com/?t=";

    private API api = new API();
    private Conversao conversor = new Conversao();
    private Scanner input = new Scanner(System.in);

    public void rodaAplicacao() {
        exibeMenuPrincipal();
    }

    private String exibeMensagem(String mensagem) {
        System.out.print(mensagem);
        return input.nextLine().replaceAll(" ", "+");
    }

    private Series retornaSerie() {
        var busca = exibeMensagem("Digite o nome da série que você quer procurar: ");
        var endereco = URL_BASE + busca + API_KEY;

        var informacoes = retornaInformacao(endereco, DadosDaSerie.class);
        return new Series(informacoes);
    }

    private void exibeMenuPrincipal() {
        var opcao = 1;

        while (opcao != 0) {
            System.out.println("""
                
                - - - - - - - - - - - - - -
                0 - SAIR DO PROGRAMA
                
                1 - BUSCAR SÉRIE
                2 - BUSCAR EPISÓDIOS
                - - - - - - - - - - - - - -
                """);
            System.out.print("Selecione uma opção: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 0:
                    System.out.println("PROGRAMA ENCERRADO.");
                    break;
                case 1:
                    var serie = retornaSerie();
                    System.out.println(serie);
                    break;
                case 2:
                    exibeEpisodios();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
                    break;
            }
        }
    }

    private void exibeEpisodios() {
        List<Temporadas> listaDeTemporadas = new ArrayList<>();
        var i = 1;

        var serie = retornaSerie();
        var busca = serie.getTitulo().replaceAll(" ", "+");

        for (i = 1; i <= serie.getTotalDeTemporadas(); i++) {
            var endereco = URL_BASE + busca + TEMPORADA + i + API_KEY;
            var informacoes = retornaInformacao(endereco, DadosDaTemporada.class);

            var temporada = new Temporadas(informacoes);
            listaDeTemporadas.add(temporada);
        }
        listaDeTemporadas.forEach(System.out::println);

        exibeMenuEpisodios(listaDeTemporadas);
    }

    private void exibeMenuEpisodios(List<Temporadas> listaDeTemporadas) {
        var opcao = 1;

        while (opcao != 0) {
            System.out.println("""
                
                - - - - - - - - - - - - - -
                0 - SAIR DO MENU DE EPISÓDIOS 
                
                1 - EXIBIR MAIORES AVALIAÇÕES
                2 - EXIBIR EPISÓDIOS A PARTIR DE UMA CERTA DATA
                3 - EXIBIR INFORMAÇÕES ADICIONAIS
                4 - PROCURAR EPISÓDIO POR TRECHO DO NOME
                - - - - - - - - - - - - - -
                """);
            System.out.print("Selecione uma opção: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    exibeMaioresAvaliacoes(listaDeTemporadas);
                    break;
                case 2:
                    exibePartindoDaData(listaDeTemporadas);
                    break;
                case 3:
                    exibeInformacaoAdicional(listaDeTemporadas);
                    break;
                case 4:
                    exibeTrechoDoNome(listaDeTemporadas);
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA, TENTE NOVAMENTE!");
                    break;
            }
        }
    }

    private List<Episodios> retornaEpisodios(List<Temporadas> listaDeTemporadas) {
        return listaDeTemporadas.stream()
                .flatMap(t -> t.getEpisodios().stream()
                        .map(e -> new Episodios(t.getTemporada(), e)))
                .collect(Collectors.toList());
    }

    private void exibeMaioresAvaliacoes(List<Temporadas> listaDeTemporadas) {
        System.out.println("- TOP 10 EPISÓDIOS COM MAIORES AVALIAÇÕES - \n");

        var listaDeEpisodios = retornaEpisodios(listaDeTemporadas);

        listaDeEpisodios.stream()
                .sorted(Comparator.comparing(Episodios::getAvaliacao).reversed())
                .limit(10)
                .forEach(System.out::println);
    }

    private void exibePartindoDaData(List<Temporadas> listaDeTemporadas) {
        System.out.print("A partir de que data você quer procurar? (Insira o dia, mês e ano, respectivamente) ");
        var data = input.nextLine().split(" ");
        System.out.println();

        var listaDeEpisodios = retornaEpisodios(listaDeTemporadas);

        var dia = Integer.parseInt(data[0]);
        var mes = Integer.parseInt(data[1]);
        var ano = Integer.parseInt(data[2]);

        var formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataBuscada = LocalDate.of(ano, mes, dia);

        listaDeEpisodios.stream()
                .filter(e -> e.getDataDeLancamento() != null && e.getDataDeLancamento().isAfter(dataBuscada))
                .forEach(e -> System.out.println("""
                        (T%d E%d) %s - DATA DE LANÇAMENTO: %s""".formatted(e.getTemporada(), e.getNumero(), e.getTitulo(), e.getDataDeLancamento().format(formato))));
    }

    private void exibeInformacaoAdicional(List<Temporadas> listaDeTemporadas) {
        var listaDeEpisodios = retornaEpisodios(listaDeTemporadas);

        DoubleSummaryStatistics est = listaDeEpisodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodios::getAvaliacao));

        System.out.println("""

                MÉDIA DA TEMPORADAS: %.1f
                QUANTIDADE DE NOTAS: %d

                MELHOR NOTA: %.1f
                PIOR NOTA: %.1f""".formatted(est.getAverage(), est.getCount(), est.getMax(), est.getMin()));
    }

    private void exibeTrechoDoNome(List<Temporadas> listaDeTemporadas) {
        var trecho = exibeMensagem("Digite um trecho do título de um episódio: ");

        var listaDeEpisodios = retornaEpisodios(listaDeTemporadas);

        Optional<Episodios> episodioBuscado = listaDeEpisodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trecho.toUpperCase()))
                .findFirst();

        if (episodioBuscado.isPresent()) {
            System.out.println("\nEPISÓDIO ENCONTRADO! :D");
            System.out.println("""
                        (T%d E%d) %s - AVALIAÇÃO: %s""".formatted(episodioBuscado.get().getTemporada(), episodioBuscado.get().getNumero(), episodioBuscado.get().getTitulo(), episodioBuscado.get().getAvaliacao()));
        } else {
            System.out.println("\nEPISÓDIO NÃO ENCONTRADO :(");
        }
    }

    @Override
    public <T> T retornaInformacao(String endereco, Class<T> classe) {
        var json = api.buscaDados(endereco);
        return conversor.converteDados(json, classe);
    }
}