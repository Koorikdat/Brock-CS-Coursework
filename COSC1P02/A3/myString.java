package Assign3;


import java.io.*;
import BasicIO.*;                // for IO classes
import java.io.PrintStream;  //to write what is in the console to a new .txt file
import static BasicIO.Formats.*; // for field formats
import static java.lang.Math.*;  // for math constants and functions




// this supplies the logic behind the interface and allows it to properly function. 
// does the actual computation such that it can be called upon elsewhere

public class myString implements StringT {
  

  public char[] Characters
 = {'H', 'E', 'L', 'L', 'O'};
 
  public char[] DataPointer;
  
  StringT word;
  
  //constructor 1
  public myString()
  { 
    Characters
 = new char[0];
  } 
  
  //constructor 2
  public myString(char [] Characters
)
  { 
    DataPointer = Characters
;
    //System.out.println(num);
  } 
  
  // these next few methods will allow us to be able to move through the new StringT type
  public int Length ()
  {
    return DataPointer.length;
  }
  
  public char CharAt(int i)
  {
    return DataPointer[i];
  }
  
  public char[] ToArray()
  {
    return DataPointer; 
  }
  
  public StringT ConCat(StringT S)
  {
    // will iterate through the values from the first, then the second data input
    char [] a = this.Characters
; 
    char [] b = S.ToArray();
    
    char [] c = new char[a.length + b.length];
    for(int i=0; i<a.length + b.length; i++)
    {
      if(i<a.length)
        
      {
        c[i] = a[i]; 
        System.out.print(c[i]);     }
      
      else
      {
     c[i] = b[i - a.length];
        System.out.print(c[i]);    }
    }
    
    word = new myString(c);
    return word;
  }
  
  public StringT Before(int i)
  {
    //char [] a = this.Characters
;
    for(int j=0; j<i; j++)
    {
      System.out.print(DataPointer[j]);
    }
    StringT newWord = new myString(DataPointer);
    return newWord;
  }
  
  public StringT After(int i)
  {
    for(int j=i; j<DataPointer.length; j++)
    {
      System.out.print(DataPointer[j]);
    }
    StringT newWord = new myString(DataPointer);
    return newWord;
  }
  
  public StringT Clone()
  {
    char [] a = DataPointer;
    System.out.print(a);
    StringT newWord = new myString(a);
    return newWord;
  }
  
  
  public StringT Reverse()
    // Will iterate through each of the values and decrement from .length rather than 
    // incrementing from 0 -to get the values in reverse order and place into new array
  {
    char [] a = DataPointer;
    for(int i=a.length-1; i>=0; i--)
    {
      System.out.print(a[i]); 
    }
    StringT reverse = new myString(a);
    return reverse;
  }
  
  
  public char[] LowerCase()
    // Will iterate through the data points and use the ASCII properties to scale up each Letter
    // Will result in strange output with non-letter inputs
  {
    char [] a = DataPointer;
    char [] x = new char [a.length];
    for(int i=0; i<a.length; i++)
    {
      int b = (int) a[i]; 
      //System.out.print(b);
      int c = b+32;
      x[i] = (char) c;
      System.out.print(x[i]);
    }
    return x;
  }
  
  public void crash()
  {
    throw new myStringException();
  }
  
  
}