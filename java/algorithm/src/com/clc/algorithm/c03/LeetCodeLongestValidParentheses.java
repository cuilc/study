/**
 * 
 */
package com.clc.algorithm.c03;

import java.util.Hashtable;
import java.util.Map;
import java.util.Stack;

/**
 * 3 （本题可选）关于栈的习题（LeetCodeLongest Valid Parentheses）
【原题】Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
For "(()", the longest valid parentheses substring is "()", which has length = 2.
Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4
【意译】给定一个只包含字符'(' 和 ')'的字符串，找出最长的合法括号字串
 * 
 *
 */
public class LeetCodeLongestValidParentheses {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] inputs = {")()())()()()()","(()"};
		for(String item : inputs){
			System.out.println(item + " : " + LongestValidParentheses(item));
		}
	}
	
	private static Map<Character, Character> map = new Hashtable<Character, Character>();
	static {
		map.put('(', ')');
	}
	public static int LongestValidParentheses(String str) {
		Stack<Character> s = new Stack<Character>();
		int max = 0;//最大匹配长度
		int currentLen = 0;//当前匹配的括号长度
		boolean isLastMatch = false;//记录上次是否是匹配的括号
		boolean isFirstPushAfterMatch = false;
		for(char c : str.toCharArray()) {
			if(!s.isEmpty() && isMatch(c, s.peek())) {
				s.pop();
				if(isLastMatch) {
					currentLen += 2;
				} else {
					currentLen = 2;
				}
				max = Math.max(max, currentLen);
				isLastMatch = true;
				isFirstPushAfterMatch = true;
			} else {
				s.push(c);
				if(isFirstPushAfterMatch) {
					isFirstPushAfterMatch = false;
				} else {
					isLastMatch = false;
				}
			}
		}
		return max;
	}

	private static boolean isMatch(Character c, Character peek) {
		return map.get(peek)==null ? false : map.get(peek).equals(c);
	}

}
