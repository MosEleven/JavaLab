package com.zx.testClass;

import com.zx.util.AsynUtil;
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
