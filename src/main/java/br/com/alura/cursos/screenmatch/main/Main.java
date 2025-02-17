package br.com.alura.cursos.screenmatch.main;

import br.com.alura.cursos.screenmatch.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public void rodaAplicacao() {
        Series classeSerie = new Series();
        classeSerie.exibeSerie();

        /*List<DadosDoEpisodio> listaDadosEp = temporadas.stream()
                .flatMap(t -> t.episodiosTemporada().stream())
                .collect(Collectors.toList());

        System.out.println("\nTop 5 episódios: ");

        List<Episodios> lista = temporadas.stream()
                .flatMap(t -> t.episodiosTemporada().stream()
                        .map(e -> new Episodios(t.numeroTemporada(), e)))
                .collect(Collectors.toList());

        var lista2 = lista.stream()
                .sorted(Comparator.comparing(Episodios::getAvaliacao).reversed())
                .limit(5)
                .collect(Collectors.toList());
        lista2.forEach(System.out::println);

//        System.out.println("A partir de que ano você deseja ver os resultados? ");
//        var ano = input.nextInt();
//        input.close();
//
//        LocalDate dataBusca = LocalDate.of(ano, 1, 1);
//
//        DateTimeFormatter formatoBrasil = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//
//        lista.stream()
///               .filter( e -> e.getDataDeLancamento() != null && e.getDataDeLancamento().isAfter(dataBusca))
//              .sorted(Comparator.comparing(Episodio::getDataDeLancamento))
//              .forEach(e -> System.out.println("""
//                      %s (T%d E%d) - Data de Lançamento: %s""".formatted(e.getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getDataDeLancamento().format(formatoBrasil))));

        System.out.println("Escreva o nomeSerie do episódio que você quer encontrar: ");
        var trecho = input.nextLine();
        input.close();

        *//*Optional<Episodio> episodio = lista.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trecho.toUpperCase()))
                .findFirst();

        if (episodio.isPresent()) {
            System.out.println("Episódio encontrado!");
            System.out.println("""
                    %s (T%d E%d)""".formatted(episodio.get().getTitulo(), episodio.get().getTemporada(), episodio.get().getNumeroEpisodio()));
        } else {
            System.out.println("Episódio não encontrado :(");
        }*//*

        *//*Map<Integer, Double> avaliacaoDaTemporada = lista.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));

        System.out.println(avaliacaoDaTemporada);*//*

        DoubleSummaryStatistics est = lista.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodios::getAvaliacao));

        System.out.println("MÉDIA: " + est.getAverage());
        System.out.println("MIN DE NOTAS: " + est.getMin());
        System.out.println("MÁX DE NOTAS: " + est.getMax());*/
    }
}
