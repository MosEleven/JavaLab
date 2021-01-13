package com.zx.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class ValidatorUtil {

    public static boolean equals(BigDecimal d1, BigDecimal d2){
        if (d1 == d2) {
            return true;
        } else if ((d1 == null) || (d2 == null)) {
            return false;
        } else {
            return d1.compareTo(d2) == 0;
        }
    }
}
