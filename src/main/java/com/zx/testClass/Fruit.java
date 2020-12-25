package com.zx.testClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class Fruit {

    private String category;

    private String name;

    private BigDecimal price;
}
