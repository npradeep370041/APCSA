import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  public void keepOnlyBlue() {
	Pixel[][] pixels = this.getPixels2D();
	for(Pixel[] rowArray : pixels) {
		for(Pixel pixelObj : rowArray) {
		  pixelObj.setRed(0);
		  pixelObj.setGreen(0);
		}
	}
  }
  
  public void negate() {
	Pixel[][] pixels = this.getPixels2D();
	for(Pixel[] rowArray : pixels) {
		for(Pixel pixelObj : rowArray) {
			pixelObj.setRed(255 - pixelObj.getRed());
			pixelObj.setGreen(255 - pixelObj.getGreen());
			pixelObj.setBlue(255 - pixelObj.getBlue());
		}
	}
  }
  
  public void grayscale() {
	Pixel[][] pixels = this.getPixels2D();
	for(Pixel[] rowArray : pixels) {
		for(Pixel pixelObj : rowArray) {
			int avg = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue())/3;
			pixelObj.setRed(avg);
			pixelObj.setGreen(avg);
			pixelObj.setBlue(avg);
		}
	}
  }
  
  public void fixUnderwater() {
	Pixel[][] pixels = this.getPixels2D();
	for(Pixel[] rowArray : pixels) {
		for(Pixel pixelObj : rowArray) {
			if(pixelObj.getBlue() > pixelObj.getGreen()) {
				pixelObj.setRed(pixelObj.getRed() + 35);
				pixelObj.setGreen(pixelObj.getGreen() + 35);
				pixelObj.setBlue(pixelObj.getBlue() + 35);
			}
		}
	}
  }
  
  public void pixelate(int size) {
	  Pixel[][] pixels = this.getPixels2D();
	  for(int i = 0; i < pixels.length; i += size) {
		  for(int j = 0; j < pixels[i].length; j += size) {
			  int redTotal = 0;
			  int redCount = 0;
			  int greenTotal = 0;
			  int greenCount = 0;
			  int blueTotal = 0;
			  int blueCount = 0;
			  if((i + size) <= pixels.length && (j + size) <= pixels[i].length) {
				  for(int k = i; k < i + size; k++) {
					  for(int l = j; l < j + size; l++) {
						  redTotal += pixels[k][l].getRed();
						  redCount++;
						  greenTotal += pixels[k][l].getGreen();
						  greenCount++;
						  blueTotal += pixels[k][l].getBlue();
						  blueCount++;
					  }
				  }
				  int redAvg = redTotal/redCount;
				  int greenAvg = greenTotal/greenCount;
				  int blueAvg = blueTotal/blueCount;
				  for(int m = i; m < i + size; m++) {
					  for(int n = j; n < j + size; n++) {
						  pixels[m][n].setRed(redAvg);
						  pixels[m][n].setGreen(greenAvg);
						  pixels[m][n].setBlue(blueAvg);
					  }
				  }
			  }
			  else if((i + size) >= pixels.length && (j + size) < pixels[i].length) {
				  for(int o = i; o < pixels.length; o++) {
					  for(int p = j; p < (j + size); p++) {
						  redTotal += pixels[o][p].getRed();
						  redCount++;
						  greenTotal += pixels[o][p].getGreen();
						  greenCount++;
						  blueTotal += pixels[o][p].getBlue();
						  blueCount++;
					  }
				  }
				  int redAvg = redTotal/redCount;
				  int greenAvg = greenTotal/greenCount;
				  int blueAvg = blueTotal/blueCount;
				  for(int q = i; q < pixels.length; q++) {
					  for(int r = j; r < (j + size); r++) {
						  pixels[q][r].setRed(redAvg);
						  pixels[q][r].setGreen(greenAvg);
						  pixels[q][r].setBlue(blueAvg);
					  }
				  }
			  }
			  else if((j + size) >= pixels[i].length && (i + size) < pixels.length) {
				  for(int s = i; s < (i + size); s++) {
					  for(int t = j; t < pixels[i].length; t++) {
						  redTotal += pixels[s][t].getRed();
						  redCount++;
						  greenTotal += pixels[s][t].getGreen();
						  greenCount++;
						  blueTotal += pixels[s][t].getBlue();
						  blueCount++;
					  }
				  }
				  int redAvg = redTotal/redCount;
				  int greenAvg = greenTotal/greenCount;
				  int blueAvg = blueTotal/blueCount;
				  for(int u = i; u < (i + size); u++) {
					  for(int v = j; v < pixels[i].length; v++) {
						  pixels[u][v].setRed(redAvg);
						  pixels[u][v].setGreen(greenAvg);
						  pixels[u][v].setBlue(blueAvg);
					  }
				  }
			  }
			  else {
				  for(int w = i; w < pixels.length; w++) {
					  for(int x = j; x < pixels[i].length; x++) {
						  redTotal += pixels[w][x].getRed();
						  redCount++;
						  greenTotal += pixels[w][x].getGreen();
						  greenCount++;
						  blueTotal += pixels[w][x].getBlue();
						  blueCount++;
					  }
				  }
				  int redAvg = redTotal/redCount;
				  int greenAvg = greenTotal/greenCount;
				  int blueAvg = blueTotal/blueCount;
				  for(int y = i; y < pixels.length; y++) {
					  for(int z = j; z < pixels[i].length; z++) {
						  pixels[y][z].setRed(redAvg);
						  pixels[y][z].setGreen(greenAvg);
						  pixels[y][z].setBlue(blueAvg);
					  }
				  }
			  }
		  }
	  }		  
  }
  
  public Picture blur(int size) {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels.length, pixels[0].length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  for(int i = 0; i < pixels.length; i++) {
		  for(int j = 0; j < pixels[i].length; j++) {
			  int redSum = 0;
			  int redCount = 0;
			  int greenSum = 0; 
			  int greenCount = 0;
			  int blueSum = 0;
			  int blueCount = 0;
			  for(int k = i - size/2; k <= i + size/2; k++) {
				  for(int l = j - 1; l <= j + 1; l++) {
					  if(k >= 0 && l >= 0 && k < pixels.length && l < pixels[k].length) {
						  redSum += pixels[k][l].getRed();
						  redCount++;
						  greenSum += pixels[k][l].getGreen();
						  greenCount++;
						  blueSum += pixels[k][l].getBlue();
						  blueCount++;
					  }
				  }
			  }
			  resultPixels[i][j].setRed(redSum/redCount);
			  resultPixels[i][j].setGreen(greenSum/greenCount);
			  resultPixels[i][j].setBlue(blueSum/blueCount);
		  }
	  }
	  return result;
  }
  
  public Picture enhance(int size) {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels.length, pixels[0].length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  for(int i = 0; i < pixels.length; i++) {
		  for(int j = 0; j < pixels[i].length; j++) {
			  int redSum = 0;
			  int redCount = 0;
			  int greenSum = 0; 
			  int greenCount = 0;
			  int blueSum = 0;
			  int blueCount = 0;
			  for(int k = i - size/2; k <= i + size/2; k++) {
				  for(int l = j - 1; l <= j + 1; l++) {
					  if(k >= 0 && l >= 0 && k < pixels.length && l < pixels[k].length) {
						  redSum += pixels[k][l].getRed();
						  redCount++;
						  greenSum += pixels[k][l].getGreen();
						  greenCount++;
						  blueSum += pixels[k][l].getBlue();
						  blueCount++;
					  }
				  }
			  }
			  resultPixels[i][j].setRed(2 * pixels[i][j].getRed() - redSum/redCount);
			  resultPixels[i][j].setGreen(2 * pixels[i][j].getGreen() - greenSum/greenCount);
			  resultPixels[i][j].setBlue(2 * pixels[i][j].getBlue() - blueSum/blueCount);
		  }
	  }
	  return result;
  }
  
  public Picture shiftRight(int percent) {
	  Pixel[][] pixels = this.getPixels2D();
	  Picture result = new Picture(pixels.length, pixels[0].length);
	  Pixel[][] resultPixels = result.getPixels2D();
	  for(int i = 0; i < pixels.length; i++) {
		  for(int j = 0; j < pixels[i].length; j++) {
			  resultPixels[i][(j + ((percent/100) * pixels[i].length)) % pixels[i].length].setRed(pixels[i][j].getRed());
			  resultPixels[i][(j + ((percent/100) * pixels[i].length)) % pixels[i].length].setGreen(pixels[i][j].getGreen());
			  resultPixels[i][(j + ((percent/100) * pixels[i].length)) % pixels[i].length].setBlue(pixels[i][j].getBlue());
		  }
	  }
	  return result;
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.pixelate(39);
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
