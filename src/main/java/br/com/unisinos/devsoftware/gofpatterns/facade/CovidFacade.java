package br.com.unisinos.devsoftware.gofpatterns.facade;

import br.com.unisinos.devsoftware.gofpatterns.ResponseDto;
import br.com.unisinos.devsoftware.gofpatterns.UpdateCovidData;
import br.com.unisinos.devsoftware.gofpatterns.builder.CountryBuilder;
import br.com.unisinos.devsoftware.gofpatterns.domain.DeathSituation;
import br.com.unisinos.devsoftware.gofpatterns.domain.Situation;
import br.com.unisinos.devsoftware.gofpatterns.domain.SituationType;
import br.com.unisinos.devsoftware.gofpatterns.factory.SituationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CovidFacade {

    @Autowired
    UpdateCovidData updateCovidData;

    public List<CountryBuilder> getHigherNumberOfCasesOnOneDay() {
        HashMap<String, List<ResponseDto>> updatedCovidData = updateCovidData.update();
        List<CountryBuilder> countryBuilderList = new ArrayList<>();
        List<CountryBuilder> finalCountryBuilderList = countryBuilderList;

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
            CountryBuilder countryBuilder = new CountryBuilder.Builder(key)
                    .date(responseDto.get().getDate())
                    .deathSituation((DeathSituation) deathSituation)
                    .build();

            finalCountryBuilderList.add(countryBuilder);
        });

        countryBuilderList = finalCountryBuilderList;
        Comparator<CountryBuilder> ordemNatural = Comparator.comparingInt(countryBuilder -> countryBuilder.getDeathSituation().getQuantity());
        countryBuilderList = countryBuilderList.stream()
                .sorted(ordemNatural.reversed())
                .collect(Collectors.toList());

        return countryBuilderList;
    }

    public List<CountryBuilder> getTotalSituationPerCountry() {
        HashMap<String, List<ResponseDto>> updatedCovidData = updateCovidData.update();
        List<CountryBuilder> countryBuilderList = new ArrayList<>();

        updatedCovidData.forEach((key, value) -> {
            ResponseDto responseDto = value.get(value.size() - 1);

            Situation deathSituation = new SituationFactory().getSituation(SituationType.DEATH, responseDto.getDeaths());
            Situation confirmedSituation = new SituationFactory().getSituation(SituationType.CONFIRMED, responseDto.getConfirmed());
            Situation recoveredSituation = new SituationFactory().getSituation(SituationType.RECOVERED, responseDto.getRecovered());
            CountryBuilder countryBuilder = new CountryBuilder.Builder(key)
                    .date(responseDto.getDate())
                    .deathSituation(deathSituation)
                    .confirmedSituation(confirmedSituation)
                    .recoveredSituation(recoveredSituation)
                    .build();

            countryBuilderList.add(countryBuilder);
        });

        return countryBuilderList.stream().sorted(Comparator.comparing(CountryBuilder::getName)).collect(Collectors.toList());
    }
}
