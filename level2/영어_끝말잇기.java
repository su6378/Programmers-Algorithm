import java.util.*;

class Test {
    public static void main(String[] args) {
        int n = 3;
        String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        new Solution().solution(n, words);
    }
}

public class Solution {
    static int[] answer;

    public int[] solution(int n, String[] words) {
        answer = new int[2];
        int person = 1;
        int round = 1;
        char endCharacter = 'a';
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            // 이전 사람의 마지막 문자와 다른 문자로 시작할 때
            if (i > 0) {
                if (words[i].charAt(0) != endCharacter) {
                    setAnswer(person, round);
                    break;
                }
            }

            // 이전의 말한 단어가 나온 경우
            if (map.containsKey(words[i])) {
                setAnswer(person, round);
                break;
            }
            else map.put(words[i], 1);

            if (person == n) {
                person = 1;
                round++;
            } else person++;

            endCharacter = words[i].charAt(words[i].length() - 1);
        }
        return answer;
    }

    public void setAnswer(int person, int round) {
        answer[0] = person;
        answer[1] = round;
    }
}