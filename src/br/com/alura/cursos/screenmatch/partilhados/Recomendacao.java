package br.com.alura.cursos.screenmatch.partilhados;

public class Recomendacao {
    public void recomenda(Classificavel classificavel) {
        if (classificavel.getClassificacao() >= 4) {
            System.out.println("> O título é MUITO recomendado!");
        } else if (classificavel.getClassificacao() >= 2) {
            System.out.println("> O título é RAZOÁVELMENTE recomendado!");
        } else {
            System.out.println("> O título NÃO é tão recomendado! Assista depois!");
        }
    }
}
