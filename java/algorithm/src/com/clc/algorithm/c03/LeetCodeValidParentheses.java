/**
 * 
 */
package com.clc.algorithm.c03;

import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;

/**
 * 2 关于栈的习题（[LeetCode] Valid Parentheses）：
 *  关于栈的习题（[LeetCode] Valid Parentheses）：
【原题】Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not
【意译】给一个只包含'(', ')', '{', '}', '[' 和 ']'的字符串，判断这个字符串是否合法（括号是否匹配）
 * 
 *
 */
public class LeetCodeValidParentheses {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] inputs = {"()[]{}","(]"};
		for(String item : inputs){
			System.out.println(item + " : " + isValid(item));
		}
	}
	
	private static Map<Character, Character> map = new Hashtable<Character, Character>();
	static {
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');
	}
	public static boolean isValid(String str) {
		Stack<Character> s = new Stack<Character>();
		for(char c : str.toCharArray()) {
			if(!s.isEmpty() && isMatch(c, s.peek())) {
				s.pop();
			} else {
				s.push(c);
			}
		}
		return s.isEmpty();
	}

	private static boolean isMatch(Character c, Character peek) {
		return map.get(peek)==null ? false : map.get(peek).equals(c);
	}

}
