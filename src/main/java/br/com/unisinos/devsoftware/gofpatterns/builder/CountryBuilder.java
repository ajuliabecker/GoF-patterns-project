package br.com.unisinos.devsoftware.gofpatterns.builder;

import br.com.unisinos.devsoftware.gofpatterns.domain.Situation;
import lombok.Data;

@Data
public class CountryBuilder {

    private String name;
    private String date;
    private Situation deathSituation;
    private Situation recoveredSituation;
    private Situation confirmedSituation;

    public CountryBuilder(Builder builder) {
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
        private Situation deathSituation;
        private Situation recoveredSituation;
        private Situation confirmedSituation;


        public Builder(String name) {
            if (name.isEmpty()) {
                throw new IllegalArgumentException("name can not be empty");
            }
            this.name = name;
        }

        public Builder deathSituation(Situation deathSituation) {
            this.deathSituation = deathSituation;
            return this;
        }

        public Builder recoveredSituation(Situation recoveredSituation) {
            this.recoveredSituation = recoveredSituation;
            return this;
        }

        public Builder confirmedSituation(Situation confirmedSituation) {
            this.confirmedSituation = confirmedSituation;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public CountryBuilder build() {
            return new CountryBuilder(this);
        }
    }
}