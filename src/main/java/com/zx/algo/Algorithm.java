package com.zx.algo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zx.util.AlgoUtil;
import com.zx.util.UnionFind;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


import static com.zx.util.CommonMethon.*;

public class Algorithm {

    public int[] sumOfDistancesInTree_834_OverTime(int N, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < N - 1; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            if (!map.containsKey(a)) {
                map.put(a, new ArrayList<Integer>());
            }
            map.get(a).add(b);
            if (!map.containsKey(b)) {
                map.put(b, new ArrayList<Integer>());
            }
            map.get(b).add(a);

        }
        //int upper = N;
        int[] res = new int[N];
        boolean[] inserted = new boolean[N];
        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            int level = 0, count = 0;
            Arrays.fill(inserted, false);
            qu.offer(i);
            inserted[i] = true;
            int size = qu.size();
            while (size != 0) {
                level++;
                int curNum = 0;
                while (size-- > 0) {
                    int n = qu.poll();
                    for (int k : map.get(n)) {
                        if (!inserted[k]) {
                            qu.offer(k);
                            curNum++;
                            inserted[k] = true;
                        }
                    }
                }
                count += curNum * level;
                size = qu.size();
            }
            res[i] = count;
        }
        return res;
    }

    public int[] sumOfDistancesInTree_834(int N, int[][] edges) {
        int[] dp = new int[N];
        int[] des = new int[N];
        int[] res = new int[N];
        //edge数组转化为图结构
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            lists.add(new ArrayList<Integer>());
        }
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            lists.get(a).add(b);
            lists.get(b).add(a);
        }

        //构建第一个根节点


        //foreach

        return res;
    }

    private void helper_843() {
        ;
    }

    public int numberOfMatches(int n) {
        return n - 1;
    }

    public int minPartitions(String n) {
        int res = 0;
        for (char c : n.toCharArray()) {
            res = Math.max(res, c - 48);
        }
        return res;
    }

    public String reformatNumber(String number) {
        char[] cs = new char[number.length()];
        int len = 0;
        for (char c : number.toCharArray()) {
            if (c != '-' && c != ' ') {
                cs[len++] = c;
            }
        }
        int subLen = len;
        if (len % 3 == 1) {
            subLen = len - 4;
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < subLen; i++) {
            if (count == 3) {
                sb.append("-");
                count = 0;
            }
            count++;
            sb.append(cs[i]);
        }
        if (subLen < len) {
            sb.append(cs[subLen++]);
            sb.append(cs[subLen++]);
            sb.append("-");
            sb.append(cs[subLen++]);
            sb.append(cs[subLen++]);
        }
        return sb.toString();
    }

    public int maximumUniqueSubarray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int temp = 0;
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (map.containsKey(n)) {
                int preTemp = map.get(n);
                if (preTemp >= pre) {
                    while (preTemp >= pre) {
                        temp -= nums[pre++];
                    }
                }
                temp += n;
            } else {
                temp += n;
            }
            map.put(n, i);
            max = Math.max(temp, max);
        }
        return max;
    }


    public void permulationAll(String s) {
        if (s.length() != 0) {
            Character[] charObjectArray =
                    s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
            permulationAll(charObjectArray, 0);
        }
    }

    private void permulationAll(Character[] strs, int pos) {
        int len = strs.length;
        if (pos == len) {
            for (Character str : strs) {
                System.out.print(str);
            }
            System.out.println(" ");
        } else {
            for (int i = pos; i < len; i++) {
                swap(strs, pos, i);
                permulationAll(strs, pos + 1);
                swap(strs, pos, i);
            }
        }
    }

    public int minimumOperations(String leaves) {
        System.out.println(leaves);
        int len = leaves.length();
        int[][] status = new int[len][3];
        char[] cs = leaves.toCharArray();
        status[0][0] = isYellow(cs[0]);
        status[0][1] = 10;
        status[0][2] = 10;
        for (int i = 1; i < len; i++) {
            char c = cs[i];
            status[i][0] = status[i - 1][0] + isYellow(c);
            status[i][1] = Math.min(status[i - 1][0], status[i - 1][1]) + isRed(c);
            status[i][2] = Math.min(status[i - 1][1], status[i - 1][2]) + isYellow(c);
        }
        return status[len - 1][2];
    }

    private int isYellow(char c) {
        return c - 'y' == 0 ? 1 : 0;
    }

    private int isRed(char c) {
        return c - 'r' == 0 ? 1 : 0;
    }

    public int numJewelsInStones(String J, String S) {
        if (S.length() == 0) return 0;
        char[] ss = S.toCharArray();
        char[] js = J.toCharArray();
        Map<Character, Integer> map = new HashMap();
        for (char c : ss) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int count = 0;
        for (char c : js) {
            count += map.getOrDefault(c, 0);
        }
        return count;
    }

    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        int lastrepeat = -1;
        int len = s.length();
        if (len == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        char[] cs = s.toCharArray();
        for (int i = 0; i < len; i++) {
            char c = cs[i];
            if (map.containsKey(c)) {
                max = Math.max(max, i - lastrepeat - 1);
                lastrepeat = Math.max(map.get(c), lastrepeat);
            }
            map.put(c, i);
        }
        int temp = len - lastrepeat - 1;
        max = Math.max(temp, max);
        return max;
    }

    public int nthUglyNumber(int n) {
        List<Integer> list = new ArrayList<>(n);
        list.add(1);
        int i = 0, j = 0, k = 0;
        int ni, nj, nk, temp;
        for (int c = 1; c < n; c++) {
            ni = list.get(i) * 2;
            nj = list.get(j) * 3;
            nk = list.get(k) * 5;
            temp = Math.min(nk, Math.min(ni, nj));
            list.add(temp);
            if (ni == temp) i++;
            if (nj == temp) j++;
            if (nk == temp) k++;
        }
        return list.get(n - 1);
    }

    public double[] twoSum(int n) {
        double[] pre = new double[]{1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d, 1 / 6d};
        int lentemp = 1;
        for (int k = 1; k < n; k++) {
            lentemp += 5;
            double[] temp = new double[lentemp + 5];
            for (int i = 0; i < lentemp; i++) {
                for (int j = 0; j < 6; j++) {
                    temp[i + j] += pre[i] / 6;
                }
            }
            pre = temp;
        }
        return pre;
    }

    public boolean isStraight(int[] nums) {
        //最大最小值相差不超过5，且只允许0重复
        int min = 14, max = -1;
        //Set<Integer> set = new HashSet<>(5);
        boolean[] ba = new boolean[14];
        for (int n : nums) {
            if (n != 0) {
                //if(!set.add(n)) return false;
                if (ba[n]) {
                    return false;
                } else ba[n] = true;
                min = Math.min(min, n);
                max = Math.max(max, n);
            }
        }
        return (max - min) < 5;
    }

    public List<String> commonChars(String[] A) {
        int[] cs = new int[26];
        Arrays.fill(cs, Integer.MAX_VALUE);
        for (String s : A) {
            int len = s.length();
            int[] temp = new int[26];
            char c;
            for (int i = 0; i < len; i++) {
                c = s.charAt(i);
                temp[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                cs[i] = Math.min(cs[i], temp[i]);
            }
        }
        List<String> list = new ArrayList<>();
        int i = 0;
        for (char c = 'a'; c <= 'z'; c++, i++) {
            while (cs[i]-- > 0) {
                list.add(String.valueOf(c));
            }
        }
        return list;
    }

    public int[] mergeSort(int[] nums) {
        int len = nums.length;
        if (len <= 1) return nums;
        int[] a = mergeSort(Arrays.copyOfRange(nums, 0, len / 2));
        int[] b = mergeSort(Arrays.copyOfRange(nums, len / 2, len));
        int[] sorted = new int[len];
        int p = len / 2 - 1, q = len - len / 2 - 1, r = len - 1;
        while (p >= 0 && q >= 0) {
            if (a[p] <= b[q]) sorted[r--] = b[q--];
            else sorted[r--] = a[p--];
        }
        if (p >= 0) System.arraycopy(a, 0, sorted, 0, p + 1);
        else System.arraycopy(b, 0, sorted, 0, q + 1);
        return sorted;
    }

    //还有更好的方法
    public int maximalNetworkRank(int n, int[][] roads) {
        if (roads.length <= 2) return roads.length;
        Map<Integer, Integer> map = new HashMap<>();
        boolean[][] bls = new boolean[n][n];
        int a, b;
        for (int[] rs : roads) {
            a = rs[0];
            b = rs[1];
            bls[a][b] = true;
            bls[b][a] = true;
            map.put(a, map.getOrDefault(a, 0) + 1);
            map.put(b, map.getOrDefault(b, 0) + 1);
        }
        int m1 = 0, m2 = 0, m1n = 0, m2n = 0;
        for (int val : map.values()) {
            if (val >= m1) {
                if (val > m1) {
                    m2 = m1;
                    m2n = m1n;
                    m1 = val;
                    m1n = 1;
                } else m1n++;
            } else if (val >= m2) {
                if (val > m2) {
                    m2 = val;
                    m2n = 1;
                } else m2n++;
            }
        }
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        if (m1n >= 2) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == m1) list1.add(entry.getKey());
            }
            int res = 2 * m1 - 1;
            out:
            for (int i = 0; i < m1n - 1; i++) {
                int n1 = list1.get(i);
                for (int j = i + 1; j < m1n; j++) {
                    int n2 = list1.get(j);
                    if (!bls[n1][n2]) {
                        res++;
                        break out;
                    }
                }
            }
            return res;
        } else {
            int n1 = 0;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == m1) n1 = entry.getKey();
                if (entry.getValue() == m2) list2.add(entry.getKey());
            }
            int res = m1 + m2 - 1;
            for (int n2 : list2) {
                if (!bls[n1][n2]) {
                    res++;
                    break;
                }
            }
            return res;
        }
    }

    public int[][] findContinuousSequence(int target) {
        int start = 2, sn = 3;
        List<int[]> list = new ArrayList<>();
        while (target >= sn) {
            if ((target - sn) % start == 0) {
                int plus = (target - sn) / start + 1;
                //打印plus开始的start个数
                int[] temp = new int[start];
                for (int i = 0; i < start; i++) {
                    temp[i] = plus + i;
                }
                list.add(temp);
            }
            start++;
            sn += start;
        }
        return list.toArray(new int[list.size()][]);
    }

    public int[] mergeTwoSortedSeqInOneArray(int[] nums) {
        int len = nums.length;
        if (len <= 1) return nums;
        int first = nums[0], p = 0;
        return null;
    }

    public boolean backspaceCompare(String S, String T) {
        int len1 = S.length();
        int len2 = T.length();
        int bc = 0;
        while (len1 > 0 && len2 > 0) {
            len1--;
            len2--;
            while (len1 >= 0 && (S.charAt(len1) == '#' || bc > 0)) {
                if (S.charAt(len1) == '#') bc++;
                else bc--;
                len1--;
            }
            len1 = Math.max(-1, len1 - bc);
            bc = 0;
            while (len2 >= 0 && (T.charAt(len2) == '#' || bc > 0)) {
                if (T.charAt(len2) == '#') bc++;
                else bc--;
                len2--;
            }
            len2 = Math.max(-1, len2 - bc);
            if (len1 == -1 || len2 == -1) {
                if (len1 == -1 && len2 == -1) return true;
                return false;
            }
            char cs = S.charAt(len1);
            char ct = T.charAt(len2);
            if (ct != cs) return false;
            bc = 0;
        }
        while (len1-- > 0) if (S.charAt(len1) != '#') return false;
        while (len2-- > 0) if (T.charAt(len2) != '#') return false;
        return true;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return 0;
        HashSet<String> start = new HashSet<>();
        HashSet<String> end = new HashSet<>();
        HashSet<String> dic = new HashSet<>(wordList);
        start.add(beginWord);
        end.add(endWord);
        int step = 1;
        if (!dic.contains(endWord)) return 0;
        while (!start.isEmpty()) {
            step++;
            HashSet<String> tmpSet = new HashSet<>();
            dic.removeAll(start);
            for (String s : start) {
                char[] arr = s.toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    char tmp = arr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (tmp == c) continue;
                        arr[i] = c;
                        String strTmp = new String(arr);
                        if (dic.contains(strTmp)) {
                            if (end.contains(strTmp)) {
                                return step;
                            } else {
                                tmpSet.add(strTmp);
                            }
                        }
                    }
                    arr[i] = tmp;
                }
            }
            if (tmpSet.size() < end.size()) {
                start = tmpSet;
            } else {
                start = end;
                end = tmpSet;
            }

        }
        return 0;
    }

    public int[][] reconstructQueue(int[][] people) {
        List<int[]> list = Arrays.asList(people);
        list.sort((a, b) -> {
            int res = 0;
            if (a[0] > b[0]) {
                res = 1;
            } else if (a[0] < b[0]) {
                res = -1;
            } else if (a[1] > b[1]) {
                res = 1;
            } else if (a[1] < b[1]) {
                res = -1;
            }
            return res;
        });
        int[][] res = new int[people.length][2];
        int p = -1, q = 0, count = 0;
        for (int[] ns : list) {
            p++;
            if (p > 0 && ns[0] != list.get(p - 1)[0]) count = 0;
            if (count == ns[1]) {
                // list.add();
            }
            count++;
        }
        return new int[2][];
    }

    public void maxNumber(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        PriorityQueue<int[]> queue1 = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        PriorityQueue<int[]> queue2 = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < len1; i++) {
            queue1.add(new int[]{nums1[i], i + 1});
        }
        for (int i = 0; i < len2; i++) {
            queue2.add(new int[]{nums2[i], i + 1});
        }
        int[][] ns1 = queue1.toArray(new int[queue1.size()][]);
        int[][] ns2 = queue2.toArray(new int[queue2.size()][]);
        int p1 = 0, p2 = 0, r1 = len1, r2 = len2;
        while (k > 0) {
            //在n1,n2中找最大的,同时这个数要在上一个数的后面
            int n = -1, p = 0;
            if (ns1[p1][0] < ns2[p2][0]) {
                n = ns2[p2][0];
                p = ns2[p2++][1];
                r2 = len2 - p;
            } else {
                n = ns1[p1][0];
                p = ns1[p1++][1];
                r1 = len1 - p;
            }
            if (r1 + r2 < k) {
                continue;
            }

        }
    }

    public int matrixScore(int[][] A) {
        int m = A.length;
        if (m == 0) return 0;
        int n = A[0].length;
        if (n == 1) return m;
        if (m == 1) return (int) Math.pow(2, n) - 1;
        int res = 0;
        int base = 1;
        for (int i = n - 1; i > 0; i--) {
            int count = 0;
            for (int[] ints : A) {
                if (ints[i] == ints[0]) count++;
            }
            count = Math.max(count, m - count);
            res += count * base;
            base = base << 1;
        }
        res += m * base;
        return res;
    }

    public int uniquePaths(int m, int n) {
        //C(m+n-2,n-1)
        //考虑溢出
        long res = 1L;
        int a = m + n - 2;
        int b = Math.min(m, n) - 1;
        //乘大除小
        int count = 0;
        while (count < b) {
            res *= a - count;
            count++;
            res /= count;
        }
        return (int) res;
    }

    public List<Integer> splitIntoFibonacci(String s) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        char[] chars = s.toCharArray();
        int sLen = chars.length;
        int baseLen = 1;
        while (sLen / baseLen >= 3) {
            List<Integer> list = new ArrayList<>();
            int n1 = AlgoUtil.getNumFromStr(chars, 0, baseLen);
            int n2 = AlgoUtil.getNumFromStr(chars, baseLen, baseLen);
            int point = baseLen * 2;

            list.add(n1);
            list.add(n2);
            int n3 = n1 + n2;
            while ((point = splitIntoFibonacciVerify(chars, point, n3)) > 0) {
                list.add(n3);
                n1 = n2;
                n2 = n3;
                n3 = n1 + n2;
            }
            list.add(n3);
            if (point == 0) {
                return list;
            }
            baseLen++;
        }
        return new ArrayList<>();
    }

    private int splitIntoFibonacciVerify(char[] cs, int offset, int target) {
        //true 返回new offset； end 返回0； false 返回-1
        int len = cs.length;
        int n = cs[offset++] - 48;
        if (n == 0) return -1;
        while (n < target && offset < len) {
            n = n * 10 + cs[offset++] - 48;
        }
        if (n == target) {
            if (offset == len) return 0;
            return offset;
        }
        return -1;
    }

    public String predictPartyVictory(String senate) {
        char[] chars = senate.toCharArray();
        boolean[] booleans = new boolean[chars.length];
        int count = 0, rn, dn;
        do {
            rn = 0;
            dn = 0;
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (booleans[i]) continue;
                if (c == 'R') {
                    count++;
                    if (count > 0) rn++;
                    else booleans[i] = true;
                } else {
                    count--;
                    if (count < 0) dn++;
                    else booleans[i] = true;
                }
            }
        } while (rn != 0 && dn != 0);
        if (rn != 0) return "Radiant";
        return "Dire";
    }

    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        int price = 5;
        for (int bill : bills) {
            int change = bill - price;
            if (change >= 10 && ten >= 10) {
                int num = Math.min(change / 10, ten / 10);
                ten -= 10 * num;
                change -= 10 * num;
            }
            five -= change;
            if (five < 0) return false;
            if (bill == 5) five += 5;
            if (bill == 10) ten += 10;
        }
        return true;
    }

    public int wiggleMaxLength(int[] nums) {
        int len = nums.length;
        if (len <= 1) return len;
        int pre = nums[0];
        int next;
        int index = 1;
        while (index < len && nums[index] == pre) index++;
        if (index == len) return 1;
        next = nums[index++];
        int count = 2;
        boolean turnUp = (next - pre) < 0;
        while (index < len) {
            pre = next;
            next = nums[index++];
            if (next == pre || turnUp == (next - pre) < 0) {
                continue;
            }
            count++;
            turnUp = !turnUp;
        }
        return count;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        PriorityQueue<Character> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        for (String s : strs) {
            for (char c : s.toCharArray()) {
                queue.add(c);
            }
            StringBuilder sb = new StringBuilder();
            while (!queue.isEmpty()) {
                sb.append(queue.poll());
            }
            String key = sb.toString();
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(s);
        }
        return new ArrayList<>(map.values());
    }

    public boolean wordPattern(String pattern, String s) {
        String[] list = s.split(" ");
        int len = pattern.length();
        if (len != list.length) return false;
        Map<Character, String> map = new HashMap<>(len);
        Set<String> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            char key = pattern.charAt(i);
            String target = map.get(key);
            if (target != null) {
                if (!target.equals(list[i])) {
                    return false;
                }
            } else {
                if (set.add(list[i])) {
                    map.put(key, list[i]);
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public int monotoneIncreasingDigits(int N) {
        List<Integer> list = new ArrayList<>();
        int pre = Integer.MAX_VALUE;
        int count = 1, zPoint = 0;
        while (N != 0) {
            count *= 10;
            int n = N % 10;
            if (n == 0) zPoint = count;
            if (pre != 0) {
                pre = Math.min(pre, n);
            } else {
                pre = n;
            }
            list.add(pre);
            N /= 10;
        }
        int res = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            int n = list.get(i);
            res = res * 10 + n;
        }
        if (zPoint > 0) {
            int srcHead = N / zPoint;
            int desHead = res / zPoint;
        }
        return res;
    }

    public int maxProfit(int[] prices, int fee) {
        int len = prices.length;
        if (len < 2) return 0;
        int pre = prices[0];
        Integer max = null;
        int count = 0;
        for (int i = 1; i < len; i++) {
            int n = prices[i];
            if (n <= pre) {
                if (max == null) {
                    pre = n;
                } else {
                    System.out.println("buy:" + pre + ";sale:" + max);
                    count += max - pre - fee;
                    pre = n;
                    max = null;
                }
            } else {
                if (max == null) {
                    if (n > pre + fee) max = n;
                } else if (n > max) {
                    max = n;
                } else if (n <= max - fee) {
                    System.out.println("buy:" + pre + ";sale:" + max);
                    count += max - pre - fee;
                    pre = n;
                    max = null;
                }
            }
        }
        if (max != null) {
            System.out.println("buy:" + pre + ";sale:" + max);
            count += max - pre - fee;
        }
        return count;
    }

    public int maxProfit2(int[] prices, int fee) {
        int pLen = prices.length;
        if (pLen == 0) return 0;
        int dp_prev_0 = 0;
        int dp_prev_1 = -(prices[0] + fee);
        for (int i = 1; i < pLen; i++) {
            int dp_0 = Math.max(dp_prev_0, dp_prev_1 + prices[i]);
            int dp_1 = Math.max(dp_prev_1, dp_prev_0 - (prices[i] + fee));
            dp_prev_0 = dp_0;
            dp_prev_1 = dp_1;
        }
        return dp_prev_0;
    }

    public char findTheDifference(String s, String t) {
        int[] chars = new int[26];
        for (char c : s.toCharArray()) {
            chars[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            int n = c - 'a';
            if (chars[n] == 0) {
                return c;
            }
            chars[n]--;
        }
        return 'a';
    }

    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        if (len <= 2) return cost[len - 1];
        for (int i = 2; i < len; i++) {
            cost[i] += Math.min(cost[i - 1], cost[i - 2]);
        }
        return Math.min(cost[len - 1], cost[len - 2]);
    }

    public int maxResult(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) return 0;
        Deque<Integer> maxInK = new LinkedList<>();
        int[] scores = new int[len];
        maxInK.addFirst(nums[0]);
        scores[0] = nums[0];
        for (int i = 1; i < len; i++) {
            //前k个取max加上自己
            int max = maxInK.getFirst();
            int n = nums[i] + max;
            scores[i] = n;

            if (i >= k && max == scores[i - k]) {
                maxInK.removeFirst();
            }

            while (!maxInK.isEmpty() && maxInK.getLast() < n) {
                maxInK.removeLast();
            }
            maxInK.addLast(n);
        }
        return scores[len - 1];
    }

    public String removeDuplicateLetters(String s) {
        return "hello";
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        int size = queue.size();
        boolean leftToRight = true;
        while (size > 0) {
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                if (leftToRight) {
                    TreeNode treeNode = queue.removeLast();
                    list.add(treeNode.val);
                    if (treeNode.left != null) queue.addFirst(treeNode.left);
                    if (treeNode.right != null) queue.addFirst(treeNode.right);
                } else {
                    TreeNode treeNode = queue.removeFirst();
                    list.add(treeNode.val);
                    if (treeNode.right != null) queue.addLast(treeNode.right);
                    if (treeNode.left != null) queue.addLast(treeNode.left);
                }
            }
            lists.add(list);
            leftToRight = !leftToRight;
            size = queue.size();
        }
        return lists;
    }

    //有快20倍的
    public int firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>(26);
        Set<Character> set = new HashSet<>(26);
        int index = 0;
        for (char c : s.toCharArray()) {
            if (!set.contains(c)) {
                map.put(c, index);
                set.add(c);
            } else {
                map.remove(c);
            }
            index++;
        }
        Iterator<Integer> iterator = map.values().iterator();
        return iterator.hasNext() ? iterator.next() : -1;
    }

    //慢逼方法
    public int candy(int[] ratings) {
        int length = ratings.length;
        if (length <= 1) return length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < length; i++) {
            int[] temp = new int[]{ratings[i], i};
            queue.add(temp);
        }
        int[] res = new int[length];
        while (!queue.isEmpty()) {
            int index = queue.poll()[1];
            int rating = ratings[index];
            int left = ((index - 1 >= 0) && ratings[index - 1] < rating) ? res[index - 1] : 0;
            int right = ((index + 1 < length) && ratings[index + 1] < rating) ? res[index + 1] : 0;
            res[index] = Math.max(left, right) + 1;
        }
        return Arrays.stream(res).reduce(0, Integer::sum);
    }

    public int candyFast(int[] ratings) {
        int[] candy = new int[ratings.length];
        int num = ratings.length;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1;
            }
        }
        for (int j = ratings.length - 1; j > 0; j--) {
            if (ratings[j - 1] > ratings[j]) {
                if (candy[j - 1] <= candy[j]) {
                    candy[j - 1] = candy[j] + 1;
                }
            }
        }
        for (int n = 0; n < ratings.length; n++) {
            num += candy[n];
        }
        return num;
    }

    //todo
    public int minPatches(int[] nums, int n) {
        int len = nums.length;
        int count = 0, max = 0, p = 0, base = 1;
        while (max < n) {

        }
        return count;
    }

    //todo
    public int lastStoneWeight(int[] stones) {
        int reduce = Arrays.stream(stones).sorted().reduce(0, (a, b) -> Math.abs(a - b));
        return reduce;
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int zeroCount = 1;
        int max = 0;
        for (int f : flowerbed) {
            if (f == 0) {
                zeroCount++;
            }
            if (f == 1) {
                zeroCount = 0;
            }
            if (zeroCount == 3) {
                max++;
                zeroCount = 1;
            }
        }
        if (zeroCount == 2) max++;
        return n <= max;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        int len = intervals.length;
        if (len <= 1) return 0;
        int count = 0;
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]) != 0 ? (a[0] - b[0]) : (a[1] - b[1]));
        int[] pre = intervals[0];
        for (int i = 1; i < len; i++) {
            int[] temp = intervals[i];
            if (pre[0] == temp[0] || pre[1] > temp[0]) {
                count++;
                if (pre[1] > temp[1]) {
                    pre = temp;
                }
            } else {
                pre = temp;
            }
        }
        return count;
    }

    //fixme
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < values.length; i++) {
            List<String> equation = equations.get(i);
            updateCalcResult(map, equation.get(0), equation.get(1), values[i]);
            updateCalcResult(map, equation.get(1), equation.get(0), 1 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            Map<String, Double> resultMap = map.get(query.get(0));
            if (resultMap == null) {
                res[i] = -1.0d;
            } else {
                if (query.get(0).equals(query.get(1))) {
                    res[i] = 1.d;
                    continue;
                }
                res[i] = resultMap.getOrDefault(query.get(1), -1.0d);
            }
        }
        return res;
    }

    private void updateCalcResult(Map<String, Map<String, Double>> map, String numerator, String denominator, double value) {
        Map<String, Double> resultMap = map.computeIfAbsent(numerator, e -> new HashMap<>());
        for (Map.Entry<String, Double> entry : resultMap.entrySet()) {
            String oldDe = entry.getKey();
            double oldValue = entry.getValue();
            Map<String, Double> tempMap = map.computeIfAbsent(denominator, e -> new HashMap<>());
            tempMap.put(oldDe, oldValue / value);
            tempMap = map.computeIfAbsent(oldDe, e -> new HashMap<>());
            tempMap.put(denominator, value / oldValue);
        }
        resultMap.put(denominator, value);
    }

    public int findCircleNum(int[][] isConnected) {
        int count = 0;
        boolean[] reach = new boolean[isConnected.length];
        for (int i = 0; i < isConnected.length; i++) {
            if (!reach[i]) {
                count++;
                findCircleHelper(isConnected, reach, i);
            }
        }
        return count;
    }

    private void findCircleHelper(int[][] isConnected, boolean[] reach, int n) {
        reach[n] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[n][i] == 1 && !reach[i]) {
                findCircleHelper(isConnected, reach, i);
            }
        }
    }

    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if (len <= 1) return;
        k = k % len;
        int count = 0, start = -1, p = -1;
        int nextNum = nums[0];
        int tempNum;
        while (count < len) {
            if (p == start) {
                start = ++p;
                tempNum = nums[start];
            } else tempNum = nextNum;
            p = (p + k) % len;
            nextNum = nums[p];
            nums[p] = tempNum;
            count++;
        }
    }

    //慢
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        //分成不同的联通集
        //每个联通集下标按数字顺序放入list，字母放入Priority Queue
        //依次从queue里面取出字母，按list里面的下标放到res数组里
        int len = s.length();
        boolean[] reached = new boolean[len];
        char[] res = new char[len];
        Map<Integer, List<Integer>> map = new HashMap<>();
        PriorityQueue<Character> que = new PriorityQueue<>();
        for (List<Integer> pair : pairs) {
            List<Integer> list = map.computeIfAbsent(pair.get(0), a -> new ArrayList<Integer>());
            list.add(pair.get(1));
            list = map.computeIfAbsent(pair.get(1), a -> new ArrayList<Integer>());
            list.add(pair.get(0));
        }
        for (int i = 0; i < len; i++) {
            List<Integer> lits = new ArrayList<>();
            if (!reached[i]) {
                smallestHelper(map, reached, i, lits, s, que);
            }
            lits.sort((a, b) -> a - b);
            for (Integer n : lits) {
                res[n] = que.poll();
            }
        }
        return new String(res);
    }

    private void smallestHelper(Map<Integer, List<Integer>> map, boolean[] reached, int n, List<Integer> list, String s, PriorityQueue<Character> que) {
        reached[n] = true;
        list.add(n);
        que.add(s.charAt(n));
        if (!map.containsKey(n)) return;
        for (Integer next : map.get(n)) {
            if (!reached[next]) {
                smallestHelper(map, reached, next, list, s, que);
            }
        }
    }

    public int[] findRedundantConnection(int[][] edges) {
        int len = edges.length;
        UnionFind unionFind = new UnionFind(len);
        for (int[] edge : edges) {
            int a = edge[0] - 1;
            int b = edge[1] - 1;
            int root1 = unionFind.findRoot(a);
            int root2 = unionFind.findRoot(b);
            if (root1 == root2) return edge;
            unionFind.mergeRoot(root1, root2);
        }
        return new int[2];
    }

    //todo
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        Set<Integer>[] sets = new Set[m];
        for (int i = 0; i < m; i++) {
            sets[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i++) {
            sets[group[i] + 1].add(i);
        }

        return new int[1];
    }

    //todo
    public int removeStones(int[][] stones) {
        int len = stones.length;
        int[] parent = new int[2*len];
        UnionFind unionFind = new UnionFind(2*len);
        for (int[] stone : stones) {
            int x = unionFind.findRoot(stone[0]);
            int y = unionFind.findRoot(stone[1]+len);

        }

        return 0;
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToIndex = new HashMap<String, Integer>();
        Map<String, String> emailToName = new HashMap<String, String>();
        int emailsCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, emailsCount++);
                    emailToName.put(email, name);
                }
            }
        }
        UnionFind uf = new UnionFind(emailsCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                uf.mergeRoot(firstIndex, nextIndex);
            }
        }
        Map<Integer, List<String>> indexToEmails = new HashMap<Integer, List<String>>();
        for (String email : emailToIndex.keySet()) {
            int index = uf.findRoot(emailToIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(index, new ArrayList<String>());
            account.add(email);
            indexToEmails.put(index, account);
        }
        return accountMergeHelper(emailToName, indexToEmails);
    }

    public static List<List<String>> accountMergeHelper(Map<String, String> emailToName, Map<Integer, List<String>> indexToEmails) {
        List<List<String>> merged = new ArrayList<List<String>>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<String>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }

    public int regionsBySlashes(String[] grid) {
        int len = grid.length;
        UnionFind unionFind = new UnionFind(4*len*len);
        for (int i = 0; i < len; i++) {
            String row = grid[i];
            for (int j = 0; j < row.length(); j++) {
                int n = 4 * ( i*len + j);
                //聚合内部小块
                switch (row.charAt(j)){
                    case '/':
                        unionFind.mergeRoot(n,n+1);
                        unionFind.mergeRoot(n+2,n+3);
                        break;
                    case ' ':
                        unionFind.mergeRoot(n,n+1);
                        unionFind.mergeRoot(n,n+2);
                        unionFind.mergeRoot(n,n+3);
                        break;
                    default:
                        unionFind.mergeRoot(n,n+3);
                        unionFind.mergeRoot(n+1,n+2);
                }
                //聚合左和上的块
                if (j>0) unionFind.mergeRoot(n-2,n);
                if (i>0) unionFind.mergeRoot(n-4*len+3,n+1);
            }
        }
        return unionFind.size();
    }

    public int makeConnected(int n, int[][] connections) {
        int len = connections.length;
        if (len<n-1) return -1;
        UnionFind unionFind = new UnionFind(n);
        for (int[] connection : connections) {
            unionFind.mergeRoot(connection[0],connection[1]);
        }
        return unionFind.size()-1;
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int[] nums = new int[n];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->b[2]-a[2]);
        for (int[] edge : edges) {
            nums[edge[0]]++;
            nums[edge[1]]++;
            queue.add(edge);
        }
        return new ArrayList<>();
    }
    private void findCAPCEHelper(PriorityQueue<int[]> queue, int[][] edges, int[] nums, int i){
        int[] peek = queue.peek();
        while (nums[peek[0]]<2 || nums[peek[1]]<2) {
            queue.poll();
            peek = queue.peek();
        }
        int[] poll = queue.poll();

    }

    public int numEquivDominoPairs(int[][] dominoes) {
        int[][] map = new int[10][10];
        for (int[] dominoe : dominoes) {
            map[dominoe[0]][dominoe[1]]++;
            if (dominoe[0]!=dominoe[1])
            map[dominoe[1]][dominoe[0]]++;
        }
        int count = 0;
        for (int i = 1; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                int n = map[i][j];
                if (n>1){
                    count += n*(n-1)/2;
                }
            }
        }
        return count;
    }

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        UnionFind unionFind =new UnionFind(n+1);
        UnionFind ua = new UnionFind(n+1);
        UnionFind ub = new UnionFind(n+1);
        int count3 = 0;
        for (int[] edge : edges) {
            if (edge[0]==3){
                if (unionFind.mergeRoot(edge[1],edge[2])) count3++;
                ua.mergeRoot(edge[1],edge[2]);
                ub.mergeRoot(edge[1],edge[2]);
            }else if (edge[0]==1) ua.mergeRoot(edge[1],edge[2]);
            else ub.mergeRoot(edge[1],edge[2]);
        }
        if (ua.size()>2 || ub.size()>2) return -1;
        return edges.length - 2*n + 2 + count3;
    }

    public int maximumProduct(int[] nums) {
        int len = nums.length;
        if (len==3) return nums[0]*nums[1]*nums[2];
        int min1=Integer.MAX_VALUE, min2=Integer.MAX_VALUE;
        int max1=Integer.MIN_VALUE,max2=Integer.MIN_VALUE,max3=Integer.MIN_VALUE;
        for (int n : nums) {
            if (n>max1){
                max3 = max2;
                max2 = max1;
                max1 = n;
            }else if (n>max2){
                max3 = max2;
                max2 = n;
            }else if (n>max3){
                max3 = n;
            }
            if (n<min1){
                min2 = min1;
                min1 = n;
            }else if (n<min2){
                min2 = n;
            }
        }
        return Math.max(min1*min2*max1,max1*max2*max3);
    }

    public int pivotIndex(int[] nums) {
        int len = nums.length;
        if (len==0) return -1;
        if (len==1) return 0;
        int l2r = 0;
        int[] r2l = new int[len];
        for (int i = len-2; i >= 0; i--) {
            r2l[i] = r2l[i+1] + nums[i+1];
        }
        if (r2l[0]==0) return 0;
        for (int i = 1; i < len; i++) {
            l2r += nums[i-1];
            if (l2r == r2l[i]) return i;
        }
        return -1;
    }

    public int minCostConnectPoints(int[][] points) {
        //每个点都与其最近点连接，怎么寻找？
        int len = points.length;
        if (len <= 1) return 0;
        int[] closest = new int[len];
        int clo1 = Integer.MAX_VALUE;
        for (int i = 1; i < len; i++) {
            int distance = minCostConnectPointsHelper(points[0], points[i]);
            clo1 = Math.min(distance,clo1);
            closest[i] = distance;
        }
        closest[0] = clo1;
        for (int i = 1; i < len-1; i++) {
            int close = closest[i];
            for (int j = i+1; j < len; j++) {
                int distance = minCostConnectPointsHelper(points[i], points[j]);
                closest[j] = Math.min(distance,closest[j]);
                close = Math.min(distance,close);
            }
            closest[i] = close;
        }
        int res = closest[0];
        int min = closest[0];
        for (int i = 1; i < len; i++) {
            res += closest[i];
            min = Math.min(min,closest[i]);
        }
        return res - min;
    }
    private int minCostConnectPointsHelper(int[]a, int[]b){
        return Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);
    }

    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int columns = heights[0].length;
        boolean[][] reached = new boolean[rows][columns];
        int[][] dij = new int[rows][columns];
        dij[0][0] = 0;
        dij[0][1] = Math.abs(heights[0][0]-heights[0][1]);
        dij[1][0] = Math.abs(heights[0][0]-heights[1][0]);
        //用dijkstra算法试试
        int x = 0; int y = 0;

        return 0;
    }

    //直方图水量[0,1,0,2,1,0,1,3,2,1,2,1]
    public int trap(int[] height) {
        int len = height.length;
        if (len < 3) return 0;
        int water = 0;
        int maxH = height[0];
        int[] temp = new int[len];

        // l to r
        temp[0] = 0;
        for (int i = 1; i < len; i++) {
            int h = height[i];
            if (maxH > h){
                temp[i] = maxH - h;
            }else {
                maxH = h;
            }
        }
        // r to l
        maxH = height[len-1];
        temp[len-1] = 0;
        for (int i = len-2; i >= 0; i--) {
            int h = height[i];
            if (maxH > h){
                temp[i] = Math.min(temp[i], (maxH - h));
            }else {
                maxH = h;
                temp[i] = 0;
            }
        }

        for (int h : temp) {
            if (h>0) water += h;
        }

        return water;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length()+1;
        int n = text2.length()+1;
        if (m==1 || n==1){
            return 0;
        }
        int[][] res = new int[m][n];
        for (int i = 1; i < m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j < n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2){
                    res[i][j] = res[i-1][j-1] + 1;
                }else {
                    res[i][j] = Math.max(res[i-1][j],res[i][j-1]);
                }
            }
        }
        return res[m-1][n-1];
    }

    public boolean search(int[] nums, int target) {
        int start = nums[0];
        if (start ==target) return true;
        boolean atLeft = isAtLeft(start,target);
        int left = 1, right = nums.length-1;
        while (left<right && nums[left]==start) left++;
        while (left<right && nums[right]==start) right--;

        while (left <= right){
            int mid = (left+right)>>>1;
            int midN = nums[mid];
            if (midN ==target) return true;
            if (atLeft == isAtLeft(start, midN)){
                if ((midN >target)){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else if (atLeft){
                right = mid - 1;
            }else {
                left = mid + 1;
            }

        }
        return false;
    }

    private boolean isAtLeft(int start, int n){
        return start <= n;
    }

    public int findMin(int[] nums) {
        int start = nums[0];
        int left = 1, right = nums.length-1;
        if (start < nums[right]) return start;
        while (left<right && nums[left]==start) left++;
        while (left<right && nums[right]==start) right--;
        while (left<=right){
            int mid = (left+right)>>>1;
            if (nums[mid]<nums[mid-1]) return nums[mid];
            if (isAtLeft(start,nums[mid])) left = mid + 1;
            else right = mid - 1;
        }
        return nums[right];
    }

    //笨阶乘
    public int clumsy(int N) {
        int res = getNextClumsyPart(N);
        N -= 3;
        while (N > 0){
            res += N--;
            res -= getNextClumsyPart(N);
            N -= 3;
        }
        return res;
    }

    private int getNextClumsyPart(int n){
        if (n <= 0) return 0;
        int res = n--;
        if (n>0) res *= n--;
        if (n>0) res /= n;
        return res;
    }

    public int clumsyFast(int N){
        if (N < 3) return N;
        if (N == 3) return 6;
        if (N == 4) return 7;
        int n = N % 4;
        if (n == 0) return N + 1;
        if (n < 3) return N + 2;
        return N - 1;
    }

    public int findMin2(int[] nums){
        int start = nums[0];
        if (nums.length == 1) return start;
        int left = 1, right = nums.length - 1;
        while (left < right && nums[left]==start) left++;
        while (left < right && nums[right]==start) right--;
        while (left <= right){
            int mid = (left + right) >>> 1;
            if (nums[mid]<nums[mid-1]) return nums[mid];
            if (nums[mid] < start) right = mid - 1;
            else left = mid + 1;
        }
        return start;
    }

    public String largestNumber(int[] nums) {
        List<Integer> collect = Arrays.stream(nums).boxed().sorted(this::compareIntPrefix).collect(Collectors.toList());
        if (collect.get(0).equals(0)) return "0";
        StringBuilder sb = new StringBuilder();
        for (Integer integer : collect) {
            sb.append(integer);
        }
        return sb.toString();
    }
    private int compareIntPrefix(int a ,int b){
        String as = String.valueOf(a);
        String bs = String.valueOf(b);
        return (bs+as).compareTo(as+bs);
    }

    public int shipWithinDays(int[] weights, int D) {
        int total = 0;
        for(int n : weights){
            total += n;
        }
        int left = total / D;
        int right = total;
        while(left < right){
            int mid = (left + right) >>> 1;
            if(load(weights,D,mid)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return right;
    }

    private boolean load(int[] weights, int D, int load){
        int rest = load;
        int d = 1;
        for(int n : weights){
            if(n>load) return false;
            if(n>rest){
                d++;
                rest = load - n;
            }else{
                rest -= n;
            }
            if(d > D) return false;
        }
        return true;
    }



    public int FillArray (int[] a, int k) {

        int p = 0;
        int len = a.length;
        int count = 1;
        int head=-1,tail=-1;
        //分段
        //每段先找开头结尾
        List<Integer> range = new ArrayList<>();
        List<Integer> width = new ArrayList<>();
        int mr = Integer.MIN_VALUE;
        int mw = Integer.MIN_VALUE;
        while(p<len){
           int segLen = 1;
            for(;p<len;p++){
                if (a[p]==0){
                    if (p==0){
                        head = 1;
                    }else {
                        head = a[p-1];
                    }
                    break;
                }
            }
            p++;
            if(head==-1) break;
            for (;p<len;p++){
                if (a[p]!=0){
                    tail = a[p];
                    break;
                }
                segLen++;
            }
            if (tail == -1) tail = k;
            range.add(tail-head+1);
            width.add(segLen);
            mr = Math.max(mr,tail-head+1);
            mw = Math.max(mw,segLen);
            //count *= FillArrayHelp(head,tail,segLen);
            head = -1;
            tail = -1;
            p++;
        }
        if (range.isEmpty()) return count;
        return count*FillArrayHelp(mr,mw,range,width);
    }
    public int FillArrayHelp(int mr, int mw, List<Integer> range,List<Integer> width){
        int[][] matrix = new int[mw][mr];
        for (int i = 0; i < mr; i++) {
            matrix[0][i] = i + 1;
        }
        for (int i = 0; i < mw; i++) {
            matrix[i][0] = 1;
        }
        long count = 1;
        for (int i = 0; i < range.size(); i++) {
            int r = range.get(i) - 1;
            int w = width.get(i) - 1;
            if (matrix[w][r]==0){
                count = (count*FillArrayHelp2(r,w,matrix)) % 1000000007;
            }else {
                count = (count*matrix[w][r]) % 1000000007;
            }
        }
        //System.out.println(Arrays.deepToString(matrix));
        return (int) count;
    }
    public int FillArrayHelp2(int r, int w, int[][] matrix){
        if (matrix[w][r] == 0) {
            matrix[w][r] = FillArrayHelp2(r-1,w,matrix) + FillArrayHelp2(r,w-1,matrix);
        }
        return  matrix[w][r];
    }


    public int maxValue (String s, int k) {
        // write code here
        if (s.length()==k) return Integer.parseInt(s);

        String maxV = "0";
        char maxN = '0';
        for (int i = 0; i<= s.length()-k;i++){
            if (s.charAt(i)<maxN) continue;
            maxN = s.charAt(i);
            String temp = s.substring(i,i+k);
            if (maxV.compareTo(temp)<0) {
                maxV = temp;
            }
        }
        return Integer.parseInt(maxV);
    }
    public int maxValue2 (String s, int k) {
        // write code here
        if (s.length()==k) return Integer.parseInt(s);

        String maxV = s.substring(0,k);

        for (int i = 1; i<= s.length()-k;i++){
            int j = 0;
            while (j<k && maxV.charAt(j)==s.charAt(i+j)){
                j++;
            }
            if (j==k) j--;
            if (maxV.charAt(j) < s.charAt(i+j)){
                maxV = s.substring(i,i+k);
            }

        }
        return Integer.parseInt(maxV);
    }

    public TreeNode pruneLeaves (TreeNode root) {
        // write code here
        if (pruneLeaves_needP(root)){
            return null;
        }
        pruneLeaves_foreach(root);
        return root;
    }
    public void pruneLeaves_foreach(TreeNode node){
        if (node == null) return;
        if (pruneLeaves_needP(node.left)){
            node.left = null;
        }
        if (pruneLeaves_needP(node.right)){
            node.right = null;
        }
        pruneLeaves_foreach(node.left);
        pruneLeaves_foreach(node.right);
    }

    public boolean pruneLeaves_needP(TreeNode node){
        if (node==null) return false;
        boolean need = false;
        if (node.left!=null){
            need = pruneLeaves_needP_isLeaf(node.left);
        }
        if (!need && node.right!=null){
            need = pruneLeaves_needP_isLeaf(node.right);
        }
        return need;
    }
    public boolean pruneLeaves_needP_isLeaf(TreeNode node){
        return (node.left==null && node.right==null);
    }

    public String wow(String s) {

        int len = s.length();
        if (len <= 1) return s;
        int max = 1,left = 0;
        boolean start = false;
        String ans = String.valueOf(s.charAt(0));
        for(int i=1;i<len;i++){
            if(left < 0){
                if(max<i){
                    max = i;
                    ans = s.substring(0,i);
                }
                start = false;
                left = i-1;
            }
            if (start){
                if (s.charAt(left)==s.charAt(i)) {
                    left--;
                }else{
                    start = false;
                    if(max<i-left+1){
                        max = i-left+1;
                        ans = s.substring(left+1,i);
                    }
                    left = i-1;
                    i--;
                }
            }else {
                if (s.charAt(i)==s.charAt(left)){
                    start = true;
                    while (left>0 && s.charAt(left-1)==s.charAt(left)) left--;
                    left--;
                    while (i<len-1 && s.charAt(i)==s.charAt(i+1)) i++;
                }else if(i>1 && s.charAt(i)==s.charAt(i-2)){
                    start = true;
                    left = i - 3;
                }else {
                    left = i;
                }
            }
        }
        if (max<len-left-1){
            ans = s.substring(left+1,len);
        }
        return ans;
    }


    public int superPow(int a, int[] b) {
        a = a % 1337;
        return superPowHelper(a,b);
    }
    public int superPowHelper(int a, int[] b) {
        int len = b.length;
        if(len>1){
            int[] half = halfHelper(b);
            int res = superPowHelper(a,half);
            res = (res*res)%1337;
            if(b[len-1]/2==1) res = (res*a) % 1337;
            return res;
        }
        int res = 1;
        for(int i=0; i<b[0];i++){
            res = (res*a) % 1337;
        }
        return res;
    }
    public int[] halfHelper(int[] b) {
        int len = b.length;
        int bs = 0;
        boolean remain = false;
        if(b[0]==1) {
            len--;
            bs = 1;
            remain = true;
        }
        int[] half = new int[len];
        for(int i=0; i<len; i++){
            int n = b[i+bs] / 2;
            if(remain) n += 5;
            half[i] = n;
            remain = b[i+bs]/2 == 1;
        }
        return half;
    }
}

