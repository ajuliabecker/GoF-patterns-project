package br.com.unisinos.devsoftware.gofpatterns.strategy;

import br.com.unisinos.devsoftware.gofpatterns.ResponseDto;
import br.com.unisinos.devsoftware.gofpatterns.builder.CountryBuilder;
import br.com.unisinos.devsoftware.gofpatterns.domain.Situation;
import br.com.unisinos.devsoftware.gofpatterns.domain.SituationType;
import br.com.unisinos.devsoftware.gofpatterns.factory.SituationFactory;

import java.util.*;
import java.util.stream.Collectors;

public class HigherNumberOfRecovered implements HigherNumberOfCasesOnOneDayStrategy {

    @Override
    public List<CountryBuilder> getHigherNumberOfCasesOnOneDay(HashMap<String, List<ResponseDto>> hashMap) {
        List<CountryBuilder> countryBuilderList = new ArrayList<>();
        hashMap.forEach((key, value) -> {
            for (int i = 0; i < value.size(); i++) {
                int recoveredPerDay;
                if (i == 0) {
                    value.get(i).setRecoveredPerDay(value.get(i).getDeaths());
                } else {
                    recoveredPerDay = value.get(i).getRecovered() - value.get(i - 1).getRecovered();
                    value.get(i).setRecoveredPerDay(recoveredPerDay);
                }
            }

            Optional<ResponseDto> responseDto = value.stream().max(Comparator.comparing(ResponseDto::getRecoveredPerDay));

            Situation recoveredSituation = new SituationFactory().getSituation(SituationType.RECOVERED, responseDto.get().getRecovered());
            CountryBuilder countryBuilder = new CountryBuilder.Builder(key)
                    .date(responseDto.get().getDate())
                    .deathSituation(recoveredSituation)
                    .recoveredPerDay(responseDto.get().getRecoveredPerDay())
                    .build();

            countryBuilderList.add(countryBuilder);
        });
        Comparator<CountryBuilder> ordemNatural = Comparator.comparingInt(countryBuilder -> countryBuilder.getRecoveredSituation().getQuantity());
        return countryBuilderList.stream()
                .sorted(ordemNatural.reversed())
                .collect(Collectors.toList());
    }
}
