package edu.uncc.cs.bridgesdrivers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import edu.uncc.cs.bridges.AbstractEdge;
import edu.uncc.cs.bridges.AbstractVertex;
import edu.uncc.cs.bridges.Follower;
import edu.uncc.cs.bridges.Vertex;

public class minHeapEdges {
	static int numberOfRecursiveCalls=1;
	static RandomGraph<Follower> graph;
	static ArrayList<AbstractEdge<Follower>> minHeap=new ArrayList<AbstractEdge<Follower>>();;
	static ArrayList<AbstractEdge<Follower>> minHeapSorted = new ArrayList<AbstractEdge<Follower>>();
	static Vertex<Follower> root;
	static int heapElements=0;
	
	/**
	 * print method prints the heap to the standard output
	 * @param minHeapDriver
	 */
	public static void print (ArrayList<AbstractEdge<Follower>> aHeap){
		
		for (AbstractEdge<Follower> F:aHeap)
				if(F!=null)
					System.out.println(F.getIdentifier()+" "+" Edge value is: "+F.getWeight());
		System.out.println();
	}
	
	/**
	 * This method removes the root element repeatedly until the heap is empty
	 * @param minHeapDriver
	 */
	public static AbstractEdge<Follower> removeHeap(ArrayList<AbstractEdge<Follower>> aHeap, int anIndex){
		if(anIndex==1){
			System.out.println(aHeap.get(1).getIdentifier()+" "+aHeap.get(1).getWeight());
			reHeap(aHeap,1);
			print(aHeap);
			return aHeap.remove(anIndex);
		} 
		else{
			swap(1,anIndex);
			aHeap.remove(anIndex);
			reHeap(aHeap,1);
			print(aHeap);
			return removeHeap(aHeap, aHeap.size()-1);
		}
	}
	/**
	 * This method returns the root value of the minHeap, removes the value and rearranges the heap
	 * @param minHeap
	 * @return
	 */
	public static AbstractEdge<Follower> collectTopElement(ArrayList<AbstractEdge<Follower>>minHeap){
		if (minHeap.isEmpty())
			return null;
		
		AbstractEdge<Follower> aMinEdge = minHeap.get(1);
		swap(1,minHeap.size()-1);
		minHeap.remove(minHeap.size()-1);
		reHeap(minHeap,1);
		
		return aMinEdge;			
	}
	
	/**
	 * This method swaps two elements of the heap
	 * @param i
	 * @param j
	 */
	 public static void swap(int i, int j){
		 AbstractEdge<Follower> temp=minHeap.get(i);
		 minHeap.set(i, minHeap.get(j));
		 minHeap.set(j, temp);
	 }
	
	/**
	 * The method returns the value of the edge between the root
	 * and the next child vertex
	 * @param i
	 * @return
	 */
	public static double getValue(int i){
		return minHeap.get(i).getWeight();
	}
	
	/**
	 * This method determines if the current array is a Heap
	 * @param A
	 * @param i
	 * @return
	 */
	public static boolean isHeap(ArrayList<AbstractEdge<Follower>> minHeap,int i){
		if (2*i>minHeap.size()-1 || 2*i+1>minHeap.size()-1)
			return true;
		else if (getValue(i)>Math.max(getValue(2*i),getValue(2*i+1))){
			return false;
		}
		return isHeap(minHeap,2*i) && isHeap(minHeap,2*i+1);
	}
	
	
	/**
	 * This method rearranges the minimum Heap such that minimum values are in root position
	 * according to definition 
	 * @return arranged heap
	 */
	public static ArrayList<AbstractEdge<Follower>> reHeap(ArrayList<AbstractEdge<Follower>> minHeap,int i){
		 if (i==minHeap.size()-1 || i*2>minHeap.size()-1|| (i*2+1>minHeap.size()-1))
			 return minHeap;
		 
  		boolean leftSmaller=true;  //left child is smaller than the right
		 if (minHeap.get(i*2+1)!=null && getValue(i*2)>getValue(i*2+1))
			 leftSmaller=false;    //right child is larger
		 if (leftSmaller && getValue(i)>getValue(i*2)){
			 swap(i,i*2);
			 i*=2;
			 reHeap(minHeap, i);
		 }
		 else if (!leftSmaller && getValue(i)>getValue(i*2+1)){
			 swap(i,i*2+1);
			 i=i*2+1;
			 reHeap(minHeap, i);
		 }
		 
		 return minHeap;
	}
	
	/**
	 * This method rearranges the minimum Heap after adding an element, O(log n)
	 * It adds the element at the last position and swaps it repeatedly 
	 * with the  its parent if its value is smaller
	 * @param minHeap
	 * @param anIndex
	 * @return
	 */
	public static ArrayList<AbstractEdge<Follower>> rearrange(ArrayList<AbstractEdge<Follower>> minHeap, int anIndex){
		if(anIndex == 1)
			return minHeap;
		if(getValue(anIndex)<getValue(anIndex/2)){
			swap(anIndex, anIndex/2);
		}
		return rearrange(minHeap,anIndex/2);
	}
	
	/**
	 * This method creates the heap based on the data available in graph
	 * @param minHeap
	 * @param root
	 * @return
	 */
	public static ArrayList<AbstractEdge<Follower>> createHeap(ArrayList<AbstractEdge<Follower>> minHeap, Vertex<Follower> root){
		Iterator<Entry<AbstractVertex<?>, String>> i=root.getNeighbors().entrySet().iterator();
		minHeap.add(null);  //the heap has a null element on its first position (index 0) this allows finding children by 2*index and 2*index+1
		while(i.hasNext()){
			minHeap.add(root.getEdge(i.next().getKey()));
			rearrange(minHeap, minHeap.size()-1);
		}
		return minHeap;
	}
	
	/**
	 * This method performs heapSort recursively by repeatedly removing the root element and performing reheap
	 * The result is stored in minHeapSorted in ascending order
	 * @param minHeap
	 * @param anIndex
	 * @return
	 */
	public static AbstractEdge<Follower> heapSort(ArrayList<AbstractEdge<Follower>> minHeap, int anIndex){
		if(anIndex==1){
			reHeap(minHeap,1);
			//print(minHeap);
			minHeapSorted.add(minHeap.get(anIndex));
			return minHeap.get(anIndex);
		} 
		else{
			swap(1,anIndex);
			minHeapSorted.add(minHeap.remove(anIndex));
			reHeap(minHeap,1);
			//print(minHeap);
			return heapSort(minHeap, minHeap.size()-1);
		}
		
	}
	

}
