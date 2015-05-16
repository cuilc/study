package com.clc.algorithm.sort;

/**
 * 
9, 8, 7, 6, 5, 4, 3, 2, 1, 
====After sort================
1, 2, 3, 4, 5, 6, 7, 8, 9, 
 *
 */
public class InsertionSort {
	
	public static void sort(int[] src) {
		int i = 0;
		int key = 0;
		for(int j = 1; j<src.length; j++) {
			key = src[j];
			i = j -1;
			while(i >= 0 && src[i] > key){
				src[i + 1] = src[i];
				i = i - 1;
			}
			src[i + 1] = key;
		}
	}

	public static void main(String[] args) {
		int[] source = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
		for(int key : source) {
			System.out.print(key + ", ");
		}
		System.out.println();
		System.out.println("====After sort================");
		sort(source);
		for(int key : source) {
			System.out.print(key + ", ");
		}
	}

}
