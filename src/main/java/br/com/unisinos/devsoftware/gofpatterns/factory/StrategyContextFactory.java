package br.com.unisinos.devsoftware.gofpatterns.factory;

import br.com.unisinos.devsoftware.gofpatterns.domain.SituationType;
import br.com.unisinos.devsoftware.gofpatterns.strategy.HigherNumberOfCasesOnOneDayStrategy;
import br.com.unisinos.devsoftware.gofpatterns.strategy.HigherNumberOfConfirmed;
import br.com.unisinos.devsoftware.gofpatterns.strategy.HigherNumberOfDeaths;
import br.com.unisinos.devsoftware.gofpatterns.strategy.HigherNumberOfRecovered;

public class StrategyContextFactory {

    public HigherNumberOfCasesOnOneDayStrategy getDataByType(SituationType type) {
        HigherNumberOfCasesOnOneDayStrategy dataType = null;
        switch (type) {
            case CONFIRMED:
                dataType = new HigherNumberOfConfirmed();
                break;
            case RECOVERED:
                dataType = new HigherNumberOfRecovered();
                break;
            case DEATH:
                dataType = new HigherNumberOfDeaths();
                break;
            default:
                break;
        }
        return dataType;
    }
}
