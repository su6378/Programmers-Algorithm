import java.util.*;

class Test {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
        new Solution().solution(skill, skill_trees);
    }
}

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int sLen = skill.length();
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < skill.length(); i++) {
            map.put(skill.charAt(i), i);
        }

        for (int i = 0; i < skill_trees.length; i++) {
            int idx = 0;
            boolean isFind = true;

            for (int j = 0; j < skill_trees[i].length(); j++) {
                if (map.containsKey(skill_trees[i].charAt(j))) {
                    if (map.get(skill_trees[i].charAt(j)) == idx) idx++;
                    else {
                        isFind = false;
                        break;
                    }
                }
            }

            if (isFind) answer++;
        }

        return answer;
    }
}