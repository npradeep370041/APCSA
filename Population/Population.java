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
		Population population = new Population();
		population.readFile();
		population.printIntroduction();
		System.out.println(population.cities.size() + " cities in database\n");
		boolean finished = false;
		while(!finished) {
			population.printMenu();
			long startMillisec = 0;
			long endMillisec = 0;
			int input = Prompt.getInt("Enter selection");
			System.out.println();
			if(input == 1) {
				startMillisec = System.currentTimeMillis();
				population.sortAscendingPopulation(population.cities);
				System.out.println("Fifty least populous cities");
				population.printData(population.cities, 50);
				endMillisec = System.currentTimeMillis();
				System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
			}
			else if(input == 2) {
				startMillisec = System.currentTimeMillis();
				population.sortDescendingPopulation(population.cities);
				System.out.println("Fifty most populous cities");
				population.printData(population.cities, 50);
				endMillisec = System.currentTimeMillis();
				System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
			}
			else if(input == 3) {
				startMillisec = System.currentTimeMillis();
				population.sortAcendingName(population.cities);
				System.out.println("Fifty cities sorted by name");
				population.printData(population.cities, 50);
				endMillisec = System.currentTimeMillis();
				System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
			}
			else if(input == 4) {
				startMillisec = System.currentTimeMillis();
				population.sortDescendingName(population.cities);
				System.out.println("Fifty cities sorted by name descending");
				population.printData(population.cities, 50);
				endMillisec = System.currentTimeMillis();
				System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
			}
			else if(input == 5) {
				boolean answered = false;
				while(!answered) {
					String state = Prompt.getString("Enter state name (ie. Alabama)");
					startMillisec = System.currentTimeMillis();
					List<City> states = new ArrayList<City>();
					for(int i = 0; i < population.cities.size(); i++) {
						if(state.contains(population.cities.get(i).getState())) {
							states.add(population.cities.get(i));
						}
					}
					if(states.size() > 0) {
						population.sortDescendingPopulation(states);
						System.out.println("\nFifty most populous cities in " + state + "\n");
						if(states.size() < 50) {
							population.printData(states, states.size());
						}
						else {
							population.printData(states, 50);
						}
						answered = true;
					}
					else { 
						System.out.println("ERROR: " + state + " is not valid");
					}
				}
				endMillisec = System.currentTimeMillis();
				System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
						
			}
			else if(input == 6) {
				boolean answered = false;
				while(!answered) {
					String city = Prompt.getString("Enter city name");
					startMillisec = System.currentTimeMillis();
					List<City> namedCities = new ArrayList<City>();
					for(int i = 0; i < population.cities.size(); i++) {
						if(city.equals(population.cities.get(i).getName())) {
							namedCities.add(population.cities.get(i));
						}
					}
					if(namedCities.size() > 0) {
						population.sortDescendingPopulation(namedCities);
						System.out.println("\nCity " + city + " by population\n");
						population.printData(namedCities, namedCities.size());
						answered = true;
					}
					else {
						System.out.println("ERROR: " + city + " is not valid");
					}
				}
				endMillisec = System.currentTimeMillis();
				System.out.println("\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
			}
			else if(input == 9) {
				System.out.println("Thanks for using Population!");
				finished = true;
			}
		}
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
	public void printData(List<City> arr, int length) {
		System.out.print("    ");
		System.out.printf("%-22s %-22s %-12s %13s", "State", "City", "Type", "Population");
		System.out.println();
		for(int i = 0; i < length; i++) {
			System.out.printf("%2d", i + 1);
			System.out.print(": ");
			System.out.printf("%-23s", arr.get(i).getState());
			System.out.printf("%-23s", arr.get(i).getName());
			System.out.printf("%-13s", arr.get(i).getDesignation());
			System.out.printf("%13s", arr.get(i).getPopulation());
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
			String value = arr.get(outer).getName();
			int inner = outer - 1;
			while(inner >= 0 && arr.get(inner).getName().compareTo(value) > 0) {
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
