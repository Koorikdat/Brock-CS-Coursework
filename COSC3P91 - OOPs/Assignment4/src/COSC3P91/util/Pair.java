package COSC3P91.util;

import java.io.Serializable;

public class Pair<X,Y> implements Cloneable, Serializable {
	
    private X first;			
    private Y second;			

    public <A extends X,B extends Y> Pair(A a,B b) {
        first = a;
        second = b;
    } 

    public Pair(Pair<? extends X, ? extends Y> p) {
        first = p.getFirst();
        second = p.getSecond();
    }

    public X getFirst() {
        return first;
    } 

    public Y getSecond() {
        return second;
    } 

    public void setFirst(X x) {
        first = x;
    } 

    public void setSecond(Y y) {
        second = y;
    } 
    
    @Override
    public Pair<X,Y> clone() throws CloneNotSupportedException {
        return (Pair<X,Y>) super.clone();
    } 

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof Pair<?,?>) {
            Pair<?,?> p = (Pair<?,?>) obj;
            result = first == p.getFirst() && second == p.getSecond();
        }
        return result;
    } 

    @Override
    public int hashCode() {
        return first.hashCode() + second.hashCode();
    } 

    @Override
    public String toString() {
        return "Pair(" + first + "," + second +")";
    } 	
}