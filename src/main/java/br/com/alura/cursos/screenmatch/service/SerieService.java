package br.com.alura.cursos.screenmatch.service;

import br.com.alura.cursos.screenmatch.dto.EpisodioDTO;
import br.com.alura.cursos.screenmatch.dto.SerieDTO;
import br.com.alura.cursos.screenmatch.model.Categoria;
import br.com.alura.cursos.screenmatch.model.Episodio;
import br.com.alura.cursos.screenmatch.model.Serie;
import br.com.alura.cursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repositorio;

    private List<SerieDTO> converteDados(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getAnoDeLancamento(), s.getFrase(), s.getPersonagem(), s.getTotalDeTemporadas(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse(), s.getAvaliacaoIMDB(), s.getVotosIMDB()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obtemTodasSeries() {
        return converteDados(repositorio.findAll());
    }

    public List<SerieDTO> obtemTop5Series() {
        return converteDados(repositorio.achaMelhoresSeries(5));
    }

    public List<SerieDTO> obtemLancamentos() {
        return converteDados(repositorio.achaSeriePorDataDeLancamento());
    }

    public SerieDTO obtemPorID(Long id) {
        Optional<Serie> serie = repositorio.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();

            return new SerieDTO(s.getId(), s.getTitulo(), s.getAnoDeLancamento(), s.getFrase(), s.getPersonagem(), s.getTotalDeTemporadas(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse(), s.getAvaliacaoIMDB(), s.getVotosIMDB());
        }
        return null;
    }

    public List<EpisodioDTO> obtemTodosEpisodios(Long id) {
        Optional<Serie> serie = repositorio.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();

            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumero(), e.getDataDeLancamento(), e.getAvaliacao()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obtemEpisodiosPorTemporada(Long id, Long numeroTemporada) {
        List<Episodio> episodios = repositorio.achaEpisodiosPorTemporada(id, numeroTemporada);

        return episodios.stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumero(), e.getDataDeLancamento(), e.getAvaliacao()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obtemSeriesPorCategoria(String nomeCategoria) {
        Categoria categoria = Categoria.fromPortugues(nomeCategoria);

        return converteDados(repositorio.achaPorGenero(categoria));
    }

    public List<EpisodioDTO> obtemTop5Episodios(Long id) {
        Serie serie = repositorio.achaPorID(id);

        return repositorio.achaMelhoresEpisodios(serie).stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getTitulo(), e.getNumero(), e.getDataDeLancamento(), e.getAvaliacao()))
                .collect(Collectors.toList());
    }

    public SerieDTO obtemFrase() {
        Optional<Serie> serie = repositorio.achaPorFrase();

        if (serie.isPresent()) {
            Serie s = serie.get();

            return new SerieDTO(s.getId(), s.getTitulo(), s.getAnoDeLancamento(), s.getFrase(), s.getPersonagem(), s.getTotalDeTemporadas(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse(), s.getAvaliacaoIMDB(), s.getVotosIMDB());
        }
        return null;
    }
}
