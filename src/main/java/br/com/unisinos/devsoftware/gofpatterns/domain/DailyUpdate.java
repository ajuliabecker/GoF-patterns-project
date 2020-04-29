package br.com.unisinos.devsoftware.gofpatterns.domain;

/**
 * Autores: Grupo2 (Arthur Linhares, Júlia Becker de Azevedo, Luis Henrique Hendges, Marcelo Augusto Gava, Mauricio Hartmann)
 */

import lombok.Data;

import java.time.LocalDate;

@Data
public class DailyUpdate {

    private LocalDate date;

    private ConfirmedSituation confirmedSituation;

    private RecoveredSituation recoveredSituation;

    private DeathSituation deathSituation;
}
