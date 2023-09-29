import java.util.*;

class Test {
    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        new Solution().solution(msg);
    }
}

class Solution {
    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < alphabet.length; i++) {
            map.put(alphabet[i], i + 1);
        }

        int idx = 0;
        int num = 27;
        StringBuilder w = new StringBuilder();
        boolean isFinish = false;

        while (true) {
            if (isFinish) break; // 다음 글자가 없는 경우

            w.append(msg.charAt(idx)); // 입력 글자 추가

            if (idx == msg.length() - 1) { // 마지막 인덱스면 해당 글자 색인 번호를 추출하고 종료
                answer.add(map.get(w.toString()));
                break;
            }

            while (true) {
                if (map.containsKey(w.toString())) { // 사전에 있는 경우
                    if (idx + 1 == msg.length()) { // 마지막 글자인 경우 색인 번호를 추출하고 종료
                        isFinish = true;
                        answer.add(map.get(w.toString()));
                        break;
                    }
                    w.append(msg.charAt(++idx));
                } else { // 사전에 없는 경우
                    map.put(w.toString(), num++);
                    w.deleteCharAt(w.length() - 1);
                    answer.add(map.get(w.toString()));
                    w.setLength(0);
                    break;
                }
            }

        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}