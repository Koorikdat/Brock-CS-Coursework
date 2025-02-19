public class Node {
    int x;
    int y;
    int numCity;
    boolean hasEdge = true;

    public Node(){

    }
    public Node(int x,int y,int numCity){
        this.x=x;
        this.y=y;
        this.numCity = numCity;

    }
    public Node(boolean hasEdge){
        this.hasEdge = hasEdge;

    }

}
