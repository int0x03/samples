package com.tianxiaohui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		Main m = new Main();
		//m.generateParenthesis(4);
		m.canBeValid("((()(()()))()((()()))))()((()(()", "10111100100101001110100010001001");
	}
	
	public List<String> generateParenthesis(int n) {
        return new ArrayList<String>(this.genParenthesis(n));
    }
	
	private Set<String> genParenthesis(int n) {
        if (1 == n) {
            return Set.of("()");
        } else {
        	Set<String> prev = genParenthesis(n - 1);
        	return prev.stream().flatMap(s -> {
        		List<String> l = new ArrayList();
        		for (int i = 0; i <= s.length(); i++) {
        			l.add(s.substring(0, i) + "()" + s.substring(i));
        		}
        		return l.stream();
        	}).collect(Collectors.toSet());
        }
    }
	
	public boolean canBeValid(String s, String locked) {
        int count = s.length();
        int chCount = 0;
        Deque<String> notChange = new ArrayDeque();// ( in it
        for (int i = 0; i < count; i++) {
        	if ('0' == locked.charAt(i)) {//changable
        		if (!notChange.isEmpty()) {
        			notChange.removeLast();
        		} else {
        			chCount++;
        		}
        	} else {
        		if ('(' == s.charAt(i)) {
        			notChange.addLast("(");
        		} else {
        			if (!notChange.isEmpty()) {
        				notChange.removeLast();
        			} else if (chCount > 0) {
        				chCount--;
        			} else {
        				return false;
        			}
        		}
        	}
        }
        System.out.print(chCount);
        System.out.print(notChange);
        if (!notChange.isEmpty()) {
        	return false;
        }
        if (1 == chCount % 2) {
        	return false;
        }
        
        return true;
    }

}
