package br.com.unisinos.devsoftware.gofpatterns.strategy;

import br.com.unisinos.devsoftware.gofpatterns.ResponseDto;
import br.com.unisinos.devsoftware.gofpatterns.builder.CountryBuilder;
import br.com.unisinos.devsoftware.gofpatterns.domain.Situation;
import br.com.unisinos.devsoftware.gofpatterns.domain.SituationType;
import br.com.unisinos.devsoftware.gofpatterns.factory.SituationFactory;

import java.util.*;
import java.util.stream.Collectors;

public class HigherNumberOfDeaths implements HigherNumberOfCasesOnOneDayStrategy {

    @Override
    public List<CountryBuilder> getHigherNumberOfCasesOnOneDay(HashMap<String, List<ResponseDto>> hashMap) {
        List<CountryBuilder> countryBuilderList = new ArrayList<>();
        hashMap.forEach((key, value) -> {
            for (int i = 0; i < value.size(); i++) {
                int deathsPerDay;
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
                    .deathSituation(deathSituation)
                    .deathsPerDay(responseDto.get().getDeathsPerDay())
                    .build();

            countryBuilderList.add(countryBuilder);
        });
        Comparator<CountryBuilder> ordemNatural = Comparator.comparingInt(countryBuilder -> countryBuilder.getDeathSituation().getQuantity());
        return countryBuilderList.stream()
                .sorted(ordemNatural.reversed())
                .collect(Collectors.toList());
    }
}
