import java.util.Stack;

public class Main{
    public static void main(String[] args) {
        new Solution().solution("()()");
    }
}

class Solution {
    boolean solution(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')'){
                if (stack.isEmpty()) return false;
                else {
                    if (stack.peek() == '(') stack.pop();
                    else return false;
                }
            }else stack.push('(');
        }
        return stack.isEmpty();
    }
}
