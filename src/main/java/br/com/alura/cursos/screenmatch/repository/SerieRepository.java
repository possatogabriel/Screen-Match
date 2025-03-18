package br.com.alura.cursos.screenmatch.repository;

import br.com.alura.cursos.screenmatch.model.Categoria;
import br.com.alura.cursos.screenmatch.model.Episodio;
import br.com.alura.cursos.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    @Query("SELECT s FROM Serie s WHERE s.titulo ILIKE %:tituloDaSerie%")
    Optional<Serie> achaPorTitulo(String tituloDaSerie);

    @Query("SELECT s FROM Serie s WHERE s.atores ILIKE %:nomeDosAtores% AND s.avaliacaoIMDB >= :avaliacao")
    List<Serie> achaPorAtores(String nomeDosAtores, Double avaliacao);

    @Query("SELECT s FROM Serie s ORDER BY s.avaliacaoIMDB DESC LIMIT :limite")
    List<Serie> achaMelhoresSeries(Integer limite);

    @Query("SELECT s FROM Serie s WHERE s.genero = :categoria")
    List<Serie> achaPorGenero(Categoria categoria);

    @Query("SELECT s FROM Serie s WHERE s.totalDeTemporadas <= :temporadas AND s.avaliacaoIMDB >= :avaliacao")
    List<Serie> achaPorTemporadas(Integer temporadas, Double avaliacao);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trecho%")
    List<Episodio> achaPorTrecho(String trecho);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serieEncontrada ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> achaMelhoresEpisodios(Serie serieEncontrada);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serieEncontrada AND e.dataDeLancamento >= :dataBuscada")
    List<Episodio> achaEpisodiosPorData(Serie serieEncontrada, LocalDate dataBuscada);
}
