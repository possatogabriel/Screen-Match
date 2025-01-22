import br.com.alura.cursos.screenmatch.calculos.CalculadoraDeTempo;
import br.com.alura.cursos.screenmatch.modelos.Episodio;
import br.com.alura.cursos.screenmatch.modelos.Filme;
import br.com.alura.cursos.screenmatch.modelos.Serie;
import br.com.alura.cursos.screenmatch.partilhados.Recomendacao;

public class Principal {
    public static void main(String[] args) {
        Serie serieUm = new Serie();
        serieUm.setNome("Girl from Nowhere");
        serieUm.setAnoDeLancamento(2019);
        serieUm.setTemporadas(2);
        serieUm.setEpisodiosPorTemporada(8);
        serieUm.setMinutosPorEpisodio(40);
        serieUm.setIncluidoNoPlano(true);
        serieUm.avaliaTitulo(10);
        serieUm.avaliaTitulo(10);
        serieUm.avaliaTitulo(10);
        serieUm.exibeFichaTecnica();
        System.out.println();

        Episodio episodio = new Episodio();
        episodio.setNome("Verdade Nua e Crua");
        episodio.setNumero(1);
        episodio.setSerie(serieUm);
        episodio.setTotalVisualizacoes(666);

        Filme filmeUm = new Filme();
        filmeUm.setNome("Dune: Part I");
        filmeUm.setAnoDeLancamento(2021);
        filmeUm.setDuracaoEmMinutos(155);
        filmeUm.setIncluidoNoPlano(false);
        filmeUm.avaliaTitulo(1);
        filmeUm.avaliaTitulo(1);
        filmeUm.avaliaTitulo(1);
        filmeUm.exibeFichaTecnica();
        System.out.println();

        Filme filmeDois = new Filme();
        filmeDois.setNome("Dune: Part II");
        filmeDois.setAnoDeLancamento(2024);
        filmeDois.setDuracaoEmMinutos(166);
        filmeDois.setIncluidoNoPlano(false);
        filmeDois.avaliaTitulo(10);
        filmeDois.avaliaTitulo(10);
        filmeDois.avaliaTitulo(10);
        filmeDois.exibeFichaTecnica();
        System.out.println();

        CalculadoraDeTempo calculadora = new CalculadoraDeTempo();
        calculadora.inclui(filmeUm);
        calculadora.inclui(filmeDois);
        calculadora.inclui(serieUm);

        Recomendacao recomendacao = new Recomendacao();
        recomendacao.recomenda(filmeUm);
        recomendacao.recomenda(episodio);

        System.out.println("""
               > Duração total para maratonar os dois filmes (%s e %s) e a série (%s): %d min""".formatted(filmeUm.getNome(), filmeDois.getNome(), serieUm.getNome(), calculadora.getTempoTotal()));
    }
}