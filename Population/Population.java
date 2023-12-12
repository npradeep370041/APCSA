import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Naithik Pradeep	
 *	@since	December 5, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	
	public void readFile() {
		Scanner fileReader = FileUtils.openToRead(DATA_FILE).useDelimiter("[\t\n]");
		while(fileReader.hasNext()) {
			cities.add(new City(fileReader.next(), fileReader.next(), fileReader.next(), Integer.parseInt(fileReader.next())));
		}
	}
	
	public Population() {
		cities = new ArrayList<City>();
	}
	
	public static void main(String[] args) {
		long startMillisec = System.currentTimeMillis();
		Population population = new Population();
		population.readFile();
		population.sortAscendingPopulation(population.cities);
		population.printData(population.cities);
		population.sortDescendingPopulation(population.cities);
		population.printData(population.cities);
		population.sortAcendingName(population.cities);
		population.printData(population.cities);
		population.sortDescendingName(population.cities);
		population.printData(population.cities);
		long endMillisec = System.currentTimeMillis();
		System.out.println(endMillisec - startMillisec);
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printData(List<City> arr) {
		for(int i = 0; i < 50; i++) {
			System.out.printf("%-23s", arr.get(i).getState());
			System.out.printf("%-23s", arr.get(i).getName());
			System.out.printf("%-23s", arr.get(i).getDesignation());
			System.out.printf("%-23s", arr.get(i).getPopulation());
			System.out.println();
		}
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y) {
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	public void sortAscendingPopulation(List<City> arr) {
		for(int outer = arr.size() - 1; outer > 0; outer--) {
			int maxIndex = 0;
			for(int inner = 0; inner <= outer; inner++) {
				if(arr.get(inner).getPopulation() > arr.get(maxIndex).getPopulation()) {
					maxIndex = inner;
				}
			}
			swap(arr, maxIndex, outer);
		}
	}
	
	public void sortDescendingPopulation(List<City> arr) {
		if(arr.size() > 1) {
			int half = arr.size() / 2;
			List<City> firstArr = new ArrayList<City>();
			List<City> secondArr = new ArrayList<City>();
			for(int i = 0; i < half; i++) {
				firstArr.add(i, arr.get(i));
			}
			for(int j = half; j < arr.size(); j++) {
				secondArr.add(j - half, arr.get(j));
			}
			sortDescendingPopulation(firstArr);
			sortDescendingPopulation(secondArr);
			mergeDescendingPopulation(firstArr, secondArr, arr);
		}
	}
	
	public void mergeDescendingPopulation(List<City> first, List<City> second, List<City> arr) {
		int i = 0; 
		int j = 0;
		int k = 0;
		while(i < first.size() && j < second.size()) {
			if(first.get(i).getPopulation() >= second.get(j).getPopulation()) {
				arr.set(k, first.get(i));
				i++;
				k++;
			}
			else {
				arr.set(k, second.get(j));
				j++;
				k++;
			}
		}
		while(i < first.size()) {
			arr.set(k, first.get(i));
			i++;
			k++;
		}
		while(j < second.size()) {
			arr.set(k, second.get(j));
			j++;
			k++;
		}
	}
	
	public void sortAcendingName(List<City> arr) {
		for(int outer = 1; outer < arr.size(); outer++) {
			String[] value = {arr.get(outer).getName()};
			int inner = outer - 1;
			while(inner >= 0 && arr.get(inner).getName().compareTo(value[0]) > 0) {
				swap(arr, inner, inner + 1);
				inner--;
			}
		} 
	}
	
	public void sortDescendingName(List<City> arr) {
		if(arr.size() > 1) {
			int half = arr.size() / 2;
			List<City> firstArr = new ArrayList<City>();
			List<City> secondArr = new ArrayList<City>();
			for(int i = 0; i < half; i++) {
				firstArr.add(i, arr.get(i));
			}
			for(int j = half; j < arr.size(); j++) {
				secondArr.add(j - half, arr.get(j));
			}
			sortDescendingName(firstArr);
			sortDescendingName(secondArr);
			mergeDescendingName(firstArr, secondArr, arr);
		}
	}
	
	public void mergeDescendingName(List<City> first, List<City> second, List<City> arr) {
		int i = 0; 
		int j = 0;
		int k = 0;
		while(i < first.size() && j < second.size()) {
			if(first.get(i).getName().compareTo(second.get(j).getName()) > 0) {
				arr.set(k, first.get(i));
				i++;
				k++;
			}
			else {
				arr.set(k, second.get(j));
				j++;
				k++;
			}
		}
		while(i < first.size()) {
			arr.set(k, first.get(i));
			i++;
			k++;
		}
		while(j < second.size()) {
			arr.set(k, second.get(j));
			j++;
			k++;
		}
	}
}
