package br.com.alura.cursos.screenmatch.controller;

import br.com.alura.cursos.screenmatch.dto.EpisodioDTO;
import br.com.alura.cursos.screenmatch.dto.SerieDTO;
import br.com.alura.cursos.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService servico;

    @GetMapping("")
    public List<SerieDTO> obtemSeries() {
        return servico.obtemTodasSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obtemTop5Series() {
        return servico.obtemTop5Series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obtemLancamentos() {
        return servico.obtemLancamentos();
    }

    @GetMapping("/{id}")
    public SerieDTO obtemPorID(@PathVariable Long id) {
        return servico.obtemPorID(id);
    }

    @GetMapping("/{id}/temporadas/todas/episodios")
    public List<EpisodioDTO> obtemTodosEpisodios(@PathVariable Long id) {
        return servico.obtemTodosEpisodios(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}/episodios")
    public List<EpisodioDTO> obtemEpisodiosPorTemporada(@PathVariable Long id, @PathVariable Long numeroTemporada) {
        return servico.obtemEpisodiosPorTemporada(id, numeroTemporada);
    }

    @GetMapping("/categoria/{nomeCategoria}")
    public List<SerieDTO> obtemSeriesPorCategoria(@PathVariable String nomeCategoria) {
        return servico.obtemSeriesPorCategoria(nomeCategoria);
    }

    @GetMapping("/{id}/temporadas/top/episodios")
    public List<EpisodioDTO> obtemTop5Episodios(@PathVariable Long id) {
        return servico.obtemTop5Episodios(id);
    }
}
