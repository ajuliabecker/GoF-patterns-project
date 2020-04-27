package br.com.unisinos.devsoftware.gofpatterns.facade;

import br.com.unisinos.devsoftware.gofpatterns.ResponseDto;
import br.com.unisinos.devsoftware.gofpatterns.UpdateCovidData;
import br.com.unisinos.devsoftware.gofpatterns.builder.CountryBuilder;
import br.com.unisinos.devsoftware.gofpatterns.domain.Situation;
import br.com.unisinos.devsoftware.gofpatterns.domain.SituationType;
import br.com.unisinos.devsoftware.gofpatterns.factory.SituationFactory;
import br.com.unisinos.devsoftware.gofpatterns.factory.StrategyContextFactory;
import br.com.unisinos.devsoftware.gofpatterns.strategy.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CovidFacade {

    @Autowired
    UpdateCovidData updateCovidData;

    public List<CountryBuilder> getHigherNumberOfCasesOnOneDay(SituationType type) {
        StrategyContextFactory strategyContextFactory = new StrategyContextFactory();
        HashMap<String, List<ResponseDto>> updatedCovidData = updateCovidData.update();
        List<CountryBuilder> countryBuilderList;

        Context context = new Context(strategyContextFactory.getDataByType(type));
        countryBuilderList = context.executeStrategy(updatedCovidData);

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
