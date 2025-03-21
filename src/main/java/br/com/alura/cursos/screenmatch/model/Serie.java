package br.com.alura.cursos.screenmatch.model;

import br.com.alura.cursos.screenmatch.service.translator.ConversaoMyMemory;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "Series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomeDaSerie", unique = true)
    private String titulo;

    private String anoDeLancamento;
    private Integer totalDeTemporadas;

    @Enumerated(EnumType.STRING)
    private Categoria genero;

    private String atores;
    private String poster;

    @Column(length = 500)
    private String sinopse;

    private double avaliacaoIMDB;
    private String votosIMDB;

    @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL)
    private List<Episodio> episodios = new ArrayList<>();

    public Serie(SerieDadosAPI rSerie) {
        this.titulo = rSerie.titulo();
        this.anoDeLancamento = rSerie.anoDeLancamento();
        this.totalDeTemporadas = rSerie.totalTemporadas();
        this.genero = Categoria.fromOMDB(rSerie.genero().split(",")[0].trim());
        this.atores = rSerie.atores();
        this.poster = rSerie.poster();
        this.sinopse = ConversaoMyMemory.obterTraducao(rSerie.sinopse().trim());
        this.avaliacaoIMDB = OptionalDouble.of(Double.valueOf(rSerie.avaliacao())).orElse(0.0);
        this.votosIMDB = rSerie.votos();
    }

    public Serie() {
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public Integer getTotalDeTemporadas() {
        return totalDeTemporadas;
    }

    public double getAvaliacaoIMDB() {
        return avaliacaoIMDB;
    }

    public Categoria getGenero() {
        return genero;
    }

    public String getAtores() {
        return atores;
    }

    public String getPoster() {
        return poster;
    }

    public String getSinopse() {
        return sinopse;
    }

    public String getVotosIMDB() {
        return votosIMDB;
    }

    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAnoDeLancamento(String anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public void setTotalDeTemporadas(Integer totalDeTemporadas) {
        this.totalDeTemporadas = totalDeTemporadas;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public void setAtores(String atores) {
        this.atores = atores;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public void setAvaliacaoIMDB(double avaliacaoIMDB) {
        this.avaliacaoIMDB = avaliacaoIMDB;
    }

    public void setVotosIMDB(String votosIMDB) {
        this.votosIMDB = votosIMDB;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e  -> e.setSerie(this));
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return """
                
                SÉRIE: %s
                > ANO DE LANÇAMENTO: %s
                > TOTAL DE TEMPORADAS: %d
                > GÊNERO: %s
                > ATORES: %s
                > SINOPSE: %s
                > AVALIAÇÃO MÉDIA (IMDB): %.1f (%s VOTOS)""".formatted(getTitulo(), getAnoDeLancamento(), getTotalDeTemporadas(), getGenero(), getAtores(), getSinopse(), getAvaliacaoIMDB(), getVotosIMDB());
    }
}