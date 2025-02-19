import java.util.Comparator;

class MyComparator implements Comparator<Node> {
    public int compare(Node x, Node y)
    {
 
        return x.key - y.key;
    }
}