package tasks.first;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class FirstTaskSolution implements FirstTask {
    @Override
    public String breadthFirst(boolean[][] adjacencyMatrix, int startIndex) {
        ArrayList<Integer> vertexList = new ArrayList<>();
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.offerFirst(startIndex);
        while (!que.isEmpty()) {
            int a = que.pollFirst();
            if (!vertexList.contains(a)) {
                vertexList.add(a);
            }
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                boolean elem = adjacencyMatrix[a][i];
                if (elem && !vertexList.contains(i)) {
                    que.add(i);
                    vertexList.add(i);
                }
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < vertexList.size(); i++) {
            int vertex = vertexList.get(i);
            result.append(vertex);
            if (i != vertexList.size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    @Override
    public Boolean validateBrackets(String s) {
        String openingBracket = "([{";
        String closingBracket = ")]}";

        char[] input = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (Character c : input) {
            if (openingBracket.contains(c.toString())) {
                stack.add(c);
            }
            if (closingBracket.contains(c.toString())) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (closingBracket.indexOf(c) != openingBracket.indexOf(stack.pop())) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    @Override
    public Long polishCalculation(String s) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int temp1;
        int temp2;

        String[] splitString = s.split(" ");

        if (splitString[0].equals(" ")) throw new IllegalArgumentException();

        for (String str : splitString) {
            if (isDigit(str)) {
                stack.addFirst(Integer.parseInt(s));
                continue;
            }
            if (!stack.isEmpty()) {
                temp1 = Integer.parseInt(String.valueOf(stack.poll()));
                temp2 = Integer.parseInt(String.valueOf(stack.poll()));
                if (s.equals("*")) {
                    stack.addFirst(temp1 * temp2);
                }
                if (s.equals("/")) {
                    stack.addFirst(temp1 / temp2);
                }
                if (s.equals("+")) {
                    stack.addFirst(temp1 + temp2);
                }
                if (s.equals("-")) {
                    stack.addFirst(temp1 - temp2);
                }
                else throw new IllegalArgumentException();
            }

            if (stack.size() == 1) {
                return Long.valueOf(stack.getFirst());
            }
        }
        throw new IllegalArgumentException();
    }

    public static boolean isDigit(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;

        }
    }
}

