import java.util.Scanner;
import java.io.PrintWriter;

/** 
 *	Creating a US Map with StdDraw
 *
 *	@author Naithik Pradeep
 *	@since September 5, 2023
 */
public class USMap {
	public static void drawMap() {
		Scanner inFile = FileUtils.openToRead("cities.txt");
		Scanner inFile2 = FileUtils.openToRead("bigCities.txt");
		Scanner inFile3 = FileUtils.openToRead("bigCities.txt");
		
		// X Coordinate
		double[] y = new double[1112];
		
		// Y Coordinate
		double[] x = new double[1112];
		
		// All Cities
		String[] cities = new String[1112];
		
		// Big Cities Information
		String[] bigCitiesInfo = new String[275];
		
		// Population Information
		int[] populationInfo = new int[275];
		
		// Reading files
		
			// Cities
			for(int a = 0; a < 1112; a++) {
				y[a] = inFile.nextDouble();
				x[a] = inFile.nextDouble();
				cities[a] = inFile.nextLine();
			}
		
			// Big Cities
			for(int b = 0; b < 275; b++) {
				inFile2.next();
				bigCitiesInfo[b] = inFile2.nextLine();
			}
		
			// Population
			for(int c = 0; c < 275; c++) {
				inFile3.next();
				while(!inFile3.hasNextInt()) {
					inFile3.next();
				}
				populationInfo[c] = inFile3.nextInt();
				inFile3.nextLine();
			}
		
		
		// Verifying Big Cities
		String[] bigCheck = new String[275];
		for(int m = 0; m < 275; m++) {
			for(int n = 0; n < 1112; n++) {
				if(bigCitiesInfo[m].contains(cities[n])) {
					boolean noRepeats = true;
					for(int o = 0; o < n; o++) {
						if(cities[n].equals(cities[o])) {
							noRepeats = false;
							break;
						} 
					}
					if(noRepeats) {
						bigCheck[m] = cities[n];
					}
				}
			}
		}
		
		// Full List of Big Cities and Population
		String[] bigCities = new String[120];
		int[] population = new int[120];
		int p = 0;
		for(int q = 0; q < 275; q++) {
			if(bigCheck[q] == null) {	
				q++;
			}
			else {
				bigCities[p] = bigCheck[q];
				population[p] = populationInfo[q];
				p++;
			}
		}
		
		// Mapping Big Cities to Coordinates
		for(int r = 0; r < 120; r++) {
			for(int s = 0; s < 1112; s++) {
				if(bigCities[r].equals(cities[s]) && r < 10) {
					StdDraw.setPenRadius(0.6 * (Math.sqrt(population[r])/18500));
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.point(x[s], y[s]);
				}
				else if(bigCities[r].equals(cities[s])) {
					StdDraw.setPenRadius(0.6 * (Math.sqrt(population[r])/18500));
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.point(x[s], y[s]);
				}
				else {
					StdDraw.setPenRadius(0.006);
					StdDraw.setPenColor(StdDraw.DARK_GRAY);
					StdDraw.point(x[s], y[s]);
				}
			}
		}
		System.out.println("done");
	}
		
	public static void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}
	public static void main(String[] args) {
		USMap.setupCanvas();
		USMap.drawMap();
	}
}



