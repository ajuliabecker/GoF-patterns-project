package br.com.unisinos.devsoftware.gofpatterns.strategy;

/**
 * Autores: Grupo2 (Arthur Linhares, Júlia Becker de Azevedo, Luis Henrique Hendges, Marcelo Augusto Gava, Mauricio Hartmann)
 */

import br.com.unisinos.devsoftware.gofpatterns.ResponseDto;
import br.com.unisinos.devsoftware.gofpatterns.builder.CountryBuilder;
import br.com.unisinos.devsoftware.gofpatterns.domain.Situation;
import br.com.unisinos.devsoftware.gofpatterns.domain.SituationType;
import br.com.unisinos.devsoftware.gofpatterns.factory.SituationFactory;

import java.util.*;
import java.util.stream.Collectors;

public class HigherNumberOfConfirmed implements HigherNumberOfCasesOnOneDayStrategy {

    @Override
    public List<CountryBuilder> getHigherNumberOfCasesOnOneDay(HashMap<String, List<ResponseDto>> hashMap) {
        List<CountryBuilder> countryBuilderList = new ArrayList<>();
        hashMap.forEach((key, value) -> {
            for (int i = 0; i < value.size(); i++) {
                int confirmedPerDay;
                if (i == 0) {
                    value.get(i).setConfirmedPerDay(value.get(i).getConfirmed());
                } else {
                    confirmedPerDay = value.get(i).getConfirmed() - value.get(i - 1).getConfirmed();
                    value.get(i).setConfirmedPerDay(confirmedPerDay);
                }
            }

            Optional<ResponseDto> responseDto = value.stream().max(Comparator.comparing(ResponseDto::getConfirmedPerDay));

            Situation confirmedSituation = new SituationFactory().getSituation(SituationType.CONFIRMED, responseDto.get().getConfirmed());
            CountryBuilder countryBuilder = new CountryBuilder.Builder(key)
                    .date(responseDto.get().getDate())
                    .confirmedSituation(confirmedSituation)
                    .confirmedPerDay(responseDto.get().getConfirmedPerDay())
                    .build();

            countryBuilderList.add(countryBuilder);
        });
        Comparator<CountryBuilder> ordemNatural = Comparator.comparingInt(countryBuilder -> countryBuilder.getConfirmedSituation().getQuantity());
        return countryBuilderList.stream()
                .sorted(ordemNatural.reversed())
                .collect(Collectors.toList());
    }
}
