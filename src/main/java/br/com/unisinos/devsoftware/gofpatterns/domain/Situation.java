package br.com.unisinos.devsoftware.gofpatterns.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class Situation {
    private Integer quantity;
}
