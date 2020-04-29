package br.com.unisinos.devsoftware.gofpatterns.controller;

/**
 * Autores: Grupo2 (Arthur Linhares, Júlia Becker de Azevedo, Luis Henrique Hendges, Marcelo Augusto Gava, Mauricio Hartmann)
 */

import br.com.unisinos.devsoftware.gofpatterns.builder.CountryBuilder;
import br.com.unisinos.devsoftware.gofpatterns.domain.SituationType;
import br.com.unisinos.devsoftware.gofpatterns.facade.CovidFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("covid")
public class CovidController {

    @Autowired
    CovidFacade covidFacade;

    @GetMapping(value = "totalSituationPerCountry", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CountryBuilder> getTotalSituationPerCountry() {
        return covidFacade.getTotalSituationPerCountry();
    }

    @GetMapping(value = "higherNumberOfCasesOnOneDay", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CountryBuilder> getHigherNumberOfCasesOnOneDay(@RequestParam SituationType type) {
        return covidFacade.getHigherNumberOfCasesOnOneDay(type);
    }
}
