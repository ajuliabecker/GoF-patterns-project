package br.com.unisinos.devsoftware.gofpatterns;

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
