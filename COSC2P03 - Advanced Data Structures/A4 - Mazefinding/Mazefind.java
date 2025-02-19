package Mazefinding;

import java.io.*;
import java.util.*;


public class Mazefind
{
    static int width;
    static int height;
    static int depth;
    String Directions = "";

    public Mazefind()
    {
        Scanner UIscan = new Scanner(System.in);
        boolean done = false;
        String menu = ("""
                1. Solve suboptimally
                2. Estimate optimal solution cost
                3. Solve Optimally
                4. Goodbye!""");
        System.out.println(menu);

        while (done == false){

            int option = UIscan.nextInt();

            if(option ==1){
                 Directions = "";
                System.out.println("Solve suboptimally");
                returnPath();
            } else if  (option ==2){
                 Directions = "";
                System.out.println("Estimate optimal solution cost");
                returnMinimum();
            } else if  (option ==3){
                 Directions = "";
                System.out.println("Solve Optimally");
                returnPath();
            } else if  (option ==4) {
                System.out.println("Goodbye!");
                done = true;
    }}
    }




    public static char[][][] inputMaze() {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter a width:");
            width = Integer.parseInt(br.readLine());
            System.out.println("Enter a height:");
            height = Integer.parseInt(br.readLine());
            System.out.println("Enter a depth:");
            depth = Integer.parseInt(br.readLine());

            System.out.println("-------------------------------------");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Scanner scan = new Scanner(System.in);
        char[][][] Maze = new char[depth][height][width];

        System.out.println("Enter maze below, Only rows of width " + width + " will be accepted");


        int i = 0;
        while (i < Maze.length) {

            for (int j = 0; j < Maze[0].length; j++) {
                String input = scan.nextLine();

                if (input.length() == 0) {
                    i++;
                    j--;
                    continue;
                }
                Maze[i][j] = input.toCharArray();


            }
            i++;
        }

        return Maze;
    }


    public Node Solver(char[][][] Maze) {


        int searchRows = 0;
        int searchColumns = 0;
        int searchDepths = 0;
        boolean[][][] marked = new boolean[Maze.length][Maze[0].length][Maze[0][0].length];
        LinkedList<Node> List = new LinkedList<>();


        for (int i = 0; i < Maze.length; i++) {
            for (int j = 0; j < Maze[0].length; j++) {
                for (int k = 0; k < Maze[0][0].length; k++) {
                    if ( 'S' == (Maze[i][j][k])) {
                        searchRows = i;
                        searchColumns = j;
                        searchDepths = k;
                        j = Maze[0].length;
                        i = Maze.length;
                        break;
                    }
                }
            }
        }


        Node head = new Node(searchRows, searchColumns, searchDepths, 0);
        List.add(head);


        while (List.size() > 0) {
            Node poppedHead = List.remove();
            if (Maze[poppedHead.row][poppedHead.column][poppedHead.depth] == 'E') {
                return poppedHead;
            }
            if (marked[poppedHead.row][poppedHead.column][poppedHead.depth]) {
                continue;
            } else {
                marked[poppedHead.row][poppedHead.column][poppedHead.depth] = true;
            }



            if (poppedHead.column + 1 < Maze[0].length && Maze[poppedHead.row][poppedHead.column + 1][poppedHead.depth] != 'X'
                    && marked[poppedHead.row][poppedHead.column + 1][poppedHead.depth] == false) {
                Node newNode = new Node(poppedHead.row, poppedHead.column + 1, poppedHead.depth, poppedHead.cost + 1);
                    List.add(newNode);
                    Directions += "S";
            }

            if (poppedHead.column - 1 >= 0 && Maze[poppedHead.row][poppedHead.column - 1][poppedHead.depth] != 'X' && marked[poppedHead.row][poppedHead.column - 1][poppedHead.depth] == false) {
                Node newNode = new Node(poppedHead.row, poppedHead.column - 1, poppedHead.depth, poppedHead.cost + 1);
                    List.add(newNode);
                    Directions += "N";
            }

            if (poppedHead.row - 1 >= 0 && Maze[poppedHead.row - 1][poppedHead.column][poppedHead.depth] != 'X' && marked[poppedHead.row - 1][poppedHead.column][poppedHead.depth] == false) {
                Node tempNode = new Node(poppedHead.row - 1, poppedHead.column, poppedHead.depth, poppedHead.cost + 1);
                    List.add(tempNode);
                    Directions += "U";
            }

            if (poppedHead.row + 1 < Maze.length && Maze[poppedHead.row + 1][poppedHead.column][poppedHead.depth] != 'X'
                    && marked[poppedHead.row + 1][poppedHead.column][poppedHead.depth] == false) {
                Node newNode = new Node(poppedHead.row + 1, poppedHead.column, poppedHead.depth, poppedHead.cost + 1);
                    List.add(newNode);
                    Directions += "D";}

            if (poppedHead.depth - 1 >= 0 && Maze[poppedHead.row][poppedHead.column][poppedHead.depth - 1] != 'X' && marked[poppedHead.row][poppedHead.column][poppedHead.depth - 1] == false) {
                Node newNode = new Node(poppedHead.row, poppedHead.column, poppedHead.depth - 1, poppedHead.cost + 1);
                    List.add(newNode);
                    Directions += "W";}

            if (poppedHead.depth + 1 < Maze[0][0].length && Maze[poppedHead.row][poppedHead.column][poppedHead.depth + 1] != 'X'
                    && marked[poppedHead.row][poppedHead.column][poppedHead.depth + 1] == false) {
                Node newNode = new Node(poppedHead.row, poppedHead.column, poppedHead.depth + 1, poppedHead.cost + 1);
                    List.add(newNode);
                    Directions += "E";
            }


        }
        return null;
    }



    public void returnPath(){

        char[][][] maze = inputMaze();
        Node MinimumCost = Solver(maze);

        if (MinimumCost == null && MinimumCost.cost <= 0 ) {
            System.out.println("the maze is unsolvable");
        } else {
            System.out.println("the path is : " + Directions);
        }

    }

    public void returnMinimum(){
        char[][][] maze = inputMaze();
        Node minCost = Solver(maze);
        System.out.println("Min path cost : " + minCost.cost);

    }



    public static void main(String[] args) { Mazefind s = new Mazefind();
    }
}
