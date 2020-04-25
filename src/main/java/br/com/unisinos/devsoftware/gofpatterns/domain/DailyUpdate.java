package br.com.unisinos.devsoftware.gofpatterns.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DailyUpdate {

    private LocalDate date;

    private ConfirmedSituation confirmedSituation;

    private RecoveredSituation recoveredSituation;

    private DeathSituation deathSituation;
}
