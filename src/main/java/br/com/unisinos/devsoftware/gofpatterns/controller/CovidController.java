package br.com.unisinos.devsoftware.gofpatterns.controller;

import br.com.unisinos.devsoftware.gofpatterns.builder.Country;
import br.com.unisinos.devsoftware.gofpatterns.service.CovidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("covid")
public class CovidController {

    @Autowired
    CovidService covidService;

    @GetMapping(value = "totalSituationPerCountry", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> getTotalSituationPerCountry() {
        return covidService.getTotalSituationPerCountry();
    }

    @GetMapping(value = "higherNumberOfCasesOnOneDay", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Country> getHigherNumberOfCasesOnOneDay() {
        return covidService.getHigherNumberOfCasesOnOneDay();
    }

}
