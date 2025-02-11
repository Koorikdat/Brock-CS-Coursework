package Assign3;

import java.io.*;
import BasicIO.*;                // for IO classes
import java.io.PrintStream;  //to write what is in the console to a new .txt file
import static BasicIO.Formats.*; // for field formats
import static java.lang.Math.*;  // for math constants and functions

// This class is responsible for testing the various functions that we have

public class TestHarness {
  public char[] S = {'W', 'O', 'R', 'L', 'D'};
  public char[] letters = {'H', 'E', 'L', 'L', 'O'};
  
  public TestHarness() {
 
 // Demostrate all of the various tests
    
    StringT s = new myString(letters);
    StringT s1 = new myString(S);
    s.Length();
    System.out.println(s.Length()); 
    
    s.CharAt(0);
    System.out.println(s.CharAt(1));
    
    s.ToArray();
    System.out.println(s.ToArray());
    
    
    StringT s2 = s.ConCat(s1);
    System.out.println();
    
    StringT s3 = s2.Before(7);
    System.out.println();
    
    StringT s4 = s2.After(3);
    System.out.println();
    
    StringT s5 = s.Clone();
    System.out.println();
    //Clones the Data
    
    s2.LowerCase();
    System.out.println();
    //translates the data into lowercase form
    
    StringT s6 = s2.Reverse();
    System.out.println();
    // will print the data in reverse order
   
    
    try{
     s.crash();
     // should create and error and try to print apple
     
    }catch(myStringException e) {
      char[] array = new char[] {
        'A', 'p', 'p', 'l', 'e'
      };
      StringT a = new myString(array);
      System.out.println(a.ToArray());
    }
  }
  
  public static void main(String[] args) {
    TestHarness t = new TestHarness(); 
  }
}