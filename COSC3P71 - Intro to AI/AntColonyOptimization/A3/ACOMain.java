import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ACOMain {

    // Declaring all global variable here
    public int vertex;
    public int CitiesSize =5;
    public Node[][] matrix;
    public int[][] HeuristicMatrix = new int[CitiesSize][CitiesSize];
    public double[][] PheromonesMatrix = new double[CitiesSize][CitiesSize];
    public int Distances;
    public double alpha;
    public double beta;
    public double numAnts;
    public boolean visited;

    public ACOMain(int i) {
    }


    public void readFile() throws FileNotFoundException { //This function is intended to take in the TSP file
        File input = new File("eli51.tsp");
        Scanner scan = new Scanner(input);

        ArrayList<String[]> data = new ArrayList<>();

        while (scan.hasNextLine()){
            String num = scan.nextLine();
            data.add(num.trim().split(" "));

        }





    }

    public double CalcDistance(Node NodeA, Node NodeB){ // finds the euclidean distances, formula from tutorial slide
        double distance;
        distance = Math.sqrt((NodeB.x - NodeA.x)^2 + (NodeB.y - NodeA.y)^2);
        System.out.println(distance);
        return (distance);
    }

    public void addEdge(Node fromNode, Node toNode) { // Creates edge to and from between 2 points

        Node node = new Node(true);
        matrix[fromNode.numCity][toNode.numCity] = node;
        matrix[toNode.numCity][fromNode.numCity] = node;
    }

    public void returnGraph() { // display the graph in terminal


        Node node=new Node();
        for (int i = 0; i < vertex; i++) {
            for (int j = 0; j < vertex; j++) {
                if(matrix[i][j]!=null){
                    System.out.print(matrix[i][j].hasEdge + " ");}
                else System.out.println(matrix[i][j]);

            }
            System.out.println();
        }
        for (int i = 0; i < vertex; i++) {
            System.out.print("The point " + i + "shares edges with - ");
            for (int j = 0; j < vertex; j++) {
                if ((matrix[i][j] != node) || !node.hasEdge) continue;
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    public void buildHeurMatrix(Node nodeA, Node nodeB){ //create an adjacency matrix with euclidean distances

        Node point = new Node();
        double distance;

        for (int i = 1; i <= vertex; i++) {

            System.out.print("");
            for (int j = 0; j < vertex; j++) {
                if(!(!point.hasEdge || matrix[i][j] != point)){
                    distance = CalcDistance(nodeA, nodeB);
                    HeuristicMatrix[i][j] = (int)distance;


                }
                else HeuristicMatrix[i][j] = Integer.parseInt(null);}
        }}

    public void buildPheromMatrix(){ // initialize phermone matrix with random 1 and zero doubles

        for (int i = 0; i< PheromonesMatrix.length; i++){
            for(int j = 0; j< PheromonesMatrix[i].length; j++){

                PheromonesMatrix[i][j] = Math.round( Math.random() )  ;

            }


        }
        System.out.println(Arrays.deepToString(PheromonesMatrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

    }



    public static void main(String[] args) {


        ACOMain graph = new ACOMain(5); //initialize a graph of size 5


        Node Node1 = new Node(37,52,1);
        Node Node2 = new Node(49,49,2);
        Node Node3 = new Node(52,64,3);
        Node Node4 = new Node(20,26,4);

        graph.addEdge(Node1, Node2);
        graph.addEdge(Node2, Node3);
        graph.addEdge(Node3, Node1);
        graph.returnGraph();

        AntInit ant = new AntInit();


//    }
//        while(ant == false){
    }



}