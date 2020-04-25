package br.com.unisinos.devsoftware.gofpatterns.factory;

import br.com.unisinos.devsoftware.gofpatterns.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SituationFactory {

    public Situation getSituation(SituationType type) {
        Situation situation = null;
        switch (type) {
            case CONFIRMED:
                situation = new ConfirmedSituation();
                break;
            case RECOVERED:
                situation = new RecoveredSituation();
                break;
            case DEATH:
                situation = new DeathSituation();
                break;
            default:
                break;
        }
        return situation;
    }
}
