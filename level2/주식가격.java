import java.util.*;

public class Problem {

    public static void main(String[] args) {
        new Solution().solution(new int[]{1, 2, 3, 2, 3, 1 });
    }
}

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Stack<Stock> stack = new Stack<>(); // 주식정보를 담은 스택

        for (int i = 0; i < prices.length; i++) {
            if (stack.isEmpty()) stack.push(new Stock(i, prices[i]));
            else {
                while (true) {
                    if (stack.isEmpty()) {
                        stack.push(new Stock(i,prices[i]));
                        break;
                    }

                    if (stack.peek().price <= prices[i]) { // 가격이 떨어지지 않는 경우
                        stack.push(new Stock(i, prices[i]));
                        break;
                    }
                    else { // 가격이 떨어질 경우
                        Stock stock = stack.pop();
                        answer[stock.index] = i - stock.index; // 현재 인덱스에서 해당 주식의 인덱스를 뺀 값이 n초간 떨어지지 않는 시간이다.
                    }
                }
            }
        }

        while (!stack.isEmpty()){
            Stock stock = stack.pop();
            answer[stock.index] = prices.length-1 - stock.index;
        }

        return answer;
    }

    class Stock {
        int index;
        int price;

        public Stock(int index, int price) {
            this.index = index;
            this.price = price;
        }
    }
}