package br.com.unisinos.devsoftware.gofpatterns.builder;

import br.com.unisinos.devsoftware.gofpatterns.domain.ConfirmedSituation;
import br.com.unisinos.devsoftware.gofpatterns.domain.DeathSituation;
import br.com.unisinos.devsoftware.gofpatterns.domain.RecoveredSituation;
import lombok.Data;

@Data
public class Country {

    private String name;
    private String date;
    private DeathSituation deathSituation;
    private RecoveredSituation recoveredSituation;
    private ConfirmedSituation confirmedSituation;

    public Country(Builder builder) {
        this.name = builder.getName();
        this.date = builder.getDate();
        this.deathSituation = builder.getDeathSituation();
        this.recoveredSituation = builder.getRecoveredSituation();
        this.confirmedSituation = builder.getConfirmedSituation();
    }

    @Data
    public static class Builder {

        private String name;
        private String date;
        private DeathSituation deathSituation;
        private RecoveredSituation recoveredSituation;
        private ConfirmedSituation confirmedSituation;


        public Builder(String name) {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("name can not be empty");
            }
            this.name = name;
        }

        public Builder deathSituation(DeathSituation deathSituation) {
            this.deathSituation = deathSituation;
            return this;
        }

        public Builder recoveredSituation(RecoveredSituation recoveredSituation) {
            this.recoveredSituation = recoveredSituation;
            return this;
        }

        public Builder confirmedSituation(ConfirmedSituation confirmedSituation) {
            this.confirmedSituation = confirmedSituation;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Country build() {
            return new Country(this);
        }
    }
}