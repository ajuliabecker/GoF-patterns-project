package br.com.unisinos.devsoftware.gofpatterns;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class ResponseDto {
    private String countryName;
    private String date;
    private Integer confirmed;
    private Integer deaths;
    private Integer recovered;
    @JsonIgnore
    private Integer deathsPerDay;
}
