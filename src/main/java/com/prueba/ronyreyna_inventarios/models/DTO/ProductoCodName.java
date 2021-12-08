package com.prueba.ronyreyna_inventarios.models.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductoCodName {
    private String cod;
    private String name;
}
