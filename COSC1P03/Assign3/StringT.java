package Assign3;

import java.io.*;
import BasicIO.*;                // for IO classes
import java.io.PrintStream;  //to write what is in the console to a new .txt file
import static BasicIO.Formats.*; // for field formats
import static java.lang.Math.*;  // for math constants and functions

//This interface program is responsible for all the operations that occur in the program,
// and allows us to work with the StringT type


public interface StringT {

//Concatenates 2 strings, this.ConCat(S)
public StringT ConCat(StringT S);

//Returns a string from index 0 to but not including i.
public StringT Before(int i);

////Returns a string from i to end.
public StringT After(int i);

//Returns the length of this.
public int Length();

//Creates a deep clone of this.
public StringT Clone();

//Returns the character at index i of this;
public char CharAt(int i);

//Returns a character array representation of this.
public char[] ToArray();


public StringT Reverse();


public char[] LowerCase();

public void crash();
}