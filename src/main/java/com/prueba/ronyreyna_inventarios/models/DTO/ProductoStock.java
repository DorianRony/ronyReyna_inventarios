package com.prueba.ronyreyna_inventarios.models.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoStock {
    private String code;
    private String name;
    private Integer stock;
}
