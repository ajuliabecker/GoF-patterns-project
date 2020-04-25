package br.com.unisinos.devsoftware.gofpatterns;

import lombok.Data;

@Data
public class ResponseDto {
    private String date;
    private Integer confirmed;
    private Integer deaths;
    private Integer recovered;
}
