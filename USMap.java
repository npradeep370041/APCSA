import java.util.Scanner;
import java.io.PrintWriter;
public class USMap {
	public static void drawMap() {
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.setPenRadius(0.006);
		Scanner inFile = FileUtils.openToRead("cities.txt");
		double[] yCoords = new double[1112];
		double[] xCoords = new double[1112];
		String[] cities = new String[1112];
		while(inFile.hasNextDouble()) {
			for(int i = 0; i < 1112; i++) {
				yCoords[i] = inFile.nextDouble();
				xCoords[i] = inFile.nextDouble();
				StdDraw.point(xCoords[i],yCoords[i]);
				cities[i] = inFile.nextLine();
				inFile.nextLine();
			}
		}
		Scanner inFile2 = FileUtils.openToRead("bigCities.txt");
		String[] bigCities = new String[275];
		while(inFile2.hasNext()) {
			for(int j = 0; j < 275; j++) {
				int useless = inFile2.nextInt();
				bigCities[j] = inFile2.next();
			}
		}
		System.out.println(bigCities[0]);		
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
