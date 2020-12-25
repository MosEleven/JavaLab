package com.zx.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AlgoUtil {

    public int getNumFromStr(char[] cs, int offset, int baseLen){
        if (baseLen == 0) return 0;
        if (offset+baseLen>cs.length) return 0;
        int n = 0;
        int count = 0;
        while (count<baseLen){
            n = n*10 +cs[offset+count] - 48;
            count++;
        }
        return n;
    }
}
