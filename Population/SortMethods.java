/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Naithik Pradeep
 *	@since	November 30, 2023
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(Integer [] arr) {
		for(int outer = arr.length - 1; outer > 0; outer--) {
			for(int inner = 0; inner < outer; inner++) {
				if(arr[inner].compareTo(arr[inner + 1]) > 0) {
					swap(arr, inner, inner + 1);
				}
			}
		}
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(Integer[] arr, int x, int y) {
		Integer temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(Integer [] arr) {
		for(int outer = arr.length - 1; outer > 0; outer--) {
			int maxIndex = 0;
			for(int inner = 0; inner <= outer; inner++) {
				if(arr[inner] > arr[maxIndex]) {
					maxIndex = inner;
				}
			}
			swap(arr, maxIndex, outer);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(Integer [] arr) {
		for(int outer = 1; outer < arr.length; outer++) {
			int value = arr[outer];
			int inner = outer - 1;
			while(inner >= 0 && arr[inner] > value) {
				swap(arr, inner, inner + 1);
				inner--;
			}
		} 
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public Integer[] mergeSort(Integer [] arr) {
		if(arr.length == 2) {
			if(arr[0] > arr[1]) {
				swap(arr, 0, 1);
			}
			return arr;
		}
		else if(arr.length == 1) {
			return arr;
		}
		else {
			int halved = arr.length/2;
			Integer[] firstArr = new Integer[halved];
			Integer[] secondArr = new Integer[arr.length - halved];
			Integer[] final1 = mergeSort(firstArr);
			Integer[] final2 = mergeSort(secondArr);
			Integer[] merged = merge(final1, final2);
			return merged;
		}
		
	}
	
	public Integer[] merge(Integer [] first, Integer [] second) {
		Integer[] merged = new Integer[first.length + second.length];
		for(int i = 0; i < first.length; i++) {
			merged[i] = first[i];
		}
		for(int j = first.length; j < second.length; j++) {
			merged[j] = second[j - first.length];
		}
		bubbleSort(merged);
		return merged;
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

	
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	}
}
