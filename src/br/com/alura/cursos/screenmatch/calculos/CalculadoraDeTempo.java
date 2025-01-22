package br.com.alura.cursos.screenmatch.calculos;

import br.com.alura.cursos.screenmatch.modelos.Titulo;

public class CalculadoraDeTempo {
    private int tempoTotal;

    public int getTempoTotal() {
        return this.tempoTotal;
    }

    public void inclui(Titulo titulo) {
    this.tempoTotal += titulo.getDuracaoEmMinutos();
    }
}
