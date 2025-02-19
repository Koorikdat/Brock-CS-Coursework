import java.util.*;
import java.io.*;

public class Encrypter
{

    String input = "";


        int key;
        char data;

        
        public Node head;
        public Node p;
        public Node parent;
        public Node leftChild;
        public Node rightChild;



    public Encrypter()
    {
        Readfile(); //we take in the text file and put it into String input 
        //FrequencyTable(input);  // create a frequency table from input
        heap(input);

    
    }

    public void Readfile(){ //this first method handles reading files using scanner

        try {
            File text = new File("empty.txt");
            Scanner scan = new Scanner(text);

            while (scan.hasNextLine() == true) {
              String data = scan.nextLine();
              input = input + data;

            }
  
          } catch (FileNotFoundException e) {
            System.out.println("Input file not found");
            e.printStackTrace();
          } 
        }
    

    public void FrequencyTable(String s){ // create a frequency table
          
    int[] frequency = new int[s.length()];   
    char Charray[] = s.toCharArray();         
    
    System.out.println("The original input string is " + input);
    System.out.println();


    for(int i = 0; i <s.length(); i++) 
    {  
            frequency[i] = 1;  
            for(int j = i+1; j <s.length(); j++) 
            {  
                if(Charray[i] == Charray[j])
                {  
                    frequency[i]++;    
                    Charray[j] = '0';  
                }  
            }  
        }   


        
        System.out.println();
        System.out.println("The frequency table is:");
        System.out.println();

        for(int i = 0; i <frequency.length; i++) 
        {  
            if(Charray[i] != '0'){
                System.out.println(frequency[i] + " key " + Charray[i] + " char");}   
                    }
    }  


    public static void printCode(Node root, String s) // based off https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/?ref=lbp
    {
 

        if (root.left
                == null
            && root.right
                   == null
            && Character.isLetter(root.data)) {
 
            // c is the character in the node
            System.out.println(root.data + ":" + s);
 
            return;
        }
 

        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }
 
    
    public static void heap(String s){ // based off https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/?ref=lbp
        
        
        int[] frequency = new int[s.length()];   
        char Charray[] = s.toCharArray();         
    
    
        for(int i = 0; i <s.length(); i++) 
        {  
                frequency[i] = 1;  
                for(int j = i+1; j <s.length(); j++) 
                {  
                    if(Charray[i] == Charray[j])
                    {  
                        frequency[i]++;    
                        Charray[j] = '0';  
                    }  
                }  
            }   
 

        int n = frequency.length;

        PriorityQueue<Node> pq
            = new PriorityQueue<Node>(n, new MyComparator());
            // created a priority queue
 
        for (int i = 0; i < n; i++) { // add each node to the pq
 
            Node ned = new Node();
 
            ned.data = Charray[i];
            ned.key = frequency[i];
            ned.left = null;
            ned.right = null;
            pq.add(ned);
        }
 
        Node root = null;
 

        while (pq.size() > 1) {
 
            // first min extract.
            Node x = pq.peek();
            pq.poll();
 
            // second min extract.
            Node y = pq.peek();
            pq.poll();
 
            // new node f which is equal
            Node f = new Node();
 
            f.key = x.key + y.key;
            f.data = '-';
 

            f.left = x;
            f.right = y;
 

            root = f;
 
            // add this node to the priority-queue.
            pq.add(f);
        }
 
        // print the codes by traversing the tree
        printCode(root, "");
    }
    
    

    public static void main(String[] args) { Scanner s = new Scanner(System.in);
 
        
}}