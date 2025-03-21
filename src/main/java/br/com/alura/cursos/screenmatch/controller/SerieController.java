package br.com.alura.cursos.screenmatch.controller;

import br.com.alura.cursos.screenmatch.dto.SerieDTO;
import br.com.alura.cursos.screenmatch.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
