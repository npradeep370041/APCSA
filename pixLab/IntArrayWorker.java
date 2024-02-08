public class IntArrayWorker
{
  /** two dimensional matrix */
  private int[][] matrix = null;
  
  /** set the matrix to the passed one
    * @param theMatrix the one to use
    */
  public void setMatrix(int[][] theMatrix)
  {
    matrix = theMatrix;
  }
  
  /**
   * Method to return the total 
   * @return the total of the values in the array
   */
  public int getTotal()
  {
    int total = 0;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        total = total + matrix[row][col];
      }
    }
    return total;
  }
  
  /**
   * Method to return the total using a nested for-each loop
   * @return the total of the values in the array
   */
  public int getTotalNested()
  {
    int total = 0;
    for (int[] rowArray : matrix)
    {
      for (int item : rowArray)
      {
        total = total + item;
      }
    }
    return total;
  }
  
  /**
   * Method to fill with an increasing count
   */
  public void fillCount()
  {
    int numCols = matrix[0].length;
    int count = 1;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < numCols; col++)
      {
        matrix[row][col] = count;
        count++;
      }
    }
  }
  
  /**
   * print the values in the array in rows and columns
   */
  public void print()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        System.out.print( matrix[row][col] + " " );
      }
      System.out.println();
    }
    System.out.println();
  }
  
  
  /** 
   * fill the array with a pattern
   */
  public void fillPattern1()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; 
           col++)
      {
        if (row < col)
          matrix[row][col] = 1;
        else if (row == col)
          matrix[row][col] = 2;
        else
          matrix[row][col] = 3;
      }
    }
  }
  
  /**
   * fill the array with a pattern
   */
  public void fillPattern2() {
	int value = 1;
	for(int row = 0; row < matrix.length; row++) {
		for(int col = 0; col < matrix[0].length; col++) {
			matrix[row][col] = value;
			value++;
		}
	}
  }
  
  /**
   * get the count of a number in the matrix
   * @param  num  The number to find
   * @return count  The total number of times num is found
   */
  public int getCount(int num) {
	int count = 0;
	for(int row = 0; row < matrix.length; row++) {
		for(int col = 0; col < matrix[0].length; col++) {
			if(matrix[row][col] == num) {
				count++;
			}
		}
	}
	return count;
  }
  
  /**
   * get the largest number in the matrix
   * @return  largest  The largest value in the matrix
   */
  public int getLargest() {
	int largest = 0; 
	for(int row = 0; row < matrix.length; row++) {
		for(int col = 0; col < matrix[0].length; col++) {
			if(matrix[row][col] > largest) {
				largest = matrix[row][col];
			}
		}
	}
	return largest;
  }
  
  /**
   * get the total sum in a column
   * @param  col  The column to sum
   * @return sum  The total sum
   */
  public int getColTotal(int col) {
	  int sum = 0;
	for(int row = 0; row < matrix.length; row++) {
		sum += matrix[row][col];
	}
	return sum;
  }
  
  /**
   * reverse a row
   */
  public void reverseRows() {
	  for(int row = 0; row < matrix.length; row++) {
		  int start = 0; 
		  int end = matrix[row].length - 1;
		  while(start < end) {
			  int temp = matrix[row][start];
			  matrix[row][start] = matrix[row][end];
			  matrix[row][end] = temp;
			  start++;
			  end--;
		  }
	  }
  }
}
