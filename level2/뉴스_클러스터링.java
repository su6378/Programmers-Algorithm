import java.util.*;

class Test {
    public static void main(String[] args) {
        String str1 = "abab";
        String str2 = "baba";
        new Solution().solution(str1, str2);
    }
}

class Solution {
    static final int NUM = 65536;

    public int solution(String str1, String str2) {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        sb.append(str1.charAt(0));

        for (int i = 1; i < str1.length(); i++) {
            sb.append(str1.charAt(i));
            String element = sb.toString().toUpperCase();

            boolean isString = true;

            for (int j = 0; j < element.length(); j++) {
                int num = element.charAt(j);

                if (num < 65 || num > 90) {
                    isString = false;
                    break;
                }
            }

            if (isString) {
                list1.add(element);
                map1.put(element, map1.getOrDefault(element, 0) + 1);
            }

            sb.deleteCharAt(0);
        }

        sb.setLength(0);
        sb.append(str2.charAt(0));

        for (int i = 1; i < str2.length(); i++) {
            sb.append(str2.charAt(i));
            String element = sb.toString().toUpperCase();

            boolean isString = true;

            for (int j = 0; j < element.length(); j++) {
                int num = element.charAt(j);

                if (num < 65 || num > 90) {
                    isString = false;
                    break;
                }
            }

            if (isString) {
                list2.add(element);
                map2.put(element, map2.getOrDefault(element, 0) + 1);
            }

            sb.deleteCharAt(0);
        }

        if (list1.isEmpty() && list2.isEmpty()) { // 둘 다 공집합인 경우
            return NUM;
        }

        HashSet<String> set1 = new HashSet<>(list1);
        HashSet<String> set2 = new HashSet<>(list2);

        set1.retainAll(set2); // 교집합 찾기

        list1.addAll(list2); // 합집합

        int same = 0;

        for (String value : set1) { // 교집합의 원소에서 두 집합의 더 작은 원소의 갯수를 더함
            same += Math.min(map1.get(value), map2.get(value));
        }

        int sum = list1.size() - same;

        int jaccard = (int) ((double) same / (double) sum * NUM);

        return jaccard;
    }
}