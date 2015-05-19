package com.clc.algorithm.sort;

/**
 * 
9, 8, 7, 6, 5, 4, 3, 2, 1, 
====After sort================
1, 2, 3, 4, 5, 6, 7, 8, 9, 
 *
 */
public class MergeSort {

	private static void merge(int[] src, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1];
		int[] R = new int[n2];
		for (int i = 0; i < n1; i++) {
			L[i] = src[p + i];
		}
		for (int j = 0; j < n2; j++) {
			R[j] = src[q + j + 1];
		}
		int i = 0;
		int j = 0;
		for(int k = p; k <= r; k++) {
			if(j == n2 || L[i] <= R[j]) {
				src[k] = L[i];
				i++;
			} else {
				src[k] = R[j];
				j++;
			}
		}
	}
	
	public static void sort(int[] src, int p, int r) {
		if(p < r) {
//			System.out.println("sort p:" + p + " r:" + r);
			int q = (p + r) /2;
			sort(src, p, q);
			sort(src, q + 1, r);
			merge(src, p, q, r);
		}
	}

	public static void main(String[] args) {
		int[] source = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
		for(int key : source) {
			System.out.print(key + ", ");
		}
		System.out.println();
		System.out.println("====After sort================");
		sort(source, 0, source.length - 1);
		for(int key : source) {
			System.out.print(key + ", ");
		}
	}

}
