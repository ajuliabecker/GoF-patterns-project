package br.com.unisinos.devsoftware.gofpatterns.service;

import br.com.unisinos.devsoftware.gofpatterns.ResponseDto;
import br.com.unisinos.devsoftware.gofpatterns.UpdateCovidData;
import br.com.unisinos.devsoftware.gofpatterns.builder.Country;
import br.com.unisinos.devsoftware.gofpatterns.domain.*;
import br.com.unisinos.devsoftware.gofpatterns.factory.SituationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CovidService {

    @Autowired
    UpdateCovidData updateCovidData;

    public List<Country> getHigherNumberOfCasesOnOneDay() {
        HashMap<String, List<ResponseDto>> updatedCovidData = updateCovidData.update();
        List<Country> countryList = new ArrayList<>();
        List<Country> finalCountryList = countryList;

        updatedCovidData.forEach((key, value) -> {
            for (int i = 0; i < value.size(); i++) {
                int deathsPerDay = 0;
                if (i == 0) {
                    value.get(i).setDeathsPerDay(value.get(i).getDeaths());
                } else {
                    deathsPerDay = value.get(i).getDeaths() - value.get(i - 1).getDeaths();
                    value.get(i).setDeathsPerDay(deathsPerDay);
                }
            }

            Optional<ResponseDto> responseDto = value.stream().max(Comparator.comparing(ResponseDto::getDeathsPerDay));

            Situation deathSituation = new SituationFactory().getSituation(SituationType.DEATH, responseDto.get().getDeathsPerDay());
            Country country = new Country.Builder(key)
                    .date(responseDto.get().getDate())
                    .deathSituation((DeathSituation) deathSituation)
                    .build();

            finalCountryList.add(country);
        });

        countryList = finalCountryList;
        Comparator<Country> ordemNatural = Comparator.comparingInt(country -> country.getDeathSituation().getQuantity());
        countryList = countryList.stream()
                .sorted(ordemNatural.reversed())
                .collect(Collectors.toList());

        return countryList;
    }

    public List<Country> getTotalSituationPerCountry() {
        HashMap<String, List<ResponseDto>> updatedCovidData = updateCovidData.update();
        List<Country> countryList = new ArrayList<>();

        updatedCovidData.forEach((key, value) -> {
            ResponseDto responseDto = value.get(value.size() - 1);

            Situation deathSituation = new SituationFactory().getSituation(SituationType.DEATH, responseDto.getDeaths());
            Situation confirmedSituation = new SituationFactory().getSituation(SituationType.CONFIRMED, responseDto.getConfirmed());
            Situation recoveredSituation = new SituationFactory().getSituation(SituationType.RECOVERED, responseDto.getRecovered());
            Country country = new Country.Builder(key)
                    .date(responseDto.getDate())
                    .deathSituation((DeathSituation) deathSituation)
                    .confirmedSituation((ConfirmedSituation) confirmedSituation)
                    .recoveredSituation((RecoveredSituation) recoveredSituation)
                    .build();

            countryList.add(country);
        });

        return countryList.stream().sorted(Comparator.comparing(Country::getName)).collect(Collectors.toList());
    }
}
