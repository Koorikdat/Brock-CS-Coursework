package Assign1;


import BasicIO.*;                // for IO classes
import static BasicIO.Formats.*; // for field formats
import static java.lang.Math.*;  // for math constants and functions


/** This class ...
  *
  * @author Maisam Anjum
  * @version 1.0 1/291021                                                        */

public class ParityMatrix {
// instance variables
  
  int [][] matrix; //intitializes an array called parity with all int 0 values
  private ASCIIDisplayer display; //declares the displayer
  
  
  
  /** This constructor ...                                                     */
  
  public ParityMatrix ( ) {
    
    // local variables
    matrix = new int [9][9]; //initializes parity as a 9X9 2d set of ints
    display = new ASCIIDisplayer(); // initializes the displayer 
    displayMatrix(matrix); //displays the matrix as a 8X8, finds the row parity, finds the column parity, finds the row and column parity(Green bit)
    column_Parity(matrix);                                //calculates column parity and displays it at (8,j)
    green_Parity(matrix);                                 //calculates the row and column parity at (8,8)
    display.newLine(); 
    display.newLine();
    flipParity(matrix); //the values get flipped
    
    // statements
    
  }; // constructor
  
  
  // methods
  private void displayMatrix(int [][] matrix) {
    int x = 0;

    for(int i=0; i<matrix.length-1; i++)        // this double loop is used a few times to parse through the array
    {
      for(int j=0; j<matrix[i].length-1; j++)
      {
        int r = (int)(Math.random() *100 + 0); //generates numbers from 0(inclusive) to 100(exclusive)
        if(r<40) 
        {
          x =1;
        }                //randomly generates a value between 0-100 and uses 40 as a cutoff for 40:60 
        else if(r<=99)   //probability of the bits being 1 or 0
        {
          x = 0;                                         
        } 
                                                            
        matrix[i][j]=x; 
        
        printMatrix(matrix,i,j);                      //displays 1 value: 0 or 1
      } 
      row_Parity(matrix,i);                            //calculates row parity and displays it at (i,8)
      display.newLine();
    }
  }
  
  private void printMatrix(int [][] matrix, int i, int j) 
  {
    display.writeInt(matrix[i][j]);
  }
  
  private void row_Parity(int [][] matrix, int i)  {
    int num = 0;

    for(int j=0; j<matrix[i].length;j++)
    {
      if(matrix[i][j]==1)
      {
        num++;      
      }
      if(j==matrix[i].length-1 && num%2==1)
      {
        matrix[i][j]=1;
        printMatrix(matrix,i,matrix[i].length-1);
      }
      else if(j==matrix[i].length-1 && num%2==0)
      {
        matrix[i][j]=0;
        printMatrix(matrix,i,j);
      }
    }
    num=0;
  }
  
  private void column_Parity(int [][] matrix)  {  // uses method similar to sudoku lab to move through values
    int num=0;                                    // and search for conditions
    for(int j=0; j<matrix.length-1;j++)
    {
      for(int i=0; i<matrix[j].length;i++)
      {
        if(matrix[i][j]==1)
        {
          num++;      
        }
        if(i==matrix[j].length-1 && num%2==1)
        {
          matrix[i][j]=1; 
          printMatrix(matrix,i,j);
        }
        else if(i==matrix[j].length-1 && num%2==0)
        {
          matrix[i][j]=0;
          printMatrix(matrix,i,j);
        }
      }
      num=0;
    }
  }
  
  private void green_Parity(int [][] matrix)  {
    int num=0;
    
    for(int i=0; i<matrix.length-1;i++)
    {
      if(matrix[i][matrix.length-1]==1)
      {
        num++;
      }
    }
    
    for(int i=0; i<matrix.length;i++)
    {
      if(matrix[matrix.length-1][i]==1)
      {
        num++;      
      }
    }
    
     if(num%2==1)
    {
      matrix[matrix.length -1][matrix.length -1]=1; 
      printMatrix(matrix,matrix.length -1,matrix.length -1);
    }
    if(num%2==0)
    {
      matrix[matrix.length -1][matrix.length -1]=0; 
      printMatrix(matrix,matrix.length -1,matrix.length -1);
    }
    
  }
  
  private void flipParity(int [][] matrix)  {

    for(int i=0;i<matrix.length; i++)
    {
      for(int j=0;j<matrix[i].length;  j++)
      {
        int x=(int)(Math.random() *1000);        //generates numbers 0 to 999
        
        if(x<12)                                                  // 1.2% (12/1000) chance of flipping
        {                                                         // else, 98.8% (988/1000) chance that it won't flip 
          System.out.println("the bit at row "+( i+1)+" and column "+( j+1)+" has been flipped.");
          if(matrix[i][j]==1)
          {
            {matrix[i][j]=0;}
          }
          if(matrix[i][j]==0)
          {
            {matrix[i][j]=1;}
          }
        }
        printMatrix(matrix,i,j);
      }
      display.newLine();
    }
  }
  

  
  // For main classes only:
  public static void main ( String[] args ) { ParityMatrix c = new ParityMatrix(); };
  
  
} // ParityMatrix
