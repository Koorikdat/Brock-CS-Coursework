package Mazefinding;


import java.io.*;
import java.util.*;


public class Node {
    int row, column, depth, cost;

    Node(int row, int column, int depth, int cost) {
        this.row = row;
        this.column = column;
        this.depth = depth;
        this.cost = cost;

    }
}
