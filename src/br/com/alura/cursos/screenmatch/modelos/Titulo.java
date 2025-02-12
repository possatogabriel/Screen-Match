package br.com.alura.cursos.screenmatch.modelos;

public class Titulo implements Comparable<Titulo> {
    private String nome;
    private int anoDeLancamento;
    private double somaAvaliacoes;
    private int totalDeAvaliacoes;
    private int duracaoEmMinutos;
    private boolean incluidoNoPlano;

    public Titulo(String nome, int anoDeLancamento) {
        this.nome = nome;
        this.anoDeLancamento = anoDeLancamento;
    }

    public Titulo(TituloOMDB tituloOMDB) {
        this.nome = tituloOMDB.title();
        this.anoDeLancamento = Integer.valueOf(tituloOMDB.year());
        this.duracaoEmMinutos = Integer.valueOf(tituloOMDB.runtime().substring(0, 3));
    }

    public String getNome() {
        return nome;
    }

    public int getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public boolean isIncluidoNoPlano() {
        return incluidoNoPlano;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public int getTotalDeAvaliacoes() {
        return totalDeAvaliacoes;
    }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) {
        this.incluidoNoPlano = incluidoNoPlano;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public void exibeFichaTecnica() {
        String mensagem = """
                **********************************
                Nome do título: %s
                
                Ano de lançamento: %d
                
                Duração do título: %d min
                
                Nota média do título: %.1f
                
                Total de avaliações: %d
                **********************************""".formatted(getNome(), getAnoDeLancamento(), getDuracaoEmMinutos(), retornaMedia(), getTotalDeAvaliacoes());

        System.out.println(mensagem);
    }

    public void avaliaTitulo(double nota) {
        somaAvaliacoes += nota;
        totalDeAvaliacoes++;
    }

    public double retornaMedia() {
        return somaAvaliacoes / totalDeAvaliacoes;
    }

    @Override
    public String toString() {
        return this.getNome() + " (" + this.getAnoDeLancamento() + ") - " + this.getDuracaoEmMinutos() + " min";
    }

    @Override
    public int compareTo(Titulo nomeDoOutroTitulo) {
        return this.getNome().compareTo(nomeDoOutroTitulo.getNome());
    }
}
