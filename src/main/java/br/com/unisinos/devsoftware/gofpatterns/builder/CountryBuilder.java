package br.com.unisinos.devsoftware.gofpatterns.builder;

/**
 * Autores: Grupo2 (Arthur Linhares, Júlia Becker de Azevedo, Luis Henrique Hendges, Marcelo Augusto Gava, Mauricio Hartmann)
 */

import br.com.unisinos.devsoftware.gofpatterns.domain.Situation;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryBuilder {

    private String name;
    private String date;
    private Situation deathSituation;
    private Situation recoveredSituation;
    private Situation confirmedSituation;
    private Integer deathsPerDay;
    private Integer confirmedPerDay;
    private Integer recoveredPerDay;

    public CountryBuilder(Builder builder) {
        this.name = builder.getName();
        this.date = builder.getDate();
        this.deathSituation = builder.getDeathSituation();
        this.recoveredSituation = builder.getRecoveredSituation();
        this.confirmedSituation = builder.getConfirmedSituation();
        this.deathsPerDay = builder.getDeathsPerDay();
        this.confirmedPerDay = builder.getConfirmedPerDay();
        this.recoveredPerDay = builder.getRecoveredPerDay();
    }

    @Data
    public static class Builder {

        private String name;
        private String date;
        private Situation deathSituation;
        private Situation recoveredSituation;
        private Situation confirmedSituation;
        private Integer deathsPerDay;
        private Integer confirmedPerDay;
        private Integer recoveredPerDay;


        public Builder(String name) {
            if (name.isEmpty() && name != null) {
                throw new IllegalArgumentException("name can not be empty");
            }
            this.name = name;
        }

        public Builder deathsPerDay(Integer quantity) {
            this.deathsPerDay = quantity;
            return this;
        }

        public Builder confirmedPerDay(Integer quantity) {
            this.confirmedPerDay = quantity;
            return this;
        }

        public Builder recoveredPerDay(Integer quantity) {
            this.recoveredPerDay = quantity;
            return this;
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