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
	public void mergeSort(Integer [] arr) {
		if(arr.length > 1) {
			int half = arr.length / 2;
			Integer[] firstArr = new Integer[half];
			Integer[] secondArr = new Integer[arr.length - half];
			for(int i = 0; i < half; i++) {
				firstArr[i] = arr[i];
			}
			for(int j = half; j < arr.length; j++) {
				secondArr[j - half] = arr[j];
			}
			mergeSort(firstArr);
			mergeSort(secondArr);
			merge(firstArr, secondArr, arr);
		}
		
	}
	
	public void merge(Integer[] first, Integer[] second, Integer[] arr) {
		int i = 0; 
		int j = 0;
		int k = 0;
		while(i < first.length && j < second.length) {
			if(first[i] <= second[j]) {
				arr[k] = first[i];
				i++;
				k++;
			}
			else {
				arr[k] = second[j];
				j++;
				k++;
			}
		}
		while(i < first.length) {
			arr[k] = first[i];
			i++;
			k++;
		}
		while(j < second.length) {
			arr[k] = second[j];
			j++;
			k++;
		}
		
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
		long startMillisec1 = System.currentTimeMillis();
		bubbleSort(arr);
		long endMillisec1 = System.currentTimeMillis();
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println(endMillisec1 - startMillisec1);
		System.out.println();
		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		long startMillisec2 = System.currentTimeMillis();
		selectionSort(arr);
		long endMillisec2 = System.currentTimeMillis();
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println(endMillisec2 - startMillisec2);
		System.out.println();

		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		long startMillisec3 = System.currentTimeMillis();
		insertionSort(arr);
		long endMillisec3 = System.currentTimeMillis();
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println(endMillisec3 - startMillisec3);
		System.out.println();

	
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		long startMillisec4 = System.currentTimeMillis();
		mergeSort(arr);
		long endMillisec4 = System.currentTimeMillis();
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println(endMillisec4 - startMillisec4);
		System.out.println();
	}
}
