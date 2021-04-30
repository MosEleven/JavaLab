package com.zx.algo;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sun.xml.internal.ws.util.StringUtils;
import com.zx.testClass.Fruit;
import com.zx.util.AlgoUtil;
import com.zx.util.UnionFind;
import com.zx.util.ValidatorUtil;
import javafx.concurrent.Worker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class algoTest {
    private static Algorithm algorithm = new Algorithm();

    @Test
    public void myTest(){
       int a = '0';
        System.out.println(a);
    }

    public Boolean randB(){
        return new Random().nextBoolean();
    }

    @Test
    public void maximalNetworkRankTest(){
        //Algorithm algorithm = new Algorithm();
        int[][] n ={{8,5},{15,9},{19,11},{18,19},{11,17},{6,17},
                {15,6},{19,1},{1,15},{3,14},{17,18},{1,4},{4,0},
                {11,0},{15,17},{3,19},{8,9},{5,12},{1,7},{7,3}};

        algorithm.maximalNetworkRank(20,n);
    }

    @Test
    public void backspaceCompareTest(){
        String s = "a##c";
        String t = "#a#c";
        algorithm.backspaceCompare(s,t);
    }

    @Test
    public void matrixScore(){
        int [][]A = {{0,0,1,1},{1,0,1,0},{1,1,0,0}};
        int result = algorithm.matrixScore(A);
        System.out.println("结果为："+result);
    }

    @Test
    public void uniquePaths(){
        int res = algorithm.uniquePaths(13, 23);
        //Assert.assertEquals(28,res);
    }

    @Test
    public void splitIntoFibonacci(){
        List<Integer> list = algorithm.splitIntoFibonacci("123456579");
        System.out.println(list);
    }

    @Test
    void predictPartyVictory(){
        String res = algorithm.predictPartyVictory("RD");
        System.out.println(res);
    }

    @Test
    void lemonadeChange(){
        boolean res = algorithm.lemonadeChange(new int[]{5, 5, 10, 10, 25});
        System.out.println(res);
    }

    @Test
    void wiggleMaxLength(){
        int res = algorithm.wiggleMaxLength(new int[]{1,2,3,4,5,6,7,8,9});
        System.out.println(res);
    }

    @Test
    void groupAnagrams(){
        List<List<String>> lists = algorithm.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
        System.out.println(lists);
    }

    @Test
    void wordPattern(){
        boolean res = algorithm.wordPattern("abba","dog dog dog dog");
        System.out.println(res);
    }

    @Test
    void monotoneIncreasingDigits(){
        int res = algorithm.monotoneIncreasingDigits(1254869);
        System.out.println(res);
    }

    @Test
    void maxProfit(){
        int res = algorithm.maxProfit2(new int[]{1, 3, 7, 5, 10, 3}, 3);
        System.out.println(res);
    }

    @Test
    void findTheDifference(){
        char res = algorithm.findTheDifference("asdfqwer", "asdfkqwer");
        System.out.println(res);
    }

    @Test
    void maxResult(){
        int res = algorithm.maxResult(new int[]{10, -5, -2, 4, 0, 3, 3},3);
        System.out.println(res);
    }

    @Test
    void setMergeFunction(){
        Fruit fruit1 = new Fruit("banana","Malaysia banana",new BigDecimal("10"));
        Fruit fruit2 = new Fruit("banana","Red apple",new BigDecimal("7"));
        Fruit fruit3 = new Fruit("banana","Red apple",new BigDecimal("5"));
        Fruit fruit4 = new Fruit("banana","green apple",new BigDecimal("5"));
        Fruit fruit5 = new Fruit("apple","Red apple",new BigDecimal("5"));
        Map<String, HashSet<String>> collect = Lists.newArrayList(fruit1, fruit2, fruit3,fruit4,fruit5).stream().collect(Collectors.toMap(Fruit::getCategory, f -> Sets.newHashSet(f.getName()), (a, b) -> {
            a.addAll(b);
            return a;
        }));
        System.out.println(JSON.toJSONString(collect));
    }

    @Test
    void candy(){
        int res = algorithm.candyFast(new int[]{1,6,10,8,7,3,2});
        System.out.println(res);
    }

    @Test
    void lastStoneWeight(){
        int res = algorithm.lastStoneWeight(new int[]{2, 3, 4, 5});
        System.out.println(res);
    }

    @Test
    void eraseOverlapIntervals(){
        int res = algorithm.eraseOverlapIntervals(new int[][]{{1, 2}, {1, 2}, {1, 2}});
        System.out.println(res);
    }

    @Test
    void computeIfAbsentTest(){
        Map<String, Integer> stringLength = new HashMap<>();
        assertEquals(4,stringLength.computeIfAbsent("John", String::length));
        assertEquals(4,stringLength.get("John"));
    }

    @Test
    void emptyListTest(){
        List<Integer> list = new ArrayList<>();
        Integer n = list.get(0);
        System.out.println(n);
    }

    @Test
    void bigDecimalValidatorTest(){
        BigDecimal d2 = new BigDecimal("12");
        BigDecimal d1 = new BigDecimal("1");
        System.out.println(ValidatorUtil.equals(d1,d2));
    }

    @Test
    void findRedundantConnection(){
        int[][] param = AlgoUtil.paramTo2DIntArray("[[1,2], [2,3], [3,4], [1,4], [1,5]]");
        int[] res = algorithm.findRedundantConnection(param);
        System.out.println(Arrays.toString(res));
    }

    @Test
    void accountsMerge(){
        String param = "[[\"David\",\"David0@m.co\",\"David1@m.co\"],[\"David\",\"David3@m.co\",\"David4@m.co\"],[\"David\",\"David4@m.co\",\"David5@m.co\"],[\"David\",\"David2@m.co\",\"David3@m.co\"],[\"David\",\"David1@m.co\",\"David2@m.co\"]]";
//        String param = "[[\"John\",\"johnsmith@mail.com\",\"john_newyork@mail.com\"],[\"John\",\"johnsmith@mail.com\",\"john00@mail.com\"],[\"Mary\",\"mary@mail.com\"],[\"John\",\"johnnybravo@mail.com\"]]";
        List<List<String>> list = AlgoUtil.paramToListListString(param);
        System.out.println(list);
        List<List<String>> res = algorithm.accountsMerge(list);
        System.out.println(res);
    }

    @Test
    void subStringTest(){
        String s = "a\\b";
        System.out.println(s.charAt(2));
    }

    @Test
    void regionsBySlashes(){
        String param = "[\"\\\\\\\\\\\\\",\"  \\\\\",\"  /\"]";
        String[] strings = AlgoUtil.paramToArrayString(param);
        int res = algorithm.regionsBySlashes(strings);
        System.out.println(res);
    }

    @Test
    void makeConnected(){
        String param = "[[0,1],[0,2],[1,2]]";
        int[][] ints = AlgoUtil.paramTo2DIntArray(param);
        int res = algorithm.makeConnected(4, ints);
        System.out.println(res);
    }

    @Test
    void collectionRemoveTest(){
        Map<String,String> map = new HashMap<>();
        map.put("123","qwe");
        map.put("456","asd");
        map.put("789","zxc");
        Set<Map.Entry<String, String>> entries = map.entrySet();
        entries.removeIf(entry-> entry.getKey().equals("456"));
        System.out.println(map);
    }
    @Test
    void numEquivDominoPairs(){
        String param = "[[1,2],[2,1],[2,1],[2,1],[3,4],[5,6]]";
        int[][] ints = AlgoUtil.paramTo2DIntArray(param);
        int res = algorithm.numEquivDominoPairs(ints);
        System.out.println(res);

    }

    @Test
    void maxNumEdgesToRemove(){
        String param = "[[3,1,2],[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]";
        int[][] ints = AlgoUtil.paramTo2DIntArray(param);
        int res = algorithm.maxNumEdgesToRemove(4, ints);
        System.out.println(res);
    }

    @Test
    void pivoxIndex(){
        String param = "[1, 7, 3, 6, 5, 6]";
        int[] ints = AlgoUtil.paramToIntArray(param);
        int res = algorithm.pivotIndex(ints);
        System.out.println(res);
    }

    public static boolean equals(BigDecimal d1, BigDecimal d2) {
        return d1 == null? (d2==null) : (d2 != null && (d1.compareTo(d2) == 0));
    }

    @Test
    void bigdecimalEqualsTest(){
        BigDecimal d1 = new BigDecimal("1.2");
        BigDecimal d2 = new BigDecimal("1.20");
        Assertions.assertTrue(equals(null,d2));

    }

    @ParameterizedTest
    @CsvSource("'[1,2,3,4]',24")
    void maxinumProduct(String param, int result){
        int[] ints = AlgoUtil.paramToIntArray(param);
        int res = algorithm.maximumProduct(ints);
        Assertions.assertEquals(result,res);
    }

    @ParameterizedTest
    @CsvSource({"'[[0,0],[2,2],[3,10],[5,2],[7,0]]',20",
    "'[[3,12],[-2,5],[-4,1]]',18"})
    void minCostConnectPoints(String param,int result){
        int[][] p = AlgoUtil.paramTo2DIntArray(param);
        int res = algorithm.minCostConnectPoints(p);
        assertEquals(result, res);
    }

    @ParameterizedTest
    @CsvSource({"3,-2","-3,-2","3,2","-3,-2"})
    void modTest(int a, int b){
        assertEquals(a%b,modMethod(a,b));
    }
    private int modMethod(int a, int b){
        return a - b * (a / b);
    }

    @Test
    void toMapTest(){
        List<String> list = new ArrayList<>();
        Map<String, String> collect = list.stream().collect(Collectors.toMap(s -> s, s -> s));
        System.out.println(collect);
    }

    @Test
    void foreachTest(){
        Map<String,List<String>> map = new HashMap<>();
        map.put("ss",Lists.newArrayList("11","22","33"));
        List<String> list = map.get("rr");
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    void finalStringTest(){
        String a = "hello2";
        final String b = "hello";
        String d = "hello";
        String c = b + 2;
        String e = d + 2;
        System.out.println((a == c));
        System.out.println((a == e));

    }


    @Test
    void trimTest(){
        int n = Integer.MAX_VALUE;
        System.out.println((n+n)>>>1);
    }


    @Test
    @DisplayName("直方图蓄水量")
    void trapTest(){
        int[] heights = AlgoUtil.paramToIntArray("[0,1,0,2,1,0,1,3,2,1,2,1]");
        int res = algorithm.trap(heights);
        System.out.println(res);
    }

    @Test
    @DisplayName("旋转过的递增数组查找")
    void searchRotatedArray(){
        int[] nums = new int[]{3,5,1};
        boolean search = algorithm.search(nums, 1);
    }

    @Test
    @DisplayName("笨阶乘")
    void clumsyTest(){
        for (int i = 0; i < 30; i++) {
            System.out.printf("No.%s : %s%n",i,algorithm.clumsy(i));
        }
    }

    @Test
    @DisplayName("最大拼接数")
    void largestNumber(){
        int[] nums = AlgoUtil.paramToIntArray("[25,257,2]");
        String res = algorithm.largestNumber(nums);
        System.out.println(res);
    }

    @Test
    @DisplayName("nothibg")
    void sfdsaf(){
        String[] split = "param[0].bizNo|ffff".split( "");
        System.out.println(Arrays.toString(split));
    }

    @Test
    @DisplayName("shipWithinDays")
    void shipWithinDays(){
        int[] ints = AlgoUtil.paramToIntArray("[1,2,3,4,5,6,7,8,9,10]");
        algorithm.shipWithinDays(ints,5);
    }
}
