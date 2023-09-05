import java.util.Scanner;
import java.io.PrintWriter;
public class USMap {
	public static void drawMap() {
		StdDraw.setPenColor(StdDraw.DARK_GRAY);
		StdDraw.setPenRadius(0.006);
		Scanner inFile = FileUtils.openToRead("cities.txt");
		while(inFile.hasNextDouble()) {
			double x = inFile.nextDouble();
			double y = inFile.nextDouble();
			StdDraw.point(x,y);
		}
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
