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

    public List<int[]> sortedDistinctHelper(int[][] nums){
        int len = nums[0].length;
        List<int[]> list = new ArrayList<>(nums.length);
        list.add(nums[0]);
        int[] pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int[] now = nums[i];
            int p = 0;
            while (p<len && now[p]==pre[p++]);
            if (p<len) list.add(now);
        }
        return list;
    }

    public int[][] paramTo2DIntArray(String s){
        return JSON.parseObject(s,int[][].class);
    }

    public int[] paramToIntArray(String s){
        return JSON.parseObject(s,int[].class);
    }

    public List<List<String>> paramToListListString(String s){
        return JSON.parseObject(s, ArrayList.class);
    }

    public String[] paramToArrayString(String s){
        return JSON.parseObject(s,String[].class);
    }
}
