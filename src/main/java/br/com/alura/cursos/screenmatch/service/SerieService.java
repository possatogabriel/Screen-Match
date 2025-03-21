package br.com.alura.cursos.screenmatch.service;

import br.com.alura.cursos.screenmatch.dto.SerieDTO;
import br.com.alura.cursos.screenmatch.model.Serie;
import br.com.alura.cursos.screenmatch.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerieService {
    @Autowired
    private SerieRepository repositorio;

    private List<SerieDTO> converteDados(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getAnoDeLancamento(), s.getTotalDeTemporadas(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse(), s.getAvaliacaoIMDB(), s.getVotosIMDB()))
                .collect(Collectors.toList());
    }

    public List<SerieDTO> obtemTodasSeries() {
        return converteDados(repositorio.findAll());
    }

    public List<SerieDTO> obtemTop5Series() {
        return converteDados(repositorio.achaMelhoresSeries(5));
    }
}
