package com.zx.util;

import com.alibaba.fastjson.JSON;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

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

    public int[][] parseStringToIntArray(String s){
        char[] chars = s.toCharArray();
        List<int[]> list = new ArrayList<>();
        List<Integer> l = null;
        for (char c : chars) {
            if (c == '[' && l == null) l = new ArrayList<>();
            if (Character.isDigit(c) && l != null) l.add(c - '0');
            if (c == ']' && l != null && !l.isEmpty()){
                list.add(l.stream().mapToInt(n->n).toArray());
                l = new ArrayList<>();
            }
        }
        return list.toArray(new int[0][]);
    }

    public List<List<String>> paramToListListString(String s){
        return JSON.parseObject(s, ArrayList.class);
    }
}
