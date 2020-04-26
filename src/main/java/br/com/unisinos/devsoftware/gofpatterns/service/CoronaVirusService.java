package br.com.unisinos.devsoftware.gofpatterns.service;

import br.com.unisinos.devsoftware.gofpatterns.ResponseDto;
import br.com.unisinos.devsoftware.gofpatterns.builder.Country;
import br.com.unisinos.devsoftware.gofpatterns.domain.DeathSituation;

import java.util.*;
import java.util.stream.Collectors;

public class CoronaVirusService {

    public List<Country> getHigherNumberOfCasesOnOneDay(HashMap<String, List<ResponseDto>> updatedCovidData) {
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

            Country country = new Country.Builder(key)
                    .date(responseDto.get().getDate())
                    .deathSituation(new DeathSituation(responseDto.get().getDeathsPerDay()))
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
}
