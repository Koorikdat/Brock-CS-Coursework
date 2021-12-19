// created in IntelliJ

    package Assign4;

    import BasicIO.*;                // will handle the ascii data


    /**
     * 3/23/2021
     *
     *
     */
    public class Maze
    {

        char [][] Puzzle; // this 2d array will be our initial structure


        String A1, A2;
        int TempX, TempY, TempA, TempB; // will store tempdata for later use

        char h = 'H'; // taken from tutorial standards
        char g = 'G';
        int row, column; // to iterate through our positions

        // private ASCIIDisplayer display = new ASCIIDisplayer(); // this display is used to demonstrate in real time

        private ASCIIOutputFile MazeSkeleton, Solution;  //our working Output files
        private ASCIIDataFile InputtedMaze;  // the maze that we will feed into the program

        //constructor
        public Maze()
        {

            MazeSkeleton = new ASCIIOutputFile("SolvedMaze.txt");
            InputtedMaze = new ASCIIDataFile();

            String line = InputtedMaze.readLine();
            A1 = line.charAt(3) + "" + line.charAt(4);



            column = Integer.parseInt(A1); // parses through the values

            if(line.charAt(1) == ' ')
                A2 = line.charAt(0) + "";
            else
                A2 = line.charAt(0) + "" + line.charAt(1);
            row = Integer.parseInt(A2);

            Puzzle = new char [row][column+1];

            BuildMaze(); // this will build the skeleton structure of the maze with inputted txt file

            Construct();
            SolveMaze();// will output the final completed puzzle txt file

        }

        private void SolveMaze() {

            if(findPath(TempA, TempB))
            {

                Solution = new ASCIIOutputFile("SolvedMaze.txt");
                for(int i = 0; i< Puzzle.length; i++)
                {

                    for(int j = 0; j< Puzzle[i].length; j++)
                    {

                        if(i == TempA && j == TempB)
                        {
                            Puzzle[TempA][TempB] = g;
                        }
                        Solution.writeC(Puzzle[i][j]);
                    }
                }

            }
            else
            {
                System.out.print("There is no possible path");
            }


        }

        private void BuildMaze() // this will write the maze into the write file
        {
            for(int i = 0; i< Puzzle.length; i++)
            {

                for(int j = 0; j< Puzzle[i].length; j++)
                {

                    Puzzle[i][j] = InputtedMaze.readC();
                    int c = (int) Puzzle[i][j];

                    MazeSkeleton.writeC(Puzzle[i][j]);
                }
            }
        }

        //Private void BreakRecurse(){
    // try catch style exceptions in new method?}

        private void Construct() // handles the placement of our characters
        {
            int UpperBoundX = row - 1;
            int LowerBoundX = 0;

            int UpperBoundY = column - 2;
            int LowerBoundY = 0;

            do

            {
                TempX = (int)((UpperBoundX - LowerBoundX + 1) * Math.random() + LowerBoundX);
                TempY = (int)((UpperBoundY - LowerBoundY + 1) * Math.random() + LowerBoundY);
                // building between the predetermined bounds of our data

                TempA = (int)((UpperBoundX - LowerBoundX + 1) * Math.random() + LowerBoundX);
                TempB = (int)((UpperBoundY - LowerBoundY + 1) * Math.random() + LowerBoundY);


                if(Puzzle[TempX][TempY] != '#' && Puzzle[TempA][TempB] != '#')
                {
                    if(TempY != TempB | TempX != TempA)
                    {

                        InputtedMaze = new ASCIIDataFile();

                        MazeSkeleton = new ASCIIOutputFile("SolvedMaze.txt");
                        //display(Puzzle([TempA],[TempB]);



                        for(int i = 0; i< Puzzle.length; i++)
                        {

                            for(int j = 0; j< Puzzle[i].length; j++) // Iterate through the Array
                            {

                                Puzzle[i][j] = InputtedMaze.readC();
                                if(j == TempY && i == TempX) {

                                    Puzzle[TempX][TempY] = h;
                                    MazeSkeleton.writeC(Puzzle[TempX][TempY]);
                                }

                                else if(i == TempA && j == TempB) {

                                    Puzzle[TempA][TempB] = g;
                                    MazeSkeleton.writeC(Puzzle[TempA][TempB]);
                                }
                                else {

                                    MazeSkeleton.writeC(Puzzle[i][j]);
                                }
                            }
                        }

                        break;
                    }
                }
            }
            while(Puzzle[TempX][TempY] == '#' || Puzzle[TempA][TempB] == '#');
        }

        private boolean findPath (int r, int c) // this method will be responsible for the iteration and position checking

        {
            if(Puzzle[r][c] == h)
            {

                return true;
            }
            else if(Puzzle[r][c] == '#' || Puzzle[r][c] == 'A' || Puzzle[r][c] == '.') // indicates that there is a wall
            {
                return false;
            }

            Puzzle[r][c] = 'A';

            // these next few lines document the movement and iteration of Hansel

            // Document Upward Movement
            if(findPath(r-1, c))
            {
                Puzzle[r][c] = '^';
                return true;
                //display.writeString('^');
            }

            // Document Downward Movement
            if(findPath(r+1, c))
            {
                Puzzle[r][c] = 'v';
                return true;
                //display.writeString('v');
            }


            // Document Movement to the left
            if(findPath(r, c-1))
            {
                Puzzle[r][c] = '<';
                //display.writeString('we went left');
                return true;

            }

            // Document Movement to the right
            if(findPath(r, c+1))
            {
                Puzzle[r][c] = '>';
                //display.writeString('we went right' + Counter);
                return true;
            }



            // Cell does not lead to path
            Puzzle[r][c] = '.';
            return false;
        }





        public static void main(String[] args) { Maze m = new Maze();
        }
    }