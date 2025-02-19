package everybodyintothepool;
//Note: This is the same package you should use for your implementation!

import java.util.List;
/*This interface represents the idea of a 'Pool Queue': a data structure that behaves
 *similarly to a queue, but with some slight differences (refer to the assignment sheet).
 */

public interface PoolQueue<E> {
	/*The generalized version of this would have 'add(E... item)' and 'remove(int number)'
	 *The former would be an example of variadic parameters, or 'Varargs',
	 *(you can invoke the method with an arbitrary number of parameters, and the
	 *implemented method would simply receive them all in an array).
	 *The latter is RECURSIVE.
	 *
	 *The specification only includes options for 1-4 entries at a time, but:
	 *****STILL INCLUDE THESE GENERALIZED VERSIONS AS HELPER METHODS!
	 *(and offload the other versions to them).
	 *    This means you will not receive full credit unless you include a recursive, generalized
	 *    remove, and a variadic add.
	 *    These should both be private (and you can add additinal parameters to the remove, if it helps).
	 *    The removePair, removeTriple, etc. all defer the 'real work' to the private function
	 */
	
	//Check out here: http://docs.oracle.com/javase/1.5.0/docs/guide/language/varargs.html
	//	and here: https://docs.oracle.com/javase/7/docs/api/java/lang/SafeVarargs.html
	
	public void addSingle(E item);
	
	public void addPair(E first, E second);
	
	public void addTriple(E first, E second, E third);
	
	public void addQuartet(E first, E second, E third, E fourth);
	
	public E removeSingle(); //Note that this one doesn't return a list
	
	public List<E> removePair();
	
	public List<E> removeTriple();
	
	public List<E> removeQuartet();
	
	public boolean hasSingle();
	
	public boolean hasPair();
	
	public boolean hasTriple();
	
	public boolean hasQuartet();
	
	public int count(); //Total # of items waiting (NOT # of groups!)
}
