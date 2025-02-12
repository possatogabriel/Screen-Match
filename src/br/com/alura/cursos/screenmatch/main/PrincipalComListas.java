package br.com.alura.cursos.screenmatch.main;

import br.com.alura.cursos.screenmatch.modelos.Episodio;
import br.com.alura.cursos.screenmatch.modelos.Filme;
import br.com.alura.cursos.screenmatch.modelos.Serie;
import br.com.alura.cursos.screenmatch.modelos.Titulo;

import java.util.*;

public class PrincipalComListas {
    public static void main(String[] args) {
        var serieUm = new Serie("Girl from Nowhere", 2019);

        var episodio = new Episodio("Verdade Nua e Crua");

        var filmeUm = new Filme("Dune - Part I", 2021);
        var filmeDois = new Filme("Dune - Part II", 2024);

        filmeUm.avaliaTitulo(9);
        filmeDois.avaliaTitulo(10);

        List<Titulo> listaDeTitulos = new LinkedList<>();
        listaDeTitulos.add(filmeDois);
        listaDeTitulos.add(filmeUm);
        listaDeTitulos.add(serieUm);

        ArrayList<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("Robert Eggers");
        buscaPorArtista.add("Adam Sandler");
        buscaPorArtista.add("Denis Villenueve");

        for (Titulo item: listaDeTitulos) {
            System.out.print("TÍTULO: " + item);

            if (item instanceof Filme filme) {
                System.out.println(" -> ESTRELAS: " + filme.getClassificacao() + "/5.0");
            }
        }

        System.out.println();
        Collections.sort(listaDeTitulos);
        System.out.println("Lista de títulos organizados por ORDEM ALFABÉTICA: " + listaDeTitulos);

        listaDeTitulos.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        System.out.println("Lista de títulos organizados por ORDEM CRESCENTE PELO ANO: " + listaDeTitulos);

        System.out.println();
        System.out.println("Lista de diretores ANTES da ordenação: " + buscaPorArtista);

        Collections.sort(buscaPorArtista);
        System.out.println("Lista de diretores DEPOIS da ordenação: " + buscaPorArtista);
    }
}
