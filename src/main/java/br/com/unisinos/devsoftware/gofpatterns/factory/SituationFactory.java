package br.com.unisinos.devsoftware.gofpatterns.factory;

/**
 * Autores: Grupo2 (Arthur Linhares, Júlia Becker de Azevedo, Luis Henrique Hendges, Marcelo Augusto Gava, Mauricio Hartmann)
 */

import br.com.unisinos.devsoftware.gofpatterns.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SituationFactory {

    public Situation getSituation(SituationType type, Integer quantity) {
        Situation situation = null;
        switch (type) {
            case CONFIRMED:
                situation = new ConfirmedSituation(quantity);
                break;
            case RECOVERED:
                situation = new RecoveredSituation(quantity);
                break;
            case DEATH:
                situation = new DeathSituation(quantity);
                break;
            default:
                break;
        }
        return situation;
    }
}
