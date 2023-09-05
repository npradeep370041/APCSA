import java.util.Scanner;
import java.io.PrintWriter;
public class USMap {
	public void inputCities() {
		double[] xCord;
		double[] yCord;
		Scanner inFile = FileUtils.openToRead("cities.txt");
		while(inFile.hasNext()) {
			inFile.nextDouble;
		
	public void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}
	public static void main(String[] args) {
		USMap map = new USMap();
		map.setUpCanvas();
	}
}
