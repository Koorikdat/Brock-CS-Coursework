import BasicIO.*;                // for IO classes
import java.io.PrintStream;  //to write what is in the console to a new .txt file


/** This assignment ...
 *
 * @author Maisam Anjum
 *
 * @version 1.0 2/22/2021                                                      */

public class Encryptor {
    // instance variables
    int Values;
    Node [] codeBook; //declares the 1-D array of Node type
    char [] charsArray;  //declares the 1-D array of Char type
    int [] IntsArray;  //declares the 1-D array of Int type



    private ASCIIDataFile in;//to read from a .txt file
    private ASCIIDataFile input;//to read from a .txt file
    private ASCIIOutputFile CodeBook;//to write to a new .txt file
    private ASCIIOutputFile Encrypted;
    private ASCIIOutputFile Decrypted;



    public class Node { // Node class from tutorial is used here
        public int item;
        public Node next;
        public Node (int numberPassed, Node nextNodePassed) {
            item = numberPassed;
            next = nextNodePassed;
        }
    }

    /** This constructor ...                                                     */

    public Encryptor( ) {
        in = new ASCIIDataFile();  //select .txt file to read from
        CodeBook = new ASCIIOutputFile();  //create a new .txt file to write to

       BuildStructure(); // will build the initial set of arrays with linked lists
       OperateProgram();       // will run encryption and decryption on the txt files


    }; // constructor


    //methods

    private static int random(int max, int min) // This function produces a random int within parameters for upper and lower bounds
    {
        int randomint = (int)(Math.random() * (max - min + 1) + min);
        return randomint;
    }


    private void add(Node list, int aCode)   // This function is used to add nodes
    {
        Node p = list;
        while(p.next != null)
        {
            p = p.next;
        }
        p.next = new Node (aCode, null);
        list.item++;
        System.out.print("  " + p.next.item);
    }

    private void BuildStructure () { // creates the Array of nodes

        int counter = 0;
        codeBook = new Node [128];
        charsArray = new char [3282];
        IntsArray = new int [3282];

        for(int i=0; i<codeBook.length; i++)
        {
            codeBook[i] = new Node (counter, null);

        }

        for(int j=1; j<=2000; j++)
        {
            int r = (int)(random(127, 0));
            System.out.print(r);
            add(codeBook[r], j);
            System.out.println("  total: " + codeBook[r].item);

        }
    }


    private void encrypt(Node list, PrintStream output) // should be moving through the nodes and outputting the encrypted data
    {

        int r = random((int)(list.item), 1);
        Node p = list;
        for(int i=0; i<r; i++)
        {
            p = p.next;
        }
        Encrypted.writeInt(p.item);

    }


    private void decrypt(Node list, int number, int index, PrintStream output) // should reverse the operations and decrypt the data
    {
        Node p = list;
        //System.out.println(p.item);
        while(p.next != null)
        {
            //System.out.print(number);
            if(p.next.item == number)
            {
                Decrypted.writeInt(index);
                break;
            }
            else
            {
                p = p.next;
            }
        }
    }


    private void OperateProgram(){ // will move through the cases of encryption and decryption
        try
        {

            for(int i=0; i<codeBook.length; i++) {
                System.out.print(i);

                CodeBook.writeInt(i);

                Node p = codeBook[i];
                while (p.next != null) {
                    p = p.next;
                    Values = p.item;


                    System.out.print("  " + Values);
                    CodeBook.writeInt(Values);
                }

                System.out.println();
                CodeBook.newLine();

            }
        }
        catch(Exception e)
        {
            e.getStackTrace();
        }

        try
        {
            PrintStream output = new PrintStream("Encrypted.txt");

            for(int i = 0; i< charsArray.length; i++)
            {
                charsArray[i] = in.readC();
                int x2 = (int) charsArray[i];
                encrypt(codeBook[x2], output);
            }
            output.close();
        }
        catch(Exception e)
        {
            e.getStackTrace();
        }

        try
        {
            PrintStream output = new PrintStream("Decrypted.txt");
            input = new ASCIIDataFile();  //select .txt file to read from

            for(int i = 0; i< IntsArray.length; i++)
            {
                IntsArray[i] = input.readInt();
                System.out.println(IntsArray[i]);
                for(int j=0; j<codeBook.length; j++)
                {
                    decrypt(codeBook[j], IntsArray[i], j, output);
                }
            }
            output.close();
        }
        catch(Exception e)
        {
            e.getStackTrace();
        }
    }






    public static void main ( String[] args )
    {
        Encryptor s = new Encryptor();
    };


} // <className>