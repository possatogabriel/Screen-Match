package br.com.alura.cursos.screenmatch.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Episodios {
    private Integer temporada;
    private String titulo;
    private Integer numero;
    private LocalDate dataDeLancamento;
    private double avaliacao;

    private List<Episodios> listaDeEpisodios = new ArrayList<>();

    private Scanner input = new Scanner(System.in);

    public Episodios(Integer temporada, DadosDoEpisodio rEpisodio) {
        this.temporada = temporada;
        this.titulo = rEpisodio.titulo();
        this.numero = rEpisodio.numeroEpisodio();

        try {
            this.dataDeLancamento = LocalDate.parse(rEpisodio.dataDeLancamento());
        } catch (DateTimeParseException e) {
            this.dataDeLancamento = null;
        }

        try {
            this.avaliacao = Double.parseDouble(rEpisodio.avaliacao());
        } catch (NumberFormatException e) {
            this.avaliacao = 0.0;
        }
    }

    public Episodios() {
    }

    public Integer getTemporada() {
        return temporada;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNumero() {
        return numero;
    }

    public LocalDate getDataDeLancamento() {
        return dataDeLancamento;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    @Override
    public String toString() {
        return """
                (T%d E%d) %s - AVALIAÇÃO: %s""".formatted(getTemporada(), getNumero(), getTitulo(), getAvaliacao());
    }

    public void exibeMaioresAvaliacoes(List<Temporadas> listaDeTemporadas) {
        System.out.println("\n- TOP 5 EPISÓDIOS COM MAIORES AVALIAÇÕES - ");

        listaDeEpisodios = listaDeTemporadas.stream()
                        .flatMap(t -> t.getEpisodios().stream()
                                .map(e -> new Episodios(t.getTemporada(), e)))
                        .collect(Collectors.toList());

        listaDeEpisodios.stream()
                .sorted(Comparator.comparing(Episodios::getAvaliacao).reversed())
                .limit(5)
                .forEach(System.out::println);

        exibeTrechoDoNome(listaDeEpisodios);
        exibePartindoDaData(listaDeEpisodios);
        avaliaTemporada(listaDeEpisodios);
    }

    public void exibeTrechoDoNome(List<Episodios> listaDeEpisodios) {
        System.out.print("\nDigite um trecho do título de um episódio: ");
        var trecho = input.nextLine();

        Optional<Episodios> episodioBuscado = listaDeEpisodios.stream()
                .filter(e -> e.getTitulo().toUpperCase().contains(trecho.toUpperCase()))
                .findFirst();

        if (episodioBuscado.isPresent()) {
            System.out.println("\nEPISÓDIO ENCONTRADO! :D");
            System.out.println("""
                    (T%d E%d) %s""".formatted(episodioBuscado.get().getTemporada(), episodioBuscado.get().getNumero(), episodioBuscado.get().getTitulo()));
        } else {
            System.out.println("\nEPISÓDIO NÃO ENCONTRADO :(");
        }
    }

    public void exibePartindoDaData(List<Episodios> listaDeEpisodios) {
        System.out.print("\nA partir de que data você quer procurar? (Insira o dia, mês e ano, respectivamente) ");
        var busca = input.nextLine();
        var data = busca.split(" ");

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

    public void avaliaTemporada(List<Episodios> listaDeEpisodios) {
        DoubleSummaryStatistics est = listaDeEpisodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodios::getAvaliacao));

        System.out.println("""
                
                MÉDIA DA TEMPORADAS: %.1f
                QUANTIDADE DE NOTAS: %d
                
                MELHOR NOTA: %.1f
                PIOR NOTA: %.1f""".formatted(est.getAverage(), est.getCount(), est.getMax(), est.getMin()));
    }
}
