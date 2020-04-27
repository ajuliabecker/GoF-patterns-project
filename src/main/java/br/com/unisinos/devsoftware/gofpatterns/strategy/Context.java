package br.com.unisinos.devsoftware.gofpatterns.strategy;

import br.com.unisinos.devsoftware.gofpatterns.ResponseDto;
import br.com.unisinos.devsoftware.gofpatterns.builder.CountryBuilder;

import java.util.HashMap;
import java.util.List;

public class Context {
    private HigherNumberOfCasesOnOneDayStrategy strategy;

    public Context(HigherNumberOfCasesOnOneDayStrategy strategy) {
        this.strategy = strategy;
    }

    public List<CountryBuilder> executeStrategy(HashMap<String, List<ResponseDto>> hashMap) {
        return strategy.getHigherNumberOfCasesOnOneDay(hashMap);
    }
}
