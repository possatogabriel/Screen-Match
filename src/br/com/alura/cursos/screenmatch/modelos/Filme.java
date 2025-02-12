package br.com.alura.cursos.screenmatch.modelos;

import br.com.alura.cursos.screenmatch.partilhados.Classificavel;

public class Filme extends Titulo implements Classificavel {
    private String diretor;

    public Filme(String nome, int anoDeLancamento) {
        super(nome, anoDeLancamento);
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @Override
    public double getClassificacao() {
        return retornaMedia() / 2;
    }
}
