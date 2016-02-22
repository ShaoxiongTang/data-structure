package com.mls.algorithm;

public class QuickSort {

	public static int _quick_sort(int[] arr, int low, int high) {
		int p = arr[low]; // 第一个元素作为支点
		int i = low;
		int pos = low;
		for (int j = high; j >= i; j--) {
			if (arr[j] < p) {
				arr[pos] = arr[j];
				pos = j;
			} else {
				continue;
			}
			for (; i <= j; i++) {
				if (arr[i] > p) {
					arr[pos] = arr[i];
					pos = i;
					break;
				}
			}
		}
		arr[pos] = p;
		return pos;
	}

	public static void sort(int[] arr, int low, int high) {
		if (low < high) {
			int p = _quick_sort(arr, low, high);
			sort(arr, low, p - 1);
			sort(arr, p + 1, high);
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 5, 7, 2, 4, 1, 10 };
		sort(arr, 0, arr.length - 1);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
