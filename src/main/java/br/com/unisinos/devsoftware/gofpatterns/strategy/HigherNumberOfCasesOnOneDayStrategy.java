package br.com.unisinos.devsoftware.gofpatterns.strategy;

import br.com.unisinos.devsoftware.gofpatterns.ResponseDto;
import br.com.unisinos.devsoftware.gofpatterns.builder.CountryBuilder;

import java.util.HashMap;
import java.util.List;

public interface HigherNumberOfCasesOnOneDayStrategy {

    List<CountryBuilder> getHigherNumberOfCasesOnOneDay(HashMap<String, List<ResponseDto>> hashMap);
}
