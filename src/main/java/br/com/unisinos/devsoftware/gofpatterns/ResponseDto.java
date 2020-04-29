package br.com.unisinos.devsoftware.gofpatterns;

/**
 * Autores: Grupo2 (Arthur Linhares, Júlia Becker de Azevedo, Luis Henrique Hendges, Marcelo Augusto Gava, Mauricio Hartmann)
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {
    private String countryName;
    private String date;
    private Integer confirmed;
    private Integer deaths;
    private Integer recovered;
    private Integer deathsPerDay;
    private Integer confirmedPerDay;
    private Integer recoveredPerDay;
}
